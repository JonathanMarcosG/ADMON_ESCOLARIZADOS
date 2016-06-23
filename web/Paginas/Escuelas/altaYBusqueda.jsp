<%-- 
    Document   : altaYBusqueda
    Created on : 3/06/2015, 11:04:43 AM
    Author     : Rocio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
        <script src="JQuery/busquedaEscuelas.js" type="text/javascript"></script>
        <script src="JQuery/table_date.js" type="text/javascript"></script>


        <title>Catálogo Escuelas</title>
    </head>
    <body>
        <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
        <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">

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

        <div id="Esc_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">


                <!--<br><h3>Catálogo de escuelas</h3><br>-->
                <br><a class="titulo">Catálogo de escuelas</a><br>
            </ul>
            <br>
            <br>


            <div id="Pestanas_infoEscuelas" class="tabs">
                <ul class="tab-links">
                    <li><a href="#tabs-1">Búsqueda</a></li>
                    <li><a href="#tabs-2">Alta</a></li>
                </ul>                
                <div class="tab-content" id="contPestanasEscuelas">
                    <div id="tabs-1" class="tab-active">
                        <div id="divElEdo">
                            <div id='margendeEdos'>
                                <label id="descModulo">
                                    En esta sección se muestran los datos de las escuelas
                                    disponibles en el catálogo.
                                </label>
                                <br><br>
                                <label id="introEscuelas">Por favor, elija un estado para iniciar la búsqueda.</label>
                                <br>
                            </div>
                            <br><br>
                            <select id="selectEdos">
                                <option id="0" value="---Seleccione---">---Seleccione---</option>
                            </select>
                            <div id="barrita_de_fondoEscuelas">
                                <div class="spinner-wave">
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                    <div></div>
                                </div>

                            </div>
                        </div>
                        <div id="content">
                            <div>

                                <div id="SelecionaClave" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all" style="display: none">
                                    <div id="contenidoCCT">
                                        
                                                
                                        
                                        <div id="listaCCT">
                                            
                                            <table  id="tablaCCT"    
                                                    class="display nowrap dataTable dtr-inline" 
                                                    width="80%" cellspacing="0" 
                                                    role="grid" 
                                                    aria-describedby="example_info" 
                                                    style="width: 80%;">
                                                <thead>
                                              
                                                <th >Clave</th>
                                                <th>Nombre de la Escuela</th>
                                                <th>Turno</th>
                                                <th>Domicilio</th>

                                                </thead>
                                            </table>
                                        </div>
                                        <input id="cancelarCCT" type="button" value="Salir">
                                        <br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="tabs-2" class="tab">
                        <div id='margenAltaEscuelas'>
                            <label id='notaIntroductoria'>
                                En esta sección se pueden dar de alta nuevas escuelas, recuerde que
                                es su responsabilidad verificar que los datos que se ingresan sean verídicos.
                     
                            </label>
                            <label id="nuevaEscuela">Por favor, proporcione la información que se le solicita.</label>
                        </div>
                        <br><br> 
                        <fieldset id="ladoIzqEscuelas" class="general tamFS">
                            <section id="etqIzqEsc" class="general">
                                <label id="etqCCt" class="margen">Clave CCT: </label><br><br>
                                <label id="etqControl" class="margenEts">Control: </label><br><br>
                                <label id="etqServ" class="margenEts">Servicio: </label><br><br>
                                <label id="etqAmb" class="margenEts">Ámbito: </label><br>
                            </section>
                            <section id="cajasIzqEsc" class="general tamFS">
                                <input type="text" id="cajaCCT"><br><br>
                                <select id="selectControl" class="margen"></select><br><br>
                                <select id="selectServicio" class="margen"></select><br><br>
                                <select id="selectAmb" class="margen"></select><br>
                            </section>
                        </fieldset>
                        <fieldset id="ladoDerEscuelas" class="general tamFS">
                            <section id="etqDerEsc" class="general">
                                <label id="etqTurno">Turno: </label><br><br>
                                <label id="etqEnt" class="margenEts">Estado: </label><br><br>                               
                                <label id="etqMun" class="margenEts">Municipio: </label><br><br>
                                <label id="etqLocal" class="margenEts">Localidad: </label><br>
                            </section>
                            <section id="cajasDerEsc" class="general tamFS">
                                <select id="selectTurno"></select><br><br>
                                <select id="selectEnt" class="margen">
                                    <option id="0" value="---Seleccione---">---Seleccione---</option>
                                </select><br><br>
                                <select disabled="true" id="selectMun"class="margen">
                                    <option id="0" value="---Seleccione---">---Seleccione---</option>
                                </select><br><br>
                                <select disabled="true" id="selectLocalidad" class="margen">
                                    <option id="0" value="---Seleccione---">---Seleccione---</option>
                                </select><br>
                            </section>
                        </fieldset>             
                        <fielset id="nombreYDomNvaEsc">
                            <section class="general">
                                <label id="etqNvaEsc" >Nombre: </label><br><br>
                                <label id="etqDomEsc">Domicilio: </label>
                            </section>
                            <section class="general" id="setInputNomDom">
                                <input type="text" id="nomNvaEsc"><br><br>
                                <input type="text" id="domNvaEsc">
                            </section>
                        </fielset>
                        <br><br>
                        <input type="button" value="Guardar" id="btnNvaEscuela" class="btn_style2">
                        <input type="button" value="Cancelar" id="btnCancelEscuela" class="btn_style2">
                        <label id="resultadoNvaEscuela"></label>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
