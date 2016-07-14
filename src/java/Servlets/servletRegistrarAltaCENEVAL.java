/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

//import ConexionBD.IngresoAbd;
import DAO.InsercionesDAO;
import Utils.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "servletRegistrarAltaCENEVAL", urlPatterns = {"/servletRegistrarAltaCENEVAL"})
public class servletRegistrarAltaCENEVAL extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        String idAspirante = request.getParameter("idAspirante");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

//        IngresoAbd bd = new IngresoAbd(usuario, contra);
//        bd.altaCENEVAL(idAspirante);
        int existeCeneval=InsercionesDAO.altaCENEVAL(usuario, contra, idAspirante);

        if (existeCeneval==0) {
            out.print(0);

        } else {
            out.print(error(existeCeneval));
        }
    }

    public String error(int error) {
        String mensaje = "";
        switch (error) {
            case -1:
                mensaje = Constants.ERROR5;
                break;
            case -2:
                mensaje = Constants.ERROR3;
                break;
            case -3:
                mensaje = Constants.ERROR2;
                break;
            default:
                mensaje = "Se produjo un error al generar la ficha.";
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
