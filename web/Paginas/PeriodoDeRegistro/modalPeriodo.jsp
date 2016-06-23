<%-- 
    Document   : modalPeriodo
    Created on : Oct 15, 2015, 11:49:15 AM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
    </body>
     <div id="fondo2" class="modal fade" role="dialog">
          <div class="modal-dialog">
     <div id="emergente2" class="modal-content">
      <div class="modal-header">
       <img  height="5%" width="12%" alt="Instituto Tecnológico de Toluca" src="Imagenes/aler.png">
       <label>AVISO IMPORTANTE</label>
      </div>
      <div  class="modal-body">
       <label id="modificara" class="etq_emergenteTitulo">Se modificará el periodo de registro </label>
       <br>
       
       <label style="float: left;"  class="etq_emergente2">Fecha de inicio: </label><label id="finicio" name="finicio" class="etq_emergente3"></label><br>
       <label style="float: left;"  class="etq_emergente2">Fecha de fin: </label><label id="ffin" name="ffin" class="etq_emergente3"></label>
      </div>
      <div class="modal-footer">
       <input type="button" id="btn_aceptar" class="btn btn-info btn-default" name="btn_aceptar" value="Aceptar"  >
       <input type="button" id="btn_cancelarEmergente" class="btn btn-default " name="btn_cancelarEmergent" value="Cancelar"  onclick="hidemodal();">
       <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">

       <inpudiv>
    <dt type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
      </div>
     </div>
          </div>
    </div> 
</html>
