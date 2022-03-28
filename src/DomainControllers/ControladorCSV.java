package DomainControllers;
import DomainModel.*;

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
     * @throws IOException
     */
    public void cargaCSV() throws IOException {
        cargacsvRef.lee();
    }

    /**
     * invoca la operación guarda() de EscribeCSV
     */
    public void escribeCSV(String _nombreDoc) {
        escribecsvRef.guarda(Documento.getDocumento(), _nombreDoc);
    }
}


