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
                        <br>
                        <label id="ref"></label>
                        <br><br>

                        <label id="referencia1"></label>
                        <br>
                        <label id="aspiante"></label><br>
                        <label id="referencia2"></label>
                        <br><br>

                        <br>
                        <div id="spinNotif" class="spinner-wave">
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                            <div></div>
                        </div>

                    </div>
                    <div class="modal-footer">
                         <input id="ligaR" type="button"  class="btn btn-info btn-default" onclick="notifLiga();" name="aceptar_conf" value="Aceptar"  >
                        <input id="pagoR" type="button"  class="btn btn-info btn-default" onclick="notifPago();" name="aceptar_conf" value="Aceptar"  >
                        <input id="renovacionR" type="button"  class="btn btn-info btn-default" onclick="notifRenovacion();" name="aceptar_conf" value="Aceptar"  >
                        <input id="prefichaR" type="button"  class="btn btn-info btn-default" onclick="notifPreficha();" name="aceptar_conf" value="Aceptar"  >
                        <input id="cenevalR" type="button"  class="btn btn-info btn-default" onclick="notifCeneval();" name="cenI" value="Aceptar" >
                        <input type="button" id="btn_cancelarEmergente" class="btn btn-default "  name="btn_cancelarEmergent" value="Cancelar" onclick="hidemodal();">

                    </div>
                </div>
            </div>
        </div> 
    </body>

</html>
