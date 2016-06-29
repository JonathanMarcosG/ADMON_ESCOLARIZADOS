/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import Beans.ClaveCCT;
import Beans.ConfigurarPeriodo;
import Beans.DatosDelDomicilio;
import Beans.DatosPDF;
import Beans.DatosPersonales;
import Beans.DatosSocioeconomicos;
import Beans.EnEmergencia;
import Beans.EscuelaProcedencia;
import Beans.Escuelas;
import Beans.LiberacionPago;
import Beans.SeguimientoDelAlumno;
import Beans.SelectCarreras;
import Beans.confConv;
import Beans.Reportes;
import itt.web.conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Rocio
 */
public class IngresoAbd {

    private ResultSet result;
    private PreparedStatement Statement;
    private boolean conStatus;
    String user;
    String pass;
    String BaseDeDatos;
    private String fechaI = "";
    private String fechaF = "";
    private String errorInsert = "ninguno";
    private int error = -1;
    private String errorInsert2 = "ninguno";
    private int error2 = -1;
    private String errorCE;
    CallableStatement cs = null;

    public IngresoAbd(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    public SeguimientoDelAlumno Notificaciones(String parametroInicial, int bandera) {
        SeguimientoDelAlumno sda = new SeguimientoDelAlumno();
        Connection laConexion = null;

        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_NOTIFICACION_ASP_SP(?,?,?,?,?)}");
            cs.setString("paParametro", parametroInicial);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.setInt("paBandera", bandera);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
                int respuesta = 0;
                while (rs.next()) {

                    sda.setIdAsp(rs.getObject("ID_ASPIRANTE").toString());
                    sda.setPreficha(Integer.parseInt(rs.getObject("PREFICHA").toString()));
                    sda.setFichaAl(rs.getString("FICHA"));
                    sda.setNombre((rs.getObject("NOMBRE").toString()));

                    sda.setApellidoP((rs.getObject("APELLIDO_PAT").toString()));
                    sda.setApellidoM((rs.getObject("APELLIDO_MAT").toString()));
                    sda.setCorreo((rs.getObject("CORREO").toString()));
                    sda.setCurp((rs.getObject("CURP").toString()));
                    sda.setCarrera((rs.getObject("NOMBRE_CARRERA").toString()));
                    sda.setPago(Integer.parseInt(rs.getObject("PAGO_PROCESADO").toString()));
                    sda.setAltaCeneval(Integer.parseInt(rs.getObject("ALTA_CENEVAL").toString()));

                    respuesta = 1;
                }

                if (respuesta == 0) {
                    errorInsert = "No se encontraron coincidencias";
                }

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
        }catch (SQLException ex) {
            errorInsert = ex.getMessage();

        } finally {
            if (laConexion != null) {
                
               Conexion.cerrarConexion(laConexion);
            }
        }
        return sda;
    }

    public SeguimientoDelAlumno obtenerSeguimiento(String parametroInicial, int bandera) {

        SeguimientoDelAlumno sda = new SeguimientoDelAlumno();
        Connection laConexion = null;

        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_SEGUIMIENTO_ASP_SP(?,?,?,?,?)}");
            cs.setString("paParametro", parametroInicial);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.setInt("paBandera", bandera);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
                int respuesta = 0;
                while (rs.next()) {

                    sda.setNombre(rs.getString("NOMBRE_ASPIRANTE"));
                    sda.setCurp(rs.getString("CURP"));
                    sda.setCorreo(rs.getString("CORREO"));
                    sda.setReferencia(rs.getString("REFERENCIA_BANCARIA"));
                    sda.setPreficha(Integer.parseInt(rs.getString("PREFICHA")));
                    sda.setRegPreficha(Integer.parseInt(rs.getString("REGISTRO_PREFICHA")));
                    sda.setRegFicha(Integer.parseInt(rs.getString("FICHA")));
                    sda.setPago(Integer.parseInt(rs.getString("PAGO_PROCESADO")));
                    sda.setFolioCeneval(Integer.parseInt(rs.getString("VALID_FOLIO_CENEVAL")));
                    sda.setAltaCeneval(Integer.parseInt(rs.getString("ALTA_CENEVAL")));
                    sda.setFechaModif(rs.getString("ULTIMA_ACTUALIZACION"));
                    sda.setUsuarioModif(rs.getString("USUARIO_ACTUALIZA"));
                    respuesta = 1;
                }

                if (respuesta == 0) {
                    errorInsert = "No se encontraron coincidencias";
                }

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
               
            }
        }
        return sda;
    }

    public String urlCeneval() {
        String url = "";
        Connection laConexion = null;
        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs =laConexion.prepareCall("{?=call FICHAS.PQ_GET_ADMIN_2.GET_LIGA_CENEVAL_FN()}");
            cs.registerOutParameter(1, OracleTypes.VARCHAR);
            cs.execute();
            url = cs.getString(1);
            cs.close();

        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();
        }  finally {
            if (laConexion != null) {
                
                    Conexion.cerrarConexion(laConexion);
               
            }
        }
        return url;
    }

    public DatosPDF datos_pdfAspirante(String folio) {
        DatosPDF datos = new DatosPDF();
        Connection con = null;
        try {
            datos.setFolio(folio);
            String resultado_error = "";
            con = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = con.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_3.GET_GENERA_SOLICITUD_ASP_SP(?,?,?,?)}");

            cs.setString("paFolioCenevalAsp", folio);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            resultado_error = cs.getString("paCodigoError");
            if ("0".equals(resultado_error)) {
                ResultSet rset = (ResultSet) cs.getObject("paCurRetorno");
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
            }
            cs.close();

        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();

        }  finally {
            if (con != null) {
                
                    Conexion.cerrarConexion(con);
               
            }
        }
        return datos;
    }

    public DatosPDF datos_pdf(String folio) {
        DatosPDF datos = new DatosPDF();

        Connection con = null;
        try {
            datos.setFolio(folio);
            String resultado_error = "";
            con = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = con.prepareCall("{call FICHAS.PQ_GET_ADMIN_2.GET_GENERA_SOLICITUD_ASP_SP(?,?,?,?)}");

            cs.setString("paFolioCenevalAsp", folio);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            resultado_error = cs.getString("paCodigoError");
            if ("0".equals(resultado_error)) {
                ResultSet rset = (ResultSet) cs.getObject("paCurRetorno");
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
                    datos.setPeriodoConcursa(rset.getObject("PERIODO_CONSURSA").toString());
                }
            }
            cs.close();
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();

        }  finally {
            if (con != null) {
                
                    Conexion.cerrarConexion(con);
               
            }
        }
        return datos;
    }

    public void insertarCeneval(String idAspirante, String folio) {
        Connection laConexion = null;
        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = null;

            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_FOLIO_CENEVAL_ASP_SP(?,?,?,?)}");

           
            cs.setString("paIdAspirante", idAspirante);
            cs.setString("paFolioCeneval", folio);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
            cs.execute();
            error = (cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

            } else {
                errorInsert = cs.getString(4);

            }
            cs.close();
        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
    }

    public ConfigurarPeriodo verificarPeriodo() {
        ConfigurarPeriodo cp = new ConfigurarPeriodo();
        CallableStatement CS = null;
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs =  laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_VIGENCIA_CONVOC_SP(?,?,?,?,?)}");

            cs.registerOutParameter("paFechaInicio", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaFin", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaActual", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            error = (cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                cp.setFechaInicio(cs.getString("paFechaInicio"));
                cp.setFechaFin(cs.getString("paFechaFin"));

            } else {
                errorInsert = cs.getString("paMjeDescError");
            }
            cs.close();

        }  catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                
                    Conexion.cerrarConexion(laConexion);
               
            }
        }

        return cp;
    }

    public void vigenciaConvoc() {

        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_VIGENCIA_CONVOC_SP(?,?,?,?,?)}");

            cs.registerOutParameter("paFechaInicio", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaFin", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaActual", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            error = (cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                setFechaI(cs.getString("paFechaInicio"));
                setFechaF(cs.getString("paFechaFin"));

            } else {
                errorInsert = cs.getString("paMjeDescError");
            }
            cs.close();


        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
               
            }
        }

    }

    public int altaCENEVAL(String idAspirante) {
        int ceneval = -4;
        errorCE = "bien";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.GENERA_ALTA_CENEVAL_SP(?,?,?)}");
            cs.setString("paIdAspirante", idAspirante);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError") + 24);

            cs.close();

            if (getError() == 0) {
                errorInsert = "ninguno";
                ceneval = 0;
            }

        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

        return ceneval;

    }

    public int verificacionEnSeguimiento(String idAspirante, int opcion) {
        int ceneval = -4;
        errorCE = "bien";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{? = call FICHAS.PQ_GET_ASPIRANTE_3.CHECK_SEGUIMIENTO_ASP_FN(?,?)}");
            cs.setInt(2, Integer.parseInt(idAspirante));
            cs.setInt(3, opcion);
            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.execute();

            int resultado = cs.getInt(1);
            cs.close();

            if (resultado == -1) {
                setErrorCE("Ocurrio un error en el proceso");
                ceneval = -1;
            } else if (resultado == -2) {
                setErrorCE("No es un parametro permitido");
                ceneval = -2;
            } else if (resultado == 0) {
                setErrorCE("El aspirante no tiene registro");
                ceneval = 0;
            } else if (resultado == 1) {
                setErrorCE("El aspirante ya fue registradoF");
                ceneval = 1;
            }

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return ceneval;
    }

    public String actualizarDP(DatosPersonales dp) {
        String errorInterno = "correcto";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_PERSONALDATA_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString("paCurp", dp.getCurp());
            cs.setString("paNombre", dp.getNombre());
            cs.setString("paApellidoPat", dp.getApellido_pat());
            cs.setString("paApellidoMat", dp.getApellido_mat());
            cs.setString("paFechaDeNacimiento", dp.getFec_nacimiento());
            cs.setString("paPaisNacimiento", dp.getPaisNac());

            if (dp.getPaisNac().contentEquals("MEX")) {
                cs.setInt("paEstadoNacimiento", Integer.parseInt(dp.getIdEdoNac()));
                cs.setInt("paMunicipioNac", Integer.parseInt(dp.getMunNac()));
                cs.setInt("paLocalidadNac", Integer.parseInt(dp.getLocalidad()));
            } else {
                cs.setString("paEstadoNacimiento", "");
                cs.setString("paMunicipioNac", "");
                cs.setString("paLocalidadNac", "");
            }

            cs.setString("paSexo", dp.getSexo());
            cs.setString("paEdoCivil", dp.getEdo_civil());
            cs.setString("paTipoSangre", dp.getTipo_sangre());
            cs.setString("paCapDiferente", dp.getCap_diferente());
            cs.setString("paCorreo", dp.getCorreo());

            cs.setInt("paIdAspirante", Integer.parseInt((dp.getIdAsp())));
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            error = cs.getInt("paCodigoError");

            if (error == 0) {
                errorInsert = "ninguno";
            } else {
                errorInsert = cs.getString(17);

            }
            cs.close();

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }

        return errorInterno;
    }

    public String actualizarCarreras(SelectCarreras ac) {
        String errorInterno = "correcto";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_CARRERA_ASP_SP(?,?,?,?,?)}");
            cs.setString("paIdAspirante", ac.getIdPais());
            cs.setString("paCarrera", ac.getNombre());
            cs.setString("paOpcion", "" + ac.getClaveCarrera());
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));
            cs.close();

            if (getError() == 0) {
                errorInsert = "ninguno";
            }

        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return errorInterno;
    }

    public String actualizarDD(DatosDelDomicilio ddd) {
        String errorInterno = "correcto";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_DOMICILIO_ASP_SP(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("paIdAspirante", ddd.getPreficha());
            cs.setString("paEstadoViveActual", ddd.getEstado());
            cs.setString("paMunicipioVive", ddd.getMunicipio());
            cs.setString("paLocalidadVive", ddd.getLocalidad());
            cs.setString("paCalle", ddd.getCalle());
            cs.setString("paNoInterior", ddd.getNoInterior() + "");
            cs.setString("paNoExterior", ddd.getNoExterior() + "");
            cs.setString("paColoniaPob", ddd.getColonia());
            cs.setString("paCodPost", ddd.getCp() + "");
            cs.setString("paTelFijo", ddd.getTelFijo());
            cs.setString("paTelCel", ddd.getTelCel());

            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
            } else {
                errorInsert = cs.getString("paMjeDescError");

            }
            cs.close();

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return errorInterno;
    }

    public String actualizarEP(EscuelaProcedencia ep) {
        String errorInterno = "correcto";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_1.UPDATE_REGIS_ESCPROC_ASP_SP(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt("paIdAspirante", Integer.parseInt(ep.getIdAspirante()));
            cs.setInt("paEscuela", Integer.parseInt(ep.getClasificacion()));
            cs.setString("paClaveEscuela", ep.getClaveEscuela());
            cs.setString("paTipoEscuela", ep.getTipoEscuela());
            cs.setInt("paMesIniEsc", Integer.parseInt(ep.getFechaIni().charAt(0) + ""));
            cs.setInt("paMesFinEsc", Integer.parseInt(ep.getFechaFin().charAt(0) + ""));
            cs.setInt("paAñoIniEsc", Integer.parseInt(ep.getFechaIni().substring(2, 6)));
            cs.setInt("paAñoFinEsc", Integer.parseInt(ep.getFechaFin().substring(2, 6)));
            cs.setInt("paPromedio", Integer.parseInt(ep.getPromedio()));

            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            error = cs.getInt("paCodigoError");

            if (error == 0) {
                errorInsert = "ninguno";
            } else {
                errorInsert = cs.getString("paMjeDescError");

            }
            cs.close();

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
        return errorInterno;
    }

    public String actualizarSE(DatosSocioeconomicos ds) {
        String errorInterno = "correcto";

        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_REG_SOCIECONOMICO_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("paIdAspirante", ds.getIdAspirante() + "");
            cs.setString("paNombrePadre", ds.getNomPadre());
            cs.setString("paVivePadre", ds.getVivePadre());
            cs.setInt("paOcupacionPadre", ds.getOcupacionPadre());
            cs.setInt("paEstudiosPadre", ds.getMaxEstudiosPadre());
            cs.setString("paNombreMadre", ds.getNomMadre());
            cs.setString("paViveMadre", ds.getViveMadre());
            cs.setInt("paOcupacionMadre", ds.getOcupacionMadre());
            cs.setInt("paEstudiosMadre", ds.getMaxEstudiosMadre());
            cs.setString("paIngresosTot", ds.getIngresosTotales());
            cs.setString("paTipoDeCasa", ds.getTipoCasa());
            cs.setString("paCuartosCasa", ds.getCuartosCasa());
            cs.setString("paNumPerDepEcon", ds.getDependeEconomicamente());
            cs.setInt("paPersonasVCasa", ds.getNoPersonasCasa());
            cs.setString("paPrgOportunid", ds.getProgOportunidades());
            cs.setString("paZonaProced", ds.getZonaProcedencia());
            cs.setString("paTipoBeca", ds.getBeca());

            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
            } else {
                errorInsert = cs.getString("paMjeDescError");

            }
            cs.close();
       

        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return errorInterno;
    }

    public String actualizarCE(EnEmergencia ce) {
        String errorInterno = "correcto";
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_UPDATE_ASPIRANTE_2.UPDATE_REG_CONTACTO_ASP_SP(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("paIdAspirante", ce.getPreficha());
            cs.setString("paNombreContacto", ce.getNomAp());
            cs.setString("paEstado", ce.getEstado());
            cs.setString("paMunicipioFk", ce.getCiudad());
            cs.setString("paColonia", ce.getColonia());
            cs.setString("paCalle", ce.getCalle());
            cs.setString("paNo_Interior", ce.getNoInterior());
            cs.setString("paNo_Exterior", ce.getNoExterior());
            cs.setString("paTel_Fijo", ce.getTelfijo());
            cs.setString("paTel_Cel", ce.getTelcel());
            cs.setString("paCentroTrabajo", ce.getCenTrabajo());
            cs.setString("paTel_Centro_Trab", ce.getTelTrabajo());

            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
            } else {
                errorInsert = cs.getString("paMjeDescError");

            }
            cs.close();

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return errorInterno;
    }

    public List<SelectCarreras> llenaOpcionesCarreras(String preficha) {
        List<SelectCarreras> opciones = new ArrayList<SelectCarreras>();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_CARRERAS_ASP_SP(?,?,?,?)}");
            cs.setString("paPreficha", preficha);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
            cs.execute();
            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                int i = 0;
                while (rs.next()) {

                    SelectCarreras sc = new SelectCarreras();
                    sc.setClaveCarrera(rs.getInt("ID_CARRERA"));
                    sc.setNombre(rs.getString("NOMBRE_CARRERA"));
                    sc.setIdPais(rs.getString("OPCION"));
                    opciones.add(sc);

                }
                errorInsert = "ninguno";

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString("paMjeDescError");
            }
            cs.close();
       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return opciones;
    }

    public List<SelectCarreras> llenarListas(int idCatalogo, int filtro) {
        List<SelectCarreras> opciones = new ArrayList<SelectCarreras>();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.CATALOGOS_ASPIRANTES_PQ.GET_CATALOGO_SP(?,?,?,?,?)}");
            cs.setInt("paOpcionCatalogo", idCatalogo);
            cs.setInt("paFiltroFk", filtro);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodError", OracleTypes.NUMBER);
            cs.registerOutParameter("paDescError", OracleTypes.VARCHAR);
            cs.execute();

            setError(cs.getInt("paCodError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
                if (rs != null) {
                    while (rs.next()) {

                        SelectCarreras sc = new SelectCarreras();
                        sc.setClaveCarrera(rs.getInt(1));
                        sc.setNombre(rs.getString(2));
                        sc.setIdPais(rs.getString(1));
                        opciones.add(sc);

                    }
                } else {
                    errorInsert = "No se encontraron coincidencias";
                }
            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString("paDescError");

            }
            cs.close();
       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
        return opciones;
    }

    public List<SelectCarreras> llenarListaPais(int idCatalogo, int filtro) {
        List<SelectCarreras> opciones = new ArrayList<SelectCarreras>();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.CATALOGOS_ASPIRANTES_PQ.GET_CATALOGO_SP(?,?,?,?,?)}");
            cs.setInt("paOpcionCatalogo", idCatalogo);
            cs.setInt("paFiltroFk", filtro);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodError", OracleTypes.NUMBER);
            cs.registerOutParameter("paDescError", OracleTypes.VARCHAR);
            cs.execute();

            setError(cs.getInt("paCodError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
                if (rs != null) {
                    while (rs.next()) {

                        SelectCarreras sc = new SelectCarreras();
                        sc.setIdPais(rs.getString(1));
                        sc.setClaveCarrera(0);
                        sc.setNombre(rs.getString(2));
                        opciones.add(sc);

                    }
                } else {
                    errorInsert = "No se encontraron coincidencias";
                }
            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString("paDescError");
            }
            cs.close();
       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
        return opciones;
    }

    public EnEmergencia obtenerDatosCE(String preficha) {
        EnEmergencia ee = new EnEmergencia();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_CONTACTO_ASP_SP(?,?,?,?)}");
            cs.setString("paPreficha", preficha);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                while (rs.next()) {
                    ee.setNomAp(rs.getObject(1).toString());
                    ee.setEstado(rs.getObject("ESTADO_FK").toString());
                    ee.setCiudad(rs.getObject(3).toString());
                    ee.setColonia(rs.getObject(4).toString());
                    ee.setCalle(rs.getObject(5).toString());

                    if (rs.getObject("NO_INTERIOR") == null) {
                        ee.setNoInterior("0");
                    } else {
                        ee.setNoInterior(rs.getObject("NO_INTERIOR").toString());
                    }

                    ee.setNoExterior(rs.getObject("NO_EXTERIOR").toString());
                    ee.setCenTrabajo(rs.getObject(8).toString());
                    ee.setTelTrabajo(rs.getObject("TEL_CENTRO_TRABAJO").toString());
                    ee.setTelfijo(rs.getObject("TEL_FIJO").toString());
                    ee.setTelcel(rs.getObject("TEL_CEL").toString());
                    ee.setPreficha(rs.getObject("ID_ASPIRANTE").toString());
                }
            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
     
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }

        return ee;
    }

    public DatosDelDomicilio obtenerDatosDD(String preficha) {
        DatosDelDomicilio ddd = new DatosDelDomicilio();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_DOMICILIO_ASP_SP(?,?,?,?)}");
            cs.setString("paPreficha", preficha);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                while (rs.next()) {
                    ddd.setEstadoFK(Integer.parseInt(rs.getObject("ESTADO_VIVE_ACTUAL_FK").toString()));
                    ddd.setEstado(rs.getObject("ESTADO").toString());
                    ddd.setMunicipio(rs.getObject("MUNICIPIO_VIVE_FK").toString());
                    ddd.setLocalidad(rs.getObject("LOCALIDAD_VIVE_FK").toString());
                    ddd.setCalle(rs.getObject(5).toString());
                    if (rs.getObject("NO_INTERIOR") == null) {
                        ddd.setNoInterior(0);
                    } else {
                        ddd.setNoInterior(Integer.parseInt(rs.getObject("NO_INTERIOR").toString()));

                    }
                    ddd.setNoExterior(Integer.parseInt(rs.getObject("NO_EXTERIOR").toString()));
                    ddd.setColonia(rs.getObject(8).toString());
                    ddd.setCp(Integer.parseInt(rs.getObject("COD_POST").toString()));
                    ddd.setTelFijo(rs.getObject("TEL_FIJO").toString());
                    ddd.setTelCel(rs.getObject("TEL_CEL").toString());
                    ddd.setPreficha(rs.getObject("ID_ASPIRANTE").toString());
                }

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
      
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

        return ddd;
    }

    public DatosSocioeconomicos obtenerDatosSE(String preficha) {

        DatosSocioeconomicos ds = new DatosSocioeconomicos();
        Connection laConexion = null;
        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_SOCIECONOMICOS_ASP_SP(?,?,?,?)}");
            cs.setString("paPreficha", preficha);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";

                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                while (rs.next()) {
                    ds.setNomPadre(rs.getObject("NOMBRE_PADRE").toString());
                    ds.setVivePadre(rs.getObject("VIVE_PADRE").toString());
                    ds.setNomMadre(rs.getObject("NOMBRE_MADRE").toString());
                    ds.setViveMadre(rs.getObject("VIVE_MADRE").toString());
                    ds.setBeca(rs.getObject("TIPO_BECA").toString());
                    ds.setZonaProcedencia(rs.getObject("ZONA_PROCEDENCIA").toString());
                    ds.setMaxEstudiosPadre(Integer.parseInt(rs.getObject("ESTUDIOS_PADRE_FK").toString()));
                    ds.setMaxEstudiosMadre(Integer.parseInt(rs.getObject("ESTUDIOS_MADRE_FK").toString()));
                    ds.setIngresosTotales(rs.getObject("INGRESOS_TOTALES").toString());
                    ds.setDependeDe(rs.getObject("DEPENDE_ECON_FK").toString());
                    ds.setOcupacionPadre(Integer.parseInt(rs.getObject("OCUPACION_PADRE_FK").toString()));
                    ds.setOcupacionMadre(Integer.parseInt(rs.getObject("OCUPACION_MADRE_FK").toString()));
                    ds.setTipoCasa(rs.getObject("TIPO_CASA_FK").toString());
                    ds.setNoPersonasCasa(Integer.parseInt(rs.getObject("PERSONAS_VIVEN_CASA").toString()));
                    ds.setCuartosCasa(rs.getObject("CUARTOS_CASA").toString());
                    ds.setProgOportunidades(rs.getObject("PROG_OPORTUNIDADES").toString());
                    ds.setViveCon(rs.getObject("VIVE_CON_FK").toString());
                    ds.setDependeEconomicamente(rs.getObject("NUM_PER_DEPENDECON").toString());
                    ds.setIdAspirante(Integer.parseInt(rs.getObject("ID_ASPIRANTE").toString()));

                }

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString("paMjeDescError");
            }
            cs.close();
       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
        return ds;
    }

    public EscuelaProcedencia obtenerDatosEP(String preficha) {

        EscuelaProcedencia ep = new EscuelaProcedencia();
        Connection laConexion = null;
        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_DATOS_ESC_PROC_ASP_SP(?,?,?,?)}");
            cs.setString("paPreficha", preficha);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

                while (rs.next()) {
                    ep.setIdAspirante(rs.getObject("ID_ASPIRANTE").toString());
                    ep.setClasificacion((rs.getObject("CLASIFICACION_ESC_FK").toString()));
                    ep.setNomEstado((rs.getObject("NOMBRE_ESTADO").toString()));
                    ep.setClaveEscuela((rs.getObject("CLAVE_CCT").toString()));
                    ep.setTipoEscuela((rs.getObject("TIPO_ESCUELA").toString()));
                    ep.setNomMpio(rs.getObject("MUNICIPIO").toString());
                    ep.setFechaIni((rs.getObject(7).toString()));
                    ep.setFechaFin((rs.getObject(8).toString()));
                    ep.setPromedio((rs.getObject("PROMEDIO").toString()));

                }
            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
        return ep;
    }

    public DatosPersonales obtenetDatosPersonales(String preficha) {
        int existe = 0;
        DatosPersonales dp = new DatosPersonales();
        Connection laConexion = null;
        try {

            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_1.GET_DATOS_PERSONALES_ASP_SP(?,?,?,?)}");
            cs.setString(1, preficha);
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            cs.registerOutParameter(3, OracleTypes.NUMBER);
            cs.registerOutParameter(4, OracleTypes.VARCHAR);

            cs.execute();

            setError(cs.getInt(3));

            if (getError() == 0) {

                ResultSet rs = (ResultSet) cs.getObject(2);

                while (rs.next()) {
                    errorInsert = "ninguno";
                    dp.setPreficha(Integer.parseInt(rs.getObject(1).toString()));
                    dp.setMatricula(rs.getObject(2).toString());
                    dp.setFolio_ceneval(rs.getObject(3).toString());
                    dp.setLugarFechaA(rs.getObject(4).toString());
                    dp.setLugarFechaB(rs.getObject(5).toString());
                    dp.setCurp(rs.getObject(6).toString());
                    dp.setApellido_pat(rs.getObject(7).toString());
                    dp.setApellido_mat(rs.getObject(8).toString());
                    dp.setNombre(rs.getObject(9).toString());
                    dp.setFec_nacimiento(rs.getObject(10).toString());
                    dp.setEdad(rs.getObject(11).toString());
                    dp.setNacionalidad(rs.getObject(12).toString()); //FK
                    if (dp.getNacionalidad().contentEquals("MEX")) {
                        dp.setPaisNac(rs.getObject(13).toString());
                        dp.setIdEdoNac(rs.getObject(14).toString()); //FK
                        dp.setEdoNacimiento(rs.getObject(15).toString());
                        dp.setMunNac(rs.getObject(16).toString()); //FK
                        dp.setLocalidad(rs.getObject(17).toString()); //FK
                    } else {

                        dp.setIdEdoNac("extranjero"); //FK
                        dp.setEdoNacimiento("extranjero");
                        dp.setMunNac("extranjero"); //FK
                        dp.setLocalidad("extranjero"); //FK
                    }

                    dp.setSexo(rs.getObject(18).toString());
                    dp.setEdo_civil(rs.getObject(19).toString());
                    dp.setTipo_sangre(rs.getObject(20).toString());
                    dp.setCap_diferente(rs.getObject(21).toString());
                    dp.setCorreo(rs.getObject(22).toString());
                    dp.setReferencia(rs.getObject(23).toString());
                    dp.setIdAsp(rs.getObject(24).toString());
                    existe = 1;
                }
                if (existe == 0) {
                    errorInsert = "El aspirante no existe. Por favor, verifique la preficha.";
                }

            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la consulta";
            } else {
                errorInsert = cs.getString(4);
            }
            cs.close();
       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }

        return dp;
    }

    public LiberacionPago liberarPago(String referencia) {
        LiberacionPago lp = new LiberacionPago();
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ADMIN_1.GET_LIBERACION_PAGO_SP(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString("paReferenciaBan", referencia);
            cs.registerOutParameter("paPreficha", OracleTypes.VARCHAR);
            cs.registerOutParameter("paNombreAsp", OracleTypes.VARCHAR);
            cs.registerOutParameter("paApellidoPat", OracleTypes.VARCHAR);
            cs.registerOutParameter("paApellidoMat", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCorreoAsp", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCarreraNombre", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFicha", OracleTypes.VARCHAR);
            cs.registerOutParameter("paIdAspirante", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));

            if (getError() == 0) {
                errorInsert = "ninguno";
                lp.setPreficha(cs.getString("paPreficha"));
                lp.setNombre(cs.getString("paNombreAsp"));
                lp.setApellidoPat(cs.getString("paApellidoPat"));
                lp.setApellidoMat(cs.getString("paApellidoMat"));
                lp.setCorreo(cs.getString("paCorreoAsp"));
                lp.setIdAsp(cs.getString("paIdAspirante"));
                lp.setCarrera1(cs.getString("paCarreraNombre"));
                lp.setFicha(cs.getString("paFicha"));

            } else {
                errorInsert = cs.getString(11);

            }
            cs.close();

        

        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
        return lp;
    }

    public void actualizarPeriodo(String fechaInicio, String fechaFin) {
        Connection laConexion = null;
        try {
            cs = null;
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.PQ_INSERT_ADMIN_1.INSERT_VIGENCIA_CONVOC_SP(?,?,?,?)}");
            cs.setString(1, fechaInicio);
            cs.setString(2, fechaFin);
            cs.registerOutParameter(3, OracleTypes.NUMBER);
            cs.registerOutParameter(4, OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt(3));

            if (getError() == 0) {
                errorInsert = "ninguno";
            } else if (getError() == -1) {
                errorInsert = "Error antes de hacer la inserción";
            } else {
                errorInsert = cs.getString(4);

            }
            cs.close();
        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();
            error = ex.getErrorCode();
        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }
    }

    public void confConvocatoria(String fechaInicio, String fechaFin, String metaReal, String metaEstablecida, String fechaInPre, String fechaFinPre) {
        Connection laConexion = null;
        CallableStatement cs;

        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.PQ_INSERT_ADMIN_1.INSERT_PARAM_CONVOC_SP(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, fechaInicio);

            cs.setString(2, fechaFin);

            cs.setString(3, metaReal);

            cs.setString(4, metaEstablecida);

            cs.setString(5, fechaInPre);

            cs.setString(6, fechaFinPre);
            cs.registerOutParameter(7, OracleTypes.NUMBER);

            cs.registerOutParameter(8, OracleTypes.NUMBER);
            cs.registerOutParameter(9, OracleTypes.VARCHAR);

            cs.execute();

            setErrorInsert(cs.getString(9));

            setError(cs.getInt(8));

            if (getError() == 0) {

                errorInsert2 = "ninguno";
                setError(cs.getInt(7));
            } else if (getError() == -1) {
                errorInsert2 = "Error antes de hacer la inserción";
            } else {

                errorInsert2 = cs.getString(9);

            }
            cs.close();
       
        } catch (SQLException ex) {

            errorInsert2 = ex.getMessage();

            error2 = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }

    }

    public ArrayList<String> checkRenovacion() {
        ArrayList<String> correos = new ArrayList();
        Connection laConexion = null;
        CallableStatement cs;

        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CHECK_CAMBIOS_RENOVACION_SP(?,?,?,?,?,?)}");
            cs.registerOutParameter("paCambioEnRenovacion", OracleTypes.NUMBER);
            cs.registerOutParameter("paFechaIniRen", OracleTypes.VARCHAR);
            cs.registerOutParameter("paFechaFinRen", OracleTypes.VARCHAR);
            cs.registerOutParameter("paCursorCorreosRen", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
            cs.execute();
            setFechaI(cs.getString("paFechaIniRen"));
            setFechaF(cs.getString("paFechaFinRen"));

            setError(cs.getInt("paCambioEnRenovacion"));
            ResultSet rs = (ResultSet) cs.getObject("paCursorCorreosRen");
            while (rs.next()) {

                correos.add(rs.getString("CORREO"));
            }

        

        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

        return correos;
    }

    public void checkCorreosEnviados() {
        Connection laConexion = null;
        CallableStatement cs;

        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_1.CONFIRMA_CAMBIOS_RENOVAR_SP(?,?)}");

            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            setError(cs.getInt("paCodigoError"));
            setErrorInsert(cs.getString("paMjeDescError"));

       
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

    }

    public confConv ParametrosConv() {
        confConv cv = new confConv();
        Connection laConexion = null;

        CallableStatement cs;
        try {

            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.PQ_GET_ADMIN_2.GET_PARAMETROS_CONV_SP(?,?,?)}");
           

            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);

            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

            while (rs.next()) {
                int numProp = rs.getInt("NUM_PROPIEDAD");

                if (numProp == 1) {
                    cv.setMetaEstablecida(rs.getString("VALOR"));
                }
                if (numProp == 2) {
                    cv.setMetaReal(rs.getString("VALOR"));
                }
                if (numProp == 3) {
                    cv.setFechaPago(rs.getString("VALOR"));
                }
                if (numProp == 4) {
                    cv.setFechaEntrega(rs.getString("VALOR"));

                }
                if (numProp == 5) {

                    cv.setFechaIpre(rs.getString("VALOR"));
                    setFechaI(rs.getString("VALOR"));
                }
                if (numProp == 6) {

                    cv.setFechaFpre(rs.getString("VALOR"));
                    setFechaF(rs.getString("VALOR"));
                }
            }
            error2 = (cs.getInt("paCodigoError"));
            cs.close();
       

        } catch (SQLException ex) {
            errorInsert2 = ex.getMessage();

        } finally {
            if (laConexion != null) {
              Conexion.cerrarConexion(laConexion);
            }
        }

       

        return cv;

    }

    public void ReinicioSec() {
        Connection laConexion = null;
        CallableStatement cs;
        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{call FICHAS.PQ_CHECK_ADMIN_2.GENERA_REINICIO_SEQUENCIAS_SP(?,?)}");
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
            cs.execute();
            error = cs.getInt("paCodigoError");

      
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }
    }

    public void CheckSec() {
        Connection laConexion = null;
        CallableStatement cs;
        try {

            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
            cs = laConexion.prepareCall("{?=call FICHAS.PQ_CHECK_ADMIN_2.CHECK_ESTADO_SEQUENCIA_FN}");

            cs.registerOutParameter(1, OracleTypes.NUMBER);

            cs.execute();
            error = cs.getInt(1);

        
        } catch (SQLException ex) {
            errorInsert = ex.getMessage();

            error = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
                Conexion.cerrarConexion(laConexion);
            }
        }

    }

//    public List<reportes> AspPAula(String horario, int opc) {
//        Connection laConexion = null;
//        CallableStatement cs;
//        List<reportes> reportes = new ArrayList<>();
//        reportes report;
//
//        try {
//            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
//
//            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_ASPIRANTES_AULA_SP(?,?,?,?,?,?)}");
//
//            cs.setString("paAulaHorario", horario);
//
//            cs.setInt("paOpcion", opc);
//            cs.registerOutParameter("paFechaExamen",OracleTypes.VARCHAR);
//            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
////            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
//            cs.execute();
//            String fecha=cs.getString("paFechaExamen");
//            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
//
//            while (rs.next()) {
//                report = new reportes();
//                report.setNombre(rs.getString("NOMBRE"));
//                report.setCarrera(rs.getInt("CARRERA"));
//                report.setFecha(fecha);
//                report.setNom_carrera(rs.getString("NOMBRE_CARR"));
//                report.setFicha(rs.getString("FICHA"));
//                report.setFolio(rs.getString("FOLIO_CENEVAL"));
//                report.setFirma(rs.getString("FIRMA"));
//                report.setAsist(rs.getString("ASIST"));
//                report.setLugar(rs.getString("LUGAR"));
//                reportes.add(report);
//            }
//
//            cs.close();
//      
//        } catch (SQLException ex) {
//            errorInsert2 = ex.getMessage();
//            error2 = ex.getErrorCode();
//
//        } finally {
//            if (laConexion != null) {
//               Conexion.cerrarConexion(laConexion);
//            }
//        }
//        System.out.println(errorInsert2);
//        return reportes;
//    }

//    public List<reportes> noAltaCen() {
//        Connection laConexion = null;
//
//        List<reportes> reportes = new ArrayList<>();
//        reportes report;
//        try {
//            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
//
//            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_CENEVAL_NO_ALTA_SP(?,?,?)}");
//
//            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
////            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
//            cs.execute();
//
//            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
//
//            while (rs.next()) {
//                report = new reportes();
//                report.setPreficha(Integer.toString(rs.getInt("PREFICHA")));
//
//                report.setReferencia(rs.getString("REFERENCIA_BANCARIA"));
//                report.setCorreo(rs.getString("CORREO"));
//                report.setUsuario(rs.getString("USUARIO"));
//                report.setUl_act(rs.getString("ULTIMA_ACTUALIZACION"));
//
//                reportes.add(report);
//            }
//
//            cs.close();
//       
//        } catch (SQLException ex) {
//            errorInsert2 = ex.getMessage();
//            error2 = ex.getErrorCode();
//
//        } finally {
//            if (laConexion != null) {
//                Conexion.cerrarConexion(laConexion);
//            }
//        }
//
//        return reportes;
//    }

          


//    public List<reportes> procesoCon() {
//        Connection laConexion = null;
//        CallableStatement cs;
//        List<reportes> reportes = new ArrayList<>();
//        reportes report;
//        try {
//            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
//
//            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_PROCESO_CONCL_FICHAS_SP(?,?,?)}");
//
//            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
////            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
//            cs.execute();
//
//            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
//
//            while (rs.next()) {
//                report = new reportes();
//                report.setNombre(rs.getString("NOMBRE"));
//                report.setPreproc(Integer.toString(rs.getInt("PRE_PROCESO_CONCLUIDO")));
//
//                reportes.add(report);
//            }
//
//            cs.close();
//       
//
//        } catch (SQLException ex) {
//            errorInsert2 = ex.getMessage();
//            error2 = ex.getErrorCode();
//
//        } finally {
//            if (laConexion != null) {
//                Conexion.cerrarConexion(laConexion);
//            }
//        }
//
//        return reportes;
//    }

//    public List<reportes> statusFichas() {
//        Connection laConexion = null;
//        CallableStatement cs;
//        List<reportes> reportes = new ArrayList<>();
//        reportes report;
//        try {
//            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
//
//            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_REP_STATUS_FICHAS_SP(?,?,?)}");
//
//            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
////            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
//            cs.execute();
//
//            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
//            
//            while (rs.next()) {
//                report = new reportes();
//                                            
//                report.setNombre(rs.getString("NOMBRE"));
//                report.setPreficha(rs.getString("PREFICHAS"));
//                report.setPreproc(rs.getString("PRE_PROCESO_CONCLUIDO"));
//                report.setPrefpagadas(rs.getString("PREFICHAS_PAGADAS"));
//
//                reportes.add(report);
//            }
//            
//            cs.close();
//        
//        } catch (SQLException ex) {
//            errorInsert2 = ex.getMessage();
//            error2 = ex.getErrorCode();
//
//        } finally {
//            if (laConexion != null) {
//                Conexion.cerrarConexion(laConexion);
//            }
//        }
//
//        return reportes;
//    }

//    public reportes preFichas() {
//        Connection laConexion = null;
//        CallableStatement cs;
//
//        reportes report = new reportes();
//        try {
//            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");
//
//            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP.GET_TOTAL_PREFICHAS_ASP_SP(?,?,?)}");
//
//            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
//            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
//            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
////            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
//            cs.execute();
//
//            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");
//            rs.next();
//            report.setNombre(rs.getString("FICHAS_TEC"));
//
//            report.setPrefpagadas(rs.getString("FICHAS_PAGADAS"));
//            report.setPreproc(rs.getString("FICHAS_PEND_X_PAGAR"));
//            report.setPreficha(rs.getString("FICHAS_DISPONIBLES"));
//
//            cs.close();
//      
//        } catch (SQLException ex) {
//            errorInsert2 = ex.getMessage();
//            error2 = ex.getErrorCode();
//
//        } finally {
//            if (laConexion != null) {
//               Conexion.cerrarConexion(laConexion);
//            }
//        }
//
//        return report;
//    }

    public List<String> HorariosCen() {
        Connection laConexion = null;
        
        List<String> horarios = new ArrayList<>();
        String horario;
        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_AULA_HORARIOS_SP(?,?,?,?)}");
            cs.setInt("paOpcion", 1);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
//            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

            while (rs.next()) {

                horario = rs.getString("LUGAR");

                horarios.add(horario);
            }

            cs.close();
      
        } catch (SQLException ex) {
            errorInsert2 = ex.getMessage();
            error2 = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

        return horarios;
    }

    public List<String> HorariosMat() {
        Connection laConexion = null;
       
        List<String> horarios = new ArrayList<>();
        String horario;
        try {
            laConexion = Conexion.getConnection(user,pass,"PAES","Modulo_Administrador");

            cs = laConexion.prepareCall("{call FICHAS.GET_REPORTES_ASP_2.GET_LISTA_AULA_HORARIOS_SP(?,?,?,?)}");
            cs.setInt("paOpcion", 2);
            cs.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);
            cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER);
            cs.registerOutParameter("paMjeDescError", OracleTypes.VARCHAR);
//            System.out.println( cs.registerOutParameter("paCodigoError", OracleTypes.NUMBER));
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject("paCurRetorno");

            while (rs.next()) {

                horario = rs.getString("LUGAR");

                horarios.add(horario);
            }

            cs.close();
      
        } catch (SQLException ex) {
            errorInsert2 = ex.getMessage();
            error2 = ex.getErrorCode();

        } finally {
            if (laConexion != null) {
               Conexion.cerrarConexion(laConexion);
            }
        }

        return horarios;
    }

    /**
     * @return the errorInsert
     */
    public String getErrorInsert() {
        return errorInsert;
    }

    /**
     * @param errorInsert the errorInsert to set
     */
    public void setErrorInsert(String errorInsert) {
        this.errorInsert = errorInsert;
    }

    /**
     * @return the errorCE
     */
    public String getErrorCE() {
        return errorCE;
    }

    /**
     * @param errorCE the errorCE to set
     */
    public void setErrorCE(String errorCE) {
        this.errorCE = errorCE;
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

    /**
     * @return the errorInsert2
     */
    public String getErrorInsert2() {
        return errorInsert2;
    }

    /**
     * @param errorInsert2 the errorInsert2 to set
     */
    public void setErrorInsert2(String errorInsert2) {
        this.errorInsert2 = errorInsert2;
    }

    /**
     * @return the error2
     */
    public int getError2() {
        return error2;
    }

    /**
     * @param error2 the error2 to set
     */
    public void setError2(int error2) {
        this.error2 = error2;
    }

    /**
     * @return the conStatus
     */
    public boolean isConStatus() {
        return conStatus;
    }

    /**
     * @param conStatus the conStatus to set
     */
    public void setConStatus(boolean conStatus) {
        this.conStatus = conStatus;
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

}
