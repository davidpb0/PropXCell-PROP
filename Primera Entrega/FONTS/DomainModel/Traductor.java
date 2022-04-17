package DomainModel;
/*
 * ClassName DomainModel.Traductor
 *
 * Version info 0.0.4
 *
 * Author Iván Risueño Martín
 */

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

    /**
     * @param _formula contenido introducido por el usuario
     * @return tipo de contenido especificado por la fórmula
     */
    private static String detecta(String _formula) {
        if (_formula.charAt(0) == '=' && _formula.indexOf(')') == _formula.length() - 1) {
            if (_formula.indexOf("=abs(") == 0) return "#ABS";
            else if (_formula.indexOf("=trunc(") == 0) return "#TRUNC";
            else if (_formula.indexOf("=aprox(") == 0) return "#APROX";
            else if (_formula.indexOf("=convertirDB(") == 0) return "#VDB";
            else if (_formula.indexOf("=convertirBD(") == 0) return "#VBD";
            else if (_formula.indexOf("=convertirHD(") == 0) return "#VHD";
            else if (_formula.indexOf("=convertirDH(") == 0) return "#VDH";
            else if (_formula.indexOf("=convertirHB(") == 0) return "#VHB";
            else if (_formula.indexOf("=convertirBH(") == 0) return "#VBH";
            else if (_formula.indexOf("=mes(") == 0) return "#MES";
            else if (_formula.indexOf("=año(") == 0) return "#AÑO";
            else if (_formula.indexOf("=diasemana(") == 0) return "#DIAS";
            else if (_formula.indexOf("=nombredia(") == 0) return "#NDIA";
            else if (_formula.indexOf("=longitud(") == 0) return "#LONG";
            else if (_formula.indexOf("=media(") == 0) return "#MEDIA";
            else if (_formula.indexOf("=mediana(") == 0) return "#MEDIANA";
            else if (_formula.indexOf("=varianza(") == 0) return "#VAR";
            else if (_formula.indexOf("=covarianza(") == 0) return "#COV";
            else if (_formula.indexOf("=desviacion(") == 0) return "#DESV";
            else if (_formula.indexOf("=pearson(") == 0) return "#COEFP";
            else return "#ERRORFUNC";
        } else if (_formula.charAt(0) == '$' && _formula.length() <= 5) { // Como mucho $AA11
            return "#REFERENCE";
        }
        return "#VALUE";
    }
}
