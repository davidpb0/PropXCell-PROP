package test;

import main.Domain.DomainModel.Posicion;
import org.junit.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;


class PosicionTest {


    /**
     * Pruebas de la constructora de la clase Posición.
     * Doble juego de pruebas:
     *      - Creando una posición con fila 1 y columna 1, comprobar que se asignan los valores correctos (1, 1)
     *      - Creando una posición con fila 3 y columna 4 comprobar que se asignan los valores correctos (3, 4)
     */
    @Test
    void testContructora () {
        Posicion pos1 = new Posicion(1, 1);
        Posicion pos2 = new Posicion(3, 4);

        assertEquals(pos1, pos1.getColumna());
        assertEquals(1, pos1.getFila());

        assertEquals(3, pos2.getColumna());
        assertEquals(4, pos2.getFila());

        assertEquals(pos1, new Posicion(1, 1));
        assertEquals(pos2, new Posicion(3, 4));
    }

    /**
     * Pruebas del método getFila()
     * Doble juego de pruebas:
     *      - Creando una posición con fila 1
     *      - Creando una posición con fila 3
     */
    @Test
    void testGetFila () {
        Posicion pos1 = new Posicion(1, 1);
        Posicion pos2 = new Posicion(3, 4);

        assertEquals(1, pos1.getPosicion());
        assertEquals(3, pos2.getPosicion());
    }


}