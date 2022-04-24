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

public class Traductor {

    private static Traductor traductor;

    /**
     * Método para acceder al traductor
     * @return la instancia singleton del traductor
     */
    public static Traductor getTraductor() {
        if (traductor == null) traductor = new Traductor();
        return traductor;
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
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * @return el string convertido a float.
     * Convierte un string a una float, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a float.
     */
    public float StringFloat(String _s) {
        try {
            return Float.parseFloat(_s);
        } catch (NumberFormatException e) {
            System.err.println("String no convertible a float: el valor del String es " + _s);
            e.printStackTrace();
        }

        return -1f;
    }

    /**
     * @return el string convertido a fecha.
     * Convierte un string a una fecha, si su sintaxis es correcta.
     * @param _s el parámetro a convertir a fecha.
     */
    public Date StringDate(String _s) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            return formatoFecha.parse(_s);
        } catch (ParseException pe) {
            System.err.println("String no convertible a date: el valor del String es " + _s);
            pe.printStackTrace();
        }

        return null;
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
     * Pre: _formula es de tipo "=func1aria(args)", "=funcNaria(arg1, arg2)", "=$AA11", o "01/01/01"
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
        } else if (_formula.charAt(1) == '$' && _formula.length() <= 6) { // Como mucho =$AA11
            return "#REFERENCIA";
        } else if (_formula.charAt(2) == '/' && _formula.charAt(5) == '/') {
            Date fecha = getTraductor().StringDate(_formula);
            return "#FECHA";
        }
        return "#VALUE";
    }

    /**
     * Pre: _c es un string desde "A" hasta "ZZ"
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
     * Pre: _pos es una posición que existe desde "$A1" hasta "$ZZ99", _idH es el id de una hoja que existe
     * Retorna la celda asociada a los parámetros de entrada
     * @param _pos posición de la celda en la hoja
     * @param _idH hoja en la que se encuentra la celda
     * @return la celda identificada por los parámetros de entrada
     */
    public Celda traduceCelda(String _pos, int _idH) {
        Celda c;
        Hoja h = Documento.getDocumento().getHoja(_idH);
        String s = _pos;
        if (_pos.startsWith("$")) s = _pos.substring(1);
        int j = 0;
        while (j < s.length() && s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') ++j;
        String columna = s.substring(0, j - 1);
        String fila = s.substring(j);
        Posicion p = new Posicion(getTraductor().StringInt(fila), traduceColumna(columna));
        c = h.getCelda(p);

        return c;
    }

    /**
     * Pre: _funcion es un String de tipo "=func1aria(arg)", _idH corresponde al id de una hoja que existe
     * Retorna los argumentos de una función introducida por el usuario
     * @param _funcion la expresión introducida por el usuario
     * @return un array de String con cada valor que especifica el argumento
     */
    public String[] getArgumentosFuncion1aria(String _funcion, int _idH) {
        Hoja h = Documento.getDocumento().getHoja(_idH);
        String f = _funcion;
        if (_funcion.startsWith("=")) f = _funcion.substring(_funcion.indexOf('(') + 1, _funcion.lastIndexOf(')'));
        else System.err.println("Argumento erróneo, el string proporcionado tiene que ser de tipo =func(arg). String actual: " + _funcion);
        ArrayList<String> ret = new ArrayList<>();


        if (f.startsWith("$") && f.length() <= 5) { // Como mucho $AA11
            Celda c = getTraductor().traduceCelda(f.substring(1, f.length() - 1), _idH);
            try {
                ret.add(c.getValor());
            } catch (NullPointerException np) {
                System.err.println("Intento de acceso a celda inexistente o vacía.");
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
     * @param _idH id de la hoja actual
     * @return un vector que contiene hasta 4 vectores de Strings, cada uno con los argumentos entre las comas
     */
    public String[][] getArgumentosFuncionNaria(String _funcion, int _idH) {
        String[][] ret = new String[4][];
        String f = _funcion.substring(_funcion.indexOf('(') + 1, _funcion.lastIndexOf(')'));
        String[] args = f.split(",");
        if (args.length < 2) return null;

        for (int i = 0; i < args.length; ++i) {
            if (args[i].contains(":")) { //$A1:$B1
                String[] s = args[i].split(":");
                Celda principioC = traduceCelda(s[0], _idH);
                Celda finalC = traduceCelda(s[1], _idH);
                ArrayList<Celda> celdas = Documento.getDocumento().getHoja(_idH).getColumnaFila(s[0], s[1]);

                String[] argsI = new String[celdas.size()];
                int j = 0;
                for (Celda c : celdas) {
                    argsI[j] = c.getValor();
                    ++j;
                }
                ret[i] = argsI;

            } else if (args[i].startsWith("$") && args[i].length() <= 5) { //$AA11
                ret[i] = new String[]{traduceCelda(args[i], _idH).getValor()};

            } else { // Es un número
                ret[i] = new String[]{args[i]};
            }
        }

        return ret;
    }
}
