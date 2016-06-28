/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ClaseEnviarCorreo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "servletCorreoPagoProcesad", urlPatterns = {"/servletCorreoPagoProcesad"})
public class servletCorreoPagoProcesad extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
             ServletContext d = getServletContext();
            String correo = request.getParameter("correo").trim();
            
           
            
            String cuerpo2= "<div  style=\"position: relative;left:130px;\" >\n" +
"            <pre style=\"font-family:'calibri'; font-size: 16px;\">\n" +
"            Estimado(a) "+ request.getParameter("nombreAsp")+" su pago ha sido procesado con éxito.\n" +
"            Datos del Aspirante:\n" +
"\n" +
"	     Ficha: "+request.getParameter("ficha")+"\n" +
"	     Carrera seleccionada en primera opción: "+request.getParameter("carrera")+"\n" +
"\n" +
"            Por favor verifique  constantemente su bandeja de entrada,  ya que recibirá un correo indicando el procedimiento para  darse de alta en CENEVAL.\n" +
"\n" +
"\n" +
"            <strong style=\"color:black\">Instituto Tecnológico de Toluca</strong>\n" +
"\n" +
"            <strong style=\"color:black\">Departamento de Servicios Escolares</strong>\n" +
"\n" +
"            </pre>\n" +
"        </div>";
            String asunto = "Aspirante Tecnológico de Toluca: Confirmación de Pago";
            
            ClaseEnviarCorreo cec = new ClaseEnviarCorreo();
            cec.setCorreo(correo);
            cec.setCuerpo(cuerpo2);
            cec.setAsunto(asunto);
            cec.sendFromMail(d);
            cec.getError();
            out.print(cec.getError());
        } catch (Exception ex) {
            Logger.getLogger(servletCorreoPagoProcesad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
