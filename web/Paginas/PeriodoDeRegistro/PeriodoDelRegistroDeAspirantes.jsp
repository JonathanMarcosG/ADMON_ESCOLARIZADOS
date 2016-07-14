<%-- 
    Document   : PeriodoDelRegistroDeAspirantes
    Created on : 5/08/2014, 07:51:41 PM
    Author     : Rocio
Configuraciones. Periodo de Registro
Permite la modificación de la fecha de registro, 
pide fecha de inicio y fecha de fin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
  <script src="JQuery/JSPeriodoDeRegistro/fecha.js" type="text/javascript"></script>
  <script>

            $.datepicker.regional['es'] = {
                closeText: 'Cerrar',
                prevText: '<Ant',
                nextText: 'Sig>',
                currentText: 'Hoy',
            monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
                monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'],
                dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
                dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb'],
                dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá'],
                weekHeader: 'Sm',
                dateFormat: 'dd/mm/yy',
                firstDay: 1,
                isRTL: false,
                showMonthAfterYear: false,
                yearSuffix: ''
            };

            $.datepicker.setDefaults($.datepicker.regional['es']);

            $(function() {

                $("#fechaInicio").datepicker({
                    firstDay: 1
                });
                $("#fechaFin").datepicker({
                    firstDay: 1
                });
            });

  </script> 
  <title>Periodo del Registro de Aspirantes</title>
 </head>
 <%@include file="../PeriodoDeRegistro/modalPeriodo.jsp" %>
 <body>
  <form id="enviarDatos">
   <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">
    <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
     <br><a class="titulo">Configuración de Convocatoria</a><br>
    </ul>
    <br>
    <div id="TipoDeBusqueda1">
     <label id="instrucciones">
       <span id="algo">Por favor complete todos los campos antes de guardar algún cambio.</span>
     <span id="algoc"></span>
    </div>
    
    <div id="AsignarPeriodo_contenedor">
        <p id="alert_param"></p>
    <br>
     <div id="asignarPeriodo">
      <section id="sobrePeriodo">
       <fieldset id="fs_periodo">
        <label id="fecha_inicio" class="etq_periodo">Fecha de inicio:</label>
        <input type="text" value="${fechas.getFechaInicio()}" id="fechaInicio" name ="fechaInicio" class="tamano_cajas_texto5" placeholder="Seleccionar fecha" maxlength="10" required/>
        
        <br><br><br>
        <label id="fecha_fin" class="etq_periodo">Fecha de fin:</label>
        <input type="text" value="${fechas.getFechaFin()}" id="fechaFin" name="fechaFin" class="tamano_cajas_texto5" placeholder="Seleccionar fecha" maxlength="10" required/>
       </fieldset>
      </section>
     </div>
       <input type="hidden" id="real" value="${convo.getMetaEstablecida()}"/>
       <input type="hidden" id="estab" value="${convo.getMetaReal()}"/>
       <input type="hidden" id="pago" value="${convo.getFechaPago()}"/>
       <input type="hidden" id="docs" value="${convo.getFechaEntrega()}"/>
       <input type="hidden" id="inPre" value="${convo.getFechaIpre()}"/>
       <input type="hidden" id="finPre" value="${convo.getFechaFpre()}"/>
       <input type="button" href="#" class="btn_style2" onclick="returnCasp();" value="Atrás" />
     <input id="btn_confirmar" type="button" class="btn_style2" value="Guardar">
     <input id="btn_cancelar" type="button" class="btn_style2" value="Limpiar" onclick="limpiarCampos();">
     
     <br>
    </div>
   
   </div>
  </form>
 </body>
</html>

