package DomainModel;

/*
 * Posicion
 *
 * v0.0.1
 *
 * Daniel Gallardo Peña
 */

public class Posicion {
    private int fila;
    private int columna;

    /**
     * Constructora de posicion
     * @param columna un entero que representa la columna
     * @param fila un entero que representa la fila
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Devuelve la fila en la que está situada
     * @return un entero que representa el número de fila
     */
    public int getFila() {
        return fila;
    }

    /**
     * Devuelve la columna en la que está situada
     * @return un entero que representa el número de columna
     */
    public int getColumna() {
        return columna;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Posicion that = (Posicion) o;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    public int hashCode(){
        int result = fila;
        result = 31 * result + columna;
        return result;
    }

}
