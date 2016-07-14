/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Beans.BMail;
import Utils.Constants;

/**
 *
 * @author Jony
 */
public class CuerpoCorreos {

    BMail beanMail;

    public BMail inicioRegistro(String nombre, String ficha, String carrera) {
        beanMail = new BMail();
        String mensaje = new StringBuffer()
                .append("<div  style=\"position: relative;left:130px;\" >\n")
                .append("            <pre style=\"font-family:'calibri'; font-size: 16px;\">\n")
                .append("            Estimado(a) ")
                .append(nombre)
                .append(" su pago ha sido procesado con éxito.\n")
                .append("<br>")
                .append("            Datos del Aspirante:\n")
                .append("<br>")
                .append("	     Ficha: ")
                .append(ficha)
                .append("\n")
                .append("	     Carrera seleccionada en primera opción: ")
                .append(carrera)
                .append("\n")
                .append("<br>")
                .append("            Por favor verifique  constantemente su bandeja de entrada,  ya que recibirá un correo indicando el procedimiento para  darse de alta en CENEVAL.\n")
                .append("<br>")
                .append("<br>")
                .append("            <strong style=\"color:black\">Instituto Tecnológico de Toluca</strong>\n")
                .append("<br>")
                .append("            <strong style=\"color:black\">Departamento de Servicios Escolares</strong>\n")
                .append("<br>")
                .append("            </pre>\n")
                .append("        </div>")
                .toString();

        beanMail.setCuerpo(mensaje);
        return beanMail;
    }

    public BMail ligaRegistro(String url) {
        beanMail = new BMail();
        String mensaje = new StringBuffer()
                .append("<b><font size=4 face=\"arialblack\" >")
                .append(" Durante el proceso de registro recibirá los  siguientes  correos, por favor permanezca al pendiente: \n")
                .append("<br><br>")
                .append(" 1.-Correo  de generación de preficha, que se le enviará  al concluir el registro de sus datos. \n")
                .append("<br>")
                .append("2.-Correo de liberación de pago, en un periodo máximo de 3 a 4 días hábiles después de haber realizado el pago bancario. \n")
                .append("<br>")
                .append("3.-Correo de alta en Ceneval, máximo 2 días  hábiles después del correo anterior. Es importante que reciba estos dos últimos correos. \n ")
                .append("<br>")
                .append("4.-Correo de generación de ficha, que le será  enviado  al concluir el proceso de registro, esto  después de haber entregado sus papeles en el departamento de servicios escolares edif. X \n")
                .append("<br><br>")
                .append("En caso de no recibir alguno de ellos, comuníquese con nostros desde:  ")
                .append("<br>")
                .append("<a href=\"" + Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a>  en el apartado de contacto.</font></b>")
                .append("<br><br>")
                .append("<b><ins>Importante:</ins><b> Para realizar cualquier cambio en los datos proporcionados deberá solicitarlo en ventanilla al")
                .append(" momento de entregar su documentación en el departamento de Servicios Escolares Edif. X. Así mismo deberá recordar que no habrá cambios en  las opciones de carrera.")
                .append("Tome en cuenta que es responsabilidad del aspirante cumplir con todas etapas del proceso\n")
                .append("para finalizar su registro de lo contrario su solicitud será rechazada.")
                .append("<br><br>")
                .append("<b><ins>Advertencia:</ins></b> ES IMPORTANTE QUE CONSIDERE QUE UNA VEZ FINALIZADO SU REGISTRO TENDRÁ DOS DÍAS HÁBILES PARA REALIZAR SU PAGO, DE LO CONTRARIO SU REFERENCIA EXPIRARÁ. \n")
                .append("En caso de que su referencia  expire usted podrá renovarla ingresando  a la liga de <a href=\"" + Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a> en el apartado de \"Renovar referencia\" considerando que:")
                .append("<br><br>")
                .append("<u1>")
                .append("<li>Al realizar su registro y no realizar el pago oportuno de su preficha  su lugar en el tecnológico  no será contemplado.</li>")
                .append("<li>La renovación de  referencia  está sujeta a la disponibilidad de cupo en el tecnológico.</li>")
                .append("<li>El aspirante  tendrá hasta dos oportunidades para renovar su referencia.</li>")
                .append("<li>El aspirante tendrá un día hábil para realizar el pago de su preficha después de renovar su referencia. </li>")
                .append("</u1>")
                .append("<br><br>")
                .append("Para continuar con su registro por favor haga click en el siguiente enlace. ")
                .append("<a href=")
                .append(url)
                .append(" >  Registro Aspirante </a></font>.")
                .toString();
        beanMail.setCuerpo(mensaje);
        return beanMail;
    }

    public BMail reenvioPreficha(String url) {
        beanMail = new BMail();
        String mensaje = new StringBuffer()
                .append("<b><font size=4 face=\"arialblack\"> Durante el proceso de registro recibirá los  siguientes  correos, por favor permanezca al pendiente: \n")
                .append("<br><br>")
                .append(" 1.-Correo  de generación de preficha, que se le enviará  al concluir el registro de sus datos. \n")
                .append("<br>")
                .append("2.-Correo de liberación de pago, en un periodo máximo de 3 a 4 días hábiles después de haber realizado el pago bancario. \n")
                .append("<br>")
                .append("3.-Correo de alta en Ceneval, máximo 2 días  hábiles  después del correo anterior. Es importante que reciba estos dos últimos correos. \n ")
                .append("<br>")
                .append("4.-Correo de generación de ficha, que se enviará al concluir el proceso de registro, esto es después de haber entregado sus \n")
                .append("papeles en el departamento de servicios escolares edif. X. \n ")
                .append("<br><br>")
                .append("En caso de no recibir alguno de ellos, comuníquese con nostros desde:  ")
                .append("<br>")
                .append(" <a href=\"" + Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a> en el apartado de contacto.</font></b>")
                .append("<br><br>")
                .append("<font size=4 face=\"arialblack\">")
                .append("<b><ins>Importante:</b></ins> Para realizar cualquier cambio en los datos proporcionados deberá solicitarlo ")
                .append("en ventanilla al momento de entregar su documentación en el departamento de Servicios Escolares Edif. X. ")
                .append("Así mismo deberá recordar que no habrá cambios en las opciones de carrera. Tome en cuenta que es responsabilidad ")
                .append("del aspirante cumplir con todas etapas del proceso para finalizar su registro de lo contrario su solicitud será ")
                .append("rechazada.")
                .append("<br><br>")
                .append("<b><ins>Advertencia:</ins> ES IMPORTANTE QUE CONSIDERE QUE UNA VEZ FINALIZADO SU REGISTRO TENDRÁ DOS DÍAS HÁBILES PARA REALIZAR SU PAGO, DE LO CONTRARIO SU REFERENCIA EXPIRARÁ.")
                .append("\nEn caso de que su referencia  expire usted podrá renovarla ingresando  a la liga de <a href=\"" + Constants.HOME_ASP + "\" target=\"_blank\">" + Constants.HOME_ASP + " </a> en el apartado de \"Renovar referencia\" considerando que ")
                .append("<br><br>")
                .append("<u1>")
                .append("<li>Al realizar su registro y no realizar el pago oportuno de su preficha  su lugar en el tecnológico  no será contemplado.</li>")
                .append("<li>La renovación de  referencia  está sujeta a la disponibilidad de cupo en el tecnológico.</li>")
                .append("<li>El aspirante  tendrá hasta dos oportunidades para renovar su referencia.</li>")
                .append("<li>El aspirante tendrá un día hábil para realizar el pago de su preficha después de renovar su referencia. </li>")
                .append("</u1>")
                .append("<br><br>")
                .append("Recibirá una  notificación cuando su  pago haya sido  procesado, permanezca al pendiente de  su correo. ")
                .append("Por favor haga click en el siguiente enlace para que pueda ver su preficha. ")
                .append("<a href=")
                .append(url)
                .append(">Genera Preficha</a></font>.")
                .toString();
        beanMail.setCuerpo(mensaje);
        return beanMail;
    }

    public BMail registroCeneval(String url,String nombre,String folio) {
        beanMail = new BMail();
        String mensaje = new StringBuffer()
                .append(" <div  style=\"position: relative;left:130px;\" >\n")
                .append("            <pre style=\"font-family:'calibri'; font-size: 16px;\">\n")
                .append("            Estimado(a) ")
                .append(nombre)
                .append(" por favor ingrese a la página <a href=\"")
                .append(url)
                .append("\">")
                .append(url)
                .append("</a>  con la \n")
                .append("<br>")
                .append("            siguiente matrícula.\n")
                .append("<br>")
                .append("            Usuario (FICHA): ")
                .append(folio)
                .append("\n")
                .append("<br>")
                .append("            Procedimiento: \n")
                .append("<br>")
                .append("            1. En cualquier lugar con acceso a internet ingrese a la página de CENEVAL utilizando la FICHA otorgada por el Instituto Tecnológico de Toluca.\n")
                .append("<br>")
                .append("            2. Deberá dar  respuesta a los cuestionarios de la página de CENEVAL.\n")
                .append("<br>")
                .append("            3. Deberá imprimir  el pase de ingreso a examen.\n")
                .append("<br>")
                .append("            Una vez que cuente con el pase de ingreso a examen, acuda al Instituto Tecnológico de Toluca y siga los pasos que se explican a continuación:\n")
                .append("<br>")
                .append("            1. Canjear el comprobante de pago.\n")
                .append("<br>")
                .append("            - Acudir a las  instalaciones del Instituto Tecnológico de Toluca y dirigirse al  Depto. de SERVICIOS FINANCIEROS (Edif. \"A\") de Lunes a Viernes de 9:00 a \n")
                .append("<br>")
                .append("            18:00 hrs.\n")
                .append("<br>")
                .append("            - Entregar en ventanilla el  comprobante de pago proporcionado por el banco y canjearlo por el recibo oficial de pago.\n")
                .append("<br>")
                .append("            2. Entregar la documentación correspondiente.\n")
                .append("<br>")
                .append("            - Acudir a las  instalaciones del Instituto Tecnológico de Toluca y dirigirse al Depto. de SERVICIOS ESCOLARES (Edif. \"X\") de Lunes a Viernes de 9:00 a \n")
                .append("<br>")
                .append("            18:00 hrs. \n")
                .append("<br>")
                .append("            - Entregar el pase de ingreso a examen (firmado), recibo oficial de pago, una fotografía tamaño infantil y preficha. \n")
                .append("<br>")
                .append("            - Recibir el material de estudio (guía de estudio y temario de matemáticas).\n")
                .append("<br>")
                .append("            - Recibir la Ficha de examen con la información necesaria para presentar el examen de admisión.\n")
                .append("<br><br>")
                .append("            <strong><u>Aviso: Le recordamos que es de suma importancia presentar la ficha de examen el día que realice su examen de admisión.</u></strong> \n")
                .append("<br><br>")
                .append("             <b><ins>NOTA: </ins><b>Para fines prácticos le recomendamos revise el archivo anexo, mismo que le servirá como guia para el proceso de registro en CENEVAL.")
                .append("            </pre>\n")
                .append("        </div>")
                .toString();
        beanMail.setCuerpo(mensaje);
        return beanMail;
    }
}
