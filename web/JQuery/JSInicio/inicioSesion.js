/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    //Inicio botón ingresar
    jQuery("#Boton_acceder").click(function() {
        iniciarSesion();
    });
//Fucnión para que inicie con enter
    $("#caja_pass").keypress(function(e) {
        if (e.which == 3) {
            iniciarSesion();
        }
    });
    //PARA INICIO DE SESIÓN 
    function iniciarSesion() {
        $("#consultarError").text(" ");
        var usuario = $("#caja_text").val().trim();
        var contra = $("#caja_pass").val().trim();
        if (usuario.length == 0 || contra.length == 0) {

            $("#consultarError").append("Por favor, ingrese usuario y contraseña.")
        } else {
            validarUsuario(usuario, contra);
        }
    }

    function validarUsuario(usuario, contra) {
        $("#Boton_acceder").hide();
        $("#spinInicio").show();
        jQuery.get("servletInicio",
                {usuario: usuario, contra: contra},
        function(data) {
            $("#consultarError").text(" ");
            if (data == "correcto") {

                 location.replace("inicioSesion_administrador.jsp?session=c");
                $(window).scroll(0, 0);
            } else {
                jQuery("#consultarError").append(data);
                $("#Boton_acceder").show();
                $("#spinInicio").hide();
            }
        });
    }
});