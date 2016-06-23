<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- EMPIEZA NAVBAR-->
<link href="Css/estandarDesarrollo.css" rel="stylesheet">

<script src="JQuery/jquery-ui.js" type="text/javascript"></script>
<script src="JQuery/bootstrap.min.js" type="text/javascript"></script>
<nav class="navbar navbar-default" role="navigation">
    <div style="background-color:#337ab7;" class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="color:white" href="#">Sistema Gestor de Registro de Aspirantes</a>
        </div>
        <div class="navbar-collapse" id="bs-example-navbar-collapse-1">            
            <ul class="nav navbar-nav navbar-right">        
                <li class="dropdown">
                    <a id="opInicio" onclick="Inicio();" style="color:white" class="glyphicon glyphicon-home"></a> 
                </li>

                <li class="dropdown">
                    <a href="#" style="color:white" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Consultas<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">                                                                      
                        <li><a id="liberacion" onclick="liberacion();">Liberación de pago individual</a></li> 
                        <li><a id="correcto" target="_blank" href="/LiberacionPagos/">Liberación de pago masiva</a></li> 
                        <li><a id="notificacion" onclick="notificaciones();">Notificaciones</a></li> 
                        <li><a id="asignacion" onclick="asignation();">Cierre de proceso</a></li> 

                        <li><a id="segAsp" onclick="Salumnos();">Seguimiento de aspirantes</a></li>
                        <li  id="reportes"><a onclick="Reportes(document.getElementById('user').value, document.getElementById('contra').value);">Reportes </a>

                            <input id="user" type="hidden" name="usuario" value="${datos.getUsuario()}" />
                            <input id="contra" type="hidden" name="contra" value="${datos.getContransenia()}" />


                        </li> 
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" style="color:white" data-toggle="dropdown" role="button" aria-expanded="false">Configuraciones<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a id="escuelas" onclick="escuelas();" >Alta de Escuelas</a></li>                                      

                        <li><a id="periodo" onclick="PDRAspirantes();">Configuración Convocatoria</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a id="recPreficha" onclick="recPreficha();" style="color:white" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Recuperar Preficha</a>
                </li>                
                <li class="dropdown">
                    <a id="errorDeCorreo" style="color:white" class="glyphicon glyphicon-alert"></a>
                    <a id="correcto" style="color:white" target="_blank" href="https://mail.ittoluca.edu.mx/mail/">
                        <i class="glyphicon glyphicon-envelope"></i>
                        <i id="contadorCorreo" class="badge" style="background-color: #dd4b39;" > </i> 
                    </a>
                </li>






                <li class="dropdown">
                    <a style="color:white" id="saliendo" class="glyphicon glyphicon-log-out" onclick="Salir();"></a> 
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!--TERMINA NAVBAR-->
