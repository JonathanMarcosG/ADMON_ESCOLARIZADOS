/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.EnEmergencia;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "servletGuardarCE", urlPatterns = {"/servletGuardarCE"})
public class servletGuardarCE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        EnEmergencia ee = new EnEmergencia();
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String preficha = request.getParameter("preficha");
        
        String nombre = request.getParameter("nom").trim();
        String estado = request.getParameter("estado").trim();
        String municipio = request.getParameter("municipio").trim();
        String colonia = request.getParameter("colonia").trim();
        String calle = request.getParameter("calle").trim();
        String numInterior = request.getParameter("numInterior").trim();
        String numExterior = request.getParameter("numExterior").trim();
        String centroTrabajo = request.getParameter("centroTrabajo").trim();
        String telCentroTrabajo = request.getParameter("telCentroTrabajo").trim();
        String telFijo = request.getParameter("telFijo").trim();
        String telCel = request.getParameter("telCel").trim();

        ee.setNomAp(nombre);
        ee.setEstado(estado);
        ee.setCiudad(municipio);
        ee.setColonia(colonia);
        ee.setCalle(calle);
        ee.setNoInterior(numInterior);
        ee.setNoExterior(numExterior);
        ee.setCenTrabajo(centroTrabajo);
        ee.setTelTrabajo(telCentroTrabajo);
        ee.setTelfijo(telFijo);
        ee.setTelcel(telCel);
        ee.setPreficha(preficha);
        
        IngresoAbd bd = new IngresoAbd(usuario,contra);
        
        bd.actualizarCE(ee);
        out.print(bd.getErrorInsert());
        HttpSession session = request.getSession(true);
        session.setAttribute("emergencia", ee);
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
