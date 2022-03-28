package DomainModel;

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
     * @param _d es el documento a formatear y guardar como CSV
     * @param _nombreDoc es el nombre del archivo CSV
     */
    public void guarda(Documento _d, String _nombreDoc) {
        //...
    }
}
