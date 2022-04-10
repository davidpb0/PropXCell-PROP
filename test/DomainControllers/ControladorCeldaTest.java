package DomainControllers;

import DomainModel.Documento;
import DomainModel.Hoja;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControladorCeldaTest {

    @Test
    public void truncarValor(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 2);

        ControladorCelda cc = new ControladorCelda();
        cc.asignaCeldaPosicion(1, 1, 2);
        d.getHoja(1).getCelda(1,2).setValor("5.5678945");
        cc.truncarValor(3);
        String s = d.getHoja(1).getCelda(1,2).getValor();

        cc.asignaCeldaPosicion(1, 1, 1);
        d.getHoja(1).getCelda(1,1).setValor("5.5678945");
        cc.truncarValor(4);
        String s2 = d.getHoja(1).getCelda(1,1).getValor();

        cc.asignaCeldaPosicion(1, 2, 1);
        d.getHoja(1).getCelda(2,1).setValor("5.5678945");
        cc.truncarValor(0);
        String s3 = d.getHoja(1).getCelda(2,1).getValor();

        cc.asignaCeldaPosicion(1, 2, 2);
        d.getHoja(1).getCelda(2,2).setValor("5.5678945");
        cc.truncarValor(10);
        String s4 = d.getHoja(1).getCelda(2,2).getValor();
        System.out.println(s4);

        assertEquals("5.567", s);
        assertEquals("5.5678", s2);
        assertEquals("5.0", s3);
        assertEquals("#ERROR", s4);

    }



}