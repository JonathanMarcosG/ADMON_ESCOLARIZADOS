/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.confConv;
import DAO.ConvocatoriaDAO;
import Utils.Constants;
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
        String[] resultado = ConvocatoriaDAO.confConvocatoria(usuario, contra, fechaEntrega, fechaPago, metaReal, metaEstablecida, fechaInPre, fechaFinPre).split("&");
        if (Integer.parseInt(resultado[1]) == 0) {
            cv.setFechaEntrega(fechaEntrega);
            cv.setFechaPago(fechaPago);
            cv.setMetaReal(metaReal);
            cv.setMetaEstablecida(metaEstablecida);
            cv.setFechaIpre(fechaInPre);
            cv.setFechaFpre(fechaFinPre);
            mensaje = resultado[2];
        } 
        else {
            String error=error(Integer.parseInt(resultado[1]));
            mensaje = "No se ha modificado la convocatoria. <br>" + error + " <br><img src=\"Imagenes/sobreRojo.png\" width=\"50\" />";

        }

        out.print(mensaje);
    }
    public String error(int error) {
        String mensaje = "";
        switch (error) {
            case -1:
                mensaje = Constants.ERROR6;
                break;
            case -2:
                mensaje = Constants.ERROR3;
                break;
            case -3:
                mensaje = Constants.ERROR2;
                break;
        }
        return mensaje;
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
