
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
      

    </head>
    <body>
        <br>
        <div id="carrera" class="demo ui-tabs ui-widget ui-widget-content ui-corner-all">
            <div id="pestana_carrera">
                <ul id="ul_carrera" class=" ui-tabs-nav ui-widget-header ui-corner-all">
                    <a class="titulo">Recuperar Preficha</a>
                    
                </ul>
                <div id="panel_consultas_recupera">
                    <br>
                    <div>
                        <div id="text_recupera_recupera">

                            Por favor, ingrese un <u>CURP</u> y de clic en el bot&oacute;n 'Preficha' para ver la solicitud de registro del aspirante correspondiente.
                            <br><br>Sólo se mostrarán aquellas prefichas que se hayan generado en el periodo de registro actual.
                        </div>
                        <br>
                        <br>
                        <div id="elem_recupera" >
                            <br><br>
                            <form target="_blank" name="crp" id="crp" action="PrefichaPDF"  method="POST">
                                <input type="text" size="24" name="curp" id="curp" maxlength="18" onkeyup="this.value = this.value.toUpperCase()" placeholder="Introduce tu CURP"/>
                                <button id="btn_crp" type=submit value=enviar class="btn_style2">Preficha</button>
                            </form>
                            <br><br>
                        </div>
                    </div> 
                </div>
            </div>
            <br>
        </div>

        <!--        <div id="fade">
        
                </div>
                <div id="light">
                    <h3><img alt="alerta" src="Imagenes/aler.png" width="90px" height="90px">¡Aviso importante!</h3>
                    <div id="pop_blanco">
                        <h5>Esta es para recuperar la preficha</h5>
                    </div>
        
                    <div id="pop_botones">
                        <button type="button" class="btn btn-default" onClick="lightbox_close();"><a href="se-llamaba-emiliano.pdf" target="_blank">Cargar foto</a></button>
                        <button type="button" class="btn btn-default" onClick="lightbox_close();">Cancelar</button>
                    </div>
                </div>-->
    </body>
</html>
