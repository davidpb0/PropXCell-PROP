package DomainModel;
/*
 * ClassName DomainModel.CargaCSV
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.util.HashMap;
import java.io.*;

public class CargaCSV {
    private HashMap<Posicion, Celda> datos;
    private String ubicacion;
    private String separador;
    private static final String COMILLAS = "\"";

    /**
     * Constructora de la clase CargaCSV
     * @param _ubicacion utilizada para cargar el CSV
     * @param _separador utilizado por el CSV a cargar
     */
    public CargaCSV (String _ubicacion, String _separador) {
        this.separador = _separador;
        this.ubicacion = _ubicacion;
    }

    /**
     * Elimina las comillas del parámetro especificado
     * @param _datos vector de String que tratar
     * @return el parámetro ahora sin comillas
     */
    private String[] eliminaComillas(String[] _datos) {
        String[] s = new String[_datos.length];

        for (int i = 0; i < s.length; i++){
            s[i] = _datos[i].replaceAll("^"+COMILLAS, "").replaceAll(COMILLAS+"$", "");
        }
        return s;
    }

    /**
     * Lee un archivo en formato csv
     */
    public void lee() throws IOException {
        //comprobar si existe el archivo con la funcion de abajo, sino excepción
        BufferedReader br = new BufferedReader(new FileReader(ubicacion));
        String fila = br.readLine();
        int i = 1;
        while (fila != null) {
            String[] datos = fila.split(separador);
            datos = eliminaComillas(datos);
            for (int j = 0; j < datos.length; ++j) {
                this.datos.put(new Posicion(i, j + 1), new Celda(datos[j], datos[j]));
            }
            fila = br.readLine();
            ++i;
        }
        br.close();
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
