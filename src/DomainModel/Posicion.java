package DomainModel;

/*
 * Posicion
 *
 * v0.0.1
 *
 * Daniel Gallardo Peña
 */

public class Posicion {
    public int columna;
    public int fila;

    /**
     * Constructora de posición.
     * @param _pos una letra representando la columna y un número representando la fila
     */
    public Posicion(String _pos) {
        char col = _pos.toUpperCase().charAt(0);
        this.fila = Integer.parseInt(_pos.substring(1));

        this.columna = columnParse(col);
    }

    /**
     * Constructora de posicion (2 enteros);
     * @param columna un entero que representa la columna
     * @param fila un entero que representa la fila
     */
    public Posicion(int fila, int columna) {
        this.columna = columna;
        this.fila = fila;
    }

    /**
     * @return la posicion en formato String
     */
    public String getPosicion() {
        String pos = columnStringify(columna) + String.valueOf(fila);
        return pos;
    }

    /**
     * @param col el char de la columna que hay que convertir a un int
     * @return el int que pertenece a la columna dada
     */
    private int columnParse(char col) {
        // 'A' en int es 65
        int column = col - 64;
        return column;
    }

    /**
     * @param col el int de la columna que hay que convertir a un char
     * @return el char que pertenece a la columna dada
     */
    private char columnStringify(int col) {
        int c = col + 64;
        char column = (char)c;
        return column;
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
