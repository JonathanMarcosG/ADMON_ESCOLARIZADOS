/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Rocio
 */
public class LiberacionPago implements java.io.Serializable {

    private String preficha;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String correo;
    private String carrera1;
    private String idAsp;
    private String ficha;

    public LiberacionPago() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidoPat
     */
    public String getApellidoPat() {
        return apellidoPat;
    }

    /**
     * @param apellidoPat the apellidoPat to set
     */
    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    /**
     * @return the apellidoMat
     */
    public String getApellidoMat() {
        return apellidoMat;
    }

    /**
     * @param apellidoMat the apellidoMat to set
     */
    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the carrera1
     */
    public String getCarrera1() {
        return carrera1;
    }

    /**
     * @param carrera1 the carrera1 to set
     */
    public void setCarrera1(String carrera1) {
        this.carrera1 = carrera1;
    }

    /**
     * @return the idAsp
     */
    public String getIdAsp() {
        return idAsp;
    }

    /**
     * @param idAsp the idAsp to set
     */
    public void setIdAsp(String idAsp) {
        this.idAsp = idAsp;
    }

    /**
     * @return the ficha
     */
    public String getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    /**
     * @return the preficha
     */
    public String getPreficha() {
        return preficha;
    }

    /**
     * @param preficha the preficha to set
     */
    public void setPreficha(String preficha) {
        this.preficha = preficha;
    }
}
