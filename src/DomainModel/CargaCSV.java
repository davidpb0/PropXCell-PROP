package DomainModel;
/*
 * ClassName DomainModel.CargaCSV
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.util.ArrayList;
import java.io.*;

public class CargaCSV {
    private ArrayList<String[]> datos;
    private String separador;
    private static final String comillas = "\"";

    /**
     * Constructora de la clase CargaCSV
     * @param _separador utilizado por el CSV a cargar
     */
    public CargaCSV (String _separador) {
        this.separador = _separador;
    }

    /**
     * Elimina las comillas del parámetro especificado
     * @param _datos vector de String que tratar
     * @return el parámetro ahora sin comillas
     */

    private String[] eliminaComillas(String[] _datos) {
        String[] s = new String[_datos.length];

        for (int i = 0; i < s.length; i++){
            s[i] = _datos[i].replaceAll("^"+comillas, "").replaceAll(comillas+"$", "");
        }
        return s;
    }

    /**
     * Lee un archivo en formato csv
     * @param _ubicacionArchivo ubicación del archivo a abrir
     */
    public void lee(String _ubicacionArchivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(_ubicacionArchivo));
        String fila = br.readLine();
        while (fila != null) {
            String[] datos = fila.split(separador);
            datos = eliminaComillas(datos);
            this.datos.add(datos);
            fila = br.readLine();
        }
    }

    /**
     * Comprueba que exista un archivo dado su path
     * @param _ubicacionArchivo supuesta ubicación del archivo
     */
    public static boolean existeArchivo(String _ubicacionArchivo) {
        File archivo = new File(_ubicacionArchivo);
        return archivo.exists() && archivo.isFile();
    }
}
