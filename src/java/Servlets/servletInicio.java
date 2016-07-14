/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author Rocio
 */
import Beans.ConfigurarPeriodo;
import Beans.DatosDelDomicilio;
import Beans.DatosPersonales;
import Beans.DatosSocioeconomicos;
import Beans.EnEmergencia;
import Beans.EscuelaProcedencia;
import Beans.LiberacionPago;
import Beans.SeguimientoDelAlumno;
import Beans.SelectCarreras;
import Beans.ValidacionContrasenia;
import Beans.VerificarCambio;
//import ConexionBD.IngresoAbd;
import DAO.ConvocatoriaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "servletInicio", urlPatterns = {"/servletInicio"})}
/* 
*
* Se ejecuta cuando se ingresa usuario y contraseña en la pantalla principal del módulo.
* Valida que el usuario y la contraseña sean válidos y tengan privilegios para ingresar a la aplicación.
* Inicializa las listas que se ocupan en la aplicación.
 */
public class servletInicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        boolean verificar = true;
        String error = "correcto";
        response.setContentType("text/html;charset=ISO-8859-1");
        request.setCharacterEncoding("UTF8");
        PrintWriter out = response.getWriter();

        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        ValidacionContrasenia vc = new ValidacionContrasenia();
        vc.setUsuario(usuario);
        vc.setContransenia(contra);
        ConfigurarPeriodo cp;

//        IngresoAbd bd = new IngresoAbd(usuario, contra);

//        try {
//            cp = bd.verificarPeriodo();
//        } catch (NullPointerException e) {
//            CompararErrores errores = new CompararErrores();
//            error = bd.getError() + "";
//            
//            errores.buscarError(Integer.parseInt(error));
//            out.print(errores.getMensajeDeError());
//            if (error.contentEquals("1017")) {
//                
//            }
//        }
        cp = ConvocatoriaDAO.verificarPeriodo(usuario, contra);

//        if (bd.getErrorInsert().equals("ninguno") || bd.getError() == 101) {
        if (cp.getCodError()==0) {
            cp.cambioPeriodo();
            out.print(error);
        } else {
            out.print(cp.getCodError());
        }
//            if (bd.getError() == -103) {
//            cp.setPeriodo(bd.getErrorInsert());
//
//        } else {
//            CompararErrores errores = new CompararErrores();
//            error = bd.getError() + "";
//
//            errores.buscarError(Integer.parseInt(error));
//
//            if (!error.contentEquals("1017")) {
//                out.print(errores.getMensajeDeError());
//
//            }
//            out.print(bd.getErrorInsert());
//        }
        VerificarCambio ver = new VerificarCambio();
        ver.setFichaUtilizada(0);
        DatosPersonales dp = null;
//        System.out.println((dp.getPreficha()));
        EscuelaProcedencia ep = new EscuelaProcedencia();
        DatosDelDomicilio ddd = new DatosDelDomicilio();
        DatosSocioeconomicos ds = new DatosSocioeconomicos();
        EnEmergencia ee = new EnEmergencia();
        LiberacionPago lp = new LiberacionPago();
        SeguimientoDelAlumno sda = new SeguimientoDelAlumno();
        List<SelectCarreras> sexo = new ArrayList<>();
        List<SelectCarreras> edoCivil = new ArrayList<>();
        List<SelectCarreras> capDif = new ArrayList<>();
        List<SelectCarreras> tipoSangre = new ArrayList<>();

        List<SelectCarreras> op1 = new ArrayList<>();
        List<SelectCarreras> op2 = new ArrayList<>();
        List<SelectCarreras> op3 = new ArrayList<>();
        List<SelectCarreras> nacionalidad = new ArrayList<>();
        List<SelectCarreras> estado = new ArrayList<>();
        List<SelectCarreras> municipio = new ArrayList<>();
        List<SelectCarreras> localidad = new ArrayList<>();

        List<SelectCarreras> vivePadre = new ArrayList<>();
        List<SelectCarreras> viveMadre = new ArrayList<>();
        List<SelectCarreras> becaDS = new ArrayList<>();
        List<SelectCarreras> Zona = new ArrayList<>();
        List<SelectCarreras> estudiosPadre = new ArrayList<>();
        List<SelectCarreras> estudiosMadre = new ArrayList<>();
        List<SelectCarreras> ingresosTotales = new ArrayList<>();
        List<SelectCarreras> dependeDe = new ArrayList<>();
        List<SelectCarreras> ocupacionPadre = new ArrayList<>();
        List<SelectCarreras> ocupacionMadre = new ArrayList<>();
        List<SelectCarreras> tipoCasa = new ArrayList<>();
        List<SelectCarreras> noPersonasCasa = new ArrayList<>();
        List<SelectCarreras> cuartosCasa = new ArrayList<>();
        List<SelectCarreras> progOportunidades = new ArrayList<>();
        List<SelectCarreras> viveCon = new ArrayList<>();
        List<SelectCarreras> dependeEconomicamente = new ArrayList<>();

        HttpSession session = request.getSession(true);

        session.setAttribute("datosPersonales", dp);
        session.setAttribute("datos", vc);
        session.setAttribute("fechas", cp);
        session.setAttribute("escuelaProcedencia", ep);
        session.setAttribute("dts", ds);
        session.setAttribute("ddd", ddd);
        session.setAttribute("emergencia", ee);
        session.setAttribute("opcion1", op1);
        session.setAttribute("opcion2", op2);
        session.setAttribute("opcion3", op3);
        session.setAttribute("verificacion", ver);
        session.setAttribute("lp", lp);
        session.setAttribute("nacionalidad", nacionalidad);
        session.setAttribute("estado", nacionalidad);
        session.setAttribute("municipio", nacionalidad);
        session.setAttribute("localidad", nacionalidad);
        session.setAttribute("sexo", sexo);
        session.setAttribute("capacidad", capDif);
        session.setAttribute("sangre", tipoSangre);
        session.setAttribute("civil", edoCivil);
        session.setAttribute("opcion1", op1);
        session.setAttribute("opcion2", op2);
        session.setAttribute("opcion3", op3);

        session.setAttribute("escuela", estado);
        session.setAttribute("estadoEP", municipio);
        session.setAttribute("tEscuela", localidad);

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
        session.setAttribute("dependeDe", dependeDe);
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

        session.setAttribute("edoE", estado);
        session.setAttribute("munE", municipio);

        session.setAttribute("seguimiento", sda);
        session.setAttribute("preficha", 0);
        session.setAttribute("pago", 0);
        session.setAttribute("registroCeneval", 0);
        session.setAttribute("folioCeneval", 0);
        session.setAttribute("ficha", 0);
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
