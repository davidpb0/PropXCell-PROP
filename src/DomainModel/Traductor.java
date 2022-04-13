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

    private Traductor() {}

    /**
     * @return el string convertido a número entero.
     * Convierte un string a un número entero, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a entero.
     */
    private static int StringInt(String _s) {
        return Integer.parseInt(_s);
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
