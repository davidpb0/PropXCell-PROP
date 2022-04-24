package main.Domain.DomainControllers;
/*
 * Controlador Documento
 *
 * v0.0.2
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
     */
    public void crearDocumento() {
        cerrarDocumento();
        documento_ref = Documento.getDocumento();
        if (documento_ref.getNumHojas() == 0) documento_ref.inicializaDocumento("Documento sin título", 50, 50);
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
        documento_ref.añadeHojaDf();
    }

    /**
     * Añade una hoja con filas y columnas especificadas.
     * @param _nFilas número de filas de la hoja a añadir.
     * @param _nColumnas número de columnas de la hoja a añadir.
     */
    public void anadirHoja(int _nFilas, int _nColumnas) {
        documento_ref.añadeHoja(_nFilas, _nColumnas);
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
     * @param _idH id de la hoja en el documento.
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
