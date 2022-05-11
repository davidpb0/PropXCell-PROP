package main.Persistence.PersistenceClasses;
/*
 * ClassName DomainModel.EscribeCSV
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.io.*;
import java.util.ArrayList;

public class EscribeCSV {
    private String ubicacion;
    private ArrayList<String> contenido;

    /**
     * Constructora de la clase EscribeCSV
     * @param _ubicacion path donde guardar el archivo
     * @param _contenido contenido del CSV a guardar
     */
    public EscribeCSV(String _ubicacion, ArrayList<String> _contenido) {
        this.ubicacion = _ubicacion;
        this.contenido = _contenido;
    }

    /**
     * Guarda en un archivo CSV las entradas del parámetro contenido
     * @param _nombreCSV nombre del archivo a crear
     */
    public void guardaCSV(String _nombreCSV) throws Exception {
        try {
            File CSV = new File(this.ubicacion + "/" + _nombreCSV + ".csv");
            FileOutputStream fos = new FileOutputStream(CSV);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Writer w = new BufferedWriter(osw);

            for (String cont : this.contenido) w.write(cont + "\n");
            w.close();
        } catch (IOException e) {
            throw new Exception("Error al escribir el fichero.");
        }
    }
}
