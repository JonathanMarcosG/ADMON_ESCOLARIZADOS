package Servlets;

import PDF.Encripta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet que envía correo al aspirante, adjuntando la ficha del examen.
//Se llama cuando se da click en el sobre verde del folio CENEVAL.
public class ServletEnvia extends HttpServlet {

    Encripta e = new Encripta();
   

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            response.setContentType("text/html;charset=ISO-8859-1");
            request.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
            
            
//            String nombre = request.getParameter("nombre");
//            String correo = request.getParameter("cajaCORREO");
            String txtFolio = request.getParameter("caja_folio");
//            String Url = Constants.HOME_ADM + "/RecibeMatricula.jsp";
            String MatriculaEncr = e.encryptURL(txtFolio);
            String liga ="RecibeMatricula.jsp?folioC=" + MatriculaEncr;
            out.print(liga);            
            
        } catch (Exception ex) {
            Logger.getLogger(ServletEnvia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
