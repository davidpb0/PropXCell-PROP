package main.Persistence.PersistenceControllers;

/* ClassName ControladorCSV
 *
 * Version info 0.0.5
 *
 * Author Iván Risueño Martín
 */

import main.Persistence.PersistenceClasses.*;
import java.util.ArrayList;

/**
 * ControladorCSV, utilizado para englobar los casos de uso relacionados con los CSVs.
 * @author Iván Risueño Martín
 */

public class ControladorCSV {

    private EscribeCSV escribecsvRef;
    private CargaCSV cargacsvRef;

    /**
     * Constructora básica para el ControladorCSV
     */
    public ControladorCSV() {}

    /**
     *
     * @param _ubicacion del archivo a escribir
     * @param _contenido contenido del CSV a escribir
     */
    public void asignaEscritor(String _ubicacion, ArrayList<String> _contenido) {
        this.escribecsvRef = new EscribeCSV(_ubicacion, _contenido);
    }

    /**
     *
     * @param _ubicacion del archivo a leer
     */
    public void asignaLector(String _ubicacion) {
        this.cargacsvRef = new CargaCSV(_ubicacion);
    }

    /**
     * invoca la operación leeCSV() de CargaCSV, y destruye el objeto a continuación
     */
    public ArrayList<String> cargaCSV() throws Exception {
        ArrayList<String> ret = cargacsvRef.leeCSV();
        this.cargacsvRef = null;
        return ret;
    }

    /**
     * invoca la operación guardaCSV() de EscribeCSV, y destruye el objeto a continuación
     * @param _nombreCSV nombre del archivo CSV a guardar
     */
    public void escribeCSV(String _nombreCSV) throws Exception {
        escribecsvRef.guardaCSV(_nombreCSV);
        this.escribecsvRef = null;
    }
}


