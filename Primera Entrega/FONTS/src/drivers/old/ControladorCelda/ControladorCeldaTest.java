package drivers.old.ControladorCelda;

import main.Domain.DomainControllers.ControladorCelda;
import main.Domain.DomainModel.Documento;
import org.junit.Assert;
import org.junit.Test;

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

        Assert.assertEquals("5.567", s);
        Assert.assertEquals("5.5678", s2);
        Assert.assertEquals("5.0", s3);
        Assert.assertEquals("#ERROR", s4);

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

        Assert.assertEquals("55", s);
        Assert.assertEquals("55", s2);
        Assert.assertEquals("1.34", s3);

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

        Assert.assertEquals("26.0", s);
        Assert.assertEquals("55.0", s2);
        Assert.assertEquals("-1.0", s3);
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

        Assert.assertEquals("10", s);
        Assert.assertEquals("11111111111111111111111111111011", s2);
    }

    @Test
    public void convertirValorDH(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.convertirValorDH(2);

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.convertirValorDH(10);

        cc.asignaCeldaPosicion(1, 2, 1);
        cc.convertirValorDH(-10);

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();
        String s3 = d.getHoja(1).getCelda(2,1).getValor();

        Assert.assertEquals("2", s);
        Assert.assertEquals("a", s2);
        Assert.assertEquals("fffffff6", s3);
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

        Assert.assertEquals("Mayo", s);
        Assert.assertEquals("#ERROR", s2);
    }

    @Test
    public void obtenerAño(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.obtenerAño("24/05/2031");

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.obtenerAño("24/03/-2031");

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();

        Assert.assertEquals("2031", s);
        Assert.assertEquals("#ERROR", s2);

    }

    @Test
    public void obtenerNombreDia(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.obtenerNombreDia("17/04/2022");

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.obtenerNombreDia("24/03/-2031");

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();

        Assert.assertEquals("Domingo", s);
        Assert.assertEquals("#ERROR", s2);

    }

    @Test
    public void longitudPalabra(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 1);
        cc.longitudPalabra("Hola");

        cc.asignaCeldaPosicion(1, 1, 2);
        cc.longitudPalabra("");

        String s = d.getHoja(1).getCelda(1,1).getValor();
        String s2 = d.getHoja(1).getCelda(1,2).getValor();

        Assert.assertEquals("4", s);
        Assert.assertEquals("0", s2);

    }


}