package test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;


public class HojaTest {

 /*   @InjectMocks
    private Hoja hoja;

    @Mock
    private Celda celda;
    private Posicion posicion;
    private HashMap<Posicion, Celda> celdas;

    private static final Posicion validpos = new Posicion(1, 2);
    private static final Celda validcel = new Celda(validpos);

    @Before
    public void inicializaMocks(){
        MockitoAnnotations.initMocks(this);

    }*/


    /**
     * Objeto de la prueba: Testear la funcion inicializaHoja
     *
     * - Stubs:
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados:
     *
     * - Operativa: En este test de inicializaHoja se definen varias Hojas con valores distintos en filas y columnas.
     * El objetivo es ver si inicializa correctamente la estructura de datos de las diferentes hojas
     * a la hora de crearlas. Para ello comprobaremos que la estructuras de datos de las Hojas tengan
     * un tamaños igual a tantas celdas como define el numero de columnas y filas que tienen.
     */
   /* @Test
    public void InicializaHojaDefault(){
        int f = 2;
        int c = 3;

        when(new Posicion(f, c)).thenReturn(validpos);
        when(new Celda(new Posicion(f, c))).thenReturn(validcel);
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
*/

    /**
     * Objeto de la prueba: Testear la constructora Hoja, que la crea con valores por defecto.
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de Hoja se define una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que se crea una Hoja con los valores por defecto, es decir, con 50 filas
     * y 50 columnas. Para ello simplemente crearemos una Hoja con la creadora sin parametros y comprobaremos
     * su numero de filas y columnas.
     */
    @Test
    public void testConstructoraDefault(){
        Hoja h =  new Hoja();

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(50, f);
        assertEquals(50, c);
    }

    /**
     * Objeto de la prueba: Testear la constructora Hoja, que la crea con valores dados
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de Hoja se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se crea una Hoja con los valores dados del numero de filas y el numero
     * de columnas. Para ello crearemos una Hoja con la creadora con parametros y comprobaremos que su numero de filas
     * y columnas sean equivalentes a los introducidos en la creadora
     */
    @Test
    public void testConstructoraValores(){
        Hoja h =  new Hoja(20, 30);

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(20, f);
        assertEquals(30, c);
    }


    /**
     * Objeto de la prueba: Testear la funcion setFilas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de setFilas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia el valor de las filas de la hoja por el valor introducido.
     * Para ello comprobaremos que el numero de filas corresponde al introducido y no al valor que viene puesto por
     * cualquiera de las dos constructoras
     */
    @Test
    public void testSetFilas(){
        Hoja h =  new Hoja(20, 30);
        h.setFilas(23);
        int f = h.getFilas();
        assertEquals(23, f);
    }

    /**
     * Objeto de la prueba: Testear la funcion setColumnas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de setColumnas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia el valor de las columnas de la hoja por el valor introducido.
     * Para ello comprobaremos que el numero de columnas corresponde al introducido y no al valor que viene puesto por
     * cualquiera de las dos constructoras
     */
    @Test
    public void testSetColumnas(){
        Hoja h =  new Hoja(20, 30);
        h.setColumnas(23);
        int c = h.getColumnas();
        assertEquals(23, c);
    }

    /**
     * Objeto de la prueba: Testear la funcion añadeNombreIdHojaDefault
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de añadeNombreIdHojaDefault se definen dos Hojas con la constructora sin parametros.
     * El objetivo es comprobar que tanto el nombre de la hoja como su id son inicializados con unos valores por defecto
     * Para ello comprobaremos que el nombre de la hoja sea "Hoja + su identificador" y el identificador sea igual
     * al numero dado.
     */
    @Test
    public void testAñadeNombreIdHojaDefault(){
        Hoja hoja = new Hoja();
        Hoja h2 = new Hoja();

        hoja.añadeNombreIdHojaDefault(1);
        h2.añadeNombreIdHojaDefault(2);

        int id = hoja.getId();
        int id2 = h2.getId();

        String name = hoja.getNombre();
        String name2 = h2.getNombre();

        assertEquals(1, id);
        assertEquals("Hoja1", name);
        assertEquals(2, id2);
        assertEquals("Hoja2", name2);
    }


    /**
     * Objeto de la prueba: Testear la funcion asignaNombre
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de asignaNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que  el nombre de la hoja corresponde con el nombre introducido
     * Para ello comprobaremos que el nombre de la hoja sea el mismo que se ha introducido
     */
    @Test
    public void testAsignaNombre(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);
        hoja.asignaNombre("HojaPrueba");
        String name = hoja.getNombre();
        assertEquals("HojaPrueba", name);
    }

    /**
     * Objeto de la prueba: Testear la funcion esHoja
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de esHoja se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que la hoja, es la hoja que tiene el identificador introducido
     * Para ello comprobaremos que devuelve cierto si el identificador es el mismo y falso si no lo es
     */
    @Test
    public void testEsHoja(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);

        boolean b = hoja.esHoja(1);
        boolean b2 = hoja.esHoja(2);

        assertEquals(true, b);
        assertEquals(false, b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion getId
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getId se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el identificador de la hoja
     * Para ello comprobaremos que devuelve el identificador que previamente le habremos asignado
     */
    @Test
    public void testGetId(){
        Hoja h = new Hoja();
        h.añadeNombreIdHojaDefault(1);

        int id = h.getId();

        h.añadeNombreIdHojaDefault(2);
        int id2 = h.getId();

        assertEquals(1, id);
        assertEquals(2, id2);
    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getColumnas se definen una Hoja con la constructora sin parametros y una Hoja con
     * la constructora con paramaetros.
     * El objetivo es comprobar que devuelve correctamente el numero de columnas de la hoja
     * Para ello comprobaremos que devuelve el numero de columnas que previamente se asigna a la hora de crearla
     * o manualmente
     */
    @Test
    public void testGetColumnas(){
        Hoja h = new Hoja();
        int c = h.getColumnas();

        h.setColumnas(15);
        int c3 = h.getColumnas();

        Hoja h2 = new Hoja(20, 30);
        int c2 = h2.getColumnas();
        assertEquals(50, c);
        assertEquals(30, c2);
        assertEquals(15, c3);
    }

    /**
     * Objeto de la prueba: Testear la funcion getFilas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getFilas se definen una Hoja con la constructora sin parametros y una Hoja con
     * la constructora con paramaetros.
     * El objetivo es comprobar que devuelve correctamente el numero de filas de la hoja
     * Para ello comprobaremos que devuelve el numero de filas que previamente se asigna a la hora de crearla
     * o manualmente
     */
    @Test
    public void testGetFilas(){
        Hoja h = new Hoja();
        int f = h.getFilas();

        h.setFilas(15);
        int f3 = h.getFilas();

        Hoja h2 = new Hoja(20, 30);
        int f2 = h2.getFilas();
        assertEquals(50, f);
        assertEquals(20, f2);
        assertEquals(15, f3);
    }


    /**
     * Objeto de la prueba: Testear la funcion getNombre
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el nombre de la hoja
     * Para ello comprobaremos que devuelve el nombre que previamente se asigna a la hora de crearla
     * o manualmente
     */
    @Test
    public void testGetNombre(){
        Hoja h = new Hoja();
        h.asignaNombre("HojaPrueba");
        String name = h.getNombre();

        h.añadeNombreIdHojaDefault(2);
        String name2 = h.getNombre();

        assertEquals("HojaPrueba", name);
        assertEquals("Hoja2", name2);
    }


    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja
     * Para ello comprobaremos que el tamaño de la estructura que devuelve sea el mismo que el que tiene la Hoja
     */
    @Test
    public void testGetCeldas(){
        Hoja h = new Hoja(2, 2);
        int clds = h.getCeldas().size();
        assertEquals(4, clds);
    }

    /**
     * Objeto de la prueba: Testear la funcion getCelda
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la celda en la posicion introducida
     * Para ello comprobaremos que el contenido de las celdas devueltas corresponden al contenido de las celdas que
     * hay en la posicion introducida
     */
  @Test
    public void testGetCelda(){
      Hoja h = new Hoja(2, 2);
      h.getCeldas().get(new Posicion(1, 1)).setContenido("ABCD");
      h.getCeldas().get(new Posicion(2, 1)).setContenido("AAAA");

      Celda cel = h.getCelda(1, 1);
      Celda cel2 = h.getCelda(1, 2);
      Celda cel3 = h.getCelda(2, 1);

      String s = cel.getContenido();
      String s2 = cel2.getContenido();
      String s3 = cel3.getContenido();

      assertEquals("ABCD", s);
      assertEquals("", s2);
      assertEquals("AAAA", s3);

    }


    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros y
     * dos Posiciones.
     * El objetivo es comprobar que devuelve true si la Posicion introducida existe en la Hoja o false si no existe
     * Para ello crearemos dos posiciones una existente en la hoja y otra que no y comprobaremos si los valores
     * son los correctos
     */
    @Test
    public void testExistePosicion(){
        Hoja h = new Hoja(2, 2);
        Posicion p = new Posicion(1, 2);
        Posicion p2 = new Posicion(3, 2);

        boolean b = h.existePosicion(p);
        boolean b2 = h.existePosicion(p2);

        assertEquals(true, b);
        assertEquals(false, b2);
    }


    /**
     * Objeto de la prueba: Testear la funcion addCeldaVacia
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de addCeldaVacia se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se añade una celda vacia a la Hoja
     * Para ello comprobaremos que una vez introducida esta celda, existe en la hoja
     */
    @Test
    public void testAddCeldaVacia(){
        Hoja h = new Hoja(2, 2);
        boolean b2 = h.addCeldaVacia(2, 3);
        boolean b3 = h.addCeldaVacia(2, 1);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 3));

        assertEquals(true, b);
        assertEquals(true, b2);
        assertEquals(false, b3);

    }

    /**
     * Objeto de la prueba: Testear la funcion quitarCelda
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de quitarCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se borra una celda de la estructura de datos, y que devuelve true si la posicion
     * existe o false de lo contrario
     * Para ello quitaremos dos posiciones una existente y otra no, comprobaremos que devuelven los valores correctos y
     * que la existente ha sido eliminada del mapa
     */
    @Test
    public void testQuitarCelda(){
        Hoja h = new Hoja(2, 2);
        boolean b2 =  h.quitarCelda(2, 1);
        boolean b3 = h.quitarCelda(2, 3);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 1));

        assertEquals(false, b);
        assertEquals(true, b2);
        assertEquals(false, b3);

    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello cogeremos una celda y nos quedaremos con su posicion, seguidamente cambiaremos la celda de posicion
     * por otra dada, una vez hecho volveremos a coger la posicion de la celda. Finalmente comprobamos que las
     * dos posiciones son distintas y ademas la nueva posicion corresponde con la introducida
     */
    @Test
    public void testCambiarPosicionCelda(){
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


    /**
     * Objeto de la prueba: Testear la variante de la funcion cambiarPosicionCelda
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que la variante de la funcion cambiarPosicionCelda funciona correctamente.
     * El funcionamiento es igual a la otra variante, lo diferente en esta, es que en lugar de introducir 4 enteros que
     * definen dos posiciones (la actual y la futura), se pasa una posicion(la futura) y una celda.
     */
    @Test
    public void testCambiarPosicionCelda2(){
        Hoja h = new Hoja(2, 2);
        Celda c = h.getCelda(2, 1);
        Posicion p = c.getPosicion();
        boolean b1 = h.cambiarPosicionCelda(new Posicion(1, 2), c);
        Posicion p2 = c.getPosicion();
        boolean b2 = h.cambiarPosicionCelda(new Posicion(3, 2), c);

        boolean b = p == p2;
        assertEquals(false, b);
        assertEquals(true, b1);
        assertEquals(false, b2);
        assertEquals(1, p2.getFila());
        assertEquals(2, p2.getColumna());
    }

    /*
    public static class PosicionStub{
        int fila;
        int columna;
        public PosicionStub(int _f, int _c) {
            this.fila = _f;
            this.columna = _c;
        }
    }

    public static class CeldaStub{
        PosicionStub posicionStub;
        String contenido;
        String valor;
        public CeldaStub(PosicionStub _ps) {
            this.posicionStub = _ps;
            this.contenido = "";
            this.valor = "";
        }
    }*/




}
