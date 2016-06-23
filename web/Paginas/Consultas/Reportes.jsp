<%-- 
    Document   : Reportes
    Created on : Nov 10, 2015, 11:30:27 AM
    Author     : David
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="JQuery/JSConsultas/reportes.js" type="text/javascript"></script>
   <link rel="stylesheet" href="Css/bootstrap.min.css.css" type="text/css">
  <title>Periodo del Registro de Aspirantes</title>

 </head>
 <body>
     <form id="enviarDatos" method="POST" action="" target="_blank">
   <div id="Asig_Contenedor" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">
    <ul class=" ui-tabs-nav ui-widget-header ui-corner-all">
     <br><a class="titulo">Reportes</a><br>
    </ul>
    <br>
     <div id="TipoDeBusqueda1">
     <span id="instrucciones">
        
         Seleccione un tipo de reporte.
     </span>
    </div>
    <div id="AsignarPeriodo_contenedor">
        
    <div id="tipoReporte" class="input-group">
        <span class="input-group-addon">TR</span>
    <select id="combo" onchange="aula();" class="form-control">
        <option>---Elija una opción---</option>
        <option value="0">Estadísticas de registros</option>
        <option value="1">Sin alta en CENEVAL</option>
        <option value="2">Estatus preficha</option>
        <option value="4">Procesos Concluidos</option>

        <option value="3">Aspirantes por aula</option>
         <option value="5">Lista de Firmas Aspirantes</option>
    </select>
    </div>
         
        <select id="comboAula" name="comboAula" onchange="horario();"  class="form-control">
            <option value="0">---Elija una opción---</option>
        <option value="1">Conocimientos</option>
        <option value="2">Matemáticas</option>
        
    </select>
        <select id="comboHorCen" name="horcen"  class="form-control">
            <option value="0">---Elija una opción---</option>
        <c:forEach items="${horarioscen}" var="lugarcen">
                            
                              
            <option value="${lugarcen}" > <c:out value="${lugarcen}" /></option>
                           
        </c:forEach>
         </select>
        <select id="comboHorMat" name="hormat" class="form-control">
            <option value="0">---Elija una opción---</option>
        <c:forEach items="${horariosmat}" var="lugarmat">
                            
                              
            <option value="${lugarmat}"> <c:out value="${lugarmat}" /></option>
                           
        </c:forEach>
         </select>   
    
        <br>
 <input type="hidden" id="usuario" name="usuario" value="${datos.getUsuario()}">
 <input type="hidden" id="contra" name="contra" value="${datos.getContransenia()}">
    <p id="mensajeBD">No se ha podido realizar la conexión a la base de datos.</p>
    <input id="reporte" type="button" onclick="repT();" class="btn_style2" value="Generar Reporte">
     <br>
     <br>
    </div>
    
    </div> 
   </div>
  </form>
 </body>
</html>
