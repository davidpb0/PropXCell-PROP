package main.Persistence.PersistenceControllers;

import main.Persistence.PersistenceClasses.CargaCSV;
import main.Persistence.PersistenceClasses.EscribeCSV;

import java.io.IOException;

/*ClassName ControladorCSV
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */
public class ControladorCSV {
    private EscribeCSV escribecsvRef;
    private CargaCSV cargacsvRef;
    private int IDHojaActual;

    /**
     * Constructora básica para el ControladorCSV
     */
    public ControladorCSV() {}

    /**
     *
     * @param _ubicacion del archivo a escribir
     * @param _separador del archivo a escribir
     */
    public void asignaEscritor(String _ubicacion, String _separador) {
        this.escribecsvRef = new EscribeCSV(_ubicacion, _separador);
    }

    /**
     *
     * @param _ubicacion del archivo a leer
     * @param _separador del archivo a leer
     */
    public void asignaLector(String _ubicacion, String _separador) {
        this.cargacsvRef = new CargaCSV(_ubicacion, _separador);
    }

    /**
     * invoca la operación lee() de CargaCSV
     * @throws IOException exepción que ocurre por leer de algo inexistente, o vacío
     */
    public void cargaCSV() throws IOException {
        cargacsvRef.lee();
    }

    /**
     * invoca la operación guarda() de EscribeCSV
     * @param _nombreCSV nombre del archivo CSV a guardar
     * @param IDHoja ID de la hoja que se va a exportar a CSV
     */
    public void escribeCSV(String _nombreCSV, int IDHoja) throws IOException {
        escribecsvRef.guarda(_nombreCSV, IDHoja);
    }
}

