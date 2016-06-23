/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBD;

/**
 *
 * @author Rocio
 */
public class CompararErrores {

    private String mensajeDeError;

    public String buscarError(int error) {

        if (error == 1017) {
            setMensajeDeError("Verifique usuario y/o contraseña.");
            
        } else {
            if (error == 28000) {
                setMensajeDeError("Usuario bloqueado. Consulte a su administrador.");
            } else {
                if (error == 17002) {
                    setMensajeDeError("No se pudo conectar a la base de datos. Consulte a su administrador.");
                } else {
                    if (error == 1720) {
                        setMensajeDeError("El tiempo de espera ha expirado. Consulte a su administrador.");
                    } else {
                        if (error == 6550) {
                            mensajeDeError = "Privilegios Insuficientes";
                        } else {
                            setMensajeDeError("Error inseperado: " + error + ". Consulte a su administrador.");
                        }
                    }
                }
            }
        }
        return getMensajeDeError();
    }

    /**
     * @return the mensajeDeError
     */
    public String getMensajeDeError() {
        return mensajeDeError;
    }

    /**
     * @param mensajeDeError the mensajeDeError to set
     */
    public void setMensajeDeError(String mensajeDeError) {
        this.mensajeDeError = mensajeDeError;
    }
}
