/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.DatosDelDomicilio;
import Beans.DatosPersonales;
import Beans.DatosSocioeconomicos;
import Beans.EnEmergencia;
import Beans.EscuelaProcedencia;
import Beans.SelectCarreras;
import Utils.Constants;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Desarrollo de sistem
 */
public class ActualizarDAO {

    public static String insertarCeneval(String username, String password, String idAspirante, String folio) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_FOLIO_CENEVAL_ASP_SP(?,?,?,?)}");
                call.setString("paIdAspirante", idAspirante);
                call.setString("paFolioCeneval", folio);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "ninguno" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + "-1";
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
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

    public static String actualizarDP(String username, String password, DatosPersonales dp) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_PERSONALDATA_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                call.setString("paCurp", dp.getCurp());
                call.setString("paNombre", dp.getNombre());
                call.setString("paApellidoPat", dp.getApellido_pat());
                call.setString("paApellidoMat", dp.getApellido_mat());
                call.setString("paFechaDeNacimiento", dp.getFec_nacimiento());
                call.setString("paPaisNacimiento", dp.getPaisNac());
                if (dp.getPaisNac().contentEquals("MEX")) {
                    call.setInt("paEstadoNacimiento", Integer.parseInt(dp.getIdEdoNac()));
                    call.setInt("paMunicipioNac", Integer.parseInt(dp.getMunNac()));
                    call.setInt("paLocalidadNac", Integer.parseInt(dp.getLocalidad()));
                } else {
                    call.setString("paEstadoNacimiento", "");
                    call.setString("paMunicipioNac", "");
                    call.setString("paLocalidadNac", "");
                }

                call.setString("paSexo", dp.getSexo());
                call.setString("paEdoCivil", dp.getEdo_civil());
                call.setString("paTipoSangre", dp.getTipo_sangre());
                call.setString("paCapDiferente", dp.getCap_diferente());
                call.setString("paCorreo", dp.getCorreo());

                call.setInt("paIdAspirante", Integer.parseInt((dp.getIdAsp())));
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();
                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
        return msjError;
    }

    public static String actualizarCarreras(String username, String password, SelectCarreras ac) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_CARRERA_ASP_SP(?,?,?,?,?)}");
                call.setString("paIdAspirante", ac.getIdPais());
                call.setString("paCarrera", ac.getNombre());
                call.setString("paOpcion", "" + ac.getClaveCarrera());
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();
                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
        return msjError;
    }

    public static String actualizarEP(String username, String password, EscuelaProcedencia ep) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_ESCPROC_ASP_SP(?,?,?,?,?,?,?,?,?,?,?)}");
                call.setInt("paIdAspirante", Integer.parseInt(ep.getIdAspirante()));
                call.setInt("paEscuela", Integer.parseInt(ep.getClasificacion()));
                call.setString("paClaveEscuela", ep.getClaveEscuela());
                call.setString("paTipoEscuela", ep.getTipoEscuela());
                call.setInt("paMesIniEsc", Integer.parseInt(ep.getFechaIni().charAt(0) + ""));
                call.setInt("paMesFinEsc", Integer.parseInt(ep.getFechaFin().charAt(0) + ""));
                call.setInt("paAñoIniEsc", Integer.parseInt(ep.getFechaIni().substring(2, 6)));
                call.setInt("paAñoFinEsc", Integer.parseInt(ep.getFechaFin().substring(2, 6)));
                call.setInt("paPromedio", Integer.parseInt(ep.getPromedio()));

                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
        return msjError;
    }

    public static String actualizarDD(String username, String password, DatosDelDomicilio ddd) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);

        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_DOMICILIO_ASP_SP(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                call.setString("paIdAspirante", ddd.getPreficha());
                call.setString("paEstadoViveActual", ddd.getEstado());
                call.setString("paMunicipioVive", ddd.getMunicipio());
                call.setString("paLocalidadVive", ddd.getLocalidad());
                call.setString("paCalle", ddd.getCalle());
                call.setString("paNoInterior", ddd.getNoInterior() + "");
                call.setString("paNoExterior", ddd.getNoExterior() + "");
                call.setString("paColoniaPob", ddd.getColonia());
                call.setString("paCodPost", ddd.getCp() + "");
                call.setString("paTelFijo", ddd.getTelFijo());
                call.setString("paTelCel", ddd.getTelCel());

                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto"+"&"+ codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
        return msjError;
    }

    public static String actualizarSE(String username, String password, DatosSocioeconomicos ds) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);

        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_REG_SOCIECONOMICO_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                call.setString("paIdAspirante", ds.getIdAspirante() + "");
                call.setString("paNombrePadre", ds.getNomPadre());
                call.setString("paVivePadre", ds.getVivePadre());
                call.setInt("paOcupacionPadre", ds.getOcupacionPadre());
                call.setInt("paEstudiosPadre", ds.getMaxEstudiosPadre());
                call.setString("paNombreMadre", ds.getNomMadre());
                call.setString("paViveMadre", ds.getViveMadre());
                call.setInt("paOcupacionMadre", ds.getOcupacionMadre());
                call.setInt("paEstudiosMadre", ds.getMaxEstudiosMadre());
                call.setString("paIngresosTot", ds.getIngresosTotales());
                call.setString("paTipoDeCasa", ds.getTipoCasa());
                call.setString("paCuartosCasa", ds.getCuartosCasa());
                call.setString("paNumPerDepEcon", ds.getDependeEconomicamente());
                call.setInt("paPersonasVCasa", ds.getNoPersonasCasa());
                call.setString("paPrgOportunid", ds.getProgOportunidades());
                call.setString("paZonaProced", ds.getZonaProcedencia());
                call.setString("paTipoBeca", ds.getBeca());

                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto"+"&"+ codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
        return msjError;
    }

    public static String actualizarCE(String username, String password, EnEmergencia ce) {
        int codError;
        String msjError = "";
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);

        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_REG_CONTACTO_ASP_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                call.setString("paIdAspirante", ce.getPreficha());
                call.setString("paNombreContacto", ce.getNomAp());
                call.setString("paEstado", ce.getEstado());
                call.setString("paMunicipioFk", ce.getCiudad());
                call.setString("paColonia", ce.getColonia());
                call.setString("paCalle", ce.getCalle());
                call.setString("paNo_Interior", ce.getNoInterior());
                call.setString("paNo_Exterior", ce.getNoExterior());
                call.setString("paTel_Fijo", ce.getTelfijo());
                call.setString("paTel_Cel", ce.getTelcel());
                call.setString("paCentroTrabajo", ce.getCenTrabajo());
                call.setString("paTel_Centro_Trab", ce.getTelTrabajo());

                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    msjError = "correcto" + "&" + codError;
                } else {
                    msjError = call.getString("paMjeDescError") + "&" + -1;
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
                call.close();
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                msjError = logger.getMensajeError() + "&" + -2;
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage() + "&" + -3;
        }
    return msjError ;
}
}
