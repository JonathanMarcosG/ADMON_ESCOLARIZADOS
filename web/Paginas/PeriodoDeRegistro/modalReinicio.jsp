<%-- 
    Document   : modalReinicio
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
       
       
       <label  class="etq_emergente4">Apreciable administrador, se le notifica que está apunto de reiniciar las secuencias para el conteo de prefichas en esta nueva convocatoria. Esta acción es irreversible, ¿Está seguro de querer reiniciar las secuencias?</label>
      
      </div>
      <div class="modal-footer">
          <input type="button" id="btn_acepta" class="btn btn-info btn-default" name="btn_aceptar" onclick="secuencias();" value="Aceptar"  >
       <input type="button" id="btn_cancelarEmergente" class="btn btn-default " name="btn_cancelarEmergent" value="Cancelar"  onclick="hidemodal();">
       <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">

       <inpudiv>
    <dt type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
      </div>
     </div>
          </div>
    </div> 
</html>
