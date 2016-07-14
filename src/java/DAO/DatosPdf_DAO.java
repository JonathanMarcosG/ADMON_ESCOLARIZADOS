/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.DatosPDF;
import Utils.Constants;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author sony
 */
public class DatosPdf_DAO {

    public static DatosPDF datos_pdfAspirante(String username, String password, String folio) {
        int codError;
        String msjError;
        DatosPDF datos = new DatosPDF();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_3.GET_GENERA_SOLICITUD_ASP_SP(?,?,?,?)}");
                call.setString("paFolioCenevalAsp", folio);
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        datos.setNombre(rset.getObject("NOMBRE_COMPLETO").toString());
                        datos.setFicha(rset.getObject("FICHA").toString());
                        datos.setFolioCENEVAL(rset.getObject("FOLIO_CENEVAL").toString());
                        datos.setFechaExamenCeneval(rset.getObject("FECHA_EXAM_CONOC").toString());
                        datos.setFechaExamenMate(rset.getObject("FECHA_EXAM_HAB").toString());
                        datos.setLugarExamenCeneval(rset.getObject("LUGAR_FECHA_A").toString());
                        datos.setLugarExamenMate(rset.getObject("LUGAR_FECHA_B").toString());
                        datos.setDiaPublicacion(rset.getObject("FECHA_PUBL_RESUL").toString());
                        datos.setPagResultados(rset.getObject("PERIODICO_PUBL").toString());
                        datos.setEstudioCeneval(rset.getObject("LIGA_ESTUDIO_CENEVAL").toString());
                        datos.setEstudioCenevalInt(rset.getObject("LIGA_ESTUDIO_CENEVAL_INT").toString());
                        datos.setPeriodoConcursa(rset.getObject("PERIODO_CONSURSA").toString());
                    }
                    //Cierre del cursor.
                    rset.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {

        }
        return datos;
    }

//    public static DatosPDF datos_pdf(String username, String password, String folio) {
//        int codError;
//        String msjError;
//        DatosPDF datos = new DatosPDF();
//        Logger logger = new Logger();
//        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
//        if (conn != null) {
//            try {
//                datos.setFolio(folio);
//                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ADMIN_2.GET_GENERA_SOLICITUD_ASP_SP(?,?,?,?)}");
//                call.setString("paFolioCenevalAsp", folio);
//                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
//
//                call.execute();
//
//                codError = call.getInt("paCodigoError");
//                if (codError == 0) {
//                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
//                    while (rset.next()) {
//                        datos.setNombre(rset.getObject("NOMBRE_COMPLETO").toString());
//                        datos.setFicha(rset.getObject("FICHA").toString());
//                        datos.setFolioCENEVAL(rset.getObject("FOLIO_CENEVAL").toString());
//                        datos.setFechaExamenCeneval(rset.getObject("FECHA_EXAM_CONOC").toString());
//                        datos.setFechaExamenMate(rset.getObject("FECHA_EXAM_HAB").toString());
//                        datos.setLugarExamenCeneval(rset.getObject("LUGAR_FECHA_A").toString());
//                        datos.setLugarExamenMate(rset.getObject("LUGAR_FECHA_B").toString());
//                        datos.setDiaPublicacion(rset.getObject("FECHA_PUBL_RESUL").toString());
//                        datos.setPagResultados(rset.getObject("PERIODICO_PUBL").toString());
//                        datos.setPeriodoConcursa(rset.getObject("PERIODO_CONSURSA").toString());
//                    }
//                    //Cierre del cursor.
//                    rset.close();
//                } else {
//                    //Ejecución incorrecta: loggear.
//                    msjError = call.getString("paMjeDescError");
//                    String logMessage = codError + "->" + msjError;
//                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
//                }
//            } catch (SQLException ex) {
//                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
//            } finally {
//                Conexion.cerrarConexion(conn);
//            }
//        } else {
//
//        }
//        return datos;
//    }
}
