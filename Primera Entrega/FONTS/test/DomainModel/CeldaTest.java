package DomainModel;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Posicion;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CeldaTest {

    @Test
    public void contenidoVacio() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        String cont = cell.getContenido();
        assertEquals("", cont);

        Celda cell2 = new Celda(p2,"");
        assertEquals("", cell2.getContenido());
    }

    @Test
    public void valorVacio() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        String val = cell.getValor();
        assertEquals("", val);

        Celda cell2 = new Celda(p2, "");
        assertEquals("", cell2.getValor());
    }

    @Test
    public void contenidoString() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("Hola Mundo");
        String cont = cell.getContenido();
        assertEquals("Hola Mundo", cont);

        Celda cell2 = new Celda(p2,"hola");
        assertEquals("hola", cell2.getContenido());
    }

    @Test
    public void valorString() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("Hola Mundo");
        String val = cell.getValor();
        assertEquals("Hola Mundo", val);

        Celda cell2 = new Celda(p2,"hola");
        assertEquals("hola", cell2.getValor());
    }

    @Test
    public void contenidoFunc() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("=SUM(1, 2)");
        String cont = cell.getContenido();
        assertEquals("=SUM(1, 2)", cont);

        Celda cell2 = new Celda(p2,"=SUM(1, 2)");
        assertEquals("=SUM(1, 2)", cell2.getContenido());
    }

    @Test
    public void valorFunc() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("=SUM(1, 2)");
        String val = cell.getValor();
        /* !!!
         * En este caso, como de momento no tenemos implementadas las funciones,
         * no espero que salga el resultado de la función, sinó que espero que
         * la clase detecte que es una función y no un string normal.
         */
        assertEquals("#FUNC", val);

        Celda cell2 = new Celda(p2,"=SUM(1, 2)");
        assertEquals("#FUNC", cell2.getValor());
    }

    @Test
    public void posicionTest() {
        Posicion p1 = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 2);
        Posicion p3 = new Posicion(2, 1);

        Celda c1 = new Celda(p1);
        Celda c2 = new Celda(p2);

        assertEquals(p1, c1.getPosicion());
        assertEquals(p2, c2.getPosicion());

        c1.setPosicion(p3);

        assertEquals(p3, c1.getPosicion());
    }

    @Test
    public void referenciantesVacia () {
        Posicion p = new Posicion(1,1);
        Celda cell = new Celda(p);
        assertEquals(new ArrayList<Celda>(), cell.getReferenciantes());
    }

    @Test
    public void addReferencianteF () {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        c1.addReferenciante(c2);

        assertEquals("A", c2.getValor());
    }

    @Test
    public void borrarReferencianteF () {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        assertEquals("A", c2.getValor());

        c1.borrarReferenciante(c2);
        assertEquals(new ArrayList<Celda>(), c1.getReferenciantes());
    }

    @Test
    public void cambioValorReferenciaF() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        assertEquals("A", c2.getValor());

        c1.setContenido("NEW CONTENT");
        assertEquals("NEW CONTENT", c2.getValor());
    }

    @Test
    public void constructoraCopia(){
        Posicion p = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "ABC");
        Celda cRef = new Celda(p2, "#REF");
        c1.addReferenciante(cRef);

        Celda c2 = new Celda(c1);

        assertEquals(p, c2.getPosicion());
        assertEquals("ABC", c2.getContenido());
        assertEquals("ABC", c2.getValor());
        assertEquals(c1.getReferenciantes(), c2.getReferenciantes());

    }

}