package backend.service;


import backend.dao.impl.HibernateDao;
import backend.dao.service.AdministerRepository;
import backend.dao.service.MailCaptchaRepository;
import backend.dao.service.UserRepository;
import backend.entity.Administer;
import backend.entity.MailCaptcha;
import backend.entity.Student;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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
    UserRepository repository;

    @Autowired
    MailCaptchaRepository mailCaptchaRepo;


    public void setEmailPermission(String emailAddress,String permission){
        Administer admin = adminRepo.findAll().get(0);
        admin.setEmailAddress(emailAddress);
        admin.setEmailadmission(permission);
        adminRepo.save(admin);
    }

    /**
     * @param name
     * @param emailAddress
     * @throws FileNotFoundException
     * @throws IOException              getProperties方法中的异常
     * @throws GeneralSecurityException sendSimpleMail方法异常
     * @throws MessagingException       Message方法调用异常sendSimpleMail方法中
     * @throws Exception                getHost方法中的host不存在异常，这个后面会改的更明确
     */
    public void insertCode(String name, String emailAddress) throws GeneralSecurityException, MessagingException, Exception {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] code = new char[6];
        for (int i = 0; i < 6; i++) {
            code[i] = codeSequence[(int) (Math.random() * 35)];//其实也许可以用SecureRandom？
        }
        String codeStr = String.valueOf(code);

        MailCaptcha mailCaptcha = new MailCaptcha();
        mailCaptcha.setEmailAddress(emailAddress);
        mailCaptcha.setCode(codeStr);
        mailCaptcha.setBuiltTime(Calendar.getInstance());
        mailCaptchaRepo.save(mailCaptcha);

        String prefix = "您(或者其他人)在重置我們的臺灣學生註冊網站的賬戶密碼時輸入了該郵箱地址。\n\n" +
                "如果該操作不是由您本人做出，請無視這封郵件。\n\n";
        String body1 =
                "為了重置您的密碼，您需要輸入該郵件中附帶的驗證碼以驗證您的身份(請註意不要泄露該驗證碼以免讓您的信息面臨泄露的風險)。\n\n" +
                        "驗證碼為 %s , 有效時間為30min，過期失效。\n\n";
        String body2 = "然而該郵箱並不在我們的數據庫記錄裏， 因此重置密碼操作將不能進行。\n\n" +
                "如果您確實擁有網站的賬戶並想重置密碼，請重試並使用註冊賬戶時所使用的郵箱。\n\n";
        String postfix = "如有疑問，請訪問網站 xxxx 或咨詢網站管理員。";

        StringBuilder fullContent = new StringBuilder();
        fullContent.append(prefix).append(repository.existsById(emailAddress) ? body1 : body2).append(postfix);

//        String sendAddress = getProperty("sendAddress");  //有这么个发件人地址,
//        String password = getProperty("mailPassword");  //有这么个密码
        String subject = "xxxx網站驗證碼";

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

    public boolean checkCode(String emailAddress, String code) {
        HibernateDao<MailCaptcha> dao = new HibernateDao<MailCaptcha>(new MailCaptcha());
        MailCaptcha mailCaptcha = dao.findByKey(emailAddress);
        Calendar limit = Calendar.getInstance();
        limit.add(Calendar.MINUTE, -30);
        if (mailCaptcha != null && mailCaptcha.getCode().equals(code) && mailCaptcha.getBuiltTime().after(limit)) {
            return true;
        } else {
            return false;
        }
    }

    public void groupSendMail() throws FileNotFoundException, IOException, SecurityException, MessagingException, GeneralSecurityException, Exception {
//        String sendAddress = getProperty("sendAddress");  //有这么个发件人地址,
//        String password = getProperty("mailpassword");  //有这么个密码
        String subject = "南京大學進度申請通知";
//        String content = getProperty("GroupSendMail");

        HibernateDao<Student> dao = new HibernateDao<>(new Student());
        List<Student> stuList = dao.getAllObjects();
        for (int i = 0; i < stuList.size(); i++) {
            String receiveAddress = stuList.get(i).getAddress();
            sendSimpleMail(subject, receiveAddress, content);
        }
    }

}
