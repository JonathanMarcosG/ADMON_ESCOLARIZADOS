/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosDelDomicilio;
import Beans.DatosSocioeconomicos;
import Beans.ListaCarreras;
import Beans.SelectCarreras;
import ConexionBD.IngresoAbd;
import ConexionBD.conexion;
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
//@WebServlet(name = "servletDomicilioSocioeconomicos", urlPatterns = {"/servletDomicilioSocioeconomicos"})
public class servletDomicilioSocioeconomicos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String aspirante = request.getParameter("aspirante");
        conexion con = new conexion(usuario, contra);
        DatosDelDomicilio ddd = null;
        DatosSocioeconomicos ds = null;

        List<SelectCarreras> estado = new ArrayList<SelectCarreras>();
        List<SelectCarreras> municipio = new ArrayList<SelectCarreras>();
        List<SelectCarreras> localidad = new ArrayList<SelectCarreras>();

        List<SelectCarreras> vivePadre = new ArrayList<SelectCarreras>();
        List<SelectCarreras> viveMadre = new ArrayList<SelectCarreras>();
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

        IngresoAbd bd = new IngresoAbd(usuario, contra);
        try {
        ds = bd.obtenerDatosSE(aspirante);
        if (bd.getErrorInsert().contentEquals("ninguno")) {
            ddd = bd.obtenerDatosDD(aspirante);
            if (bd.getErrorInsert().contentEquals("ninguno")) {
                ListaCarreras opcns = new ListaCarreras();
                List<SelectCarreras> opcio = bd.llenaOpcionesCarreras(aspirante);

                opcio = bd.llenarListas(2, 0);
                int idEstado = ddd.getEstadoFK();
              
                opcns.comparar(opcio, estado, idEstado);
                opcns.AgregarOpciones(opcio, estado, idEstado);
                if (idEstado != 0) {
                    opcio = bd.llenarListas(3, idEstado);
                    int mun = Integer.parseInt(ddd.getMunicipio());
                    opcns.comparar(opcio, municipio, mun);
                    opcns.AgregarOpciones(opcio, municipio, mun);

                    opcio = bd.llenarListas(9, mun);
                    int local = Integer.parseInt(ddd.getLocalidad());
                    opcns.comparar(opcio, localidad, local);
                    opcns.AgregarOpciones(opcio, localidad, local);
                }
                opcio = opcns.llenaVive();
                String vivePa = ds.getVivePadre();

               
                 if (vivePa == null) {
                    vivePa = "No asignada";
                }
                opcns.compararPais(opcio, vivePadre, vivePa);
                opcns.AgregarOpcionesPais(opcio, vivePadre, vivePa);

                String viveMa = ds.getViveMadre();
                 if (viveMa == null) {
                    viveMa = "No asignada";
                }
                opcns.compararPais(opcio, viveMadre, viveMa);
                opcns.AgregarOpcionesPais(opcio, viveMadre, viveMa);

                opcio = opcns.llenaZonaProcedencia();
                String zonaProbando = ds.getZonaProcedencia();
                  if (zonaProbando == null) {
                    ds.setZonaProcedencia("No asignada") ;
                }
                String zonaDS = ds.getZonaProcedencia().toUpperCase();
              
                opcns.compararPais(opcio, Zona, zonaDS);
                opcns.AgregarOpcionesPais(opcio, Zona, zonaDS);

                opcio = bd.llenarListas(4, 0);
                int estudiosPa = ds.getMaxEstudiosPadre();

                opcns.comparar(opcio, estudiosPadre, estudiosPa);
                opcns.AgregarOpciones(opcio, estudiosPadre, estudiosPa);

                int estudiosMa = ds.getMaxEstudiosMadre();
                opcns.comparar(opcio, estudiosMadre, estudiosMa);
                opcns.AgregarOpciones(opcio, estudiosMadre, estudiosMa);

                opcio = opcns.llenaIngresos();
                String ingresos = ds.getIngresosTotales();
                if (ingresos == null) {
                    ingresos = "No asignada";
                }
                opcns.compararPais(opcio, ingresosTotales, ingresos);
                opcns.AgregarOpcionesPais(opcio, ingresosTotales, ingresos);

                opcio = opcns.llenaNumero();
                if (ds.getDependeEconomicamente() == null) {
                    ds.setDependeEconomicamente("0");
                }
                int dependencia = Integer.parseInt(ds.getDependeEconomicamente());
                opcns.comparar(opcio, dependeEconomicamente, dependencia);
                opcns.AgregarOpciones(opcio, dependeEconomicamente, dependencia);

                opcio = bd.llenarListas(6, 0);
                int ocupacionPa = ds.getOcupacionPadre();
                opcns.comparar(opcio, ocupacionPadre, ocupacionPa);
                opcns.AgregarOpciones(opcio, ocupacionPadre, ocupacionPa);

                opcio = bd.llenarListas(6, 0);
                int ocupacionMa = ds.getOcupacionMadre();
                opcns.comparar(opcio, ocupacionMadre, ocupacionMa);
                opcns.AgregarOpciones(opcio, ocupacionMadre, ocupacionMa);

                opcio = opcns.llenaCasaEs();
                String casaEsPrueba = ds.getTipoCasa();
                if (casaEsPrueba == null) {
                    ds.setTipoCasa("No asignada");
                }
                String casaEs = ds.getTipoCasa().toUpperCase();
                opcns.compararPais(opcio, tipoCasa, casaEs);
                opcns.AgregarOpcionesPais(opcio, tipoCasa, casaEs);

                opcio = opcns.llenaNumero();
                int noPersonas = ds.getNoPersonasCasa();
                opcns.comparar(opcio, noPersonasCasa, noPersonas);
                opcns.AgregarOpciones(opcio, noPersonasCasa, noPersonas);

                
                String noCuartos = ds.getCuartosCasa();
                opcio = opcns.llenaNumCuartos(noCuartos);
               
                if (noCuartos == null) {
                    noCuartos = "No asignada";
                }
                opcns.compararPais2(opcio, cuartosCasa, noCuartos);
                opcns.AgregarOpcionesPais2(opcio, cuartosCasa, noCuartos);

                opcio = opcns.llenaYN();
                String oportunidades = ds.getProgOportunidades();
                if (oportunidades == null) {
                    oportunidades = "No asignada";
                }
                opcns.compararPais(opcio, progOportunidades, oportunidades);
                opcns.AgregarOpcionesPais(opcio, progOportunidades, oportunidades);

                opcio = bd.llenarListas(5, 0);

                String vive = ds.getViveCon();
                if (vive == null) {
                    vive = "No asignada";
                }
                opcns.compararPais(opcio, viveCon, vive);
                opcns.AgregarOpcionesPais(opcio, viveCon, vive);

//                String noDependen = ds.getDependeDe();
////                if (noDependen == null) {
////                    noDependen = "No asignada";
////                }
////                opcns.compararPais(opcio, dependeDe, noDependen);
////                opcns.AgregarOpcionesPais(opcio, dependeDe, noDependen);
////
            }
        }
        out.print(bd.getErrorInsert());
        } catch (IndexOutOfBoundsException e) {

            if (!bd.getErrorInsert().contentEquals("ninguno")) {
                
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
               
            }
        } catch (NumberFormatException ee) {
            if (!bd.getErrorInsert().contentEquals("ninguno")) {
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
            }
        } catch (NullPointerException ee) {
            if (!bd.getErrorInsert().contentEquals("ninguno")) {
                out.print(bd.getErrorInsert() + "/Error al llenar listas de datos pesonales");
            } else {
                out.print("Error al llenar listas de datos personales");
       
            }
        }

        HttpSession session = request.getSession(true);

        session.setAttribute("dts", ds);
        session.setAttribute("ddd", ddd);
        session.setAttribute("edoDD", estado);
        session.setAttribute("munDD", municipio);
        session.setAttribute("locaDD", localidad);
        session.setAttribute("beca", becaDS);
        session.setAttribute("zonaProcedencia", Zona);
        session.setAttribute("maxEstudiosPadre", estudiosPadre);
        session.setAttribute("maxEstudiosMadre", estudiosMadre);
        session.setAttribute("ingresosTotales", ingresosTotales);
       // session.setAttribute("dependeDe", dependeDe);
        session.setAttribute("ocupacionPadre", ocupacionPadre);
        session.setAttribute("ocupacionMadre", ocupacionMadre);
        session.setAttribute("tipoCasa", tipoCasa);
        session.setAttribute("noPersonasCasa", noPersonasCasa);
        session.setAttribute("cuartosCasa", cuartosCasa);
        session.setAttribute("progOportunidades", progOportunidades);
        session.setAttribute("dependeEconomicamente", dependeEconomicamente);
        session.setAttribute("vivePa", vivePadre);
        session.setAttribute("viveMa", viveMadre);
        session.setAttribute("viveCon", viveCon);

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
