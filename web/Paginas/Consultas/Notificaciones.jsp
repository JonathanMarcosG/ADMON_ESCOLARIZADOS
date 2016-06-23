<%-- 
    Document   : Notificaciones
    Created on : 10/02/2016, 09:34:17 AM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
          <script src="JQuery/JSConsultas/notificaciones.js" type="text/javascript"></script>
        <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
        <title>Notificaciones</title>
    </head>
    <%@include file="../Consultas/modalNotificaciones.jsp" %>
    <body>
        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <!--<br><h3>Liberación de Pago</h3><br>-->
                <br><a class="titulo">Notificaciones</a><br>
            </ul>
            <div id="TipoDeBusqueda">
                <label id="instrucciones">
                    Elija una categoría de búsqueda.
                </label>
            </div>
            <br><div id="notifPref" onclick="busqueda('Preficha:');" class="btn_busqueda">Preficha</div><div id="notifCurp" onclick="busqueda('CURP:');" class="btn_busqueda">CURP</div><div id="notifMail" onclick="busqueda('Correo:');" class="btn_busqueda">Correo</div><br>
            <br>
            <div id="Caja_busquedaNotif">
                
                <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
                <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">

                <label id="etiqueta_preficha">Preficha:</label>
                <input type="text"   id="caja_busqueda_pref"  class="tamano_cajas_textoBusquedaReferencia" required/>
                
                <input type="button" id="botonBuscar" onclick="search();" class="btn_style2" value="Buscar">
                 <div id="spinInicioNot" class="spinner-wave">
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                        </div>
               
               
            </div> 
            <br>
            <div id="ContDatosPersonalesPago">
              <fieldset id="cajaRen">
                        <label id="renovacion" class="etiqueta_foliosN">Renovación de Prefichas:</label>
                        <img id="imgRenmail" onclick="openModal(5);" src="Imagenes/icon-mail.png">
                    </fieldset>

                <section id="notificacionesCorreos" >  
                    <label id="errorDPLP"></label>                    
                    <br><br>
                     <fieldset id="cajaReg">
                        <label id="registro" class="etiqueta_foliosN">Reenviar Liga Registro:</label>
                        
                        <img id="imgRegmail" onclick="openModal(6);" src="Imagenes/reload.png">
                    </fieldset>
                    <fieldset id="cajaPref">
                        <label id="preficha" class="etiqueta_foliosN">Reenviar preficha:</label>
                        <img id="imgPrefmailP" onclick="openModal(3);" src="Imagenes/icon-mail.png">
                        <img id="imgPrefmail" onclick="openModal(3);" src="Imagenes/reload.png">
                    </fieldset>
                    <fieldset id="cajaNotifCen">
                        <label id="ceneval" class="etiqueta_foliosN">He registrado en CENEVAL:</label>
                        <img id="imgCenP" onclick="openModal(4);"  src="Imagenes/icon-mail.png">
                        <img id="imgCenmail" onclick="openModal(1);"  src="Imagenes/reload.png">
                    </fieldset>
                    <fieldset id="cajaNotifPago">
                        <label id="folioPago" class="etiqueta_foliosN">He realizado pago:</label>
                         <img id="imgPagoP"   src="Imagenes/prohibido.png">
                        <img id="imgPagomail" onclick="openModal(2);"  src="Imagenes/reload.png">
                    </fieldset>



                    <br>
                </section>
                <br><br><br>
                <div id="contenedorInterno_Notif">

                    <label id="dp_busquedaDPLP">DATOS PERSONALES</label>
                    <div id="margenVerNotif">
                        <section id="sectionBusquedaDPerechoDPLP">
                            <input  name="idAsp"  value="${seguimiento.getIdAsp()}" id="caja_idAsp" type="hidden" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                            <fieldset id="fs_busquedaDPerechoDPLP">
                               <label id="etq_correoDPLP"  class="etqueta_dpPago">No. de Solicitud:</label>
                                <input name="caja_prefB" readonly="readonly" value="${seguimiento.getPreficha()}" id="caja_prefN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                               <label id="etq_correoDPLP"  class="etqueta_dpPago">Ficha:</label>
                                <input name="caja_fichaB" readonly="readonly" value="${seguimiento.getFichaAl()}" id="caja_fichaN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_apDPLP"  class="etqueta_dpPago">Nombre(s):</label>
                                <input  name="caja_apB" readonly="readonly" value="${seguimiento.getNombre()}" id="caja_nomN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                 <label id="etq_ap"  class="etqueta_dpPago">Apellido Paterno:</label>
                                <input  name="caja_ap" readonly="readonly" value="${seguimiento.getApellidoP()}" id="caja_apN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                 <label id="etq_am"  class="etqueta_dpPago">Apellido Materno:</label>
                                <input  name="caja_am" readonly="readonly" value="${seguimiento.getApellidoM()}" id="caja_amN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_correoDPLP"  class="etqueta_dpPago">Correo:</label>
                                <input name="caja_correoB" readonly="readonly" value="${seguimiento.getCorreo()}" id="caja_correoN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                 <label id="etq_correoDPLP"  class="etqueta_dpPago">CURP:</label>
                                <input name="caja_curpB" readonly="readonly" value="${seguimiento.getCurp()}" id="caja_curpN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                 
                                
                                <label id="etq_carreraDPLP" class="etqueta_dpPago">Carrera por la cual participa:</label>
                                <input name="caja_carreraB" readonly="readonly" value="${seguimiento.getCarrera()}" id="caja_carreraN" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  

                                 
                                <br>
                                <br>
                            </fieldset>
                        </section>                       

                    </div>
                </div>
            </div>




            <div id="fondoReferencia"></div> 
        </div>
    </div>
</body>
</html>
