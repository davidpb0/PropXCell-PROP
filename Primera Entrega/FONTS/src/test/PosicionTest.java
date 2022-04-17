package test;

import main.Domain.DomainModel.Posicion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


class PosicionTest {


    /**
     * Pruebas de la constructora de la clase Posición.
     * Doble juego de pruebas:
     *      - Creando una posición A1, comprobar que se asignan los valores correctos (1, 1)
     *      - Creando una posición C4, comprobar que se asignan los valores correctos (3, 4)
     */
    @Test
    void testContructora () {
        Posicion pos1 = new Posicion("A1");
        Posicion pos2 = new Posicion("C4");

        Assert.assertEquals(1, pos1.columna);
        Assert.assertEquals(1, pos1.fila);

        Assert.assertEquals(3, pos2.columna);
        Assert.assertEquals(4, pos2.fila);
    }

    /**
     * Pruebas del método getPosición()
     * Doble juego de pruebas:
     *      - Creando una posición A1, comprobar que se retorna la posición correcta ("A1")
     *      - Creando una posición C4, comprobar que se retorna la posición correcta ("C4")
     */
    @Test
    void testGet () {
        Posicion pos1 = new Posicion("A1");
        Posicion pos2 = new Posicion("C4");

        assertEquals("A1", pos1.getPosicion());
        assertEquals("C4", pos2.getPosicion());
    }


}