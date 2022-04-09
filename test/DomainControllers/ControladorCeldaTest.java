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
        assertEquals("5.567", s);

    }

}