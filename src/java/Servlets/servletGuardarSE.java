/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosDelDomicilio;
import Beans.DatosSocioeconomicos;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
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
//@WebServlet(name = "servletGuardarSE", urlPatterns = {"/servletGuardarSE"})
public class servletGuardarSE extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        DatosDelDomicilio ddd = new DatosDelDomicilio();
        DatosSocioeconomicos ds = new DatosSocioeconomicos();
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String preficha = request.getParameter("preficha");

        String estado = request.getParameter("estadoDomicilio").trim();
        String municipio = request.getParameter("municipioDomicilio").trim();
        String localidad = request.getParameter("localidadDomicilio").trim();
        String colonia = request.getParameter("colonia").trim();
        String calle = request.getParameter("calle").trim();
        String numExterior = request.getParameter("numExterior").trim();
        String numInterior = request.getParameter("numInterior").trim();
        String cp = request.getParameter("cp").trim();
        String telCel = request.getParameter("telCel").trim();
        String telFijo = request.getParameter("telFijo").trim();

        String beca = request.getParameter("beca");

        String padre = request.getParameter("padre");
        String vivePadre = request.getParameter("vivePadre");
        String zonaProcedencia = request.getParameter("zonaProcedencia");
        String maxEstudiosPadre = request.getParameter("maxEstudiosPadre");
        String maxEstudiosMadre = request.getParameter("maxEstudiosMadre");
        String ingresosTotales = request.getParameter("ingresosTotales");
        String depEconomicamente = request.getParameter("DependeDe");
        String ocupacionPa = request.getParameter("ocupacionPa");
        String ocupacionMa = request.getParameter("ocupacionMa");
        String madre = request.getParameter("madre");
        String viveMadre = request.getParameter("viveMadre");
        String tipoCasa = request.getParameter("tipoCasa");
        String noPersonasCasa = request.getParameter("noPersonasCasa");
        String noCuartos = request.getParameter("noCuartos");
        String oportunidades = request.getParameter("oportunidades");
        String viveCon = request.getParameter("viveCon");
        String DependeDe = request.getParameter("depEconomicamente");

        ddd.setEstado(estado);
        ddd.setMunicipio(municipio);
        ddd.setLocalidad(localidad);
        ddd.setColonia(colonia);
        ddd.setCalle(calle);
        ddd.setNoExterior(Integer.parseInt(numExterior));
        ddd.setNoInterior(Integer.parseInt(numInterior));
        ddd.setCp(Integer.parseInt(cp));
        ddd.setTelCel(telCel);
        ddd.setTelFijo(telFijo);
        ddd.setPreficha(preficha);

        ds.setBeca(beca);
        ds.setNomPadre(padre);
        ds.setVivePadre(vivePadre);
        ds.setZonaProcedencia(zonaProcedencia);
        ds.setMaxEstudiosPadre(Integer.parseInt(maxEstudiosPadre));
        ds.setMaxEstudiosMadre(Integer.parseInt(maxEstudiosMadre));
        ds.setIngresosTotales(ingresosTotales);
        ds.setDependeEconomicamente(depEconomicamente);
        ds.setOcupacionPadre(Integer.parseInt(ocupacionPa));
        ds.setOcupacionMadre(Integer.parseInt(ocupacionMa));
        ds.setNomMadre(madre);
        ds.setViveMadre(viveMadre);
        ds.setTipoCasa(tipoCasa);
        ds.setNoPersonasCasa(Integer.parseInt(noPersonasCasa));
        ds.setCuartosCasa(noCuartos);
        ds.setDependeDe(DependeDe);
        ds.setProgOportunidades(oportunidades);
        ds.setViveCon(viveCon);
        ds.setIdAspirante(Integer.parseInt(preficha));

        IngresoAbd bd = new IngresoAbd(usuario, contra);
        String error = "1";
        if (opcion == 0) {
            error = "ninguno";
        } else if (opcion == 1) {
            bd.actualizarDD(ddd);
            error = bd.getErrorInsert();
        } else if (opcion == 2) {
            bd.actualizarSE(ds);
            error = bd.getErrorInsert();
            System.out.println(error);
        }

        out.print(error);

        HttpSession session = request.getSession(true);
        session.setAttribute("dts", ds);
        session.setAttribute("ddd", ddd);
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
