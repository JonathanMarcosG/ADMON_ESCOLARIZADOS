/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function Reportes(usuario,contra){
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Consultas/Reportes.jsp");
    jQuery.get("servletHorarios",
            {usuario:usuario, contra:contra},
    function(data) {  
        
        
    });
}


function asignation(){
     limpiarBeans();
    $(window).scroll(0, 0);
                $("#MargenYEncabezado").load("Paginas/Consultas/AsignacionDeMatricula.jsp");
}
 
function limpiarBeans() {
    $.get("servletLimpiarBeans",
            function(data) {
                

            });
}

//Opción recuperar preficha
function recPreficha(){

    $("#MargenYEncabezado").load("Paginas/RecuperarPreficha/recuperarPreficha.jsp");
}

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
function Aregistrados() {
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Estadisticas/AspirantesRegistados.jsp");
}
function escuelas(){
    alert("mama");
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Escuelas/altaYBusqueda.jsp");
   
    escuelasInterval = setInterval(function() {
        var sinDefinir = typeof $("#introEscuelas").val();
        if (sinDefinir !== undefined) {
            buscarEscuelas("#selectEdos", usuario, contra);
            clearInterval(escuelasInterval);
          
        }
    }, 500);
}

//Menú
//Se ejecuta al seleccionar del menú:
//Consultas: Seguimiento de alumnos

function PDRAspirantes() {
    $(window).scroll(0, 0);
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    
    jQuery.get("servletSequence",{usuario:usuario,contra:contra},function(data){
        if(data.toString()==="true"){
             $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/ReinicioSecuencias.jsp");
        }
        else if(data.toString()==="false"){
            
             $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/CantAsp.jsp");
        }else{
            $("#MargenYEncabezado").load("Paginas/PeriodoDeRegistro/ReinicioSecuencias.jsp");
        }
    });
   
}

//Se utitliza en la función Inicio
//Conecta con la base de datos 
//Regresa la cadena del periodo actual
function reEnviarPeriodo(usuario, contra) {
    jQuery.get("servletInicio",
            {usuario: usuario, contra: contra});
}

//Se ejecuta cada que se selecciona "Inicio"
//en el menú
//se creó porque después de cierto tiempo
//que la aplicación se ejecuta
//se pierde la cadena que contiene el periodo

//Se ejecuta cuando se da click en salir en el menú
function Salir() {
    window.location.href = '/Modulo_Administrador/';
}

$(document).ready(function() {
    
    function Filtros(id, data) {
    $(id).html("");
    $.each(data, function(index, item) {
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
//consultas LiberacionDePago.jsp
// se ejecuta al dar click en la opción del menú
    $("#liberacion").click(function() {
        
        jQuery.get("servletLimpiarBeanReferencia");
       
    $(window).scroll(0, 0);
    $("#MargenYEncabezado").load("Paginas/Consultas/LiberacionDePago.jsp");
    });

    function buscarEscuelas(id, usuario, contra) {

        jQuery.getJSON("servletEstados", {usuario: usuario,
            contra: contra},
        function(data) {
            Filtros(id, data);
        });
    }


   
   


});