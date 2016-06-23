<%-- 
    Document   : Pestana2
    Created on : 20/01/2015, 02:55:10 PM
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
        <title>JSP Page</title>
    </head>
    <body>
        <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
        <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
        <input type="hidden" id="idAspirante2" name="idAspirante" value="${datosPersonales.getIdAsp()}">

        <div id="tabs-2" class="tab-active">
            <div id="etiquetas_superior">
                <section id="sectionParaAlinear">
                    <fieldset id="solicitud">
                        <label id="noSolicitud1" class="etiqueta_folios">N° de Solicitud:</label>
                        <input type="text" readonly="readonly" id="caja_noSolicitud" value="${datosPersonales.getPreficha()}" class="tamano_cajas_textoFolios campo_bloqueado">
                        <!--<input id="guardarS" type="text" value="${verificacion.getFichaUtilizada()}">-->
                    </fieldset>

                    <fieldset id="et_periodo">
                        <label id="Periodo">Periodo:</label>
                        <label id="tiempo"> ${fechas.getPeriodo()}</label>

                    </fieldset>
                </section>
                <br><br>
                <label id="errorDD"></label>

            </div>
            <div id="contendorDomicilio">
                <label id="datosDomicilio">DATOS DEL DOMICILIO</label><br>
                <section id="DIzquierda">
                    <fieldset id="fs_DIzquierda">                       
                        <p>

                            <input type="hidden" name="aspirante" value="${aspirante.getAspirante()}" id="aspirante" class="tamano_cajas_texto">  

                            <label id="etq_edo2" class="etqueta_dp">Estado:</label>
                            <select type="text" name="estado"  id="estadoDeAspirante" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${edoDD}" begin="0" end="${edoDD.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br> 

                            <label id="etq_municipioDD" class="etqueta_dp">Municipio:</label>
                            <select type="text"  name="caja_cuidad" id="caja_mun2" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${munDD}" begin="0" end="${munDD.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br> 

                            <label id="etq_ciudad" class="etqueta_dp">Localidad:</label>
                            <select type="text"   name="caja_cuidad" id="caja_loc2" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${locaDD}" begin="0" end="${locaDD.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br> 

                            <label id="etq_colonia" class="etqueta_dp">Colonia:</label>
                            <input type="text" maxlength ="150" value="${ddd.getColonia()}" name="Colonia" id="ColoniaDDD" class="tamano_cajas_texto">  
                            <br>  

                            <label id="etq_calleNo" class="etqueta_dp">Calle: </label>
                            <input type="text" maxlength ="150" value="${ddd.getCalle()}" name="calleNo" id="calleNoDDD" class="tamano_cajas_texto">  
                            <br> 

                        </p>
                    </fieldset>
                </section>

                <section id="DDerecha">
                    <fieldset id="fs_DDerecha">
                        <p>
                            <label id="etq_numExterior" class="etqueta_dp">Núm. Exterior:</label>
                            <input type="text" name="caja_cel" maxlength ="5" value="${ddd.getNoExterior()}" id="caja_numExterior" class="tamano_cajas_texto">  
                            <br> 

                            <label id="etq_numInterior" class="etqueta_dp">Núm.Interior:</label>
                            <input type="text" name="caja_cel" maxlength ="5" value="${ddd.getNoInterior()}" id="caja_numInterior" class="tamano_cajas_texto">  
                            <br> 

                            <label id="etq_cpdd" class="etqueta_dp">Código Postal:</label>
                            <input type="text" name="caja_cel" maxlength ="7" value="${ddd.getCp()}" id="caja_cpdd" class="tamano_cajas_texto">  
                            <br> 

                            <label id="etq_telCel" class="etqueta_dp">Teléfono Celular:</label>
                            <input type="text" name="telFijo" maxlength ="13" value="${ddd.getTelCel()}" id="caja_telCel" class="tamano_cajas_texto">  
                            <br> 

                            <label id="etq_telFijo" class="etqueta_dp">Teléfono Fijo:</label>
                            <input type="text"  value="${ddd.getTelFijo()}" maxlength ="13" name="codigoPostal" id="caja_telFijo" class="tamano_cajas_texto">  
                            <br>
                        </p>
                    </fieldset>
                </section>

            </div>


            <label id="errorDS"></label>


            <div id="contenedorDatosSE">

                <label id="datosSocioE">DATOS SOCIOECONÓMICOS</label><br>

                <div id="datosSocioMargen">

                    <section id="SEIzquierda">

                        <fieldset id="fs_SEIzquierda">
                            <p>

                                <label id="etq_nomPa" class="etqueta_dp">Nombre del padre:</label>
                                <input type="text" maxlength ="70" name="nomPadre" value="${dts.getNomPadre()}" id="nomPadre" class="tamano_cajas_texto">  
                                <br>

                                <label id="etq_vivePa" class="etqueta_dp">Vive Padre:</label>
                                <select type="text"  name="caja_vivePA" value="${dts.getZonaProcedencia()}" id="caja_vivePa" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${vivePa}" begin="0" end="${vivePa.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_ocupacionPadre" class="etqueta_dp">Ocupación del padre:</label>
                                <select type="text"  name="ocupacionPadre" value="${dts.getOcupacionPadre()}" id="ocupacionPadre" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${ocupacionPadre}" begin="0" end="${ocupacionPadre.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>


                            </p>
                        </fieldset>
                    </section>

                    <section id="SEDerecha">
                        <fieldset id="fs_SEDerecha">
                            <p>
                                <label id="etq_nomMa" class="etqueta_dp">Nombre de la madre:</label>
                                <input type="text" maxlength ="70" name="nomMadre" value="${dts.getNomMadre()}" id="nomMadre" class="tamano_cajas_texto">  
                                <br>

                                <label id="etq_viveMa" class="etqueta_dp">Vive Madre:</label>
                                <select type="text"  name="caja_viveMA" value="${dts.getZonaProcedencia()}" id="caja_viveMa" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${viveMa}" begin="0" end="${viveMa.size()}" >
                                        <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                                <label id="etq_ocupacionMadre" class="etqueta_dp">Ocupación de la madre:</label>
                                <select type="text"  name="ocupacionMadre" value="${dts.getOcupacionMadre()}" id="ocupacionMadre" class="tamano_cajas_texto">  
                                    <c:forEach  var="carreras" items="${ocupacionMadre}" begin="0" end="${ocupacionMadre.size()}" >
                                        <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                            <c:out value="${carreras.getNombre()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                                <br>

                            </p>
                        </fieldset>
                    </section>
                    <br><br>                 
                </div>
                <div id="estPadresdiv">
                    <section id="sectionEstPadres">
                        <fieldset id="fs_estPadres">
                            <label id="etq_estudiosPadre" class="etqueta_dp">Máximo nivel de estudios del padre:</label>
                            <select type="text"  name="estudiosPadre" value="${dts.getMaxEstudiosPadre()}" id="estudiosPadre" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${maxEstudiosPadre}" begin="0" end="${maxEstudiosPadre.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                            <label id="etq_estudiosMadre" class="etqueta_dp">Máximo nivel de estudios de la madre:</label>
                            <select type="text"  name="estudiosMadre" value="${dts.getMaxEstudiosMadre()}" id="estudiosMadre" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${maxEstudiosMadre}" begin="0" end="${maxEstudiosMadre.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                        </fieldset>
                    </section>
                </div>

                <div id="datosSocioMargen2">
                    <section id="SEIzquierda">
                        <fieldset id="fs_SEIzquierda">
                            <label id="etq_procedencia" class="etqueta_dp">Zona de Procedencia:</label>
                            <select type="text"  name="procedencia" value="${dts.getZonaProcedencia()}" id="procedencia" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${zonaProcedencia}" begin="0" end="${zonaProcedencia.size()}" >
                                    <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>


                            <label id="etq_casaVive" class="etqueta_dp">La casa donde vive es:</label>
                            <select type="text"  name="casaVive" value="${dts.getTipoCasa()}" id="casaVive" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${tipoCasa}" begin="0" end="${tipoCasa.size()}" >
                                    <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>

                            <label id="etq_depEconomico" class="etqueta_dp">Económicamente depende de:</label>
                            <br>
                            <input type="text" readonly="readonly" name="cajaDepende" id="cajaDepende" value="${dts.getDependeDe()}" class="tamano_cajas_texto campo_bloqueado" size="30">  


                            <!--<label id="etq_depEconomico" class="etqueta_dp1">Económicamente depende de:</label>-->
                            <!--<select type="text" name="depEconomico"  id="depEconomico" class="tamano_cajas_texto1">-->  
                            <%--<c:forEach  var="carreras" items="${dependeDe}" begin="0" end="${dependeDe.size()}" >--%>
                                <!--<option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">-->
                            <%--<c:out value="${carreras.getNombre()}"/>--%>
                            <!--</option>-->
                            <%--</c:forEach>--%>
                            <!--</select>-->

                        </fieldset>
                    </section>
                    <section id="SEDerecha">
                        <fieldset id="fs_SEDerecha">
                            <label id="etq_viveCon" class="etqueta_dp">Actualmente vive con:</label>
                            <input type="text" readonly="readonly"  name="viveCon" id="viveCon" value="${dts.getViveCon()}" class="tamano_cajas_texto campo_bloqueado">  

                            <label id="etq_beca" class="etqueta_dp">Cuenta con alguna beca:</label>
                            <input type="text"  name="ccbeca" maxlength ="50" value="${dts.getBeca()}" id="ccbeca" class="tamano_cajas_texto"> 

                            <br>
                            <label id="etq_ingresos" class="etqueta_dp">Ing. familiares mensuales:</label>
                            <select type="text" name="ingresos" value="${dts.getIngresosTotales()}" id="ingresos" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${ingresosTotales}" begin="0" end="${ingresosTotales.size()}" >
                                    <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>



                            <br>


                            <!--<label id="etq_viveCon" class="etqueta_dp1">Actualmente vive con:</label>-->
                            <!--<select type="text"  name="viveCon" id="viveCon" value="${dts.getViveCon()}" class="tamano_cajas_texto1">-->  
                            <%--<c:forEach  var="carreras" items="${viveCon}" begin="0" end="${viveCon.size()}" >--%>
                                <!--<option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">-->
                            <%--<c:out value="${carreras.getNombre()}"/>--%>
                            <!--</option>-->
                            <%--</c:forEach>--%>
                            <!--</select>-->
                            <br>
                        </fieldset>
                    </section>

                </div>

                <div id="divDSLargas">
                    <section id="sectionDSetqsLargas">
                        <fieldset id="fs_DSetqsLargas">

                            <label id="etq_programaOportunidades" class="etqueta_dp">Pertenece al programa oportunidades:</label>
                            <select type="text"  name="programaOportunidades" value="${dts.getProgOportunidades()}" id="programaOportunidades" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${progOportunidades}" begin="0" end="${progOportunidades.size()}" >
                                    <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <label id="etq_habitantes" class="etqueta_dp">Número de personas que viven en esa casa:</label>
                            <select type="text" name="habitantes" value="${dts.getNoPersonasCasa()}" id="habitantes" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${noPersonasCasa}" begin="0" end="${noPersonasCasa.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>                                     
                            <label id="etq_cuartosCasa" class="etqueta_dp">Número de cuartos en la casa(no baños):</label>
                            <select type="text"  name="cuartosCasa" value="${dts.getCuartosCasa()}" id="cuartosCasa" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${cuartosCasa}" begin="0" end="${cuartosCasa.size()}" >
                                    <option id="${carreras.getIdPais()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>

                            <label id="etq_sustento" class="etqueta_Dependientes">Número de personas(incluyendo al aspirante) que 
                                dependen del principal sustento:</label>
                            <select type="text" name="sustento"  id="sustento" class="selectDependiente">  
                                <c:forEach  var="carreras" items="${dependeEconomicamente}" begin="0" end="${dependeEconomicamente.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                        </fieldset>
                    </section>
                </div>
            </div>
            <br>

            <label type="text" id="errorGuardarSE" class="tamano_cajas_texto21"></label>
            <input type="button" id="boton_guardar2"  class="btn_style2" value="GUARDAR">
            <br>

        </div>

    </body>
</html>
