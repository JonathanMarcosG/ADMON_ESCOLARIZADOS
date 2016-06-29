/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ListaCarreras;
import Beans.SelectCarreras;
import Beans.UnirListas;
import ConexionBD.IngresoAbd;
import DAO.CatalogosDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "servletActualizarSelects", urlPatterns = {"/servletActualizarSelects"})
public class servletActualizarSelects extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        String o1 = request.getParameter("opcion1");
        String o2 = request.getParameter("opcion2");
        String o3 = request.getParameter("opcion3");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        IngresoAbd bd = new IngresoAbd(usuario, contra);

        ListaCarreras opcns = new ListaCarreras();
        List<SelectCarreras> opcio = CatalogosDAO.llenarListas(usuario,contra,10, 0);
//        List<SelectCarreras> opcio = bd.llenarListas(10, 0);

        List<SelectCarreras> op1 = new ArrayList();
        List<SelectCarreras> op2 = new ArrayList();
        List<SelectCarreras> op3 = new ArrayList();

        int opcion1 = Integer.parseInt(o1);
        int opcion2 = Integer.parseInt(o2);
        int opcion3 = Integer.parseInt(o3);

        opcns.comparar(opcio, op1, opcion1);
        opcns.comparar(opcio, op2, opcion2);
        opcns.comparar(opcio, op3, opcion3);

        opcns.SinRepetir(opcio, op1, opcion2, opcion3);
        opcns.SinRepetir(opcio, op2, opcion1, opcion3);
        opcns.SinRepetir(opcio, op3, opcion2, opcion1);
        UnirListas ul = new UnirListas(op1, op2, op3);
        String json;
        json = new Gson().toJson(ul);
  
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        HttpSession session = request.getSession(true);

        session.setAttribute("opcion1", op1);
        session.setAttribute("opcion2", op2);
        session.setAttribute("opcion3", op3);
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
