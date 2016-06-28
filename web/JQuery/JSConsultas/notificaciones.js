/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$("#caja_busqueda_pref").keypress(function(e) {
        if (e.which == 13) {
            search();
        }
    });
function notifRenovacion(){
   var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    
     jQuery.get("servletMailsMasivos",
            {usuario: usuario, contra: contra},
    function (data) {
       if(data.toString()==="correcto"){
            $("#ref").text(""); 
             $("#btn_cancelarEmergente").hide();
              $("#renovacionR").hide();
        $("#ref").append("En breve los aspirantes serán notificados.".fontsize(3).fontcolor("green"));
        $("#referencia1").text("");
        $("#aspiante").text("");
        $("#referencia2").text("");
          setTimeout(function ()
            {
                hidemodal();
            }, 3000);
       }else{
        $("#ref").text(""); 
        $("#ref").append(data+" Se produjo el error al intentar notificar a los aspirantes, por favor intentelo de nuevo.".fontsize(3).fontcolor("red"));
//        $("#ref").append("Ha ocurrido un error al intentar notificar a los aspirantes, por favor intentelo de nuevo.".fontsize(3).fontcolor("red"));
        $("#referencia1").text("");
        $("#aspiante").text("");
        $("#referencia2").text("");
        
       }
    });
    
}
function busqueda(tipo) {
    $("#etiqueta_preficha").text("");
    $("#etiqueta_preficha").text(tipo);
    document.getElementById("caja_busqueda_pref").value = "";
}
function openModal(opc) {
    $('#fondo2').modal({
        backdrop: "static"
    });
    $("#btn_cancelarEmergente").show();
    if (opc === 1 || opc===4) {
        if(opc===4){
            altaCeneval();
        }else{
        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#cenevalR").show();
        $("#renovacionR").hide();
        $("#prefichaR").hide();
        $("#pagoR").hide();
        $("#ligaR").hide();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("indicando que debe registrarse en CENEVAL.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));
    }
    }
    if (opc === 2) {
        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#pagoR").show();
        $("#renovacionR").hide();
        $("#prefichaR").hide();
        $("#cenevalR").hide();
        $("#ligaR").hide();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("para confirmarle que su pago ha sido registrado.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));
    }
    if (opc === 3 ) {
        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#pagoR").hide();
        $("#prefichaR").show();
        $("#cenevalR").hide();
        $("#renovacionR").hide();
        $("#ligaR").hide();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("para recuperar su preficha.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));
    }
    if(opc===5){
        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#pagoR").hide();
        $("#prefichaR").hide();
        $("#cenevalR").hide();
        $("#ligaR").hide();
        $("#renovacionR").show();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Por favor considere que al elegir esta opción se le notificara a todos".fontsize(3).fontcolor("black"));
        $("#aspiante").append("los aspirantes de las fechas del periodo de renovación de prefichas vigente.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("¿Desea continuar?".fontsize(3).fontcolor("black"));
    }
        if (opc === 6 ) {
        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#pagoR").hide();
        $("#prefichaR").hide();
        $("#cenevalR").hide();
        $("#renovacionR").hide();
        $("#ligaR").show();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("para reenviarle la liga para registro.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));
    }
}

function search() {
    if ($("#caja_busqueda_pref").val() === "" || $("#caja_busqueda_pref").val() === null) {
        document.getElementById("caja_busqueda_pref").style.border = "red 2px solid";
        document.getElementById("caja_busqueda_pref").placeholder = "Campo vacio";
        $("#caja_busqueda_pref").focus();
    } else {
         $("#botonBuscar").hide();
         $("#spinInicioNot").show();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var parametroInicial = $("#caja_busqueda_pref").val();
        var bandera;


        if (document.getElementById("etiqueta_preficha").textContent === "Preficha:") {

            bandera = 3;
        }
        if (document.getElementById("etiqueta_preficha").textContent === "CURP:") {
            bandera = 2;
        }
        if (document.getElementById("etiqueta_preficha").textContent === "Correo:") {
            bandera = 1;
        }

        jQuery.get("servletNotificaciones",
                {usuario: usuario, contra: contra, bandera: bandera, parametroInicial: parametroInicial},
        function (data) {

            $("#margenVerNotif").load("Paginas/Consultas/Notificaciones.jsp #margenVerNotif");
            var estado = data.substring(0, 4);
            if (estado === "bien") {
                 $("#botonBuscar").show();
                 $("#spinInicioNot").hide();
                $("#cajaPref").show();
                $("#cajaNotifPago").show();
                 $("#cajaReg").show();
                document.getElementById("caja_busqueda_pref").style.border = " #A9A9A9 1px solid";
                $("#cajaNotifCen").show();
                var pago = data.substring(7, 8);
                var alta = data.substring(9, 10);
                
                if (pago == 0) {
                    $("#cajaNotifCen").hide();
                    $("#imgPagoP").show();
                    $("#imgPagomail").hide();
                } else {
                    $("#imgPagoP").hide();
                    $("#imgPagomail").show();
                }
                if (alta == 0) {
                    $("#imgCenP").show();
                    $("#imgCenmail").hide();
                } else {
                    $("#imgCenP").hide();
                    $("#imgCenmail").show();

                }
            } else {
                $("#cajaPref").hide();
                $("#cajaNotifPago").hide();

                $("#cajaNotifCen").hide();
                 $("#botonBuscar").show();
                 $("#spinInicioNot").hide();
                document.getElementById("caja_busqueda_pref").value = "";
                document.getElementById("caja_busqueda_pref").style.border = "red 2px solid";
                document.getElementById("caja_busqueda_pref").placeholder = "No se encontraron resultados";

            }


        });


    }
}
function altaCeneval(){
     var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
     var idAspirante = $("#caja_idAsp").val();

    jQuery.get("servletRegistrarAltaCENEVAL",
            {usuario: usuario, contra: contra, idAspirante: idAspirante},
    function(data) {

        if (data === "ninguno") {
           
            $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#cenevalR").show();
        $("#prefichaR").hide();
        $("#pagoR").hide();
        $("#ligaR").hide();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("indicando que debe registrarse en CENEVAL.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));
        } else {
             $("#referencia1").text("");
            $("#referencia1").text("Ha ocurrido al guardar el registro en CENEVAL. Por favor, intentelo de nuevo más tarde.\n");
        }

    });
}
function notifCeneval() {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var nombre = $("#caja_nomN").val();
    var correo =  $("#caja_correoN").val();
    var folio = $("#caja_fichaN").val();
    
    $("#ref").text(" ");
    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");
    $("#spinNotif").show();
    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");

    jQuery.get("servletCorreoCENEVAL", {usuario: usuario, contra: contra, nombre: nombre, correo: correo, folio: folio},
    function (data) {

        if (data === "correcto") {
            $("#referencia2").text("Enviado");
            document.getElementById("referencia2").style.color = "green";
            $("#spinNotif").hide();

            setTimeout(function ()
            {
                hidemodal();
            }, 1000);
        } else {
            $("#spinNotif").hide();
            $("#referencia2").text(" ");
            $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
            $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante.\n");
            $("#aspiante").append("Por favor, intente de nuevo más tarde.");


        }


    });
}
function notifPago() {

    var carrera =$("#caja_carreraN").val();
    var nombre = $("#caja_nomN").val();
    var correo = $("#caja_correoN").val(); //$("#contraOculta").val(); 
    var folio = $("#caja_fichaN").val();
    
     $("#ref").text(" ");
    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");
    $("#spinNotif").show();
    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");
    jQuery.get("/Modulo_Administrador/servletCorreoPagoProcesad", {correo: correo, nombreAsp: nombre, carrera: carrera, ficha: folio},
    function (data) {

        if (data == "correcto") {
            $("#referencia2").text("Enviado");
            document.getElementById("referencia2").style.color = "green";
            $("#spinNotif").hide();

            setTimeout(function ()
            {
                hidemodal();
            }, 1000);

        } else {
            $("#spinNotif").hide();
            $("#referencia2").text(" ");
            $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
            $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante.\n");
            $("#aspiante").append("Por favor, intente de nuevo más tarde.");
        }



    });
}
function notifLiga() {
    var curp =  $("#caja_curpN").val();//$("#usuarioOculto").val();
    
    var nombre = $("#caja_nomN").val();
    var correo =  $("#caja_correoN").val(); //$("#contraOculta").val(); 
    var folio = $("#caja_fichaN").val();
    $("#ref").text(" ");
    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");
    $("#spinNotif").show();
    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");

    jQuery.get("servletReenvioLiga", {  correo: correo},
    function (data) {

        if (data === "correcto") {
            $("#referencia2").text("Enviado");
            document.getElementById("referencia2").style.color = "green";
            $("#spinNotif").hide();

            setTimeout(function ()
            {
                hidemodal();
            }, 1000);
        } else {
            $("#spinNotif").hide();
            $("#referencia2").text(" ");
            $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
            $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante.\n");
            $("#aspiante").append("Por favor, intente de nuevo más tarde.");


        }


    });
}
function notifPreficha() {
    var curp =  $("#caja_curpN").val();//$("#usuarioOculto").val();
    
    var nombre = $("#caja_nomN").val();
    var correo =  $("#caja_correoN").val(); //$("#contraOculta").val(); 
    var folio = $("#caja_fichaN").val();
    $("#ref").text(" ");
    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");
    $("#spinNotif").show();
    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");

    jQuery.get("servletReenvioPref", {  correo: correo,nombre: nombre, folio: folio,curp:curp},
    function (data) {

        if (data === "correcto") {
            $("#referencia2").text("Enviado");
            document.getElementById("referencia2").style.color = "green";
            $("#spinNotif").hide();

            setTimeout(function ()
            {
                hidemodal();
            }, 1000);
        } else {
            $("#spinNotif").hide();
            $("#referencia2").text(" ");
            $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
            $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante.\n");
            $("#aspiante").append("Por favor, intente de nuevo más tarde.");


        }


    });
}
