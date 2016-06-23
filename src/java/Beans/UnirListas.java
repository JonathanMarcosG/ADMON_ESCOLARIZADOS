/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rocio
 */
public class UnirListas {

    List<SelectCarreras> op1, op2, op3;
    List<SelectCarreras> control, servicio, ambito, turno;

    public UnirListas(List<SelectCarreras> op1, List<SelectCarreras> op2, List<SelectCarreras> op3) {
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

    public UnirListas(List<SelectCarreras> control, List<SelectCarreras> servicio, List<SelectCarreras> ambito, List<SelectCarreras> turno) {
        this.control = control;
        this.servicio = servicio;
        this.ambito = ambito;
        this.turno = turno;
    }
}
