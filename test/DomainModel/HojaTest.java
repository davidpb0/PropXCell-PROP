package DomainModel;
import static org.junit.Assert.*;
import org.junit.Test;


public class HojaTest {

    @Test
    public void inicializaHojaDefault(){
        Hoja hoja = new Hoja();
        hoja.inicializaHojaDefault(1);
        int id = hoja.getId();
        String name = hoja.getNombre();
        assertEquals(1, id);
        assertEquals("Hoja1", name);
    }

    @Test
    public void assignaNombre(){
        Hoja hoja = new Hoja();
        hoja.inicializaHojaDefault(1);
        hoja.assignaNombre("HojaPrueba");
        String name = hoja.getNombre();
        assertEquals("HojaPrueba", name);
    }

    @Test
    public void esHoja(){
        Hoja hoja = new Hoja();
        hoja.inicializaHojaDefault(1);
        boolean b = hoja.esHoja(1);
        assertEquals(true, b);

    }
}
