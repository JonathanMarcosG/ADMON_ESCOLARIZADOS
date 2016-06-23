/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author David
 */
public class servletGraficas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
      
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       String SITIO_1="prueba 1";
       String SITIO_2="prueba 2";
    // Visitas del sitio web 1
    dataset.setValue(100, SITIO_1, "Lunes");
    dataset.setValue(120, SITIO_1, "Martes");
    dataset.setValue(110, SITIO_1, "Miércoles");
    dataset.setValue(103, SITIO_1, "Jueves");
    dataset.setValue(106, SITIO_1, "Viernes");

    // Visitas del sitio web 2
    dataset.setValue(60, SITIO_2, "Lunes");
    dataset.setValue(62, SITIO_2, "Martes");
    dataset.setValue(61, SITIO_2, "Miércoles");
    dataset.setValue(63, SITIO_2, "Jueves");
    dataset.setValue(66, SITIO_2, "Viernes");
       JFreeChart chart = ChartFactory.createBarChart3D("Aspirantes", "",
   "Número visitas", dataset, PlotOrientation.VERTICAL, true,
   true, false);
       try
 {
 File image = File.createTempFile("image", "tmp");
 
 // Assume that we have the chart
 ChartUtilities.saveChartAsPNG(image, chart,500, 300);
 
 FileInputStream fileInStream = new FileInputStream(image);
 OutputStream outStream = response.getOutputStream();   
 
 long fileLength;
 byte[] byteStream;
 
 fileLength = image.length();
 byteStream = new byte[(int)fileLength];
 fileInStream.read(byteStream, 0, (int)fileLength);
 
 response.setContentType("image/png");
 response.setContentLength((int)fileLength);
 response.setHeader("Cache-Control", 
     "no-store,no-cache, must-revalidate, post-check=0, pre-check=0");
 response.setHeader("Pragma", "no-cache");
 
 fileInStream.close();
 outStream.write(byteStream);
 outStream.flush();
 outStream.close();
 
 }
 catch (IOException e)
 {
 System.err.println("Problem occurred creating chart.");
 }
    }

 
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
 
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
