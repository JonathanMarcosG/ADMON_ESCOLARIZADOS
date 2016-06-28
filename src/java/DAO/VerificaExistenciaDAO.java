/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PrefichaModel;
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
public class VerificaExistenciaDAO {

    public static PrefichaModel recuperaPreficha(String username, String password, String curp) {
        PrefichaModel prefichaTmp = new PrefichaModel();
        String resultado_error;
        String nombrebd;
        String descrip_error;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            //Se genera el bloque try-catch-finally
            try {

                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_RECUPERAR_PREFICHA_ASP_SP(?,?,?,?)}");
                call.setString("paCurpAspirante", curp);
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                call.execute();
                resultado_error = call.getString("paCodigoError");
                descrip_error = call.getString("paMjeDescError");

                if ("0".equals(resultado_error)) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        nombrebd = rset.getString("NOMBRE");
                        if ("".equals(nombrebd) || nombrebd == null) {
                            prefichaTmp.setExiste(0);
                        } else {

                            String appat = rset.getObject("APELLIDO_PAT").toString();
                            String apmat = rset.getObject("APELLIDO_MAT").toString();
                            String date_exp_ref = rset.getString("FECHA_EXPIRA_REF");

                            prefichaTmp.setMensaje(descrip_error);
                            prefichaTmp.setNombrebd(rset.getObject("NOMBRE").toString());
                            prefichaTmp.setApellidosbd(appat + " " + apmat);
                            prefichaTmp.setCarrerabd(rset.getObject("NOMBRE_CARRERA").toString());
                            prefichaTmp.setPrefichabd(rset.getObject("PREFICHA").toString());
                            prefichaTmp.setPeriodobd(rset.getObject("PERIODO_CONSURSA").toString());
                            prefichaTmp.setModalidadbd(rset.getObject("MODALIDAD").toString());
                            prefichaTmp.setRef_bancaria(rset.getObject(11).toString());
                            prefichaTmp.setImporte_bd(rset.getObject("IMPORTE").toString());
                            //prefichaTmp.set(rset.getObject("FECHA_EMISION").toString());
                            prefichaTmp.setFechapdf(rset.getObject("FECHA_ACTUAL").toString());

                            //Revisamos si el aspirate tiene fecha de expriacion de referecia:
                            // Si la fecha no viene valida, ponemos 'No asignada'
                            // Si la fecha es valida, simplemente la asignamos.
                            prefichaTmp.setFecha_limite_pago((date_exp_ref == null || "".equals(date_exp_ref)) ? "No asignada" : date_exp_ref);
//                        fechalim = rset.getObject("FECHA_LIMITE_PAGO").toString();

                            prefichaTmp.setCurpbd(curp);
                            prefichaTmp.setExiste(1);
                        }
                    }
                    call.close();
                } else {
                    String logMessage = resultado_error + "->" + descrip_error;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    prefichaTmp.setExiste(0);
                    prefichaTmp.setMensaje(descrip_error);

                }
            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                prefichaTmp.setExiste(0);
                prefichaTmp.setMensaje(logger.getMensajeError());
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            prefichaTmp.setExiste(0);
            prefichaTmp.setMensaje(Conexion.getConnectionErrorMessage());

        }
        return prefichaTmp;
    }
}
