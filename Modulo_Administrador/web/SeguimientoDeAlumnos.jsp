<%-- 
    Document   : SeguimientoDeAlumnos
    Created on : 4/08/2014, 12:26:05 PM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script> 
        <title>Seguimiento de Alumnos</title>
    </head>
    <body>
        <div id="Seguimiento_contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
                <br><h3>Seguimiento de Alumnos</h3><br>
            </ul> 

            <div id="TipoDeBusqueda">
                <label id="instrucciones">
                    Elija una categoría de búsqueda.
                </label>
            </div>

            <div id="busqueda_contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

                <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
                    <input type="button" onclick="BuscarPorFicha();" id="busqueda_preficha" value="Preficha" class="botones_busqueda">
                    <input type="button" onclick="BuscarPorCURP();" id="busquda_curp" value="CURP" class="botones_busqueda">
                    <input type="button" onclick="BuscarPorNombre();" id="busqueda_nombre" value="Nombre completo">
                </ul> 


                <div id="DesplegarDatos">
                    <div id="ingresar">

                        <div id="porNombre">
                            <section>
                                <fieldset id="fs_busqueda">
                                    <label id="etq_nombre1" class="etqueta_dp3">Nombre(s):</label>
                                    <input type="text"  name="cajaNombre1" id="cajaNombre1" class="tamano_cajas_texto3">  
                                    <label id="etq_ap1" class="etqueta_dp3">Apellido Paterno:</label>
                                    <input type="text"  name="cajaap1" id="cajaap1" class="tamano_cajas_texto3">  
                                    <label id="etq_am1" class="etqueta_dp3">Apellido Materno:</label>
                                    <input type="text"  name="cajaam1" id="cajaam1" class="tamano_cajas_texto3">  

                                    <br>
                                    <input type="submit" id="boton_busqueda1" value="Buscar">
                                </fieldset>
                            </section>    
                        </div>

                        <div id="porFicha">
                            <section>
                                <fieldset id="fs_busquedaFicha">
                                    <label id="etq_porFicha" class="etqueta_dp3">No. Ficha:</label>
                                    <input type="text"  name="cajaporFicha" id="cajaporFicha" class="tamano_cajas_texto4">  
                                    <br>
                                    <input type="submit" id="boton_busqueda2" value="Buscar">
                                </fieldset>
                            </section>    
                        </div>

                        <div id="porCURP">
                            <section>
                                <fieldset id="fs_busquedaCURP">
                                    <label id="etq_porCURP" class="etqueta_dp3">Ingrese CURP:</label>
                                    <input type="text"  name="cajaporCURP" id="cajaporCURP" class="tamano_cajas_texto4">  
                                    <br>
                                    <input type="submit" id="boton_busqueda3" value="Buscar">
                                </fieldset>
                            </section>    
                        </div>


                    </div>

                    <div id="contenedor_consulta">
                        <br>
                        <div id="ver">
                            <section>
                                <fieldset id="fs_busqueda1">
                                    <label id="etq_nombre1" class="etqueta_dp3">Prbando1):</label>

                                    <label id="etq_ap1" class="etqueta_dp3">Probando2:</label>

                                    <label id="etq_am1" class="etqueta_dp3">123:</label>
                                </fieldset>
                            </section> 
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
