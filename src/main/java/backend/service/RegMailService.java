package backend.service;


import backend.dao.impl.HibernateDao;
import backend.entity.MailCaptcha;
import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Service
public class RegMailService {


    /**
     *
     * @param name
     * @param emailAddress
     * @throws FileNotFoundException
     * @throws IOException  getproperties方法中的异常
     * @throws GeneralSecurityException sendSimpleMail方法异常
     * @throws MessagingException   Message方法调用异常sendSimpleMail方法中
     * @throws Exception    gethost方法中的host不存在异常，这个后面会改的更明确
     */
    public void InsertCode(String name,String emailAddress)throws FileNotFoundException, IOException,GeneralSecurityException,MessagingException,Exception{
        char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        char[] code=new char[6];
        for(int i=0;i<6;i++){
            code[i]=codeSequence[(int)(Math.random()*35)];
        }
        String codestr=String.valueOf(code);

        String sendAddress=getproperty("sendAddress");  //有这么个发件人地址,
        String password=getproperty("mailpassword");  //有这么个密码
        String subject="驗證碼";

        sendSimpleMail(sendAddress,password,subject,emailAddress,codestr);
        //随机数生成完毕
    }

    /**
     * 从application,properties中调用需要的元素
     * @param key
     * @return
     * @throws FileNotFoundException    没找到文件
     * @throws IOException
     */
    public String getproperty(String key)throws FileNotFoundException,IOException{
        Properties properties=new Properties();
        InputStream input =null;
        input=new FileInputStream(".\\src\\main\\resources\\application.properties");
        properties.load(input);
        String result=properties.getProperty(key);
        input.close();
        return result;
    }

    /**
     *
     * @param sendAddress
     * @param password
     * @param subject
     * @param receiveAddress
     * @param content
     * @throws GeneralSecurityException init出现异常
     * @throws MessagingException   Message发送出现异常，一般是方法调用问题
     * @throws Exception    host未找到异常
     */
    private static void sendSimpleMail(String sendAddress, String password, String subject, String receiveAddress, String content)throws GeneralSecurityException,MessagingException,Exception{
        MimeMessage message = new MimeMessage(init(sendAddress, password));
        message.setFrom(new InternetAddress(sendAddress));// 发件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));// 收件人
        message.setSubject(subject);// 主题
        message.setText(content);// 内容
        Transport.send(message);
        System.out.println("The message has been sent.");
    }

    /**
     *
     * @param sendAddress
     * @param password
     * @return
     * @throws GeneralSecurityException
     * @throws Exception 继续上抛host未找到问题
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
     *
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

    public  boolean checkCode(String emailAddress,String code){
        HibernateDao<MailCaptcha> dao=new HibernateDao<MailCaptcha>(new MailCaptcha());
        MailCaptcha mailCaptcha=dao.findByKey(emailAddress);
        if(mailCaptcha!=null && mailCaptcha.getCode()==code){
            return true;
        }
        else {
            return false;
        }
    }

}
