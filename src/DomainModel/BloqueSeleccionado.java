package DomainModel;
/*
 * ClassName DomainModel.BloqueSeleccionado
 *
 * Version info 1.0
 *
 * Author Iván Risueño Martín
 */

public class BloqueSeleccionado {
    private static BloqueSeleccionado unico_bloque; // Única instancia

    private Celda celdaInicial;
    private Celda celdaFinal;
    private Hoja hoja;

    private BloqueSeleccionado() {}

    public static BloqueSeleccionado getBloque() {
        if (unico_bloque == null) unico_bloque = new BloqueSeleccionado();
        return unico_bloque;
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

    public Hoja getHoja() {
        return this.hoja;
    }
}
