/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosPersonales;
import Beans.EscuelaProcedencia;
import Beans.FolioCENEVAL;
import Beans.SelectCarreras;
import DAO.ActualizarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
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
        EscuelaProcedencia ep = new EscuelaProcedencia();
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
        String municipio = request.getParameter("municipio");
        String ciudad = request.getParameter("cuidad");

        String correo = request.getParameter("correo");
        String materno = request.getParameter("materno");
        String pais = request.getParameter("pais");
        String estado = request.getParameter("estado");
        String capDif = request.getParameter("capDif");

        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String sexo = request.getParameter("sexo");
        String edoCivil = request.getParameter("edoCivil");
        String sangre = request.getParameter("sangre");

        String opcion1 = request.getParameter("opcion1");

        String edoProcedencia = request.getParameter("estadoEP");
        String munProcedencia = request.getParameter("municipioEP");
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

        //Datos Personales
        dp.setPreficha(Integer.parseInt(preficha));
        dp.setMatricula(ficha);
        dp.setReferencia(referencia);

        dp.setFolio_ceneval(ceneval);

        dp.setLugarFechaA(LFA);
        dp.setLugarFechaB(LFB);

        dp.setCurp(curp);
        dp.setApellido_pat(paterno);
        dp.setFec_nacimiento(fecNac);
        dp.setMunNac(municipio);
        dp.setLocalidad(ciudad);

        dp.setCorreo(correo);
        dp.setApellido_mat(materno);
        dp.setPaisNac(pais);
        dp.setIdEdoNac(estado);
        dp.setCap_diferente(capDif);

        dp.setNombre(nombre);
        dp.setEdad(edad);
        dp.setSexo(sexo);
        dp.setEdo_civil(edoCivil);
        dp.setTipo_sangre(sangre);

        dp.setIdAsp(idAspirante);
        
        ep.setIdAspirante(idAspirante);

        ep.setNomEstado(edoProcedencia);
        System.out.println(ep.getNomEstado()+"este es el nombre del estado");
        ep.setNomMpio(munProcedencia);
        ep.setTipoEscuela(tipoEscuela);
        ep.setClasificacion(escuela);

        ep.setClaveEscuela(claveEscuela);
        ep.setFechaIni(Pinicio);
        ep.setFechaFin(Pfin);
        ep.setPromedio(promedio);

        ep.setEstadoFK(estado);

        SelectCarreras ac1 = new SelectCarreras();

        ac1.setIdPais(idAspirante);
        ac1.setClaveCarrera(1);
        ac1.setNombre(opcion1);
        HttpSession session = request.getSession(true);

        String error = "0";
        String[] codError;
        switch (opcion) {
            case 0:
                error = "correcto";
                break;
            case 1:
                codError = ActualizarDAO.actualizarDP(usuario, contra, dp).split("&");
                if (Integer.parseInt(codError[1]) == 0) {
                    codError = ActualizarDAO.actualizarCarreras(usuario, contra, ac1).split("&");
                    if (Integer.parseInt(codError[1]) == 0) {
                        error = codError[0];
                        fC.setFolioCENEVAL(dp.getFolio_ceneval());
                        session.setAttribute("datosPersonales", dp);
                        session.setAttribute("folioCeneval", fC);
                    }
                }
                break;
            case 2:
                codError = ActualizarDAO.actualizarEP(usuario, contra, ep).split("&");
                if (Integer.parseInt(codError[1]) == 0) {
                    error = codError[0];
                    session.setAttribute("escuelaProcedencia", ep);
                    System.out.println(ep.getNomEstado()+"llllllllllllllllllllll");
                }
                break;
            case 3:
                codError = ActualizarDAO.actualizarDP(usuario, contra, dp).split("&");
                if (Integer.parseInt(codError[1]) == 0) {
                    codError = ActualizarDAO.actualizarCarreras(usuario, contra, ac1).split("&");
                    if (Integer.parseInt(codError[1]) == 0) {
                        codError = ActualizarDAO.actualizarEP(usuario, contra, ep).split("&");
                        if (Integer.parseInt(codError[1]) == 0) {
                            error = codError[0];
                            fC.setFolioCENEVAL(dp.getFolio_ceneval());
                            session.setAttribute("escuelaProcedencia", ep);
                            session.setAttribute("datosPersonales", dp);
                            session.setAttribute("folioCeneval", fC);
                        }
                    }
                }
                break;
            default:
                break;
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
