package DomainModel;
import static org.junit.Assert.*;
import org.junit.Test;



public class HojaTest {

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
    public void setFilas(){
        Hoja h =  new Hoja(20, 30);
        h.setFilas(23);
        int f = h.getFilas();
        assertEquals(23, f);
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
    public void getId(){
        Hoja h = new Hoja();
        h.añadeNombreIdHojaDefault(1);
        int id = h.getId();
        assertEquals(1, id);
    }

    @Test
    public void getColumnas(){
        Hoja h = new Hoja();
        int c = h.getColumnas();

        Hoja h2 = new Hoja(20, 30);
        int c2 = h2.getColumnas();
        assertEquals(50, c);
        assertEquals(30, c2);
    }

    @Test
    public void getFilas(){
        Hoja h = new Hoja();
        int f = h.getFilas();

        Hoja h2 = new Hoja(20, 30);
        int f2 = h2.getFilas();
        assertEquals(50, f);
        assertEquals(20, f2);
    }

    @Test
    public void getNombre(){
        Hoja h = new Hoja();
        h.assignaNombre("HojaPrueba");
        String name = h.getNombre();
        assertEquals("HojaPrueba", name);
    }

    @Test
    public void getCeldas(){
        Hoja h = new Hoja(2, 2);
        int clds = h.getCeldas().size();
        assertEquals(4, clds);
    }

  @Test
    public void getCelda(){
        Hoja h = new Hoja(2, 2);
        h.getCeldas().get(new Posicion(1, 1)).setContenido("ABCD");
        Celda cel = h.getCelda(1, 1);
        String s = cel.getContenido();;
        Celda cel2 = h.getCelda(1, 2);
        String s2 = cel2.getContenido();;
        assertEquals("ABCD", s);
        assertEquals("", s2);

    }

    @Test
    public void añadirCeldaVacia(){
        Hoja h = new Hoja(2, 2);
        h.addCeldaVacia(2, 3);
        h.addCeldaVacia(2, 1);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 3));

        assertEquals(true, b);

    }

    @Test
    public void quitarCelda(){
        Hoja h = new Hoja(2, 2);
        h.quitarCelda(2, 1);
        h.quitarCelda(2, 3);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 1));

        assertEquals(false, b);

    }

    @Test
    public void cambiarPosicionCelda(){
        Hoja h = new Hoja(2, 2);
        Celda c = h.getCelda(2, 1);
        Posicion p = c.getPosicion();
        h.cambiarPosicionCelda(2, 1, 1, 2);

        Posicion p2 = c.getPosicion();

        boolean b = p == p2;
        assertEquals(false, b);
        assertEquals(1, p2.getFila());
        assertEquals(2, p2.getColumna());
    }

    @Test
    public void cambiarPosicionCelda2(){
        Hoja h = new Hoja(2, 2);
        Celda c = h.getCelda(2, 1);
        Posicion p = c.getPosicion();
        h.cambiarPosicionCelda(new Posicion(1, 2), c);

        Posicion p2 = c.getPosicion();

        boolean b = p == p2;
        assertEquals(false, b);
        assertEquals(1, p2.getFila());
        assertEquals(2, p2.getColumna());
    }
}
