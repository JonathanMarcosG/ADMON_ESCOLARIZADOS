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
public class ValidacionContrasenia implements java.io.Serializable {

    private String usuario;
    private String contransenia;
    private String valido;

    public ValidacionContrasenia() {
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contransenia
     */
    public String getContransenia() {
        return contransenia;
    }

    /**
     * @param contransenia the contransenia to set
     */
    public void setContransenia(String contransenia) {
        this.contransenia = contransenia;
    }

    /**
     * @return the valido
     */
    public String getValido() {
        return valido;
    }

    /**
     * @param valido the valido to set
     */
    public void setValido(String valido) {
        this.valido = valido;
    }
}
