<%-- 
    Document   : inicioSesion_administrador
    Created on : 5/07/2014, 07:27:07 PM
    Author     : Rocio CR

Está página es el módulo de inicio de sesión del Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JQuery/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="JQuery/Funcion_Menu.js" type="text/javascript"></script>
        <link rel="stylesheet" href="Css/Estilo_InicioSesion.css" type="text/css">
        <link rel="stylesheet" href="Css/Estilo_MenuAdministrador.css" type="text/css"> 
        <link rel="stylesheet" href="Css/lookandfeel_tec.css" type="text/css">
        <title>Iniciar Sesión</title>
   
    </head>
    
    <body>
        <header>
            <div class="encabezado">
                <img alt="Instituto Tecnológico de Toluca" src="Imagenes/header-ittoluca1.png">
            </div>
        </header>
        
        <div id="Informacion_InicioSesion" class="Informacion_InicioSesion">
            <br>
            <img width="1000" height="25" alt="" src="Imagenes/barra_sup.png">
            <br><br>
            <label id="label_titulo"><h1>Sistema gestor del registro de aspirantes</h1></label> 
            <br><br>
            
            <p>
                <label id="etiqueta_usuario" class="etiqueta_usuario" >
                    <img src="Imagenes/usuario.ico" >Usuario:</label>
                
                <input type="text" id="caja_text" class="caja_text">
            </p> 
            <br>
            
            <p>
                <label class="etiqueta_contrasena"><img src="Imagenes/password.ico">Contraseña:</label>
                <input type="password" id="caja_pass">
            </p>
            <br>
            
            <p>
                <input type="button" name="Boton_acceder" id="Boton_acceder" class="Boton_acceder" value="ENTRAR">
            </p>
            <br><br>
            
            <img width="1000" height="25" alt="" src="Imagenes/barra_inf.png">
            <br>
            <br>
        </div>
        
        <footer>
            <div class="pieDePagina"><br><br>
                <label id="DR_tec" name="DR_tec" class="texto_inferior">
                    Instituto Tecnológico de Toluca | www.ittoluca.edu.mx
                    <br>
                    Instituto Tecnológico de Toluca - Algunos derechos reservados © 2013
                    <br>
                </label>
                
                <img width="940" height="88" alt="" src="Imagenes/footer.png" title="footer">
                <br>
                <label id="datos_tec" name="datos_tec" class="texto_inferior">
                    Av. Tecnológico s/n. Fraccionamiento La Virgen
                    <br>
                    Metepec, Edo. De México, México C.P. 52149
                    <br>
                    Tel. (52) (722) 2 08 72 00
                </label>
            </div>
        </footer>
    </body>
</html>
