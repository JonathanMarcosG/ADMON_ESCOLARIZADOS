/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

/**
 *
 * @author David
 */
public class NoCorreosSinLeer {
    private String host;
    private String usuario;
    private String password;
    private String resultado="correcto";
    
    public void CorreosSinLeer(){
        try {
           
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imap");
            props.put("mail.imap.ssl.trust", "mail.ittoluca.edu.mx");
            props.put("mail.imap.starttls.enable", "true");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imap");
            store.connect(host, usuario, password);
            
           Folder inbox = store.getFolder("Inbox");
           inbox.open(Folder.READ_ONLY);
           
           
            Flags seen = new Flags(Flags.Flag.SEEN);
    FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
    Message messages[] = inbox.search(unseenFlagTerm);
     
    if (messages.length == 0){ 
        resultado="0";
    }else{
        resultado=Integer.toString(messages.length);
    }
 

 
    inbox.close(true);
    store.close();
  }     catch (MessagingException ex) {
           resultado="error";
        }
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the resultado
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * @param resultado the resultado to set
     */
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
