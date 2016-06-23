/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    var sinLeer = "0";
    var sinLeerNuevo = "1";

    noCorreosSinLeer();
    intervaloDeCorreo();
    

    $("#errorDeCorreo").click( function() {
        $("#correcto").show();
        $("#errorDeCorreo").hide();
     
       intervaloDeCorreo();
     
    });

    function noCorreosSinLeer() {
        
        $.ajax({
            dataType: "json",
            url: "ServletNoCorreos",
            success: function(data) {
                var error = data.error;
                sinLeerNuevo = data.sinLeer;
                
                if (sinLeer !== sinLeerNuevo) {
                   
                    $("#contadorCorreo").hide
                    $("#imMail").css("border", "solid");
                    $("#imMail").css("border-color", "#0099ff");
                    var snd = new Audio("Sonidos/ns.wav");
                    snd.play();
                    $("#correcto").hide();
                    $("#errorDeCorreo").show();
                }
                if (error === "0") {
                   
                    $("#contadorCorreo").text(sinLeerNuevo);
                    sinLeer = sinLeerNuevo;

                } else {
                    
                    
                    $("#contadorCorreo").text(sinLeerNuevo);
                    $("#etqErrorCorreo").text(error.fontsize(1).fontcolor("red"));
                     $("#correcto").hide();
                    $("#errorDeCorreo").show();

                }
            },
            error: function() {
               
                
                $("#correcto").hide();
                $("#errorDeCorreo").show();
            }
        });
    }

    function intervaloDeCorreo() {
        var intervalo2 = setInterval(function() {
            $("#contadorCorreo").text("");
            $.ajax({
                dataType: "json",
                url: "ServletNoCorreos",
                success: function(data) {
                    var error = data.error;
                    sinLeerNuevo = data.sinLeer;
                    if (sinLeer !== sinLeerNuevo) {
                        $("#imMail").css("border", "solid");
                        $("#imMail").css("border-color", "#0099ff");
                        var snd = new Audio("Sonidos/ns.wav");
                        snd.play();
                    }
                    if (error === "0") {
                        $("#contadorCorreo").text(" ");
                        $("#contadorCorreo").text(sinLeerNuevo);
                        sinLeer = sinLeerNuevo;

                    } else {
                        $("#contadorCorreo").text(" ");
                        $("#etqErrorCorreo").text(error.fontsize(1).fontcolor("red"));
                        $("#correcto").hide();
                        $("#errorDeCorreo").show();
                        clearInterval(intervalo2);
                    }
                },
                error: function() {
                    $("#contadorCorreo").text(" ");
                    $("#correcto").hide();
                    $("#errorDeCorreo").show();
                    clearInterval(intervalo2);
                }
            });

        }, 900000);
    }
});



