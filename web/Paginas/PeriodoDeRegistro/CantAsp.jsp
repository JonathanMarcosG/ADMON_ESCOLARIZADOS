<%-- 
    Document   : ReinicioSecuencias
    Created on : Aug 21, 2015, 1:06:30 PM
    Author     : David
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="JQuery/JSPeriodoDeRegistro/fecha.js" type="text/javascript"></script>
 <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
  <title>Periodo del Registro de Aspirantes</title>
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
                 $("#fechaInPre").datepicker({
                    firstDay: 1
                });
                $("#fechaFinPre").datepicker({
                    firstDay: 1
                });
            });

  </script> 
 </head>
 <%@include file="../PeriodoDeRegistro/modalCantAsp.jsp" %>
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
      <p id="alert_params"></p>
    </div>
    <div style="margin-left:100px;" class="spanf">
            <span  >Metas:</span>
    </div>
    <div style="margin-left:390px" class="spanf">
            <span  >Límites:</span>
    </div>
    <div style="margin-left:670px" class="spanf">
            <span >Renovaciones:</span>
    </div>
    <br>
    <div id="AsignarPeriodo_contenedor" >
        <div style="margin-left:50px;" class="shadowbox"> <input type="hidden" value=""/>
   
            <br>
            
    <label class="L_casp">Meta Establecida:</label>
    <br>
    <input type='text' value="${convo.getMetaEstablecida()}" id="metaes" class="cantAsp" name="cantAsp" placeholder="Cantidad de aspirantes"  /><img onmouseover="showbox(1);" onmouseout="hidebox(1);" style="border:none" width="20" src="Imagenes/mark_1.png" />
    <div id="metaE" class="hidebox">Por favor introduzca la cantidad de aspirantes que desea aceptar la institución.</div>
    <p id="message1" class="fieldMessage"></p>
    <br><br>
    <label class="L_casp">Meta Real:</label>
    <br>
    <input type="text" value="${convo.getMetaReal()}"  id="metare" class="cantAsp" name="cantAsp" placeholder="Cantidad de aspirantes"  /><img  onmouseover="showbox(2);" onmouseout="hidebox(2);"  style="border:none" width="20" src="Imagenes/mark_1.png" />
    <div id="metaR" class="hidebox">Por favor introduzca la cantidad real de aspirantes que puede aceptar la institución.</div>
     <p id="message2" class="fieldMessage"></p>
    <br><br></div>
    
    <!------------------------------------------------------------------------------------->
    
    <div style="margin-left:335px;" class="shadowbox"><br><label class="L_casp">Fecha límite de pago:</label>
     <br>
     <input type="text" value="${convo.getFechaPago()}" id="fechaInicio" name ="fechaInicio" class="cantAsp" placeholder="Seleccionar fecha" maxlength="10" required/><img onmouseover="showbox(3);" onmouseout="hidebox(3);" style="border:none" width="20" src="Imagenes/mark_1.png" />
     <div id="Finicio" class="hidebox">Por favor introduzca la fecha límite para que los aspirantes realicen su pago.</div>   
        <p id="message3" class="fieldMessage"></p>
        <br><br><label class="L_casp">Fecha límite de entrega de documentación:</label>
        <br>
        <input type="text" value="${convo.getFechaEntrega()}" id="fechaFin" name="fechaFin" class="cantAsp" placeholder="Seleccionar fecha" maxlength="10" required/><img onmouseover="showbox(4);" onmouseout="hidebox(4);" style="border:none" width="20" src="Imagenes/mark_1.png" />
        <div id="Ffin" class="hidebox">Por favor introduzca la fecha límite para que los aspirantes entreguen su documentación.</div>
           <p id="message4" class="fieldMessage"></p>
           
     <br> </div>
     
     <!-------------------------------------------------------------------------------------->
     
     <div style="margin-left:620px;" class="shadowbox"><br><label class="L_casp">Fecha inicio para renovación de preficha:</label>
     <input type="text" value="${convo.getFechaIpre()}" id="fechaInPre" name ="fechaInPre" class="cantAsp" placeholder="Seleccionar fecha" maxlength="10" required/><img onmouseover="showbox(5);" onmouseout="hidebox(5);" style="border:none" width="20" src="Imagenes/mark_1.png" />
     <div id="Finpre"  class="hidebox">Por favor introduzca la fecha de inicio para la renovación de prefichas.</div>   
     <p id="message5" class="fieldMessage"></p><br><br>
            <label class="L_casp">Fecha fin para renovación de preficha:</label>
        <br>
        <input type="text" value="${convo.getFechaFpre()}" id="fechaFinPre" name="fechaFinPre" class="cantAsp" placeholder="Seleccionar fecha" maxlength="10" required/><img onmouseover="showbox(6);" onmouseout="hidebox(6);" style="border:none" width="20" src="Imagenes/mark_1.png" />
        <div id="Ffinpre" class="hidebox">Por favor introduzca la fecha final para la renovación de prefichas</div>
           <p id="message6" class="fieldMessage"></p></div>
        
        <br><br><br><br><br><br><br><br><br><br><br>
   
        <input type="hidden" value="${fechas.getFechaInicio()}" id="fechaInicioP" name ="fechaInicioP" />

        <input type="hidden" value="${fechas.getFechaFin()}" id="fechaFinP" name="fechaFinP"/>
    <input id="btn_confirma" onclick="emptyFieldcasp();" type="button" class="btn_style2" value="Confirmar">
     <input id="btn_cancelar"  style="display:${convo.getMensaje()}" type="button" class="btn_style2" value="Siguiente" onclick="Periodo();">
     <br>
     <br>
    </div>
  
    </div> 
   </div>
  </form>
 </body>
</html>


