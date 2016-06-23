package Beans;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Contains methods to register log messages from applications into log files
 *
 * @author giovanni
 */
public class LogFile {

    // Niveles de gravedad para los mensajes registrados
    public static final int GRAVE = 1;
    public static final int ADVERTENCIA = 2;
    public static final int INFORMACION = 3;
    public static final int MENSAJE = 4;

    private String mensajeError;

    public void registrarError(int nivelGravedad, String logMessage, String application,
            String module, String username) {

        try (FileWriter logWriter = this.getLogWriter(application)) {

            logWriter.append("\n");
            logWriter.append(this.getCurrentDatetime());

            switch (nivelGravedad) {
                case LogFile.GRAVE:
                    logWriter.append("[GRAVE]");
                    break;
                case LogFile.ADVERTENCIA:
                    logWriter.append("[ADVERTENCIA]");
                    break;
                case LogFile.INFORMACION:
                    logWriter.append("[INFORMACION]");
                    break;
                default:
                    logWriter.append("[MENSAJE]");
                    break;
            }

            logWriter.append("[" + application + "]");

            if (module != null && module.length() > 0) {
                logWriter.append("[" + module + "]");
            }

            if (username != null && username.length() > 0) {
                logWriter.append("[" + username + "]");
            }

            logWriter.append("\n" + logMessage);

            logWriter.append("\n");
            logWriter.close();
        } catch (IOException ex) {

            System.err.println("No se puede escribir al archivo de log");
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Registers unknown errors into log.
     *
     * @param code
     * @param logMessage
     * @param application
     * @param module
     * @param username
     */
    private void registrarErrorDesconocido(int nivelGravedad, final int code, final String logMessage,
            final String application, final String module, final String username) {

        try (FileWriter logWriter = this.getLogWriter("")) {

            logWriter.append("\n");
            logWriter.append(this.getCurrentDatetime());

            switch (nivelGravedad) {
                case LogFile.GRAVE:
                    logWriter.append("[GRAVE]");
                    break;
                case LogFile.ADVERTENCIA:
                    logWriter.append("[ADVERTENCIA]");
                    break;
                case LogFile.INFORMACION:
                    logWriter.append("[INFORMACION]");
                    break;
                default:
                    logWriter.append("[MENSAJE]");
                    break;
            }

            logWriter.append("[" + application + "]");

            if (module != null && module.length() > 0) {
                logWriter.append("[" + module + "]");
            }

            if (username != null && username.length() > 0) {
                logWriter.append("[" + username + "]");
            }

            logWriter.append("\n(" + code + ") " + logMessage);

            logWriter.append("\n");
            logWriter.close();
        } catch (IOException ex) {

            System.err.println("No se puede escribir al archivo de log");
            System.err.println(ex.getMessage());
        }
    }

    public void registrarErrorSQL(final SQLException sqlException, final String application,
            final String module, final String username) {

        String errorMessage;
        int nivelGravedad;
        int code = sqlException.getErrorCode();

        switch (code) {
            case 1017:
                errorMessage = "Usuario o password incorrecto, acceso denegado.";
                nivelGravedad = LogFile.INFORMACION;
                break;
            case 28000:
                errorMessage = "Estimado usuario por seguridad su cuenta ha sido "
                        + "bloqueada por 1 hora, si despu&eacute;s de ese lapso "
                        + "el problema persiste acuda a Servicios Escolares a "
                        + "validar su contrase&ntilde;a.";
                nivelGravedad = LogFile.ADVERTENCIA;
                break;
            case 17002:
                errorMessage = "Ocurrió un error con la conexión a la base datos,"
                        + " consulte con el administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 4068:
                errorMessage = "Ocurrió un error en la base de datos, paquete "
                        + "inválido, consulte con el administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 17006:
                errorMessage = "Ocurrió un error en la base de datos. Nombre de columna "
                        + "inválida, consulte con el administrador. ";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 6550:
                errorMessage = "Lo sentimos, no existen privilegios o no existe el procedimiento."
                        + " Por favor, consulte con su administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 17003:
                errorMessage = "Número de columna incorrecta. Por favor, consulte con su administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 17012:
                errorMessage = "Tipo de parámetro incorrecto."+
                               " Por favor, consulte con su administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
            case 17059:
                errorMessage = "Los tipos de datos no coinciden."+
                               " Por favor, consulte con su administrador.";
                nivelGravedad = LogFile.GRAVE;
                break;
                
            default:
                errorMessage = "Ocurrió un error desconocido.";
                nivelGravedad = LogFile.GRAVE;
                this.registrarErrorDesconocido(nivelGravedad, code, errorMessage, application, module, username);
        }

        this.mensajeError = errorMessage;
        errorMessage += " -> " + sqlException.getMessage();

        errorMessage = "(" + code + ") " + errorMessage;
        this.registrarError(nivelGravedad, errorMessage, application, module, username);
    }

    /**
     * Returns a String with current date with hour, minutes and seconds. Use
     * this method to register log events with datetime
     *
     * @return A String like this one: '[2015/10/23 23:45:22] '
     */
    private String getCurrentDatetime() {

        Calendar calendar = new GregorianCalendar();

        StringBuilder currentDate = new StringBuilder("[");

        currentDate.append(calendar.get(Calendar.YEAR));
        currentDate.append("/").append((calendar.get(Calendar.MONTH) + 1));
        currentDate.append("/").append(calendar.get(Calendar.DAY_OF_MONTH));

        currentDate.append(" ").append(calendar.get(Calendar.HOUR_OF_DAY));
        currentDate.append(":").append(calendar.get(Calendar.MINUTE));
        currentDate.append(":").append(calendar.get(Calendar.SECOND));

        currentDate.append("] ");

        return currentDate.toString();
    }

    /**
     * Generates a String like 'Enero2015' with current month and year
     *
     * @return Current month and year separated by a dash
     */
    private String getCurrentMonthYear() {

        Calendar calendar = new GregorianCalendar();

        String currentMonth = "Enero";
        switch (calendar.get(Calendar.MONTH)) {

            case 0:
                currentMonth = "Enero";
                break;
            case 1:
                currentMonth = "Febrero";
                break;
            case 2:
                currentMonth = "Marzo";
                break;
            case 3:
                currentMonth = "Abril";
                break;
            case 4:
                currentMonth = "Mayo";
                break;
            case 5:
                currentMonth = "Junio";
                break;
            case 6:
                currentMonth = "Julio";
                break;
            case 7:
                currentMonth = "Agosto";
                break;
            case 8:
                currentMonth = "Septiembre";
                break;
            case 9:
                currentMonth = "Octubre";
                break;
            case 10:
                currentMonth = "Noviembre";
                break;
            case 11:
                currentMonth = "Diciembre";
                break;
        }

        return currentMonth + calendar.get(Calendar.YEAR);
    }

    /**
     * Creates a <code>BufferedWriter</code> for the log file of current month
     *
     * @param application Application that produces error to write, if is an
     * empty String, open FileWriter to file for unknown errors
     * @return A <code>BufferedWriter</code> ready to use to write events to
     * current month log file
     */
    private FileWriter getLogWriter(String application) {

        StringBuilder fileNameSB = new StringBuilder("../logs/LOG_");
        if (application.length() > 0) {
            fileNameSB.append(application);
            fileNameSB.append("_");
        } else {
            fileNameSB.append("ERRDESCONOCIDO_");
        }

        fileNameSB.append(this.getCurrentMonthYear());
        fileNameSB.append(".log");

        try {

            File logFile = new File(fileNameSB.toString());
            FileWriter fileWriter = new FileWriter(logFile, true);
            return fileWriter;
        } catch (IOException ex) {

            System.err.println("No se puede abrir el archivo de log para escritura");
            System.err.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Gets a user friendly message generated based on previously registered
     * errors.
     *
     * @return User friendly message about last registered event
     */
    public String getMensajeError() {
        return this.mensajeError;
    }

}
