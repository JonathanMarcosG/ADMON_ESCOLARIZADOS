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
public class SelectCarreras implements java.io.Serializable {

    private int codError;
    private String msjError;
    private int claveCarrera;
    private String nombre;
    private String idPais;

    public SelectCarreras() {
    }

    /**
     * @return the claveCarrera
     */
    public int getClaveCarrera() {
        return claveCarrera;
    }

    /**
     * @param claveCarrera the claveCarrera to set
     */
    public void setClaveCarrera(int claveCarrera) {
        this.claveCarrera = claveCarrera;
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
     * @return the idPais
     */
    public String getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    /**
     * @return the codError
     */
    public int getCodError() {
        return codError;
    }

    /**
     * @param codError the codError to set
     */
    public void setCodError(int codError) {
        this.codError = codError;
    }

    /**
     * @return the msjError
     */
    public String getMsjError() {
        return msjError;
    }

    /**
     * @param msjError the msjError to set
     */
    public void setMsjError(String msjError) {
        this.msjError = msjError;
    }

}
