/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosPersonales;
import Beans.EnEmergencia;
import Beans.EscuelaProcedencia;
import Beans.FolioCENEVAL;
import Beans.SelectCarreras;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
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
//@WebServlet(name = "servletGuardarDP", urlPatterns = {"/servletGuardarDP"})
public class servletGuardarDP extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();
        DatosPersonales dp = new DatosPersonales();
        FolioCENEVAL fC = new FolioCENEVAL();
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String preficha = request.getParameter("preficha");
        String ficha = request.getParameter("ficha");
        String referencia = request.getParameter("referencia");
        String ceneval = request.getParameter("ceneval");
        String LFA = request.getParameter("LFA");
        String LFB = request.getParameter("LFB");
        String curp = request.getParameter("curp");
        String paterno = request.getParameter("paterno");
        String fecNac = request.getParameter("fechaNac");
        String sexo = request.getParameter("sexo");
        String edoCivil = request.getParameter("edoCivil");
        String correo = request.getParameter("correo");
        String capDif = request.getParameter("capDif");
        String opcion1 = request.getParameter("opcion1");
        String materno = request.getParameter("materno");
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        String municipio = request.getParameter("municipio");
        String ciudad = request.getParameter("cuidad");
        String sangre = request.getParameter("sangre");
        String opcion2 = request.getParameter("opcion2");
        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String opcion3 = request.getParameter("opcion3");

        EscuelaProcedencia ep = new EscuelaProcedencia();
        SelectCarreras ac1 = new SelectCarreras();
        SelectCarreras ac2 = new SelectCarreras();
        SelectCarreras ac3 = new SelectCarreras();

        String estadoFK = request.getParameter("estado");
        String tipoEscuela = request.getParameter("tipoEscuela");
        String escuela = request.getParameter("escuela");
        String claveEscuela = request.getParameter("claveEscuela");
        String Pinicio = request.getParameter("Pinicio");
        String Pfin = request.getParameter("Pfin");
        String promedio = request.getParameter("promedio");
        String idAspirante = request.getParameter("idAspirante");

        StringTokenizer st = new StringTokenizer(Pinicio, " ");
        String mes = st.nextToken();
        String anio = st.nextToken();
        st = new StringTokenizer(Pfin, " ");
        String mesFin = st.nextToken();
        String anioFin = st.nextToken();

        Pinicio = cadenaAInt(mes) + " " + anio;
        Pfin = cadenaAInt(mesFin) + " " + anioFin;
        ac1.setIdPais(idAspirante);
        ac1.setClaveCarrera(1);
        ac1.setNombre(opcion1);
        ac2.setIdPais(idAspirante);
        ac2.setClaveCarrera(2);
        ac2.setNombre(opcion2);
        ac3.setIdPais(idAspirante);
        ac3.setClaveCarrera(3);
        ac3.setNombre(opcion3);
        dp.setMatricula(ficha);
        dp.setReferencia(referencia);
        dp.setPreficha(Integer.parseInt(preficha));

        dp.setFolio_ceneval(ceneval);
        dp.setLugarFechaA(LFA);
        dp.setLugarFechaB(LFB);
        dp.setCurp(curp);
        dp.setApellido_pat(paterno);
        dp.setSexo(sexo);
        dp.setEdo_civil(edoCivil);
        dp.setCorreo(correo);
        dp.setCap_diferente(capDif);
        dp.setIdAsp(idAspirante);
        // dp.setOpcion1(opcion1);
        dp.setApellido_mat(materno);
        dp.setPaisNac(pais);
        dp.setIdEdoNac(estado);
        dp.setMunNac(municipio);
        dp.setLocalidad(ciudad);
        dp.setTipo_sangre(sangre);
        dp.setEdad(edad);
        dp.setNombre(nombre);
        dp.setFec_nacimiento(fecNac);

        ep.setEstadoFK(estadoFK);
        ep.setTipoEscuela(tipoEscuela);
        ep.setClasificacion(escuela);
        ep.setClaveEscuela(claveEscuela);
        ep.setFechaIni(Pinicio);
        ep.setFechaFin(Pfin);
        ep.setPromedio(promedio);
        ep.setIdAspirante(idAspirante);
        
        HttpSession session = request.getSession(true);

        IngresoAbd bd = new IngresoAbd(usuario, contra);
        String error = "0";
        if (bd.getErrorInsert().contentEquals("ninguno")) {
            error = "correcto";
            if (opcion == 0) {
                error = "correcto";
            } else if (opcion == 1) {
                bd.actualizarDP(dp);
                error = bd.getErrorInsert();
                bd.actualizarCarreras(ac3);
                error += bd.getErrorInsert();
                bd.actualizarCarreras(ac2);
                error += bd.getErrorInsert();
                bd.actualizarCarreras(ac1);
                error += bd.getErrorInsert();
                if (error.contentEquals("ningunoningunoningunoninguno")) {
                    error = "correcto";
                    fC.setFolioCENEVAL(ceneval);
                    session.setAttribute("datosPersonales", dp);
                    session.setAttribute("folioCeneval", fC);

                }
            } else if (opcion == 2) {
                bd.actualizarEP(ep);
                error = bd.getErrorInsert();

                if (error.contentEquals("ninguno")) {
                    error = "correcto";
                    session.setAttribute("escuelaProcedencia", ep);
                }
            } else if (opcion == 3) {
                bd.actualizarDP(dp);
                error = bd.getErrorInsert();
                bd.actualizarCarreras(ac3);
                error += bd.getErrorInsert();
                bd.actualizarCarreras(ac2);
                error += bd.getErrorInsert();
                bd.actualizarCarreras(ac1);
                error += bd.getErrorInsert();
                bd.actualizarEP(ep);
                error += bd.getErrorInsert();

                if (error.contentEquals("ningunoningunoningunoningunoninguno")) {
                    error = "correcto";
                    fC.setFolioCENEVAL(dp.getFolio_ceneval());
                    session.setAttribute("escuelaProcedencia", ep);
                    session.setAttribute("datosPersonales", dp);
                    session.setAttribute("folioCeneval", fC);
                }
            }
        } else {
            error = bd.getErrorInsert();
        }
        out.print(error);

    }

    public int cadenaAInt(String mes) {
        int mesEntero = 0;

        switch (mes) {
            case "Enero":
                mesEntero = 1;
                break;
            case "Febrero":
                mesEntero = 2;
                break;
            case "Marzo":
                mesEntero = 3;
                break;
            case "Abril":
                mesEntero = 4;
                break;
            case "Mayo":
                mesEntero = 5;
                break;
            case "Junio":
                mesEntero = 6;
                break;
            case "Julio":
                mesEntero = 7;
                break;
            case "Agosto":
                mesEntero = 8;
                break;
            case "Septiembre":
                mesEntero = 9;
                break;
            case "Octubre":
                mesEntero = 10;
                break;
            case "Noviembre":
                mesEntero = 11;
                break;
            case "Diciembre":
                mesEntero = 12;
                break;
        }
        return mesEntero;
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
