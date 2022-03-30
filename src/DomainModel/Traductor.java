package DomainModel;
/*
 * ClassName DomainModel.Traductor
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Traductor {
    private static Traductor unico_traductor;                               // Única instancia

    private Traductor() {}

    public static Traductor getTraductor() {
        if (unico_traductor == null) unico_traductor = new Traductor();
        return unico_traductor;
    }

    /**
     * @return true si _s puede formatearse como un entero, false en caso contrario.
     * Comprueba que la sintaxis de un string corresponda a la de un número entero.
     * @param _s el parámetro a comprobar.
     */
    private static boolean esInt(String _s) {
        return _s != null && _s.matches("[0-9.]+");
    }

    /**
     * @return el string convertido a número entero.
     * Convierte un string a un número entero, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a entero.
     */
    private static int StringInt(String _s) {
        if (esInt(_s)) return Integer.parseInt(_s);
        return -1;                                                          // Cambiar por throw exception
    }

    /**
     * @return el string convertido a float.
     * Convierte un string a una float, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a float.
     */
    private static float StringFloat(String _s) {
        return Float.parseFloat(_s);
    }

    /**
     * @return el string convertido a fecha.
     * Convierte un string a una fecha, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a fecha.
     */
    private static Date StringDate(String _s) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(_s);
    }

    /**
     * @return el entero convertido a String.
     * Convierte un entero a String.
     * @param _entero el entero a convertir a String.
     */
    private static String IntString(Integer _entero) { return String.valueOf(_entero); }

    /**
     * @return el float convertido a String.
     * Convierte un float a String.
     * @param _float el float a convertir a String.
     */
    private static String FloatString(Float _float) { return String.valueOf(_float); }

    /**
     * @return la fecha convertido a String.
     * Convierte una fecha a String en formato dd/MM/aaaa.
     * @param _fecha la fecha a convertir a String.
     */
    private static String DateString(Date _fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(_fecha);
    }


}
