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
public class ListaCarreras {

    List<SelectCarreras> opciones = new ArrayList<SelectCarreras>();

    public List<SelectCarreras> llenaVive() {

        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("S");
        b.setNombre("Sí");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("N");
        b.setNombre("No");
        num.add(b);

        return num;
    }

    public List<SelectCarreras> llenaYN() {

        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("Y");
        b.setNombre("Sí");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("N");
        b.setNombre("No");
        num.add(b);

        return num;
    }

    public List<SelectCarreras> llenaSiNo() {

        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("true");
        b.setNombre("Sí");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("false");
        b.setNombre("No");
        num.add(b);

        return num;
    }

    public List<SelectCarreras> llenaNumero() {
        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;

        for (int i = 1; i < 10; i++) {
            b = new SelectCarreras();
            b.setClaveCarrera((i));
            b.setNombre(String.valueOf(i));
            b.setIdPais("error");
            num.add(b);
        }
        return num;
    }
    public List<SelectCarreras> llenaNumCuartos() {
        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("1 a 5");
        b.setNombre("1 a 5");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("mas 5");
        b.setNombre("Mas 5");
        num.add(b);
        return num;
    }
    public List<SelectCarreras> llenaNumCuartos(String clave) {
        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
          b = new SelectCarreras();
        if(clave.contentEquals("1 A 5")){
             
        b.setIdPais("mas 5");
        b.setNombre("Mas de 5");
        num.add(b);
        }else{
           
        b.setIdPais("1 a 5");
        b.setNombre("1 a 5");
        num.add(b);
        }
       
       
        return num;
    }

    public List<SelectCarreras> llenaIngresos() {
        List<SelectCarreras> num = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();;
        b = new SelectCarreras();
        b.setIdPais("Menos 3000");
        b.setNombre("Menos 3000");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("3001-5000");
        b.setNombre("3001-5000");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("5001-7000");
        b.setNombre("5001-7000");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("7001-9000");
        b.setNombre("7001-9000");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("9001-11000");
        b.setNombre("9001-11000");
        num.add(b);
        b = new SelectCarreras();
        b.setIdPais("Mas de 11000");
        b.setNombre("Mas de 11000");
        num.add(b);
        return num;
    }

    public List<SelectCarreras> llenaCasaEs() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b = new SelectCarreras();
        b.setIdPais("PROPIA");
        b.setNombre("Propia");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("RENTADA");
        b.setNombre("Rentada");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("PRESTADA");
        b.setNombre("Prestada");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("SE ESTÁ PAGANDO");
        b.setNombre("Se está pagando");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaZonaProcedencia() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("INDÍGENA");
        b.setNombre("Indígena");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("RURAL");
        b.setNombre("Rural");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("URBANO");
        b.setNombre("Urbano");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("MARGINADO");
        b.setNombre("Urbano Marginado");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaViveCon() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b = new SelectCarreras();
        b.setIdPais("Padre");
        b.setNombre("Padre");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("Madre");
        b.setNombre("Madre");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("Otro");
        b.setNombre("Otro");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaTEscuela() {
        List<SelectCarreras> tEscuela = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("ESTATAL");
        b.setNombre("Estatal");
        tEscuela.add(b);
        b = new SelectCarreras();
        b.setIdPais("PRIVADA");
        b.setNombre("Privada");
        tEscuela.add(b);
        b = new SelectCarreras();
        b.setIdPais("FEDERAL");
        b.setNombre("Federal");
        tEscuela.add(b);
        return tEscuela;
    }

    public List<SelectCarreras> llenaSexo() {
        List<SelectCarreras> sexo = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("F");
        b.setNombre("Femenino");
        sexo.add(b);
        b = new SelectCarreras();
        b.setIdPais("M");
        b.setNombre("Masculino");
        sexo.add(b);

        return sexo;
    }

    public List<SelectCarreras> llenaSangre() {
        List<SelectCarreras> Sangre = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("A+");
        b.setNombre("A+");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("A-");
        b.setNombre("A-");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("B+");
        b.setNombre("B+");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("B-");
        b.setNombre("B-");
        Sangre.add(b);
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("AB+");
        b.setNombre("AB+");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("AB-");
        b.setNombre("AB-");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("O+");
        b.setNombre("O+");
        Sangre.add(b);
        b = new SelectCarreras();
        b.setIdPais("O-");
        b.setNombre("O-");
        Sangre.add(b);
        return Sangre;
    }

    public List<SelectCarreras> llenaEdoCivil() {
        List<SelectCarreras> Civil = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("SOLTERO");
        b.setNombre("Soltero");
        Civil.add(b);
        b = new SelectCarreras();
        b.setIdPais("CASADO");
        b.setNombre("Casado");
        Civil.add(b);
        b = new SelectCarreras();
        b.setIdPais("DIVORCIADO");
        b.setNombre("Divorciado");
        Civil.add(b);
        b = new SelectCarreras();
        b.setIdPais("UNIÓN LIBRE");
        b.setNombre("Unión Libre");
        Civil.add(b);
        b = new SelectCarreras();
        b.setIdPais("VIUDO");
        b.setNombre("Viudo");
        Civil.add(b);
        return Civil;
    }

    public List<SelectCarreras> llenaDiscapacidad() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("VISUAL");
        b.setNombre("Discapacidad Visual");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("MOTRIZ");
        b.setNombre("Discapacidad Motriz");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("AUDITIVA");
        b.setNombre("Discapacidad auditiva");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("NINGUNA");
        b.setNombre("Ninguna");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaControl() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("SELECCIONE");
        b.setNombre("---Seleccione---");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("DESCONOCIDO");
        b.setNombre("Desconocido");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("PRIVADO");
        b.setNombre("Privado");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("PUBLICO");
        b.setNombre("Público");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("S/C");
        b.setNombre("S/C");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaServicio() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("SELECCIONE");
        b.setNombre("---Seleccione---");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("ACUERDO 286");
        b.setNombre("Acuerdo 286");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("BACHILLERATO");
        b.setNombre("Bachillerato");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("BACHILLERATO GENERAL");
        b.setNombre("Bachillerato General");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("BACHILLERATO TECNICO");
        b.setNombre("Bachillerato Técnico");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("INEA");
        b.setNombre("INEA");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("DESCONOCIDO");
        b.setNombre("Desconocido");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaAmbito() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("SELECCIONE");
        b.setNombre("---Seleccione---");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("DESCONOCIDO");
        b.setNombre("Desconocido");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("RURAL");
        b.setNombre("Rural");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("S/A");
        b.setNombre("S/A");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("URBANA");
        b.setNombre("Urbano");
        lista.add(b);
        return lista;
    }

    public List<SelectCarreras> llenaTurno() {
        List<SelectCarreras> lista = new ArrayList<>();
        SelectCarreras b;
        b = new SelectCarreras();
        b.setIdPais("SELECCIONE");
        b.setNombre("---Seleccione---");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("COMPLEMENTARIO");
        b.setNombre("Complementario");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("DESCONOCIDO");
        b.setNombre("Desconocido");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("DISCONTINUO");
        b.setNombre("Discontinuo");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("MATUTINO");
        b.setNombre("Matutino");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("MATUTINO/VESPERTINO");
        b.setNombre("Matutino/Vespertino");
        lista.add(b);
        b = new SelectCarreras();
        b.setIdPais("MATUTINO/VESPERTINO/NOCTURNO");
        b.setNombre("Matutino/Vespertino/Nocturno");
        lista.add(b);
        return lista;
    }

    public void comparar(List<SelectCarreras> opcio, List<SelectCarreras> op, int clave) {

        for (int j = 0; j < opcio.size(); j++) {

            if (clave == opcio.get(j).getClaveCarrera()) {
                op.add(opcio.get(j));
                //  System.out.println(clave + "id1:" + opcio.get(j).getClaveCarrera());

            }
        }

        if (op.isEmpty()) {
            List<SelectCarreras> lista = new ArrayList<>();
            SelectCarreras b;
            b = new SelectCarreras();
            b.setIdPais("error");
            b.setNombre("error");
            b.setClaveCarrera(clave);

            op.add(0, b);

        }
    }

    public void SinRepetir(List<SelectCarreras> opcio, List<SelectCarreras> op, int clave1, int clave2) {
        if (op.get(0).getNombre().contentEquals("error")) {

        } else {
            for (int j = 0; j < opcio.size(); j++) {

                if ((opcio.get(j).getClaveCarrera() != op.get(0).getClaveCarrera())) {

                    if (clave1 != opcio.get(j).getClaveCarrera() && clave2 != opcio.get(j).getClaveCarrera()) {
                        op.add(opcio.get(j));
                        // System.out.println(clave1 + "id:" + opcio.get(j).getClaveCarrera() + " " + opcio.get(j).getNombre());

                    }
                }
            }
        }
    }

    public void AgregarOpciones(List<SelectCarreras> opcio, List<SelectCarreras> op, int clave1) {
        if (!(op.get(0).getClaveCarrera() == 0)) {
            for (int j = 0; j < opcio.size(); j++) {

                if ((opcio.get(j).getClaveCarrera() != op.get(0).getClaveCarrera())) {
                    op.add(opcio.get(j));
                    //     System.out.println(clave1 + "id:" + opcio.get(j).getClaveCarrera());

                }
            }
        } else {
            for (int j = 0; j < opcio.size(); j++) {

                op.add(opcio.get(j));
                //    System.out.println(clave1 + "id:" + opcio.get(j).getIdPais());

            }
        }
    }

    public void compararPais(List<SelectCarreras> opcio, List<SelectCarreras> op, String clave) {

        for (int j = 0; j < opcio.size(); j++) {
            if (clave.contentEquals(opcio.get(j).getIdPais())) {
                op.add(opcio.get(j));
                // System.out.println(clave + "muuuuajajaja:" + opcio.get(j).getIdPais());
            }
        }

        if (op.isEmpty()) {

            SelectCarreras b;
            b = new SelectCarreras();
            b.setIdPais("error");
            b.setNombre(clave);
            op.add(0, b);
        }
    }

    public void AgregarOpcionesPais(List<SelectCarreras> opcio, List<SelectCarreras> op, String clave1) {
        if (!(op.get(0).getIdPais().contentEquals("error"))) {
            for (int j = 0; j < opcio.size(); j++) {

                if (!(opcio.get(j).getIdPais().contentEquals(op.get(0).getIdPais()))) {
                    op.add(opcio.get(j));
                    //    System.out.println(clave1 + "id:" + opcio.get(j).getIdPais());

                }
            }
        } else {
            for (int j = 0; j < opcio.size(); j++) {

                op.add(opcio.get(j));
                //    System.out.println(clave1 + "id:" + opcio.get(j).getIdPais());

            }
        }
    }
    public void compararPais2(List<SelectCarreras> opcio, List<SelectCarreras> op, String clave) {

        for (int j = 0; j < opcio.size(); j++) {
            if (clave.contentEquals(opcio.get(j).getIdPais())) {
                op.add(opcio.get(j));
                // System.out.println(clave + "muuuuajajaja:" + opcio.get(j).getIdPais());
            }
        }

        if (op.isEmpty()) {

            SelectCarreras b;
            b = new SelectCarreras();
            b.setIdPais(clave);
            b.setNombre(clave);
            op.add(0, b);
        }
    }
      public void AgregarOpcionesPais2(List<SelectCarreras> opcio, List<SelectCarreras> op, String clave1) {
          System.out.println(opcio.size());
          if (!(op.get(0).getIdPais().contentEquals("error"))) {
            for (int j = 0; j < opcio.size(); j++) {

                if (!(opcio.get(j).getIdPais().contentEquals(op.get(0).getIdPais()))) {
                    op.add(opcio.get(j));
                    //    System.out.println(clave1 + "id:" + opcio.get(j).getIdPais());

                }
            }
        } else {
            for (int j = 0; j < opcio.size(); j++) {

                op.add(opcio.get(j));
                //    System.out.println(clave1 + "id:" + opcio.get(j).getIdPais());

            }
        }
    }
}

