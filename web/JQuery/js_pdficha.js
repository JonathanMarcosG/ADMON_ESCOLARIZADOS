function enviarCorreoDeCENEVAL1(folio) {
    jQuery.get("Envia.jsp",
            {caja_folio: folio},
    function(data) {
        var vinculo = data.substring(0, 300);
        window.open(vinculo);
            $("#emergenteSobreVerde").show();
            $("#fondoSobreVerde").show();
            $("#spinPago1").hide();
            $("#fondoSpin").hide();
    });

}

$(document).ready(function() {

    $("#botonVerde").click(function() {
        var folio = $("#caja_folio").val();
        enviarCorreoDeCENEVAL1(folio);
        $("#spinPago1").show();
        $("#fondoSpin").show();
    });
});