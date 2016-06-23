<%-- 
    Document   : menu_administrador
    Created on : 7/07/2014, 09:51:35 AM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
        <script src="JQuery/ScriptAsignacion.js" type="text/javascript"></script>
        <link rel="stylesheet" href="Css/bubbles.css" type="text/css">
        <script src="JQuery/contadorCorreo.js" type="text/javascript"></script>
        <title>Menú Administrador</title>
    </head>

    <body>

        <header>
            
        </header>
        <div id="menuPrincipal">
            <%@include file="../Menu/navbarGrupos.jsp"%><br><br>
        </div>
        <div class="Contenido_menu">
            <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
            <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">


            <div id="Contenedor_Bienvenido">
                
                <div id="MargenYEncabezado" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

                    <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                        <br><a class="titulo">INICIO</a><br>

                    </ul><br><br><br>
                    <fieldset id="fs_inicio">
                        <label id="etq_pre"> <h3>Periodo de registro de aspirantes

                            </h3> </label>
                        <br>
                        <label id="etq_mesPeriodo"><h3> 
                                ${fechas.getPeriodo()}</h3></label>
                    </fieldset>
                    <img  id="imagen_inicio" width="90%"
                          alt="Por favor elige una opción del menú" src="Imagenes/imagenInicio.png">
                </div>



            </div>
        </div>
    </body>
</html>
