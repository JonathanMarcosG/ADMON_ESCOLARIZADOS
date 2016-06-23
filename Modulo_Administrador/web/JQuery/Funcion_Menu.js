/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function asignarMatricula(){
    window.scrollTo(0,0);
    document.getElementById('fondo').style.display='block';
    document.getElementById('emergente').style.display='block'; 
}

function aceptar(){
    document.getElementById('fondo').style.display='none';
    document.getElementById('emergente').style.display='none';
}

function totales() {
    document.getElementById('fs_totales').style.display = 'block';
    document.getElementById('fs_pago').style.display = 'none';
    document.getElementById('fs_CENEVAL').style.display = 'none';
    $('#graficas_totales').css('background-color', '#ffff70');
    $('#graficas_pagado').css('background-color', 'white');
    $('#graficas_ceneval').css('background-color', 'white');
}
function pagoBancario() {
    document.getElementById('fs_totales').style.display = 'none';
    document.getElementById('fs_pago').style.display = 'block';
    document.getElementById('fs_CENEVAL').style.display = 'none';
    $('#graficas_totales').css('background-color', 'white');
    $('#graficas_pagado').css('background-color', '#ffff70');
    $('#graficas_ceneval').css('background-color', 'white');
}
function enCENEVAL() {
    document.getElementById('fs_totales').style.display = 'none';
    document.getElementById('fs_pago').style.display = 'none';
    document.getElementById('fs_CENEVAL').style.display = 'block';
    $('#graficas_totales').css('background-color', 'white');
    $('#graficas_pagado').css('background-color', 'white');
    $('#graficas_ceneval').css('background-color', '#ffff70');
}
function BuscarPorFicha() {
    document.getElementById('porFicha').style.display = 'block';
    document.getElementById('porCURP').style.display = 'none';
    document.getElementById('porNombre').style.display = 'none';
    $('#busqueda_nombre').css('background-color', 'white');
    $('#busquda_curp').css('background-color', 'white');
    $('#busqueda_preficha').css('background-color', '#ffff70');


}
function BuscarPorCURP() {
    document.getElementById('porFicha').style.display = 'none';
    document.getElementById('porCURP').style.display = 'block';
    document.getElementById('porNombre').style.display = 'none';
    $('#busqueda_preficha').css('background-color', 'white');
    $('#busquda_curp').css('background-color', '#ffff70');
    $('#busqueda_nombre').css('background-color', 'white');

}
function BuscarPorNombre() {
    document.getElementById('porFicha').style.display = 'none';
    document.getElementById('porCURP').style.display = 'none';
    document.getElementById('porNombre').style.display = 'block';
    $('#busqueda_nombre').css('background-color', '#ffff70');
    $('#busquda_curp').css('background-color', 'white');
    $('#busqueda_preficha').css('background-color', 'white');

}
function Amatricula() {

    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("AsignacionDeMatricula.jsp");
}

function Salumnos() {

    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("SeguimientoDeAlumnos.jsp");
}

function Aregistrados() {
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("AspirantesRegistados.jsp");
}

function PDRAspirantes() {
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("PeriodoDelRegistroDeAspirantes.jsp");
}



$(document).ready(function() {

    $('.tabs .tab-links a').on('click', function(e) {
        var currentAttrValue = $(this).attr('href');

        // Show/Hide Tabs
        $('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        $(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
    });




    $("#Boton_acceder").click(function() {
        $("#Informacion_InicioSesion").load("menu_administrador.jsp");
        $(window).scroll(0);
    });


    $("#Menu_desplegable h3").click(function() {

        $("#Menu_desplegable ul ul").slideUp();

        if (!$(this).next().is(":visible"))
        {
            $(this).next().slideDown();
        }
    });
});




