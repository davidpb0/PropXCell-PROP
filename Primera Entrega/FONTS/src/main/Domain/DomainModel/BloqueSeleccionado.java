package main.Domain.DomainModel;
/*
 * ClassName DomainModel.BloqueSeleccionado
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

public class BloqueSeleccionado {
    private static BloqueSeleccionado instanceOfThisClass; // Única instancia

    private Celda celdaInicial;
    private Celda celdaFinal;
    private Hoja hoja;

    private BloqueSeleccionado() {}

    public static BloqueSeleccionado getBloque() {
        if (instanceOfThisClass == null) instanceOfThisClass = new BloqueSeleccionado();
        return instanceOfThisClass;
    }

    /**
     * Asigna las celdas pertinentes a los extremos que definen el bloque.
     * @param _inicial la celda a utilizar como la superior izquierda.
     *        _final la celda a utilizar como la inferior derecha.
     */
    public void setCelda(Celda _inicial, Celda _final, Hoja _hoja) {
        this.celdaInicial = _inicial;
        this.celdaFinal = _final;
        this.hoja = _hoja;
    }

    /**
     * @return la celda superior izquierda del bloque seleccionado.
     */
    public Celda getCeldaInicial() {
        return this.celdaInicial;
    }

    /**
     * @return la celda inferior derecha del bloque seleccionado.
     */
    public Celda getCeldaFinal() {
        return this.celdaFinal;
    }

    /**
     * @return la hoja a la que pertenecen las celdas del bloque.
     */
    public Hoja getHoja() {
        return this.hoja;
    }

    /**
     * Resetea el valor de las celdas que forman el bloque.
     */
    public void clearCeldas() {
        this.celdaInicial = null;
        this.celdaFinal = null;
        this.hoja = null;
    }

    /**
     * Comprueba si existe un bloque seleccionado.
     * @return true si existe un bloque seleccionado actualmente, falso en caso contrario.
     */
    public Boolean existeBloque() {
        return !(this.celdaInicial == null && this.celdaFinal == null && this.hoja == null);
    }
}
