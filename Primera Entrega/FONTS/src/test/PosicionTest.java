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
        p1 = new Posicion(5, 1);
    }

    @Test
    void testContructora () {
        Posicion pos1 = new Posicion(1, 8);

        int f =  pos1.getColumna();
        int c = pos1.getFila();

        String test = f + " " + c;
        assertEquals("1 8", test);
    }
    
    @Test
    void testGetFila() {
        assertEquals(5, p1.getFila());
    }
    
    @Test
    void testGetColumna() {
        assertEquals(1, p1.getColumna());
    }

    @Test
    void testEquals() {

        //Propiedad reflexiva
        boolean reflex =  p1.equals(p1);


        p1 = new Posicion(2, 3);
        p2 = new Posicion(2, 3);

        //Propiedad simetrica
        boolean simetr = p1.equals(p2) && p2.equals(p1);


        Posicion p3 = new Posicion(2, 3);

        //Propiedad transitiva
        boolean trans = p1.equals(p2) && p2.equals(p3) && p1.equals(p3);

        //Consistencia
        boolean cons = p1.equals(p2) && p1.equals(p2) && p1.equals(p2);

        //Comparacion con null
        boolean notNull = (p1 != null) && (p1.equals(null) == false);

        assertTrue(reflex && simetr && trans && cons && notNull);
    }

    @Test
    void testHashCode() {

        p1 = new Posicion(15, 30);
        p2 = new Posicion(15, 30);

        //Si son iguales segun equals(), mismo hash code
        boolean sonIguales = p1.equals(p2);
        boolean mismoHashCode = p1.hashCode() == p2.hashCode();
        assertTrue(sonIguales && mismoHashCode);
    }
}