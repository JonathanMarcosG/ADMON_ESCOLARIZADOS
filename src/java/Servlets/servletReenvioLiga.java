/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.ClaseEnviarCorreo;
import PDF.Encripta;
import Utils.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David
 */
public class servletReenvioLiga extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
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
        PrintWriter out = response.getWriter();
        try {

            ServletContext d = getServletContext();
            String correo = request.getParameter("correo");
      Encripta e = new Encripta();
           
            String email=e.encryptURL(correo.trim());
       
            String url = Constants.HOME_ASP+"/Datos_Aspirante.jsp?correo=" + email;
            ClaseEnviarCorreo cec = new ClaseEnviarCorreo();
            cec.setCorreo(correo);
            cec.setAsunto("Aspirante Tecnológico de Toluca: Liga para Registro");
            
            cec.setCuerpo("<b><font size=4 face=\"arialblack\" >"
                            + " Durante el proceso de registro recibirá los  siguientes  correos, por favor permanezca al pendiente: \n"
                            + "<br><br>"
                            + " 1.-Correo  de generación de preficha, que se le enviará  al concluir el registro de sus datos. \n"
                            + "<br>"
                            + "2.-Correo de liberación de pago, en un periodo máximo de 3 a 4 días hábiles después de haber realizado el pago bancario. \n"
                            + "<br>"
                            + "3.-Correo de alta en Ceneval, máximo 2 días  hábiles después del correo anterior. Es importante que reciba estos dos últimos correos. \n "
                            + "<br>"
                            + "4.-Correo de generación de ficha, que le será  enviado  al concluir el proceso de registro, esto  después de haber entregado sus papeles en el departamento de servicios escolares edif. X \n"
                            + "<br><br>"
                            + "En caso de no recibir alguno de ellos, comuníquese con nostros desde:  "
                            + "<br>"
                            + "<a href=\""+ Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a>  en el apartado de contacto.</font></b>"
                            + "<br><br>"
                            + "<b><ins>Importante:</ins><b> Para realizar cualquier cambio en los datos proporcionados deberá solicitarlo en ventanilla al"
                            + " momento de entregar su documentación en el departamento de Servicios Escolares Edif. X. Así mismo deberá recordar que no habrá cambios en  las opciones de carrera."
                            + "Tome en cuenta que es responsabilidad del aspirante cumplir con todas etapas del proceso\n"
                            + "para finalizar su registro de lo contrario su solicitud será rechazada."
                            + "<br><br>"
                            + "<b><ins>Advertencia:</ins></b> ES IMPORTANTE QUE CONSIDERE QUE UNA VEZ FINALIZADO SU REGISTRO TENDRÁ DOS DÍAS HÁBILES PARA REALIZAR SU PAGO, DE LO CONTRARIO SU REFERENCIA EXPIRARÁ. \n" +
"En caso de que su referencia  expire usted podrá renovarla ingresando  a la liga de <a href=\""+ Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a> en el apartado de \"Renovar referencia\" considerando que:"
                            + "<br><br>"
                            + "<u1>"
                            + "<li>Al realizar su registro y no realizar el pago oportuno de su preficha  su lugar en el tecnológico  no será contemplado.</li>"
                            + "<li>La renovación de  referencia  está sujeta a la disponibilidad de cupo en el tecnológico.</li>"
                            + "<li>El aspirante  tendrá hasta dos oportunidades para renovar su referencia.</li>"
                            + "<li>El aspirante tendrá un día hábil para realizar el pago de su preficha después de renovar su referencia. </li>"
                            + "</u1>"
                            + "<br><br>"
                            + "Para continuar con su registro por favor haga click en el siguiente enlace. "
                            + "<a href=" + url + " >  Registro Aspirante </a></font>."
);
            cec.sendFromMail(d);
            out.print(cec.getError());
        } catch (Exception ex) {
            Logger.getLogger(servletReenvioPref.class.getName()).log(Level.SEVERE, null, ex);
        }
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
