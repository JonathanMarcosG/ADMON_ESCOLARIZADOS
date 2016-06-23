/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    var sinLeer = "0";
    var sinLeerNuevo = "1";

//    noCorreosSinLeer();
correos();

    var checkCorreos = setInterval(function() {
        $("#contadorCorreo").text("");
      correos();

    }, 900000);
    //setInterval(noCorreosSinLeer(), 900000);
function correos(){
    jQuery.get("ServletNoCorreos",function(data){
        
        $("#contadorCorreo").text("");
        $("#contadorCorreo").text(data);
        if(data>0){
          $("#imMail").css("border", "solid");
                $("#imMail").css("border-color", "#0099ff");
                var snd = new Audio("Sonidos/ns.wav");
                snd.play();
            }
    });
}

});

