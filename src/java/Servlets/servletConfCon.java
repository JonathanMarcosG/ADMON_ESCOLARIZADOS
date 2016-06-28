/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.confConv;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author David
 */
//@WebServlet(name = "servletConfCon", urlPatterns = {"/servletConfCon"})
public class servletConfCon extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String mensaje;
        
        ServletContext d = getServletContext();
        PrintWriter out = response.getWriter();
        String fechaPago = request.getParameter("fechaPago");
        String fechaEntrega = request.getParameter("fechaEntrega");
         String fechaInPre = request.getParameter("fechaInPre");
        String fechaFinPre = request.getParameter("fechaFinPre");
        String metaReal = request.getParameter("metaReal");
        String metaEstablecida = request.getParameter("metaEstablecida");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        confConv cv = new confConv();
        IngresoAbd bd = new IngresoAbd(usuario, contra);
     
        bd.confConvocatoria(fechaEntrega, fechaPago,metaReal,metaEstablecida, fechaInPre, fechaFinPre);
         
        if (bd.getErrorInsert2().contentEquals("ninguno")) {
           
          cv.setFechaEntrega(fechaEntrega);
          cv.setFechaPago(fechaPago);
          cv.setMetaReal(metaReal);
          cv.setMetaEstablecida(metaEstablecida);
          cv.setFechaIpre(fechaInPre);
          cv.setFechaFpre(fechaFinPre);
          
          mensaje= Integer.toString(bd.getError());
          
          
        } else {
        
        mensaje= "No se ha modificado la convocatoria. "+bd.getErrorInsert2()+" <img src=\"Imagenes/sobreRojo.png\" width=\"50\" />";
           
        }
       
        out.print(mensaje);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
