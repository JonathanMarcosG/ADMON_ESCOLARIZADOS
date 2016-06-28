/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Beans.EscuelaProcedencia;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "sevletCambioEP", urlPatterns = {"/sevletCambioEP"})
public class sevletCambioEP extends HttpServlet{
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
     
     EscuelaProcedencia ep = new EscuelaProcedencia();
      
     String estadoFK = request.getParameter("estado");
     String tipoEscuela = request.getParameter("tipoEscuela");
     String escuela = request.getParameter("escuela");
     String claveEscuela = request.getParameter("claveEscuela");
     String Pinicio = request.getParameter("Pinicio");
     String Pfin = request.getParameter("Pfin");
     String promedio = request.getParameter("promedio");
     
     ep.setEstadoFK(estadoFK);
     ep.setTipoEscuela(tipoEscuela);
     ep.setClasificacion(escuela);
     ep.setClaveEscuela(claveEscuela);
     ep.setFechaIni(Pinicio);
     ep.setFechaFin(Pfin);
     ep.setPromedio(promedio);
     HttpSession session = request.getSession(true);
      session.setAttribute("escuelaProcedencia", ep);
}
     
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

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
        processRequest(request, response);
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
