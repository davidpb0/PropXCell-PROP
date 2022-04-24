
import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Posicion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

public class CeldaTest {

    @InjectMocks
    private Celda c1;

    @Mock
    private Celda c2;

    @Mock
    private Posicion p1;

    @Mock
    private Posicion p2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        p1 = new StubPosicion(1, 1);
        p2 = new StubPosicion(1, 2);
        c1 = new Celda(p1);
        c2 = new Celda(p2);
    }

    public void cleanRefs() {
        LinkedList<Celda> listaVacia = new LinkedList<>();
        c1.setReferenciantes(listaVacia);
        c2.setReferenciantes(listaVacia);
    }

    @After
    public void tearDown(){

    }

    /**
     * Objeto de la prueba: Testear la constructora de una celda vacía
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia que la posición se asigna bien a la celda al ser creada con la constructora
     * - Operativa: se definen una posicion con fila y columna y una celda con esa posición
     *
     */
    @Test
    public void ConstructoraVacia () {
        Celda c = new Celda(p1);
        Assert.assertEquals(p1, c.getPosicion());
    }

    /**
     * Objeto de la prueba: Testear la constructora de una celda con contenido
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia que el contenido se asigna correctamente al crear una celda con contenido
     * - Operativa: se definen una posicion con fila y columna, un String con el contenido y una celda con esa posición y ese contenido
     *
     */
    @Test
    public void ConstructoraContenido () {
        String cont = "contenido";
        Celda c = new Celda(p1, cont);
        Assert.assertEquals(cont, c.getContenido());
    }

    /**
     * Objeto de la prueba: Testear la constructora de una celda copia de otra
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia que una celda creada a partir de otra sea igual, sin copiar las referencias
     * - Operativa: se define una celda con contenido, valor y posición. Se define una segunda celda copia de la primera
     *
     */
    @Test
    public void ConstructoraCopia () {
        String cont = "contenido";
        String val = "valor";
        c1 = new Celda(p1, cont);
        c1.setValor(val);

        Celda copia = new Celda(c1);
        String attrC1 = c1.getPosicion() + c1.getContenido() + c1.getValor();
        String attrCopia = copia.getPosicion() + copia.getContenido() + copia.getValor();
        Assert.assertEquals(attrC1, attrCopia);
    }

    /**
     * Objeto de la prueba: Testear la función SetValor
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta de el valor pasado por parámetro
     * - Operativa: se define una celda vacía, un String con el valor. Se añade el valor a la celda
     *
     */
    @Test
    public void SetValorString () {
        String val = "valor";
        c1.setValor(val);

        Assert.assertEquals(val, c1.getValor());
    }

    /**
     * Objeto de la prueba: Testear la función SetContenido
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta de el contenido pasado por parámetro
     * - Operativa: se define una celda vacía, un String con el contenido. Se añade el contenido a la celda
     *
     */
    @Test
    public void SetContenidoString () {
        String cont = "contenido";
        c1.setContenido(cont);

        Assert.assertEquals(cont, c1.getContenido());
    }

    /**
     * Objeto de la prueba: Testear la función SetPosicion
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta de la posición pasada por parámetro
     * - Operativa: se define una celda vacía, y una posición adicional. Se asigna la posición adicional a la celda
     *
     */
    @Test
    public void SetPosicion () {
        c1.setPosicion(p2);

        Assert.assertEquals(p2, c1.getPosicion());
    }

    /**
     * Objeto de la prueba: Testear la función AddReferenciante
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se añada correctamente una celda referenciante
     * - Operativa: se definen 2 celdas distintas. La segunda celda referencia a la otra // Añadimos la segunda celda
     *              como referenciante de la primera
     *
     */
    @Test
    public void AddReferenciante () {
        cleanRefs();
        c1.addReferenciante(c2);

        LinkedList<Celda> cs = new LinkedList<>();
        cs.add(c2);
        Assert.assertEquals(cs, c1.getReferenciantes());
    }


    /**
     * Objeto de la prueba: Testear la funcionalidad de Referenciar a una celda
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se asigne el valor de la celda referenciada a la celda referenciante
     * - Operativa: se definen 2 celdas distintas y un String valor. Se asigna el valor a la primera celda y
     *              se añade la 2a celda como refernciante de la primera.
     *
     */
    @Test
    public void ValorEnReferencia () {
        cleanRefs();
        String val = "valor_c1";
        c1.setValor(val);
        // C2 referencia a C1, el valor de c2 deberia ser el valor de c1
        c1.addReferenciante(c2);

        Assert.assertEquals(val, c2.getValor());
    }

    /**
     * Objeto de la prueba: Testear la funcionalidad de cambiar el valor al Referenciar a una celda
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se asigne, al cambiar el valor de la celda referenciada, se asigne a la
     *                       celda referenciante
     * - Operativa: se definen 2 celdas distintas y un dos Strings val1 y val2. Se asigna val1 a la primera celda,
     *              se añade la 2a celda como referenciante de la primera y se cambia el valor de la 1a por val2.
     *
     */
    @Test
    public void CambioValorEnReferencia () {
        cleanRefs();
        String val1 = "valor1_c1";
        String val2 = "valor2_c1";

        c1.setValor(val1);
        c1.addReferenciante(c2);
        // C2 referencia C1 : el valor de c2 es val1 (demostrado en test anterior)
        // Al cambiar el valor de C1, el valor de C2 cambia también.
        c1.setValor(val2);

        Assert.assertEquals(val2, c2.getValor());
    }

    /**
     * Objeto de la prueba: Testear los límites de la funcionalidad de Referenciar a una celda
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta del un valor al referenciar una celda que, a su vez,
     *                       referencia a otra.
     * - Operativa: se definen 3 celdas distintas y un String val. Se asigna el valor val a la primera celda. La
     *              segunda celda referencia a la primera, y la tercera celda referencia a la segunda.
     *
     */
    @Test
    public void DobleReferencia () {
        cleanRefs();
        Celda c3 = new Celda(new StubPosicion(1,3));

        String val = "valor_c1";
        c1.setValor(val);
        // C2 referencia a C1 || C3 referencia a C2 --> C3 deberia tener el mismo valor que C1
        c1.addReferenciante(c2);
        c2.addReferenciante(c3);

        Assert.assertEquals(val, c3.getValor());
    }

    /**
     * Objeto de la prueba: Testear los límites de la funcionalidad de Referenciar a una celda
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta del un valor al referenciar una celda que, a su vez,
     *                       referencia a otra, en un orden distinto.
     * - Operativa: se definen 3 celdas distintas y un String val. Se asigna el valor val a la primera celda. La
     *              tercera celda referencia a la segunda, y la segunda celda referencia a la primera.
     *
     */
    @Test
    public void DobleReferenciaDesordenada () {
        cleanRefs();
        Celda c3 = new Celda(new Posicion(1,3));

        String val = "valor_c1";
        c1.setValor(val);
        // C3 referencia a C2 || C2 referencia a C1 --> C3 deberia tener el mismo valor que C1
        c2.addReferenciante(c3);
        c1.addReferenciante(c2);

        Assert.assertEquals(val, c3.getValor());
    }

    /**
     * Objeto de la prueba: Testear los límites de la funcionalidad de Referenciar a una celda
     * - Stubs: StubPosicion
     * - FDs: ninguno
     * - Valores estudiados: se estudia la asignación correcta del un valor al referenciar una celda que, a su vez,
     *                       referencia a otra, en un orden distinto.
     * - Operativa: se definen 3 celdas distintas y 2 Strings val y val2. Se asigna el valor val a la primera celda. La
     *              tercera celda referencia a la segunda, y la segunda celda referencia a la primera. El valor de la
     *              primera celda cambia a val2.
     *
     */
    @Test
    public void CambioValorDobleRefDesordenada () {
        cleanRefs();
        Celda c3 = new Celda(new Posicion(1,3));

        String val = "valor_c1";
        String val2 = "VALOR2_C1";
        c1.setValor(val);
        // C3 referencia a C2 || C2 referencia a C1 --> C3 deberia tener el mismo valor que C1
        c2.addReferenciante(c3);
        c1.addReferenciante(c2);

        c1.setValor(val2);

        Assert.assertEquals(val2, c3.getValor());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en AutoReferencia
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia el comportamiento ante una Auto Referencia, en concreto, que la
     *                       referencia que genera el error no se añada
     * - Operativa: se defie 1 celda. Esa celda se referencia a sí misma.
     *
     */
    @Test
    public void AutoReferencia () {
        cleanRefs();
        c1.addReferenciante(c1);
        Assert.assertEquals(new LinkedList<Celda>(), c1.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia Circular Directa
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia el comportamiento ante una Referencia Circular Directa, en concreto, que la
     *                       referencia que genera el error no se añada
     * - Operativa: se defien 2 celdas distintas. La segunda celda referncia a la primera, y la primera
     *              referencia a la segunda
     *
     */
    @Test
    public void ReferenciaCircular () {
        cleanRefs();
        c1.addReferenciante(c2);
        c2.addReferenciante(c1);

        // Al estar C2 referenciando ya a C1, C1 no podrá referenciar a C2, con lo que la lista de referenciantes
        // de C2 será vacia.

        Assert.assertEquals(new LinkedList<Celda>(), c2.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia Circular Múltiple
     * - Stubs: StubPosición
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se borre correctamente una celda de la lista de referenciantes
     * - Operativa: se definen 4 celdas distintas. La segunda referencia a la primera. La tercera a la segunda.
     *              La cuarta a la tercera. Y la primera a la cuarta.
     *
     */
    @Test
    public void ReferenciaCircularMultiple () {
        cleanRefs();
        Celda c3 = new Celda(new StubPosicion(1, 3));
        Celda c4 = new Celda(new StubPosicion(1, 4));

        c1.addReferenciante(c2);
        c2.addReferenciante(c3);
        c3.addReferenciante(c4);
        c4.addReferenciante(c1);

        System.out.println("Refs C1:" + c1.getReferenciantes());
        System.out.println("Refs C2:" + c2.getReferenciantes());
        System.out.println("Refs C3:" + c3.getReferenciantes());
        System.out.println("Refs C4:" + c4.getReferenciantes());

        Assert.assertEquals(new LinkedList<Celda>(), c4.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia Circular Múltiple
     * - Stubs: StubPosición
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se borre correctamente una celda de la lista de referenciantes
     * - Operativa: se definen 4 celdas distintas. La primera referencia a la cuarta. La segunda a la primera.
     *              La tercera a la segunda. Y la cuarta a la tercera.
     *
     */
    @Test
    public void ReferenciaCircularMultipleDesordenada () {
        cleanRefs();
        Celda c3 = new Celda(new StubPosicion(1, 3));
        Celda c4 = new Celda(new StubPosicion(1, 4));

        // C3 deberia quedar sin ningun referenciante, ya que es la última referencia en ser creada
        c4.addReferenciante(c1);
        c1.addReferenciante(c2);
        c2.addReferenciante(c3);
        c3.addReferenciante(c4);

        System.out.println("Refs C1:" + c1.getReferenciantes());
        System.out.println("Refs C2:" + c2.getReferenciantes());
        System.out.println("Refs C3:" + c3.getReferenciantes());
        System.out.println("Refs C4:" + c4.getReferenciantes());

        Assert.assertEquals(new LinkedList<Celda>(), c3.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear la función BorrarReferenciante
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se borre correctamente una celda de la lista de referenciantes
     * - Operativa: se definen 2 celdas distintas. La segunda referencia a la primera. Se borra la segunda celda de
     *              la lista de referencias de la primera.
     *
     */
    @Test
    public void BorrarReferenciante () {
        cleanRefs();
        c1.addReferenciante(c2);
        c1.borrarReferenciante(c2);

        Assert.assertEquals(new LinkedList<Celda>(), c1.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear la función SetReferenciantes
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se añada una lista de referenciantes correctamente.
     * - Operativa: se definen dos celdas y una lista de celdas. Se añade la segunda celda a la lista. Se asigna la
     *              lista a la primera celda.
     *
     */
    @Test
    public void SetReferenciantes () {
        cleanRefs();
        LinkedList<Celda> lista = new LinkedList<>();
        lista.add(c2);
        c1.setReferenciantes(lista);

        Assert.assertEquals(lista, c1.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en AutoReferencia
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia el comportamiento de la funcion Set ante una Auto Referencia, en concreto,
     *                       que la referencia que genera el error no se añada
     * - Operativa: se defie 1 celda. Se añade esa celda a una lista de celdas. Se asigna la lista a la misma celda.
     *
     */
    @Test
    public void SetAutoReferencia () {
        cleanRefs();
        LinkedList<Celda> lista = new LinkedList<>();
        lista.add(c1);
        c1.setReferenciantes(lista);
        Assert.assertEquals(new LinkedList<Celda>(), c1.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia circular
     * - Stubs: ninguno
     * - FDs: ninguno
     * - Valores estudiados: se estudia el comportamiento ante una Referencia Circular Directa de la función Set, en
     *                       concreto, que la referencia que genera el error no se añada
     * - Operativa: se defien 2 celdas distintas. La segunda celda referencia a la primera. Se añade la primera a una
     *              lista de celdas. Se asigna la lista a la segunda celda.
     *
     */
    @Test
    public void SetReferenciaCircular () {
        cleanRefs();
        c1.addReferenciante(c2);
        LinkedList<Celda> lista = new LinkedList<>();
        lista.add(c1);
        c2.setReferenciantes(lista);

        // Al estar C2 referenciando ya a C1, C1 no podrá referenciar a C2, con lo que la lista de referenciantes
        // de C2 será vacia.

        Assert.assertEquals(new LinkedList<Celda>(), c2.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia Circular Múltiple
     * - Stubs: StubPosición
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se borre correctamente una celda de la lista de referenciantes con la función Set
     * - Operativa: se definen 4 celdas distintas y una lista de celdas. La primera referencia a la cuarta. La segunda
     *              a la primera. La tercera a la segunda. Se añade la cuarta a la lista y se asigna como lista de referenciantes de la tercera.
     *
     */
    @Test
    public void SetReferenciaCircularMultiple () {
        cleanRefs();
        Celda c3 = new Celda(new StubPosicion(1, 3));
        Celda c4 = new Celda(new StubPosicion(1, 4));

        c4.addReferenciante(c1);
        c1.addReferenciante(c2);
        c2.addReferenciante(c3);
        LinkedList<Celda> lista = new LinkedList<>();
        lista.add(c4);
        c3.setReferenciantes(lista);

        System.out.println("Refs C1:" + c1.getReferenciantes());
        System.out.println("Refs C2:" + c2.getReferenciantes());
        System.out.println("Refs C3:" + c3.getReferenciantes());
        System.out.println("Refs C4:" + c4.getReferenciantes());

        Assert.assertEquals(new LinkedList<Celda>(), c3.getReferenciantes());
    }

    /**
     * Objeto de la prueba: Testear el comportamiento en Referencia Circular Múltiple
     * - Stubs: StubPosición
     * - FDs: ninguno
     * - Valores estudiados: se estudia que se borre correctamente una celda de la lista de referenciantes con la función Set
     * - Operativa: se definen 4 celdas distintas y una lista de celdas. La segunda referencia a la primera.
     *              La tercera a la segunda. La cuarta a la tercera. Se añade la primera a la lista, y se asigna esa lista
     *              como referenciantes de la cuarta.
     *
     */
    @Test
    public void SetReferenciaCircularMultipleDesordenada () {
        cleanRefs();
        Celda c3 = new Celda(new StubPosicion(1, 3));
        Celda c4 = new Celda(new StubPosicion(1, 4));

        c1.addReferenciante(c2);
        c2.addReferenciante(c3);
        c3.addReferenciante(c4);
        LinkedList<Celda> lista = new LinkedList<>();
        lista.add(c1);
        c4.setReferenciantes(lista);

        System.out.println("Refs C1:" + c1.getReferenciantes());
        System.out.println("Refs C2:" + c2.getReferenciantes());
        System.out.println("Refs C3:" + c3.getReferenciantes());
        System.out.println("Refs C4:" + c4.getReferenciantes());

        Assert.assertEquals(new LinkedList<Celda>(), c4.getReferenciantes());
    }

    /** ==========================
     *  Stub de la clase Posicion
     *  ==========================
     */
    private static class StubPosicion extends Posicion {
        public StubPosicion (int _fila, int _columna) {
            super(_fila, _columna);
        }
    }

}