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
public class DatosDelDomicilio {

    private int estadoFK;
    private String estado;
    private String municipio;
    private String localidad;
    private String calle;
    private int noInterior;
    private int noExterior;
    private String colonia;
    private int cp;
    private String telFijo;
    private String telCel;
    private String preficha = "0";

    /**
     * @return the estadoFK
     */
    public int getEstadoFK() {
        return estadoFK;
    }

    /**
     * @param estadoFK the estadoFK to set
     */
    public void setEstadoFK(int estadoFK) {
        this.estadoFK = estadoFK;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the noInterior
     */
    public int getNoInterior() {
        return noInterior;
    }

    /**
     * @param noInterior the noInterior to set
     */
    public void setNoInterior(int noInterior) {
        this.noInterior = noInterior;
    }

    /**
     * @return the noExterior
     */
    public int getNoExterior() {
        return noExterior;
    }

    /**
     * @param noExterior the noExterior to set
     */
    public void setNoExterior(int noExterior) {
        this.noExterior = noExterior;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the cp
     */
    public int getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(int cp) {
        this.cp = cp;
    }

    /**
     * @return the telFijo
     */
    public String getTelFijo() {
        return telFijo;
    }

    /**
     * @param telFijo the telFijo to set
     */
    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    /**
     * @return the telCel
     */
    public String getTelCel() {
        return telCel;
    }

    /**
     * @param telCel the telCel to set
     */
    public void setTelCel(String telCel) {
        this.telCel = telCel;
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
