package DomainModel;
/*
 * ClassName DomainModel.EscribeCSV
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

import java.io.*;
import java.util.HashMap;

public class EscribeCSV {
    private String ubicacion;
    private String separador;

    /**
     * Constructora de la clase EscribeCSV
     * @param _ubicacion path donde guardar el archivo
     * @param _separador separador para delimitar dónde empieza y/o acaban los valores
     */
    public EscribeCSV(String _ubicacion, String _separador) {
        this.ubicacion = _ubicacion;
        this.separador = _separador;
    }

    /**
     * Guarda el documento como CSV en un archivo en disco
     * @param _nombreCSV nombre del archivo CSV
     * @param IDHoja hoja para exportar a CSV
     */
    public void guarda(String _nombreCSV, int IDHoja) throws IOException {
        // Preparamos el archivo y el buffer para escribir en él
        File CSV = new File(ubicacion + "/" + _nombreCSV);
        FileOutputStream fos = new FileOutputStream(CSV);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        Writer w = new BufferedWriter(osw);

        // Conseguimos la información necesaria para empezar a escribir y escribimos
        Hoja hoja = Documento.getDocumento().getHoja(IDHoja);
        HashMap<Posicion, Celda> celdas = hoja.getCeldas();
        int ncolumnas = hoja.getColumnas();
        int col = 1;

        for (Posicion pos: celdas.keySet()) {
            if (col == ncolumnas) {
                w.write("\n");
                col = 1;
            } else if (col != 1) w.write(separador);
            Celda c = celdas.get(pos);
            String s = c.getValor();
            w.write(s);

            ++col;
        }

        w.close();
    }
}
