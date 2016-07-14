/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.confConv;
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
 * @author David
 */
public class servletSequence extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mensaje;
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        confConv cv;
//        IngresoAbd bd = new IngresoAbd(usuario, contra);

//        bd.CheckSec();
        String[] resultado1 = ConvocatoriaDAO.CheckSec(usuario, contra).split("&");
        switch (Integer.parseInt(resultado1[1])) {
            case 0:
                mensaje = "true";
                break;
            case 1:
                cv = ConvocatoriaDAO.ParametrosConv(usuario, contra);
                mensaje = "false";
                if (cv.getCodError() == 0) {
                    if (cv.getMetaEstablecida() == null || cv.getMetaEstablecida().contentEquals("") || cv.getMetaReal() == null || cv.getMetaReal().contentEquals("") || cv.getFechaEntrega() == null || cv.getFechaEntrega().contentEquals("") || cv.getFechaFpre() == null || cv.getFechaFpre().contentEquals("") || cv.getFechaIpre() == null || cv.getFechaIpre().contentEquals("") || cv.getFechaPago() == null || cv.getFechaPago().contentEquals("")) {
                        cv.setMensaje("none");
                    }
                } else {
                    mensaje = "Existe un error en la base de datos. Error:" + error(cv.getCodError());
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("convo", cv);
                break;
            default:
                mensaje = "Existe un error en la base de datos. Error:" + error(Integer.parseInt(resultado1[1]));
                break;
        }

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
