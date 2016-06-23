package Beans;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author Rocio
 */
public class ConfigurarPeriodo implements java.io.Serializable {

    String[] meses = {"Periodo Inválido", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private String FechaInicio;
    private String FechaFin;
    private String periodo;
    private String mensaje = "";
    boolean valido = true;
    boolean diferente = true;

    public ConfigurarPeriodo() {
    }

    public boolean cambioPeriodo() {

        String inicio = getFechaInicio() + "/";
        String fin = getFechaFin() + "/";

//        boolean perVacio = false;
//        if (inicio == null || fin == null) {
        try {
            StringTokenizer st = new StringTokenizer(inicio, "/");
            int diaInicio = Integer.parseInt(st.nextToken());
            int mesInicio = Integer.parseInt(st.nextToken());
            String anioInicio = st.nextToken();

            st = new StringTokenizer(fin, "/");
            int diaFin = Integer.parseInt(st.nextToken());
            int mesFin = Integer.parseInt(st.nextToken());
            String anioFin = st.nextToken();

            if (anioFin.contentEquals(anioInicio)) {
                setMensaje("El periodo de registro de aspirantes se ha modificado exitosamente.");
                periodo = diaInicio + " de " + meses[mesInicio] + " al " + diaFin + " de " + meses[mesFin] + " " + anioFin;
                valido = true;
            } else {
                setMensaje("El año de inicio y fin son diferentes. No se ha guardado en la base de datos.");
                valido = false;
                periodo = meses[0];
            }
        } catch (NumberFormatException ex) {
            setMensaje("No se ha asignado periodo.");
            periodo = "No se ha asignado periodo.";
            valido = true;
        }
//        } else {
//            setMensaje("No hay periodo asignado para el año actual.");
//            periodo = "No hay periodo asignado para el año actual.";
//            valido = true;
//        }
        return valido;
    }

    public boolean diferentePeriodo(String inicio1, String inicio2) {

        String inicio = inicio1 + "/";
        String fin = inicio2 + "/";
        System.out.println(inicio + "\n" + fin);
        StringTokenizer st = new StringTokenizer(inicio, "/");
        st.nextToken();
        int mesInicio = Integer.parseInt(st.nextToken());
        String anioInicio = st.nextToken();

        st = new StringTokenizer(fin, "/");
        st.nextToken();
        int mesFin = Integer.parseInt(st.nextToken());
        String anioFin = st.nextToken();

        if (anioFin.contentEquals(anioInicio)) {

            diferente = true;

        } else {

            diferente = false;

        }
        return diferente;
    }

    public String fecha() {
        Calendar f = Calendar.getInstance();

        int dia = f.get(Calendar.DATE);
        int mes = f.get(Calendar.MONTH) + 1;
        int año = f.get(Calendar.YEAR);

        String fecha = dia + "/" + mes + "/" + año;
        return fecha;
    }

    /**
     * @return the FechaInicio
     */
    public String getFechaInicio() {
        return FechaInicio;
    }

    /**
     * @param FechaInicio the FechaInicio to set
     */
    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    /**
     * @return the FechaFin
     */
    public String getFechaFin() {
        return FechaFin;
    }

    /**
     * @param FechaFin the FechaFin to set
     */
    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

}
