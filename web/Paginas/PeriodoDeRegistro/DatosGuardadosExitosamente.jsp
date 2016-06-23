<%-- 
    Document   : DatosGuardadosExitosamente
    Created on : 20/08/2014, 10:20:20 PM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Periodo modificado exitosamente</title>
    </head>
    <body>

        <div id="Exito_datos" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <!--<br><h3>AVISO</h3><br>-->
                <br><a class="titulo">AVISO</a><br>
            </ul>
            <br>
            <div id="TipoDeBusqueda1">
                <label id="instrucciones">
                    ${fechas.getMensaje()}
                </label>
            </div>

            <div id="ModificacionPeriodo_contenedor">
                <br>
                <div id="confirmarPeriodo">
                    <section id="sobrePeriodo">
                        <fieldset id="fs_confirmacion">
                            ${fechas.getPeriodo()}

                        </fieldset>

                    </section>

                </div>
                <br><br><br>
            </div>


        </div>
    </body>
</html>
