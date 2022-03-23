public class BloqueSeleccionado {
    private static BloqueSeleccionado unico_bloque; // Única instancia

    private Celda celdaInicial;
    private Celda celdaFinal;

    private BloqueSeleccionado() {}
    public static BloqueSeleccionado getBloque() {
        if (unico_bloque == null) unico_bloque = new BloqueSeleccionado();
        return unico_bloque;
    }
    private void setCelda(Celda _inicial, Celda _final) {
        this.celdaInicial = _inicial;
        this.celdaFinal = _final;
    }

    private Celda getCeldaInicial() {
        return this.celdaInicial;
    }
    private Celda getCeldaFinal() {
        return this.celdaFinal;
    }
}
