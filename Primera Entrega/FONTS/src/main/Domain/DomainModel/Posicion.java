package main.Domain.DomainModel;
import java.io.Serializable;
import java.util.Objects;

/*
 * Posicion
 *
 * v1.0.0
 *
 * Daniel Gallardo Peña
 */

public class Posicion implements Serializable {
    private final int fila;
    private final int columna;

    /**
     * Constructora de posicion
     * @param _columna un entero que representa la columna, siempre es >=1
     * @param _fila un entero que representa la fila, siempre es >= 1
     */
    public Posicion(int _fila, int _columna) {
        this.fila = _fila;
        this.columna = _columna;
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

    /**
     * Reimplementación de la igualdad para los objetos de tipo Posicion.
     * Dos objetos Posicion son iguales si tienen el mismo numero de fila y de columna.
     * @param _o objeto a comparar con el objeto actual
     */
    @Override
    public boolean equals(Object _o){
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        Posicion that = (Posicion) _o;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    /**
     * Reimplementacion de la funcion que calcula el hashcode de los objetos de tipo Posicion.
     * @return entero con el hash code de el objeto actual 
     */
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
