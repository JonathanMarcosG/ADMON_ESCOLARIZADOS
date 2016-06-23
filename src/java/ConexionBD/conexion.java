/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {

    private Connection conexion;
    private String user;
    private String pass;
    private Boolean ConStatus;
   private String BaseDeDatos = "jdbc:oracle:thin:@192.168.40.103:1521:SIA";
    private String error = "ninguno";
    private boolean b = false;

    public conexion(String usuario, String password) {
        this.pass = password;
        this.user = usuario;

    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        setB(false);
        error = "ninguno";
        conexion = null;

        Class.forName("oracle.jdbc.OracleDriver");

        conexion = DriverManager.getConnection(getBaseDeDatos(), getUser(), getPass());

        return conexion;
    }

    public void cerrar() throws SQLException {
       /*try{
        setConStatus(true);
        conexion.close();
        
       }catch(NullPointerException ex){
           setConStatus(false);
       }
        */
       
         if (conexion!=null) {
            conexion.close(); 
            setConStatus(true);
            }else{
             setConStatus(false);
         }
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the BaseDeDatos
     */
    public String getBaseDeDatos() {
        return BaseDeDatos;
    }

    /**
     * @param BaseDeDatos the BaseDeDatos to set
     */
    public void setBaseDeDatos(String BaseDeDatos) {
        this.BaseDeDatos = BaseDeDatos;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the b
     */
    public boolean isB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(boolean b) {
        this.b = b;
    }

    /**
     * @return the ConStatus
     */
    public Boolean getConStatus() {
        return ConStatus;
    }

    /**
     * @param ConStatus the ConStatus to set
     */
    public void setConStatus(Boolean ConStatus) {
        this.ConStatus = ConStatus;
    }

}
