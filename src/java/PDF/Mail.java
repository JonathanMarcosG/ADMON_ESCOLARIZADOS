package PDF;




//import Beans.BMail;
import Beans.BMail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class Mail {

    public boolean sendMail(BMail beanMail, String from, String to, String pass, boolean link,String asunto) {
        boolean retorno = false;
        Properties props = System.getProperties();
        props.setProperty("mail.mime.charset", "ISO-8859-1");
        String host = "mail.ittoluca.edu.mx"; //itt
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.localhost", "sia.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "mail.ittoluca.edu.mx");
        props.put("mail.smtp.ehlo", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.store.protocol", "pop3");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(asunto);
            message.setText("Cuerpo");

            message.setText(beanMail.getCuerpo(), "ISO-8859-1", "html");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            try {
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
                retorno = true;
            } catch (MessagingException ex) {
            }
        } catch (MessagingException me) {
            me.printStackTrace();
            retorno = false;
        }
        return retorno;
    }
}
