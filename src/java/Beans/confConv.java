/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author David
 */
public class confConv {
    private int codError;
    private String msjError;
    private String metaReal;
    private String metaEstablecida;
    private String fechaIpre;
    private String fechaFpre;
    private String fechaPago;
    private String fechaEntrega;
    private String mensaje="";
    

    /**
     * @return the metaReal
     */
    public String getMetaReal() {
        return metaReal;
    }

    /**
     * @param metaReal the metaReal to set
     */
    public void setMetaReal(String metaReal) {
        this.metaReal = metaReal;
    }

    /**
     * @return the metaEstablecida
     */
    public String getMetaEstablecida() {
        return metaEstablecida;
    }

    /**
     * @param metaEstablecida the metaEstablecida to set
     */
    public void setMetaEstablecida(String metaEstablecida) {
        this.metaEstablecida = metaEstablecida;
    }

    /**
     * @return the fechaPago
     */
    public String getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the fechaEntrega
     */
    public String getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the fechaIpre
     */
    public String getFechaIpre() {
        return fechaIpre;
    }

    /**
     * @param fechaIpre the fechaIpre to set
     */
    public void setFechaIpre(String fechaIpre) {
        this.fechaIpre = fechaIpre;
    }

    /**
     * @return the fechaFpre
     */
    public String getFechaFpre() {
        return fechaFpre;
    }

    /**
     * @param fechaFpre the fechaFpre to set
     */
    public void setFechaFpre(String fechaFpre) {
        this.fechaFpre = fechaFpre;
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
