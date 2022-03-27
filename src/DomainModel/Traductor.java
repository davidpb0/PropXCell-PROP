package DomainModel;
/*
 * ClassName DomainModel.Traductor
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Traductor<T> {
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
     * @return el string convertido a fecha.
     * Convierte un string a una fecha, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a fecha.
     */
    private static Date StringDate(String _s) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(_s);
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
     * @return el objeto convertido a String.
     * Convierte cualquier objeto de tipo básico a String.
     * @param _objeto el objeto a convertir a String.
     */
    private String ObjectString(T _objeto) { return _objeto.toString(); }
}
