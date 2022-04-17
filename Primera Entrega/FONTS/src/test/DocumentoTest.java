package test;
import static org.junit.Assert.*;

import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import org.junit.Test;


public class DocumentoTest {

    @Test
    public void cogerDocumento(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");
        int i = d.getNumHojas();
        Assert.assertEquals(1, i);
    }

    @Test
    public void inicializaDocumento(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 3);
        int i = d.getNumHojas();

        String s = d.getNombre();

        Hoja h = (Hoja) d.getHoja(1);
        int i2 = h.getCeldas().size();
        String s2 = h.getNombre();
        int i4 = h.getId();

        int i3 = d.getHojasContenidas().size();

        System.out.println(d.getFecha());
        Assert.assertEquals(1, i);
        Assert.assertEquals(6, i2);
        Assert.assertEquals("Doc1", s);
        Assert.assertEquals("Hoja1", s2);
        Assert.assertEquals(1, i3);
        Assert.assertEquals(1, i4);
    }

    @Test
    public void inicializaDocumentoDefault(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");
        int i = d.getNumHojas();

        String s = d.getNombre();

        Hoja h = (Hoja) d.getHoja(1);
        int i2 = h.getCeldas().size();
        String s2 = h.getNombre();
        int i4 = h.getId();

        int i3 = d.getHojasContenidas().size();

        System.out.println(d.getFecha());
        Assert.assertEquals(1, i);
        Assert.assertEquals(2500, i2);
        Assert.assertEquals("Doc1", s);
        Assert.assertEquals("Hoja1", s2);
        Assert.assertEquals(1, i3);
        Assert.assertEquals(1, i4);
    }

    @Test
    public void cogeHoja(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        Hoja h = d.getHoja(1);
        int i = h.getId();
        Hoja h2 = d.getHoja(2);
        boolean b = h2 == null;
        Assert.assertEquals(1, i);
        Assert.assertEquals(true, b);

    }




}
