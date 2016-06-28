/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.Reportes;
import Utils.Constants;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Jony
 */
public class ReportesDAO {

    public static List<Reportes> AspPAula(String username, String password, String horario, int opc) {
        int codError;
        String msjError;
        Reportes report;
        Logger logger = new Logger();
        List<Reportes> reportes = new ArrayList<>();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement cs = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_ASPIRANTES_AULA_SP(?,?,?,?,?,?)}");
                cs.setString("paAulaHorario", horario);
                cs.setInt("paOpcion", opc);
                cs.registerOutParameter("paFechaExamen", OracleTypes.VARCHAR);
                cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                cs.execute();

                codError = cs.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = cs.getString("paMjeDescError");
                    String fecha = cs.getString("paFechaExamen");
                    ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                    while (rs.next()) {
                        report = new Reportes();
                        report.setCodError(codError);
                        report.setMsjError(msjError);
                        report.setNombre(rs.getString("NOMBRE"));
                        report.setCarrera(rs.getInt("CARRERA"));
                        report.setFecha(fecha);
                        report.setNom_carrera(rs.getString("NOMBRE_CARR"));
                        report.setFicha(rs.getString("FICHA"));
                        report.setFolio(rs.getString("FOLIO_CENEVAL"));
                        report.setFirma(rs.getString("FIRMA"));
                        report.setAsist(rs.getString("ASIST"));
                        report.setLugar(rs.getString("LUGAR"));
                        reportes.add(report);
                    }
                } else {
                    msjError = cs.getString("paMjeDescError");
                    report = new Reportes();
                    report.setMsjError(msjError);
                    report.setCodError(-1);
                    reportes.add(report);
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                cs.close();

            } catch (SQLException ex) {
                //Loggeo del error.
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                report = new Reportes();
                report.setMsjError(msjError);
                report.setCodError(-2);
                reportes.add(report);

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            msjError = Conexion.getConnectionErrorMessage();
            report = new Reportes();
            report.setMsjError(msjError);
            report.setCodError(-3);
            reportes.add(report);
        }
        return reportes;
    }

    public static List<Reportes> noAltaCen(String username, String password) {
        int codError;
        String msjError;
        Reportes report;
        Logger logger = new Logger();
//        Reportes reportError = new Reportes();s
        List<Reportes> reportes = new ArrayList<>();

        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement cs = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_CENEVAL_NO_ALTA_SP(?,?,?)}");
                cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                cs.execute();

                codError = cs.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = cs.getString("paMjeDescError");
                    ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                    while (rs.next()) {
                        report = new Reportes();
                        report.setPreficha(Integer.toString(rs.getInt("PREFICHA")));
                        report.setMsjError(msjError);
                        report.setCodError(codError);
                        report.setReferencia(rs.getString("REFERENCIA_BANCARIA"));
                        report.setCorreo(rs.getString("CORREO"));
                        report.setUsuario(rs.getString("USUARIO"));
                        report.setUl_act(rs.getString("ULTIMA_ACTUALIZACION"));

                        reportes.add(report);
                    }
                } else {
                    msjError = cs.getString("paMjeDescError");
                    report = new Reportes();
                    report.setMsjError(msjError);
                    report.setCodError(-1);
                    reportes.add(report);
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                cs.close();

            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                report = new Reportes();
                report.setMsjError(msjError);
                report.setCodError(-2);
                reportes.add(report);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            msjError = Conexion.getConnectionErrorMessage();
            report = new Reportes();
            report.setMsjError(msjError);
            report.setCodError(-3);
            reportes.add(report);
        }
        return reportes;
    }

    public static List<Reportes> procesoCon(String username, String password) {
        int codError;
        String msjError;
        Reportes report = null;
        Logger logger = new Logger();
        List<Reportes> reportes = new ArrayList<>();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement cs = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_PROCESO_CONCL_FICHAS_SP(?,?,?)}");
                cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                cs.execute();

                codError = cs.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = cs.getString("paMjeDescError");
                    ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                    while (rs.next()) {
                        report = new Reportes();
                        report.setCodError(codError);
                        report.setMsjError(msjError);
                        report.setNombre(rs.getString("NOMBRE"));
                        report.setPreproc(Integer.toString(rs.getInt("PRE_PROCESO_CONCLUIDO")));

                        reportes.add(report);
                    }
                } else {
                    report = new Reportes();
                    msjError = cs.getString("paMjeDescError");
                    report.setMsjError(msjError);
                    report.setCodError(-1);
                    reportes.add(report);
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                cs.close();

            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                report = new Reportes();
                report.setMsjError(msjError);
                report.setCodError(-2);
                reportes.add(report);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            report = new Reportes();
            msjError = Conexion.getConnectionErrorMessage();
            report.setMsjError(msjError);
            report.setCodError(-3);
            reportes.add(report);
        }
        return reportes;
    }

    public static List<Reportes> statusFichas(String username, String password) {
        int codError;
        String msjError;
        Reportes report;
        Logger logger = new Logger();
        List<Reportes> reportes = new ArrayList<>();

        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement cs = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_REP_STATUS_FICHAS_SP(?,?,?)}");
                cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                cs.execute();

                codError = cs.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = cs.getString("paMjeDescError");
                    ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                    while (rs.next()) {
                        report = new Reportes();
                        report.setMsjError(msjError);
                        report.setCodError(codError);
                        report.setNombre(rs.getString("NOMBRE"));
                        report.setPreficha(rs.getString("PREFICHAS"));
                        report.setPreproc(rs.getString("PRE_PROCESO_CONCLUIDO"));
                        report.setPrefpagadas(rs.getString("PREFICHAS_PAGADAS"));

                        reportes.add(report);
                    }
                } else {
                    msjError = cs.getString("paMjeDescError");
                    report = new Reportes();
                    report.setMsjError(msjError);
                    report.setCodError(-1);
                    reportes.add(report);
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                cs.close();

            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                report = new Reportes();
                report.setMsjError(msjError);
                report.setCodError(-2);
                reportes.add(report);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            msjError = Conexion.getConnectionErrorMessage();
            report = new Reportes();
            report.setMsjError(msjError);
            report.setCodError(-3);
            reportes.add(report);
        }
        return reportes;
    }

    public static Reportes preFichas(String username, String password) {
        int codError;
        String msjError;
        Reportes report = new Reportes();
        Logger logger = new Logger();

        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement cs = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_TOTAL_PREFICHAS_ASP_SP(?,?,?)}");
                cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                cs.execute();

                codError = cs.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = cs.getString("paMjeDescError");
                    ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
                    rs.next();
                    report.setNombre(rs.getString("FICHAS_TEC"));
                    report.setMsjError(msjError);
                    report.setCodError(codError);
                    report.setPrefpagadas(rs.getString("FICHAS_PAGADAS"));
                    report.setPreproc(rs.getString("FICHAS_PEND_X_PAGAR"));
                    report.setPreficha(rs.getString("FICHAS_DISPONIBLES"));
                } else {
                    msjError = cs.getString("paMjeDescError");
                    report.setMsjError(msjError);
                    report.setCodError(-1);
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                cs.close();

            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError();
                report.setMsjError(msjError);
                report.setCodError(-2);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
            msjError = Conexion.getConnectionErrorMessage();
            report.setMsjError(msjError);
            report.setCodError(-3);
        }
        return report;
    }
}
