/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.BMail;
import Models.ClaseEnviarCorreo;
import Models.CuerpoCorreos;
import Utils.Constants;
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
            String nombre = request.getParameter("nombreAsp");
            String ficha = request.getParameter("ficha");
            String carrera = request.getParameter("carrera");
            
           BMail beanMail=new CuerpoCorreos().inicioRegistro(nombre,ficha,carrera);
            
            ClaseEnviarCorreo cec = new ClaseEnviarCorreo();
            
            boolean band = cec.sendMail(d, correo,beanMail.getCuerpo(),Constants.MAIL_ASUNTO_PAGO);
            out.print(band);
        } catch (Exception ex) {
            Logger.getLogger(servletCorreoPagoProcesad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
