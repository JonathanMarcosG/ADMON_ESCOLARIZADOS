<%-- 
    Document   : Pestana1
    Created on : 20/01/2015, 02:54:33 PM
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
        <script src="JQuery/js_pdficha.js" type="text/javascript"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
        <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
        <input type="hidden" id="idAspirante1" name="idAspirante" value="${datosPersonales.getIdAsp()}">

        <div id="tabs-1" class="tab-active">

            <div id="etiquetas_superior">
                <label id="paraErrorDeConcidencias"></label>
                <label id="paraError"></label>


                <section id="ParaAlinearMatricula" >   




                    <fieldset id="folioCaja">
                        <label id="folio" class="etiqueta_foliosNvaEtqCen">Folio CENEVAL:</label>
                        <input type="text" maxlength ="20" id="caja_folio" class="tamano_cajas_textoNvoCeneval" value="${folioCeneval.getFolioCENEVAL()}" maxlength=20" required/>
                        <section id="confirmFolio"></section>
                    </fieldset>
                    <fieldset id ="sobresFolio">
                        <img id="imgRojaImprimir" width="75%" alt="No hay folio CENEVAL" class="masterTooltip" title="No hay folio CENEVAL." src="Imagenes/sobreRojo.png">
                        <img id="ImgGuardarFolio" width="75%" alt="Guardar"  class="masterTooltip" title="Guardar Folio." src="Imagenes/imgGuardar.png">

                        <button id="botonVerde"  class="masterTooltip" title="Imprimir solicitud." value="">
                            <img id="imgVerdeImprimir" width="90%" src="Imagenes/icon-mail.png">
                        </button>

                    </fieldset>

                    <br>
                </section>
                <br>

            </div>
            <section id="SectionReferencia">
                <fieldset id="solicitud">
                    <label id="noSolicitud" class="etiqueta_Nsolicitud">N° de Solicitud:</label>
                    <input type="text" readonly="readonly" id="caja_noSolicitud" class="campo_bloqueado tamano_cajas_textoNSolicitud" value="${datosPersonales.getPreficha()}">
                </fieldset>

                <fieldset id="cajaFecha">
                    <label id="ficha" class="etiqueta_FichaConsultar">Ficha:</label>
                    <input type="text" readonly="readonly" id="caja_ficha" value="${datosPersonales.getMatricula()}" class="tamano_cajas_textoFichaNva campo_bloqueado">
                    <label id="confirm"></label>
                </fieldset>
                <br>
                <fieldset id="fs_referencia">
                    <label id="etq_referencia">Referencia:</label>
                    <input value="${datosPersonales.getReferencia()}" type="text" class="campo_bloqueado" readonly="readonly" id="caja_referencia">
                    <br>
                    <label id="etiqueta_datosExamen">
                        Lugar y Hora asignada para presentar examen CENEVAL:
                    </label>
                    <input maxlength ="150" readonly="readonly" value="${datosPersonales.getLugarFechaA()}" type="text" id="caja_datosExamen">
                    <br><br><br>
                    <label id="etiqueta_datosExamenMate">
                        Lugar y Hora asignada para presentar examen de matemáticas: 
                    </label>
                    <input type="text" readonly="readonly" maxlength ="150" value="${datosPersonales.getLugarFechaB()}" id="caja_datosExamenMate">


                </fieldset>
            </section>
            <br>
            <div id="contenedor_datosAlu">

                <div id="contenedorDatosPersonales">
                    <label id="etq_dtsPersonales" class="etq_titulos">
                        DATOS PERSONALES
                    </label>
                    <br><br>
                    <section id="dp_izquierdo">

                        <fieldset id="fs_dpIzquierdo">
                            <p>

                                <label id="etq_curp" class="etqueta_dp" >CURP:</label>
                                <input type="text" readonly="readonly" name="cajaCURP" id="cajaCURP" value="${datosPersonales.getCurp()}"  class="tamano_cajas_texto campo_bloqueado">
                                <br>
                                <label id="etq_ap" class="etqueta_dp">Apellido Paterno: </label>
                                <input type="text" maxlength ="30" name="cajaAP" id="cajaAP" value="${datosPersonales.getApellido_pat()}" class="tamano_cajas_texto" maxlength="30" onblur=" return CamposDeNombre('cajaAP', 'etq_ap')">  
                                <br>

                                <label id="etq_nacimiento" class="etqueta_dp">Fecha de Nacimiento:</label>
                                <input type="text" readonly="readonly" name="cajaNACIMIENTO" id="cajaNacimiento" value="${datosPersonales.getFec_nacimiento()}" class="tamano_cajas_texto campo_bloqueado">  
                                <br>

                                <label id="etq_municipio" class="etqueta_dp">Municipio de Nacimiento:</label>
                                <select type="text" name="cajaCURP" value="${datosPersonales.getMunNac()}" id="cajaMunicipio" class="tamano_cajas_texto">  
                                    <c:if test="${municipio.isEmpty()}">
                                        <option >
                                            <c:out value="Extranjero"/>
                                        </option>
                                    </c:if>
                                    <c:forEach  var="carreras" items="${municipio}" begin="0" end="${municipio.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_cdNac" class="etqueta_dp">Ciudad de Nacimiento:</label>
                                <select type="text"  name="cajaCdNac" id="cajaCn" value="${datosPersonales.getLocalidad()}" class="tamano_cajas_texto">
                                    <c:if test="${localidad.isEmpty()}">
                                        <option >
                                            <c:out value="Extranjero"/>
                                        </option>
                                    </c:if>
                                    <c:forEach  var="carreras" items="${localidad}" begin="0" end="${localidad.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>
                            </p>
                        </fieldset>
                    </section>

                    <section id="central">          

                        <fieldset id="fs_dpCentral">
                            <p>
                                <label id="etq_correo" class="etqueta_dp">Correo Electrónico:</label>
                                <input type="text" readonly="readonly"  name="cajaCURP" id="cajaCORREO" value="${datosPersonales.getCorreo()}" class="tamano_cajas_texto campo_bloqueado">  
                                <br>

                                <label id="etq_am" class="etqueta_dp">Apellido Materno:</label>
                                <input type="text" maxlength =30" name="cajaCURP" id="cajaAM" value="${datosPersonales.getApellido_mat()}" class="tamano_cajas_texto"  maxlength="30" onblur="CamposDeNombre('cajaAM', 'etq_am')">  
                                <br>

                                <label id="etq_pnac" class="etqueta_dp">País de Nacimiento:</label>
                                <select type="text" name="cajaPnac" id="cajaPais" value="${datosPersonales.getNacionalidad()}"  class="tamano_cajas_texto">
                                    <c:forEach  var="carreras" items="${nacionalidad}" begin="0" end="${nacionalidad.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_en" class="etqueta_dp">Estado de Nacimiento:</label>
                                <select type="text" name="cajaCURP" value="${datosPersonales.getEdoNacimiento()}" id="cajaEdo" class="tamano_cajas_texto">
                                    <c:if test="${estado.isEmpty()}">
                                        <option >
                                            <c:out value="Extranjero"/>
                                        </option>
                                    </c:if>
                                    <c:forEach  var="carreras" items="${estado}" begin="0" end="${estado.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_capdiferente" class="etqueta_dp">Capacidad Direfente:</label>
                                <select type="text"  name="cajaCURP" value="${datosPersonales.getCap_diferente()}" id="cajaCapDif" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${capacidad}" begin="0" end="${capacidad.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>
                            </p>
                        </fieldset>
                    </section>
                    <section id="Derecha">
                        <fieldset id="fs_dpDerecho">
                            <p>


                                <label id="etq_nombre" class="etqueta_dpNuevo">Nombre(s):</label>
                                <input type="text" maxlength ="30" name="cajaCURP" id="cajaNombre" value="${datosPersonales.getNombre()}" class="tamano_cajas_textoNuevo"  maxlength="30" onblur="CamposDeNombre('cajaNombre', 'etq_nombre')">  
                                <br>

                                <label id="etq_edad" class="etqueta_dpNuevo">Edad:</label>
                                <input type="text" readonly="readonly" name="cajaCURP" id="cajaEdad" value="${datosPersonales.getEdad()}" class="tamano_cajas_textoNuevo campo_bloqueado"  maxlength="30">  
                                <br>     <label id="etq_sexo" class="etqueta_dpNuevo">Sexo: </label>
                                <select type="text"  name="cajaSexo" value="${datosPersonales.getSexo()}" id="cajaSexo" class="tamano_cajas_textoNuevo">  
                                    <c:forEach  var="carreras" items="${sexo}" begin="0" end="${sexo.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_edoCivil" class="etqueta_dpNuevo">Estado Civil:</label>
                                <select type="text"  name="cajaEdoCivil" id="cajaCivil" value="${datosPersonales.getEdo_civil()}" class="tamano_cajas_textoNuevo">  
                                    <c:forEach  var="carreras" items="${civil}" begin="0" end="${civil.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_sangre" class="etqueta_dpNuevo">Tipo de Sangre:</label>
                                <select type="text" name="cajaCURP" id="cajaSangre" value="${datosPersonales.getTipo_sangre()}" class="tamano_cajas_textoNuevo">  
                                    <c:forEach  var="carreras" items="${sangre}" begin="0" end="${sangre.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>
                            </p>
                        </fieldset>
                    </section>
                    <br><br><br><br><br><br><br>
                    <br>
                    <section id="carreras">
                        <fieldset id="carrera1">
                            <label id="etq_op1" class="etqueta_opsCarreras">Carrera:</label>

                            <select  type="text" disabled ="disabled" name="estado3" id="estado3" class="Selects_socioeconomicos" >
                                <c:forEach  var="carreras" items="${opcion1}" begin="0" end="${opcion1.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>

                        </fieldset>

                    </section>

                </div>
                <br><br>
                <div id="contenedorEscuelaPro">

                    <label id="etq_escuelaProc" class="etq_titulos">
                        DATOS DE LA ESCUELA DE PROCEDENCIA
                    </label>
                    <br><br>
                    <section id="dp_izquierdoSection">
                        <fieldset id="fs_dpIzquierdo1">
                            <p>
                                <label id="etq_edo" class="etqueta_dp">Estado:</label>
                                <input type="text" readonly="readonly" name="cajaCURP"  id="cajaEstadoEP" value="${escuelaProcedencia.getNomEstado()}" class="tamano_cajas_texto campo_bloqueado">  

                                <br>

                                <label id="etq_municipio1" class="etqueta_dp">Municipio:</label>    
                                <br>
                                <input type="text" readonly="readonly" name="cajaCURP"  id="cajaMpioEP" value="${escuelaProcedencia.getNomMpio()}" class="tamano_cajas_texto campo_bloqueado">  
                                <br>

                                <label id="etq_tipoEscuela" class="etqueta_dp">Tipo de Escuela:</label>
                                <select type="text" name="cajaCURP"  id="cajaTipoEscuela"  class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${tEscuela}" begin="0" end="${tEscuela.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>
                                <label id="etq_escuela" class="etqueta_dp">Escuela:</label>
                                <select type="text"  name="cajaCURP" id="cajaEscuela" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${escuela}" begin="0" end="${escuela.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </p>
                        </fieldset>
                    </section>
                    <section id="centralSection">
                        <fieldset id="fs_dpCentral1">
                            <p>
                                <label id="etq_clave" class="etqueta_dp">Clave de Escuela:</label>
                                <input type="text" readonly="readonly" name="cajaCURP" value="${escuelaProcedencia.getClaveEscuela()}" id="cajaClaveEscuela" class="tamano_cajas_texto campo_bloqueado">  
                                <br>
                                <label id="etq_pInicio" class="etqueta_dp">Periodo Inicio:</label>
                                <input type="text" readonly="readonly" name="cajaCURP" value="${escuelaProcedencia.getFechaFin()}" id="cajaPInicio" class="tamano_cajas_texto campo_bloqueado">  
                                <br>
                                <label id="etq_pFin" class="etqueta_dp">Periodo Fin:</label>
                                <input type="text" readonly="readonly" name="cajaCURP" value="${escuelaProcedencia.getFechaIni()}" id="cajaPfin" class="tamano_cajas_texto campo_bloqueado">  
                                <br>
                                <label id="etq_pBachillerato" class="etqueta_dp">Promedio del Bachillerato:</label>
                                <input type="text" name="cajaCURP" readonly="readonly" value="${escuelaProcedencia.getPromedio()}" id="cajaPromedio" class="tamano_cajas_texto campo_bloqueado">  
                                <br>
                            </p>
                        </fieldset>
                    </section>

                </div>  

            </div>

            <label id="errorGuardarDP" class="tamano_cajas_texto21"></label>
            <input type="button" id="boton_guardarDP"   class="btn_style2" value="GUARDAR">
        </div>

        <!-- <div id="emergenteSobreVerde">
             <img  height="15%" width="12%" alt="Instituto Tecnológico de Toluca" src="Imagenes/cuidado.png">
             <label id="titulo" >AVISO IMPORTANTE</label>
 
             <fieldset id="datos_emergentesSObreVerde">
                 <br>
                 <label id="etqsobreVerde"></label>
 
 
             </fieldset>
 
             <input type="button" id="btn_aceptarSobreVerde" name="btn_aceptarSobreVerde" value="Aceptar" class="botonesEmergente">
         </div>-->
        <div id="fondoSobreVerde"></div> 

    </body>
</html>
