/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ConfigurarPeriodo;
import ConexionBD.CompararErrores;
import ConexionBD.IngresoAbd;
import DAO.ConvocatoriaDAO;
import Utils.Constants;
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
//@WebServlet(name = "servletPeriodo", urlPatterns = {"/servletPeriodo"})
public class servletPeriodo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mensaje;
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        ConfigurarPeriodo cp = new ConfigurarPeriodo();

        IngresoAbd bd = new IngresoAbd(usuario, contra);
//        bd.actualizarPeriodo(fechaInicio, fechaFin);
        String[] resultado = ConvocatoriaDAO.actualizarPeriodo(usuario, contra, fechaInicio, fechaFin).split("&");

//        if (bd.getErrorInsert().contentEquals("ninguno")) {
        if (Integer.parseInt(resultado[1]) == 0) {

            cp.setFechaInicio(fechaInicio);
            cp.setFechaFin(fechaFin);
            cp.cambioPeriodo();
            mensaje = "Los cambios se han guardado con éxito. <img src=\"Imagenes/sobreVerde.png\" width=\"50\" />";
        } else {
            cp.setPeriodo("No se ha registrado periodo");
            CompararErrores ce = new CompararErrores();
            ce.buscarError(bd.getError());
//            mensaje = ("No se ha resgistrado el periodo. Error: " + bd.getErrorInsert() + "<img src=\"Imagenes/sobreRojo.png\" width=\"50\" />");
            mensaje = ("No se ha resgistrado el periodo. Error:  " + error(Integer.parseInt(resultado[1])) + "<img src=\"Imagenes/sobreRojo.png\" width=\"50\" />");
            cp.setMensaje(ce.getMensajeDeError());
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("fechas", cp);
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
