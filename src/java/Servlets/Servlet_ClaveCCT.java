/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import Beans.ClaveCCT;
import ConexionBD.IngresoAbd;
import DAO.CatalogosDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Obtiene  el catalogo de escuelas  filtrado por  Estados  --Filtrado por municipios(pendiente)
 * @author ElyyzZ BaRruEtA
 */
@WebServlet(name = "Servlet_ClaveCCT", urlPatterns = {"/Servlet_ClaveCCT"})
public class Servlet_ClaveCCT extends HttpServlet {

    private static final long serialVersionUID = 1L;

    List<ClaveCCT> ClaveCCT;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
       
      
        String opc = request.getParameter("estado");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");
        int pk = Integer.parseInt(opc);
        
        try {
            ClaveCCT=CatalogosDAO.getClaveCCT(usuario, contra, 7, pk);
        } catch (SQLException ex) {
            Logger.getLogger(Servlet_ClaveCCT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servlet_ClaveCCT.class.getName()).log(Level.SEVERE, null, ex);
        }
//        IngresoAbd bd = new IngresoAbd(usuario, contra);
//        try {
//            ClaveCCT = bd.getClaveCCT(pk);
//        } catch (SQLException ex) {
//            Logger.getLogger(Servlet_ClaveCCT.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Servlet_ClaveCCT.class.getName()).log(Level.SEVERE, null, ex);
//        } 
        String json = null;
        json = new Gson().toJson(ClaveCCT);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

}
