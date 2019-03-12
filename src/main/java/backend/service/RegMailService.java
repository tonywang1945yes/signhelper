package backend.service;


import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.dao.service.MailCaptchaRepository;
import backend.dao.service.StudentRepository;
import backend.dao.service.UserRepository;
import backend.entity.Administer;
import backend.entity.MailCaptcha;
import backend.entity.Student;
import backend.entity.User;
import backend.enums.StudentState;
import backend.response.BasicResponse;
import backend.response.EmailResponse.SETAdmissionResponse;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import static backend.enums.StudentState.*;
import static backend.enums.StudentState.JUNIOR_PASSED;

@Service
public class RegMailService {
    //使用@Value注解导入值
//    @Value("${sendAddress}")
//    String sendAddress;
//
//    @Value("${mailPassword}")
//    String password;

    @Value("${GroupSendMail}")
    String content;

    @Autowired
    AdministerRepository adminRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    StudentRepository studentRepo;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;


    public boolean setEmailPermission(String emailAddress, String permission) {
        List<Administer> list = adminRepo.findAll();
        if(list.size()==0){
            Administer admin =new Administer();
            admin.setEmailAddress(emailAddress);
            admin.setEmailadmission(permission);
            adminRepo.save(admin);
            return true;
        }
        else if(list.size()!=0){
            Administer admin = list.get(0);
            admin.setEmailAddress(emailAddress);
            admin.setEmailadmission(permission);
            adminRepo.save(admin);
            return true;
        }
        return false;
    }

    /**
     * @param emailAddress
     * @throws FileNotFoundException
     * @throws IOException              getProperties方法中的异常
     * @throws GeneralSecurityException sendSimpleMail方法异常
     * @throws MessagingException       Message方法调用异常sendSimpleMail方法中
     * @throws Exception                getHost方法中的host不存在异常，这个后面会改的更明确
     */
    @Transactional
    public void sendVerificationCode(String emailAddress) throws GeneralSecurityException, MessagingException, Exception {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            code[i] = codeSequence[(int) (Math.random() * 35)];//其实也许可以用SecureRandom？
        }
        String codeStr = String.valueOf(code);

        MailCaptcha mailCaptcha;
        List<MailCaptcha> res = mailCaptchaRepo.findByEmailAddress(emailAddress);
        mailCaptcha = (res == null || res.size() == 0) ? new MailCaptcha() : res.get(0);//设定每个用户同时最多只有一个验证码
        mailCaptcha.setEmailAddress(emailAddress);
        mailCaptcha.setCode(codeStr);
        mailCaptcha.setBuiltTime(Calendar.getInstance());
        mailCaptchaRepo.save(mailCaptcha);

        String prefix = "您(或他人)在申請重置南京大學臺灣免試生報名系統的賬戶密碼。\n\n" +
                "如果該操作不是由您本人做出，請無視這封郵件。\n\n";
        String body1 =
                "為了重置您的密碼，您需要輸入該郵件中附帶的驗證碼以驗證您的身份(請註意不要洩露該驗證碼以免讓您的資訊面臨洩露的風險)。\n\n" +
                        "驗證碼為 %s , 有效時間為30min，過期失效。\n\n";
        String body2 = "然而該郵箱並不在我們的數據庫記錄裏， 因此重置密碼操作將不能進行。\n\n" +
                "如果您確實擁有網站的賬戶並想重置密碼，請重試並使用註冊賬戶時所使用的郵箱。\n\n";
        String postfix = "如有疑問，請撥打招生電話 86-4001859680 咨詢。";

        StringBuilder fullContent = new StringBuilder();
        fullContent.append(prefix).append(userRepo.existsById(emailAddress) ? body1 : body2).append(postfix);

//        String sendAddress = getProperty("sendAddress");  //有这么个发件人地址,
//        String password = getProperty("mailPassword");  //有这么个密码
        String subject = "南京大學臺灣免試生報名系統重置密碼驗證碼";

        sendSimpleMail(subject, emailAddress, String.format(fullContent.toString(), codeStr));
        //随机数生成完毕
    }


    /**
     * @param subject
     * @param receiveAddress
     * @param content
     * @throws GeneralSecurityException init出现异常
     * @throws MessagingException       Message发送出现异常，一般是方法调用问题
     * @throws Exception                host未找到异常
     */
    private void sendSimpleMail(String subject, String receiveAddress, String content) throws GeneralSecurityException, MessagingException, Exception {
        Administer admin = adminRepo.findAll().get(0);

        MimeMessage message = new MimeMessage(init(admin.getEmailAddress(), admin.getEmailadmission()));
        message.setFrom(new InternetAddress(admin.getEmailAddress()));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));// 收件人
        message.setSubject(subject);// 主题
        message.setText(content);// 内容
        Transport.send(message);
        System.out.println("The message has been sent.");
    }

    /**
     * @param sendAddress
     * @param password
     * @return
     * @throws GeneralSecurityException
     * @throws Exception                继续上抛host未找到问题
     */
    private static Session init(final String sendAddress, final String password)
            throws GeneralSecurityException, Exception {
        String host = getHost(sendAddress);// 邮件服务器
        Properties properties = System.getProperties();// 系统设置
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();// SSL
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {// 授权
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendAddress, password);
            }
        });
        return session;
    }

    /**
     * @param sendAddress
     * @return
     * @throws Exception host未找到
     */
    private static String getHost(String sendAddress) throws Exception {
        if (sendAddress.endsWith("@smail.nju.edu.cn")) {
            return "smtp.exmail.qq.com";
        } else if (sendAddress.endsWith("@qq.com")) {
            return "smtp.qq.com";
        } else if (sendAddress.endsWith("@126.com")) {
            return "smtp.126.com";
        } else if (sendAddress.endsWith("@163.com")) {
            return "smtp.163.com";
        } else if (sendAddress.endsWith("@sina.com")) {
            return "smtp.sina.com.cn";
        } else if (sendAddress.endsWith("@sohu.com")) {
            return "smtp.sohu.com.cn";
        } else if (sendAddress.endsWith("@139.com")) {
            return "smtp.139.com";
        } else {
            throw new Exception("Unknown Host.");
        }
    }

    public boolean checkCodeAndReset(String email, String password, String code) {
        List<MailCaptcha> res = mailCaptchaRepo.findByEmailAddress(email);
        if (res == null || res.size() == 0)
            return false;
        MailCaptcha mailCaptcha = res.get(0);
        Calendar limit = Calendar.getInstance();
        limit.add(Calendar.MINUTE, -30);
        if (mailCaptcha != null && mailCaptcha.getCode().equals(code) && mailCaptcha.getBuiltTime().after(limit)) {
            User user = userRepo.getOne(email);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPasswordEncoded(passwordEncoder.encode(password));
            user.setLastPasswordResetDate(Calendar.getInstance());
            userRepo.save(user);
            return true;
        } else {
            return false;
        }
    }

    public BasicResponse groupSendMail(int from) throws FileNotFoundException, IOException, SecurityException, MessagingException, GeneralSecurityException, Exception {
        BasicResponse response = new BasicResponse();
        String subject = "南京大學申請進度通知";
        StudentState state_pass;
        StudentState state_failed;
        switch (from){
            case 0:state_pass= JUNIOR_PASSED;state_failed=JUNIOR_FAILED;break;
            case 1:state_pass=SENIOR_PASSED;state_failed=SENIOR_FAILED;break;
            default:response.setSucceed(false);response.setMsg("No such state");return response;
        }
        List<Student> stuList = studentRepo.findAll();
        for (int i = 0; i < stuList.size(); i++) {
            if(stuList.get(i).getStudentState()==state_pass||stuList.get(i).getStudentState()==state_failed) {
                String receiveAddress = stuList.get(i).getEmail();
                sendSimpleMail(subject, receiveAddress, content);
            }
        }
        response.setMsg("");
        response.setSucceed(true);
        return response;
    }

    public boolean checkIdentity(String email, String idCardNumber) {
        if (studentRepo.existsById(email)) {
            Student student = studentRepo.findById(email).get();
            if (student.getIdentityNum().equals(idCardNumber))
                return true;
        }
        return false;
    }

    public SETAdmissionResponse getSETAdmission(){
        List<Administer> list = adminRepo.findAll();
        if(list.size()!=0){
            SETAdmissionResponse response = new SETAdmissionResponse();
            response.email = "";
            response.admission = "";
            if(list.get(0).getEmailAddress() != null){
                response.email = list.get(0).getEmailAddress();
            }
            if(list.get(0).getEmailadmission()!=null){
                response.admission = list.get(0).getEmailadmission();
            }

            return response;
        }
        else {
            return new SETAdmissionResponse("No admin","No admin");
        }
    }

}
