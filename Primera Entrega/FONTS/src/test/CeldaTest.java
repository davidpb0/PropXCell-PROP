package test;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Posicion;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CeldaTest {

    @Test
    public void contenidoVacio() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        String cont = cell.getContenido();
        Assert.assertEquals("", cont);

        Celda cell2 = new Celda(p2,"");
        Assert.assertEquals("", cell2.getContenido());
    }

    @Test
    public void valorVacio() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        String val = cell.getValor();
        Assert.assertEquals("", val);

        Celda cell2 = new Celda(p2, "");
        Assert.assertEquals("", cell2.getValor());
    }

    @Test
    public void contenidoString() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("Hola Mundo");
        String cont = cell.getContenido();
        Assert.assertEquals("Hola Mundo", cont);

        Celda cell2 = new Celda(p2,"hola");
        Assert.assertEquals("hola", cell2.getContenido());
    }

    @Test
    public void valorString() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("Hola Mundo");
        String val = cell.getValor();
        Assert.assertEquals("Hola Mundo", val);

        Celda cell2 = new Celda(p2,"hola");
        Assert.assertEquals("hola", cell2.getValor());
    }

    @Test
    public void contenidoFunc() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda cell = new Celda(p);
        cell.setContenido("=SUM(1, 2)");
        String cont = cell.getContenido();
        Assert.assertEquals("=SUM(1, 2)", cont);

        Celda cell2 = new Celda(p2,"=SUM(1, 2)");
        Assert.assertEquals("=SUM(1, 2)", cell2.getContenido());
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
        Assert.assertEquals("#FUNC", val);

        Celda cell2 = new Celda(p2,"=SUM(1, 2)");
        Assert.assertEquals("#FUNC", cell2.getValor());
    }

    @Test
    public void posicionTest() {
        Posicion p1 = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 2);
        Posicion p3 = new Posicion(2, 1);

        Celda c1 = new Celda(p1);
        Celda c2 = new Celda(p2);

        Assert.assertEquals(p1, c1.getPosicion());
        Assert.assertEquals(p2, c2.getPosicion());

        c1.setPosicion(p3);

        Assert.assertEquals(p3, c1.getPosicion());
    }

    @Test
    public void referenciantesVacia () {
        Posicion p = new Posicion(1,1);
        Celda cell = new Celda(p);
        Assert.assertEquals(new ArrayList<Celda>(), cell.getReferenciantes());
    }

    @Test
    public void setReferencianteF () {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        ArrayList<Celda> celdasReferenciantes = new ArrayList<Celda>();
        celdasReferenciantes.add(c2);

        c1.setReferenciantes(celdasReferenciantes);

        Assert.assertEquals("A", c2.getValor());

    }

    @Test
    public void addReferencianteF () {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        c1.addReferenciante(c2);

        Assert.assertEquals("A", c2.getValor());
    }

    @Test
    public void borrarReferencianteF () {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        Assert.assertEquals("A", c2.getValor());

        c1.borrarReferenciante(c2);
        Assert.assertEquals(new ArrayList<Celda>(), c1.getReferenciantes());
    }

    @Test
    public void cambioValorReferenciaF() {
        Posicion p = new Posicion(1,1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "A");
        Celda c2 = new Celda(p2, "B");

        //C2 esta referenciando a C1 --> C2 tiene el valor de C1
        c1.addReferenciante(c2);
        Assert.assertEquals("A", c2.getValor());

        c1.setContenido("NEW CONTENT");
        Assert.assertEquals("NEW CONTENT", c2.getValor());
    }

    @Test
    public void constructoraCopia(){
        Posicion p = new Posicion(1, 1);
        Posicion p2 = new Posicion(1, 2);
        Celda c1 = new Celda(p, "ABC");
        Celda cRef = new Celda(p2, "#REF");
        c1.addReferenciante(cRef);

        Celda c2 = new Celda(c1);

        Assert.assertEquals(p, c2.getPosicion());
        Assert.assertEquals("ABC", c2.getContenido());
        Assert.assertEquals("ABC", c2.getValor());
        Assert.assertEquals(c1.getReferenciantes(), c2.getReferenciantes());

    }

}