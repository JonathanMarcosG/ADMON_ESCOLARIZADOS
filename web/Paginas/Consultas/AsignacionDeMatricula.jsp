<%-- 
    Document   : AsignacionDeMatricula
    Created on : 15/07/2014, 09:36:11 AM
    Author     : Rocio
<script src="JQuery/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="JQuery/jquery-ui.js"></script>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
        <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
        <title>Cierre de proceso</title>
    </head>
    <%@include file="../Consultas/modalPestana1.jsp" %>
    <body>

        <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
        <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
        <!--<input type="hidden" id="idAspirante" name="idAspirante" value="${datosPersonales.getIdAsp()}">-->

        <div id="fondoSpin">
            <div id ="spinPago1"  class="progressSpin">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>


        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <br><a class="titulo">Cierre de proceso</a><br>
                <!--<br><h3>Consulta de Datos</h3><br>-->

            </ul>
            <br><br>


            <div id="Caja_busqueda">

                <label id="etiqueta_preficha">Preficha :</label>
                <input type="text"  maxlength="4" id="caja_boton_busquedapreficha" value="${datosPersonales.getPreficha()}" required/>
                <input type="button" id="botonPreficha" class="btn_style2" value="Buscar">
                <input id="guardarE" type="hidden">
                <input id="guardarS" type="hidden">
                <input  id="idAspirante" name="idAspirante" type="hidden">
                <input id="folioAM" type="hidden">
                <input id="validarAM" type="hidden">
                <input  id="libCenAM" type="hidden">
            </div>
            <br>


            <div id="Pestanas_info" class="tabs">
                <ul class="tab-links">
                    <li><a id="df" >Datos de la ficha</a></li>
                    <li><a id="ddatoss" >Domicilio y Datos Socioeconómicos</a></li>
                    <li><a id="ece" >En caso de emergencia</a></li>
                    <fieldset id="et_periodo">
                        <label id="Periodo">Periodo:</label>
                        <label id="tiempo">${fechas.getPeriodo()}</label>
                        <input type="hidden" id="libCeneval" value="${datosPersonales.getLibCeneval()}">
                    </fieldset>
                </ul>

                <%@include file="../cargarDiv.jsp"%>

                <div id="mensajeInicio">
                    <br><br>
                    <label id="etiquetaInicio">Por favor, ingrese una preficha para comenzar
                        la búsqueda.</label>
                    <br><br>
                    <img id="imgBusqueda" width="61%" height="40%" src="Imagenes/imgBusqueda.png">
                </div>
                <div class="tab-content" id="contenedor_pestanas">

                </div>
            </div>
        </div>
    </body>
</html>