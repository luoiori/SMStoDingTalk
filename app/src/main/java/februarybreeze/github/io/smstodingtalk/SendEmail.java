package februarybreeze.github.io.smstodingtalk;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private static String from = "iorilawcn@163.com";
    private static String host = "smtp.163.com";
    private static String port = "465";
    private static String username = "iorilawcn@163.com";
    private static String password = "luo13907256758";

    public static void sendEmail(String address,String subject, String content) throws Exception {
        Properties properties = new Properties();

        // 设置邮件服务器
        properties.setProperty("mail.transport.protocol", "SMTP");
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", true);

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        // 登陆邮件发送服务器的用户名和密码
                        return new PasswordAuthentication(username, password);
                    }
                });

        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);

        // Set From: 头部头字段
        message.setFrom(new InternetAddress(from));

        // Set To: 头部头字段
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));

        // Set Subject: 头部头字段
        message.setSubject(subject);

        // 设置消息体
        message.setContent(content,"text/html;charset=utf-8");

        // 发送消息
        try {
            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
