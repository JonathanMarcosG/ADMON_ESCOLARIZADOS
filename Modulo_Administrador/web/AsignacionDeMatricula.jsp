<%-- 
    Document   : AsignacionDeMatricula
    Created on : 15/07/2014, 09:36:11 AM
    Author     : Rocio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script> 
        <title>Asignación de Matrícula</title>
    </head>
    <body>
        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <br><h3>Asignación de Matrícula</h3><br>

            </ul>
            <br><br>

            <div id="Caja_busqueda">
                <label id="etiqueta_preficha">Preficha :</label>
                <input type="text" id="caja_boton_busquedapreficha">
                <input type="button" id="boton_busqueda" class="botonsillos" value="Buscar">
            </div>
            <br>
            <div id="Pestanas_info" class="tabs">
                <ul class="tab-links">
                    <li><a href="#tabs-1">Datos de la ficha</a></li>
                    <li><a href="#tabs-2">Domicilio y Datos Socioeconómicos</a></li>
                    <li><a href="#tabs-3">En caso de emergencia</a></li>
                </ul>

                <div class="tab-content" id="contenedor_pestanas">

                    <div id="tabs-1" class="tab-active">
                        <div id="etiquetas_superior">
                            <fieldset id="solicitud">
                                <label id="noSolicitud" class="etiqueta_folios">N° de Solicitud:</label>
                                <input type="text" disabled="true" id="caja_noSolicitud" class="tamano_cajas_textoFolios">
                            </fieldset>

                            <fieldset id="et_periodo">
                                <label id="Periodo">Periodo:</label>
                                <label id="tiempo"> Agosto- Diciembre 2015</label>

                            </fieldset>
                            <fieldset id="Dficha">
                                <label id="datosFicha">DATOS DE LA FICHA</label>
                            </fieldset>
                            <fieldset id="cajaFecha">
                                <label id="ficha" class="etiqueta_folios">Ficha:</label>
                                <input type="text" disabled="true" id="caja_ficha" class="tamano_cajas_textoFolios">
                            </fieldset>

                            <form method="POST" action="" onsubmit="asignarMatricula();">
                                <fieldset id="folioCaja">
                                    <label id="folio" class="etiqueta_folios">Folio CENEVAL:</label>
                                    <input type="text" id="caja_folio" class="tamano_cajas_textoFolios">
                                </fieldset>
                                <fieldset  id="boton_asigFicha">
                                    <input type="button" id="botonAsignar" class="botonsillos" value="Asignar Ficha" onclick="asignarMatricula();">
                                </fieldset>
                            </form>
                        </div>

                        <div id="emergente">
                            <img  height="15%" width="12%" alt="Instituto Tecnológico de Toluca" src="Imagenes/cuidado.png">
                            <label id="titulo" >AVISO IMPORTANTE</label>

                            <fieldset id="datos_emergentes">
                                <label id="matricula" class="etq_emergente">La matrícula asignada: </label>
                                <br>
                                <label id="aspirante" class="etq_emergente">Nombre del aspirante: </label>

                                <label id="correo_asp" class="etq_emergente">Esta matrícula será enviada
                                    al correo: </label>
                            </fieldset>

                            <input type="submit" id="btn_aceptar" name="btn_aceptar" value="Aceptar" class="botonesEmergente"  onclick="aceptar()">
                            <input type="submit" id="btn_cancelarEmergente" name="btn_cancelarEmergent" value="Cancelar" class="botonesEmergente" onclick="aceptar()">
                        </div>
                        <div id="fondo"></div> 


                        <div id="contenedor_datosAlu">
                            <label id="etiqueta_datosExamen">
                                Lugar y Hora asignada para presentar el examen de selección.
                            </label>
                            <br>
                            <input type="text" id="caja_datosExamen">

                            <div id="contenedorDatosPersonales">
                                <label id="etq_dtsPersonales" class="etq_titulos">
                                    DATOS PERSONALES
                                </label>
                                <section id="dp_izquierdo">

                                    <fieldset id="fs_dpIzquierdo">
                                        <p>
                                            <label id="etq_curp" class="etqueta_dp" >CURP:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP"   class="tamano_cajas_texto">
                                            <br>

                                            <label id="etq_ap" class="etqueta_dp">Apellido Paterno: </label>
                                            <input type="text" name="cajaAP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_nacimiento" class="etqueta_dp">Fecha de Nacimiento:</label>
                                            <input type="text" disabled="true" name="cajaNACIMIENTO" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_sexo" class="etqueta_dp">Sexo: </label>
                                            <input type="text" disabled="true" name="cajaSexo" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_edoCivil" class="etqueta_dp">Estado Civil:</label>
                                            <input type="text"  disabled="true" name="cajaEdoCivil" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_cdNac" class="etqueta_dp">Ciudad de Nacimiento:</label>
                                            <input type="text" disabled="true" name="cajaCdNac" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_pnac" class="etqueta_dp">País de Nacimiento:</label>
                                            <input type="text" disabled="true" name="cajaPnac" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_op1" class="etqueta_dp">Opción 1:</label>
                                            <select  id="estado1" class="Selects_socioeconomicos" ></select>
                                            <br>
                                        </p>
                                    </fieldset>
                                </section>

                                <section id="central">

                                    <fieldset id="fs_dpCentral">
                                        <p>
                                            <label id="etq_am" hidden="true" class="etqueta_dp">Esta etiqueta no se ve</label>
                                            <input type="hidden" name="cajaOculta" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_am" class="etqueta_dp">Apellido Materno:</label>
                                            <input type="text" name="cajaCURP" id="cajaAM" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_en" class="etqueta_dp">Estado de Nacimiento:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_correo" class="etqueta_dp">Correo Electrónico:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_capdiferente" class="etqueta_dp">Capacidad Direfente:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_sangre" class="etqueta_dp">Tipo de Sangre:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_am" hidden="true" class="etqueta_dp">Esta etiqueta no se ve</label>
                                            <input type="hidden" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_op2" class="etqueta_dp">Opción 2:</label>
                                            <select  id="estado" class="Selects_socioeconomicos" ></select>
                                            <br>
                                        </p>
                                    </fieldset>
                                </section>
                                <section id="Derecha">
                                    <fieldset id="fs_dpDerecho">
                                        <p>
                                            <label id="etq_am" hidden="true" class="etqueta_dp">Esta etiqueta no se ve</label>
                                            <input type="hidden" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_nombre" class="etqueta_dp">Nombre(s):</label>
                                            <input type="text" name="cajaCURP" id="cajaNombre" class="tamano_cajas_texto">  
                                            <br>

                                            <label id="etq_op3" class="etqueta_dp">Opción 3:</label>
                                            <select  id="estado2" class="Selects_socioeconomicos" ></select>
                                            <br>

                                        </p>
                                    </fieldset>
                                </section>


                            </div>
                            <div id="contenedorEscuelaPro">

                                <label id="etq_escuelaProc" class="etq_titulos">
                                    DATOS DE LA ESCUELA DE PROCEDENCIA
                                </label>

                                <section id="dp_izquierdo">
                                    <fieldset id="fs_dpIzquierdo1">
                                        <p>
                                            <label id="etq_edo" class="etqueta_dp">Estado:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_municipio" class="etqueta_dp">Municipio:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_tipoEscuela" class="etqueta_dp">Tipo de Escuela:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_escuela" class="etqueta_dp">Escuela:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                        </p>
                                    </fieldset>
                                </section>
                                <section id="central">
                                    <fieldset id="fs_dpCentral1">
                                        <p>
                                            <label id="etq_clave" class="etqueta_dp">Clave de Escuela:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_pInicio" class="etqueta_dp">Periodo Inicio:</label>
                                            <input type="text" disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_pFin" class="etqueta_dp">Periodo Fin:</label>
                                            <input type="text"  disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                            <label id="etq_pBachillerato" class="etqueta_dp">Periodo del Bachillerato:</label>
                                            <input type="text"  disabled="true" name="cajaCURP" id="cajaCURP" class="tamano_cajas_texto">  
                                            <br>
                                        </p>
                                    </fieldset>
                                </section>
                                <section>
                                    <fieldset id="fs_dpDerecho1" title="Observaciones">
                                        <label id="etq_observaciones" class="etqueta_dp">Observaciones:</label>
                                        <textarea id="caja_observaciones">
                                            
                                        </textarea>
                                    </fieldset>
                                </section>
                            </div>  

                        </div>

                        <input type="button" id="boton_guardar" class="botonsillos" value="GUARDAR">
                    </div>
                    <div id="tabs-2" class="tab">
                        <div id="etiquetas_superior">
                            <fieldset id="solicitud">
                                <label id="noSolicitud" class="etiqueta_folios">N° de Solicitud:</label>
                                <input type="text" disabled="true" id="caja_noSolicitud" class="tamano_cajas_textoFolios">
                            </fieldset>

                            <fieldset id="et_periodo">
                                <label id="Periodo">Periodo:</label>
                                <label id="tiempo"> Agosto- Diciembre 2015</label>

                            </fieldset>

                            <fieldset id="Ddomicilio">
                                <label id="datosDomicilio">DATOS DEL DOMICILIO</label>
                            </fieldset>
                        </div>
                        <div id="contendorDomicilio">
                            <section id="DIzquierda">
                                <fieldset id="fs_DIzquierda">
                                    <p>
                                        <label id="etq_edo2" class="etqueta_dp">Estado:</label>
                                        <input type="text" disabled="true" name="estado" id="estado3" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_ciudad" class="etqueta_dp">Ciudad:</label>
                                        <input type="text" disabled="true" name="caja_cuidad" id="caja_ciudad" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_calleNo" class="etqueta_dp">Calle y número: </label>
                                        <input type="text" disabled="true" name="calleNo" id="calleNo" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_cdg" class="etqueta_dp">Estado:</label>
                                        <input type="text" disabled="true" name="codigoPostal" id="codigoPostal" class="tamano_cajas_texto">  
                                        <br> 

                                    </p>
                                </fieldset>
                            </section>

                            <section id="DDerecha">
                                <fieldset id="fs_DDerecha">
                                    <p>

                                        <label id="etq_lada1" class="etqueta_dp">LADA:</label>
                                        <input type="text" disabled="true" name="lada1" id="lada1" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_cel" class="etqueta_dp">Teléfono Celular:</label>
                                        <input type="text" disabled="true" name="caja_cel" id="caja_cel" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_lada2" class="etqueta_dp">LADA: </label>
                                        <input type="text" disabled="true" name="lada2" id="lada2" class="tamano_cajas_texto">  
                                        <br> 

                                        <label id="etq_telFijo" class="etqueta_dp">Teléfono Fijo:</label>
                                        <input type="text" disabled="true" name="telFijo" id="telFijo" class="tamano_cajas_texto">  
                                        <br> 

                                    </p>
                                </fieldset>
                            </section>

                        </div>
                        <fieldset id="Dse">
                            <label id="datosSocioE">DATOS SOCIOECONÓMICOS</label>
                        </fieldset>
                        <div id="contenedorDatosSE">
                            <section id="SEIzquierda">
                                <fieldset id="fs_SEIzquierda">
                                    <p>
                                        <label id="etq_procedencia" class="etqueta_dp1">Zona de Procedencia:</label>
                                        <input type="text" disabled="true" name="procedencia" id="procedencia" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_estudiosPadre" class="etqueta_dp1">Máximo nivel de estudios del padre:</label>
                                        <input type="text" disabled="true" name="estudiosPadre" id="estudiosPadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_estudiosMadre" class="etqueta_dp1">Máximo nivel de estudios de la madre:</label>
                                        <input type="text" disabled="true" name="estudiosMadre" id="estudiosMadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ingresos" class="etqueta_dp1">Ingresos familiares mensuales.</label>
                                        <label id="etq_cantidad" class="etqueta_dp1">Cantidad.</label>
                                        <br>
                                        <label id="etq_ingresosPadre" class="etqueta_dp1">Padre:</label>
                                        <input type="text" disabled="true" name="ingresosPadre" id="ingresosPadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ingresosMadre" class="etqueta_dp1">Madre:</label>
                                        <input type="text" disabled="true" name="ingresosMadre" id="ingresosMadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ingresosHermanos" class="etqueta_dp1">Hermanos:</label>
                                        <input type="text" disabled="true" name="ingresosHermanos" id="ingresosHermanos" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ingresosPropio" class="etqueta_dp1">Propio:</label>
                                        <input type="text" disabled="true" name="ingresoPpropio" id="ingresosPropio" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ingresosOtro" class="etqueta_dp1">Otros:</label>
                                        <input type="text" disabled="true" name="ingresosOtro" id="ingresosOtro" class="tamano_cajas_texto1">  
                                        <br>

                                    </p>
                                </fieldset>
                            </section>

                            <section id="SEDerecha">
                                <fieldset id="fs_SEDerecha">
                                    <p>
                                        <label id="etq_depEconomico" class="etqueta_dp1">Económicamente depende de:</label>
                                        <input type="text" disabled="true" name="depEconomico" id="depEconomico" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_casaVive" class="etqueta_dp1">La casa donde vive es:</label>
                                        <input type="text" disabled="true" name="casaVive" id="casaVive" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_habitantes" class="etqueta_dp1">No. de personas que viven en esa casa:</label>
                                        <input type="text" disabled="true" name="habitantes" id="habitantes" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ocupacionPadre" class="etqueta_dp1">Ocupación del padre:</label>
                                        <input type="text" disabled="true" name="ocupacionPadre" id="ocupacionPadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_ocupacionMadre" class="etqueta_dp1">Ocupación de la madre:</label>
                                        <input type="text" disabled="true" name="ocupacionMadre" id="ocupacionMadre" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_cuartosCasa" class="etqueta_dp1">No. de cuartos en la casa(no baños):</label>
                                        <input type="text" disabled="true" name="cuartosCasa" id="cuartosCasa" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_programaOportunidades" class="etqueta_dp1">Pertenece al programa oportunidades:</label>
                                        <input type="text" disabled="true" name="programaOportunidades" id="programaOportunidades" class="tamano_cajas_texto1">  
                                        <br>
                                        <label id="etq_viveCon" class="etqueta_dp1">Actualmente vive con:</label>
                                        <input type="text" disabled="true" name="viveCon" id="viveCon" class="tamano_cajas_texto1">  
                                        <br>

                                        <label id="etq_sustento" class="etqueta_dp1">Número de personas(incluyendo al aspirante) que 
                                            dependen del principal sustento:</label>
                                        <input type="text" disabled="true" name="sustento" id="sustento" class="tamano_cajas_texto1">  
                                        <br>
                                    </p>
                                </fieldset>
                            </section>
                        </div>
                    </div>
                    <div id="tabs-3" class="tab">                        
                        <div id="etiquetas_superior">
                            <fieldset id="solicitud">
                                <label id="noSolicitud" class="etiqueta_folios">N° de Solicitud:</label>
                                <input type="text" disabled="true" id="caja_noSolicitud" class="tamano_cajas_textoFolios">
                            </fieldset>

                            <fieldset id="et_periodo">
                                <label id="Periodo">Periodo:</label>
                                <label id="tiempo"> Agosto- Diciembre 2015</label>

                            </fieldset>
                        </div>
                        <fieldset id="CE">
                            <label id="datosCE">EN CASO DE EMERGENCIA</label>
                        </fieldset>
                        <div id="contenedorEmergencia">
                            <section id="EUnico">
                                <fieldset id="fs_unico">
                                    <p>
                                        <label id="etq_EContacto" class="etqueta_dp2">En emergencia llamar a(nombre y apellidos):</label>
                                        <input type="text" disabled="true" name="EContacto" id="EContacto" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ECiudad" class="etqueta_dp2">Ciudad:</label>
                                        <input type="text" disabled="true" name="ECiudad" id="ECiudad" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_EEstado" class="etqueta_dp2">Estado:</label>
                                        <input type="text" disabled="true" name="EEstado" id="EEstado" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_EColonia" class="etqueta_dp2">Colonia:</label>
                                        <input type="text" disabled="true" name="EColonia" id="EColonia" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ECalle" class="etqueta_dp2">Calle y No:</label>
                                        <input type="text" disabled="true" name="ECalle" id="ECalle" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ETelFijo" class="etqueta_dp2">Teléfono fijo(LADA incluida):</label>
                                        <input type="text" disabled="true" name="ETelFijo" id="ETelFijo" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ETelCel" class="etqueta_dp2">Teléfono celular(LADA incluida):</label>
                                        <input type="text" disabled="true" name="ETelCel" id="ETelCel" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ECTrabajo" class="etqueta_dp2">Centro de trabajo:</label>
                                        <input type="text" disabled="true" name="ECTrabajo" id="ECTrabajo" class="tamano_cajas_texto2">  
                                        <br>
                                        <label id="etq_ETelCT" class="etqueta_dp2">Teléfono del centro de trabajo:</label>
                                        <input type="text" disabled="true" name="ETelCT" id="ETelCT" class="tamano_cajas_texto2">  
                                        <br>
                                    </p>
                                </fieldset>
                            </section>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </body>
</html>