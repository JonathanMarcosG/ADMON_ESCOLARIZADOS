/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class VerificaExistencia_DAO {

    public static String verificaAspirante(String username, String password, String curp, String correo) {
        String cod_error="-1";
        String msj_error;
        Logger logger = new Logger();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);

        if (conn != null) {
            //Se genera el bloque try-catch-finally
            try {
                //Objeto callableStatement para llamar el procedimiento.
                CallableStatement cst = conn.prepareCall("{call FICHAS.PQ_GET_ASPIRANTE_2.GET_RECUPERAR_PREFICHA_ASP_SP(?,?,?,?)}");
                //Registro de parámetros de entrada
                cst.setString("paCurpAspirante", curp);
                cst.registerOutParameter("paCurRetorno", oracle.jdbc.OracleTypes.CURSOR);
                cst.registerOutParameter("paCodigoError", oracle.jdbc.OracleTypes.NUMBER);
                cst.registerOutParameter("paMjeDescError", oracle.jdbc.OracleTypes.VARCHAR);
                //Ejecución del SP
                cst.execute();
                cod_error = cst.getString("paCodigoError");
                msj_error = cst.getString("paMjeDescError");
                if ("0".equals(cod_error)) {
                    ResultSet rset = (ResultSet) cst.getObject("paCurRetorno");
                    while (rset.next()) {
                        nombrebd = rset.getString("NOMBRE");
                        String appat = rset.getString("APELLIDO_PAT");
                        String apmat = rset.getString("APELLIDO_MAT");
                        apellidosbd = appat + " " + apmat;
                        fichabd = rset.getString("PREFICHA");
                        carrerabd = rset.getString("NOMBRE_CARRERA");
                        prefichabd = rset.getString("PREFICHA");
                        periodobd = rset.getString("PERIODO_CONSURSA");
                        modalidadbd = rset.getString("MODALIDAD");
                        ref_bancaria = rset.getString("REFERENCIA_BANCARIA");
                        importe_bd = rset.getString("IMPORTE");
                        fechaEm = rset.getString("FECHA_EMISION");
                        fechapdf = rset.getString("FECHA_ACTUAL");
                        fechalim = rset.getString("FECHA_EXPIRA_REF");
                    }

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
            cod_error = "-1";
        }
        return cod_error;
    }
}
