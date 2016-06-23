<%-- 
    Document   : SeguimientoDeAlumnos
    Created on : 4/08/2014, 12:26:05 PM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script> 
        <title>Seguimiento de Aspirantes</title>
    </head>
    <body>
        <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
        <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">

        <div id="Seguimiento_contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">


            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
                <!--<br><h3>Seguimiento de Aspirantes</h3><br>-->
                <br><a class="titulo">Seguimiento de Aspirantes</a><br>
            </ul> 

            <div id="TipoDeBusqueda">
                <label id="instrucciones">
                    Elija una categoría de búsqueda.
                </label>
            </div>

            <div id="busqueda_contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

                <ul class=" ui-tabs-nav ui-widget-header2 ui-corner-all">
                    <input type="button" id="busqueda_preficha" value="Preficha" class="btn_busqueda">
                    <input type="button" id="busquda_curp" value="CURP" class="btn_busqueda">
                    <input type="button" id="busqueda_nombre" class="btn_busqueda" value="Correo Electrónico">
                    <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
                    <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">

                </ul> 


                <div id="DesplegarDatos">
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
                    <div id="ingresar">

                        <div id="porNombre">
                            <section>
                                <fieldset id="fs_busqueda">

                                    <label id="etq_nombre1" class="etqueta_dp3">Correo Electrónico:</label>
                                    <input type="text"  name="cajaNombre1" id="cajaCorreoElectronicoBusqueda" class="tamano_cajas_texto4" maxlength="150">  



                                    <input type="button" id="boton_busqueda2" class="btn_style2" value="Buscar">

                                </fieldset>
                            </section>    
                        </div>

                        <div id="porFicha">
                            <section>
                                <fieldset id="fs_busquedaFicha">

                                    <label id="etq_porFicha" class="etqueta_dp3">Preficha:</label>
                                    <input type="text"  name="cajaporFicha" id="cajaporFicha" class="tamano_cajas_texto4" maxlength="4">  

                                    <input type="button" id="boton_busqueda3" class="btn_style2" value="Buscar" >

                                </fieldset>
                            </section>    
                        </div>

                        <div id="porCURP">
                            <section>
                                <fieldset id="fs_busquedaCURP">

                                    <label id="etq_porCURP" class="etqueta_dp3">Ingrese CURP:</label>
                                    <input type="text"  name="cajaporCURP" id="cajaporCURP" class="tamano_cajas_texto4"  maxlength="18">  

                                    <input type="button" id="boton_busqueda1" class="btn_style2"  value="Buscar">

                                </fieldset>
                            </section>    
                        </div>
                    </div>




                    <%@include file="../cargarDiv.jsp"%>
                    <label id="error"></label>
                    <div id="contenedor_consulta">


                        <br>
                        <div id="ver">

                            <label id="dp_busqueda">DATOS DEL ASPIRANTE</label>

                            <div id="margenVer">
                                <section id="sectionBusquedaDPerecho">
                                    <fieldset id="fs_busquedaDPerecho">
                                        <label id="etq_apB"  class="etqueta_dpPagoSeg">Nombre:</label>
                                        <input  name="caja_apB" readonly="readonly" id="caja_apB" value="${seguimiento.getNombre()}" class="tamano_cajas_textoBusquedaSeg">  
                                        <br>

                                        <label id="etq_carreraB" class="etqueta_dpPagoSeg">Referencia:</label>
                                        <input name="caja_carreraB" readonly="readonly" id="caja_carreraB" value="${seguimiento.getReferencia()}"  class="tamano_cajas_textoBusquedaSeg">  
                                        <br>
                                        <label id="etq_curpB" class="etqueta_dpPagoSeg">Curp:</label>
                                        <input name="caja_curpB" readonly="readonly" id="caja_curpB" value="${seguimiento.getCurp()}"  class="tamano_cajas_textoBusquedaSeg">  
                                        <br >
                                    </fieldset>

                                    <fieldset id="fs_busquedaDPIzquierdo11">
                                        <label id="etq_correoB"  class="etqueta_dpPagoSeg">Correo:</label>
                                        <input name="caja_correoB" readonly="readonly" id="caja_correoB" value="${seguimiento.getCorreo()}"  class="tamano_cajas_textoBusquedaSeg">  
                                        <br>

                                        <label id="ficha" class="etqueta_dpPagoSeg">Preficha:</label>
                                        <input id="caja_ficha" readonly="readonly" class="tamano_cajas_textoBusquedaSeg" value="${seguimiento.getPreficha()}" type="text" disabled="true">
                                    </fieldset>
                                </section>                                
                            </div>
                            <div id="pasos">
                                <section id="sctionPAsos">
                                    <label id="prefichaGenerada" class="pasosEtq">Preficha</label>
                                    <img id="checkPreficha"  src="Imagenes/Bien.png" width="10%">
                                    <img id="noPreficha"  src="Imagenes/Mal.png" width="10%">

                                    <br><br>
                                    <label id="ClaveCeneval" class="pasosEtq">Folio Ceneval</label>
                                    <img id="checkFolio"  src="Imagenes/Bien.png" width="10%">
                                    <img id="noFolio"  src="Imagenes/Mal.png" width="10%">

                                </section>
                                <section id="sctionPasosCentral">
                                    <label id="pagoProcesado" class="pasosEtq">Pago Procesado</label>
                                    <img id="checkPago"  src="Imagenes/Bien.png" width="10%">
                                    <img id="noPago"  src="Imagenes/Mal.png" width="10%">

                                    <br><br>
                                    <label id="fichaEntregada" class="pasosEtq">Ficha</label>
                                    <img id="checkFicha"  src="Imagenes/Bien.png" width="10%">
                                    <img id="noFicha"  src="Imagenes/Mal.png" width="10%">

                                </section>
                                <section id="sctionPAsosDer">
                                    <label id="registroCeneval" class="pasosEtq">Registro Ceneval</label>
                                    <img id="checkRegistro"  src="Imagenes/Bien.png" width="10%">
                                    <img id="noRegistro"  src="Imagenes/Mal.png" width="10%">

                                </section>
                                <br><br>
                                <section id="move">
                                    <label id="ultimoMove" >Fecha del último movimiento: </label>
                                    <input type="text" id="ultimoMove_caja" class="moveEtq" readonly="readonly" value="${seguimiento.getFechaModif()}">
                                    <br><br>
                                    <label id="ultimoMoveUsu">Usuario que realizó último movimiento: </label>
                                    <input type="text" id="ultimoMove_caja" class="moveEtq" readonly="readonly" value="${seguimiento.getUsuarioModif()}">

                                </section>

                            </div>

                        </div>       
                        <br><br>
                    </div>
                    <br>
                </div>
            </div>
        </div>
        <br>


    </body>
</html>
