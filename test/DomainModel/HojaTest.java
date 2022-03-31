package DomainModel;
import static org.junit.Assert.*;
import org.junit.Test;


public class HojaTest {

    @Test
    public void TestConstructoraDefault(){
        Hoja h =  new Hoja();

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(50, f);
        assertEquals(50, c);
    }

    @Test
    public void TestConstructoraValores(){
        Hoja h =  new Hoja(20, 30);

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(20, f);
        assertEquals(30, c);
    }

    @Test
    public void añadeNombreIdHojaDefault(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);
        int id = hoja.getId();
        String name = hoja.getNombre();
        assertEquals(1, id);
        assertEquals("Hoja1", name);
    }

    @Test
    public void assignaNombre(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);
        hoja.assignaNombre("HojaPrueba");
        String name = hoja.getNombre();
        assertEquals("HojaPrueba", name);
    }

    @Test
    public void esHoja(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);
        boolean b = hoja.esHoja(1);
        assertEquals(true, b);

    }

    @Test
    public void inicializaHoja(){
        Hoja h = new Hoja(2, 2);
        int s = h.getCeldas().size();

        Hoja h2 = new Hoja(3, 4);
        int s2 = h2.getCeldas().size();

        Hoja h3 = new Hoja(3, 2);
        int s3 = h3.getCeldas().size();

        Hoja h4 = new Hoja();
        int s4 = h4.getCeldas().size();

        assertEquals(4, s);
        assertEquals(12, s2);
        assertEquals(6, s3);
        assertEquals(2500, s4);
    }
}
