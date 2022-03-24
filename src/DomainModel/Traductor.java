package DomainModel;

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

    private static boolean esInt(String s) {
        return s != null && s.matches("[0-9.]+");
    }

    private static int StringInt(String s) {
        if (esInt(s)) return Integer.parseInt(s);
        return -1;                                                          // Cambiar por throw exception
    }

    private static Date StringDate(String s) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(s);
    }

    private static float StringFloat(String s) {
        return Float.parseFloat(s);
    }
}
