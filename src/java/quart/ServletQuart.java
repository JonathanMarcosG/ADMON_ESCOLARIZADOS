/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quart;


import Beans.Fechas;
import ConexionBD.IngresoAbd;
import Utils.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author David
 */
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class ServletQuart implements ServletContextListener {
        Scheduler scheduler = null;

        @Override
        public void contextInitialized(ServletContextEvent servletContext) {
                System.out.println("Context Initialized");
                IngresoAbd bd= new IngresoAbd(Constants.userT,Constants.passT);
                bd.vigenciaConvoc();
                String[] fin=bd.getFechaF().split("/");
                 String[] inicio=bd.getFechaI().split("/");
                 
                 if(fin.length>1 && inicio.length>1){
                   
                Fechas dates= new Fechas();
                String dias="";
                String mesF=dates.fechasConvoc(fin[1]);
                String diaF=dates.fechasConvoc(fin[0]);
                String mesI=dates.fechasConvoc(inicio[1]);
                String diaI=dates.fechasConvoc(inicio[0]);
                int monthI=Integer.parseInt(mesI);
                int monthF=Integer.parseInt(mesF);
                if((monthF-monthI)==0){
                    dias=diaI+"-"+diaF;
                    
                }else{
                    dias="1-31";
                }
              
                            // Setup the Job class and the Job group
                        JobDetail job = newJob(TriggerCorreos.class).withIdentity(
                                        "CronQuartzJob", "Group").build();

                        
                        Trigger trigger = newTrigger()
                        .withIdentity("TriggerName", "Group")
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 "+dias+" "+mesI+"-"+mesF+" ?"))
                        .build();

            try {
                // Setup the Job and Trigger with Scheduler & schedule jobs
                scheduler = new StdSchedulerFactory().getScheduler();
                 scheduler.start();
                        scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException ex) {
                Logger.getLogger(ServletQuart.class.getName()).log(Level.SEVERE, null, ex);
            }
                 }          
               
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContext) {
                System.out.println("Context Destroyed");
               
            try {
                scheduler.shutdown();
            } catch (SchedulerException ex) {
                Logger.getLogger(ServletQuart.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
}