/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Utils.Constants;
import Beans.ClaseEnviarCorreo;
import ConexionBD.IngresoAbd;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rocio
 */
//@WebServlet(name = "servletCorreoCENEVAL", urlPatterns = {"/servletCorreoCENEVAL"})
public class servletCorreoCENEVAL extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
            ServletContext d = getServletContext();
            String user = request.getParameter("usuario");
            String pass = request.getParameter("contra");
            String correo = request.getParameter("correo");
            String nombre = request.getParameter("nombre");
            String folio = request.getParameter("folio");
            IngresoAbd bd = new IngresoAbd(user, pass);
            String url = bd.urlCeneval();

            String cuerpo2 = " <div  style=\"position: relative;left:130px;\" >\n"
                    + "            <pre style=\"font-family:'calibri'; font-size: 16px;\">\n"
                    + "            Estimado(a) " + nombre + " por favor ingrese a la página <a href=\"" + url + "\">" + url + "</a>  con la \n"
                    + "\n"
                    + "            siguiente matrícula.\n"
                    + "\n"
                    + "            Usuario (FICHA): " + folio + "\n"
                    + "\n"
                    + "            Procedimiento: \n"
                    + "\n"
                    + "            1. En cualquier lugar con acceso a internet ingrese a la página de CENEVAL utilizando la FICHA otorgada por el Instituto Tecnológico de Toluca.\n"
                    + "\n"
                    + "            2. Deberá dar  respuesta a los cuestionarios de la página de CENEVAL.\n"
                    + "\n"
                    + "            3. Deberá imprimir  el pase de ingreso a examen.\n"
                    + "\n"
                    + "            Una vez que cuente con el pase de ingreso a examen, acuda al Instituto Tecnológico de Toluca y siga los pasos que se explican a continuación:\n"
                    + "\n"
                    + "            1. Canjear el comprobante de pago.\n"
                    + "\n"
                    + "            - Acudir a las  instalaciones del Instituto Tecnológico de Toluca y dirigirse al  Depto. de SERVICIOS FINANCIEROS (Edif. \"A\") de Lunes a Viernes de 9:00 a \n"
                    + "\n"
                    + "            18:00 hrs.\n"
                    + "\n"
                    + "            - Entregar en ventanilla el  comprobante de pago proporcionado por el banco y canjearlo por el recibo oficial de pago.\n"
                    + "\n"
                    + "            2. Entregar la documentación correspondiente.\n"
                    + "\n"
                    + "            - Acudir a las  instalaciones del Instituto Tecnológico de Toluca y dirigirse al Depto. de SERVICIOS ESCOLARES (Edif. \"X\") de Lunes a Viernes de 9:00 a \n"
                    + "\n"
                    + "            18:00 hrs. \n"
                    + "\n"
                    + "            - Entregar el pase de ingreso a examen (firmado), recibo oficial de pago, una fotografía tamaño infantil y preficha. \n"
                    + "\n"
                    + "            - Recibir el material de estudio (guía de estudio y temario de matemáticas).\n"
                    + "\n"
                    + "            - Recibir la Ficha de examen con la información necesaria para presentar el examen de admisión.\n"
                    + "\n"
                    + "\n"
                    + "            <strong><u>Aviso: Le recordamos que es de suma importancia presentar la ficha de examen el día que realice su examen de admisión.</u></strong> \n"
                    + "\n"
                    + "\n"
                    + "             <b><ins>NOTA: </ins><b>Para fines prácticos le recomendamos revise el archivo anexo, mismo que le servirá como guia para el proceso de registro en CENEVAL."
                    + "            </pre>\n"
                    + "        </div>";
            String asunto = "Aspirante Tecnológico de Toluca: Registro en Ceneval";

            ClaseEnviarCorreo cec = new ClaseEnviarCorreo();
            cec.setCorreo(correo);
            cec.setCuerpo(cuerpo2);
            cec.setAsunto(asunto);
            cec.sendMail(d);
            cec.getError();
            out.print(cec.getError());
        } catch (Exception ex) {
            Logger.getLogger(servletCorreoCENEVAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
