/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.SeguimientoDelAlumno;
import DAO.SeguimientoAspiranteDAO;
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
//@WebServlet(name = "servletPorFicha", urlPatterns = {"/servletPorFicha"})
public class servletSeguimientoDelAlumno extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String parametroInicial = request.getParameter("parametroInicial");
        int bandera = Integer.parseInt(request.getParameter("bandera"));

        SeguimientoDelAlumno sda = SeguimientoAspiranteDAO.obtenerSeguimiento(usuario, contra, parametroInicial, bandera);

        HttpSession session = request.getSession(true);
        if (sda != null) {
            if (sda.getCodError() == 0) {
                session.setAttribute("seguimiento", sda);
                out.print("bien/" + sda.getRegPreficha()
                        + "/" + sda.getPago()
                        + "/" + sda.getAltaCeneval()
                        + "/" + sda.getFolioCeneval() + ""
                        + "/" + sda.getRegFicha());
            } else {
                out.print(error(sda.getCodError()));
            }
        }
    }

    public String error(int error) {
        String mensaje = "";
        switch (error) {
            case -1:
                mensaje = Constants.ERROR4;
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
