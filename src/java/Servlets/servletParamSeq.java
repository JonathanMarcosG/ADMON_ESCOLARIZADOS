/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ConfigurarPeriodo;
import Beans.confConv;
import ConexionBD.CompararErrores;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David
 */
public class servletParamSeq extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String mensaje;
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String opc = request.getParameter("opc");
        confConv cv= new confConv();
         ConfigurarPeriodo cp = new ConfigurarPeriodo();
        IngresoAbd bd = new IngresoAbd(usuario, contra);
        if(opc.contentEquals("1")){
        cv = bd.ParametrosConv();
            
        if (!bd.getErrorInsert2().contentEquals("ninguno")) {
         if(bd.isConStatus()==false){
             cv.setMensaje("false");
           
         }else{
              
        
            if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")){
            cv.setMensaje("none");
                
            }
         
        }
        }else{
             bd.ReinicioSec();
             if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")){
            cv.setMensaje("none");
               
            }
         if(bd.getError()!=0){
             
             cv.setMensaje(Integer.toString(bd.getError()));
           
         }
        }
        }else if(opc.contentEquals("2")){
            
            cp = bd.verificarPeriodo();
             if (bd.getErrorInsert().equals("ninguno") || bd.getError()==101) {
            
            cp.cambioPeriodo();
            if(!bd.getErrorInsert2().equals("ninguno")){
                
            }else{
                
            }
            
        } else {
            
            if (bd.getError() == -103) {
                cp.setPeriodo(bd.getErrorInsert());
                
            } 

        }
            cv = bd.ParametrosConv(); 
           if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")){
            cv.setMensaje("none");
                
            }
        
        }else{
            
             cv = bd.ParametrosConv(); 
           if(cv.getMetaEstablecida()==null || cv.getMetaEstablecida().contentEquals("")||cv.getMetaReal()==null || cv.getMetaReal().contentEquals("")||cv.getFechaEntrega()==null || cv.getFechaEntrega().contentEquals("")||cv.getFechaFpre()==null || cv.getFechaFpre().contentEquals("")||cv.getFechaIpre()==null || cv.getFechaIpre().contentEquals("")||cv.getFechaPago()==null || cv.getFechaPago().contentEquals("")){
            cv.setMensaje("none");
                
            }
        }
         
        mensaje=cv.getMensaje();
          HttpSession session = request.getSession(true);
        session.setAttribute("convo", cv);
        session.setAttribute("fechas", cp);
       
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
