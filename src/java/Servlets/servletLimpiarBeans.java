/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosDelDomicilio;
import Beans.DatosPersonales;
import Beans.DatosSocioeconomicos;
import Beans.EnEmergencia;
import Beans.EscuelaProcedencia;
import Beans.SelectCarreras;
import Beans.VerificarCambio;
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
//@WebServlet(name = "servletLimpiarBeans", urlPatterns = {"/servletLimpiarBeans"})
public class servletLimpiarBeans extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        DatosPersonales dp = null;
        //System.out.println((dp.getPreficha()));
        EscuelaProcedencia ep = new EscuelaProcedencia();
        DatosDelDomicilio ddd = new DatosDelDomicilio();
        DatosSocioeconomicos ds = new DatosSocioeconomicos();
        EnEmergencia ee = new EnEmergencia();
        VerificarCambio ver = null;

        List<SelectCarreras> nacionalidad = new ArrayList<SelectCarreras>();
        List<SelectCarreras> estado = new ArrayList<SelectCarreras>();
        List<SelectCarreras> municipio = new ArrayList<SelectCarreras>();
        List<SelectCarreras> localidad = new ArrayList<SelectCarreras>();
        List<SelectCarreras> sexo = new ArrayList<SelectCarreras>();
        List<SelectCarreras> edoCivil = new ArrayList<SelectCarreras>();
        List<SelectCarreras> capDif = new ArrayList<SelectCarreras>();
        List<SelectCarreras> tipoSangre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> op1 = new ArrayList<SelectCarreras>();
        List<SelectCarreras> op2 = new ArrayList<SelectCarreras>();
        List<SelectCarreras> op3 = new ArrayList<SelectCarreras>();
        List<SelectCarreras> estadoEP = new ArrayList<SelectCarreras>();
        List<SelectCarreras> tEscuela = new ArrayList<SelectCarreras>();
        List<SelectCarreras> escuela = new ArrayList<SelectCarreras>();
        List<SelectCarreras> becaDS = new ArrayList<SelectCarreras>();
        List<SelectCarreras> Zona = new ArrayList<SelectCarreras>();
        List<SelectCarreras> estudiosPadre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> estudiosMadre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> ingresosTotales = new ArrayList<SelectCarreras>();
        List<SelectCarreras> dependeDe = new ArrayList<SelectCarreras>();
        List<SelectCarreras> ocupacionPadre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> ocupacionMadre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> tipoCasa = new ArrayList<SelectCarreras>();
        List<SelectCarreras> noPersonasCasa = new ArrayList<SelectCarreras>();
        List<SelectCarreras> cuartosCasa = new ArrayList<SelectCarreras>();
        List<SelectCarreras> progOportunidades = new ArrayList<SelectCarreras>();
        List<SelectCarreras> viveCon = new ArrayList<SelectCarreras>();
        List<SelectCarreras> dependeEconomicamente = new ArrayList<SelectCarreras>();

        HttpSession session = request.getSession(true);

        session.setAttribute("datosPersonales", dp);
        session.setAttribute("escuelaProcedencia", ep);
        session.setAttribute("dts", ds);
        session.setAttribute("ddd", ddd);
        session.setAttribute("emergencia", ee);
        session.setAttribute("nacionalidad", nacionalidad);
        session.setAttribute("estado", estado);
        session.setAttribute("municipio", municipio);
        session.setAttribute("localidad", localidad);
        session.setAttribute("verificacion", ver);
        session.setAttribute("sexo", sexo);
        session.setAttribute("capacidad", capDif);
        session.setAttribute("sangre", tipoSangre);
        session.setAttribute("civil", edoCivil);
        session.setAttribute("opcion1", op1);
        session.setAttribute("opcion2", op2);
        session.setAttribute("opcion3", op3);
        session.setAttribute("escuela", escuela);
        session.setAttribute("estado", estadoEP);
        session.setAttribute("tEscuela", tEscuela);
        session.setAttribute("beca", becaDS);
        session.setAttribute("zonaProcedencia", Zona);
        session.setAttribute("maxEstudiosPadre", estudiosPadre);
        session.setAttribute("maxEstudiosMadre", estudiosMadre);
        session.setAttribute("ingresosTotales", ingresosTotales);
        session.setAttribute("dependeDe", dependeDe);
        session.setAttribute("ocupacionPadre", ocupacionPadre);
        session.setAttribute("ocupacionMadre", ocupacionMadre);
        session.setAttribute("tipoCasa", tipoCasa);
        session.setAttribute("noPersonasCasa", noPersonasCasa);
        session.setAttribute("cuartosCasa", cuartosCasa);
        session.setAttribute("progOportunidades", progOportunidades);
        session.setAttribute("dependeEconomicamente", dependeEconomicamente);
        session.setAttribute("viveCon", viveCon);
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
