package DomainModel;
/*
 * ClassName DomainModel.BloqueTemporalCopiado
 *
 * Version info 0.1
 *
 * Author Daniel Gallardo Peña
 */

public class BloqueTemporalCopiado {

    private Celda[][] bloqueCopiado;
    private Boolean cortar;


    /**
     * Constructora
     * Crea una copia del bloque de celdas seleccionado
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
                bloqueCopiado[iterFila - filaIni][iterColum - columIni] = new Celda(hoja.getCelda(iterFila, iterColum));
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
     * @param _f indica la fila relativa dentro del bloque temporal copiado (1 <= _f <= numFilas)
     * @param _c indica la columna relativa dentro del bloque temporal copiado (1 <= _c <= numCols)
     * @return la celda con Posicion(f, c)
     */
    public Celda getCelda(int _f, int _c) {
        return bloqueCopiado[_f - 1][_c - 1];
    }

    /**
     * Setea el atributo cortar
     * @param _cortar nuevo valor del atributo
     */
    public void setCortar(Boolean _cortar) { this.cortar = _cortar;}

    /**
     * @return el atributo cortar del bloque
     */
    public Boolean getCortar() { return this.cortar;}
}
