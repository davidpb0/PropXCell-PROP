package main.Persistence.PersistenceControllers;
/*ClassName ControladorPersistencia
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

public class ControladorPersistencia {
    private static ControladorPersistencia instanceOfThisClass;
    private final ControladorCSV controladorCSVRef;
    private final ControladorDocumentoPersistencia controladorDocumentoPersistenciaRef;

    /**
     * Creadora privada del Controlador de Persistencia. Crea el objeto e inicializa el resto de Controladores.
     */
    private ControladorPersistencia() {
        this.controladorCSVRef = new ControladorCSV();
        this.controladorDocumentoPersistenciaRef = new ControladorDocumentoPersistencia();
    }

    /**
     * Getter del Controlador de Persistencia.
     * @return la instancia singleton del Controlador de Persistencia.
     */
    public static ControladorPersistencia getControladorPersistencia() {
        if (instanceOfThisClass == null) instanceOfThisClass = new ControladorPersistencia();
        return instanceOfThisClass;
    }

    /**
     * Getter del ControladorCSV.
     * @return la instancia del ControladorCSV.
     */
    public ControladorCSV getControladorCSV() { return this.controladorCSVRef; }

    /**
     * Getter del ControladorDocumentoPersistencia.
     * @return la instancia del ControladorDocumentoPersistencia.
     */
    public ControladorDocumentoPersistencia getControladorDocumentoPersistenciaRef() {return this.controladorDocumentoPersistenciaRef; }
}
