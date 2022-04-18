package test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;
import main.Domain.DomainModel.Traductor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.HashMap;


public class HojaTest {

   /* @InjectMocks
    private Hoja h;

    @Mock
    private Traductor;

    @Before
    public void inicializaMocks() {
        MockitoAnnotations.initMocks(this);
    }*/

    /**
     * Objeto de la prueba: Testear la funcion inicializaHoja
     *He probado a hacer Mocks para el hashmap y me sale esto->
     * - Stubs:  you stub either of: final/private/equals()/hashCode() methods.
     *    Those methods *cannot* be stubbed/verified.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso de la creador con los valores por defecto.
     * - Operativa: En este test de inicializaHoja se definen varias Hojas con valores distintos en filas y columnas.
     * El objetivo es ver si inicializa correctamente la estructura de datos de las diferentes hojas
     * a la hora de crearlas. Para ello comprobaremos que la estructuras de datos de las Hojas tengan
     * un tamaños igual a tantas celdas como define el numero de columnas y filas que tienen.
     */
    @Test
    public void inicializaHojaDefault(){
        Hoja hoja = new Hoja( );
        int s = hoja.getCeldas().size();
        assertEquals(2500, s);

    }

    /**
     * Objeto de la prueba: Testear la funcion inicializaHoja
     *He probado a hacer Mocks para el hashmap y me sale esto->
     * - Stubs:  you stub either of: final/private/equals()/hashCode() methods.
     *    Those methods *cannot* be stubbed/verified.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso de la creador con los valores dados.
     * - Operativa: En este test de inicializaHoja se definen varias Hojas con valores distintos en filas y columnas.
     * El objetivo es ver si inicializa correctamente la estructura de datos de las diferentes hojas
     * a la hora de crearlas. Para ello comprobaremos que la estructuras de datos de las Hojas tengan
     * un tamaños igual a tantas celdas como define el numero de columnas y filas que tienen.
     */
    @Test
    public void inicializaHoja(){
        Hoja hoja = new Hoja(2, 3);
        int s = hoja.getCeldas().size();
        assertEquals(6, s);

    }

//Creo que no hace falta
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
  /*  @Test
    public void constructoraDefault(){
        Hoja h =  new Hoja();

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(50, f);
        assertEquals(50, c);
    }*/

    //Creo que no hace falta
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
    /*@Test
    public void constructoraValores(){
        Hoja h =  new Hoja(20, 30);

        int f = h.getFilas();
        int c = h.getColumnas();

        assertEquals(20, f);
        assertEquals(30, c);
    }*/

    //Creo que no hace falta
    /**
     * Objeto de la prueba: Testear la funcion setFilas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan manualmente las filas, con un numero positivo
     * - Operativa: En este test de setFilas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia el valor de las filas de la hoja por el valor introducido.
     * Para ello comprobaremos que el numero de filas corresponde al introducido y no al valor que viene puesto por
     * cualquiera de las dos constructoras
     */
    @Test
    public void setFilasPositivo(){
        Hoja h =  new Hoja(20, 30);
        boolean b = h.setFilas(23);

        int f = h.getFilas();

        assertEquals(23, f);
        assertTrue(b);
    }

    //Creo que no hace falta
    /**
     * Objeto de la prueba: Testear la funcion setFilas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan manualmente las filas, con un valor negativo
     * - Operativa: En este test de setFilas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia el valor de las filas de la hoja por el valor introducido, ya que este
     * es negativo.
     * Para ello comprobaremos que el numero de filas corresponde al de su creadora y no al valor introducido y que
     * devuelve false
     */
    @Test
    public void setFilasNegativo(){
        Hoja h =  new Hoja(20, 30);
        boolean b = h.setFilas(-23);

        int f = h.getFilas();

        assertEquals(20, f);
        assertFalse(b);
    }

    //Creo que no hace falta
    /**
     * Objeto de la prueba: Testear la funcion setColumnas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan manualmente las columnas, con un numero positivo
     * - Operativa: En este test de setColumnas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia el valor de las columnas de la hoja por el valor introducido.
     * Para ello comprobaremos que el numero de columnas corresponde al introducido y no al valor que viene puesto por
     * cualquiera de las dos constructoras y devuelve true
     */
    @Test
    public void setColumnasPositivo(){
        Hoja h =  new Hoja(20, 30);

        boolean b = h.setColumnas(23);
        int c = h.getColumnas();

        assertEquals(23, c);
        assertTrue(b);
    }

    //Creo que no hace falta
    /**
     * Objeto de la prueba: Testear la funcion setColumnas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan manualmente las columnas, con un numero negativo
     * - Operativa: En este test de setColumnas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia el valor de las columnas de la hoja por el valor introducido.
     * Para ello comprobaremos que el numero de columnas corresponde al  que viene puesto por cualquiera de las dos
     * constructoras y devuelve false
     */
    @Test
    public void setColumnasNegativo(){
        Hoja h =  new Hoja(20, 30);

        boolean b = h.setColumnas(-23);
        int c = h.getColumnas();

        assertEquals(30, c);
        assertFalse(b);
    }

    /**
     * Objeto de la prueba: Testear la funcion añadeNombreIdHojaDefault
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan un nombre y un identificador positivo por defecto.
     * - Operativa: En este test de añadeNombreIdHojaDefault se define una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que tanto el nombre de la hoja como su id son inicializados con unos valores por defecto
     * Para ello comprobaremos que el nombre de la hoja sea "Hoja + su identificador" y el identificador sea igual
     * al numero dado.
     */
    @Test
    public void añadeNombreIdHojaDefaultPositivo(){
        Hoja hoja = new Hoja();

        hoja.añadeNombreIdHojaDefault(1);

        int id = hoja.getId();
        String name = hoja.getNombre();

        assertEquals(1, id);
        assertEquals("Hoja1", name);

    }

    /**
     * Objeto de la prueba: Testear la funcion añadeNombreIdHojaDefault
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan un nombre y un identificador en negativo.
     * - Operativa: En este test de añadeNombreIdHojaDefault se define una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que tanto el nombre de la hoja como su id no son inicializados dado que se le introduce
     * un numero en negativo. Para ello comprobaremos que devuelve false.
     */
    @Test
    public void añadeNombreIdHojaDefaultNegativo(){
        Hoja hoja = new Hoja();

        boolean b = hoja.añadeNombreIdHojaDefault(-1);

        assertFalse(b);
    }



    /**
     * Objeto de la prueba: Testear la funcion asignaNombre
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan un nombre a la hoja manualmente.
     * - Operativa: En este test de asignaNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que  el nombre de la hoja corresponde con el nombre introducido
     * Para ello comprobaremos que el nombre de la hoja sea el mismo que se ha introducido
     */
    @Test
    public void asignaNombre(){
        Hoja hoja = new Hoja();
        hoja.asignaNombre("HojaPrueba");

        String name = hoja.getNombre();

        assertEquals("HojaPrueba", name);
    }

    /**
     * Objeto de la prueba: Testear la funcion esHoja
     *- Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se comprueba una hoja correcta.
     * - Operativa: En este test de esHoja se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que la hoja, es la hoja que tiene el identificador introducido
     * Para ello comprobaremos que devuelve cierto si el identificador es el mismo.
     */
    @Test
    public void esHojaCorrecta(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);

        boolean b = hoja.esHoja(1);

        assertTrue(b);

    }

    /**
     * Objeto de la prueba: Testear la funcion esHoja
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se comprueba una hoja incorrecta.
     * - Operativa: En este test de esHoja se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que la hoja, no es la hoja que tiene el identificador introducido
     * Para ello comprobaremos que devuelve false si el identificador no es el mismo.
     */
    @Test
    public void esHojaIncorrecta(){
        Hoja hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);

        boolean b = hoja.esHoja(2);

        assertFalse(b);

    }

    /**
     * Objeto de la prueba: Testear la funcion getId
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el identificador de una hoja.
     * - Operativa: En este test de getId se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el identificador de la hoja
     * Para ello comprobaremos que devuelve el identificador que previamente le habremos asignado
     */
    @Test
    public void getId(){
        Hoja h = new Hoja();
        h.añadeNombreIdHojaDefault(1);

        int id = h.getId();

        assertEquals(1, id);

    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el numero de columnas de una Hoja
     * - Operativa: En este test de getColumnas se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el numero de columnas de la hoja
     * Para ello comprobaremos que devuelve el numero de columnas que previamente se asigna a la hora de crearla
     * o manualmente
     */
    @Test
    public void getColumnas(){
        Hoja h = new Hoja();

        int c = h.getColumnas();

        assertEquals(50, c);

    }

    /**
     * Objeto de la prueba: Testear la funcion getFilas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el numero de filas de una Hoja
     * - Operativa: En este test de getFilas se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el numero de filas de la hoja
     * Para ello comprobaremos que devuelve el numero de filas que previamente se asigna a la hora de crearla
     * o manualmente
     */
    @Test
    public void getFilas(){
        Hoja h = new Hoja();

        int f = h.getFilas();

        assertEquals(50, f);

    }


    /**
     * Objeto de la prueba: Testear la funcion getNombre
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el nombre de una Hoja
     * - Operativa: En este test de getNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el nombre de la hoja
     * Para ello comprobaremos que devuelve el nombre que previamente se asigna manualmente.
     */
    @Test
    public void getNombre(){
        Hoja h = new Hoja();
        h.asignaNombre("HojaPrueba");

        String name = h.getNombre();

        assertEquals("HojaPrueba", name);

    }


    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que la hoja tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja
     * Para ello comprobaremos que el tamaño de la estructura que devuelve sea el mismo que el que tiene la Hoja y que
     * la estructura sea la misma
     */
    @Test
    public void getCeldasLleno(){
        Hoja h = new Hoja(2, 2);

        int clds = h.getCeldas().size();
        HashMap<Posicion, Celda> m = h.getCeldas();

        assertEquals(4, clds);
        assertSame(m, h.getCeldas());
    }
    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que la hoja no tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja vacia
     * Para ello comprobaremos que el tamaño de la estructura que devuelve sea el mismo que el que tiene la Hoja
     */
    @Test
    public void getCeldasVacio(){
        Hoja h = new Hoja(0, 0);
        int clds = h.getCeldas().size();

        HashMap<Posicion, Celda> m = h.getCeldas();

        assertEquals(0, clds);
        assertSame(m, h.getCeldas());
    }


    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se pide una celda con una posicion existente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la celda en la posicion introducida existente
     * Para ello comprobaremos que la celda devuelta es la que hay en la posicion pedida
     */
  @Test
    public void getCeldaPosicionExistente(){
      Hoja h = new Hoja(2, 2);

      Celda c = h.getCelda(2, 2);

      assertSame(c, h.getCeldas().get(new Posicion(2, 2)));

    }

    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se pide una celda con una posicion inexistente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve una celda nula.
     * Para ello comprobaremos que la celda devuelta es nula.
     */
    @Test
    public void getCeldaPosicionInexistente(){
        Hoja h = new Hoja(2, 2);

        Celda c = h.getCelda(3, 2);

        assertNull(c);

    }


    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se comprueba una posicion existente
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros y
     * una Posicion.
     * El objetivo es comprobar que devuelve true si la Posicion introducida existe en la Hoja.
     * Para ello crearemos una posicion existente en la hoja y comprobaremos si los valores son los correctos
     */
    @Test
    public void existePosicionExistente(){
        Hoja h = new Hoja(2, 2);
        Posicion p = new Posicion(1, 2);

        boolean b = h.existePosicion(p);

        assertTrue(b);
    }

    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se comprueba una posicion inexistente
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros y
     * una Posicion.
     * El objetivo es comprobar que devuelve false si la Posicion introducida no existe en la Hoja.
     * Para ello crearemos una posicion inexistente en la hoja y comprobaremos si los valores son los correctos
     */
    @Test
    public void existePosicionInexistente(){
        Hoja h = new Hoja(2, 2);
        Posicion p = new Posicion(3, 2);

        boolean b = h.existePosicion(p);

        assertFalse(b);
    }


    /**
     * Objeto de la prueba: Testear la funcion addCeldaVacia
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se añade una celda vacia a una posicion inexistente
     * - Operativa: En este test de addCeldaVacia se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se añade una celda vacia a la Hoja
     * Para ello comprobaremos que una vez introducida esta celda, existe en la hoja
     */
    @Test
    public void addCeldaVaciaPosicionInexistente(){
        Hoja h = new Hoja(2, 2);
        h.addCeldaVacia(2, 3);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 3));

        assertTrue(b);

    }

    /**
     * Objeto de la prueba: Testear la funcion addCeldaVacia
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se añade una celda vacia a una posicion existente
     * - Operativa: En este test de addCeldaVacia se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no añade una celda vacia a la Hoja si la posicion ya existe
     * Para ello comprobaremos que la funcion devuelve false
     */
    @Test
    public void addCeldaVaciaPosicionExistente(){
        Hoja h = new Hoja(2, 2);
        boolean b2 = h.addCeldaVacia(2, 1);

        assertFalse(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion quitarCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se elimina una posicion y una celda en una posicion existente
     * - Operativa: En este test de quitarCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se borra una celda de la estructura de datos, y que devuelve true si la posicion
     * existe
     * Para ello quitaremos una posicion existente, comprobaremos que devuelve los valores correctos y
     * que la existente ha sido eliminada del mapa
     */
    @Test
    public void quitarCeldaPosicionExistente(){
        Hoja h = new Hoja(2, 2);

        boolean b2 =  h.quitarCelda(2, 1);

        boolean b = h.getCeldas().containsKey(new Posicion(2, 1));

        assertFalse(b);
        assertTrue(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion quitarCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se elimina una posicion y una celda en una posicion inexistente
     * - Operativa: En este test de quitarCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se borra una celda de la estructura de datos si la posicion no existe.
     * Para ello quitaremos una posicion inexistente, comprobaremos que devuelve false.
     */
    @Test
    public void quitarCeldaPosicionInexistente(){
        Hoja h = new Hoja(2, 2);

        boolean b2 =  h.quitarCelda(2, 3);

        assertFalse(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas dos posiciones existentes
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello cambiaremos una celda de posicion por otra dada, una vez hecho cogeremos la posicion de la celda.
     * Finalmente comprobamos que la nueva posicion corresponde con la introducida.
     */
    @Test
    public void cambiarPosicionCeldaPosicionesExistentes(){
        Hoja h = new Hoja(2, 2);
        Celda c = h.getCeldas().get(new Posicion(2, 1));

        h.cambiarPosicionCelda(2, 1, 1, 2);

        Posicion p2 = c.getPosicion();

        assertEquals(1, p2.getFila());
        assertEquals(2, p2.getColumna());
    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas dos posiciones una de ellas
     * inexistentes.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello comprobaremos que la funcion devuelve false, ya que significara que esta no hace nada
     */
    @Test
    public void cambiarPosicionCeldaPosicionesInxistentes(){
        Hoja h = new Hoja(2, 2);

        boolean b =h.cambiarPosicionCelda(2, 1, 1, 3);

        assertFalse(b);
    }


    /**
     * Objeto de la prueba: Testear la variante de la funcion cambiarPosicionCelda
     *- Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas una posicion y una celda existentes.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que la variante de la funcion cambiarPosicionCelda funciona correctamente.
     * El funcionamiento es igual a la otra variante, lo diferente en esta, es que en lugar de introducir 4 enteros que
     * definen dos posiciones (la actual y la futura), se pasa una posicion(la futura) y una celda.
     */
    @Test
    public void cambiarPosicionCelda2PosicionesExistentes(){
        Hoja h = new Hoja(2, 2);
        Celda c = h.getCelda(2, 1);
        boolean b1 = h.cambiarPosicionCelda(new Posicion(1, 2), c);
        Posicion p2 = c.getPosicion();

        assertEquals(1, p2.getFila());
        assertEquals(2, p2.getColumna());
    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas una posicion inexistente y una
     * celda.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello comprobaremos que la funcion devuelve false, ya que significara que esta no hace nada
     */
    @Test
    public void cambiarPosicionCelda2PosicionesInexistentes(){
        Hoja h = new Hoja(2, 2);

        Celda c = h.getCelda(2, 1);
        boolean b1 = h.cambiarPosicionCelda(new Posicion(1, 3), c);
        assertFalse(b1);
    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnaFila
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se da dos posiciones de manera que se selecciona una columna
     * de arriba a abajo
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar se consigue un conjunto de celdas correcto.
     * Para ello comprobaremos que el ArrayList que devuelve la función es el correcto.
     */
    @Test
    public void cogerColumnaArribaAbajo(){
        Hoja h = new Hoja(3, 3);
        String p1 = "A1";
        String p2 = "A3";

        ArrayList<Celda> cls = h.getColumnaFila(p1, p2);

        assertEquals(3, cls.size());


    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnaFila
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se da dos posiciones de manera que se selecciona una fila
     * de izquierda a derecha
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar se consigue un conjunto de celdas correcto.
     * Para ello comprobaremos que el ArrayList que devuelve la función es el correcto.
     */
    @Test
    public void cogerFilaIzquierdaDerecha(){
        Hoja h = new Hoja(3, 3);
        String p1 = "A1";
        String p2 = "C3";

        ArrayList<Celda> cls = h.getColumnaFila(p1, p2);

        assertEquals(3, cls.size());


    }




}
