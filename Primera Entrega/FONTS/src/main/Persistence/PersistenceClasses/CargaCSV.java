package main.Persistence.PersistenceClasses;
/*
 * ClassName DomainModel.CargaCSV
 *
 * Version info 0.0.4
 *
 * Author Iván Risueño Martín
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargaCSV {
    private String ubicacion;

    /**
     * Constructora de la clase CargaCSV
     * @param _ubicacion utilizada para cargar el CSV
     */
    public CargaCSV (String _ubicacion) {
        this.ubicacion = _ubicacion;
    }

    /**
     * Elimina las comillas del parámetro especificado
     * @param _datos vector de String que tratar
     * @return el parámetro ahora sin comillas
     */
    private String[] eliminaComillas(String[] _datos) {
        String COMILLAS = "\"";
        String[] s = new String[_datos.length];

        for (int i = 0; i < s.length; i++){
            s[i] = _datos[i].replaceAll("^"+COMILLAS, "").replaceAll(COMILLAS+"$", "");
        }
        return s;
    }

    /**
     * Lee un archivo en formato CSV
     */
    public ArrayList<String> leeCSV() {
        ArrayList<String> ret = new ArrayList<>();
        try {
            //comprobar si existe el archivo con la funcion de abajo, sino excepción
            BufferedReader br = new BufferedReader(new FileReader(this.ubicacion));
            String fila = br.readLine();
            while (fila != null) {
                ret.add(fila);
                fila = br.readLine();
            }
            br.close();

            return ret;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
            e.printStackTrace();
        }

        return ret;
    }
}
