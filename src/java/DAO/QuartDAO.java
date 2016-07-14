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
import java.util.ArrayList;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author sony
 */
public class QuartDAO {

    private String fechaI = "";
    private String fechaF = "";
    private int error;

    public ArrayList<String> checkRenovacion(String username, String password) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        ArrayList<String> correos = new ArrayList();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_CAMBIOS_RENOVACION_SP(?,?,?,?,?,?)}");
                call.registerOutParameter("paCambioEnRenovacion", OracleTypes.NUMBER);
                call.registerOutParameter("paFechaIniRen", OracleTypes.VARCHAR);
                call.registerOutParameter("paFechaFinRen", OracleTypes.VARCHAR);
                call.registerOutParameter("paCursorCorreosRen", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    setFechaI(call.getString("paFechaIniRen"));
                    setFechaF(call.getString("paFechaFinRen"));
                    setError(call.getInt("paCambioEnRenovacion"));
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        correos.add(rset.getString("CORREO"));
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
        return correos;
    }

    public void checkCorreosEnviados(String username, String password) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CONFIRMA_CAMBIOS_RENOVAR_SP(?,?)}");
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    
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

    }

    /**
     * @return the fechaI
     */
    public String getFechaI() {
        return fechaI;
    }

    /**
     * @param fechaI the fechaI to set
     */
    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    /**
     * @return the fechaF
     */
    public String getFechaF() {
        return fechaF;
    }

    /**
     * @param fechaF the fechaF to set
     */
    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    /**
     * @return the error
     */
    public int getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(int error) {
        this.error = error;
    }
}
