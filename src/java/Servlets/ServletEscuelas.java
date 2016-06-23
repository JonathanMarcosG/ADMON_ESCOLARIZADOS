/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Escuelas;
import ConexionBD.IngresoAbd;
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
public class ServletEscuelas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        Escuelas esc = new Escuelas();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        
        String claveCCT = request.getParameter("claveCCT");
        String control = request.getParameter("control");
        String servicio = request.getParameter("servicio");
        String ambito = request.getParameter("ambito");
        String turno = request.getParameter("turno");
        String estadoID = request.getParameter("estadoID");
        String entidad = request.getParameter("entidad");
        String municipioID = request.getParameter("municipioID");
        String localidadID = request.getParameter("localidadID");
        String nombre = request.getParameter("nombre");
        String domicilio = request.getParameter("domicilio");
        
        esc.setClaveCCT(claveCCT);
        esc.setControl(control);
        esc.setServicio(servicio);
        esc.setAmbito(ambito);
        esc.setTurno(turno);
        esc.setEdoId(estadoID);
        esc.setEntidad(entidad);
        esc.setMunicipio(municipioID);
        esc.setLocalidad(localidadID);
        esc.setNombre(nombre);
        esc.setDomicilio(domicilio);
        
        IngresoAbd bd = new IngresoAbd(usuario,contra);
        bd.altaEscuela(esc);
        out.print(bd.getErrorInsert());
        
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
