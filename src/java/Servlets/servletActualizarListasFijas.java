/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ListaCarreras;
import Beans.SelectCarreras;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
//@WebServlet(name = "servletActualizarListasFijas", urlPatterns = {"/servletActualizarListasFijas"})
public class servletActualizarListasFijas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String aliasLista = request.getParameter("aliasLista");
        int opcion = Integer.parseInt(op);
        ListaCarreras opcns = new ListaCarreras();
        List<SelectCarreras> opcio = new ArrayList<SelectCarreras>();
        List<SelectCarreras> verificar = new ArrayList<SelectCarreras>();

        if (opcion == 1) {
            opcio = opcns.llenaDiscapacidad();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 2) {
            opcio = opcns.llenaSexo();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 3) {
            opcio = opcns.llenaEdoCivil();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 4) {
            opcio = opcns.llenaVive();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 5) {
            opcio = opcns.llenaSiNo();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 6) {
            opcio = opcns.llenaZonaProcedencia();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 7) {
            opcio = opcns.llenaIngresos();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 8) {
            opcio = opcns.llenaCasaEs();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 9) {
            opcio = opcns.llenaNumero();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 10) {
            opcio = opcns.llenaYN();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 11) {
            opcio = opcns.llenaViveCon();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 12) {
            opcio = opcns.llenaSangre();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        } else if (opcion == 13) {
            opcio = opcns.llenaTEscuela();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        }else if (opcion == 14){
            opcio = opcns.llenaNumCuartos();
            opcns.compararPais(opcio, verificar, id);
            opcns.AgregarOpcionesPais(opcio, verificar, id);
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(aliasLista, verificar);
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
