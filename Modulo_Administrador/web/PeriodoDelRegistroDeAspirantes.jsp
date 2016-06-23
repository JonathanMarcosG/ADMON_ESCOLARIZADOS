<%-- 
    Document   : PeriodoDelRegistroDeAspirantes
    Created on : 5/08/2014, 07:51:41 PM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Periodo del Registro de Aspirantes</title>
    </head>
    <body>
        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <br><h3>Periodo del Registro de Aspirantes</h3><br>

            </ul>
            <br>
            <div id="TipoDeBusqueda1">
                <label id="instrucciones">
                    Por favor indica el periodo de registro para los aspirantes.
                </label>
            </div>
            <div id="AsignarPeriodo_contenedor">
                <br>
                <div id="asignarPeriodo">
                    <section id="sobrePeriodo">
                        <fieldset id="fs_periodo">
                            <label id="fecha_inicio" class="etq_periodo">Fecha de inicio:</label>
                            <!--                            <input type="text" id="caja_inicio" class="tamano_cajas_texto5">-->
                            <label id="OpcionesInicio"  class="tamano_cajas_texto5">
                            <select name="SelectDiaInicio" id="SelectDiaInicio">
                                <option value="-">dia</option>
                                <option value="01"> 1 </option>
                                <option value="02"> 2 </option>
                                <option value="03"> 3 </option>
                                <option value="04"> 4 </option>
                                <option value="05"> 5 </option>
                                <option value="06"> 6 </option>
                                <option value="07"> 7 </option>
                                <option value="08"> 8 </option>
                                <option value="09"> 9 </option>
                                <option value="10"> 10 </option>
                                <option value="11"> 11 </option>
                                <option value="12"> 12 </option>
                                <option value="13"> 13 </option>
                                <option value="14"> 14 </option>
                                <option value="15"> 15 </option>
                                <option value="16"> 16 </option>
                                <option value="17"> 17 </option>
                                <option value="18"> 18 </option>
                                <option value="19"> 19 </option>
                                <option value="20"> 20 </option>
                                <option value="21"> 21 </option>
                                <option value="22"> 22 </option>
                                <option value="23"> 23 </option>
                                <option value="24"> 24 </option>
                                <option value="25"> 25 </option>
                                <option value="26"> 26 </option>
                                <option value="27"> 27 </option>
                                <option value="28"> 28 </option>
                                <option value="29"> 29 </option>
                                <option value="30"> 30 </option>
                                <option value="31"> 31 </option>
                            </select>
                            </label>
                             <label id="OpcionesMesInicio"  class="tamano_cajas_texto5">
                            <select name="SelectMesInicio" id="SelectMesInicio">
                                <option value="-">mes</option>
                                <option value="01">Ene</option>                          
                                <option value="02">Feb</option>                           
                                <option value="03">Mar</option>                           
                                <option value="04">Abr</option>                            
                                <option value="05">May</option>                            
                                <option value="06">Jun</option>                           
                                <option value="07">Jul</option>
                                <option value="08">Agos</option>
                                <option value="09">Sep</option>
                                <option value="10">Oct</option>
                                <option value="11">Nov</option>
                                <option value="12">Dic</option>
                            </select>
                            </label>
                            <br>
                            <label id="fecha_fin" class="etq_periodo">Fecha de fin:</label>
                           
                            <label id="OpcionesFin" class="tamano_cajas_texto5">
                            <select name="SelectDiaFin" id="SelectDiaFin">
                                <option value="-">dia</option>
                                <option value="01"> 1 </option>
                                <option value="02"> 2 </option>
                                <option value="03"> 3 </option>
                                <option value="04"> 4 </option>
                                <option value="05"> 5 </option>
                                <option value="06"> 6 </option>
                                <option value="07"> 7 </option>
                                <option value="08"> 8 </option>
                                <option value="09"> 9 </option>
                                <option value="10"> 10 </option>
                                <option value="11"> 11 </option>
                                <option value="12"> 12 </option>
                                <option value="13"> 13 </option>
                                <option value="14"> 14 </option>
                                <option value="15"> 15 </option>
                                <option value="16"> 16 </option>
                                <option value="17"> 17 </option>
                                <option value="18"> 18 </option>
                                <option value="19"> 19 </option>
                                <option value="20"> 20 </option>
                                <option value="21"> 21 </option>
                                <option value="22"> 22 </option>
                                <option value="23"> 23 </option>
                                <option value="24"> 24 </option>
                                <option value="25"> 25 </option>
                                <option value="26"> 26 </option>
                                <option value="27"> 27 </option>
                                <option value="28"> 28 </option>
                                <option value="29"> 29 </option>
                                <option value="30"> 30 </option>
                                <option value="31"> 31 </option>
                            </select>
                            </label>
                            <label id="OpcionesMesFin" class="tamano_cajas_texto5">
                            <select name="SelectMesFin" id="SelectMesFin">
                                <option value="-">mes</option>
                                <option value="01">Ene</option>                          
                                <option value="02">Feb</option>                           
                                <option value="03">Mar</option>                           
                                <option value="04">Abr</option>                            
                                <option value="05">May</option>                            
                                <option value="06">Jun</option>                           
                                <option value="07">Jul</option>
                                <option value="08">Agos</option>
                                <option value="09">Sep</option>
                                <option value="10">Oct</option>
                                <option value="11">Nov</option>
                                <option value="12">Dic</option>
                            </select>
                            </label>
                        </fieldset>
                    </section>
                </div>

                <input id="btn_confirmar" type="button" class="botonesPeriodo" value="Confirmar">

                <input id="btn_cancelar" type="button" class="botonesPeriodo" value="Cancelar">

                <br>

            </div>

        </div>
    </body>
</html>
