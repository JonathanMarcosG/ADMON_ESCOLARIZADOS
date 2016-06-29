/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.DatosPersonales;
import Beans.EscuelaProcedencia;
import Beans.FolioCENEVAL;
import Beans.ListaCarreras;
import Beans.SelectCarreras;
import ConexionBD.IngresoAbd;
import DAO.CatalogosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
//@WebServlet(name = "servletDatosPersonales", urlPatterns = {"/servletDatosPersonales"})
public class servletDatosPersonales extends HttpServlet implements Serializable {

    String errorServlet;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        String preficha = request.getParameter("preficha");

        DatosPersonales dp = null;
        EscuelaProcedencia ep = null;

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
        List<SelectCarreras> mpioEP = new ArrayList<SelectCarreras>();
        List<SelectCarreras> tipoEscuelaEP = new ArrayList<SelectCarreras>();
        List<SelectCarreras> escuelaEP = new ArrayList<SelectCarreras>();

        FolioCENEVAL fC = new FolioCENEVAL();

        IngresoAbd bd = new IngresoAbd(usuario, contra);

        dp = bd.obtenetDatosPersonales(preficha);
        try {
            if (bd.getErrorInsert().contentEquals("ninguno")) {

                fC.setFolioCENEVAL(dp.getFolio_ceneval());
                int pago = bd.verificacionEnSeguimiento(dp.getIdAsp(), 3);
                // Validación de pago, sí es 1 se llenan las listas, de lo contrario
                //no se terminan de llenar
                if (pago == 1 || pago == 0) {
                    ep = bd.obtenerDatosEP(preficha);
                    if (bd.getErrorInsert().contentEquals("ninguno")) {

                        ListaCarreras opcns = new ListaCarreras();
                        List<SelectCarreras> opcio = bd.llenaOpcionesCarreras(preficha);

                        if (bd.getErrorInsert().contentEquals("ninguno")) {
                            int opcion1 = 0, opcion2 = 0, opcion3 = 0;
                            for (int i = 0; i < opcio.size(); i++) {
                                if (Integer.parseInt(opcio.get(i).getIdPais()) == 1) {
                                    opcion1 = opcio.get(i).getClaveCarrera();

                                } else if (Integer.parseInt(opcio.get(i).getIdPais()) == 2) {
                                    opcion2 = opcio.get(i).getClaveCarrera();

                                } else if (Integer.parseInt(opcio.get(i).getIdPais()) == 3) {
                                    opcion3 = opcio.get(i).getClaveCarrera();

                                }
                            }

                            opcio = CatalogosDAO.llenarListas(usuario, contra, 10, 0);
//                            opcio = bd.llenarListas(10, 0);

                            opcns.comparar(opcio, op1, opcion1);
                            opcns.comparar(opcio, op2, opcion2);
                            opcns.comparar(opcio, op3, opcion3);

                            opcns.SinRepetir(opcio, op1, opcion2, opcion3);
                            opcns.SinRepetir(opcio, op2, opcion1, opcion3);
                            opcns.SinRepetir(opcio, op3, opcion2, opcion1);

                            opcio = bd.llenarListaPais(1, 0);
                            String pais = dp.getNacionalidad();
                            opcns.compararPais(opcio, nacionalidad, pais);
                            opcns.AgregarOpcionesPais(opcio, nacionalidad, pais);

                            if (!pais.contains("MEX")) {

                            } else {

                                opcio = CatalogosDAO.llenarListas(usuario, contra, 2, 0);
//                                opcio = bd.llenarListas(2, 0);
                                int idEstado = Integer.parseInt(dp.getIdEdoNac());

                                opcns.comparar(opcio, estado, idEstado);
                                opcns.AgregarOpciones(opcio, estado, idEstado);

                                opcio = CatalogosDAO.llenarListas(usuario, contra, 3, idEstado);
//                                opcio = bd.llenarListas(3, idEstado);
                                int mun = Integer.parseInt(dp.getMunNac());
                                opcns.comparar(opcio, municipio, mun);
                                opcns.AgregarOpciones(opcio, municipio, mun);

                                opcio = CatalogosDAO.llenarListas(usuario, contra, 9, mun);
//                                opcio = bd.llenarListas(9, mun);
                                int local = Integer.parseInt(dp.getLocalidad());
                                opcns.comparar(opcio, localidad, local);
                                opcns.AgregarOpciones(opcio, localidad, local);
                            }
                            opcio = opcns.llenaDiscapacidad();
                            String discapacidad = dp.getCap_diferente().toUpperCase();
                            opcns.compararPais(opcio, capDif, discapacidad);
                            opcns.AgregarOpcionesPais(opcio, capDif, discapacidad);

                            opcio = opcns.llenaEdoCivil();
                            String edoCiv = dp.getEdo_civil().toUpperCase();

                            opcns.compararPais(opcio, edoCivil, edoCiv);
                            opcns.AgregarOpcionesPais(opcio, edoCivil, edoCiv);

                            opcio = opcns.llenaSangre();
                            String sangre = dp.getTipo_sangre().toUpperCase();
                            opcns.compararPais(opcio, tipoSangre, sangre);
                            opcns.AgregarOpcionesPais(opcio, tipoSangre, sangre);

                            opcio = opcns.llenaSexo();
                            String genero = dp.getSexo().toUpperCase();
                            opcns.compararPais(opcio, sexo, genero);
                            opcns.AgregarOpcionesPais(opcio, sexo, genero);
                        }
                    }
                    ListaCarreras opcns = new ListaCarreras();
                    List<SelectCarreras> opcio = bd.llenaOpcionesCarreras(preficha);

//                opcio = bd.llenarListas(2, 0);
//                int idEstadoEsculaProd = Integer.parseInt(ep.getEstadoFK());
//                
//                opcns.comparar(opcio, estadoEP, idEstadoEsculaProd);
//                ep.setNomEstado(estadoEP.get(0).getNombre());
//                opcio = bd.llenarListas(3, 0);
//                int idMpioEsculaProd = Integer.parseInt(ep.getNomMpio());
//                
//                opcns.comparar(opcio, mpioEP, idMpioEsculaProd);
//                ep.setNomMpio(mpioEP.get(0).getNombre());
                    opcio = CatalogosDAO.llenarListas(usuario, contra, 8, 0);
//                    opcio = bd.llenarListas(8, 0);
                    int idEscuela = Integer.parseInt(ep.getClasificacion());
                    opcns.comparar(opcio, escuelaEP, idEscuela);
                    opcns.AgregarOpciones(opcio, escuelaEP, idEscuela);

                    opcio = opcns.llenaTEscuela();
                    String tEscuela = ep.getTipoEscuela().toUpperCase();

                    opcns.compararPais(opcio, tipoEscuelaEP, tEscuela);
                    opcns.AgregarOpcionesPais(opcio, tipoEscuelaEP, tEscuela);

                    int ceneval = bd.verificacionEnSeguimiento(dp.getIdAsp(), 5);
                    dp.setLibCeneval(bd.getErrorInsert() + ceneval);
                   
                    out.print(bd.getErrorInsert() + ceneval);

                    String inicio = ep.getFechaFin();
                    String fin = ep.getFechaIni();
                    StringTokenizer st = new StringTokenizer(inicio, " ");
                    int mes = Integer.parseInt(st.nextToken());
                    int anio = Integer.parseInt(st.nextToken());

                    st = new StringTokenizer(fin, " ");
                    int mesFin = Integer.parseInt(st.nextToken());
                    int anioFin = Integer.parseInt(st.nextToken());
                    ep.setFechaIni(compararMes(mes) + " " + anio);
                    ep.setFechaFin(compararMes(mesFin) + " " + anioFin);

                } else {
                    if (pago == 0) {
                        
                        out.print("0");
                        dp.setLibCeneval("0");
                    } else {
                        out.print("Ha ocurrido un error" + bd.getErrorCE());
                    }
                }

            } else {
                out.print(bd.getErrorInsert());
                
            }

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
        }
        HttpSession session = request.getSession(true);
        //     session.setAttribute("aspirante", aspirante);
        session.setAttribute("datosPersonales", dp);
        session.setAttribute("escuelaProcedencia", ep);
        session.setAttribute("nacionalidad", nacionalidad);
        session.setAttribute("estado", estado);
        session.setAttribute("municipio", municipio);
        session.setAttribute("localidad", localidad);
        session.setAttribute("sexo", sexo);
        session.setAttribute("capacidad", capDif);
        session.setAttribute("sangre", tipoSangre);
        session.setAttribute("civil", edoCivil);
        session.setAttribute("opcion1", op1);
        session.setAttribute("opcion2", op2);
        session.setAttribute("opcion3", op3);
        session.setAttribute("escuela", escuelaEP);
        session.setAttribute("estadoEP", estadoEP);
        session.setAttribute("mpioEP", mpioEP);
        session.setAttribute("tEscuela", tipoEscuelaEP);
        session.setAttribute("folioCeneval", fC);
    }

    public String compararMes(int mes) {
        String letraMes = "error";
        switch (mes) {
            case 1:
                letraMes = "Enero";
                break;
            case 2:
                letraMes = "Febrero";
                break;
            case 3:
                letraMes = "Marzo";
                break;
            case 4:
                letraMes = "Abril";
                break;
            case 5:
                letraMes = "Mayo";
                break;
            case 6:
                letraMes = "Junio";
                break;
            case 7:
                letraMes = "Julio";
                break;
            case 8:
                letraMes = "Agosto";
                break;
            case 9:
                letraMes = "Septiembre";
                break;
            case 10:
                letraMes = "Octubre";
                break;
            case 11:
                letraMes = "Noviembre";
                break;
            case 12:
                letraMes = "Diciembre";
                break;
        }
        return letraMes;
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
