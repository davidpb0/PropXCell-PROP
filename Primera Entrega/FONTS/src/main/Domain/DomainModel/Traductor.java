package main.Domain.DomainModel;
/*
 * ClassName DomainModel.Traductor
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import main.Domain.DomainControllers.ControladorDominio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Traductor {

    /**
     * @return el string convertido a número entero.
     * Convierte un string a un número entero, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a entero.
     */
    public static int StringInt(String _s) throws Exception {
        try {
            return Integer.parseInt(_s);
        } catch (NumberFormatException e) {
            throw new Exception("String no convertible a int: el valor del String es " + _s);
        }
    }

    /**
     * @return el string convertido a float.
     * Convierte un string a una float, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a float.
     */
    public static float StringFloat(String _s) throws Exception {
        try {
            return Float.parseFloat(_s);
        } catch (NumberFormatException e) {
            throw new Exception("String no convertible a float: el valor del String es " + _s);
        }
    }

    /**
     * @return el string convertido a fecha.
     * Convierte un string a una fecha, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a fecha.
     */
    public static Date StringDate(String _s) throws Exception {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            return formatoFecha.parse(_s);
        } catch (ParseException pe) {
            throw new Exception("String no convertible a date: el valor del String es " + _s);
        }
    }

    /**
     * @return el entero convertido a String.
     * Convierte un entero a String.
     * @param _entero el entero a convertir a String.
     */
    public static String IntString(Integer _entero) { return String.valueOf(_entero); }

    /**
     * @return el float convertido a String.
     * Convierte un float a String.
     * @param _float el float a convertir a String.
     */
    public static String FloatString(Float _float) { return String.valueOf(_float); }

    /**
     * @return la fecha convertido a String.
     * Convierte una fecha a String en formato dd/MM/aaaa.
     * @param _fecha la fecha a convertir a String.
     */
    public static String DateString(Date _fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(_fecha);
    }

    /**
     * Pre: _formula es de tipo "=func1aria(args)", "=funcNaria(arg1, arg2)", "=$AA11", o "01/01/01"
     * @param _formula contenido introducido por el usuario
     * @return tipo de contenido especificado por la fórmula
     */
    public static String detecta(String _formula) {
        if (_formula.charAt(0) == '=' && _formula.indexOf(')') == _formula.length() - 1) {
            if (_formula.indexOf("=suma(") == 0) return "#SUMA";
            else if (_formula.indexOf("=resta(") == 0) return "#RESTA";
            else if (_formula.indexOf("=mult(") == 0) return "#MULT";
            else if (_formula.indexOf("=div(") == 0) return "#DIV";
            else if (_formula.indexOf("=abs(") == 0) return "#ABS";
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
        } else if (_formula.charAt(0) == '=' && _formula.charAt(1) == '$' && _formula.length() <= 6) { // Como mucho =$AA11
            return "#REFERENCIA";
        }
        return "#VALUE";
    }

    /**
     * Pre: _c es un string desde "A" hasta "ZZ"
     * Retorna el número de columna que corresponde a su valor alfabético
     * @param _c columna(alfabético)
     * @return identificador de la columna que corresponde a su valor alfabético
     */
    public static int traduceColumna(String _c) {
        int ret = 0;
        if (_c.length() == 1) {
            int cr = _c.charAt(0);
            ret = cr - 64;
        } else {
            for (int i = 0; i < _c.length(); ++i) {
                ret += 26 * i + traduceColumna(Character.toString(_c.charAt(i)));
            }
        }
        return ret;
    }

    /**
     * Pre: _pos es una posición que existe desde "$A1" hasta "$ZZ99", _idH es el id de una hoja que existe
     * Retorna la celda asociada a los parámetros de entrada
     * @param _pos posición de la celda en la hoja
     * @return la celda identificada por los parámetros de entrada
     */
    public static Celda traduceCelda(String _pos) throws Exception {
        Celda c;
        Documento d = Documento.getDocumento();
        if(d.getNombre().isEmpty()) d.inicializaDocumentoDefault("Doc1");
        Hoja h = ControladorDominio.getControladorDominio().getControladorHoja().getHojaRef();
        String s = _pos;
        if (_pos.startsWith("=$")) s = _pos.substring(2);
        else if (_pos.startsWith("$")) s = _pos.substring(1);
        int j = 0;
        while (j < s.length() && s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') ++j;
        String columna = s.substring(0, j);
        String fila = s.substring(j);
        Posicion p = new Posicion(StringInt(fila), traduceColumna(columna));
        c = h.getCelda(p);

        return c;
    }

    /**
     * Pre: _funcion es un String de tipo "=func1aria(arg)", _idH corresponde al id de una hoja que existe
     * Retorna los argumentos de una función introducida por el usuario
     * @param _funcion la expresión introducida por el usuario
     * @return un array de String con cada valor que especifica el argumento
     */
    public static String[] getArgumentosFuncion1aria(String _funcion) throws Exception {
        Hoja h = ControladorDominio.getControladorDominio().getControladorHoja().getHojaRef();
        String f = _funcion;
        if (_funcion.startsWith("=") && _funcion.charAt(1) != '$') f = _funcion.substring(_funcion.indexOf('(') + 1, _funcion.lastIndexOf(')'));
        else throw new Exception("Argumento erróneo, el string proporcionado tiene que ser de tipo =func(arg). String actual: " + _funcion);
        ArrayList<String> ret = new ArrayList<>();


        if (f.startsWith("=$") && f.length() <= 6) { // Como mucho $AA11
            Celda c = traduceCelda(f.substring(1, f.length() - 1));
            try {
                ret.add(c.getValor());
            } catch (NullPointerException np) {
                throw new Exception("Intento de acceso a celda inexistente o vacía.");
            }
        } else if (f.contains(":")) { // $A1:$B2
            String from = f.split(":")[0];
            String to = f.split(":")[1];
            from = from.replaceAll("[$]", "");
            to = to.replaceAll("[$]", "");

            ArrayList<Celda> intervalo = h.getColumnaFila(from, to);
            for (Celda c : intervalo) {
                ret.add(c.getValor());
            }
        } else { // Otro valor
            ret.add(f);
        }

        return ret.toArray(new String[0]);
    }

    /**
     * Pre: _funcion es un String de tipo "=funcNaria(arg1, arg2)", _idH corresponde al id de una hoja que existe
     * @param _funcion introducida por el usuario
     * @return un vector que contiene hasta 4 vectores de Strings, cada uno con los argumentos entre las comas
     */
    public static ArrayList<String[]> getArgumentosFuncionNaria(String _funcion) throws Exception {
        ArrayList<String[]>ret = new ArrayList<>();
        String f = _funcion.substring(_funcion.indexOf('(') + 1, _funcion.lastIndexOf(')'));
        String[] args = f.split(",");
        //if (args.length < 2) return null;

        for (String arg : args) {
            if (arg.contains(":")) { //$A1:$B1
                String[] s = arg.split(":");
                Celda principioC = traduceCelda(s[0]);
                Celda finalC = traduceCelda(s[1]);
                ArrayList<Celda> celdas = ControladorDominio.getControladorDominio().getControladorHoja().getHojaRef().getColumnaFila(s[0], s[1]);

                String[] argsI = new String[celdas.size()];
                int j = 0;
                for (Celda c : celdas) {
                    argsI[j] = c.getValor();
                    ++j;
                }
                ret.add(argsI);

            } else if (arg.startsWith("$") && arg.length() <= 5) { //$AA11
                ret.add(new String[]{traduceCelda(arg).getValor()});

            } else { // Es un número
                String[] s = {arg};
                ret.add(s);
            }
        }

        return ret;
    }
}
