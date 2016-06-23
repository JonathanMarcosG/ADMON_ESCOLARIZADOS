/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ListaCarreras;
import Beans.SelectCarreras;
import ConexionBD.IngresoAbd;
import com.google.gson.Gson;
import java.io.IOException;
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
//@WebServlet(name = "servletActualizarListas", urlPatterns = {"/servletActualizarListas"})
public class servletActualizarListas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        String idSelect = request.getParameter("idSelect");
        int filtro2 = Integer.parseInt(request.getParameter("filtro2"));
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String id = request.getParameter("id");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String nomLista = request.getParameter("nomLista");
        String aliasLista = request.getParameter("aliasLista");
        int filtro = Integer.parseInt(request.getParameter("filtro"));
       
        IngresoAbd bd = new IngresoAbd(usuario, contra);
        ListaCarreras opcns = new ListaCarreras();
        List<SelectCarreras> opcio = bd.llenarListas(opcion, filtro);

        List<SelectCarreras> actualizar = new ArrayList<SelectCarreras>();

        if (filtro2 == 0) {
            SelectCarreras b = new SelectCarreras();
            b.setIdPais("0");
            b.setClaveCarrera(0);
            b.setNombre("--Seleccione--");
            opcio.add(0, b);
            actualizar = opcio;

        } else {
            if (opcion == 1) {
                opcio = bd.llenarListaPais(opcion, filtro);
                opcns.compararPais(opcio, actualizar, id);
                opcns.AgregarOpcionesPais(opcio, actualizar, id);
            } else {
                int op = Integer.parseInt(id);
                opcns.comparar(opcio, actualizar, op);
                opcns.AgregarOpciones(opcio, actualizar, op);

            }
        }

        if (opcion == 2 || opcion == 1 || opcion == 3 || opcion == 9) {
            String json = null;
            json = new Gson().toJson(actualizar);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(aliasLista, actualizar);

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
