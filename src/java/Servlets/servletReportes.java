/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;



import PDF.Reportes;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author David
 */
public class servletReportes extends HttpServlet {



     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException{
              
      
    }

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
         ServletContext d = getServletContext();
        response.setContentType("application/pdf");
        
        int tr=  Integer.parseInt(request.getParameter("tp"));
       
        String header="";
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
    
        String horario1 = request.getParameter("horcen");
        String horario2 = request.getParameter("hormat");
        String opcion = request.getParameter("comboAula");
        String horario="";
       
        
        int opc= Integer.parseInt(opcion);
        if(opc==1){
            horario=horario1;
        }else{
            horario=horario2;
        }
        
         switch(tr){
            case 0:
                header="estadisticas";
                break;
            case 1:
               header="sinaltaceneval";
                break;
            case 2:
                header="estatusprefichas";
                break;
            case 3:
                header="procesosconcluidos";
                break;
            case 4:
                header="Firmasaspirantes_"+horario;
                break;
            case 5:
                
                header="Aspirantesporaula_"+horario;
                break;
        }
         
        response.setHeader("Content-Disposition", "filename=Reportes_"+header+ ".pdf"); 
        Reportes rep = new Reportes(d);
      rep.reportesPDF(response, tr, d, usuario, contra, horario, opc);
//        IngresoAbd bd= new IngresoAbd(usuario,contra);
//        Reportes rep = new Reportes();
//        if(opc==1){
//            rep.reportesPDF(response, tr, d, usuario, contra, horario1, opc);
//        }else{
//        rep.reportesPDF(response, tr, d, usuario, contra, horario2, opc);
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
