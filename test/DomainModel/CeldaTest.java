package DomainModel;

import static org.junit.Assert.*;
import org.junit.Test;

class CeldaTest {

    @Test
    void contenidoVacio() {
        Celda cell = new Celda();
        String cont = cell.getContenido();
        assertEquals("", cont);
    }

    @Test
    void valorVacio() {
        Celda cell = new Celda();
        String val = cell.getValor();
        assertEquals("", val);
    }

    @Test
    void contenidoString() {
        Celda cell = new Celda();
        cell.setContenido("Hola Mundo");
        String cont = cell.getContenido();
        assertEquals("Hola Mundo", cont);
    }

    @Test
    void valorString() {
        Celda cell = new Celda();
        cell.setContenido("Hola Mundo");
        String val = cell.getValor();
        assertEquals("Hola Mundo", val);
    }

    @Test
    void contenidoFunc() {
        Celda cell = new Celda();
        cell.setContenido("=SUM(1, 2)");
        String cont = cell.getContenido();
        assertEquals("=SUM(1, 2)", cont);
    }

    @Test
    void valorFunc() {
        Celda cell = new Celda();
        cell.setContenido("=SUM(1, 2)");
        String val = cell.getValor();
        /* !!!
         * En este caso, como de momento no tenemos implementadas las funciones,
         * no espero que salga el resultado de la función, sinó que espero que
         * la clase detecte que es una función y no un string normal.
         */
        assertEquals("#FUNC", val);
    }

}