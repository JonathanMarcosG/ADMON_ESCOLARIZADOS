/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Utils.Constants;
import java.net.URISyntaxException;
import mx.edu.ittoluca.logutils.*;

import java.util.ArrayList;
import java.util.Properties;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import quart.TriggerCorreos;

/**
 *
 * @author David
 */
public class AvisoCorreos {
  private MimeMultipart multipart = new MimeMultipart("related");
    private String origen = Constants.MAIL_NOMBRE;
    private String contrasenia = Constants.MAIL_PASS;
    private String correo;
    private ArrayList<String> correos;
    private String asunto = "Aspirante Tecnológico de Toluca: Periodo de Renovación";
    private String cuerpo;
    private String fechaI="";
    private String fechaF="";
    private String error = "correcto";

    public void sendFromMail() throws Exception {
     
        
        Properties props = System.getProperties();
        props.setProperty("mail.mime.charset", "ISO-8859-1");

        String host = "mail.ittoluca.edu.mx"; //itt
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", origen);
        props.put("mail.smtp.password", contrasenia);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.localhost", "sia.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "mail.ittoluca.edu.mx");
        props.put("mail.smtp.ehlo", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth", "true");
        
        String cabecera = "<html>\n" +
"    <head>\n" +
"      \n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    </head>\n" +
"    <header style=\"position: relative;left:80px;\">\n" +
"        <img src=\"cid:cidcabecera\" />\n" +
"        <pre style=\"font-family:'calibri'; font-size: 16px;\">\n" +
"            Instituto Tecnológico de Toluca\n" +
"        </pre>\n" +
"    </header>\n" +
"    <body >";

String pie = " </body>\n" +
"    <footer  style=\"position: relative;\" >\n" +
"         <img src=\"cid:cidpie\" />\n" +
"    </footer>\n" +
"</html>";


setCuerpo(" <pre style=\"font-family:'calibri'; font-size: 16px;\">\n" +
"Estimado(a) Aspirante:\n" +
"\n" +
"Le informamos que  en caso de que su referencia esté expirada y no haya realizado el pago de su preficha o cuente con una referencia no válida  usted podrá  renovarla  o  \n" +
"\n" +
"generarla  en la página de  <a href=\" http://hermes.ittoluca.edu.mx:8100/MODULO_ASPIRANTE/\"> http://hermes.ittoluca.edu.mx:8100/MODULO_ASPIRANTE/</a>   desde el  apartado de \"Renovar Referencia\" durante el periodo del <strong style='font-size:20px'>"+fechaI+" al " + fechaF+"</strong>,\n"+
"\n" +
" no obstante le recordamos que dicha renovación está sujeta a la disponibilidad de Prefichas en el tecnológico.\n" +
"\n" +
"Tome en cuenta las siguientes indicaciones  antes de realizar la renovación de su referencia:\n" +
"\n" +
"1. El aspirante tendrá hasta dos oportunidades para renovar su referencia.\n" +
"\n" +
"2. El aspirante tendrá un día hábil para realizar el pago de su preficha después de renovar su referencia.\n" +
"\n" +
"Si desea conocer el costo de la preficha por favor consulte la convocatoria en la página oficial del Tecnológico:  <a href=\"www.ittoluca.edu.mx\">www.ittoluca.edu.mx</a>\n" +
"\n" +
"\n" +
"            </pre>");
String content= String.format("%s%s%s%s%s", cabecera,"<br/>",getCuerpo(),"<br/>", pie);
addContent(content);
       


 String path_head="";
 String path_foot="";
        try {
            path_head = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            path_head= path_head.replace("WEB-INF/classes/Beans/AvisoCorreos.class", "Imagenes/header_correo.png");
              path_foot = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            path_foot= path_foot.replace("WEB-INF/classes/Beans/AvisoCorreos.class", "Imagenes/footer_correo.png");
        
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
        }

         

addCID("cidcabecera", path_head);
addCID("cidpie",path_foot);

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(origen));
            InternetAddress[] toAddress = new InternetAddress[1];
            InternetAddress[] aspMails = new InternetAddress[1];
            
            toAddress[0] = new InternetAddress(origen);
           
            
            message.addRecipient(Message.RecipientType.TO, toAddress[0]);
            

            message.setSubject(asunto);
            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, origen, contrasenia);
        
        
            transport.sendMessage(message, message.getAllRecipients());
                for(int i=0; i<correos.size();i++){
                    try{
                MimeMessage massive = new MimeMessage(session);
                massive.setFrom(new InternetAddress(origen));
                aspMails[0]= new InternetAddress(correos.get(i));
                massive.addRecipient(Message.RecipientType.BCC, aspMails[0]);
                 massive.setSubject(asunto);
                 massive.setContent(multipart);
                     
                  transport.sendMessage(massive, massive.getAllRecipients());
                    }  catch (MessagingException me) {
            
            error = me.getMessage();
            LogFile audit= new LogFile();
            audit.registrarError(2, me.getCause().toString(), "Modulo_Administrador", "Modulo_Administrador","CorreosMasivos");
           
           
        }
            }
//         
            transport.close();
        } catch (MessagingException me) {
            
            error = me.getMessage();
          Logger audit= new Logger();
            audit.registrarError(2, me.getCause().toString(), "PAES", "Modulo_Administrador","CorreosMasivos");
           
            System.out.println(error+" "+me.getCause());
        }
        

    }
    public void addContent(String htmlText) throws Exception
    {
        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        this.multipart.addBodyPart(messageBodyPart);
    }
    public void addCID(String cidname,String pathname) throws Exception
    {
        DataSource fds = new FileDataSource(pathname);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+cidname+">");
        this.multipart.addBodyPart(messageBodyPart);
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the correos
     */
    public ArrayList<String> getCorreos() {
        return correos;
    }

    /**
     * @param correos the correos to set
     */
    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }

    /**
     * @return the fechaI
     */
    public String getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI the fechaI to set
     */
    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    /**
     * @return the fechaF
     */
    public String getFechaF() {
        return fechaF;
    }

    /**
     * @param fechaF the fechaF to set
     */
    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }
}

