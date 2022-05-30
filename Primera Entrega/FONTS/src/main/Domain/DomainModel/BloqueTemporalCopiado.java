package main.Domain.DomainModel;
/*
 * ClassName DomainModel.BloqueTemporalCopiado
 *
 * Version info 1.0.0
 *
 * Iván Risueño Martín
 */

/**
 * Clase BloqueTemporalCopiado, utilizada para almacenar las celdas copiadas o cortadas por el usuario.
 * @author Iván Risueño Martín
 */

public class BloqueTemporalCopiado {

    private Celda[][] bloqueCopiado;
    private Boolean cortar;

    /**
     * Constructora
     * Crea una copia del bloque de celdas seleccionado
     * Se garantiza que el bloque de celdas indicado en BloqueSeleccionado es válido
     */
    public BloqueTemporalCopiado() {
        Celda celdaInicial = BloqueSeleccionado.getBloque().getCeldaInicial();
        Celda celdaFinal = BloqueSeleccionado.getBloque().getCeldaFinal();
        Hoja hoja = BloqueSeleccionado.getBloque().getHoja();

        int filaIni = celdaInicial.getPosicion().getFila();
        int filaFin = celdaFinal.getPosicion().getFila();
        int columIni = celdaInicial.getPosicion().getColumna();
        int columFin = celdaFinal.getPosicion().getColumna();

        bloqueCopiado = new Celda[filaFin - filaIni + 1][columFin - columIni + 1];

        for (int iterFila = filaIni; iterFila <= filaFin; ++iterFila) {
            for (int iterColum = columIni; iterColum <= columFin; ++iterColum) {
                bloqueCopiado[iterFila - filaIni][iterColum - columIni] = new Celda(hoja.getCelda(new Posicion(iterFila, iterColum)));
            }     
        }
    }

    /**
     * Devuelve el numero de filas del bloque
     * @return entero que representa el tamaño de las filas
     */
    public int getTamanoFilas() {
        return bloqueCopiado.length;
    }

    /**
     * Devuelve el numero de columnas del bloque
     * @return entero que representa el tamaño de las columnas
     */
    public int getTamanoColumnas() {
        return bloqueCopiado[0].length;
    }
    
    /**
     * Devuelve la celda de la posición indicada
     * @param _f indica la fila relativa dentro del bloque temporal copiado (1 menor o igual a _f , menor o igual a numero de filas)
     * @param _c indica la columna relativa dentro del bloque temporal copiado (1 menor o igual a _c, menor o igual a numero de columnas)
     * @return la celda con Posicion(f, c)
     */
    public Celda getCelda(int _f, int _c) {
        return bloqueCopiado[_f - 1][_c - 1];
    }

    /**
     * Setea el atributo cortar para indicar que el bloque se esta copiando con la funcionalidad de Cortar
     * @param _cortar cierto si el bloque se ha copiado con la funcionalidad de Cortar, sino falso
     */
    public void setCortar(Boolean _cortar) { this.cortar = _cortar;}

    /**
     * Indica si el bloque se ha copiado con la funcionalidad de Cortar
     * @return si el bloque se ha copiado mediante la funcionalidad de Cortar devuelve cierto, sino falso
     */
    public Boolean getCortar() { return this.cortar;}
}
