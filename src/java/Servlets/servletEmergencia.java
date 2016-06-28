/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.EnEmergencia;
import Beans.ListaCarreras;
import Beans.SelectCarreras;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
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
//@WebServlet(name = "servletEmergencia", urlPatterns = {"/servletEmergencia"})
public class servletEmergencia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String aspirante = request.getParameter("aspirante");

        EnEmergencia ee;
        List<SelectCarreras> estado = new ArrayList<SelectCarreras>();
        List<SelectCarreras> municipio = new ArrayList<SelectCarreras>();

        IngresoAbd bd = new IngresoAbd(usuario, contra);
        ee = bd.obtenerDatosCE(aspirante);

      try {
            if (bd.getErrorInsert().contentEquals("ninguno")) {

                ListaCarreras opcns = new ListaCarreras();
                List<SelectCarreras> opcio = bd.llenaOpcionesCarreras(aspirante);

                
                opcio = bd.llenarListas(2, 0);
                if(ee.getEstado() == null){
                    ee.setEstado("0");
                }
                int idEstado = Integer.parseInt(ee.getEstado());
                opcns.comparar(opcio, estado, idEstado);
                opcns.AgregarOpciones(opcio, estado, idEstado);

                opcio = bd.llenarListas(3, idEstado);
                int mun = Integer.parseInt(ee.getCiudad());
                opcns.comparar(opcio, municipio, mun);
                opcns.AgregarOpciones(opcio, municipio, mun);
            }
            out.print(bd.getErrorInsert());
        } catch (IndexOutOfBoundsException e) {

            if (!bd.getErrorInsert().contentEquals("ninguno")) {
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
            }
        }catch (NumberFormatException nf){
              if (!bd.getErrorInsert().contentEquals("ninguno")) {
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
            }
        }catch (NullPointerException ede) {
            if (!bd.getErrorInsert().contentEquals("ninguno")) {
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
            }
        }
            

        HttpSession session = request.getSession(true);

        session.setAttribute("emergencia", ee);
        session.setAttribute("edoE", estado);
        session.setAttribute("munE", municipio);

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
