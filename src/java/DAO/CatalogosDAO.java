/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.ClaveCCT;
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
 * @author Jony
 */
public class CatalogosDAO {
    //Método que regresa en un arreglo de objetos(ArrayList) el contenido de los catálogos de la B.D.
    //Parámetros de Entrada:
    //pk---Corresponde a la llave foránea para realizar la condición en la consulta.
    //Parámetros de Salida: Clave--Regresa un ArrayList con el contenido de la consulta.
    public static List<ClaveCCT> getClaveCCT(String username, String password, int opc, int pk) throws SQLException, ClassNotFoundException {
        ClaveCCT cla;
        String codError;
        String mensajeError;
        Logger logger = new Logger();
        List<ClaveCCT> Clave = new ArrayList<>();
        Connection conn = Conexion.getConnection(username, password, Constants.NOMBRE_APP, Constants.NOMBRE_MODULO);
        if (conn != null) {
            try {
                CallableStatement call = conn.prepareCall("{call FICHAS.CATALOGOS_ASPIRANTES_PQ.GET_CATALOGO_SP(?,?,?,?,?)}");
                //Registro de parámetros de entrada
                call.setInt("paOpcionCatalogo", opc);//pasar  atributos  para  where o insertar 
                call.setInt("paFiltroFk", pk);
                //Registro de parámetros de salida.
                call.registerOutParameter("paCurRetorno", OracleTypes.CURSOR);//tomas  parametro de salida de  la  base de datos           
                call.registerOutParameter("paCodError", OracleTypes.NUMBER);
                call.registerOutParameter("paDescError", OracleTypes.VARCHAR);
                //Ejecución del SP
                call.execute();
                //Validación de ejecución correcta.
                codError = call.getString("paCodError");
                if (codError.equals("0")) {
                    //Eejecución correcta
                    ResultSet rs = (ResultSet) call.getObject("paCurRetorno");

                    while (rs.next()) {
                        cla = new ClaveCCT();
                        cla.setClave(rs.getObject(1).toString());
                        cla.setCentroEducativo(rs.getObject(2).toString());
                        cla.setTurno(rs.getObject(3).toString());
                        cla.setDomicilio(rs.getObject(4).toString());
                        Clave.add(cla);
                    }
                    //Cierre del cursor.
                    rs.close();
                } else {
                    //Ejecución incorrecta: loggear.
                    mensajeError = call.getString("paMjeDescError");
                    String logMessage = codError + "->" + mensajeError;
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
        return Clave;
    }
}
