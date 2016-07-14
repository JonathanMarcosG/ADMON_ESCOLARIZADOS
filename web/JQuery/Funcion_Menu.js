/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//consultas LiberacionDePago.jsp
// se ejecuta al dar click en la opción del menú
function liberacion() {
    $.get("servletLimpiarBeans",
            function (data) {
                $(window).scroll(0, 0);
                $("#MargenYEncabezado").load("Paginas/Consultas/LiberacionDePago.jsp");

            });
}
//Opción recuperar preficha
$("#recPreficha").click(function () {
    $("#MargenYEncabezado").load("Paginas/RecuperarPreficha/recuperarPreficha.jsp");

});
//Validación para CURP
//Consultas SeguimientoDeAlumnos.jsp
//Al elegir la opción de búsqueda por CURP.
function tryCURP(id) {
    curp = $("#" + id).val();
    if (curp.length < 12) {
        alert("CURP de menos de 18 dígitos.");
    } else {
        numeros = /^[0-9]+$/;
        letras = /^[A-Za-z-ñäöüßÄÖÜ]+$/;
        primeras = curp.substring(0, 4);
        siguientes = curp.substring(4, 10);
        ultimas = curp.substring(10, 16);
        digitosFinales = curp.substring(16, 18);
        if (!letras.test(primeras) || !letras.test(ultimas)
                || !numeros.test(siguientes) || !numeros.test(digitosFinales)) {
            alert(curp + "\n\nNo es un CURP válido");
            $("#" + id).css("background", "#E80000");
            return false;
        } else {
            return true;
            $("#" + id).css("background", "");
        }


    }
}

//Consultas ConsultaDeDatos.jsp
//Validación en los campos que aceptan letras , números
//y espacios.
function validaAlfaNum(id) {
    var patron = /^[0-9A-Za-zÑ-ñ]+$/;
    var txtcurp = $("#" + id).val().trim();
    if (!patron.test(txtcurp)) {
        $("#" + id).css("background", "#E80000");
        alert("Símbolos no aceptados");
        return false;
    } else {
        $("#" + id).css("background", "");
        return true;
    }
}
function validaAlfa(id) {
    var patron = /^[A-Za-zÑ-ñ]+$/;
    var txtcurp = $("#" + id).val();
    if (!patron.test(txtcurp)) {
        $("#" + id).css("background", "#E80000");
        alert("Símbolos no aceptados");
        return false;
    } else {
        $("#" + id).css("background", "");
        return true;
    }
}
function validaNum(id) {
    var patron = /^[0-9]+$/;
    var txtcurp = $("#" + id).val();

    if (!patron.test(txtcurp)) {
        $("#" + id).css("background", "#E80000");
        alert("Símbolos no aceptados");
        return false;
    } else {
        $("#" + id).css("background", "");
        return true;
    }
}

//Consultas ConsultaDeDatos.jsp
//Validación en los campos que sólo
//aceptan números.
function tryNum(id) {

    numeros = $("#" + id).val();
    if (numeros == "" || numeros == null) {
        alert("Porfavor ingrese algún valor");
    } else
        validaAlfaNum(id);
    {
        patron = /^[0-9]+$/;
        if (!patron.test(numeros)) {
            $("#" + id).css("background", "#E80000");
            alert("Sólo números");
            return false;
        } else
            $("#" + id).css("background", "");
        return true;
    }

}

//Consultas ConsultaDeDatos.jsp
//Validación en los campos que sólo
//aceptan letras y espacios.
function tryLetts(id) {

    letras = $("#" + id).val();
    if (letras == "" || letras == null) {
        alert("El campo" + id + "está vacío.");
        return false;
    } else

    {

        patron1 = /^[A-Za-z-ñäöüßÄÖÜáéíóúÁÉÍÓÚ ]+$/;
        if (!patron1.test(letras)) {

            $("#" + id).css("background", "#E80000");
            return false;
            alert("Sólo letras");
        } else {
            $("#" + id).css("background", "");
            return true;
        }
    }
}

//Consultas ConsultaDeDatos.jsp
//Validación en los campos que sólo
//aceptan letras y espacios y no pueden ser nulos
//nombre jejeje.
function CamposDeNombre(id, idEtq) {

    letras = $("#" + id).val();
    if (letras == "" || letras == null) {
        alert("El campo   " + $("#" + idEtq).text() + "   está vacío.");
        $("#" + id).css("background", "#E80000");
        return false;
    } else

    {

        validaAlfa(id);
    }
}



//Consultas SegumientoDealumnos.jsp
//Muestra el div que contiene todas las
//coincidencias de la búsqueda por nombre
function ConsultarPorNombre() {
    $('#contenedor_verCarreraConsulta').hide();
    $('#contenedor_verConsulta').show();
    $('#contenedor_ver').hide();
}

//Consultas SegumientoDealumnos.jsp
//Muestra el div que contiene todas las
//coincidencias de la búsqueda por carrera
function ConsultarPorCarrera() {
    $("#contenedor_verConsulta").hide();
    $('#contenedor_verCarreraConsulta').show();
    $('#contenedor_ver').hide();
}


//Consultas SegumientoDealumnos.jsp
//Muestra el div donde se deberían desplegar
//los datos del alumno consultado
//Por carrera
function verCarrera() {
    $('#porCarrera').hide();
    $('#contenedor_consulta').show();
    $('#avance').show();
    $('#contenedor_verCarreraConsulta').hide();
}

//Consultas SegumientoDealumnos.jsp
//Muestra el div donde se deberían desplegar
//los datos del alumno consultado
//Por nombre
function verNombre() {

    $('#porNombre').hide();
    $('#contenedor_consulta').show();
    $('#avance').show();
    $('#contenedor_ver').hide();
    $('#contenedor_verConsulta').hide();
}

//Se ejecuta cada que se selecciona "Inicio"
//en el menú
//se creó porque después de cierto tiempo
//que la aplicación se ejecuta
//se pierde la cadena que contiene el periodo
function Inicio() {
    $("#Informacion_InicioSesion").load("Paginas/Menu/menu_administrador.jsp");
    $(window).scroll(0, 0);
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    reEnviarPeriodo(usuario, contra);
}

//Se utitliza en la función Inicio
//Conecta con la base de datos 
//Regresa la cadena del periodo actual
function reEnviarPeriodo(usuario, contra) {
    jQuery.get("servletInicio",
            {usuario: usuario, contra: contra});
}

//Se ejecuta cuando se da click en salir en el menú
function Salir() {
    window.location.href = '/Modulo_Administrador/';
}

function aceptar() {
    $('#fondo').hide();
    $('#emergente').hide();
    $('#fondo3').show();
    $('#emergente3').show();
    $("#CenevalConfirmacion").prop("disabled", false);
}

function cancelar() {
    $('#fondo').hide();
    $('#emergente').hide();
    $('#fondo3').hide();
    $('#emergente3').hide();
}

function cancelar2() {
    $('#fondo3').hide();
    $('#emergente3').hide();
} /////////////////////////////////

function cancelarGuardar() {
    $('#fondoGuardar').hide();
    $('#emergenteGuardar').hide();
}

//PeriodoDeRegistro PeriodoDeRegistroDeAspirantes.jsp
//Se ejecuta al dar click en el botón cancelar
//de la ventanan emergente de confirmación de
//cambio de perioodo.
function limpiarCampos() {
    $('#fechaInicio').val('');
    $('#fechaFin').val('');
    $('#fondo2').hide();
    $('#emergente2').hide();
}

//Consultas SeguimientoDeAlumnos.jsp
//se ejecuta al dar
//click en boton "ver" de barrita de proceso
//Despliega el manual de proceso
function verProceso() {
    window.scrollTo(0, 0);
    $('#emergente_manual').show();
    $('#emergenteManual_fondo').show();
}
function aceptarPeriodo() {
    $('#fondo2').hide();
    $('#emergente2').hide();
}

//Consultas SeguimientoDeAlumnos.jsp
//Desaparece el manual de proceso
//Se ejecuta al dar click en el fondo
//del aviso emergente
function procesoAspirantes() {
    $('#emergente_manual').hide();
    $('#emergenteManual_fondo').hide();
}




function resaltarPreficha() {

}

//Consultas SeguimientosDeAlumnos.jsp
//Se ejecuta cuando se selecciona el botón de 
//búsqueda por preficha
//Cambia los botones de color para marcar la opción seleccionada
//Muestra el campo para ingresar la preficha que se busca
$("#busqueda_preficha").click(function () {
    limpiarBeansSeguimiento();
    $("#contenedor_consulta").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp #contenedor_consulta");
    $("#cajaporFicha").val("");
    $('#porFicha').show();
    $('#porCURP').hide();
    $('#porNombre').hide();
    $('#busqueda_nombre').css('background-color', 'white');
    $('#busquda_curp').css('background-color', 'white');
    $('#busqueda_preficha').css('background-color', '#ffff70');
    $('#busquda_carrera').css('background-color', 'white');

    activar();

});
//Consultas SeguimientosDeAlumnos.jsp
//Se ejecuta cuando se selecciona el botón de 
//búsqueda por curp
//Cambia los botones de color para marcar la opción seleccionada
//Muestra el campo para ingresar el curp que se busca
$("#busquda_curp").click(function () {
    limpiarBeansSeguimiento();
    $("#contenedor_consulta").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp #contenedor_consulta");
    $("#cajaporCURP").val("");
    $('#porFicha').hide();
    $('#porCURP').show();
    $('#porNombre').hide();
    $('#busqueda_preficha').css('background-color', 'white');
    $('#busquda_curp').css('background-color', '#ffff70');
    $('#busqueda_nombre').css('background-color', 'white');
    $('#busquda_carrera').css('background-color', 'white');
});
//Consultas SeguimientosDeAlumnos.jsp
//Se ejecuta cuando se selecciona el botón de 
//búsqueda por correo
//Cambia los botones de color para marcar la opción seleccionada
//Muestra los campos para ingresar el nombre que se busca
$("#busqueda_nombre").click(function () {
    limpiarBeansSeguimiento();
    $("#cajaCorreoElectronicoBusqueda").val("");
    $("#contenedor_consulta").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp #contenedor_consulta");
    $('#porFicha').hide();
    $('#porCURP').hide();
    $('#porNombre').show();
    $('#busqueda_nombre').css('background-color', '#ffff70');
    $('#busquda_curp').css('background-color', 'white');
    $('#busqueda_preficha').css('background-color', 'white');
    $('#busquda_carrera').css('background-color', 'white');
});
//Consultas AsignacionDeMatricula.jsp 
//Se ejecuta al finalizar el servlet de DatosPersonalesObtener
//si el pago del aspsirante ya ha sido procesado
//(cambia los sobres de color)
function recPreficha() {

    $("#MargenYEncabezado").load("Paginas/RecuperarPreficha/recuperarPreficha.jsp");
}
function Amatricula() {
    $(window).scroll(0, 0);

    $("#ece").css("background", "gainsboro");
    $("#ddatoss").css("background", "gainsboro");
    $("#df").css("background", "#a7cce5");
    intAsig = setInterval(function () {

        var sinDefinir = typeof $("#caja_folio").val();
        if (sinDefinir !== "undefined") {

            var folio = $("#caja_folio").val().trim();
            var validar = $('#cajaCURP').val();
            var libCen = $("#libCeneval").val();
            $("#folioAM").val(folio);
            $("#validarAM").val(validar);
            $("#libCenAM").val(libCen);
            if (folio == "" || folio == null || folio == "NO ASIGNADA") {
                $("#imgRojaImprimir").show();
                $("#botonVerde").hide();
            } else {
                $("#imgRojaImprimir").hide();
                $("#botonVerde").show();
            }


            var preficha = $("#caja_boton_busquedapreficha").val();
            registroCeneval(preficha.trim());
            clearInterval(intAsig);
            activar();
        }
    }, 500);
}

//Servlet para limpiar los beans de seguimiento
function limpiarBeansSeguimiento() {
    jQuery.get("servletLimpiarBeansSeguimiento");
}

//Menú
//Se ejecuta al seleccionar del menú:
//Consultas: Seguimiento de alumnos
function Salumnos() {

    $(window).scroll(0, 0);
    limpiarBeansSeguimiento();
    $("#MargenYEncabezado").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp");
}

//Menú
//Se ejecuta al seleccionar del menú:
//Consultas: Aspirantes Registrados
function Reportes(usuario, contra) {
    $(window).scroll(0, 0);

    jQuery.get("servletHorarios",
            {usuario: usuario, contra: contra},
            function (data) {
                if (data.toString() === "correcto") {
                    $("#MargenYEncabezado").load("Paginas/Consultas/Reportes.jsp");
                } else {
                    alert("Ocurrio un error al cargar las opciones, por favor refresque la página.")
                }

            });
}
function Aregistrados() {
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Estadisticas/AspirantesRegistados.jsp");
}

//Menú
//Se ejecuta al seleccionar del menú:
//Consultas: Seguimiento de alumnos
function PDRAspirantes() {
    $(window).scroll(0, 0);
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();

    jQuery.get("servletSequence", {usuario: usuario, contra: contra}, function (data) {
        if (data.toString() === "true") {
            $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/ReinicioSecuencias.jsp");
        } else if (data.toString() === "false") {

            $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/CantAsp.jsp");
        } else {
            $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/ReinicioSecuencias.jsp");
        }
    });

}

//Menú
//Se ejecuta al seleccionar del menú:
//Peridodo de Registro
function ManualAPP() {
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Ayuda/ManualDeLaAplicacion.jsp");
}

//Verifica si algún campo del fieldset ladoIzqEscuelas
//ha sido modificado
//Escuelas altaYBusqueda.jsp
$("#ladoIzqEscuelas :input").change(function () {

    $("#ladoIzqEscuelas").data("changed", true);
});

//Verifica si algún campo del fieldset ladoDerEscuelas
//ha sido modificado
//Escuelas altaYBusqueda.jsp
$("#ladoDerEscuelas :input").change(function () {

    $("#ladoDerEscuelas").data("changed", true);
});

//Verifica si algún campo del fieldset nombreYDomNvaEsc
//ha sido modificado
//Escuelas altaYBusqueda.jsp
$("#nombreYDomNvaEsc :input").change(function () {

    $("#nombreYDomNvaEsc").data("changed", true);
});
//Verifica si algún campo del div de etiquetas_superior
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#etiquetas_superior :input").change(function () {

    $("#etiquetas_superior").data("changed", true);
});
///Verifica si caja_datosExamen
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#caja_datosExamen :input").change(function () {

    $("#caja_datosExamen").data("changed", true);
});
///Verifica si caja_datosExamenMate
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#caja_datosExamenMate :input").change(function () {

    $("#caja_datosExamenMate").data("changed", true);
});
///Verifica si contenedorDatosPersonales
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#contenedorDatosPersonales :input").change(function () {

    $("#contenedorDatosPersonales").data("changed", true);
});
///Verifica si contenedorEscuelaPro
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#contenedorEscuelaPro :input").change(function () {

    $("#contenedorEscuelaPro").data("changed", true);
});
///Verifica si contendorDomicilio
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#contendorDomicilio :input").change(function () {

    $("#contendorDomicilio").data("changed", true);
});
///Verifica si contenedorDatosSE
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#contenedorDatosSE :input").change(function () {

    $("#contenedorDatosSE").data("changed", true);
});
///Verifica si contenedorEmergencia
//ha sido modificado para el guardado dd datos.
//Consultas AsignaciónDeMatrícula.jsp
$("#contenedorEmergencia :input").change(function () {

    $("#contenedorEmergencia").data("changed", true);
});
$(document).ready(function () {


////Revisan el tiempo de vida de la sesión
//    window.onclick = r;
//    window.onmousemove = r;
//    window.onkeypress = r;
//    function r() {
//        javascript:c = 0;
//    }

//Link de la notificación de correo
    $("#notifCorreos").click(function () {
        window.open("https://mail.ittoluca.edu.mx/mail/");
    });

//Consultas AsignaciónDeMatrícula.jsp pestaña  1
//Se manda llamar cuando se da click en el disco que
//aparece a la derecha de FOLIO CENEVAL
//actualiza el folio CENEVAL del aspirante
    function modificarFolioCENEVAL(folio, aspirante, usuario, contra) {
        jQuery.get("servletFCENEVAL"
                , {folio: folio, aspirante: aspirante, usuario: usuario, contra: contra}
        , function (data) {
            $("#confirmFolio").text("");
            if (data == "ninguno") {
                $("#confirmFolio").append("guardado...".fontsize(1).fontcolor("green"));
                $("#imgRojaImprimir").hide();
                $("#botonVerde").show();
                $("#ImgGuardarFolio").hide();
            } else {
                alert(data);
                $("#confirmFolio").append("no guardado...".fontsize(1).fontcolor("red"));
                $("#imgRojaImprimir").show();
                $("#botonVerde").hide();
                $("#ImgGuardarFolio").hide();
            }
        });
    }

//Consultas AsignaciónDeMatrícula.jsp pestaña  1
//Revisa si el folio ha sido modificado, si así fuera
//muestra la imagen para guardar
//si el campo está vacio, muestra sobre rojo
    $("#caja_folio").on('input', function () {
        var folio = $("#caja_folio").val().trim();
        if (validaAlfaNum("caja_folio")) {

            if (folio == "" || folio == null || folio == "NO ASIGNADA") {
                $("#imgRojaImprimir").show();
                $("#botonVerde").hide();
                $("#ImgGuardarFolio").hide();
            } else {

                $("#ImgGuardarFolio").show();
                $("#imgRojaImprimir").hide();
                $("#botonVerde").hide();
            }
        } else {
            $("#imgRojaImprimir").show();
            $("#botonVerde").hide();
            $("#ImgGuardarFolio").hide();
        }
    });
////Consultas AsignaciónDeMatrícula.jsp pestaña  1
    //imagen de disco para guardar...
    $("#ImgGuardarFolio").off().on('click', function () {
        $("#confirmFolio").text("");
        $("#confirmFolio").append("guardando...".fontsize(1).fontcolor("blue"));
        var folio = $("#caja_folio").val().trim();
        var aspirante = $("#idAspirante1").val();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        modificarFolioCENEVAL(folio, aspirante, usuario, contra);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña  1.2 y 3
    //en los input 
    //Se verifica que el tipo de dato que se ingrese sea correcto
    //y los campos no sean nulos en caso de ser necesario.
    ///**************
    $("#caja_datosExamen").blur(function () {
        var fechaYHora = $("#caja_datosExamen").val().trim();
        if (fechaYHora == "" || fechaYHora == null) {
            alert("Campo de examen vacio");
            $("#caja_datosExamen").css("background", "#E80000");
        } else {
            $("#caja_datosExamen").css("background", "");
        }
    });
    $("#caja_datosExamenMate").blur(function () {
        var fechaYHora = $("#caja_datosExamenMate").val().trim();
        if (fechaYHora == "" || fechaYHora == null) {
            alert("Campo de examen vacio");
            $("#caja_datosExamenMate").css("background", "#E80000");
        } else {
            $("#caja_datosExamenMate").css("background", "");
        }
    });
    $("#calleNoDDD").blur(function () {

        var calle = $("#calleNoDDD").val();
        if (calle == "" || calle == null) {
            $("#calleNoDDD").css("background", "#E80000");
        } else {
            $("#calleNoDDD").css("background", "");
            validaAlfaNum("calleNoDDD");
        }
    });
    $("#ColoniaDDD").blur(function () {

        var calle = $("#ColoniaDDD").val();
        if (calle == "" || calle == null) {
            $("#ColoniaDDD").css("background", "#E80000");
        } else {
            $("#ColoniaDDD").css("background", "");
            validaAlfaNum("ColoniaDDD");
        }
    });
    $("#caja_numInterior").blur(function () {

        validaNum("caja_numInterior");


    });
    $("#caja_numExterior").blur(function () {

        validaNum("caja_numExterior");


    });
    $("#ENumInterior").blur(function () {

        validaNum("ENumInterior");

    });
    $("#caja_numExterior").blur(function () {

        tryNum("caja_numExterior");
    });
    $("#ENumExterior").blur(function () {

        tryNum("ENumExterior");
    });
    $("#caja_cpdd").blur(function () {

        tryNum("caja_cpdd");
    });
    $("#caja_telCel").blur(function () {

        tryNum("caja_telCel");
    });
    $("#caja_telFijo").blur(function () {

        tryNum("caja_telFijo");
    });
    $("#ETelCT").blur(function () {

        tryNum("ETelCT");
    });
    $("#ETelFijoCT").blur(function () {

        tryNum("ETelFijoCT");
    });
    $("#ETelCelCT").blur(function () {

        tryNum("ETelCelCT");
    });
    $("#nomMadre").blur(function () {

        tryLetts("nomMadre");
    });
    $("#nomPadre").blur(function () {

        tryLetts("nomPadre");
    });
    $("#ccbeca").blur(function () {

        tryLetts("ccbeca");
    });
    $("#ECalle").blur(function () {
        var calle = $("#ECalle").val();
        if (calle == "" || calle == null) {
            $("#ECalle").css("background", "#E80000");
        } else {
            $("#ECalle").css("background", "");
            validaAlfaNum("ECalle");
        }

    });
    $("#ECTrabajo").blur(function () {
        var calle = $("#ECTrabajo").val();
        if (calle == "" || calle == null) {
            $("#ECTrabajo").css("background", "#E80000");
        } else {
            $("#ECTrabajo").css("background", "");
            validaAlfaNum("ECTrabajo");
        }

    });
    $("#EColonia").blur(function () {

        var calle = $("#EColonia").val();
        if (calle == "" || calle == null) {
            $("#EColonia").css("background", "#E80000");
        } else {
            $("#EColonia").css("background", "");
            validaAlfaNum("EColonia");
        }
    });
    $("#EContacto1").blur(function () {

        tryLetts("EContacto1");
    });
    function busq1() {
        limpiarBeansSeguimiento();

        var parametroInicial = $("#cajaporCURP").val();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        obtenerDatosSegumiento(usuario, contra, 2, parametroInicial);
    }
    function busq2() {
        var parametroInicial = $("#cajaCorreoElectronicoBusqueda").val();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        obtenerDatosSegumiento(usuario, contra, 1, parametroInicial);
    }
    function busq3() {
        var parametroInicial = $("#cajaporFicha").val();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        obtenerDatosSegumiento(usuario, contra, 3, parametroInicial);
    }

//busqueda cuando click por curp
    $("#cajaporCURP").off().on('keypress', function (e) {

        if (e.which == 13) {
            busq1();
        }
    });
    ///busqueda por curp
    $("#boton_busqueda1").click(function () {
        busq1();
    });
    //busqueda cuando click por correo electrónico
    $("#cajaCorreoElectronicoBusqueda").off().on('keypress', function (e) {

        if (e.which == 13) {
            busq2();
        }
    });
    //busqueda por correo electrónico
    $("#boton_busqueda2").click(function () {
        busq2();
    });
    //busqueda cuando click por preficha
    $("#cajaporFicha").off().on('keypress', function (e) {

        if (e.which == 13) {
            busq3();
        }
    });
    //busqueda por preficha
    $("#boton_busqueda3").click(function () {
        busq3();
    });


    //Seguimiento de Aspirantes
    function obtenerDatosSegumiento(usuario, contra, bandera, parametroInicial) {
        $("#spinPago1").show();
        $("#fondoSpin").show();
        $("#error").text(" ");
        $("#caja_ficha").val("0");
        jQuery.get("servletSeguimientoDelAlumno",
                {usuario: usuario, contra: contra, bandera: bandera, parametroInicial: parametroInicial},
                function (data) {
                    $("#contenedor_consulta").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp #contenedor_consulta");


                    intAsig = setInterval(function () {
                        if ($("#caja_ficha").val() !== undefined || $("#caja_ficha").val() !== "undefined" || $("#caja_ficha").val() !== "0") {

                            var estado = data.substring(0, 4);

                            if (estado == "bien") {
                                var preficha = data.substring(5, 6);
                                var pago = data.substring(7, 8);
                                var alta = data.substring(9, 10);
                                var clave = data.substring(11, 12);
                                var ficha = data.substring(13, 14);
                                if (preficha == 0) {
                                    $("#checkPreficha").hide();
                                    $("#noPreficha").show();
                                } else {
                                    if (preficha == 1) {
                                        $("#noPreficha").hide();
                                        $("#checkPreficha").show();
                                    }
                                }

                                if (pago == 0) {
                                    $("#checkPago").hide();
                                    $("#noPago").show();
                                } else {
                                    if (pago == 1) {
                                        $("#noPago").hide();
                                        $("#checkPago").show();
                                    }
                                }
                                if (alta == 0) {
                                    $("#checkRegistro").hide();
                                    $("#noRegistro").show();
                                } else {
                                    if (alta == 1) {
                                        $("#noRegistro").hide();
                                        $("#checkRegistro").show();
                                    }
                                }

                                if (clave == 0) {
                                    $("#checkFolio").hide();
                                    $("#noFolio").show();
                                } else {
                                    if (clave == 1) {
                                        $("#noFolio").hide();
                                        $("#checkFolio").show();
                                    }
                                }
                                if (ficha == 0) {
                                    $("#checkFicha").hide();
                                    $("#noFicha").show();
                                } else {
                                    if (ficha == 1) {
                                        $("#noFicha").hide();
                                        $("#checkFicha").show();
                                    }
                                }
                            } else {
                                $("#error").append(data.fontcolor("red"));
                            }


                            clearInterval(intAsig);
                            activar();
                        }

                    }, 500);
                    $("#spinPago1").hide();
                    $("#fondoSpin").hide();
                });
    }

    ///*********************************

    //Consultas AsignaciónDeMatrícula.jsp pestaña  2
    //Guarda los datos del domicilio y/o socioeconómicos
    //el parámetro opción marca sí se guardan los datos del domicilio,
    //los datos socioeconómicos o ambos. Dependiendo de los cambios que se
    //registraron en las variables declaradas desde la línea 481   
    function guardarDDSE(opcion, usuario, contra, preficha,
            estadoDomicilio, municipioDomicilio, localidadDomicilio, colonia, calle,
            numExterior, numInterior, cp, telCel, telFijo
            , padre, vivePadre, ocupacionPa
            , madre, viveMadre, ocupacionMa
            , maxEstudiosPadre, maxEstudiosMadre
            , zonaProcedencia, tipoCasa, depEconomicamente
            , viveCon, beca, ingresosTotales
            , oportunidades, noPersonasCasa, noCuartos, DependeDe) {

        $("#spinPago1").show();
        $("#fondoSpin").show();
        jQuery.get("servletGuardarSE", {opcion: opcion, usuario: usuario, contra: contra, preficha: preficha,
            estadoDomicilio: estadoDomicilio, municipioDomicilio: municipioDomicilio, localidadDomicilio: localidadDomicilio, colonia: colonia, calle: calle
            , numExterior: numExterior, numInterior: numInterior, cp: cp, telCel: telCel, telFijo: telFijo
            , padre: padre, vivePadre: vivePadre, ocupacionPa: ocupacionPa
            , madre: madre, viveMadre: viveMadre, ocupacionMa: ocupacionMa
            , maxEstudiosPadre: maxEstudiosPadre, maxEstudiosMadre: maxEstudiosMadre
            , zonaProcedencia: zonaProcedencia, tipoCasa: tipoCasa, depEconomicamente: depEconomicamente
            , viveCon: viveCon, beca: beca, ingresosTotales: ingresosTotales
            , oportunidades: oportunidades, noPersonasCasa: noPersonasCasa, noCuartos: noCuartos, DependeDe: DependeDe},
                function (data) {
                    $("#spinPago1").hide();
                    $("#fondoSpin").hide();
                    $("#contendorDomicilio").data("changed", false);
                    $("#contenedorDatosSE").data("changed", false);
                    $("#errorGuardarSE").text(" ");

                    if (data == "correcto") {
                        $("#errorGuardarSE").append("Se guardaron correctamente los datos.".fontsize(1).fontcolor("green"));
                        $("#errorGuardarSE").show();

                        if (opcion == 2 || opcion == 3) {
                            actualizarListasSE();
                        }
                    } else {
                        $("#errorGuardarSE").append("Ha ocurrido un error al guardar".fontsize(1).fontcolor("red"));
                        $("#errorGuardarSE").show();
                    }
                });
    }
    ;
    //Consultas AsignaciónDeMatrícula.jsp pestaña  3
    //Guarda los datos del contacto de emergencia del aspirante,
    //en caso de que hayan sido modificados
    function guardarCE(opcion,usuario, contra, preficha, nom, estado, municipio, colonia, calle, numInterior, numExterior, centroTrabajo, telCentroTrabajo, telFijo, telCel) {
        $("#spinPago1").show();
        $("#fondoSpin").show();
        jQuery.get("servletGuardarCE", {opcion:opcion,usuario: usuario, contra: contra, preficha: preficha, nom: nom, estado: estado, municipio: municipio
            , colonia: colonia, calle: calle, numInterior: numInterior
            , numExterior: numExterior, centroTrabajo: centroTrabajo, telCentroTrabajo: telCentroTrabajo, telFijo: telFijo, telCel: telCel},
                function (data) {
                    $("#spinPago1").hide();
                    $("#fondoSpin").hide();
                    $("#contenedorEmergencia").data("changed", false);
                    $("#errorGuardarCE").text(" ");
                    if (data == "correcto") {
                        $("#errorGuardarCE").append("Se guardaron correctamente los datos.".fontsize(1).fontcolor("green"));
                        $("#errorGuardarCE").show();
                    } else {
                        $("#errorGuardarCE").append("Ha ocurrido un error al guardar".fontsize(1).fontcolor("red"));
                        $("#errorGuardarCE").show();
                    }
                });
    }

    //Nueva Escuela
    //Para guardar datos
    function guardarNvaEscuela(usuario, contra, claveCCT, control, servicio, ambito
            , turno, estadoID, entidad, municipioID, localidadID, nombre
            , domicilio) {
        $("#spinPago1").show();
        $("#fondoSpin").show();
        jQuery.get("ServletEscuelas", {usuario: usuario, contra: contra,
            claveCCT: claveCCT, control: control,
            servicio: servicio, ambito: ambito, turno: turno, estadoID: estadoID,
            entidad: entidad, municipioID: municipioID, localidadID: localidadID, nombre: nombre,
            domicilio: domicilio},
                function (data) {

                    $("#spinPago1").hide();
                    $("#fondoSpin").hide();
                    $("#ladoIzqEscuelas").data("changed", false);
                    $("#ladoDerEscuelas").data("changed", false);
                    $("#nombreYDomNvaEsc").data("changed", false);
                    $("#resultadoNvaEscuela").text(" ");
                    if (data == "ninguno") {
                        $("#resultadoNvaEscuela").append("Se guardaron correctamente los datos.".fontsize(1).fontcolor("green"));
                        $("#resultadoNvaEscuela").show();
                        ActualizarSelectNvaEscuela();
                    } else {
//                $("#resultadoNvaEscuela").append("Ha ocurrido un error al guardar".fontsize(1).fontcolor("red"));
                        $("#resultadoNvaEscuela").append(("Ha ocurrido el siguiente error al intentar guardar los datos: " + data).fontsize(1).fontcolor("red"));
                        $("#resultadoNvaEscuela").show();
                    }
                });

    }

//Consultas AsignaciónDeMatrícula.jsp pestaña  2
    ////Guarda datos personales y/o escuela de procedencia
    //El parámetroo opcíón marca el tipo de guardado, dependiendo
    //de los cambios. si se registraron cambios en la parte de datos del aaspirante
    //y/o datos de la escuela de procedencia
//    function guardarDPEP(opcion, usuario, contra, preficha, ficha, referencia, ceneval, LFA, LFB, curp, paterno, fechaNac, sexo, edoCivil, correo, capDif, opcion1
//            , materno, pais, estado, municipio, ciudad
//            , sangre, opcion2, nombre, edad, opcion3
//            , estadoEP, tipoEscuela, escuela, claveEscuela
//            , Pinicio, Pfin, promedio, idAspirante)
    function guardarDPEP(opcion, usuario, contra
            , preficha, ficha, referencia, ceneval, LFA, LFB
            , curp, paterno, fechaNac, municipio, ciudad
            , correo, materno, pais, estado, capDif
            , nombre, edad, sexo, edoCivil, sangre
            , opcion1
            , estadoEP, municipioEP, tipoEscuela, escuela
            , claveEscuela, Pinicio, Pfin, promedio
            , idAspirante)
    {
        $("#spinPago1").show();
        $("#fondoSpin").show();
        jQuery.get("servletGuardarDP", {opcion: opcion, usuario: usuario, contra: contra
            , preficha: preficha, ficha: ficha, referencia: referencia, ceneval: ceneval, LFA: LFA, LFB: LFB
            , curp: curp, paterno: paterno, fechaNac: fechaNac, municipio: municipio, cuidad: ciudad
            , correo: correo, materno: materno, pais: pais, estado: estado, capDif: capDif
            , nombre: nombre, edad: edad, sexo: sexo, edoCivil: edoCivil, sangre: sangre
            , opcion1: opcion1
            , estadoEP: estadoEP, municipioEP: municipioEP, tipoEscuela: tipoEscuela, escuela: escuela
            , claveEscuela: claveEscuela, Pinicio: Pinicio, Pfin: Pfin, promedio: promedio
            , idAspirante: idAspirante},
                function (data) {
                    $("#spinPago1").hide();
                    $("#fondoSpin").hide();
                    $("#errorGuardarDP").text(" ");
                    if (data == "correcto") {
                        $("#errorGuardarDP").append("Se guardaron correctamente los datos.".fontsize(1).fontcolor("green"));
                        $("#errorGuardarDP").show();
                        $("#contenedorEscuelaPro").data("changed", false);
                        $("#contenedorDatosPersonales").data("changed", false);
                        $("#caja_datosExamenMate").data("changed", false);
                        $("#caja_datosExamen").data("changed", false);
                        $("#etiquetas_superior").data("changed", false);
                        if (opcion == 1 || opcion == 3) {
                            actualizar();
                        }
                    } else {
                        $("#errorGuardarDP").append("Ha ocurrido un error al guardar".fontsize(1).fontcolor("red"));
                        $("#errorGuardarDP").show();
                    }

                });
    }

    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Func´n asignada al botón de guardar
    //Manda a llamar la función de guardado si se registro algún cambio

    $("#boton_guardarDP").click(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var ceneval = $("#caja_folio").val();

        var preficha = $("#caja_noSolicitud").val();
        var ficha = $("#caja_ficha").val();
        var referencia = $("#caja_referencia").val();
        var LFA = $("#caja_datosExamen").val();
        var LFB = $("#caja_datosExamenMate").val();

        var curp = $("#cajaCURP").val();
        var paterno = $("#cajaAP").val().trim();
        var fechaNac = $("#cajaNacimiento").val();
        var municipio = $("#cajaMunicipio").find("option:selected").attr("id");
        var ciudad = $("#cajaCn").find("option:selected").attr("id");

        var correo = $("#cajaCORREO").val();
        var materno = $("#cajaAM").val();
        var pais = $("#cajaPais").find("option:selected").attr("id");
        var estado = $("#cajaEdo").find("option:selected").attr("id");
        var capDif = $("#cajaCapDif").find("option:selected").attr("id");

        var nombre = $("#cajaNombre").val();
        var edad = $("#cajaEdad").val();
        var sexo = $("#cajaSexo").find("option:selected").attr("id");
        var edoCivil = $("#cajaCivil").find("option:selected").attr("id");
        var sangre = $("#cajaSangre").find("option:selected").attr("id");

        var opcion1 = $("#estado3").find("option:selected").attr("id");

        var estadoEP = $("#cajaEstadoEP").val();
        var municipioEP = $("#cajaMpioEP").val();
        var tipoEscuela = $("#cajaTipoEscuela").find("option:selected").attr("id");
        var escuela = $("#cajaEscuela").find("option:selected").attr("id");

        var claveEscuela = $("#cajaClaveEscuela").val();
        var Pinicio = $("#cajaPInicio").val();
        var Pfin = $("#cajaPfin").val();
        var promedio = $("#cajaPromedio").val();

        var idAspirante = $("#idAspirante1").val();

        var opcion = 0;
        if ($("#contenedorEscuelaPro").data("changed") && $("#contenedorDatosPersonales").data("changed")) {
            opcion = 3;
        } else if ($("#caja_datosExamen").data("changed") || $("#caja_datosExamenMate").data("changed") || $("#contenedorDatosPersonales").data("changed")) {
            opcion = 1;
        } else if ($("#contenedorEscuelaPro").data("changed")) {
            opcion += 2;
        }


        //opcion =0 cuando no se ha hecho ningún cambio
        //opcion = 1 cuando sólo se registraron cambios en los datos personales del aspirante
        //opcion = 2 cuando sólo se registraron cambios en los datos de la escuela de procedencia.
        //opcion = 3 cuando se registraron cambios en toda la página
        guardarDPEP(opcion, usuario, contra
                , preficha, ficha, referencia, ceneval, LFA, LFB
                , curp, paterno, fechaNac, municipio, ciudad
                , correo, materno, pais, estado, capDif
                , nombre, edad, sexo, edoCivil, sangre
                , opcion1
                , estadoEP, municipioEP, tipoEscuela, escuela
                , claveEscuela, Pinicio, Pfin, promedio
                , idAspirante);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña  2
    //función que se asigna al botón de guarder de la página que se especifica
    //Manda a llamar el servlet para guardar datos
    function paraGuardar2() {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var preficha = $("#idAspirante2").val();

        var estadoDomicilio = $("#estadoDeAspirante").find("option:selected").attr("id");
        var municipioDomicilio = $("#caja_mun2").find("option:selected").attr("id");
        var localidadDomicilio = $("#caja_loc2").find("option:selected").attr("id");
        var colonia = $("#ColoniaDDD").val();
        var calle = $("#calleNoDDD").val();

        var numExterior = $("#caja_numExterior").val();
        var numInterior = $("#caja_numInterior").val();
        var cp = $("#caja_cpdd").val();
        var telCel = $("#caja_telCel").val();
        var telFijo = $("#caja_telFijo").val();

        var padre = $("#nomPadre").val();
        var vivePadre = $("#caja_vivePa").find("option:selected").attr("id");
        var ocupacionPa = $("#ocupacionPadre").find("option:selected").attr("id");

        var madre = $("#nomMadre").val();
        var viveMadre = $("#caja_viveMa").find("option:selected").attr("id");
        var ocupacionMa = $("#ocupacionMadre").find("option:selected").attr("id");

        var maxEstudiosPadre = $("#estudiosPadre").find("option:selected").attr("id");
        var maxEstudiosMadre = $("#estudiosMadre").find("option:selected").attr("id");

        var zonaProcedencia = $("#procedencia").find("option:selected").attr("id");
        var tipoCasa = $("#casaVive").find("option:selected").attr("id");
        var depEconomicamente = $("#cajaDepende").val();

        var viveCon = $("#viveCon").val();
        var beca = $("#ccbeca").val();
        var ingresosTotales = $("#ingresos").find("option:selected").attr("id");


        var oportunidades = $("#programaOportunidades").find("option:selected").attr("id");
        var noPersonasCasa = $("#habitantes").find("option:selected").attr("id");
        var noCuartos = $("#cuartosCasa").find("option:selected").attr("id");
        var DependeDe = $("#sustento").find("option:selected").attr("id");

        var opcion = 0;
        if ($("#contendorDomicilio").data("changed") && $("#contenedorDatosSE").data("changed")) {
            opcion = 3;
        } else if ($("#contendorDomicilio").data("changed")) {
            opcion = 1;
        } else if ($("#contenedorDatosSE").data("changed")) {
            opcion += 2;
        }

        //opcion = 0 cuando no se han registrado cambios
        //opcion = 1 cuando se han registrado cambios sólo en los datos del domicilio}
        //opcion = 2 cuando se han registrado cambios sólo en los datos socioeconómicos
        //opción = 3 cuando se han registrado cambios en ambos 

        guardarDDSE(opcion, usuario, contra, preficha,
                estadoDomicilio, municipioDomicilio, localidadDomicilio, colonia, calle,
                numExterior, numInterior, cp, telCel, telFijo
                , padre, vivePadre, ocupacionPa
                , madre, viveMadre, ocupacionMa
                , maxEstudiosPadre, maxEstudiosMadre
                , zonaProcedencia, tipoCasa, depEconomicamente
                , viveCon, beca, ingresosTotales
                , oportunidades, noPersonasCasa, noCuartos, DependeDe);
    }


    //Consultas AsignaciónDeMatrícula.jsp pestaña  2
    //función ligada directamente con el botón de guardar de la pestaña 2

    $("#boton_guardar2").click(function () {
        paraGuardar2();
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña  3
    //Manda a llamar el servlet de guardado de datos del contacto de emergencia
    //en caso de que se hayan registrado cambios

    $("#boton_guardar3").click(function () {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var preficha = $("#idAspirante3").val();

        var nom = $("#EContacto1").val();
        var estado = $("#EEstado").find("option:selected").attr("id");
        var municipio = $("#ECiudad").find("option:selected").attr("id");
        var colonia = $("#EColonia").val();
        var calle = $("#ECalle").val();
        var numInterior = $("#ENumInterior").val();

        var numExterior = $("#ENumExterior").val();
        var centroTrabajo = $("#ECTrabajo").val();
        var telCentroTrabajo = $("#ETelCT").val();
        var telFijo = $("#ETelFijoCT").val();
        var telCel = $("#ETelCelCT").val();

        var opcion = 0;
        //opcion = 0 cuando no se han registrado cambios
        //opcion = 1 cuando se han registrado cambios en los datos de Emergencia
        if ($("#contenedorEmergencia").data("changed")) {
            opcion = 1;
        }
        guardarCE(opcion,usuario, contra, preficha, nom, estado, municipio, colonia, calle, numInterior, numExterior, centroTrabajo, telCentroTrabajo, telFijo, telCel);
        ;
    });

    //altaYBusqueda.jsp
    //Mostras las pestañas
    $("#btn-tabs-2").click(function () {

//    $("#resultadoNvaEscuela").append("qweqweq");
        $("#resultadoNvaEscuela").hide();
    });
    //Acción del botón guardar
    $("#btnNvaEscuela").click(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var claveCCT = ObtenerValor("#cajaCCT");
        var control = ValorSeleccionado("#selectControl");
        var servicio = ValorSeleccionado("#selectServicio");
        var ambito = ValorSeleccionado("#selectAmb");

        var turno = ValorSeleccionado("#selectTurno");
        var edoId = ValorSeleccionado("#selectEnt");
        var entidad = ValorSeleccionado("#selectEnt");
        var municipio = ValorSeleccionado("#selectMun");
        var localidad = ValorSeleccionado("#selectLocalidad");

        var nombre = ObtenerValor("#nomNvaEsc");
        var domicilio = ObtenerValor("#domNvaEsc");

        if (claveCCT !== false && control !== false && servicio !== false && ambito !== false
                && turno !== false && edoId !== false && entidad !== false && municipio !== false
                && localidad !== false && nombre !== false && domicilio !== false) {

            guardarNvaEscuela(usuario, contra, claveCCT, control, servicio, ambito
                    , turno, edoId, entidad, municipio, localidad, nombre
                    , domicilio);
        } else {
            alert("Complete todos los campos");
        }

    });
    function  ObtenerValor(id) {
        var novalido = false;
        var valor = $(id).val();
        if (valor === '' || valor === null || valor === undefined || valor === '-Seleccione-' || valor === '--Selecione--' || valor === '--' || valor === 0 || valor === '0' || valor === "null" || valor === "s/n" || valor === "S/N" || valor === "S/n" || valor === "s/N") {
            $(id).css("border", "1px solid red");
//        alert("retorna: " + novalido);
            return novalido;
        } else {
            $(id).css("border", "");
            return  valor;
        }
    }
    function  ValorSeleccionado(id) {
        var novalido = false;
        var valor = $(id).find("option:selected").attr("id");
        if (valor === '' || valor === null || valor === undefined || valor === '-Seleccione-' || valor === '---Selecione---' || valor === 'SELECCIONE' || valor === 0 || valor === '0' || valor === "null" || valor === "s/n" || valor === "S/N" || valor === "S/n" || valor === "s/N") {
            $(id).css("border", "1px solid red");
//        alert("retorna: " + novalido);
            return novalido;
        } else {
            $(id).css("border", "");
            return  valor;
        }
    }
    //Consultas AsignaciónDeMatrícula.jsp pestaña  2 y 1
    //Actualiza opciones de listas de estado,municipio  o localidad
    //dependiendo del id que se especifique.

    function ListasEdoMunLocal(idSelect, filtro2, opcion, id, usuario, contra, nomLista, aliasLista, filtro) {

        jQuery.getJSON("servletActualizarListas",
                {idSelect: idSelect, filtro2: filtro2, opcion: opcion, id: id, usuario: usuario, contra: contra, nomLista: nomLista, aliasLista: aliasLista, filtro: filtro}
        , function (data) {
            Filtros(idSelect, data);
        });
    }

    $("#selectEnt").change(function () {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#selectEnt").find("option:selected").attr("id");

        if (id == 0) {
            alert("Elija un estado para continuar");
            $("#selectLocalidad").prop("disabled", true);
            $("#selectMun").prop("disabled", true);
        } else {
            ListasEdoMunLocal("#selectMun", 0, 3, id, usuario, contra, "municipio", "municipio", id);
            $("#selectMun").prop("disabled", false);
        }
    });


    $("#selectMun").change(function () {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#selectMun").find("option:selected").attr("id");

        if (id == 0) {
            alert("Elija un municipio para continuar");
            $("#selectLocalidad").prop("disabled", true);
        } else {
            ListasEdoMunLocal("#selectLocalidad", 0, 9, id, usuario, contra, "localidad", "localidad", id);
            $("#selectLocalidad").prop("disabled", false);
        }
    });

    $("#selectLocalidad").change(function () {
        var id = $("#selectLocalidad").find("option:selected").attr("id");
        if (id == 0) {
            alert("Elija una localidad para continuar");
        }
    });
//Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de país.
    //Al cambiar, cambia el select de estado
    //y se bloquea el de municipio y ciudad
    $("#cajaPais").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var pais = $("#cajaPais").find("option:selected").attr("id");
        ListasEdoMunLocal("#cajaPais", 1, 1, pais, usuario, contra, "nacionalidad", "nacionalidad", 0);
        if (pais == "MEX") {

            var id = $("#cajaEdo").find("option:selected").attr("id");
            ListasEdoMunLocal("#cajaEdo", 0, 2, id, usuario, contra, "estado", "estado", 0);
            $("#cajaMunicipio").prop("disabled", true);
            $("#cajaCn").prop("disabled", true);
            $("#cajaEdo").prop("disabled", false);
        } else {
            $("#cajaMunicipio").prop("disabled", true);
            $("#cajaCn").prop("disabled", true);
            $("#cajaEdo").prop("disabled", true);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de estado.
    //Al cambiar, cambia el select de municipio
    //y se bloquea el de ciudad
    $("#cajaEdo").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#cajaEdo").find("option:selected").attr("id");
        ListasEdoMunLocal("#cajaEdo", 1, 2, id, usuario, contra, "estado", "estado", 0);
        if (id == 0) {
            alert("seleccione una opción");
        } else {

            ListasEdoMunLocal("#cajaMunicipio", 0, 3, id, usuario, contra, "municipio", "municipio", id);
            $("#cajaMunicipio").prop("disabled", false);
            $("#cajaCn").prop("disabled", true);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 2
    //Select de estado(datos del domicilio).
    //Al cambiar, cambia el select de municipio
    //y se bloquea el de ciudad
    $("#estadoDeAspirante").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#estadoDeAspirante").find("option:selected").attr("id");
        ListasEdoMunLocal("#estadoDeAspirante", 1, 2, id, usuario, contra, "edoDD", "edoDD", 0);
        if (id == 0) {
            alert("seleccione una opción");
        } else {

            ListasEdoMunLocal("#caja_mun2", 0, 3, id, usuario, contra, "munDD", "munDD", id);
            $("#caja_mun2").prop("disabled", false);
            $("#caja_loc2").prop("disabled", true);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 3
    //Select de estado(datos del contacto de emergencia).
    //Al cambiar, cambia el select de municipio
    $("#EEstado").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#EEstado").find("option:selected").attr("id");
        ListasEdoMunLocal("#EEstado", 1, 2, id, usuario, contra, "edoE", "edoE", 0);
        if (id == 0) {
            alert("seleccione una opción");
        } else {

            ListasEdoMunLocal("#ECiudad", 0, 3, id, usuario, contra, "munE", "munE", id);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de municipio(datos personales del aspirante).
    //Al cambiar, cambia el select de municipio
    //y se bloquea el de ciudad
    $("#cajaMunicipio").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var idEDo = $("#cajaEdo").find("option:selected").attr("id");
        var id = $("#cajaMunicipio").find("option:selected").attr("id");
        ListasEdoMunLocal("#cajaMunicipio", 1, 3, id, usuario, contra, "municipio", "municipio", idEDo);
        if (id == 0) {
            alert("Seleccione una opción");
        } else {


            $("#cajaCn").prop("disabled", false);
            ListasEdoMunLocal("#cajaCn", 0, 9, id, usuario, contra, "localidad", "localidad", id);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 2
    //Select de municipio(datos del domicilio).
    //Al cambiar, cambia el select de municipio
    //y se bloquea el de ciudad
    $("#caja_mun2").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#caja_mun2").find("option:selected").attr("id");
        var idEDo = $("#estadoDeAspirante").find("option:selected").attr("id");
        ListasEdoMunLocal("#caja_mun2", 1, 3, id, usuario, contra, "munDD", "munDD", idEDo);
        if (id == 0) {
            alert("Seleccione una opción");
        } else {

            $("#caja_loc2").prop("disabled", false);
            ListasEdoMunLocal("#caja_loc2", 0, 9, id, usuario, contra, "locaDD", "locaDD", id);
        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 3
    //Select de municipio(datos del domicilio).
    $("#ECiudad").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#ECiudad").find("option:selected").attr("id");
        var idEDo = $("#EEstado").find("option:selected").attr("id");
        ListasEdoMunLocal("#ECiudad", 1, 3, id, usuario, contra, "munE", "munE", idEDo);
        if (id == 0) {
            alert("Seleccione una opción");
        } else {

        }
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de localidad
    $("#cajaCn").change(function () {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#cajaCn").find("option:selected").attr("id");
        var idLocal = $("#cajaMunicipio").find("option:selected").attr("id");
        ListasEdoMunLocal("#cajaCn", 1, 9, id, usuario, contra, "localidad", "localidad", idLocal);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 2
    //Select de localidad
    $("#caja_loc2").change(function () {
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var id = $("#caja_loc2").find("option:selected").attr("id");
        var idLocal = $("#caja_mun2").find("option:selected").attr("id");
        ListasEdoMunLocal("#caja_loc2", 1, 9, id, usuario, contra, "locaDD", "locaDD", idLocal);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select carrera1
    $("#estado3").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var opcion1 = $(this).find("option:selected").attr("id");
        var opcion2 = $("#estado").find("option:selected").attr("id");
        var opcion3 = $("#estado2").find("option:selected").attr("id");
        ActualizarSelect(opcion1, opcion2, opcion3, usuario, contra);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de carrera2
    $("#estado").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var opcion2 = $(this).find("option:selected").attr("id");
        var opcion1 = $("#estado3").find("option:selected").attr("id");
        var opcion3 = $("#estado2").find("option:selected").attr("id");
        ActualizarSelect(opcion1, opcion2, opcion3, usuario, contra);
    });
    //Consultas AsignaciónDeMatrícula.jsp pestaña 1
    //Select de carrera3
    $("#estado2").change(function () {

        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        var opcion3 = $(this).find("option:selected").attr("id");
        var opcion2 = $("#estado").find("option:selected").attr("id");
        var opcion1 = $("#estado3").find("option:selected").attr("id");
        ActualizarSelect(opcion1, opcion2, opcion3, usuario, contra);
    }
    );
    //Inicio botón ingresar

    //Inicio botón ingresar
    jQuery("#Boton_acceder").click(function () {
        iniciarSesion();
    });

    //Fucnión para que inicie con enter
    $("#caja_pass").keypress(function (e) {
        if (e.which == 13) {
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

    // PeriodoDelRegistroDeAspirante.jsp
    //Aceptar para cambio de periodo
    jQuery("#btn_aceptar").click(function () {
        var fechaInicio = $("#fechaInicio").val();
        var fechaFin = $("#fechaFin").val();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        convertirFecha(fechaInicio, fechaFin, usuario, contra);
    });
    //AsignaciónDeMatrícula.jsp
    //Boton de búsqueda de preficha
    jQuery("#botonPreficha").off().on('click', function () {
        $.get("servletLimpiarBeans");
        $("#guardarS").val("");
        $("#guardarE").val("");
        var nuevo = $("#caja_boton_busquedapreficha").val().trim();
        $('#idAspirante').val(nuevo);
        if (tryNum("caja_boton_busquedapreficha")) {
            var preficha = $("#caja_boton_busquedapreficha").val();
            var usuario = $("#usuarioOculto").val();
            var contra = $("#contraOculta").val();
            dpPRueba(preficha.trim(), usuario, contra);
        } else {

//            }
        }
    });
    //sin usar
    $("#btn_terminar").click(function () {
        $('#fondo3').hide();
        $('#emergente3').hide();
        jQuery("#correo_asp").append(" ");
    });
    //AsignaciónDeMatrícula.jsp pestaña1
    //función para cuando das click sobre "datos de la ficha"
    $("#df").off().on('click', function () {
        if ($("#folioAM").val() != "") {

            if ($("#contendorDomicilio").data("changed") || $("#contenedorDatosSE").data("changed")
                    || $("#contenedorEmergencia").data("changed")) {
                alert("Por favor, guarde sus datos");
            } else {
                $("#contenedor_pestanas").load("Paginas/Consultas/Pestana1.jsp");
                $("#ece").css("background", "gainsboro");
                $("#ddatoss").css("background", "gainsboro");
                $("#df").css("background", "#a7cce5");
                setTimeout(function () {
                    var folio = $("#folioAM").val().trim();
                    var libCen = $("#libCenAM").val();
                    if (folio == "" || folio == null || folio == "NO ASIGNADA") {

                    } else {
                        $("#imgRojaImprimir").hide();
                        $("#botonVerde").show();
                    }

                    if (libCen === "ninguno0") {

                    } else {
                        if (libCen === "ninguno1") {
                            $("#imgFolioRojo").hide();
                            $("#imgFolioVerde").show();
                        }
                    }
                    var preficha = $("#caja_boton_busquedapreficha").val();
                    registroCeneval(preficha.trim());


                }, 500);
            }
        }
    });
    $("#ddatoss").off().on('click', function () {
        if ($("#folioAM").val() != null) {
            if ($("#contenedorEmergencia").data("changed") ||
                    $("#caja_datosExamen").data("changed") || $("#caja_datosExamenMate").data("changed")
                    || $("#contenedorDatosPersonales").data("changed") || $("#contenedorEscuelaPro").data("changed")) {
                alert("Por favor, guarde sus datos.");
            } else {

                $("#ece").css("background", "gainsboro");
                $("#ddatoss").css("background", "#a7cce5");
                $("#df").css("background", "gainsboro");
                var nuevo = $("#idAspirante").val();
                var viejo = $("#guardarS").val();
                if (nuevo === viejo) {
                    $("#contenedor_pestanas").load("Paginas/Consultas/Pestana2.jsp");
                } else {
                    if (tryNum("caja_noSolicitud")) {
                        cargar();
                        var aspirante = $("#caja_noSolicitud").val();
                        var usuario = $("#usuarioOculto").val();
                        var contra = $("#contraOculta").val();
                        ddomicilio(aspirante, usuario, contra);
                    }
                }
            }
        }
    });
    $("#ece").off().on('click', function () {
        if ($("#folioAM").val() != null) {
            if ($("#contendorDomicilio").data("changed") || $("#contenedorDatosSE").data("changed") ||
                    $("#caja_datosExamen").data("changed") || $("#caja_datosExamenMate").data("changed")
                    || $("#contenedorDatosPersonales").data("changed") || $("#contenedorEscuelaPro").data("changed")) {
                alert("Por favor, guarde sus datos.");
            } else {
                $("#ece").css("background", "#a7cce5");
                $("#ddatoss").css("background", "gainsboro");
                $("#df").css("background", "gainsboro");
                var nuevo = $("#idAspirante").val();
                var viejo = $("#guardarE").val();
                if (nuevo === viejo) {
                    $("#contenedor_pestanas").load("Paginas/Consultas/Pestana3.jsp");
                } else {
                    if (tryNum("caja_noSolicitud")) {
                        var aspirante = $("#caja_noSolicitud").val();
                        var usuario = $("#usuarioOculto").val();
                        var contra = $("#contraOculta").val();
                        emergencia(aspirante, usuario, contra);
                    }
                }
            }
        }
    });
    $("#btn_aceptar2").click(function () {
        $("#btn_aceptar2").hide();
        $("#btn_desaparecido").hide();
        $("#spinInicio").show();
        var nombre = $('#cajaNombre').val() + " " + $('#cajaAP').val() + " " + $('#cajaAM').val();
        var matricula = $('#caja_ficha').val();
        var carrera = $('#estado3').find("option:selected").attr("value");
        var fechaAsignacion = "6/06/2014";
        var lugarYFecha = "Q2 13/05/2015 09:00 hrs.";
        var correo = $('#cajaCORREO').val();
        var preficha = $('#caja_noSolicitud').val();
        enviarCorreo(correo, nombre, preficha, matricula, carrera, fechaAsignacion, lugarYFecha);
    });



});

///////////número de correos

function actualizarDatosPersonales(aspirante, usuario, contra, matriculaNueva, cenevalNuevo, nombreNuevo, paternoNuevo, maternoNuevo, op1Nueva, op2Nueva, op3Nueva) {

    jQuery.get("servletActualizarDP",
            {aspirante: aspirante, usuario: usuario, contra: contra, matriculaNueva: matriculaNueva, cenevalNuevo: cenevalNuevo, nombreNuevo: nombreNuevo, paternoNuevo: paternoNuevo, maternoNuevo: maternoNuevo, op1Nueva: op1Nueva, op2Nueva: op2Nueva, op3Nueva: op3Nueva},
            function (data) {

                $("#confirmacionGuardado").append(data);
                $("#emergenteGuardar").hide();
                $("#fondoGuardar").hide();
            });
}


function enviarCorreo(correo, nombre, preficha, matricula, carrera, fechaAsignacion, lugarYFecha) {
    $("#spinInicio").show();
    $("#botonLiberar").hide;
    jQuery.get("servletEnviarCorreo",
            {correo: correo, nombre: nombre, preficha: preficha, matricula: matricula, carrera: carrera, fechaAsignacion: fechaAsignacion, lugarYFecha: lugarYFecha},
            function (data) {
                setTimeout(function ()
                {

                    if (data === "correcto") {
                        $("#correo_asp").val(" ");
                        $("#correo_asp1").val(" ");
                        $("#notificacion").append("El correo se ha enviado exitosamente.");
                        $("#btn_aceptar2").hide();
                        $("#btn_desaparecido").hide();
                        $("#btn_terminar").show();
                        $("#spinInicio").hide();
                    } else {
                        jQuery("#correo_asp").append(data);
                    }

                }, 300
                        );
            });
}

function enviarCorreoDeCENEVAL(usuario, contra, nombre, correo, folio) {

    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");
    $("#confirm").text("");
    $("#confirm").append("enviando...".fontsize(1).fontcolor("red"));
    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");
    jQuery.get("servletCorreoCENEVAL", {usuario: usuario, contra: contra, nombre: nombre, correo: correo, folio: folio},
            function (data) {
                setTimeout(function ()
                {
                    if (data === "correcto") {
                        $("#confirm").text("");
                        $("#referencia1").append("El correo se ha enviado correctamente.\n".fontsize(5).fontcolor("black"));
                        $("#btn_terminarCENEVAL").show();
                        $("#spinCorreoPago").hide();
                        $("#botonLiberar").show();
                        $("#confirm").append("enviado...".fontsize(1).fontcolor("green"));
                    } else {
                        $("#referencia2").text(" ");
                        $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
                        $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante.\n");
                        $("#aspiante").append("Por favor, intenta de nuevo desde la opción de 'Consulta de Datos'");
                        $("#btn_terminarCENEVAL").show();
                        $("#spinCorreoPago").hide();
                        $("#confirm").text("");
                        $("#confirm").append("no enviado...".fontsize(1).fontcolor("red"));
                    }
                    $("#emergenteReferencia").show();
                    $("#fondoReferencia").show();
                }, 300
                        );
            });
}
function enviarAmbosCorreos(usuario, contra, nombre, correo, folio) {

    var carrera = $("#estado3").val();
    var check = 0;
    var check2 = 0;

    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#caja_fichaDPLP").text(" ");

    $("#aspiante").text(" ");
    $("#caja_fichaDPLP").text(" ");
    jQuery.get("/Modulo_Administrador/servletCorreoPagoProcesad", {correo: correo, nombreAsp: nombre, carrera: carrera, ficha: folio},
            function (data) {

                if (data == "correcto") {

                    $("#folioDPLP").text("");
                    document.getElementById("folioDPLP").style.color = "green";
                    $("#imgDisco").hide();
                    $("#imgnVerificado").show();
                    $("#imgFolioVerde").show();
                    $("#imgFolioRojo").hide();
                    $("#folioDPLP").text("Enviado");

                } else {
                    $("#folioDPLP").text("");
                    $("#folioDPLP").text("Ningún correo fue enviado, sin embargo el pago y el registro fueron dados de alta.");
                    $("#imgDisco").hide();
                    $("#imgnVerificado").show();
                }



            });

    jQuery.get("servletCorreoCENEVAL", {usuario: usuario, contra: contra, nombre: nombre, correo: correo, folio: folio},
            function (data) {

                if (data == "correcto") {
                    $("#folioDPLP").text("");
                    document.getElementById("folioDPLP").style.color = "green";
                    $("#imgDisco").hide();
                    $("#imgnVerificado").show();

                    $("#folioDPLP").text("Enviado");
                } else {
                    $("#folioDPLP").text("");
                    $("#folioDPLP").text("Ningún correo fue enviado, sin embargo el pago y el registro fueron dados de alta.");
                    $("#imgDisco").hide();
                    $("#imgnVerificado").show();
                }


            });


}

$("#liberacion").click(function () {
    jQuery.get("servletLimpiarBeanReferencia");
});
function enviarCorreoPagoProcesado(correo, nombreAsp) {
    $('#fondo2').modal({
        backdrop: "static"
    });
    var ficha2 = $("#caja_fichaDPLP").val();
    var carrera = $("#caja_carreraDPLP").val();
    $("#liberation").hide();
    $("#cenevalI").hide();
    $("#btn_cancelarEmergente").hide();
    jQuery.get("/Modulo_Administrador/servletCorreoPagoProcesad", {correo: correo, nombreAsp: nombreAsp, carrera: carrera, ficha: ficha2},
            function (data) {

                setTimeout(function ()
                {
                    if (data) {
                        $("#referencia1").append("Se ha notificado al aspirante que el pago se ha procesado exitosamente.\n");
                        $("#referencia2").append("Recuerda que para continuar con el proceso debes registrar al aspirante en CENEVAL.");
                    } else {
                        $("#referencia2").text(" ");
                        $("#referencia2").append("\n\n" + data.fontsize(2).fontcolor("red"));
                        $("#referencia1").append("\n\nHa ocurrido un error al notificar al aspirante, porfavor procesa el pago una vez más para continuar.\n");
                    }
                    enviarPago();
                }, 300);
            });
}
function hidemodal() {
    $("#fondo2").modal('hide');
}

function enviarPago() {

    $("#btn_cancelarEmergente").show();
    $("#liberation").show();



}

function emergencia(aspirante, usuario, contra) {

    cargar();
    $("#contenedor_pestanas").load("Paginas/Consultas/Pestana3.jsp");
    jQuery.get("servletEmergencia",
            {aspirante: aspirante, usuario: usuario, contra: contra},
            function (data) {
                $("#contenedor_pestanas").load("Paginas/Consultas/Pestana3.jsp");
                setTimeout(function ()
                {
                    var registroActual = $("#idAspirante").val();
                    comprobarCarga(registroActual, 2);
                    if (data == "ninguno") {


                    } else {
                        $("#errorCE").css("display", "block");
                        $("#errorCE").append(data.fontsize(3).fontcolor("red"));
                        $("#errorCE").show();
                    }

                }, 1000
                        );
                activar();
            });
}

function ddomicilio(aspirante, usuario, contra) {

    cargar();
    $("#contenedor_pestanas").load("Paginas/Consultas/Pestana2.jsp");
    jQuery.get("servletDomicilioSocioeconomicos",
            {aspirante: aspirante, usuario: usuario, contra: contra},
            function (data) {
                $("#contenedor_pestanas").load("Paginas/Consultas/Pestana2.jsp");
                setTimeout(function ()
                {
                    var registroActual = $("#idAspirante").val();
                    comprobarCarga(registroActual, 1);
                    if (data == "ninguno") {

                    } else {

                        $("#errorDD").css("display", "block");
                        $("#errorDD").append(data.fontsize(3).fontcolor("red"));
                    }

                }, 1000

                        );
                activar();
            });
}

function comprobarCarga(registroActual, id) {
    jQuery.get("servletDecidirCarga",
            {registroActual: registroActual},
            function (data) {
                if (id === 1) {
                    $("#guardarS").val(data);
                } else {
                    if (id === 2) {
                        $("#guardarE").val(data);
                    }
                }
            });
}



function cargar() {
    $("#barrita_de_fondo").show();
    $("#contenedor_pestanas").hide();
    $("#Pestanas_info").css("background", "#c5dbec");
    $("#mensajeInicio").hide();
}

function activar() {
    $("#barrita_de_fondo").hide();
    $("#contenedor_pestanas").show();
    $("#Pestanas_info").css("background", "white");
    $("#mensajeInicio").hide();
}




function dpPRueba(preficha, usuario, contra) {

    cargar();
    $.ajax({url: "/Modulo_Administrador/servletDatosPersonales",
        type: "GET",
        async: false,
        data: {preficha: preficha, usuario: usuario, contra: contra},
        success: function (data) {
            $("#folioAM").val(undefined);
            $("#contenedor_pestanas").load("Paginas/Consultas/Pestana1.jsp");
            $("#et_periodo").show();
            $("#paraError").text(" ");
            setTimeout(function () {
                if (data == "0") {
                    // $("#contenedor_pestanas").load("Paginas/Consultas/AspiranteSinPagoProcesado.jsp");
                    activar();
                } else if (data == "ninguno1" || data == "ninguno0") {

                    Amatricula();
                } else {
                    $("#folioAM").val(undefined);
                    $("#contenedor_pestanas").load("Paginas/Consultas/AspiranteNoExiste.jsp");
                    activar();
                }
            });
        }});
    registroCeneval(preficha);
}
function registroCeneval(preficha) {

    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    pagoYcen(usuario, contra, 3, preficha);
}
function pagoYcen(usuario, contra, bandera, parametroInicial) {

    jQuery.get("servletSeguimientoDelAlumno",
            {usuario: usuario, contra: contra, bandera: bandera, parametroInicial: parametroInicial},
            function (data) {
                $("#contenedor_consulta").load("Paginas/Consultas/SeguimientoDeAlumnos.jsp #contenedor_consulta");

                var estado = data.substring(0, 4);
                if (estado == "bien") {

                    var pago = data.substring(7, 8);
                    var alta = data.substring(9, 10);


                    if (pago == 0) {
                        $("#imgProhibido").show();
                        $("#imgFolioRojo").show();


                        $("#imgDisco").hide();
                        $("#folioDPLP").text("");
                        $("#folioDPLP").text("Aún falta liberar el pago.");
                    } else {
                        if (pago == 1) {

                            $("#imgProhibido").hide();
                            $("#imgFolioRojo").hide();
                            $("#imgFolioVerde").show();


                        }
                        if (alta == 0) {

                            $("#imgDisco").show();
                        } else {
                            if (alta == 1) {

                                $("#imgDisco").hide();
                                $("#folioDPLP").text("");
                                $("#folioDPLP").text("Notificación de Registro en Ceneval");
                                $("#imgnVerificado").show();

                            }
                        }
                    }




                } else {
                    $("#error").append(data.fontcolor("red"));
                }





            });
}
function cenevalGuardar() {


    $('#fondo2').modal({
        backdrop: "static"
    });

    $("#ref").text(" ");
    $("#referencia1").text("");
    $("#referencia2").text(" ");
    $("#aspiante").text(" ");
    $("#cenevalI").show();

    $("#liberation").hide();
    $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
    $("#referencia1").append("Al seleccionar esta casilla se enviaran dos correos al aspirante,".fontsize(3).fontcolor("black"));
    $("#aspiante").append("el primero indicando que su pago ya ha sido procesado y el segundo comunicandole que debe registrarse en CENEVAL.".fontsize(3).fontcolor("black"));
    $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));


}


function convertirFecha(fechaInicio, fechaFin, usuario, contra) {

    jQuery.get("servletPeriodo",
            {fechaInicio: fechaInicio, fechaFin: fechaFin, usuario: usuario, contra: contra},
            function (data) {
                $("#Asig_Contenedor").load("Paginas/PeriodoDeRegistro/DatosGuardadosExitosamente.jsp");
                $("#fondo2").hide();
                $("#emergente2").hide();
            }
    );
}
function registrarAltaCENEVAL(idAspirante, usuario, contra, folio2) {
    idAspirante = $("#idAsp").val();
    $("#btn_cancelarEmergente").show();
    var nombre = $("#caja_nomDPLP").val() + " " + $("#caja_apDPLP").val() + " " + $("#caja_amDPLP").val();
//    var folio = $("#caja_fichaDPLP").val();
    var correo = $("#caja_correoDPLP").val();
    $("#ref").text("");
    $("#referencia1").text("");
    $("#referencia2").text("");
    $("#aspiante").text("");
    $("#cenevalI").hide();
    $("#spinInicio").show();
    jQuery.get("servletRegistrarAltaCENEVAL",
            {usuario: usuario, contra: contra, idAspirante: idAspirante},
            function (data) {

                if (data == 0) {
                    enviarCorreoDeCENEVAL(usuario, contra, nombre, correo, folio2);
                    $("#botonLiberar").show();
                    $("#imgnVerificado").show();
                    $("#imgnNoRegistrado").hide();
                    $("#referencia1").append("Enviado".fontsize(3).fontcolor("green"));
                    $("#imgDisco").hide();
                    $("#spinInicio").hide();
                    setTimeout(function () {

                        $('#fondo2').modal("hide");
                    }, 1000);
                } else {
                    $("#errorDPLP").text(" ");
                    $("#errorDPLP").css("display", "block");
                    $("#referencia1").append(data.fontsize(3).fontcolor("red"));
                    $("#cenevalI").show();
                    $("#botonLiberar").show();
                    $("#imgnVerificado").hide();
                    $("#imgnNoRegistrado").show();
                }

            });
}
function registrarAltaCENEVAL2(idAspirante, usuario, contra, folio2) {

    idAspirante = $("#idAspirante1").val();
    var nombre = $("#cajaNombre").val() + " " + $("#cajaAP").val() + " " + $("#cajaAM").val();
    var folio = $("#caja_ficha").val();
    var correo = $("#cajaCORREO").val();

    jQuery.get("servletRegistrarAltaCENEVAL",
            {usuario: usuario, contra: contra, idAspirante: idAspirante},
            function (data) {

                if (data === "ninguno") {
                    enviarAmbosCorreos(usuario, contra, nombre, correo, folio2);
                    $("#botonLiberar").show();

                    $("#imgnNoRegistrado").hide();


                    $('#fondo2').modal("hide");
                } else {
                    $("#errorDPLP").text(" ");
                    $("#errorDPLP").css("display", "block");
                    $("#referencia1").append(data.fontsize(3).fontcolor("red"));

                    $("#botonLiberar").show();
                    $("#imgnVerificado").hide();
                    $("#imgnNoRegistrado").show();
                }

            });

}

function liberarPago(usuario, contra, referencia) {

    jQuery.get("servletLiberacionDePago",
            {usuario: usuario, contra: contra, referencia: referencia},
            function (data) {

//                var errorBD = data.substring(0, 7);
//                var opcionCHEck = data.substring(7, 8);
                var opcionCHEck = data;
//                if (errorBD === "ninguno") {
                if (opcionCHEck == 0 || opcionCHEck == 1) {
                    $("#MargenYEncabezado").load("Paginas/Consultas/LiberacionDePago.jsp");
                    setTimeout(function ()
                    {
                        var correo = $("#caja_correoDPLP").val();
                        var nombreAsp = $("#caja_nomDPLP").val() + " " + $("#caja_apDPLP").val() + " " + $("#caja_amDPLP").val();
                        if (opcionCHEck == 1) {
                            $("#imgnVerificado").show();
                            $("#imgnNoRegistrado").hide();
                        } else if (opcionCHEck == 0) {
                            $("#imgnVerificado").hide();
                            $("#imgnNoRegistrado").show();
                        }
                        enviarCorreoPagoProcesado(correo, nombreAsp);
                    }, 300
                            );
                } else {
                    $("#errorDPLP").text(" ");
                    $("#errorDPLP").css("display", "block");
                    $("#errorDPLP").append(data.fontsize(3).fontcolor("red"));
                    $("#botonLiberar").show();

                }


            });
}

function validarUsuario(usuario, contra) {
    $("#Boton_acceder").hide();
    $("#btn_cancelarEmergente").show();
    jQuery.get("servletInicio",
            {usuario: usuario, contra: contra},
            function (data) {
                $("#consultarError").text(" ");
                if (data == "correcto") {

                    location.replace("inicioSesion_administrador.jsp?session=c");
                    $(window).scroll(0, 0);
                } else {

                    $("#consultarError").append(data);
                    $("#Boton_acceder").show();
                    $("#spinInicio").hide();
                }
            });
}


$("#inicio").click(function () {

    $("#consultas").next().slideUp();
    $("#estadisticas").next().slideUp();
    $("#ayuda").next().slideUp();
    if (!$(this).next().is(":visible"))
    {
        $(this).next().slideDown();
    }


});
$("#consultas").click(function () {

    $("#estadisticas").next().slideUp();
    $("#ayuda").next().slideUp();
    if (!$(this).next().is(":visible"))
    {
        $(this).next().slideDown();
    }

});
$("#estadisticas").click(function () {
    $("#consultas").next().slideUp();
    $("#ayuda").next().slideUp();
    if (!$(this).next().is(":visible"))
    {
        $(this).next().slideDown();
    }


});
$("#periodo").click(function () {

    $("#consultas").next().slideUp();
    $("#estadisticas").next().slideUp();
    $("#ayuda").next().slideUp();
    if (!$(this).next().is(":visible"))
    {
        $(this).next().slideDown();
    }
});
$("#ayuda").click(function () {

    $("#consultas").next().slideUp();
    $("#estadisticas").next().slideUp();
    if (!$(this).next().is(":visible"))
    {
        $(this).next().slideDown();
    }
});
function ServletListas(filtro2, opcion, id, usuario, contra, nomLista, aliasLista, filtro) {

    jQuery.get("servletActualizarListas",
            {filtro2: filtro2, opcion: opcion, id: id, usuario: usuario, contra: contra, nomLista: nomLista, aliasLista: aliasLista, filtro: filtro}
    , function (data) {


    });
}


function ActualizarListasFijas(op, id, aliasLista) {
    jQuery.get("servletActualizarListasFijas",
            {op: op, id: id, aliasLista: aliasLista});
}


function ActualizarSelect(opcion1, opcion2, opcion3, usuario, contra) {

    jQuery.getJSON("servletActualizarSelects",
            {opcion1: opcion1, opcion2: opcion2, opcion3: opcion3, usuario: usuario, contra: contra},
            function (data) {
                var id;
                $("#estado").html("");
                $("#estado2").html("");
                $("#estado3").html("");
                for (var lista in data) {
                    if (lista === "op1") {
                        id = "#estado3";
                    } else if (lista === "op2") {
                        id = "#estado";
                    } else if (lista === "op3") {
                        id = "#estado2";
                    }
                    var cortar = data[lista];
                    for (var nombre in cortar) {
                        var txt = cortar[nombre].claveCarrera;
                        var t = cortar[nombre].nombre;
                        $(id).append("<option  id='" + txt + "'  value='" + t + "'>" + t + "</option>");
                    }

                }


            });
}

function Filtros(id, data) {
    $(id).html("");
    $.each(data, function (index, item) {
        if (id == "#cajaPais") {
            var txt = item.idPais;
            var t = item.nombre;
            $(id).append("<option  id='" + txt + "'  value='" + t + "'>" + t + "</option>");
        } else {
            var txt = item.claveCarrera;
            var t = item.nombre;
            $(id).append("<option  id='" + txt + "'  value='" + t + "'>" + t + "</option>");
        }
    });
}
function notificaciones() {
    $(window).scroll(0, 0);
    limpiarBeansSeguimiento();
    $("#MargenYEncabezado").load("Paginas/Consultas/Notificaciones.jsp");
}
//CLICK EN OPCIÓN ESCUELAS
function escuelas() {

    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Escuelas/altaYBusqueda.jsp");

    escuelasInterval = setInterval(function () {
        var sinDefinir = typeof $("#introEscuelas").val();
        if (sinDefinir !== undefined) {
            buscarEscuelas("#selectEdos", usuario, contra);
            clearInterval(escuelasInterval);

        }
    }, 500);
}

function buscarEscuelas(id, usuario, contra) {
    jQuery.getJSON("servletEstados", {usuario: usuario,
        contra: contra},
            function (data) {
                Filtros(id, data);
            });
}


function actualizar() {
    var id = $("#cajaSexo").find("option:selected").attr("id");
    ActualizarListasFijas(2, id, "sexo");
    var id1 = $("#cajaCivil").find("option:selected").attr("id");
    ActualizarListasFijas(3, id1, "civil");
    var id2 = $("#cajaCapDif").find("option:selected").attr("id");
    ActualizarListasFijas(1, id2, "capacidad");
    var id3 = $("#cajaSangre").find("option:selected").attr("id");
    ActualizarListasFijas(12, id3, "sangre");
}
/////////////////////// actualizar beans ///////////////////


$("#cajaEstadoEP").change(function () {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var id = $("#cajaEstadoEP").find("option:selected").attr("id");

    ServletListas(1, 2, id, usuario, contra, "estadoEP", "estadoEP", 0);

});

$("#cajaTipoEscuela").change(function () {
    var id = $("#cajaTipoEscuela").find("option:selected").attr("id");

    ActualizarListasFijas(13, id, "tEscuela");
});

$("#cajaEscuela").change(function () {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var id = $("#cajaEscuela").find("option:selected").attr("id");

    ServletListas(1, 8, id, usuario, contra, "escuela", "escuela", 0);

});

////////////////////////////////////////////////////////////////////////

$("#btn_contRef").click(function () {
    $("#emergenteReferencia").hide();
    $("#fondoReferencia").hide();
    $("#botonLiberar").show();
    $("#spinPago1").hide();
    $("#fondoSpin").hide();
});

$("#imgnNoRegistrado").off().on('click', function () {

    var valCaja = $("#idAsp").val();
    if (valCaja == null || valCaja == "") {

    } else {
        $('#fondo2').modal({
            backdrop: "static"
        });

        $("#ref").text(" ");
        $("#referencia1").text("");
        $("#referencia2").text(" ");
        $("#aspiante").text(" ");
        $("#cenevalI").show();

        $("#liberation").hide();
        $("#ref").append("Apreciable administrador,".fontsize(3).fontcolor("black"));
        $("#referencia1").append("Al seleccionar esta casilla se enviará un correo al aspirante".fontsize(3).fontcolor("black"));
        $("#aspiante").append("indicando que debe registrarse en CENEVAL.".fontsize(3).fontcolor("black"));
        $("#referencia2").append("Por favor, confirme su acción.".fontsize(3).fontcolor("black"));

    }
});

$("#cenevalI").off().on('click', function () {

    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var idAspirante = $("#idAsp").val();
    var folio = $("#caja_fichaDPLP").val();


    registrarAltaCENEVAL(idAspirante, usuario, contra, folio);
});
$("#cenevalI2").off().on('click', function () {

    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var idAspirante = $("#idAspirante1").val();
    var folio = $("#caja_ficha").val();

    registrarAltaCENEVAL2(idAspirante, usuario, contra, folio);
});

// Tooltip only Text
$('.masterTooltip').hover(function () {
// Hover over code
    var title = $(this).attr('title');
    $(this).data('tipText', title).removeAttr('title');
    $('<p class="tooltip"></p>')
            .text(title)
            .appendTo('body')
            .fadeIn('slow');
}, function () {
// Hover out code
    $(this).attr('title', $(this).data('tipText'));
    $('.tooltip').remove();
}).mousemove(function (e) {
    var mousex = e.pageX + 20; //Get X coordinates
    var mousey = e.pageY + 10; //Get Y coordinates
    $('.tooltip')
            .css({top: mousey, left: mousex})
});
$("#imgFolioVerde").off().on('click', function () {
    var nombre = $("#cajaNombre").val() + " " + $("#cajaAP").val() + " " + $("#cajaAM").val();
    var correo = $("#cajaCORREO").val();
    var folio = $("#caja_ficha").val();
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var curp = $("#curp").val();
    enviarCorreoDeCENEVAL(usuario, contra, nombre, correo, folio);
});
$("#botonLiberar").off().on('click', function () {
    var referencia = $("#caja_boton_liberacionReferencia").val().trim();
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var ficha = $("#caja_fichaDPLP").val();
    if (referencia === "" || referencia === null) {
        alert("Campo de referencia vacio.");
    } else {
        if (validaAlfaNum("caja_boton_liberacionReferencia")) {

            liberarPago(usuario, contra, referencia);
        }
    }


});
$("#caja_boton_liberacionReferencia").off().on('keypress', function (e) {
    if (e.which == 13) {
        var referencia = $("#caja_boton_liberacionReferencia").val().trim();
        var usuario = $("#usuarioOculto").val();
        var contra = $("#contraOculta").val();
        if (referencia == "" || referencia == null) {
            alert("Campo de referencia vacio.");
        } else {
            if (validaAlfaNum("caja_boton_liberacionReferencia")) {

                liberarPago(usuario, contra, referencia);
            }
        }
    }
});
$("#caja_boton_busquedapreficha").off().on('keypress', function (e) {
    if (e.which == 13) {
        $.get("servletLimpiarBeans");
        $("#guardarS").val("");
        $("#guardarE").val("");
        var nuevo = $("#caja_boton_busquedapreficha").val().trim();
        $("#idAspirante").val(nuevo);
        if (tryNum("caja_boton_busquedapreficha")) {
            var preficha = $("#caja_boton_busquedapreficha").val();
            var usuario = $("#usuarioOculto").val();
            var contra = $("#contraOculta").val();
            dpPRueba(preficha.trim(), usuario, contra);
        } else {

//            }
        }
    }
});




function actualizarListasSE() {

    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();

    var vivePadre = $("#caja_vivePa").find("option:selected").attr("id");
    ActualizarListasFijas(4, vivePadre, "vivePa");
    var zonaProcedencia = $("#procedencia").find("option:selected").attr("id");
    ActualizarListasFijas(6, zonaProcedencia, "zonaProcedencia");

    var maxEstudiosPadre = $("#estudiosPadre").find("option:selected").attr("id");
    ServletListas(1, 4, maxEstudiosPadre, usuario, contra, "estudiosPadre", "maxEstudiosPadre", 0);

    var maxEstudiosMadre = $("#estudiosMadre").find("option:selected").attr("id");
    ServletListas(1, 4, maxEstudiosMadre, usuario, contra, "estudiosMadre", "maxEstudiosMadre", 0);

    var ingresosTotales = $("#ingresos").find("option:selected").attr("id");
    ActualizarListasFijas(7, ingresosTotales, "ingresosTotales");

    var ocupacionPa = $("#ocupacionPadre").find("option:selected").attr("id");
    ServletListas(1, 6, ocupacionPa, usuario, contra, "ocupacionPadre", "ocupacionPadre", 0);
    var ocupacionMa = $("#ocupacionMadre").find("option:selected").attr("id");
    ServletListas(1, 6, ocupacionMa, usuario, contra, "ocupacionMadre", "ocupacionMadre", 0);

    var viveMadre = $("#caja_viveMa").find("option:selected").attr("id");
    ActualizarListasFijas(4, viveMadre, "viveMa");
    var tipoCasa = $("#casaVive").find("option:selected").attr("id");
    ActualizarListasFijas(8, tipoCasa, "tipoCasa");
    var noPersonasCasa = $("#habitantes").find("option:selected").attr("id");
    ActualizarListasFijas(9, noPersonasCasa, "noPersonasCasa");

    var noCuartos = $("#cuartosCasa").find("option:selected").attr("id");
    ActualizarListasFijas(14, noCuartos, "cuartosCasa");
    var oportunidades = $("#programaOportunidades").find("option:selected").attr("id");
    ActualizarListasFijas(10, oportunidades, "progOportunidades");

}

//llenar selects de alta de escuelas
function ActualizarSelectNvaEscuela() {
    jQuery.getJSON("ServletNvaEscuelaSelects",
            function (data) {

                var id;
                limpiarNvaEscuela();
                for (var campo in data) {

                    if (campo === "control") {
                        id = "#selectControl";
                    } else if (campo === "servicio") {
                        id = "#selectServicio";
                    } else if (campo === "ambito") {
                        id = "#selectAmb";
                    } else if (campo === "turno") {
                        id = "#selectTurno";
                    }

                    var buscar = data[campo];
                    for (var opcion in buscar) {
                        var txt = buscar[opcion].idPais;
                        var t = buscar[opcion].nombre;
                        $(id).append("<option  id='" + txt + "'  value='" + t + "'>" + t + "</option>");
                    }
                }
            });
}

function limpiarNvaEscuela() {
    $("#cajaCCT").val("");
    $("#nomNvaEsc").val("");
    $("#domNvaEsc").val("");
    $("#selectControl").html("");
    $("#selectServicio").html("");
    $("#selectAmb").html("");
    $("#selectTurno").html("");
    $("#selectMun").html("");
    $("#selectLocalidad").html("");
    $("#selectMun").prop("disabled", true);
    $("#selectLocalidad").prop("disabled", true);
    $("#selectMun").append("<option  id='0'  value='---Seleccione---'>---Seleccione---</option>");
    $("#selectLocalidad").append("<option  id='0'  value='---Seleccione---'>---Seleccione---</option>");

    cargarEdo();
}

$("#btnCancelEscuela").off().on('click', function () {
    ActualizarSelectNvaEscuela();
    $("#ladoIzqEscuelas").data("changed", false);
    $("#ladoDerEscuelas").data("changed", false);
    $("#nombreYDomNvaEsc").data("changed", false);
    $("#resultadoNvaEscuela").hide();
});

$('.tabs .tab-links a').on('click', function (e) {

    if ($("#ladoIzqEscuelas").data("changed") ||
            $("#ladoDerEscuelas").data("changed") ||
            $("#nombreYDomNvaEsc").data("changed")) {
        alert("Guarde sus datos antes de salir o cancele la operación");
    } else {
        var currentAttrValue = $(this).attr('href');
        // Show/Hide Tabs
        $('.tabs ' + currentAttrValue).show().siblings().hide();
        // Change/remove current tab to active     
        $(this).parent('li').addClass('active').siblings().removeClass('active');
        e.preventDefault();

        if ($("#tabs-2").is(':visible')) {
            ActualizarSelectNvaEscuela();
        }
    }
});

//Llenas select estado
function cargarEdo() {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
//    $("#selectMun").prop("disabled", true);
//    $("#selectLocalidad").prop("disabled", true);
    buscarEscuelas("#selectEnt", usuario, contra);
}



//var i, c = 0, t = 600;
//A();
//function A()
//{
//    i = setInterval("B()", 1000);
//}
//function B()
//{
//    c++;
//    window.status = "[" + c + " de " + t + "] " + (t - c);
//    if (c >= t)
//    {
//        clearInterval(i);
//        //                    window.location = "about:blank";
//        alert("El tiempo de la sesion ha finalizado");
//        window.location = "/Modulo_Administrador/";
//    }
//};