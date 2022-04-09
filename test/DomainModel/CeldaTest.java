package DomainModel;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CeldaTest {

    @Test
    public void contenidoVacio() {
        Celda cell = new Celda();
        String cont = cell.getContenido();
        assertEquals("", cont);

        Celda cell2 = new Celda("");
        assertEquals("", cell2.getContenido());
    }

    @Test
    public void valorVacio() {
        Celda cell = new Celda();
        String val = cell.getValor();
        assertEquals("", val);

        Celda cell2 = new Celda("");
        assertEquals("", cell2.getValor());
    }

    @Test
    public void contenidoString() {
        Celda cell = new Celda();
        cell.setContenido("Hola Mundo");
        String cont = cell.getContenido();
        assertEquals("Hola Mundo", cont);

        Celda cell2 = new Celda("hola");
        assertEquals("hola", cell2.getContenido());
    }

    @Test
    public void valorString() {
        Celda cell = new Celda();
        cell.setContenido("Hola Mundo");
        String val = cell.getValor();
        assertEquals("Hola Mundo", val);

        Celda cell2 = new Celda("hola");
        assertEquals("hola", cell2.getValor());
    }

    @Test
    public void contenidoFunc() {
        Celda cell = new Celda();
        cell.setContenido("=SUM(1, 2)");
        String cont = cell.getContenido();
        assertEquals("=SUM(1, 2)", cont);

        Celda cell2 = new Celda("=SUM(1, 2)");
        assertEquals("=SUM(1, 2)", cell2.getContenido());
    }

    @Test
    public void valorFunc() {
        Celda cell = new Celda();
        cell.setContenido("=SUM(1, 2)");
        String val = cell.getValor();
        /* !!!
         * En este caso, como de momento no tenemos implementadas las funciones,
         * no espero que salga el resultado de la función, sinó que espero que
         * la clase detecte que es una función y no un string normal.
         */
        assertEquals("#FUNC", val);

        Celda cell2 = new Celda("=SUM(1, 2)");
        assertEquals("#FUNC", cell2.getValor());
    }

    @Test
    public void referenciantesVacia () {
        Celda cell = new Celda();
        assertEquals(new ArrayList<Celda>(), cell.getReferenciantes());
    }

    @Test
    public void addReferencianteF () {
        Celda c1 = new Celda("A");
        Celda c2 = new Celda("B");

        c1.addReferenciante(c2);

        assertEquals("A", c2.getValor());
    }

    @Test
    public void borrarReferencianteF () {
        Celda c1 = new Celda("A");
        Celda c2 = new Celda("B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        assertEquals("A", c2.getValor());

        c1.borrarReferenciante(c2);
        assertEquals(new ArrayList<Celda>(), c1.getReferenciantes());
    }

    @Test
    public void cambioValorReferenciaF() {
        Celda c1 = new Celda("A");
        Celda c2 = new Celda("B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        assertEquals("A", c2.getValor());

        c1.setContenido("NEW CONTENT");
        assertEquals("NEW CONTENT", c2.getValor());
    }

}