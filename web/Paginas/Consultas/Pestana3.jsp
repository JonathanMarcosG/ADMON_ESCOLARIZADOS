<%-- 
    Document   : Pestana3
    Created on : 20/01/2015, 02:55:20 PM
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
        <input type="hidden" id="idAspirante3" name="idAspirante" value="${datosPersonales.getIdAsp()}">


        <div id="tabs-3" class="tab-active">                        
            <div id="etiquetas_superior">
                <fieldset id="solicitud">
                    <label id="noSolicitud2" class="etiqueta_folios">N° de Solicitud:</label>
                    <input type="text" readonly="readonly" value="${datosPersonales.getPreficha()}" id="caja_noSolicitud" class="campo_bloqueado tamano_cajas_textoFolios">

                </fieldset>

                <fieldset id="et_periodo">
                    <label id="Periodo">Periodo:</label>
                    <label id="tiempo">${fechas.getPeriodo()}</label>


                </fieldset>
            </div>
            <br><br>
            <div id="contenedorEmergencia">
                <label id="datosCE">EN CASO DE EMERGENCIA</label>
                <br>
                <label id="etq_EContacto" class="etqueta_dp">En emergencia llamar a (nombre y apellidos):</label>
                <input type="text" maxlength ="150" name="EContacto" value="${emergencia.getNomAp()}" id="EContacto1" class="tamano_cajas_texto">  
                <br><br>
                <div id="aaaaaaaah">
                    <section id="EmIzq">
                        <fieldset id="fs_emIzq">
                            <label id="etq_EEstado" class="etqueta_dp">Estado:</label>
                            <select type="text"  name="EEstado" value="${emergencia.getEstado()}" id="EEstado" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${edoE}" begin="0" end="${edoE.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                            <br>
                            <label id="etq_ECiudad" class="etqueta_dp">Municipio:</label>
                            <select name="ECiudad" id="ECiudad" class="tamano_cajas_texto">  
                                <c:forEach  var="carreras" items="${munE}" begin="0" end="${munE.size()}" >
                                    <option id="${carreras.getClaveCarrera()}" value="${carreras.getNombre()}">
                                        <c:out value="${carreras.getNombre()}"/>
                                    </option>
                                </c:forEach>
                            </select>

                            <br>
                            <label id="etq_EColonia" class="etqueta_dp">Colonia:</label>
                            <input type="text" maxlength ="45" name="EColonia" value="${emergencia.getColonia()}" id="EColonia" class="tamano_cajas_texto">  
                            <br>

                            <label id="etq_ECalle" class="etqueta_dp">Calle:</label>
                            <input type="text" maxlength ="45" name="ECalle" value="${emergencia.getCalle()}" id="ECalle" class="tamano_cajas_texto">  
                            <br>
                            <label id="etq_ETelFijo" class="etqueta_dp">Número Interior:</label>
                            <input type="text" maxlength ="5" name="ETelFijo" value="${emergencia.getNoInterior()}" id="ENumInterior" class="tamano_cajas_texto">  
                            <br>
                        </fieldset>
                    </section>
                    <section id="EmDer">
                        <fieldset id="fs_emDer">

                            <label id="etq_ETelCel" class="etqueta_dp">Número Exterior:</label>
                            <input type="text" maxlength ="5" name="ETelCel" value="${emergencia.getNoExterior()}" id="ENumExterior" class="tamano_cajas_texto">  
                            <br>

                            <label id="etq_ECTrabajo" class="etqueta_dp">Centro de trabajo:</label>
                            <input type="text" maxlength ="200" name="ECTrabajo" value="${emergencia.getCenTrabajo()}" id="ECTrabajo" class="tamano_cajas_texto">  
                            <br>
                            <label id="etq_ETelCT" class="etqueta_dp">Teléfono del centro de trabajo:</label>
                            <input type="text" maxlength ="13" name="ETelCT" value="${emergencia.getTelTrabajo()}" id="ETelCT" class="tamano_cajas_texto">  
                            <br>

                            <label id="etq_ETelFijoCt" class="etqueta_dp">Teléfono fijo:</label>
                            <input type="text" maxlength ="12" name="ETelFijoCT" value="${emergencia.getTelfijo()}" id="ETelFijoCT" class="tamano_cajas_texto">  
                            <br>

                            <label id="etq_ETelCelCT" class="etqueta_dp">Teléfono celular:</label>
                            <input type="text" maxlength ="13" name="ETelCelCT" value="${emergencia.getTelcel()}" id="ETelCelCT" class="tamano_cajas_texto">  


                        </fieldset>
                    </section>
                </div>
            </div>
            <br>
            <label type="text" id="errorGuardarCE" class="tamano_cajas_texto21"></label>
            <input type="button" id="boton_guardar3" class="btn_style2" value="GUARDAR">
            <br>
        </div> 

    </body>
</html>
