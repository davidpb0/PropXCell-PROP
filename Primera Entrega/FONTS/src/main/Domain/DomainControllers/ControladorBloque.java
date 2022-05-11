package main.Domain.DomainControllers;
/*
 * ClassName DomainControllers.ControladorBloque
 *
 * Version info 0.1.0
 *
 * Author Iván Risueño Martín
 */

import main.Domain.DomainModel.*;

public class ControladorBloque {

    private static ControladorBloque instanceOfThisClass;
    private BloqueTemporalCopiado bloqueCopiado;

    /**
     * Constructora básica para el ControladorBloque
     */
    private ControladorBloque() {
        bloqueCopiado = null;
    }

    public static ControladorBloque getControldorBloque() {
        if (instanceOfThisClass == null) instanceOfThisClass = new ControladorBloque();
        return instanceOfThisClass;
    }
    /**
     * Da de alta un BloqueTemporalCopiado para copiarlo a otro lugar del documento
     */
    public void copiar() {
        bloqueCopiado = new BloqueTemporalCopiado();
        bloqueCopiado.setCortar(false);
    }

    /**
     * Da de alta un BloqueTemporalCopiado para cortarlo a otro lugar del documento
     */
    public void cortar() {
        bloqueCopiado = new BloqueTemporalCopiado();
        bloqueCopiado.setCortar(true);
    }

    /**
     * Pega el BloqueTemporalCopiado en un cierto lugar del documento
     * @param _h hoja destino
     * @param _filaInicio identificador de la fila destino
     * @param _columnaInicio identificador de la columna destino
     */
    public void pegar(Hoja _h, int _filaInicio, int _columnaInicio) {
        for (int f = 1; f <= bloqueCopiado.getTamanoFilas(); ++f) {
            for (int c = 1; c <= bloqueCopiado.getTamanoColumnas(); ++c) {
                Celda cell = bloqueCopiado.getCelda(f, c);
                _h.cambiarPosicionCelda(new Posicion(_filaInicio + f - 1, _columnaInicio + c - 1), cell);
            }
        }
        if (bloqueCopiado.getCortar()) {
            bloqueCopiado = null;
        }
    }

    /**
     * Define los parámetros del bloque seleccionado
     * @param _h hoja a la que pertenece el bloque
     * @param _filaInicial fila de la celda superior izquierda que define el bloque
     * @param _columnaInicial columna de la celda superior izquierda que define el bloque
     * @param _filaFinal fila de la celda inferior derecha que define el bloque
     * @param _columnaFinal columna de la celda inferior derecha que define el bloque
     */
    public void setBloqueSeleccionado(Hoja _h, int _filaInicial, int _columnaInicial, int _filaFinal, int _columnaFinal) {
        Celda inicialC = _h.getCelda(new Posicion(_filaInicial, _columnaInicial));
        Celda finalC = _h.getCelda(new Posicion(_filaFinal, _columnaFinal));
        BloqueSeleccionado.getBloque().setCelda(inicialC, finalC, _h);
    }
}
