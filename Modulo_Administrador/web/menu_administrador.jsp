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
        <title>Menú Administrador</title>
    </head>

    <body>
        <header>
            <h1>Sistema gestor del registro de aspirantes</h1><br><br>
        </header>

        <div class="Contenido_menu">

            <div id="Menu_desplegable">
                <ul>
                    <li>
                        <h3>Inicio</h3>

                    </li>
                    <li>
                        <h3>Consultas</h3>
                        <ul>
                            <li>
                                <a onclick="Amatricula();">Asignación de Matrícula</a>
                            </li>
                            <li>
                                <a onclick="Salumnos();">Seguimiento de Alumnos</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <h3>Estadísticas</h3>
                        <ul>
                            <li>
                                <a onclick="Aregistrados();">Aspirantes Registrados</a>
                            </li>

                        </ul>
                    </li>
                    <li>
                        <h3 onclick="PDRAspirantes();">Periodo de registro</h3>

                    </li>

                    <li>
                        <h3>Ayuda</h3>
                        <ul>
                            <li>
                                <a href="#">Manual de la aplicación</a>
                            </li>

                        </ul>
                    </li>
                    <li>
                        <h3 href="#">Salir</h3>

                    </li>
                </ul>
                <br>
                <img width="80%" height="70%" alt="" src="Imagenes/computadora.jpg" title="imagen_computadora">
                <br>
                <br>
                <img width="80%" height="70%" alt="" src="Imagenes/entrada-tec2.jpg" title="imagen_entrada">
            <br>
            <br>
            </div>



            <div id="Contenedor_Bienvenido">

                <div id="MargenYEncabezado" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

                    <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                        <br><h3>INICIO</h3><br>

                    </ul><br><br><br>
                    <fieldset id="fs_inicio">
                    <label id="etq_pre"> <h3>Periodo de preinscripciones</h3> <br></label>
                    <label id="etq_mesPeriodo"><h3>Agosto-Diciembre 2015</h3></label>
                    </fieldset>
                    <img  id="imagen_inicio" width="90%"
                          alt="Por favor elige una opción del menú" src="Imagenes/imagenInicio.png">
                </div>
            </div>
        </div>
    </body>
</html>
