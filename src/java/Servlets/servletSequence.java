/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.confConv;
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
        confConv cv=new confConv();
        IngresoAbd bd = new IngresoAbd(usuario, contra);
        bd.CheckSec();
         cv=bd.ParametrosConv();
        
        if(bd.getError()==0){
            
            mensaje="true";
        }else{
            
            if(bd.getError()==1){
                mensaje="false";  
               if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")||cv.getMetaReal()==null || cv.getMetaReal().contentEquals("")||cv.getFechaEntrega()==null || cv.getFechaEntrega().contentEquals("")||cv.getFechaFpre()==null || cv.getFechaFpre().contentEquals("")||cv.getFechaIpre()==null || cv.getFechaIpre().contentEquals("")||cv.getFechaPago()==null || cv.getFechaPago().contentEquals("")){
            cv.setMensaje("none");
                
            }
                if (!bd.getErrorInsert2().contentEquals("ninguno")) {
                  
             if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")||cv.getMetaReal()==null || cv.getMetaReal().contentEquals("")||cv.getFechaEntrega()==null || cv.getFechaEntrega().contentEquals("")||cv.getFechaFpre()==null || cv.getFechaFpre().contentEquals("")||cv.getFechaIpre()==null || cv.getFechaIpre().contentEquals("")||cv.getFechaPago()==null || cv.getFechaPago().contentEquals("")){
            cv.setMensaje("none");
                
            }
        
        }
                
           HttpSession session = request.getSession(true);
        session.setAttribute("convo", cv);      
            }else{
                mensaje="Existe un error en la base de datos. Error:"+bd.getError();
            }
        }
       
        out.print(mensaje);
        
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
