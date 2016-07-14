/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.SeguimientoDelAlumno;
import Utils.Constants;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.edu.ittoluca.logutils.Logger;

/**
 *
 * @author sony
 */
public class SeguimientoAspiranteDAO {
    public static SeguimientoDelAlumno obtenerSeguimiento(String username, String password, String parametroInicial, int bandera) {
        int codError;
        String msjError = null;
        Logger logger = new Logger();
        SeguimientoDelAlumno sda = new SeguimientoDelAlumno();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_SEGUIMIENTO_ASP_SP(?,?,?,?,?)}");
                call.setString("paParametro", parametroInicial);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.setInt("paBandera", bandera);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);
                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    sda.setCodError(-1);
                    while (rset.next()) {
                        sda.setCodError(codError);
                        sda.setMsjError(msjError);
                        sda.setNombre(rset.getString("NOMBRE_ASPIRANTE"));
                        sda.setCurp(rset.getString("CURP"));
                        sda.setCorreo(rset.getString("CORREO"));
                        sda.setReferencia(rset.getString("REFERENCIA_BANCARIA"));
                        sda.setPreficha(Integer.parseInt(rset.getString("PREFICHA")));
                        sda.setRegPreficha(Integer.parseInt(rset.getString("REGISTRO_PREFICHA")));
                        sda.setRegFicha(Integer.parseInt(rset.getString("FICHA")));
                        sda.setPago(Integer.parseInt(rset.getString("PAGO_PROCESADO")));
                        sda.setFolioCeneval(Integer.parseInt(rset.getString("VALID_FOLIO_CENEVAL")));
                        sda.setAltaCeneval(Integer.parseInt(rset.getString("ALTA_CENEVAL")));
                        sda.setFechaModif(rset.getString("ULTIMA_ACTUALIZACION"));
                        sda.setUsuarioModif(rset.getString("USUARIO_ACTUALIZA"));
                    }
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    sda.setCodError(-1);
                    sda.setMsjError(logger.getMensajeError());

                }
                call.close();
            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                sda.setCodError(-2);
                sda.setMsjError(msjError);

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            sda.setCodError(-3);
            sda.setMsjError(msjError);
        }
        return sda;
    }
    
}
