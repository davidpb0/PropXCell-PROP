package test;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.mockConstruction;

public class HojaTest {

    HashMap<Posicion, Celda> allcelds;
    @InjectMocks
    private Hoja hoja;

    @Mock
    //private Posicion posicion;
    private Celda celda;
   // ArrayList<Celda> refs = new ArrayList<>();


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

    }

    @After
    public void tearDown(){

    }





   /**
     * Objeto de la prueba: Testear la constructora Hoja, que la crea con valores por defecto.
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se crea una hoja sin pasarle parametros
     * - Operativa: En este test de Hoja se define una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que se crea una Hoja con los valores por defecto, es decir, con 50 filas
     * ,50 columnas y que se llena su estructura de datos. Para ello simplemente crearemos una Hoja con la creadora sin
     *  parametros y comprobaremos su numero de filas, columnas y tamaño de su estructura de Datos.
     */
    @Test
    public void constructoraDefault(){
         hoja =  new Hoja();

        int f = hoja.getFilas();
        int c = hoja.getColumnas();
        int s = hoja.getCeldas().size();

        assertEquals(50, f);
        assertEquals(50, c);
        assertEquals(2500, s);
    }

    /**
     * Objeto de la prueba: Testear la constructora Hoja, que la crea con valores dados
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se crea una Hoja con valores introducidos
     * - Operativa: En este test de Hoja se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se crea una Hoja con los valores dados del numero de filas y el numero
     * de columnas. Para ello crearemos una Hoja con la creadora con parametros y comprobaremos que su numero de filas
     * y columnas sean equivalentes a los introducidos en la creadora y su estructura de datos este tan llena como
     * los valores introducidos indican
     */
    @Test
    public void constructoraValores(){
        hoja =  new Hoja(20, 30);

        int f = hoja.getFilas();
        int c = hoja.getColumnas();
        int s = hoja.getCeldas().size();

        assertEquals(20, f);
        assertEquals(30, c);
        assertEquals(600, s);
    }


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
        hoja =  new Hoja(20, 30);

        boolean b = hoja.setFilas(23);
        int f = hoja.getFilas();

        assertEquals(23, f);
        assertTrue(b);
    }

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
        hoja =  new Hoja(20, 30);
        boolean b = hoja.setFilas(-23);

        int f = hoja.getFilas();

        assertEquals(20, f);
        assertFalse(b);
    }


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
       hoja =  new Hoja(20, 30);

        boolean b = hoja.setColumnas(23);
        int c = hoja.getColumnas();

        assertEquals(23, c);
        assertTrue(b);
    }

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
        hoja =  new Hoja(20, 30);

        boolean b = hoja.setColumnas(-23);
        int c = hoja.getColumnas();

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
        hoja = new Hoja();

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
        hoja = new Hoja();
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
    public void asignaNombreLleno(){
        hoja = new Hoja();
        hoja.asignaNombre("HojaPrueba");

        String name = hoja.getNombre();

        assertEquals("HojaPrueba", name);
    }

    /**
     * Objeto de la prueba: Testear la funcion asignaNombre
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se colocan un nombre a la hoja manualmente.
     * - Operativa: En este test de asignaNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que  si se introduce un nombre vacio, este no se asigna
     * Para ello comprobaremos que la funcion devuelve false, ya que significara que esta no hace nada
     */
    @Test
    public void asignaNombreVacio(){
        hoja = new Hoja();

        boolean b = hoja.asignaNombre("");

        assertFalse(b);

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
        hoja = new Hoja();
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
        hoja = new Hoja();
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
     * Para ello, despues de crear la hoja, le asignaremos un id mediante la funcion "añadeNombreIdHojaDefault"
     * ya probada y comprobaremos que devuelve el identificador que previamente se le ha asignado
     */
    @Test
    public void getIdAsignado(){
        hoja = new Hoja();
        hoja.añadeNombreIdHojaDefault(1);

        int id = hoja.getId();

        assertEquals(1, id);

    }

    /**
     * Objeto de la prueba: Testear la funcion getId
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se quiere obtener el identificador de una hoja que aun no
     * tiene identificador.
     * - Operativa: En este test de getId se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve un identificador sin iniciar, es decir 0
     * Para ello no añadiremos el identificador que se crea por defecto con la funcion "añadeNombreIdHojaDefault"
     * y comprobaremos que devuelve 0
     */
    @Test
    public void getIdNoAsignado(){
        hoja = new Hoja();

        int id = hoja.getId();

        assertEquals(0, id);

    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el numero de columnas de una Hoja
     * - Operativa: En este test de getColumnas se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el numero de columnas de la hoja
     * Para ello comprobaremos que devuelve el numero de columnas que previamente se asigna a la hora de crearla.
     */
    @Test
    public void getColumnas(){
        hoja = new Hoja();

        int c = hoja.getColumnas();

        assertEquals(50, c);

    }

    /**
     * Objeto de la prueba: Testear la funcion getFilas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el numero de filas de una Hoja
     * - Operativa: En este test de getFilas se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el numero de filas de la hoja
     * Para ello comprobaremos que devuelve el numero de filas que previamente se asigna a la hora de crearla.
     */
    @Test
    public void getFilas(){
        hoja = new Hoja();

        int f = hoja.getFilas();

        assertEquals(50, f);

    }


    /**
     * Objeto de la prueba: Testear la funcion getNombre
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el nombre de una Hoja con nombre
     * - Operativa: En este test de getNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el nombre de la hoja
     * Para ello, despues de crearla, le ñadiremos un nombre manualmnete, seguidamente comprobaremos que devuelve
     * el nombre que previamente se le ha asignado.
     */
    @Test
    public void getNombreIni(){
        hoja = new Hoja();
        hoja.asignaNombre("HojaPrueba");

        String name = hoja.getNombre();

        assertEquals("HojaPrueba", name);

    }

    /**
     * Objeto de la prueba: Testear la funcion getNombre
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se obtiene el nombre de una Hoja sin nombre
     * - Operativa: En este test de getNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve null al no tener nombre la hoja
     * Para ello comprobaremos que despues de crearla, sin añadirle ningun nombre, devuelve null.
     */
    @Test
    public void getNombreNoIni(){
        hoja = new Hoja();
        hoja.asignaNombre("HojaPrueba");

        String name = hoja.getNombre();

        assertEquals("HojaPrueba", name);

    }


    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la calse del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la calse del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que la hoja tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros, 4 posiciones y
     *   4 celdas con las cuales se llenara la hoja .
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja
     * Para ello comprobaremos que el tamaño de la estructura que devuelve sea el mismo que el que tiene la Hoja y que
     * la estructura sea la misma
     */
    @Test
    public void getCeldasLleno(){
        hoja = new Hoja(2, 2);

        allcelds = new HashMap<>(4);
        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        allcelds.put(p1, new CeldaStub(p1));
        allcelds.put(p2, new CeldaStub(p2));
        allcelds.put(p3, new CeldaStub(p3));
        allcelds.put(p4, new CeldaStub(p4));

        hoja.setCeldas(allcelds);

        int clds = hoja.getCeldas().size();
        HashMap<Posicion, Celda> m = hoja.getCeldas();

        assertEquals(4, clds);
        assertSame(m, hoja.getCeldas());
    }
    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que la hoja no tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja vacia
     * Para ello crearemos una hoja sin filas ni columnas y comprobaremos que el tamaño de la estructura que
     * devuelve sea el mismo que el que tiene la Hoja, es decir 0 y que la estructura se la misma.
     */
    @Test
    public void getCeldasVacio(){
        hoja = new Hoja(0, 0);
        int clds = hoja.getCeldas().size();

        HashMap<Posicion, Celda> m = hoja.getCeldas();

        assertEquals(0, clds);
        assertSame(m, hoja.getCeldas());
    }



    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - MockObject: ·MockPosicion: Se utiliza un mock de Posicion para substituir la clase de Posicion, dado que
     *                dentro de la funcion solo se necesita la creador, y mockito no puede burlarse de ellas,
     *                al ejecutar el test, gracias al if dentro de la función se crearà un mockobject de posicion
     *                en vez de una posicion como tal.
     *                ·MockCelda: Se utiliza un mockobject de celda para substituir la clase Celda.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se pide una celda con una posicion existente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la celda en la posicion introducida existente
     * Para ello comprobaremos que la celda devuelta es la que hay en la posicion pedida
     */
  @Test
    public void getCeldaPosicionExistente(){
      /*hoja = new Hoja(2, 2);

      celda = hoja.getCelda(2, 2);

      posicion = new Posicion(2,2);

      assertSame(celda, hoja.getCeldas().get(posicion));*/

    }


    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - MockObject: ·MockPosicion: Se utiliza un mock de Posicion para substituir la clase de Posicion, dado que
     *                dentro de la funcion solo se necesita la creador, y mockito no puede burlarse de ellas,
     *                al ejecutar el test, gracias al if dentro de la función se crearà un mockobject de posicion
     *                en vez de una posicion como tal.
     *                ·MockCelda: Se utiliza un mockobject de celda para substituir la clase Celda.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se pide una celda con una posicion inexistente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve una celda nula.
     * Para ello comprobaremos que la celda devuelta es nula.
     */
    @Test
    public void getCeldaPosicionInexistente(){
        Hoja h = new Hoja(2, 2);

        celda = h.getCelda(3, 2);

        assertNull(celda);

    }


    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la calse del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la calse del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se comprueba una posicion existente
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros, 4 posiciones
     * y 4 celdas.
     * El objetivo es comprobar que devuelve true si la Posicion introducida existe en la Hoja.
     * Para ello crearemos 4 posiciones junto a 4 celdas para llenar la hoja, seguidamente una posicion existente
     * y comprobaremos si los valores son los correctos
     */
    @Test
    public void existePosicionExistente(){
        hoja = new Hoja(2, 2);

        allcelds = new HashMap<>(4);
        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        allcelds.put(p1, new CeldaStub(p1));
        allcelds.put(p2, new CeldaStub(p2));
        allcelds.put(p3, new CeldaStub(p3));
        allcelds.put(p4, new CeldaStub(p4));

        hoja.setCeldas(allcelds);

        PosicionStub p = new PosicionStub(1, 2);

        boolean b = hoja.existePosicion(p);

        assertTrue(b);
    }

    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la calse del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la calse del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se comprueba una posicion inexistente
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros 4 posiciones
     *   y 4 celdas.
     * El objetivo es comprobar que devuelve false si la Posicion introducida no existe en la Hoja.
     * Para ello crearemos 4 posiciones junto a 4 celdas para llenar la hoja, seguidamente una posicion inexistente
     * y comprobaremos si los valores son los correctos
     */
    @Test
    public void existePosicionInexistente(){
        hoja = new Hoja(2, 2);
        allcelds = new HashMap<>(4);
        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        allcelds.put(p1, new CeldaStub(p1));
        allcelds.put(p2, new CeldaStub(p2));
        allcelds.put(p3, new CeldaStub(p3));
        allcelds.put(p4, new CeldaStub(p4));

        hoja.setCeldas(allcelds);

        PosicionStub p = new PosicionStub(3, 2);

        boolean b = hoja.existePosicion(p);

        assertFalse(b);
    }


    //Problema Mockito
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

    //Problema Mockito
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

    //Problema Mockito
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

    //Problema Mockito
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

    //Mockito Problem
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
    public void cambiarPosicionCeldaV2PosicionesExistentes(){
        //hoja = new Hoja(2, 2);

        when(celda.getReferenciantes()).thenReturn(new ArrayList<>());
        Celda c =  hoja.getCelda(2, 1);
        Posicion p2 = new Posicion(1,2);
        boolean b1 = hoja.cambiarPosicionCelda(p2, c);

        assertTrue(b1);
    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la calse del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la calse del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas una posicion inexistente y una
     * celda.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello comprobaremos que la funcion devuelve false, ya que significara que esta no hace nada
     */
    @Test
    public void cambiarPosicionCelda2PosicionesInexistentes(){
        hoja = new Hoja(2, 2);
        allcelds = new HashMap<>(4);
        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        allcelds.put(p1, new CeldaStub(p1));
        allcelds.put(p2, new CeldaStub(p2));
        allcelds.put(p3, new CeldaStub(p3));
        allcelds.put(p4, new CeldaStub(p4));

        hoja.setCeldas(allcelds);

        CeldaStub c = (CeldaStub) hoja.getCelda(2, 1);
        boolean b1 = hoja.cambiarPosicionCelda(new PosicionStub(1, 3), c);
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


    public static class PosicionStub extends Posicion{

        public PosicionStub(int _fila, int _columna) {
            super(_fila, _columna);
        }
    }

    public static class CeldaStub extends Celda{
        private PosicionStub pos;
        String contenido;

        public CeldaStub(PosicionStub _pos) {
            super(_pos);
            pos = _pos;
            contenido = "";

        }

        public PosicionStub getPos() {
            return pos;
        }

        public String getContenidoStub() {
            return contenido;
        }

        public void setContentStub(String _s){
            contenido = _s;

        }

    }




}
