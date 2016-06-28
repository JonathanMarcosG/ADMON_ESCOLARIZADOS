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
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
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
        ByteArrayOutputStream baos=preficha.ElaboraPreficha(curp, d);
        sendDocument(response, baos);
    }
      public void sendDocument(HttpServletResponse response, ByteArrayOutputStream obj) throws IOException{
         
        response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(obj.size());
        try (OutputStream os = response.getOutputStream()) {
            obj.writeTo(os);
            os.flush();
            os.close();
        }
    }
      
}
