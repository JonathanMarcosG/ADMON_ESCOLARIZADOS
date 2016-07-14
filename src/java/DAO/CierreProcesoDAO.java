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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.ittoluca.logutils.Logger;
import oracle.jdbc.driver.OracleTypes;

/**
 *
 * @author sony
 */
public class CierreProcesoDAO {

    public static DatosPersonales obtenetDatosPersonales(String username, String password, String preficha) {
        int codError;
        String msjError;
        DatosPersonales dp = new DatosPersonales();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {

                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_DATOS_PERSONALES_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

                call.execute();
                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");

                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    dp.setCodError(-1);
                    while (rset.next()) {
                        dp.setCodError(codError);
                        dp.setMsjError(msjError);
                        dp.setPreficha(Integer.parseInt(rset.getObject("PREFICHA").toString()));
                        dp.setMatricula(rset.getObject("FICHA").toString());
                        dp.setFolio_ceneval(rset.getObject("FOLIO_CENEVAL").toString());
                        dp.setLugarFechaA(rset.getObject("LUGAR_FECHA_A").toString());
                        dp.setLugarFechaB(rset.getObject("LUGAR_FECHA_B").toString());
                        dp.setCurp(rset.getObject("CURP").toString());
                        dp.setApellido_pat(rset.getObject("APELLIDO_PAT").toString());
                        dp.setApellido_mat(rset.getObject("APELLIDO_MAT").toString());
                        dp.setNombre(rset.getObject("NOMBRE").toString());
                        dp.setFec_nacimiento(rset.getObject("FEC_NACIMIENTO").toString());
                        dp.setEdad(rset.getObject("EDAD").toString());
                        dp.setNacionalidad(rset.getObject("NACIONALIDAD_FK").toString()); //FK
                        if (dp.getNacionalidad().contentEquals("MEX")) {
                            dp.setPaisNac(rset.getObject("PAIS").toString());
                            dp.setIdEdoNac(rset.getObject("ESTADO_NAC_FK").toString()); //FK
                            dp.setEdoNacimiento(rset.getObject("NOMBRE").toString());
                            dp.setMunNac(rset.getObject("MUNICIPIO_NAC_FK").toString()); //FK
                            dp.setLocalidad(rset.getObject("LOCALIDAD_NAC_FK").toString()); //FK
                        } else {

                            dp.setIdEdoNac("Extranjero"); //FK
                            dp.setEdoNacimiento("Extranjero");
                            dp.setMunNac("Extranjero"); //FK
                            dp.setLocalidad("Extranjero"); //FK
                        }

                        dp.setSexo(rset.getObject("SEXO").toString());
                        dp.setEdo_civil(rset.getObject("EDO_CIVIL").toString());
                        dp.setTipo_sangre(rset.getObject("TIPO_SANGRE").toString());
                        dp.setCap_diferente(rset.getObject("CAP_DIFERENTE").toString());
                        dp.setCorreo(rset.getObject("CORREO").toString());
                        dp.setReferencia(rset.getObject("REFERENCIA_BANCARIA").toString());
                        dp.setIdAsp(rset.getObject("ID_ASPIRANTE").toString());
                    }
                    call.close();
                } else {
                    String logMessage = codError + "->" + msjError;
                    dp.setCodError(-1);
                    dp.setMsjError(logMessage);
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                dp.setCodError(-2);
                dp.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            dp.setCodError(-3);
            dp.setMsjError(msjError);
        }
        return dp;
    }

    public static EscuelaProcedencia obtenerDatosEP(String username, String password, String preficha) {
        int codError;
        String msjError;
        EscuelaProcedencia ep = new EscuelaProcedencia();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {

                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_DATOS_ESC_PROC_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");

                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    ep.setCodError(-1);
                    while (rset.next()) {
                        ep.setCodError(codError);
                        ep.setMsjError(msjError);
                        ep.setIdAspirante(rset.getObject("ID_ASPIRANTE").toString());
                        ep.setClasificacion((rset.getObject("CLASIFICACION_ESC_FK").toString()));
                        ep.setNomEstado((rset.getObject("NOMBRE_ESTADO").toString()));
                        ep.setClaveEscuela((rset.getObject("CLAVE_CCT").toString()));
                        ep.setTipoEscuela((rset.getObject("TIPO_ESCUELA").toString()));
                        ep.setNomMpio(rset.getObject("MUNICIPIO").toString());
                        ep.setFechaIni((rset.getObject(7).toString()));
                        ep.setFechaFin((rset.getObject(8).toString()));
                        ep.setPromedio((rset.getObject("PROMEDIO").toString()));
                    }
                    call.close();
                } else {
                    String logMessage = codError + "->" + msjError;
                    ep.setCodError(-1);
                    ep.setMsjError(logMessage);
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                ep.setCodError(-2);
                ep.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
            ep.setCodError(-3);
            ep.setMsjError(msjError);
        }
        return ep;
    }

    public static List<SelectCarreras> llenaOpcionesCarreras(String username, String password, String preficha) {
        int codError;
        String msjError;
        List<SelectCarreras> opciones = new ArrayList<>();
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_CARRERAS_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);
                call.execute();
                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");

                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        SelectCarreras sc = new SelectCarreras();
                        sc.setCodError(codError);
                        sc.setMsjError(msjError);
                        sc.setClaveCarrera(rset.getInt("ID_CARRERA"));
                        sc.setNombre(rset.getString("NOMBRE_CARRERA"));
                        sc.setIdPais(rset.getString("OPCION"));
                        opciones.add(sc);
                    }
                    call.close();
                } else {
                    SelectCarreras sc = new SelectCarreras();
                    String logMessage = codError + "->" + msjError;
                    sc.setCodError(-1);
                    sc.setMsjError(logMessage);
                    opciones.add(sc);
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                }
            } catch (SQLException ex) {
                SelectCarreras sc = new SelectCarreras();
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                sc.setCodError(-2);
                sc.setMsjError(logger.getMensajeError());
                opciones.add(sc);
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            SelectCarreras sc = new SelectCarreras();
            msjError = Conexion.getConnectionErrorMessage();
            sc.setCodError(-3);
            sc.setMsjError(msjError);
            opciones.add(sc);
        }
        return opciones;
    }

    public static List<SelectCarreras> llenarListas(String username, String password, int idCatalogo, int filtro) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        List<SelectCarreras> opciones = new ArrayList();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.CATALOGOS_ASPIRANTES_PQ.GET_CATALOGO_SP(?,?,?,?,?)}");
                //Registro de parámetros de entrada
                call.setInt("paOpcionCatalogo", idCatalogo);
                call.setInt("paFiltroFk", filtro);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paDescError", oracle.jdbc.OracleTypes.VARCHAR);
                call.execute();

                codError = call.getInt("paCodError");
                msjError = call.getString("paDescError");
                if (codError == 0) {
                    ResultSet rs = (ResultSet) call.getObject("paCurRetorno");
                    while (rs.next()) {
                        SelectCarreras sc = new SelectCarreras();
                        sc.setCodError(codError);
                        sc.setMsjError(msjError);
                        sc.setClaveCarrera(rs.getInt(1));
                        sc.setNombre(rs.getString(2));
                        sc.setIdPais(rs.getString(1));
                        opciones.add(sc);
                    }
                    //Cierre del cursor.
                    rs.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    //Gestión de la respuesta al usuario...
                    //Se envía el mensajeError.
                }
            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
        }
        return opciones;
    }

    public static List<SelectCarreras> llenarListaPais(String username, String password, int idCatalogo, int filtro) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        List<SelectCarreras> opciones = new ArrayList();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.CATALOGOS_ASPIRANTES_PQ.GET_CATALOGO_SP(?,?,?,?,?)}");
                //Registro de parámetros de entrada
                call.setInt("paOpcionCatalogo", idCatalogo);
                call.setInt("paFiltroFk", filtro);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paDescError", oracle.jdbc.OracleTypes.VARCHAR);
                call.execute();

                codError = call.getInt("paCodError");
                if (codError == 0) {
                    ResultSet rs = (ResultSet) call.getObject("paCurRetorno");
                    while (rs.next()) {

                        SelectCarreras sc = new SelectCarreras();
                        sc.setIdPais(rs.getString(1));
                        sc.setClaveCarrera(0);
                        sc.setNombre(rs.getString(2));
                        opciones.add(sc);
                    }
                    //Cierre del cursor.
                    rs.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    //Gestión de la respuesta al usuario...
                    //Se envía el mensajeError.
                }
            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                //Gestión de la respuesta para el usuario.
                //Se obtiene la traducción del error con: logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            //Sólo se gestiona la respuesta que se dará al usuario, la librería ya loguea los errores al crear la conexión.
            //El error traducido está en Conexion.getConnectionErrorMessage();
        }
        return opciones;
    }

    public static String verificacionEnSeguimiento(String username, String password, String idAspirante, int opcion) {
        int ceneval = -4;
        int codError;
        String msjError = null;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {

                CallableStatement call = conn.prepareCall("{?= call FICHAS.PQ_GET_ASPIRANTE_3.CHECK_SEGUIMIENTO_ASP_FN(?,?)}");
                call.setInt(2, Integer.parseInt(idAspirante));
                call.setInt(3, opcion);
                call.registerOutParameter(1, OracleTypes.NUMBER);

                call.execute();

                codError = call.getInt(1);

                switch (codError) {
                    case -1:
                        msjError = ("Ocurrio un error en el proceso");
                        ceneval = -1;
                        break;
                    case -2:
                        msjError = ("No es un parametro permitido");
                        ceneval = -2;
                        break;
                    case 0:
//                        msjError = ("El aspirante no tiene registro");
                        msjError = "ninguno";
                        ceneval = 0;
                        break;
                    case 1:
//                        msjError = ("El aspirante ya fue registrado");
                        msjError = "ninguno";
                        ceneval = 1;
                        break;
                    default:
                        break;
                }
                call.close();
            } catch (SQLException ex) {
                //Loggeo del error.
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                System.out.println(logger.getMensajeError());
                msjError = logger.getMensajeError();

            } finally {
                //El bloque finally es importante pues aquí se garantiza que no se dejen conexiones abiertas.
                Conexion.cerrarConexion(conn);
            }
        } else {
            msjError = Conexion.getConnectionErrorMessage();
        }
        return (msjError + "&" + ceneval);
    }

    public static EnEmergencia obtenerDatosCE(String username, String password, String preficha) {
        EnEmergencia ee = new EnEmergencia();
        int codError;
        String msjError;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_CONTACTO_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
                call.execute();
                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        ee.setCodError(codError);
                        ee.setNomAp(rset.getObject("NOMBRE").toString());
                        ee.setEstado(rset.getObject("ESTADO_FK").toString());
                        ee.setCiudad(rset.getObject("MUNICIPIO_FK").toString());
                        ee.setColonia(rset.getObject("COLONIA_POB").toString());
                        ee.setCalle(rset.getObject("CALLE").toString());

                        if (rset.getObject("NO_INTERIOR") == null) {
                            ee.setNoInterior("0");
                        } else {
                            ee.setNoInterior(rset.getObject("NO_INTERIOR").toString());
                        }

                        ee.setNoExterior(rset.getObject("NO_EXTERIOR").toString());
                        ee.setCenTrabajo(rset.getObject("CENTRO_TRABAJO").toString());
                        ee.setTelTrabajo(rset.getObject("TEL_CENTRO_TRABAJO").toString());
                        ee.setTelfijo(rset.getObject("TEL_FIJO").toString());
                        ee.setTelcel(rset.getObject("TEL_CEL").toString());
                        ee.setPreficha(rset.getObject("ID_ASPIRANTE").toString());
                    }
                    //Cierre del cursor.
                    rset.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    ee.setCodError(-1);
                    ee.setMsjError(msjError);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                ee.setCodError(-2);
                ee.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            ee.setCodError(-3);
            ee.setMsjError(Conexion.getConnectionErrorMessage());
        }
        return ee;
    }

    public static DatosSocioeconomicos obtenerDatosSE(String username, String password, String preficha) {

        int codError;
        String msjError;
        Logger logger = new Logger();
        DatosSocioeconomicos ds = new DatosSocioeconomicos();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_SOCIECONOMICOS_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                msjError = call.getString("paMjeDescError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        ds.setCodError(codError);
                        ds.setMsjError("ninguno");
                        ds.setNomPadre(rset.getObject("NOMBRE_PADRE").toString());
                        ds.setVivePadre(rset.getObject("VIVE_PADRE").toString());
                        ds.setNomMadre(rset.getObject("NOMBRE_MADRE").toString());
                        ds.setViveMadre(rset.getObject("VIVE_MADRE").toString());
                        ds.setBeca(rset.getObject("TIPO_BECA").toString());
                        ds.setZonaProcedencia(rset.getObject("ZONA_PROCEDENCIA").toString());
                        ds.setMaxEstudiosPadre(Integer.parseInt(rset.getObject("ESTUDIOS_PADRE_FK").toString()));
                        ds.setMaxEstudiosMadre(Integer.parseInt(rset.getObject("ESTUDIOS_MADRE_FK").toString()));
                        ds.setIngresosTotales(rset.getObject("INGRESOS_TOTALES").toString());
                        ds.setDependeDe(rset.getObject("DEPENDE_ECON_FK").toString());
                        ds.setOcupacionPadre(Integer.parseInt(rset.getObject("OCUPACION_PADRE_FK").toString()));
                        ds.setOcupacionMadre(Integer.parseInt(rset.getObject("OCUPACION_MADRE_FK").toString()));
                        ds.setTipoCasa(rset.getObject("TIPO_CASA_FK").toString());
                        ds.setNoPersonasCasa(Integer.parseInt(rset.getObject("PERSONAS_VIVEN_CASA").toString()));
                        ds.setCuartosCasa(rset.getObject("CUARTOS_CASA").toString());
                        ds.setProgOportunidades(rset.getObject("PROG_OPORTUNIDADES").toString());
                        ds.setViveCon(rset.getObject("VIVE_CON_FK").toString());
                        ds.setDependeEconomicamente(rset.getObject("NUM_PER_DEPENDECON").toString());
                        ds.setIdAspirante(Integer.parseInt(rset.getObject("ID_ASPIRANTE").toString()));

                    }
                    //Cierre del cursor.
                    rset.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    ds.setCodError(-1);
                    ds.setMsjError(msjError);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                ds.setCodError(-2);
                ds.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        } else {
            ds.setCodError(-3);
            ds.setMsjError(Conexion.getConnectionErrorMessage());
        }
        return ds;
    }

    public static DatosDelDomicilio obtenerDatosDD(String username, String password, String preficha) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        DatosDelDomicilio ddd = new DatosDelDomicilio();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_DOMICILIO_ASP_SP(?,?,?,?)}");
                call.setString("paPreficha", preficha);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);

                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        ddd.setCodError(codError);
                        ddd.setMsjError("ninguno");
                        ddd.setEstadoFK(Integer.parseInt(rset.getObject("ESTADO_VIVE_ACTUAL_FK").toString()));
                        ddd.setEstado(rset.getObject("ESTADO").toString());
                        ddd.setMunicipio(rset.getObject("MUNICIPIO_VIVE_FK").toString());
                        ddd.setLocalidad(rset.getObject("LOCALIDAD_VIVE_FK").toString());
                        ddd.setCalle(rset.getObject("CALLE").toString());
                        if (rset.getObject("NO_INTERIOR") == null) {
                            ddd.setNoInterior(0);
                        } else {
                            ddd.setNoInterior(Integer.parseInt(rset.getObject("NO_INTERIOR").toString()));

                        }
                        ddd.setNoExterior(Integer.parseInt(rset.getObject("NO_EXTERIOR").toString()));
                        ddd.setColonia(rset.getObject("COLONIA_POB").toString());
                        ddd.setCp(Integer.parseInt(rset.getObject("COD_POST").toString()));
                        ddd.setTelFijo(rset.getObject("TEL_FIJO").toString());
                        ddd.setTelCel(rset.getObject("TEL_CEL").toString());
                        ddd.setPreficha(rset.getObject("ID_ASPIRANTE").toString());
                    }
                    //Cierre del cursor.
                    rset.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    msjError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + msjError;
                    logger.registrarError(Logger.GRAVE, logMessage, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                    ddd.setCodError(-1);
                    ddd.setMsjError(msjError);
                }
            } catch (SQLException ex) {
                logger.registrarErrorSQL(ex, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO, username);
                ddd.setCodError(-2);
                ddd.setMsjError(logger.getMensajeError());
            } finally {
                Conexion.cerrarConexion(conn);
            }
        }
        return ddd;
    }

    public static List<String> HorariosCen(String username, String password) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        List<String> horarios = new ArrayList<>();
        String horario;
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_AULA_HORARIOS_SP(?,?,?,?)}");
                call.setInt("paOpcion", 1);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);
//            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        horario = rset.getString("LUGAR");
                        horarios.add(horario);
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
        return horarios;
    }

    public static List<String> HorariosMat(String username, String password) {
        int codError;
        String msjError;
        Logger logger = new Logger();
        List<String> horarios = new ArrayList<>();
        String horario;
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_AULA_HORARIOS_SP(?,?,?,?)}");
                call.setInt("paOpcion", 2);
                call.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                call.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                call.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);
//            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
                call.execute();

                codError = call.getInt("paCodigoError");
                if (codError == 0) {
                    ResultSet rset = (ResultSet) call.getObject("paCurRetorno");
                    while (rset.next()) {
                        horario = rset.getString("LUGAR");
                        horarios.add(horario);
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
        return horarios;
    }
}
