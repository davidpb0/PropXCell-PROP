package DomainControllers;

import DomainModel.Documento;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorCeldaTest {

    @Test
    public void truncarValor(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 2);
        cc.truncarValor(5.5678945, 3);
        String s = d.getHoja(1).getCelda(1,2).getValor();

        cc.asignaCeldaPosicion(1, 1, 1);
        cc.truncarValor(5.5678945, 4);
        String s2 = d.getHoja(1).getCelda(1,1).getValor();

        cc.asignaCeldaPosicion(1, 2, 1);
        cc.truncarValor(5.5678945, 0);
        String s3 = d.getHoja(1).getCelda(2,1).getValor();

        cc.asignaCeldaPosicion(1, 2, 2);
        cc.truncarValor(5.5678945,10);
        String s4 = d.getHoja(1).getCelda(2,2).getValor();
        System.out.println(s4);

        assertEquals("5.567", s);
        assertEquals("5.5678", s2);
        assertEquals("5.0", s3);
        assertEquals("#ERROR", s4);

    }
    @Test
    public void valorAbs(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.valorAbsoluto(-55);

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.valorAbsoluto(55);

        cc.asignaCeldaPosicion(1, 2, 1);
        cc.valorAbsoluto(-1.34);

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();
        String s3 = d.getHoja(1).getCelda(2,1).getValor();

        assertEquals("55", s);
        assertEquals("55", s2);
        assertEquals("1.34", s3);

    }

    @Test
    public void valorAprox(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.aproximarValor(25.578);

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.aproximarValor(55);

        cc.asignaCeldaPosicion(1, 2, 1);
        cc.aproximarValor(-1.34);

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();
        String s3 = d.getHoja(1).getCelda(2,1).getValor();

        assertEquals("26.0", s);
        assertEquals("55.0", s2);
        assertEquals("-1.0", s3);
    }

    @Test
    public void convertirValorDB(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.convertirValorDB(2);

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.convertirValorDB(-5);

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();

        assertEquals("10", s);
        assertEquals("11111111111111111111111111111011", s2);
    }

    @Test
    public void obtenerMes(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.obtenerMes("24/05/2031");

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.obtenerMes("24/15/2031");

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();

        assertEquals("Mayo", s);
        assertEquals("#ERROR", s2);
    }


}