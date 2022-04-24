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

    private BloqueTemporalCopiado bloqueCopiado;

    /**
     * Constructora básica para el ControladorBloque
     */
    public ControladorBloque() {
        bloqueCopiado = null;
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
     * @param _numHoja identificador de la hoja destino
     * @param _filaInicio identificador de la fila destino
     * @param _columnaInicio identificador de la columna destino
     */
    public void pegar(int _numHoja, int _filaInicio, int _columnaInicio) {
        Hoja h = Documento.getDocumento().getHoja(_numHoja);
        for (int f = 1; f <= bloqueCopiado.getTamanoFilas(); ++f) {
            for (int c = 1; c <= bloqueCopiado.getTamanoColumnas(); ++c) {
                Celda cell = bloqueCopiado.getCelda(f, c);
                Boolean asd = h.cambiarPosicionCelda(new Posicion(_filaInicio + f - 1, _columnaInicio + c - 1), cell);
                System.out.println(asd);
            }
        }
        if (bloqueCopiado.getCortar()) {
            bloqueCopiado = null;
        }
    }

    /**
     * Define los parámetros del bloque seleccionado
     * @param _idH id de la hoja a la que pertenece el bloque
     * @param _filaInicial fila de la celda superior izquierda que define el bloque
     * @param _columnaInicial columna de la celda superior izquierda que define el bloque
     * @param _filaFinal fila de la celda inferior derecha que define el bloque
     * @param _columnaFinal columna de la celda inferior derecha que define el bloque
     */
    public void setBloqueSeleccionado(int _idH, int _filaInicial, int _columnaInicial, int _filaFinal, int _columnaFinal) {
        Hoja h = Documento.getDocumento().getHoja(_idH);
        Celda inicialC = h.getCelda(new Posicion(_filaInicial, _columnaInicial));
        Celda finalC = h.getCelda(new Posicion(_filaFinal, _columnaFinal));
        BloqueSeleccionado.getBloque().setCelda(inicialC, finalC, h);
    }
}
