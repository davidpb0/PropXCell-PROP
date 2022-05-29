package main.Domain.DomainControllers;
/*
 * Controlador Documento
 *
 * v0.0.3
 *
 * Joaquim Torra Garcia
 */

import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import main.Persistence.PersistenceControllers.ControladorCSV;
import main.Persistence.PersistenceControllers.ControladorDocumentoPersistencia;
import main.Persistence.PersistenceControllers.ControladorPersistencia;


public class ControladorDocumento {
    private Documento documento_ref;

    /**
     * Creadora por defecto.
     */
    public ControladorDocumento() {}

    public Documento getDocumento() {
        return documento_ref;
    }

    /**
     * Cierra el documento actual.
     */
    public void cerrarDocumento() {
        documento_ref = null;
    }

    /**
     * Cierra el documento actual y abre uno nuevo.
     * @param filas numero de filas para la hoja del documento
     * @param columnas numero de columnas para la hoja del documento
     */
    public void crearDocumento(int filas, int columnas) {
        if (documento_ref != null) {
            documento_ref.eliminaDocumento();
            cerrarDocumento();
        }
        documento_ref = Documento.getDocumento();
        if (documento_ref.getNumHojas() == 0) documento_ref.inicializaDocumento("Documento sin título", filas, columnas);
    }

    /**
     * Elimina el documento abierto actualmente.
     */
    public void eliminarDocumento() {
        documento_ref.eliminaDocumento();
        documento_ref = null;
    }

    /**
     * Añade una hoja vacía le pone el id que toca a continuación.
     */
    public void anadirHoja() {
        documento_ref.anadeHojaDf();
    }

    /**
     * Añade una hoja con filas y columnas especificadas.
     * @param _nFilas número de filas de la hoja a añadir.
     * @param _nColumnas número de columnas de la hoja a añadir.
     */
    public void anadirHoja(int _nFilas, int _nColumnas) {
        documento_ref.anadeHoja(_nFilas, _nColumnas);
    }

    /**
     * Elimina la hoja con id _idH
     * @param _idH id de la hoja a eliminar.
     */
    public void eliminarHoja(int _idH) {
        documento_ref.eliminaHoja(_idH);
    }

    /**
     * Cambia el nombre de la hoja _idH por _nuevoNombre.
     * @param _nuevoNombre nuevo nombre para la hoja.
     */
    public boolean asignaNombreHoja(String _nuevoNombre) throws Exception {
        Hoja h = ControladorDominio.getControladorDominio().getControladorHoja().getHojaRef();
        if(h != null) {
            h.asignaNombre(_nuevoNombre);
            return true;
        } else {
            throw new Exception("La hoja seleccionada no existe.");
        }
    }

    /**
     * Obtiene el número de hojas del documento actual.
     * @return el número de hojas del documento aceual.
     */
    public int getNumHojas() {
        return documento_ref.getNumHojas();
    }

    public void renombrarDocumento(String _nuevoNombre) {
        documento_ref.setNombre(_nuevoNombre);
    }

    public void guardarDocumento(String _path) {
        ControladorDocumentoPersistencia cdp = ControladorPersistencia.getControladorPersistencia().getControladorDocumentoPersistenciaRef();
        try {
            cdp.almacenaDocumento(documento_ref, _path + "/" + this.documento_ref.getNombre() + ".prop");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //this.documento_ref = null; // Esto seria solo si queremos Guardar y Cerrar, no?
    }

    public void cargaDocumento(String _path) {
        ControladorDocumentoPersistencia cdp = ControladorPersistencia.getControladorPersistencia().getControladorDocumentoPersistenciaRef();

        try {
            this.documento_ref = cdp.cargaDocumento(_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargaCSV (String _path) {
        try {
            ControladorCSV ccsv = ControladorPersistencia.getControladorPersistencia().getControladorCSV();
            ccsv.asignaLector(_path);
            documento_ref.leeCSV(ccsv.cargaCSV());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
