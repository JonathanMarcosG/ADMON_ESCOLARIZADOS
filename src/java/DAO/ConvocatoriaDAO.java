/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.ConfigurarPeriodo;
import Beans.confConv;
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
public class ConvocatoriaDAO {

    public static String CheckSec(String username, String password) {
        int codError;
        String msjError = null;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{?=call FICHAS.PQ_CHECK_ADMIN_2.CHECK_ESTADO_SEQUENCIA_FN}");
                call.registerOutParameter(1, OracleTypes.NUMBER);
                call.execute();
                codError = call.getInt(1);
                if (codError == 0) {
                    msjError = "sin reinicio" + "&" + codError;
                } else if (codError > 0) {
                    msjError = "reinicio" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    msjError = logger.getMensajeError() + "&" + "-1";
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + "-2";
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + "-3";
        }
        return msjError;
    }

    public static String confConvocatoria(String username, String password, String fechaInicio, String fechaFin, String metaReal, String metaEstablecida, String fechaInPre, String fechaFinPre) {
        int codError, renovacion;
        String msjError = null;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_INSERT_ADMIN_1.INSERT_PARAM_CONVOC_SP(?,?,?,?,?,?,?,?,?)}");
                call.setString("paFechaEntDoc", fechaInicio);
                call.setString("paFechaFinPago", fechaFin);
                call.setString("paMetaReal", metaReal);
                call.setString("paMetaEstablec", metaEstablecida);
                call.setString("paFechaIniRen", fechaInPre);
                call.setString("paFechaFinRen", fechaFinPre);
                call.registerOutParameter("vlCambioEnRenovacion", OracleTypes.NUMBER);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                renovacion = call.getInt("vlCambioEnRenovacion");
                if (codError == 0) {
                    msjError = "ninguno" + "&" + codError + "&" + renovacion;
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    msjError = logger.getMensajeError() + "&" + "-1";
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + "-2";
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + "-3";
        }
        return msjError;
    }

    public static confConv ParametrosConv(String username, String password) {
        int codError;
        String msjError;
        confConv cv = new confConv();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ADMIN_2.GET_PARAMETROS_CONV_SP(?,?,?)}");
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        int numProp = rset.getInt("NUM_PROPIEDAD");
                        if (numProp == 1) {
                            cv.setMetaEstablecida(rset.getString("VALOR"));
                        }
                        if (numProp == 2) {
                            cv.setMetaReal(rset.getString("VALOR"));
                        }
                        if (numProp == 3) {
                            cv.setFechaPago(rset.getString("VALOR"));
                        }
                        if (numProp == 4) {
                            cv.setFechaEntrega(rset.getString("VALOR"));
                        }
                        if (numProp == 5) {
                            cv.setFechaIpre(rset.getString("VALOR"));
//                            setFechaI(rset.getString("VALOR"));
                        }
                        if (numProp == 6) {
                            cv.setFechaFpre(rset.getString("VALOR"));
//                            setFechaF(rset.getString("VALOR"));
                        }
                        cv.setCodError(codError);
                        cv.setMsjError(msjError);
                    }
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    cv.setCodError(-1);
                    cv.setMsjError(msjError);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                cv.setCodError(-2);
                cv.setMsjError(msjError);
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            cv.setCodError(-3);
            cv.setMsjError(msjError);
        }
        return cv;
    }

    public static ConfigurarPeriodo verificarPeriodo(String username, String password) {
        int codError;
        String msjError;
        ConfigurarPeriodo cp = new ConfigurarPeriodo();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_VIGENCIA_CONVOC_SP(?,?,?,?,?)}");
                call.registerOutParameter("paFechaInicio", OracleTypes.VARCHAR);
                call.registerOutParameter("paFechaFin", OracleTypes.VARCHAR);
                call.registerOutParameter("paFechaActual", OracleTypes.VARCHAR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");
                if (codError == 0) {
                    cp.setCodError(codError);
                    cp.setMsjError(msjError);
                    cp.setFechaInicio(call.getString("paFechaInicio"));
                    cp.setFechaFin(call.getString("paFechaFin"));
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    cp.setCodError(-1);
                    cp.setMsjError(msjError);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                cp.setCodError(-2);
                cp.setMsjError(msjError);
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            cp.setCodError(-3);
            cp.setMsjError(msjError);
        }
        return cp;
    }

    public static int ReinicioSec(String username, String password) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_2.GENERA_REINICIO_SEQUENCIAS_SP(?,?)}");
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    codError = call.getInt("paCodigoError");
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    codError = -1;
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                codError = -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            codError = -3;
        }
        return codError;
    }

    public static String actualizarPeriodo(String username, String password, String fechaInicio, String fechaFin) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_INSERT_ADMIN_1.INSERT_VIGENCIA_CONVOC_SP(?,?,?,?)}");
                call.setString("paFechaInicio", fechaInicio);
                call.setString("paFechaFin", fechaFin);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "ninguno" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    msjError = msjError + "&" + "-1";
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + "-2";
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + "-3";
        }
        return msjError;
    }
}
