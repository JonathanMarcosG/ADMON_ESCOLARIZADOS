/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConexionBD.CreaPrefichaPDF;
//Recupera  la  preficha  desde el apartado de Recuperar  preficha en el inicio
public class PrefichaPDF extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
       
    }
    @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      ServletContext d = getServletContext();
        
        response.setContentType("application/pdf");
        String curp = request.getParameter("curp");
        CreaPrefichaPDF preficha = new CreaPrefichaPDF();
//        System.out.println("curp"+curp);
        preficha.ElaboraPreficha(curp, response, d);
       
    }
      
}
