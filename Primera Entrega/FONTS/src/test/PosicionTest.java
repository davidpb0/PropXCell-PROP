package test;

import main.Domain.DomainModel.Posicion;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

class PosicionTest {

    Posicion p1;
    Posicion p2;
    Posicion p3;


    @Before
    void inicializaPosiciones() {
        p1 = new Posicion(10, 15);
        p2 = new Posicion(4, 1);
    }

    @Test
    void testContructora () {
        Posicion pos1 = new Posicion(1, 1);
        Posicion pos2 = new Posicion(3, 4);

        assertEquals(1, pos1.getColumna());
        assertEquals(1, pos1.getFila());

        assertEquals(3, pos2.getColumna());
        assertEquals(4, pos2.getFila());
    }
    
    @Test
    void testGetFila() {
        assertEquals(10, p1.getFila());
        assertEquals(4, p2.getFila());
    }
    
    @Test
    void testGetColumna() {
        assertEquals(15, p1.getColumna());
        assertEquals(1, p2.getColumna());
    }

    @Test
    void testEquals() {

        //Propiedad reflexiva
        assertTrue(p1.equals(p1));
        assertTrue(p2.equals(p2));


        p1 = new Posicion(2, 3);
        p2 = new Posicion(2, 3);

        //Propiedad simetrica
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p1));

        Posicion p3 = new Posicion(2, 3);

        //Propiedad transitiva
        assertTrue(p1.equals(p2));
        assertTrue(p2.equals(p3));
        assertTrue(p1.equals(p3));

        //Consistencia
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p2));

        //Comparacion con null
        assertNotNull(p1);
        assertNotNull(p2);
        assertFalse(p1.equals(null));
        assertFalse(p2.equals(null));
    }

    @Test
    void testHashCode() {

        p1 = new Posicion(15, 30);
        p2 = new Posicion(15, 30);

        //Si son iguales segun equals(), mismo hash code
        assertEquals(p1, p2);

        assertNotEquals(p1, null);
        assertNotEquals(p2, null);
    }
}