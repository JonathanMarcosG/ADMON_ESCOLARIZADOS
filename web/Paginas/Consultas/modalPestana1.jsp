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
                        <label>AVISO IMPORTANTE!</label>
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


                    </div>
                    <div class="modal-footer">
                        <input id="liberation" type="button"  class="btn btn-info btn-default" name="aceptar_conf" value="Aceptar" onclick="hidemodal();" >
                        <input id="cenevalI2" type="button"  class="btn btn-info btn-default" name="cenI" value="Aceptar" >

                    </div>
                </div>
            </div>
        </div> 
    </body>

</html>
