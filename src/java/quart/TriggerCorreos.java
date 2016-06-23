/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quart;

import Beans.AvisoCorreos;
import Beans.Fechas;
import ConexionBD.IngresoAbd;
import Utils.Constants;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author David
 */
public class TriggerCorreos implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        IngresoAbd bd = new IngresoAbd(Constants.userT, Constants.passT);
        String error = "incorrecto";
        ArrayList<String> correos = new ArrayList();
        
        correos = bd.checkRenovacion();
        
       

        if (bd.getError() == 1 ) {

            if (correos.size() > 0) {
                try {

                    AvisoCorreos mail = new AvisoCorreos();
                    mail.setFechaI(bd.getFechaI());
                    mail.setFechaF(bd.getFechaF());
                    mail.setCorreos(correos);
                    mail.sendFromMail();
                    error = mail.getError();

                } catch (Exception ex) {
                    System.out.println(ex.getCause());
                }
            } else {
                System.out.println("No hay correos para enviar.");
            }
        }

        if (error.contentEquals("correcto") || error.contentEquals("Invalid Addresses")) {

            bd.checkCorreosEnviados();
        }
    }

}
