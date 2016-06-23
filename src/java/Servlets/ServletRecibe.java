package Servlets;

import ConexionBD.IngresoAbd;
import PDF.CrearPDF_Ficha;
import PDF.Encripta;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet que arma el pdf de la ficha de examen 
public class ServletRecibe extends HttpServlet {

    String user = "ASPIRANTE_ITT";
    String pass = "ASP1R4NT3";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        String folioC = request.getParameter("folioC");
        Encripta e = new Encripta();
        String folioCD = e.decrypt(folioC);

        String url_logo = "/Imagenes/logoTec.png";
        String absolute_url_logo = getServletContext().getRealPath(url_logo);
        try {

            IngresoAbd in = new IngresoAbd(user, pass);
            
            CrearPDF_Ficha nuevoPDF = new CrearPDF_Ficha();
            nuevoPDF.generarPDF(response.getOutputStream(), in.datos_pdfAspirante(folioCD), absolute_url_logo);

        } catch (MalformedURLException ex) {
            Logger.getLogger(ServletRecibe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
