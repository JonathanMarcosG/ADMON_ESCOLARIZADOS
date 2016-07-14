/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.BMail;
import Models.ClaseEnviarCorreo;
//import ConexionBD.IngresoAbd;
import DAO.ConsultasDAO;
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
//@WebServlet(name = "servletCorreoCENEVAL", urlPatterns = {"/servletCorreoCENEVAL"})
public class servletCorreoCENEVAL extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
            ServletContext d = getServletContext();
            String user = request.getParameter("usuario");
            String pass = request.getParameter("contra");
            String correo = request.getParameter("correo");
            String nombre = request.getParameter("nombre");
            String folio = request.getParameter("folio");
            
//            IngresoAbd bd = new IngresoAbd(user, pass);
//            String url = bd.urlCeneval();
            String url = ConsultasDAO.urlCeneval(user, pass);
            BMail beanMail=new CuerpoCorreos().registroCeneval(url, nombre, folio);
            
            ClaseEnviarCorreo cec = new ClaseEnviarCorreo();
            boolean band=cec.sendMail(d, correo, beanMail.getCuerpo(), Constants.MAIL_ASUNTO_CENEVAL);
            out.print(band);
        } catch (Exception ex) {
            Logger.getLogger(servletCorreoCENEVAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
