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
 </head>
  <%@include file="../PeriodoDeRegistro/modalReinicio.jsp" %>
 <body>
  <form id="enviarDatos">
   <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">
    <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
     <br><a class="titulo">Reinicio de Secuencias</a><br>
    </ul>
    <br>
     <div id="TipoDeBusqueda1">
     <span id="instrucciones">
        <img width="30" src="Imagenes/aler.png" />
         Antes de realizar algun cambio en el periodo de registro, se deben reiniciar las secuencias.
     </span>
    </div>
    <div id="AsignarPeriodo_contenedor">
    <br>
 <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">
       
    <dt type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
    <p id="mensajeBD">No se ha podido realizar la conexión a la base de datos.</p>
    <input id="btn_confsec" type="button" onclick="confSecuencias();" class="botonsillos" value="Reiniciar">
     <br>
     <br>
    </div>
    
    </div> 
   </div>
  </form>
 </body>
</html>


