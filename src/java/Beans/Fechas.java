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
public class Fechas {

    private String fecha;

    public String fechasConvoc(String fecha) {
        switch (fecha) {
            case "01":
              return "1";
            case "02":
              return "2";
            case "03":
               return "3";
            case "04":
                return "4";
            case "05":
              return "5";
            case "06":
               return "6";
            case "07":
              return "7";
            case "08":
               return "8";
            case "09":
               return "9";

        }
   return fecha;
    }
    
    

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the mesI
     */
}
