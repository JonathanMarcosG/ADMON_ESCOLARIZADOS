/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PrefichaModel;
import Beans.SeguimientoDelAlumno;
import Utils.Constants;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.driver.OracleTypes;

/**
 *
 * @author Jony
 */
public class ConsultasDAO {
    
    public static SeguimientoDelAlumno Notificaciones(String username, String password, String parametroInicial, int bandera) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        SeguimientoDelAlumno sda = new SeguimientoDelAlumno();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_NOTIFICACION_ASP_SP(?,?,?,?,?)}");
                call.setString("paParametro", parametroInicial);
                call.setInt("paBandera", bandera);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    sda.setCodError(-1);
                    while (rset.next()) {
                        sda.setCodError(codError);
                        sda.setMsjError(msjError);
                        sda.setIdAsp(rset.getObject("ID_ASPIRANTE").toString());
                        sda.setPreficha(Integer.parseInt(rset.getObject("PREFICHA").toString()));
                        sda.setFichaAl(rset.getString("FICHA"));
                        sda.setNombre((rset.getObject("NOMBRE").toString()));

                        sda.setApellidoP((rset.getObject("APELLIDO_PAT").toString()));
                        sda.setApellidoM((rset.getObject("APELLIDO_MAT").toString()));
                        sda.setCorreo((rset.getObject("CORREO").toString()));
                        sda.setCurp((rset.getObject("CURP").toString()));
                        sda.setCarrera((rset.getObject("NOMBRE_CARRERA").toString()));
                        sda.setPago(Integer.parseInt(rset.getObject("PAGO_PROCESADO").toString()));
                        sda.setAltaCeneval(Integer.parseInt(rset.getObject("ALTA_CENEVAL").toString()));
                    }
                } else {
                    String logMessage = codError + "->" + msjError;
                    sda.setCodError(-1);
                    sda.setMsjError(logMessage);
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                sda.setCodError(-2);
                sda.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            sda.setCodError(-3);
            sda.setMsjError(msjError);
        }
        return sda;
    }

    public static String urlCeneval(String username, String password) {
        String url = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{?=call FICHAS.PQ_GET_ADMIN_2.GET_LIGA_CENEVAL_FN()}");
                call.registerOutParameter(1, OracleTypes.VARCHAR);
                call.execute();
                url = call.getString(1);
                call.close();

            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                url = logger.getMensajeError();
                System.out.println("errrrrrroooooooooooorrrrrrrrrrrrr:"+url);
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            url = Conexion.getConnectionErrorMessage();
        }
        return url;
    }
}
