<%-- 
    Document   : AspirantesRegistados
    Created on : 5/08/2014, 05:48:10 PM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script> 
        <title>Aspirantes Registrados</title>
    </head>
    <body>
        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <br><h3>Estadísticas</h3><br>

            </ul>
            <br>

            <div id="graficas_contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

                <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
                    <input type="button" onclick="totales();" id="graficas_totales" value="Totales">
                    <input type="button"  onclick="pagoBancario();" id="graficas_pagado" value="Realizaron pago" class="botones_busqueda">
                    <input type="button"  onclick="enCENEVAL();" id="graficas_ceneval" value="Registrados en CENEVAL" class="botones_busqueda">
                </ul> 
            </div>

            <div id="contenedor_graficas">
                <br>
                <div id="ver1">
                    <section>
                        <fieldset id="fs_totales">
                            <label id="asp_totales" class="etq_graficas">Aspirantes que registraron su solicitud: </label>
                            <label id="no.asp_totales" class="etq_graficas1">1234</label>

                            <div class="graph">

                                  <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroIndustrial">
                                        536
                                    </label><br>
                                    <div style="height: 8%" class="bar">
                                        <label id="porcentajeIndustrial" class="porcentajes">8%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">   
                                    <label id="numeroLogistica">
                                        536
                                    </label>
                                    <div style="height: 60%;" class="bar">
                                        <label class="porcentajes">60%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroGestion">
                                        458
                                    </label>
                                    <div style="height: 30%;" class="bar">
                                        <label class="porcentajes">30%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroMecatronica">
                                        874
                                    </label>
                                    <div style="height: 49%;" class="bar">
                                        <label class="porcentajes">49%</label>
                                    </div>    
                                </div>

                                <div style="height: 100%" class="barEtiqueta">    
                                    <label id="numeroElectromecanica">
                                        5200
                                    </label>
                                    <div style="height: 28%;" class="bar">
                                        <label class="porcentajes">28%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroElectronica">
                                        740
                                    </label>
                                    <div style="height: 22%;" class="bar">
                                        <label class="porcentajes">22%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroQuimica">
                                        100
                                    </label>
                                    <div style="height: 11%;" class="bar">
                                        <label class="porcentajes">11%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroSistemas">
                                        40000
                                    </label>
                                    <div style="height: 6%;" class="bar">
                                        <label class="porcentajes">6%</label>
                                    </div>
                                </div>
                                <div style="height: 100%;" class="barInvisible">

                                </div> <br>
                                <div style="width: 100%;" class="plano"></div>
                            </div>
                            <fieldset id="fs_carreras">
                                <label class="primera">Industrial</label>
                                <label class="carreras">Logística</label>
                                <label class="carreras">Gestión</label>
                                <label class="paraNombreLargo">Mecatrónica</label>
                                <label class="paraNombreLargo">Electromecánica</label>
                                <label class="paraNombreLargo">Electrónica</label>
                                <label class="final">Química</label>
                                <label class="final">Sistemas</label>
                            </fieldset>
                        </fieldset>


                        <fieldset id="fs_pago">
                            <label id="asp_totales" class="etq_graficas">Aspirantes que ya realizaron el pago bancario: </label>
                            <label id="no.asp_totales" class="etq_graficas1">5678</label>

                            <div class="graph">
  <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroIndustrial">
                                        536
                                    </label><br>
                                    <div style="height: 8%" class="bar">
                                        <label id="porcentajeIndustrial" class="porcentajes">8%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">   
                                    <label id="numeroLogistica">
                                        536
                                    </label>
                                    <div style="height: 60%;" class="bar">
                                        <label class="porcentajes">60%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroGestion">
                                        458
                                    </label>
                                    <div style="height: 30%;" class="bar">
                                        <label class="porcentajes">30%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroMecatronica">
                                        874
                                    </label>
                                    <div style="height: 49%;" class="bar">
                                        <label class="porcentajes">49%</label>
                                    </div>    
                                </div>

                                <div style="height: 100%" class="barEtiqueta">    
                                    <label id="numeroElectromecanica">
                                        5200
                                    </label>
                                    <div style="height: 28%;" class="bar">
                                        <label class="porcentajes">28%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroElectronica">
                                        740
                                    </label>
                                    <div style="height: 22%;" class="bar">
                                        <label class="porcentajes">22%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroQuimica">
                                        100
                                    </label>
                                    <div style="height: 11%;" class="bar">
                                        <label class="porcentajes">11%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroSistemas">
                                        40000
                                    </label>
                                    <div style="height: 6%;" class="bar">
                                        <label class="porcentajes">6%</label>
                                    </div>
                                </div>

                                <div style="height: 100%;" class="barInvisible">

                                </div>
                                <br>
                                <div style="width: 100%;" class="plano"></div>
                            </div>
                            <fieldset id="fs_carreras">
                                <label class="primera">Industrial</label>
                                <label class="carreras">Logística</label>
                                <label class="carreras">Gestión</label>
                                <label class="paraNombreLargo">Mecatrónica</label>
                                <label class="paraNombreLargo">Electromecánica</label>
                                <label class="paraNombreLargo">Electrónica</label>
                                <label class="final">Química</label>
                                <label class="final">Sistemas</label>
                            </fieldset>
                        </fieldset>
                        <fieldset id="fs_CENEVAL">
                            <label id="asp_totales" class="etq_graficas">Aspirantes que se han registrado en CENEVAL: </label>
                            <label id="no.asp_totales" class="etq_graficas1">91011</label>

                            <div class="graph">
                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroIndustrial">
                                        536
                                    </label><br>
                                    <div style="height: 8%" class="bar">
                                        <label id="porcentajeIndustrial" class="porcentajes">8%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">   
                                    <label id="numeroLogistica">
                                        536
                                    </label>
                                    <div style="height: 60%;" class="bar">
                                        <label class="porcentajes">60%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroGestion">
                                        458
                                    </label>
                                    <div style="height: 30%;" class="bar">
                                        <label class="porcentajes">30%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroMecatronica">
                                        874
                                    </label>
                                    <div style="height: 49%;" class="bar">
                                        <label class="porcentajes">49%</label>
                                    </div>    
                                </div>

                                <div style="height: 100%" class="barEtiqueta">    
                                    <label id="numeroElectromecanica">
                                        5200
                                    </label>
                                    <div style="height: 28%;" class="bar">
                                        <label class="porcentajes">28%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroElectronica">
                                        740
                                    </label>
                                    <div style="height: 22%;" class="bar">
                                        <label class="porcentajes">22%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroQuimica">
                                        100
                                    </label>
                                    <div style="height: 11%;" class="bar">
                                        <label class="porcentajes">11%</label>
                                    </div>
                                </div>

                                <div style="height: 100%" class="barEtiqueta">
                                    <label id="numeroSistemas">
                                        40000
                                    </label>
                                    <div style="height: 6%;" class="bar">
                                        <label class="porcentajes">6%</label>
                                    </div>
                                </div>
                                <div style="height: 100%;" class="barInvisible">

                                </div>
                                <div style="height: 100%;" class="barInvisible"></div>
                                <br>
                                <div style="width: 100%;" class="plano"></div>
                            </div>
                            <fieldset id="fs_carreras">
                                <label class="primera">Industrial</label>
                                <label class="carreras">Logística</label>
                                <label class="carreras">Gestión</label>
                                <label class="paraNombreLargo">Mecatrónica</label>
                                <label class="paraNombreLargo">Electromecánica</label>
                                <label class="paraNombreLargo">Electrónica</label>
                                <label class="final">Química</label>
                                <label class="final">Sistemas</label>
                            </fieldset>
                        </fieldset>


                    </section> 
                </div>
                <br>
            </div>

        </div>
    </body>
</html>
