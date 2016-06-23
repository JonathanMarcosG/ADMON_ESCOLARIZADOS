<%-- 
    Document   : LiberacionDePago
    Created on : 2/12/2014, 09:34:17 AM
    Author     : Rocio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
        <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
        <title>Liberación de Pago</title>
    </head>
    <%@include file="../Consultas/modalLiberacion.jsp" %>
    <body>
        <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">

            <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">

                <!--<br><h3>Liberación de Pago</h3><br>-->
                <br><a class="titulo">Liberación de Pago</a><br>
            </ul>
            <br><br>

            <div id="Caja_busquedaDPLP">

                <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
                <input type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">

                <label id="etiqueta_preficha">Referencia:</label>
                <input type="text"   id="caja_boton_liberacionReferencia"  class="tamano_cajas_textoBusquedaReferencia" required/>
                <input type="button" id="botonLiberar" class="btn_style2" value="Liberar">
                
               
               
            </div> 
            <br>
            <div id="ContDatosPersonalesPago">


                <section id="ParaAlinearMatricula" >  
                    <label id="errorDPLP"></label>                    
                    <br><br>
                    <fieldset id="cajaPrefichaDPLP">
                        <label id="prefichaDPLP" class="etiqueta_folios2">Preficha:</label>
                        <input type="text" value="${lp.getPreficha()}" readonly="readonly" id="caja_prefichaDPLP" class="tamano_cajas_textoFolios campo_bloqueado">
                    </fieldset>

                    <fieldset id="cajaFechaDPLP">
                        <label id="fichaDPLP" class="etiqueta_folios2">Ficha:</label>
                        <input type="text" value="${lp.getFicha()}" readonly="readonly" id="caja_fichaDPLP" class="tamano_cajas_textoFolios campo_bloqueado">
                    </fieldset>
                    <fieldset id="folioCajaDPLP">
                        <label id="folioDPLP" class="etiqueta_folios">He registrado en CENEVAL:</label>



                        <img id="imgnVerificado"  src="Imagenes/Bien.png">
                        <img id="imgnNoRegistrado"  src="Imagenes/imgGuardar.png">


                    </fieldset>



                    <br>
                </section>
                <br><br><br>
                <div id="contenedorInterno_DPPago">

                    <label id="dp_busquedaDPLP">DATOS PERSONALES</label>
                    <div id="margenVerDPLP">
                        <section id="sectionBusquedaDPerechoDPLP">
                            <fieldset id="fs_busquedaDPerechoDPLP">
                                <input  name="idAsp"  value="${lp.getIdAsp()}" id="caja_idAsp" type="hidden" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <label id="etq_apDPLP"  class="etqueta_dpPago">Apellido Paterno:</label>
                                <input  name="caja_apB" readonly="readonly" value="${lp.getApellidoPat()}" id="caja_apDPLP" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_amDPLP"  class="etqueta_dpPago">Apellido Materno:</label>
                                <input  name="caja_amB" readonly="readonly" value="${lp.getApellidoMat()}" id="caja_amDPLP" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_apDPLP"  class="etqueta_dpPago">Nombre(s):</label>
                                <input  name="caja_apB" readonly="readonly" value="${lp.getNombre()}" id="caja_nomDPLP" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_correoDPLP"  class="etqueta_dpPago">Correo:</label>
                                <input name="caja_correoB" readonly="readonly" value="${lp.getCorreo()}" id="caja_correoDPLP" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  
                                <br>
                                <label id="etq_carreraDPLP" class="etqueta_dpPago">Carrera por la cual participa:</label>
                                <input name="caja_carreraB" readonly="readonly" value="${lp.getCarrera1()}" id="caja_carreraDPLP" class="tamano_cajas_textoBusqueda1 campo_bloqueado">  

                                <input name="idAsp" type="hidden" value="${lp.getIdAsp()}" id="idAsp" class="tamano_cajas_textoBusqueda1">  
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
