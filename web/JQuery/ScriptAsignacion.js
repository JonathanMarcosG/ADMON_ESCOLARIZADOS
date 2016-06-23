/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function asignation(){

    limpiarBeans();
}

function limpiarBeans() {
    $.get("servletLimpiarBeans",
            function(data) {
                $(window).scroll(0, 0);
                $("#MargenYEncabezado").load("Paginas/Consultas/AsignacionDeMatricula.jsp");
              
            });
}
