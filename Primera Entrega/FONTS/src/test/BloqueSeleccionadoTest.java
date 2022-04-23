package test;

import main.Domain.DomainModel.BloqueSeleccionado;
import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BloqueSeleccionadoTest {

    /**
     * Objeto de la prueba: seteo de las celdas que forman el BloqueSeleccionado.
     * Otros elementos integrados en la prueba: Stub de Hoja, Stub de Celda y Stub de Posición, para utilizar
     *      correctamente la creadora de CeldaStub.
     * Drivers: -
     * Stubs: Hoja(simula una hoja, sólo necesario por los argumentos de la operación a testear), y
     *      Celda(simula una celda, de la cual sólo es necesario su contenido).
     * Archivos de datos necesarios: -
     * Valores estudiados: dos celdas con contenidos "hola" y "adiós", respectivamente.
     * Efectos estudiados: -
     * Operativa: Se crean instancias de los stubs necesarios, y se setea el valor de las celdas a
     *      "hola" y "adiós". Posteriormente, se ejecuta la operación objeto de la prueba y se comprueba
     *      que el resultado es correcto.
     */
    @Test
    public void testSetCelda() {
        BloqueSeleccionado b = BloqueSeleccionado.getBloque();
        HojaStub h = new HojaStub();
        CeldaStub c = new CeldaStub("hola");
        CeldaStub d = new CeldaStub("irrelevante");
        b.setCelda(c, d, h);

        assertEquals(b.getCeldaInicial().getContenido(), new CeldaStub("hola"));
    }

    /**
     * Objeto de la prueba: getter de la celda inicial que forma el bloque seleccionado.
     * Otros elementos integrados en la prueba: Stub de Hoja, Stub de Celda y Stub de Posición, para utilizar
     *      correctamente la creadora de CeldaStub.
     * Drivers: -
     * Stubs: Hoja(simula una hoja, sólo necesario por los argumentos de la operación a testear), y
     *      Celda(simula una celda, de la cual sólo es necesario su contenido).
     * Archivos de datos necesarios: -
     * Valores estudiados: única celda con contenido "hola".
     * Efectos estudiados: -
     * Operativa: Se crean instancias de los stubs necesarios, y se setea el valor de la celda a
     *      "hola". Posteriormente, se ejecuta la operación objeto de la prueba y se comprueba
     *      que el resultado es correcto.
     */
    @Test
    public void testGetCeldaInicial() {
        BloqueSeleccionado b = BloqueSeleccionado.getBloque();
        HojaStub h = new HojaStub();
        CeldaStub c = new CeldaStub("hola");
        b.setCelda(c, null, h);

        assertEquals(b.getCeldaInicial().getContenido(), c.getContenido());
    }

    /**
     * Objeto de la prueba: getter de la celda final que forma el bloque seleccionado.
     * Otros elementos integrados en la prueba: Stub de Hoja, Stub de Celda y Stub de Posición, para utilizar
     *      correctamente la creadora de CeldaStub.
     * Drivers: -
     * Stubs: Hoja(simula una hoja, sólo necesario por los argumentos de la operación a testear), y
     *      Celda(simula una celda, de la cual sólo es necesario su contenido).
     * Archivos de datos necesarios: -
     * Valores estudiados: única celda con contenido "hola".
     * Efectos estudiados: -
     * Operativa: Se crean instancias de los stubs necesarios, y se setea el valor de la celda a
     *      "hola". Posteriormente, se ejecuta la operación objeto de la prueba y se comprueba
     *      que el resultado es correcto.
     */
    @Test
    public void testGetCeldaFinal() {
        BloqueSeleccionado b = BloqueSeleccionado.getBloque();
        HojaStub h = new HojaStub();
        CeldaStub c = new CeldaStub("hola");
        b.setCelda(null, c, h);

        assertEquals(b.getCeldaFinal().getContenido(), c.getContenido());
    }

    /**
     * Objeto de la prueba: getter de la hoja que contiene las celdas que forman el bloque seleccionado.
     * Otros elementos integrados en la prueba: Stub de Hoja.
     * Drivers: -
     * Stubs: Hoja(simula una hoja, sólo necesario por los argumentos de la operación a testear).
     * Archivos de datos necesarios: -
     * Valores estudiados: única hoja.
     * Efectos estudiados: -
     * Operativa: Se crean instancias del stub de hoja, y se setea el valor del bloque con la hoja
     *      en cuestión. Posteriormente, se ejecuta la operación objeto de la prueba y se comprueba
     *      que el resultado es correcto.
     */
    @Test
    public void testGetHoja() {
        BloqueSeleccionado b = BloqueSeleccionado.getBloque();
        HojaStub h = new HojaStub();
        b.setCelda(null, null, h);

        assertEquals(b.getHoja(), h);
    }

    private class PosicionStub extends Posicion {
        public PosicionStub(int _f, int _c) {
            super(_f, _c);
        }
    }

    private class CeldaStub extends Celda {
        public CeldaStub(String _cont) {
            super(new PosicionStub(1, 1), _cont);
        }
    }

    private class HojaStub extends Hoja {
        public HojaStub() {}
    }
}
