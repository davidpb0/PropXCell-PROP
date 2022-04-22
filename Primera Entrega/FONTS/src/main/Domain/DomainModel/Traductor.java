package main.Domain.DomainModel;
/*
 * ClassName DomainModel.Traductor
 *
 * Version info 0.0.4
 *
 * Author Iván Risueño Martín
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Traductor {

    private Traductor traductor;

    /**
     * Método para acceder al traductor
     * @return la instancia singleton del traductor
     */
    public Traductor getTraductor() {
        if (this.traductor == null) this.traductor = new Traductor();
        return this.traductor;
    }

    /**
     * Creadora por defecto.
     */
    private Traductor() {}

    /**
     * @return el string convertido a número entero.
     * Convierte un string a un número entero, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a entero.
     */
    public int StringInt(String _s) {
        try {
            return Integer.parseInt(_s);
        } catch (NumberFormatException e) {
            System.err.println("String no convertible a int: el valor del String es " + _s);
        }

        return
    }

    /**
     * @return el string convertido a float.
     * Convierte un string a una float, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a float.
     */
    public float StringFloat(String _s) {
        return Float.parseFloat(_s);
    }

    /**
     * @return el string convertido a fecha.
     * Convierte un string a una fecha, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a fecha.
     */
    public Date StringDate(String _s) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(_s);
    }

    /**
     * @return el entero convertido a String.
     * Convierte un entero a String.
     * @param _entero el entero a convertir a String.
     */
    public String IntString(Integer _entero) { return String.valueOf(_entero); }

    /**
     * @return el float convertido a String.
     * Convierte un float a String.
     * @param _float el float a convertir a String.
     */
    public String FloatString(Float _float) { return String.valueOf(_float); }

    /**
     * @return la fecha convertido a String.
     * Convierte una fecha a String en formato dd/MM/aaaa.
     * @param _fecha la fecha a convertir a String.
     */
    public String DateString(Date _fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(_fecha);
    }

    /**
     * @param _formula contenido introducido por el usuario
     * @return tipo de contenido especificado por la fórmula
     */
    public String detecta(String _formula) {
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
            else if (_formula.indexOf("=contarLetra(") == 0) return "#CLETRA";
            else if (_formula.indexOf("=reemplazarPal(") == 0) return "#REEMPPAL";
            else if (_formula.indexOf("=reemplazarLet(") == 0) return "#REEMPLET";
            else return "#ERRORFUNC";
        } else if (_formula.charAt(0) == '$' && _formula.length() <= 5) { // Como mucho $AA11
            return "#REFERENCIA";
        } else if (_formula.charAt(2) == '/' && _formula.charAt(5) == '/') {
            try {
                Date fecha = getTraductor().StringDate(_formula);
                return "#FECHA";
            } catch (ParseException pe) {
                return "#ERRORFECHA";
            }
        }
        return "#VALUE";
    }

    /**
     * Retorna el número de columna que corresponde a su valor alfabético
     * @param _c columna(alfabético)
     * @return identificador de la columna que corresponde a su valor alfabético
     */
    public int traduceColumna(String _c) {
        int ret = 0;
        if (_c.length() == 1) {
            ret = getTraductor().StringInt(_c) - 16;
        } else {
            for (int i = 0; i < _c.length(); ++i) {
                ret += 26 * i + getTraductor().traduceColumna(Character.toString(_c.charAt(i)));
            }
        }
        return ret;
    }

    /**
     * Retorna la celda asociada a los parámetros de entrada
     * @param _pos posición de la celda en la hoja
     * @param _idH hoja en la que se encuentra la celda
     * @return la celda identificada por los parámetros de entrada
     */
    public Celda traduceCelda(String _pos, int _idH) {
        Celda c = null;
        Hoja h = Documento.getDocumento().getHoja(_idH);
        String s = _pos;
        if (_pos.startsWith("$")) s = _pos.substring(1);
        int j = 0;
        while (j < s.length() && s.charAt(j) <= 'Z') ++j;
        String columna = s.substring(0, j - 1);
        String fila = s.substring(j);
        Posicion p = new Posicion(getTraductor().StringInt(fila), traduceColumna(columna));
        c = h.getCelda(p);

        return c;
    }

    /**
     * Retorna los argumentos de una función introducida por el usuario
     * @param _funcion la expresión introducida por el usuario
     * @return un array de String con cada valor que especifica el argumento
     */
    public String[] getArgumentos(String _funcion, int _idH) {
        Hoja h = Documento.getDocumento().getHoja(_idH);
        String f = _funcion;
        if (_funcion.startsWith("=")) f = _funcion.substring(_funcion.indexOf('('), _funcion.lastIndexOf(')'));
        String[] args = f.split(",");
        List<String> ret = null;

        for (String arg : args) {
            if (arg.startsWith("$") && arg.length() <= 5) {                                     // Como mucho $AA11
                Celda c = getTraductor().traduceCelda(arg, _idH);
                try {
                    ret.add(c.getValor());
                } catch (NullPointerException np) {
                    System.err.println("Intento de acceso a celda inexistente o vacía.");
                }
            } else if (arg.contains(":")) {
                String from = arg.split(":")[0];
                String to = arg.split(":")[1];
                from = from.replaceAll("[$]", "");
                to = to.replaceAll("[$]", "");

                ArrayList<Celda> intervalo = h.getColumnaFila(from, to);
                for (Celda c : intervalo) {
                    ret.add(c.getValor());
                }
            } else ret.add(arg);
        }

        return (String[]) ret.toArray();
    }

    /*public Posicion getPosReferencia(String _ref){

    }*/
}
