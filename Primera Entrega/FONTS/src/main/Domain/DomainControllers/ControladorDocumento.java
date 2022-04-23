package main.Domain.DomainControllers;
/*
 * Traductor
 *
 * 0.0.1
 *
 * Joaquim Torra Garcia
 */

import main.Domain.DomainModel.Documento;

public class ControladorDocumento {
    private Documento documento_ref;

    /**
     * Creadora por defecto, setea el valor de documento_ref con el documento abierto.
     */
    public ControladorDocumento() {
        documento_ref = Documento.getDocumento();
        if (documento_ref.getNumHojas() == 0) documento_ref.inicializaDocumento("Documento sin título", 50, 50);
    }

    /**
     * Cierra el documento actual.
     */
    public void cerrarDocumento() {
        documento_ref = null;
        // Literalmente no sé qué más poner xd
    }

    /**
     * Crea un documento nuevo.
     */
    public void crearDocumento() {
        cerrarDocumento();
        documento_ref = Documento.getDocumento();
        if (documento_ref.getNumHojas() == 0) documento_ref.inicializaDocumento("Documento sin título", 50, 50);
        // No sé cómo hacer que presentación abra un documento nuevo xd
    }

    /**
     * Elimina el documento abierto actualmente.
     */
    public void eliminarDocumento() {
        documento_ref.eliminaDocumento();
        // No creo que esto sea suficiente pero weno
    }

    /**
     * Añade una hoja vacy le pone el id que toca a continuación.
     */
    public void anadirHoja() {
        documento_ref.añadeHojaDf();
    }

    /**
     * Añade una hoja y le pone el id que toca a continuación.
     */
    public void anadirHoja(int _nFilas, int _nColumnas) {
        documento_ref.añadeHoja(_nFilas, _nColumnas);
    }

    /**
     * Elimina la hoja con id _idH
     * @param _idH identificador de la hoja a eliminar.
     */
    public void eliminarHoja(int _idH) {
        documento_ref.eliminaHoja(_idH);
    }

    /**
     * Cambia el nombre de la hoja _idH por _nuevoNombre.
     * @param _idH identificador de la hoja en el documento.
     * @param _nuevoNombre nuevo nombre para la hoja.
     */
    public void asignaNombreHoja(int _idH, String _nuevoNombre) {
        documento_ref.getHoja(_idH).asignaNombre(_nuevoNombre);
    }

    /**
     * Obtiene el número de hojas del documento actual.
     * @return el número de hojas del documento aceual.
     */
    public int getNumHojas() {
        return documento_ref.getNumHojas();
    }
}