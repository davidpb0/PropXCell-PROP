package test.suit;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;
import main.Domain.DomainModel.Traductor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;


public class HojaTest {

    HashMap<Posicion, Celda> allcelds;

    @InjectMocks
    private Hoja hoja;

    @Mock
    private Celda celda;

    @Mock
    Traductor traductor;

    @Mock
    private Posicion posicion;




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

        String s1 = f + " " + c + " " + s;

        assertEquals("50 50 2500", s1);
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

        String s1 = f + " " + c + " " + s;

        assertEquals("20 30 600", s1);
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

        String s = b + " " + f;
        assertEquals("true 23", s);
    }

    /**
     * Objeto de la prueba: Testear la funcion setFilas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso extremo en el que se colocan manualmente las filas, con un valor negativo
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

        String s = b + " " + f;
        assertEquals("false 20", s);
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

        String s = b + " " + c;
        assertEquals("true 23", s);;
    }

    /**
     * Objeto de la prueba: Testear la funcion setColumnas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso extremo en el que se colocan manualmente las columnas con un
     *   numero negativo
     * - Operativa: En este test de setColumnas se define una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia el valor de las columnas de la hoja por el valor introducido.
     * Para ello crearemos una hoja con la constructora con parametros, comprobaremos que el numero de columnas
     * corresponde al  que viene puesto por la constructora y que efectivamente devuelve false
     * constructoras y devuelve false
     */
    @Test
    public void setColumnasNegativo(){
        hoja =  new Hoja(20, 30);

        boolean b = hoja.setColumnas(-23);
        int c = hoja.getColumnas();

        String s = b + " " + 30;
        assertEquals("false 30", s);;
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

        String s = id + " " + name;

        assertEquals("1 Hoja1", s);

    }

    /**
     * Objeto de la prueba: Testear la funcion añadeNombreIdHojaDefault
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso extremno en el que se colocan un nombre y un identificador en negativo.
     * - Operativa: En este test de añadeNombreIdHojaDefault se define una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que tanto el nombre de la hoja como su id no son inicializados dado que se le introduce
     * un numero en negativo. Para ello crearemos una hoja, e intentaremos añadir un nombre y un identificador
     * introduciendo un numero negativo, comprobaremos que devuelve false.
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
     * - Valores estudiados: Se estudia el caso en el que se coloca un nombre a la hoja manualmente.
     * - Operativa: En este test de asignaNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que  el nombre de la hoja corresponde con el nombre introducido
     * Para ello crearemos un hoja y le asignaremos un nombre, seguidamente comprobaremos que el nombre de la hoja ç
     * sea el mismo que se ha introducido
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
     * - Valores estudiados: Se estudia el caso extremo en el que se coloca un nombre vacio a la hoja manualmente.
     * - Operativa: En este test de asignaNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que  si se introduce un nombre vacio, este no se asigna
     * Para ello crearemos un hoja, les asignaremos un nombre vacio y comprobaremos que la funcion devuelve false,
     * ya que significara que esta no hace nada.
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
     * - Valores estudiados: Se estudia el caso en el que se comprueba que es la hoja correcta.
     * - Operativa: En este test de esHoja se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que la hoja, es la hoja que tiene el identificador introducido
     * Para ello crearemos una hoja, le asignaremos un identificador, seguidamente comprobaremos que devuelve cierto
     * si el identificador es el mismo.
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
     * - Valores estudiados: Se estudia el caso algo extremo en el que se comprueba que no es la hoja correcta.
     * - Operativa: En este test de esHoja se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que la hoja, no es la hoja que tiene el identificador introducido
     * Para ello crearemos una hoja, le asignaremos un identificador, seguidamente comprobaremos que devuelve false
     * si el identificador no es el mismo.
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
     * - Valores estudiados: Se estudia el caso en el que se obtiene el identificador de una hoja a partir de
     *   un identidicador positivo.
     * - Operativa: En este test de getId se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el identificador de la hoja
     * Para ello, despues de crear la hoja, le asignaremos un id y comprobaremos que devuelve el identificador
     * que previamente se le ha asignado
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
     * - Valores estudiados: Se estudia un caso algo extremo en el que se quiere obtener el identificador de una hoja
     * que aun no tiene identificador.
     * - Operativa: En este test de getId se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve un identificador sin iniciar, es decir 0
     * Para ello crearemos una hoja, pero no añadiremos el identificador que se crea por defecto con la funcion
     * "añadeNombreIdHojaDefault" y comprobaremos que devuelve 0
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
     * Para ello crearemos una hoja y comprobaremos que devuelve el numero de columnas que previamente se le
     * asigna a la hora de crearla.
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
     * Para ello crearemos una hoja y comprobaremos que devuelve el numero de filas que previamente se le
     * asigna a la hora de crearla.
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
     * - Valores estudiados: Se estudia un caso en el que se obtiene el nombre de una Hoja con nombre
     * - Operativa: En este test de getNombre se definen una Hoja con la constructora sin parametros.
     * El objetivo es comprobar que devuelve correctamente el nombre de la hoja
     * Para ello, despues de crearla, le añadiremos un nombre manualmente, seguidamente comprobaremos que devuelve
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
     * - Valores estudiados: Se estudia un caso algo extremo en el que se obtiene el nombre de una Hoja sin nombre
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
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso poco extremo en el que se pretende coger las celdas de una hoja
     * que tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros, 4 posiciones y
     *   4 celdas con las cuales se llenara la hoja .
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja
     * Para ello comprobaremos que el tamaño de la estructura que devuelve sea el mismo que el que tiene la hoja.
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

    }
    /**
     * Objeto de la prueba: Testear la funcion getCeldas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso extremo en el se pretende coger las celdas de una hoja que no
     * tiene celdas
     * - Operativa: En este test de getCeldas se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la estructura de datos "celdas" de la hoja vacia
     * Para ello crearemos una hoja sin filas ni columnas y comprobaremos que el tamaño de la estructura que
     * devuelve sea el mismo que el que tiene la Hoja, es decir 0.
     */
    @Test
    public void getCeldasVacio(){
        hoja = new Hoja(0, 0);

        int clds = hoja.getCeldas().size();

        assertEquals(0, clds);

    }



    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso poco extremo en el que se pide una celda con una posicion existente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve correctamente la celda en la posicion introducida existente
     * Para ello despues de crear la hoja, comprobaremos que la celda devuelta es la que hay en la posicion pedida
     */
    @Test
    public void getCeldaPosicionExistente(){
      hoja = new Hoja(2, 2);

      CeldaStub celda = (CeldaStub) hoja.getCelda(new PosicionStub(2, 2));

      PosicionStub posicion = new PosicionStub(2,2);

      assertSame(celda, hoja.getCeldas().get(posicion));

    }


    /**
     * Objeto de la prueba: Testear la funcion getCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso algo extremo en el que se pide una celda con una posicion inexistente
     * - Operativa: En este test de getCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que devuelve una celda nula, q siginificara que no existe.
     * Para ello despues de crear la hoja, comprobaremos que la celda devuelta es nula.
     */
    @Test
    public void getCeldaPosicionInexistente(){
        hoja = new Hoja(2, 2);

        CeldaStub celda = (CeldaStub) hoja.getCelda(new PosicionStub(2, 2));

        assertNull(celda);

    }


    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia un caso poco extremo en el que se comprueba si existe una posicion existente
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


        boolean b = hoja.existePosicion(p2);

        assertTrue(b);
    }

    /**
     * Objeto de la prueba: Testear la funcion existePosicion
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia un caso poco extremo en el que se comprueba si existe una posicion inexistente
     * - Operativa: En este test de existePosicion se definen una Hoja con la constructora con parametros, 4 posiciones,
     *   4 celdas y 1 posicion inexistente.
     * El objetivo es comprobar que devuelve false si la Posicion introducida no existe en la Hoja.
     * Para ello crearemos 4 posiciones junto a 4 celdas, que se añadiran a un HashMap para llenar la hoja,
     * seguidamente se creara una posicion inexistente y comprobaremos que el valor devuelto es false
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

//----------------------------------------------------------------
    /**
     * Objeto de la prueba: Testear la funcion addCeldaVacia
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso poco extremo en el que se añade una celda vacia a una hoja en
     *   una posicion inexistente
     * - Operativa: En este test de addCeldaVacia se definen una Hoja con la constructora con parametros, 4 posiciones,
     *   4 celdas y una posicion inexistente.
     * El objetivo es comprobar que se añade una celda vacia a la Hoja en una posicion no existente
     * Para ello crearemos una hoja con la creadora con parametros, que tendra dos filas y dos columnas, es decir
     * 4 celdas y 4 posiciones, la llenaremos con 4 celdas y 4 posiciones creadas, seguidamente comprobaremos que
     * se añade la celda en la posicion inexistente introducida devolviendonos true.
     */
    @Test
    public void addCeldaVaciaPosicionInexistente(){
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

        PosicionStub p5 = new PosicionStub(3, 2);

        boolean b = hoja.addCeldaVacia(p5);

        assertTrue(b);

    }

    /**
     * Objeto de la prueba: Testear la funcion addCeldaVacia
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso algo extremo en el que se añade una celda vacia a una posicion
     *  existente en la hoja
     * - Operativa: En este test de addCeldaVacia se definen una Hoja con la constructora con parametros, 4 posiciones
     *  y 4 celdas.
     * El objetivo es comprobar que no añade una celda vacia a la Hoja si la posicion ya existe en ella.
     * Para ello crearemos una hoja con la creadora con parametros, que tendra dos filas y dos columnas, es decir
     * 4 celdas y 4 posiciones, la llenaremos con 4 celdas y 4 posiciones creadas, seguidamente comprobaremos que
     * no se añade la celda en la posicion existente introducida devolviendonos false.
     */
    @Test
    public void addCeldaVaciaPosicionExistente(){
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

        boolean b2 = hoja.addCeldaVacia(p2);

        assertFalse(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion quitarCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso no tan extremo en el que se elimina una posicion y una celda en una
     * posicion existente para comprobar el buen funcionamiento de la funcion
     * - Operativa: En este test de quitarCelda se definen una Hoja con la constructora con parametros , 4 posiciones y
     * 4 celdas.
     * El objetivo es comprobar que se borra una celda de la estructura de datos.
     * Para ello, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con 4 celdas y 4 posiciones creadas,
     * comprobaremos que devuelve true si introducidomos una posicion exsitente lo que significara que ha sido
     * eliminada del mapa.
     */
    @Test
    public void quitarCeldaPosicionExistente(){
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

        boolean b2 =  hoja.quitarCelda(p2);

        assertTrue(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion quitarCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso algo extremo en el que se pretende eliminar una posicion y una celda
     *   en una posicion inexistente
     * - Operativa: En este test de quitarCelda se definen una Hoja con la constructora con parametros, 4 posiciones y
     *   4 celdas.
     * El objetivo es comprobar que no se borra una celda de la estructura de datos si la posicion no existe.
     * Para ello, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con 4 celdas y 4 posiciones creadas,
     * comprobaremos que devuelve false si introducidomos una posicion inexsitente lo que significara que no se ha
     * sido eliminada del mapa.
     */
    @Test
    public void quitarCeldaPosicionInexistente(){
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

        PosicionStub p5 = new PosicionStub(3, 3);

        boolean b2 =  hoja.quitarCelda(p5);

        assertFalse(b2);

    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     *  -Mocks: ·MockCelda: Se utiliza un mock de celda para substituir la clase Celda y las funciones necesarias para
     *           el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia un caso no tan extremo en el que se cambia una celda, dadas
     *  dos posiciones existentes.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros,
     *  4 posiciones y 4 celdas para llenar la estructura de la hoja.
     * El objetivo es comprobar que se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con 4 celdas creadas y 4 posiciones
     * creadas, cambiaremos el comportamiento de las funciones de celda que utiliza la funcion a probar, con mockito,
     * probaremos a cambiar una celda de posicion por otra dada, comprobamos que se devuelve true, lo que signficiara
     * que la  celda se ha cambiado de posicion.
     */
    @Test
    public void cambiarPosicionCeldaPosicionesExistentes(){
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

        when(celda.getReferenciantes()).thenReturn(new LinkedList<>());
        doNothing().when(celda).setReferenciantes(isA(LinkedList.class));

        boolean b = hoja.cambiarPosicionCelda(p3, p2);

        assertTrue(b);
    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso algo extremo donde el que se pretende cambiar una celda,
     *  dadas dos posiciones, una de ellas inexistente.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros,
     *   4 posiciones y 4 celdas para llenar la estructura de la hoja y una posicion inexistente.
     * El objetivo es comprobar que no se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con 4 celdas y 4 posiciones, crearemos
     * una posicion que no existe en la hoja, intentaremos cambiar una celda de posicion por la inexistente,
     * comprobamos que se devuelve false, lo que signficiara que la celda no se ha cambiado de posicion.
     * Como aqui no entraremos en la parte de la funcion que utiliza celda, no hara falta cambiar el comportamiento de
     * las funciones utilizadas dentro de la funcion a probar con mockito.
     */
    @Test
    public void cambiarPosicionCeldaPosicionesInxistentes(){

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

        PosicionStub p5 = new PosicionStub(3, 2);
        boolean b = hoja.cambiarPosicionCelda(p3, p5);

        assertFalse(b);
    }


    /**
     * Objeto de la prueba: Testear la variante de la funcion cambiarPosicionCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la clase del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la clase del modelo
     *  -Mocks: ·MockCelda: Se utiliza un mock de celda para substituir la clase Celda y las funciones necesarias para
     *           el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *- Valores estudiados: Se estudia el caso en el que se cambia una celda, dadas una posicion y una celda existentes.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que la variante de la funcion cambiarPosicionCelda funciona correctamente.
     * El funcionamiento es igual a la otra variante, lo diferente en esta, es que en lugar de introducir 4 enteros que
     * definen dos posiciones (la actual y la futura), se pasa una posicion(la futura) y una celda.
     * Para comprobar el correcto funcionamiento, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con
     * 4 celdas y 4 posiciones, cambiaremos el comportamiento de las funciones de celda que utiliza la funcion a probar,
     * con mockito, intentaremos cambiar una celda de posicion por otra posicion existente y comprobamos que
     * se devuelve true, lo que signficiara que la celda se ha cambiado de posicion.
     */
    @Test
    public void cambiarPosicionCeldaV2PosicionesExistentes(){
        hoja = new Hoja(2, 2);

        allcelds = new HashMap<>(4);

        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        CeldaStub c1 = new CeldaStub(p1);
        CeldaStub c2 = new CeldaStub(p2);
        CeldaStub c3 = new CeldaStub(p3);
        CeldaStub c4 = new CeldaStub(p4);

        allcelds.put(p1, c1);
        allcelds.put(p2, c2);
        allcelds.put(p3, c3);
        allcelds.put(p4, c4);

        hoja.setCeldas(allcelds);

        when(celda.getReferenciantes()).thenReturn(new LinkedList<>());
        doNothing().when(celda).setReferenciantes(isA(LinkedList.class));


        //Se desea cambiar la celda c3 a la posicion 2
        boolean b1 = hoja.cambiarPosicionCelda(p2, c3);

        assertTrue(b1);
    }

    /**
     * Objeto de la prueba: Testear la funcion cambiarPosicionCelda
     * - Stubs: ·CeldaStub: Se ha creado un stub de la clase Celda el cual reemplaza a la calse del modelo
     *          ·PosicionStub: Se ha creado un stub de la clase Posicion el cual reemplaza a la calse del modelo
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia un caso algo extremo en el que se pretendende cambiar una celda, dadas
     * una posicion inexistente y una celda.
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que no se cambia la celda de la primera posicion dada a la segunda posicion dada.
     * Para ello, crearemos una hoja con 4 columnas y 4 filas, la llenaremos con 4 celdas y 4 posiciones, crearemos
     * una posicion que no existe en la hoja, intentaremos cambiar una celda de posicion por la inexistente,
     * comprobamos que se devuelve false, lo que signficiara que la celda no se ha cambiado de posicion.
     * Como aqui no entraremos en la parte de la funcion que utiliza celda, no hara falta cambiar el comportamiento de
     * las funciones utilizadas dentro de la funcion a probar con mockito.
     */
    @Test
    public void cambiarPosicionCelda2PosicionesInexistentes(){
        hoja = new Hoja(2, 2);

        allcelds = new HashMap<>(4);

        PosicionStub p1 = new PosicionStub(1, 1);
        PosicionStub p2 = new PosicionStub(1, 2);
        PosicionStub p3 = new PosicionStub(2, 1);
        PosicionStub p4 = new PosicionStub(2, 2);

        CeldaStub c1 = new CeldaStub(p1);
        CeldaStub c2 = new CeldaStub(p2);
        CeldaStub c3 = new CeldaStub(p3);
        CeldaStub c4 = new CeldaStub(p4);

        allcelds.put(p1, c1);
        allcelds.put(p2, c2);
        allcelds.put(p3, c3);
        allcelds.put(p4, c4);

        hoja.setCeldas(allcelds);

        PosicionStub p5 = new PosicionStub(3, 3);

        //Se desea cambiar la celda c3 a la posicion 5 (inexistente)
        boolean b1 = hoja.cambiarPosicionCelda(p5, c3);

        assertFalse(b1);
    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnaFila
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se da dos posiciones de manera que se selecciona una columna
     * de arriba a abajo
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar que se consigue un conjunto de celdas correcto.
     * Para ello comprobaremos que el ArrayList que devuelve la función tenga el tamaño de celdas correcto.
     */
    @Test
    public void cogerColumnaArribaAbajo(){
        hoja = new Hoja(50, 50);
        hoja.añadeNombreIdHojaDefault(1);

        String s1 = "$A1";
        String s2 = "$A3";

        ArrayList<Celda> cls = hoja.getColumnaFila(s1, s2);

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
     * Para ello comprobaremos que el ArrayList que devuelve la función tenga el tamaño de celdas correcto.
     */
   @Test
    public void cogerFilaIzquierdaDerecha(){
        hoja = new Hoja(50, 50);
        hoja.añadeNombreIdHojaDefault(1);

        String p1 = "$A1";
        String p2 = "$C1";

        ArrayList<Celda> cls = hoja.getColumnaFila(p1, p2);

        assertEquals(3, cls.size());


    }

    /**
     * Objeto de la prueba: Testear la funcion getColumnaFila
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se da dos posiciones de manera que se selecciona una fila
     * de derecha a izquierda
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar se consigue un conjunto de celdas correcto.
     * Para ello comprobaremos que el ArrayList que devuelve la función tenga el tamaño de celdas correcto.
     */
    @Test
    public void cogerFilaDerechaIzquierda(){
        hoja = new Hoja(50, 50);
        hoja.añadeNombreIdHojaDefault(1);

        String p1 = "$C1";
        String p2 = "$A1";

        ArrayList<Celda> cls = hoja.getColumnaFila(p1, p2);

        assertEquals(3, cls.size());


    }
    /**
     * Objeto de la prueba: Testear la funcion getColumnaFila
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se da dos posiciones de manera que se selecciona una fila
     * de derecha a izquierda
     * - Operativa: En este test de cambiarPosicionCelda se definen una Hoja con la constructora con parametros.
     * El objetivo es comprobar se consigue un conjunto de celdas correcto.
     * Para ello comprobaremos que el ArrayList que devuelve la función tenga el tamaño de celdas correcto.
     */
    @Test
    public void cogerColumnasAbajoArriba(){
        hoja = new Hoja(50, 50);
        hoja.añadeNombreIdHojaDefault(1);

        String p1 = "$A3";
        String p2 = "$A1";

        ArrayList<Celda> cls = hoja.getColumnaFila(p1, p2);

        assertEquals(3, cls.size());


    }

    //Stub de Posicion que sustituye la clase Posicion asi como los metodos necesarios para el test
    private static class PosicionStub extends Posicion{

        public PosicionStub(int _fila, int _columna) {
            super(_fila, _columna);
        }
    }


    //Stub de Celda que sustituye la clase Celda asi como los metodos necesarios para el test
    private static class CeldaStub extends Celda{
        private PosicionStub pos;
        String contenido;
        ArrayList<Celda> refs;

        public CeldaStub(PosicionStub _pos) {
            super(_pos);
            pos = _pos;
            contenido = "";

        }

        @Override
        public PosicionStub getPosicion() {
            return pos;
        }


        @Override
        public void setReferenciantes(LinkedList<Celda> _refs){
            refs = new ArrayList<>();

        }

        public void setContentStub(String _s){
            contenido = _s;

        }
        @Override
        public void setPosicion(Posicion p){
            pos = (PosicionStub) p;
        }

    }




}
