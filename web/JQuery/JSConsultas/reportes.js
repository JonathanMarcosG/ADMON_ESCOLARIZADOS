/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function aula(){
   var tr= document.getElementById("combo").value;
   if(tr==="3" || tr==="5"){
    document.getElementById("comboAula").style.display= "block";
    document.getElementById("comboAula").style.top="-34px";
     document.getElementById("comboAula").style.left="500px";
     document.getElementById("tipoReporte").style.left="200px";
     
 }else{
     document.getElementById("comboAula").style.display= "none";
     document.getElementById("comboHorMat").style.display= "none";
     document.getElementById("comboHorCen").style.display= "none";
     document.getElementById("tipoReporte").style.left="340px";
 }
}
function horario(){
     var tr= document.getElementById("comboAula").value;
   if(tr==="1"){
        document.getElementById("comboHorMat").style.display= "none";
    document.getElementById("comboHorCen").style.display= "block";
    document.getElementById("comboHorCen").style.left="370px";
   }else{
       document.getElementById("comboHorCen").style.display= "none";
        document.getElementById("comboHorMat").style.display= "block";
    document.getElementById("comboHorMat").style.left="370px";
   }
}

function repT(){
     if(document.getElementById("combo").value==="0"){
       
         graficas();
     }
     if(document.getElementById("combo").value==="1"){
       
         repNoCen();
     }
     if(document.getElementById("combo").value==="2"){
         statFichas();
     }
     if(document.getElementById("combo").value==="3"){
       
        aspAula();
     }
     if(document.getElementById("combo").value==="4"){
         proceso();
     }
     if(document.getElementById("combo").value==="5"){
        firmas();
     }
     
 }

     function repNoCen() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=1").submit();
         
    }
     function statFichas() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=2").submit();
         
    }
     function aspAula() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=5").submit();
         
    }
     function proceso() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=3").submit();
         
    }
     
    function graficas() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=0").submit();
         
    }
     function firmas() {

        $('#enviarDatos').attr('action', "reportes.pdf?tp=4").submit();
         
    }