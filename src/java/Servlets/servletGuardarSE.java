/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosDelDomicilio;
import Beans.DatosSocioeconomicos;
//import ConexionBD.IngresoAbd;
import DAO.ActualizarDAO;
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

        String padre = request.getParameter("padre");
        String vivePadre = request.getParameter("vivePadre");
        String ocupacionPa = request.getParameter("ocupacionPa");

        String madre = request.getParameter("madre");
        String viveMadre = request.getParameter("viveMadre");
        String ocupacionMa = request.getParameter("ocupacionMa");

        String maxEstudiosPadre = request.getParameter("maxEstudiosPadre");
        String maxEstudiosMadre = request.getParameter("maxEstudiosMadre");

        String zonaProcedencia = request.getParameter("zonaProcedencia");
        String tipoCasa = request.getParameter("tipoCasa");
        String DependeDe = request.getParameter("depEconomicamente");

        String viveCon = request.getParameter("viveCon");
        String beca = request.getParameter("beca");
        String ingresosTotales = request.getParameter("ingresosTotales");

        String oportunidades = request.getParameter("oportunidades");
        String noPersonasCasa = request.getParameter("noPersonasCasa");
        String noCuartos = request.getParameter("noCuartos");
        String depEconomicamente = request.getParameter("DependeDe");

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

        ds.setNomPadre(padre);
        ds.setVivePadre(vivePadre);
        ds.setOcupacionPadre(Integer.parseInt(ocupacionPa));

        ds.setNomMadre(madre);
        ds.setViveMadre(viveMadre);
        ds.setOcupacionMadre(Integer.parseInt(ocupacionMa));

        ds.setMaxEstudiosPadre(Integer.parseInt(maxEstudiosPadre));
        ds.setMaxEstudiosMadre(Integer.parseInt(maxEstudiosMadre));

        ds.setZonaProcedencia(zonaProcedencia);
        ds.setTipoCasa(tipoCasa);
        ds.setDependeEconomicamente(depEconomicamente);

        ds.setViveCon(viveCon);
        ds.setBeca(beca);
        ds.setIngresosTotales(ingresosTotales);

        ds.setProgOportunidades(oportunidades);
        ds.setNoPersonasCasa(Integer.parseInt(noPersonasCasa));
        ds.setCuartosCasa(noCuartos);
        ds.setDependeDe(DependeDe);

        ds.setIdAspirante(Integer.parseInt(preficha));

//        IngresoAbd bd = new IngresoAbd(usuario, contra);
        HttpSession session = request.getSession(true);
        String[] resultado;
        String error = "0";
        switch (opcion) {
            case 0:
                error = "correcto";
                break;
            case 1:
                resultado = ActualizarDAO.actualizarDD(usuario, contra, ddd).split("&");
                if (Integer.parseInt(resultado[1]) == 0) {
                    session.setAttribute("ddd", ddd);
                    error = resultado[0];
                }
                //            bd.actualizarDD(ddd);
                break;
            case 2:
//                bd.actualizarSE(ds);
                resultado = ActualizarDAO.actualizarSE(usuario, contra, ds).split("&");
                if (Integer.parseInt(resultado[1]) == 0) {
                    session.setAttribute("dts", ds);
                    error = resultado[0];
                }
//                error = bd.getErrorInsert();
                break;
            case 3:
                resultado = ActualizarDAO.actualizarDD(usuario, contra, ddd).split("&");
                if (Integer.parseInt(resultado[1]) == 0) {
                    resultado = ActualizarDAO.actualizarSE(usuario, contra, ds).split("&");
                    if (Integer.parseInt(resultado[1]) == 0) {
                        session.setAttribute("ddd", ddd);
                        session.setAttribute("dts", ds);
                        error = resultado[0];
                    }
                }
//                error = bd.getErrorInsert();
                break;
            default:
                break;
        }

        out.print(error);

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
