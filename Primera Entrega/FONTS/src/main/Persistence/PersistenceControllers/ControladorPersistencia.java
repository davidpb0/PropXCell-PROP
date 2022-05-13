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

    private ControladorPersistencia() {
        this.controladorCSVRef = new ControladorCSV();
        this.controladorDocumentoPersistenciaRef = new ControladorDocumentoPersistencia();
    }

    public static ControladorPersistencia getControladorPersistencia() {
        if (instanceOfThisClass == null) instanceOfThisClass = new ControladorPersistencia();
        return instanceOfThisClass;
    }

    public ControladorCSV getControladorCSV() { return this.controladorCSVRef; }

    public ControladorDocumentoPersistencia getControladorDocumentoPersistenciaRef() {return this.controladorDocumentoPersistenciaRef; }
}
