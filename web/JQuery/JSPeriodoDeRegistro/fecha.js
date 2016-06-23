/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Date Format 1.2.3
 * (c) 2007-2009 Steven Levithan <stevenlevithan.com>
 * MIT license
 *
 * Includes enhancements by Scott Trenda <scott.trenda.net>
 * and Kris Kowal <cixar.com/~kris.kowal/>
 *
 * Accepts a date, a mask, or a date and a mask.
 * Returns a formatted version of the given date.
 * The date defaults to the current date/time.
 * The mask defaults to dateFormat.masks.default.
 */

var dateFormat = function () {
    var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
            timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
            timezoneClip = /[^-+\dA-Z]/g,
            pad = function (val, len) {
                val = String(val);
                len = len || 2;
                while (val.length < len)
                    val = "0" + val;
                return val;
            };

    // Regexes and supporting functions are cached through closure
    return function (date, mask, utc) {
        var dF = dateFormat;

        // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
        if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
            mask = date;
            date = undefined;
        }

        // Passing date through Date applies Date.parse, if necessary
        date = date ? new Date(date) : new Date;
        if (isNaN(date))
            throw SyntaxError("invalid date");

        mask = String(dF.masks[mask] || mask || dF.masks["default"]);

        // Allow setting the utc argument via the mask
        if (mask.slice(0, 4) == "UTC:") {
            mask = mask.slice(4);
            utc = true;
        }

        var _ = utc ? "getUTC" : "get",
                d = date[_ + "Date"](),
                D = date[_ + "Day"](),
                m = date[_ + "Month"](),
                y = date[_ + "FullYear"](),
                H = date[_ + "Hours"](),
                M = date[_ + "Minutes"](),
                s = date[_ + "Seconds"](),
                L = date[_ + "Milliseconds"](),
                o = utc ? 0 : date.getTimezoneOffset(),
                flags = {
                    d: d,
                    dd: pad(d),
                    ddd: dF.i18n.dayNames[D],
                    dddd: dF.i18n.dayNames[D + 7],
                    m: m + 1,
                    mm: pad(m + 1),
                    mmm: dF.i18n.monthNames[m],
                    mmmm: dF.i18n.monthNames[m + 12],
                    yy: String(y).slice(2),
                    yyyy: y,
                    h: H % 12 || 12,
                    hh: pad(H % 12 || 12),
                    H: H,
                    HH: pad(H),
                    M: M,
                    MM: pad(M),
                    s: s,
                    ss: pad(s),
                    l: pad(L, 3),
                    L: pad(L > 99 ? Math.round(L / 10) : L),
                    t: H < 12 ? "a" : "p",
                    tt: H < 12 ? "am" : "pm",
                    T: H < 12 ? "A" : "P",
                    TT: H < 12 ? "AM" : "PM",
                    Z: utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                    o: (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                    S: ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
                };

        return mask.replace(token, function ($0) {
            return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
        });
    };
}();

// Some common format strings
dateFormat.masks = {
    "default": "ddd mmm dd yyyy HH:MM:ss",
    shortDate: "m/d/yy",
    mediumDate: "mmm d, yyyy",
    longDate: "mmmm d, yyyy",
    fullDate: "dddd, mmmm d, yyyy",
    shortTime: "h:MM TT",
    mediumTime: "h:MM:ss TT",
    longTime: "h:MM:ss TT Z",
    isoDate: "yyyy-mm-dd",
    isoTime: "HH:MM:ss",
    isoDateTime: "yyyy-mm-dd'T'HH:MM:ss",
    isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
    dayNames: [
        "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    ],
    monthNames: [
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
        "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
    ]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
    return dateFormat(this, mask, utc);
};

function CoerenciaPeriodo() {

    inicio = $('#fechaInicio').val();
    fin = $('#fechaFin').val();
    if ((inicio == "" || inicio == null) || (fin == "" || fin == null)) {

    } else {
        alert("pues algo");
        fechaDeInicio = new Date(inicio);
        fechaDeFin = new Date(fin);

        fechaDeInicio.format("dd/mm/yyyy");
        fechaDeFin.format("dd/mm/yyyy");

        if (fechaDeFin <= fechaDeInicio) {
            alert("La fecha de inicio debe ser mayor y diferente a la fecha de fin");
        }
    }
}

function validacionConv(fechain, fechafin, pago, entrega, inpre, finpre) {

    diaInicio = fechain.substring(0, 2);
    mesInicio = fechain.substring(3, 5);
    diaFin = fechafin.substring(0, 2);
    mesFin = fechafin.substring(3, 5);
    diapago = pago.substring(0, 2);
    mespago = pago.substring(3, 5);
    diaentrega = entrega.substring(0, 2);
    mesentrega = entrega.substring(3, 5);
    diaInpre = inpre.substring(0, 2);
    mesInpre = inpre.substring(3, 5);
    diafinpre = finpre.substring(0, 2);
    mesfinpre = finpre.substring(3, 5);

    if (((mespago && mesentrega && mesInpre && mesfinpre) === mesInicio) && ((mespago && mesentrega && mesInpre && mesfinpre) === mesFin)) {
        if ((diapago < diaInicio) || (diaInpre < diaInicio) || (diaentrega < diaInicio) || (diafinpre < diaInicio)) {
            $("#alert_param").text("La fecha de fin debe ser menor a la fecha de los parametros.");
            document.getElementById("alert_param").style.display = "block";
        } else if ((diapago > diaFin) || (diaInpre > diaFin) || (diaentrega > diaFin) || (diafinpre > diaFin)) {
            $("#alert_param").text("La fecha de fin debe ser mayor a la fecha de los parametros.");
            document.getElementById("alert_param").style.display = "block";
        } else {
            asignarPeriodo();
        }
    } else {
        if ((mespago < mesInicio) || (mesInpre < mesInicio) || (mesentrega < mesInicio) || (mesfinpre < mesInicio)) {

            $("#alert_param").text("La fecha de fin debe ser menor a la fecha de los parametros.");
            document.getElementById("alert_param").style.display = "block";
        } else if ((mespago > mesFin) || (mesInpre > mesFin) || (mesentrega > mesFin) || (mesfinpre > mesFin)) {

            $("#alert_param").text("La fecha de fin debe ser mayor a la fecha de los parametros.");
            document.getElementById("alert_param").style.display = "block";
        }
        else {
            asignarPeriodo();
        }
    }
}
$("#btn_confirmar").click(function () {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var opc = "3";
    jQuery.get("servletParamSeq",
            {usuario: usuario, contra: contra, opc: opc},
    function (data) {
        var real = $('#real').val();
        var estab = $('#estab').val();
        var fechain = $('#fechaInicio').val();
        var fechafin = $('#fechaFin').val();
        var pago = $('#pago').val();
        var entrega = $('#docs').val();
        var inpre = $('#inPre').val();
        var finpre = $('#finPre').val();
        if (data.toString() !== "none") {
            if (real == "" || real == null || estab == "" || estab == null || inpre == "" || inpre == null || finpre == "" || finpre == null || pago == "" || pago == null || entrega == "" || entrega == null || fechain == "" || fechain == null || fechafin == "" || fechafin == null) {

            } else {
                document.getElementById("alert_param").style.display = "none";
                validacionConv(fechain.toString(), fechafin.toString(), pago.toString(), entrega.toString(), inpre.toString(), finpre.toString());
            }
        } else {
            $("#alert_param").text("Antes de dar de alta el periodo de la convocatoria los parametors de la misma deben estar completos");
            document.getElementById("alert_param").style.display = "block";

        }
    });





});

function asignarPeriodo() {
    var validar = $('#fechaInicio').val();
    var validarFin = $('#fechaFin').val();

    if (validar == "" || validar == null || validarFin == "" || validarFin == null) {

        $("#alert_param").text("Para modificar el periodo debe seleccionar una fecha de inicio y de fin");
        document.getElementById("alert_param").style.display = "block";
    } else {

        var fechaDeInicio = new Date(validar);
        var fechaDeFin = new Date(validarFin);


        descomponerCadena(validarFin.toString(), validar.toString());

    }
}

function descomponerCadena(fechaDeFin, fechaDeInicio) {

    diaInicio = fechaDeInicio.substring(0, 2);
    mesInicio = fechaDeInicio.substring(3, 5);


    diaFin = fechaDeFin.substring(0, 2);
    mesFin = fechaDeFin.substring(3, 5);

    if (mesInicio === mesFin) {
        if (diaInicio >= diaFin) {
            $("#alert_param").text("Por favor verifique la fecha.");
            document.getElementById("alert_param").style.display = "block";
        } else {


            $('#finicio').text($('#fechaInicio').val());
            $('#ffin').text($('#fechaFin').val());

            window.scrollTo(0, 0);
            $('#fondo2').modal({
                backdrop: "static"
            });

        }
    } else {
        if (mesInicio > mesFin) {
            $("#alert_param").text("La fecha de fin debe ser mayor a la fecha de inicio");
            document.getElementById("alert_param").style.display = "block";
        } else {


            $('#finicio').text($('#fechaInicio').val());
            $('#ffin').text($('#fechaFin').val());

            window.scrollTo(0, 0);
            $('#fondo2').modal({
                backdrop: "static"
            });

        }
    }
}
function convertirFecha(fechaInicio, fechaFin, usuario, contra) {
    $("#fondo2").modal('hide');
    jQuery.get("servletPeriodo",
            {fechaInicio: fechaInicio, fechaFin: fechaFin, usuario: usuario, contra: contra},
    function (data) {
        document.getElementById("algoc").style.display = "block";
        document.getElementById("algo").style.display = "none";
        document.getElementById("alert_param").style.display = "none";
        $("#algoc").text("");
        $("#algoc").append(data);

    }
    );
}

function confConvocatoria(fechaInicio, fechaFin, metaReal, metaEstablecida, fechaInPre, fechaFinPre, usuario, contra) {
var mensaje="";
    $.post("servletConfCon",
            {fechaPago: fechaInicio, fechaEntrega: fechaFin, metaReal: metaReal, metaEstablecida: metaEstablecida, fechaInPre: fechaInPre, fechaFinPre: fechaFinPre, usuario: usuario, contra: contra},
    function (data) {

       mensaje=data.toString();
      
        if (mensaje === "-1") {

            $("#algo").text("");
            $("#algo").append("Los cambios se han guardado con éxito.<img src=\"Imagenes/sobreVerde.png\" width=\"50\" />");
           $("#btn_cancelar").show();
            $("#fondo2").modal('hide');
        } else if (mensaje === "1") {
            
            $("#algo").text("");
            $("#algo").append("Los cambios se han guardado con éxito.<img src=\"Imagenes/sobreVerde.png\" width=\"50\" />");
            $("#MEL").hide();
            $("#MRL").hide();
            $("#FLPL").hide();
            $("#FLEDL").hide();
            $("#FIRPL").hide();
            $("#FFRPL").hide();
            $("#btn_cancelar").show();
            $("#metE").hide();
            $("#metR").hide();
            $("#Fpago").hide();
            $("#Fentrega").hide();
            $("#inPref").hide();
            $("#finPref").hide();

            $("#aceptar_conf").hide();
            $("#hidden_acept").show();

            
            
             $("#modificara").text("Los cambios se han guardado con éxito, sin embargo se detecto una modificación en las fechas de renovación de prefichas. Se le notificara a los aspirantes de este cambio.");
        } 
        else{
             $("#algo").text("");
            $("#algo").append(mensaje);
            $("#fondo2").modal('hide');
        }
    });


}


//PeriodoDeRegistro PeriodoDeRegistroDeAspirantes.jsp
//Se ejecuta al dar click en el botón cancelar
//de la ventanan emergente de confirmación de
//cambio de perioodo.
function limpiarCampos() {
    $("#fechaInicio").val("");
    $("#fechaFin").val("");


}
function hidemodal() {
    $("#fondo2").modal('hide');
}
function hidemodal2() {
     $("#MEL").show();
            $("#MRL").show();
            $("#FLPL").show();
            $("#FLEDL").show();
            $("#FIRPL").show();
            $("#FFRPL").show();

            $("#metE").show();
            $("#metR").show();
            $("#Fpago").show();
            $("#Fentrega").show();
            $("#inPref").show();
            $("#finPref").show();
            $("#hidden_acept").hide();
            $("#aceptar_conf").show();
            $("#btn_cancelarEmergente").show();
            $("#modificara").text("");
            $("#modificara").text("Se modificará la conovocatoria");
    $("#fondo2").modal('hide');
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

jQuery("#aceptar_conf").click(function () {
    var fechaPago = $("#fechaInicio").val();
    var fechaEntrega = $("#fechaFin").val();
    var fechaInPre = $("#fechaInPre").val();
    var fechaFinPre = $("#fechaFinPre").val();
    var metaReal = $("#metare").val();
    var metaEstablecida = $("#metaes").val();
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();


    confConvocatoria(fechaPago, fechaEntrega, metaReal, metaEstablecida, fechaInPre, fechaFinPre, usuario, contra);
});

//funcion para reiniciar secuencias
function Periodo() {
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var opc = "2";
    jQuery.get("servletParamSeq",
            {usuario: usuario, contra: contra, opc: opc},
    function (data) {
        $("#MargenYEncabezado").animate({'opacity': 1}, 0).animate({'opacity': 0}, 0);
        $("#MargenYEncabezado").delay(500).load("Paginas/PeriodoDeRegistro/PeriodoDelRegistroDeAspirantes.jsp");
        $("#MargenYEncabezado").animate({'opacity': 1}, 500).animate({'opacity': 1}, 500);
    });
}
function confSecuencias() {
    $(window).scroll(0, 0);
    $('#fondo2').modal({
        backdrop: "static"
    });
}
function secuencias() {
    $("#fondo2").modal('hide');
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    var opc = "1";
    jQuery.get("servletParamSeq",
            {usuario: usuario, contra: contra, opc: opc},
    function (data) {
        if (data.toString() !== ("false")) {



            $("#MargenYEncabezado").animate({'opacity': 1}, 0).animate({'opacity': 0}, 0);
            $("#MargenYEncabezado").delay(500).load("Paginas/PeriodoDeRegistro/CantAsp.jsp");
            $("#MargenYEncabezado").animate({'opacity': 1}, 500).animate({'opacity': 1}, 500);

        } else {


            document.getElementById("mensajeBD").style.display = "block";

        }
    }
    );

}
function valNum(e) {

    if (!/^([0-9])*$/.test(e)) {
        return false;
    } else {
        return true;
    }


}
function emptyFieldcasp() {
    var text;
    if ($("#metare").val() === "" || $("#metare").val() === null || $("#metaes").val() === null || $("#metaes").val() === "") {
        if ($("#metare").val() !== null && $("#metare").val() !== "") {
            $("#message2").hide();
            if ($("#fechaFin").val() === "" || $("#fechaFin").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message4").text(text);
                $("#message4").show();
            } else {
                $("#message4").hide();
            }
            if ($("#fechaInicio").val() === "" || $("#fechaInicio").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message3").text(text);
                $("#message3").show();
            } else {
                $("#message3").hide();
            }
            if ($("#fechaInPre").val() === "" || $("#fechaInPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message5").text(text);
                $("#message5").show();
            } else {
                $("#message5").hide();
            }
            if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message6").text(text);
                $("#message6").show();
            } else {
                $("#message6").hide();
            }
            text = "Complete este campo";
            $("#message1").text(text);
            $("#message1").show();
            document.getElementById("metaes").focus();
        } else if ($("#metaes").val() !== null && $("#metaes").val() !== "") {
            $("#message1").hide();
            if ($("#fechaFin").val() === "" || $("#fechaFin").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message4").text(text);
                $("#message4").show();
            } else {
                $("#message4").hide();
            }
            if ($("#fechaInicio").val() === "" || $("#fechaInicio").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message3").text(text);
                $("#message3").show();
            } else {
                $("#message3").hide();
            }
            if ($("#fechaInPre").val() === "" || $("#fechaInPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message5").text(text);
                $("#message5").show();
            } else {
                $("#message5").hide();
            }
            if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message6").text(text);
                $("#message6").show();
            } else {
                $("#message6").hide();
            }
            text = "Complete este campo";
            $("#message2").text(text);
            $("#message2").show();
            document.getElementById("metare").focus();

        } else {
            text = "Complete este campo";
            $("#message1").text(text);
            $("#message2").text(text);
            if ($("#fechaFin").val() === "" || $("#fechaFin").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message4").text(text);
            }
            if ($("#fechaInicio").val() === "" || $("#fechaInicio").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message3").text(text);
            }
            if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message6").text(text);
            }
            if ($("#fechaInPre").val() === "" || $("#fechaInPre").val() === null) {
                text = "Por favor seleccione una fecha";
                $("#message5").text(text);
            }
            $("#message1").show();
            $("#message2").show();
            $("#message3").show();
            $("#message4").show();
            $("#message5").show();
            $("#message6").show();
            document.getElementById("metare").focus();
        }

    } else {
        $("#message1").hide();
        $("#message2").hide();
        var fieldx = $("#metare").val();
        x = valNum(fieldx);
        var fieldy = $("#metaes").val();
        y = valNum(fieldy);
        if (x && y) {
            text = "Por favor seleccione una fecha";
            if ($("#fechaInicio").val() === "" || $("#fechaInicio").val() === null) {

                $("#message3").text(text);
                $("#message3").show();
                document.getElementById("fechaInicio").focus();
                if ($("#fechaFin").val() === "" || $("#fechaFin").val() === null) {

                    $("#message4").text(text);
                    $("#message4").show();
                    document.getElementById("fechaInicio").focus();
                }
                if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {

                    $("#message6").text(text);
                    $("#message6").show();
                    document.getElementById("fechaInicio").focus();
                }
                if ($("#fechaInPre").val() === "" || $("#fechaInPre").val() === null) {

                    $("#message5").text(text);
                    $("#message5").show();
                    document.getElementById("fechaInicio").focus();
                }

            } else {
                if ($("#fechaFin").val() === "" || $("#fechaFin").val() === null) {
                    $("#message3").hide();
                    $("#message4").text(text);
                    $("#message4").show();
                    document.getElementById("fechaFin").focus();
                } else if ($("#fechaInPre").val() === "" || $("#fechaInPre").val() === null) {
                    $("#message3").hide();
                    $("#message4").hide();
                    $("#message5").text(text);
                    if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {
                        $("#message6").text(text);
                        $("#message6").show();
                    }
                    $("#message5").show();
                    document.getElementById("fechaInPre").focus();
                } else if ($("#fechaFinPre").val() === "" || $("#fechaFinPre").val() === null) {
                    $("#message3").hide();
                    $("#message4").hide();
                    $("#message5").hide();
                    $("#message6").text(text);
                    $("#message6").show();
                    document.getElementById("fechaFinPre").focus();
                } else {
                    $("#message1").hide();
                    $("#message2").hide();
                    $("#message3").hide();
                    $("#message4").hide();
                    $("#message5").hide();
                    $("#message6").hide();
                    inpre = $("#fechaInPre").val();
                    finpre = $("#fechaFinPre").val();
                    if (inpre == "" || inpre == null || finpre == "" || finpre == null) {
                    }
                    else {
                        inp = $("#fechaInicioP").val();
                        fip = $("#fechaFinP").val();
                        if (inp == "" || inp == null || fip == "" || fip == null) {
                            Casp();
                        }
                        else {
                            Casp();
                        }
                    }

                }
            }

        } else {
            alert("Introduzca solo valores númericos enteros");
        }
    }
}



function limiteParam() {
    alert($("#periodoInicio").text($("#metaes").val()));
}
function Casp() {

    document.getElementById("alert_params").style.display = "none";
    window.scrollTo(0, 0);
    $("#metE").text($("#metaes").val());
    $("#metR").text($("#metare").val());
    $("#Fpago").text($("#fechaInicio").val());
    $("#Fentrega").text($("#fechaFin").val());
    $("#inPref").text($("#fechaInPre").val());
    $("#finPref").text($("#fechaFinPre").val());


     $('#fondo2').modal({
        backdrop: "static"
    });



    //  $("#MargenYEncabezado").animate({'opacity':1},500).delay(500).animate({'opacity':0},500);
    //setTimeout(showpanel2, 2000);
}
function returnCasp() {

    var opc = "3";
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    jQuery.get("servletParamSeq",
            {usuario: usuario, contra: contra, opc: opc},
    function (data) {


        $("#MargenYEncabezado").animate({'opacity': 1}, 0).animate({'opacity': 0}, 0);
        $("#MargenYEncabezado").delay(500).load("Paginas/PeriodoDeRegistro/CantAsp.jsp");
        $("#MargenYEncabezado").animate({'opacity': 1}, 500).animate({'opacity': 1}, 500);


    });

}



function showpanel() {

    $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/CantAsp.jsp");
    $("#MargenYEncabezado").animate({'opacity': 1}, 500).delay(500).animate({'opacity': 1}, 500);

}
function showpanel3() {

    $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/ReinicioSecuencias.jsp");
    $("#MargenYEncabezado").animate({'opacity': 1}, 500).delay(500).animate({'opacity': 1}, 500);
}
function showbox(x) {
    if (x === 1) {
        document.getElementById("metaE").style.display = "block";
    }
    if (x === 2) {
        document.getElementById("metaR").style.display = "block";
    }
    if (x === 3) {
        document.getElementById("Finicio").style.display = "block";
    }
    if (x === 4) {
        document.getElementById("Ffin").style.display = "block";
    }
    if (x === 5) {
        document.getElementById("Finpre").style.display = "block";
    }
    if (x === 6) {
        document.getElementById("Ffinpre").style.display = "block";
    }



}
function hidebox(x) {
    if (x === 1) {
        document.getElementById("metaE").style.display = "none";
    }
    if (x === 2) {
        document.getElementById("metaR").style.display = "none";
    }
    if (x === 3) {
        document.getElementById("Finicio").style.display = "none";
    }
    if (x === 4) {
        document.getElementById("Ffin").style.display = "none";
    }
    if (x === 5) {
        document.getElementById("Finpre").style.display = "none";
    }
    if (x === 6) {
        document.getElementById("Ffinpre").style.display = "none";
    }


}
$(document).ready(function () {
    $("#prueba").hide();

});
