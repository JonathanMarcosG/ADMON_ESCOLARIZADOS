/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.FolioCENEVAL;
//import ConexionBD.IngresoAbd;
import DAO.ActualizarDAO;
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
//@WebServlet(name = "servletFCENEVAL", urlPatterns = {"/servletFCENEVAL"})
public class servletFCENEVAL extends HttpServlet {

    String errorServlet;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String idAspirante = request.getParameter("aspirante");

        String folio = request.getParameter("folio");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        String[] mensaje = ActualizarDAO.insertarCeneval(usuario, contra, idAspirante, folio).split("&");
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
//        bd.insertarCeneval(idAspirante, folio);
        int codMensaje=Integer.parseInt(mensaje[1]);
        if (codMensaje!= 0) {
            switch(codMensaje){
                case -1:
                    out.print(Constants.ERROR5);
                    break;
                case -2:
                    out.print(Constants.ERROR2);
                    break;
                case -3:
                    out.print(Constants.ERROR3);
                    break;                          
            }
        } else {
            out.print(mensaje[0]);
        }
        FolioCENEVAL fC = new FolioCENEVAL();
        fC.setFolioCENEVAL(folio);
//        out.print(bd.getErrorInsert());
        HttpSession session = request.getSession(true);
        session.setAttribute("folioCeneval", fC);
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
