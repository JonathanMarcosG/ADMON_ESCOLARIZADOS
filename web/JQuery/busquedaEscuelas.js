/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#selectEdos').off().on('change', function() {
    $("#barrita_de_fondoEscuelas").show();
    var usuario = $("#usuarioOculto").val();
    var contra = $("#contraOculta").val();
    $("#cargandoCCT").show();
    $("#tablaCCT").find("tr:gt(0)").remove();
    var estado = $('#selectEdos').find("option:selected").attr("id");
    var estadoNom = $('#selectEdos').find("option:selected").attr("value");
    $("#buscar_clave").prop("disabled", true);
    $.get('Servlet_ClaveCCT',
            {estado: estado, usuario: usuario, contra: contra},
    function(retorno) {
        var $ul = $('<tbody id="ListaClave"></tbody>').appendTo($('#tablaCCT'));
        $.each(retorno, function(index, item) {
            var txt = item.clave;
            var t = item.CentroEducativo;
            var c = txt + "-" + t;
            $('#ListaClave').append("<tr></td>\n\
                  <td>" + item.clave + "</td><td>" + item.CentroEducativo + "</td>\n\
                  <td>" + item.turno + "</td><td>" + item.Domicilio + "</td></tr><br>");
        });
        $("#content").show();
        $('#FondoSeleccionaClave').show();
        $('#SelecionaClave').show();
        table = $('#tablaCCT').DataTable();
        $("#edoConsultado").text(" ");
        $("#edoConsultado").append(estadoNom);

        $("#barrita_de_fondoEscuelas").hide();
    });

});


$('#cancelarCCT').on('click', function() {
    $('#SelecionaClave').hide();
    $('#FondoSeleccionaClave').hide();
    $('#cargandoCCT').hide();
    table.destroy();

});



