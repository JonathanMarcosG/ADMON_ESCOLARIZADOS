<%-- 
    Document   : modalCantAsp
    Created on : Oct 13, 2015, 12:25:10 PM
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
 <div id="fondo2" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div id="emergente2" class="modal-content">
                            <div class="modal-header">
                                <img  height="5%" width="12%" alt="Instituto Tecnológico de Toluca" src="Imagenes/aler.png">
                                <label>AVISO IMPORTANTE</label>
                            </div>
                            <div  class="modal-body">
                                <label id="modificara" class="etq_emergenteTitulo">Se modificará la convocatoria</label>
                                <br>
                                
                                <label id="MEL" style="float:left;">Meta Establecida:</label><label style="position:relative;left:135px;" id="metE" name="metE" class="etq_emergente2"></label><br>
                                <label id="MRL" style="float:left;">Meta Real:</label><label style="position:relative;left:160px;" id="metR" name="metR" class="etq_emergente2"></label><br>
                                <label id="FLPL" style="float:left;">F.L de pago:</label><label style="position:relative;left:178px;" id="Fpago" name="Fpago" class="etq_emergente2"></label><br>
                                <label id="FLEDL" style="float:left;">F.L de entrega de documentos:</label><label style="position:relative;left:110px;" id="Fentrega" name="Fentrega" class="etq_emergente2"></label><br>
                                <label id="FIRPL" style="float:left;">F.I Renovación de prefichas:</label><label style="position:relative;left:120px;" id="inPref" name="inpre" class="etq_emergente2"></label><br>
                                <label id="FFRPL" style="float:left;">F.F Renovación de prefichas:</label><label style="position:relative;left:120px;" id="finPref" name="finpre" class="etq_emergente2"></label><br>
                                
                            
                            </div>
                            <div class="modal-footer">
                                <input type="button" id="aceptar_conf" class="btn btn-info btn-default" name="aceptar_conf" value="Aceptar"  >
                      
                                <input type="button" id="hidden_acept" onclick="hidemodal2();" class="btn btn-info btn-default" name="aceptar_conf" value="Notificar"  >

                                <input type="button" id="btn_cancelarEmergente" class="btn btn-default "  name="btn_cancelarEmergent" value="Cancelar" onclick="hidemodal();">
                                <input type="hidden" id="usuarioOculto" name="usuarioOculta" value="${datos.getUsuario()}">

                                <dt type="hidden" id="contraOculta" name="contraOculta" value="${datos.getContransenia()}">
                            </div>
                        </div>
                    </div>
                </div> 
    </body>
               
</html>
