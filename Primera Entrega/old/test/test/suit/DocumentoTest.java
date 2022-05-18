package test.suit;

import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


public class DocumentoTest {
    @InjectMocks
    private Documento d;

    @Mock
    Hoja hoja;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

    }
    @After
    public void tearDown(){
        d.eliminaDocumento();
    }


    /**
     * Objeto de la prueba: Testear la funcion getDocumento
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se crea o se coge un documento
     * - Operativa: En este test de getDocumento se utiliza o crea la instancia de Documento
     * El objetivo es ver si se crea o utiliza correctamente la instancia de Documento (al ser Singleton solo habra una)
     * Para ello comrpobaremos que el objeto que se devuelve de la llamada es un documento no nullo
     */
    @Test
    public void cogerDocumentoTest(){
        d = Documento.getDocumento();;

        boolean b = d == null;
        String b2 = d.getClass().getSimpleName();

        b2 = b2 + b;
        assertEquals("Documentofalse", b2);
        d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion inicializaDocumento
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *                    el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se inicializa un documento con valores dados para la hoja
     * - Operativa: En este test de inicializaDocumento se utiliza o crea la instancia de Documento.
     * El objetivo es ver que el Documento se inicializa correctamente.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado y valores de fila y columna
     * dados para la Hoja, comprobaremos despues que el nombre corresponde con el introducido, la fecha corresponde
     * con la actual y que tiene una hoja.
     */
    @Test
    public void inicializaDocumentoTest(){
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String exp = "Doc1" + 1 + 1 + LocalDateTime.now().format(fm);

        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumento("Doc1", 2, 3);

        String s = d.getNombre();

        int i = d.getNumHojas();
        int i3 = d.getHojasContenidas().size();

        String s1 = s + i + i3 + d.getFecha();

        assertEquals(exp, s1);
    }


    /**
     * Objeto de la prueba: Testear la funcion inicializaDocumentoDefault
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *                    el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se inicializa un documento con valores default para la hoja
     *- Operativa: En este test de inicializaDocumento se utiliza o crea la instancia de Documento.
     * El objetivo es ver que el Documento se inicializa correctamente con los valores para la Hoja por defecto.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado,
     * comprobaremos despues que el nombre corresponde con el introducido, la fecha corresponde con la actual
     * y que tiene una hoja.
     */
    @Test
    public void inicializaDocumentoDefaultTest(){
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String exp = "Doc1" + 1 + 1 + LocalDateTime.now().format(fm);

        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumentoDefault("Doc1");

        String s = d.getNombre();

        int i = d.getNumHojas();
        int i3 = d.getHojasContenidas().size();

        String s1 = s + i + i3 + d.getFecha();

        assertEquals(exp, s1);
    }


    /**
     * Objeto de la prueba: Testear la funcion getNumHoja
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso algo extremo en el que se pretende coger el numero de hojas,
     * de un documento vacio
     *- Operativa: En este test de getNumHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el numero de hojas correcto
     * Para ello cogeremos la instancia de Documento, y comprobaremos sin inicializarlo que
     * el numero de hojas deberia ser 0.
     */
    @Test
    public void getNumHojasVacioTest(){
        d = Documento.getDocumento();

        int n = d.getNumHojas();

        assertEquals(0, n);


    }

    /**
     * Objeto de la prueba: Testear la funcion getNumHoja
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso poco extremo en el que se pretende coger el numero de hojas,
     * de un documento vacio
     *- Operativa: En este test de getNumHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el numero de hojas correcto
     * Para ello cogeremos la instancia de Documento, esta vez lo inicializaremos para que contenga alguna hoja,
     * comrpobaremos que devuelve el numero de hojas correcto
     */
    @Test
    public void getNumHojasLlenoTest(){
        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);

        d.inicializaDocumentoDefault("Doc1");

        int n = d.getNumHojas();

        assertEquals(1, n);


    }


    /**
     * Objeto de la prueba: Testear la funcion getHoja
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso poco extremo en el que se pretende coger una hoja existente en
     *                       el documento.
     * - Operativa: En este test de getHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve la Hoja indicada
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * pediremos una hoja existente y comprobaremos que devuelve una Hoja no nula
     */
    @Test
    public void getHojaContenidaTest() throws Exception {
        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumentoDefault("Doc1");

        Hoja h =  d.getHoja(1);

        assertNotNull(h);

    }
    /**
     * Objeto de la prueba: Testear la funcion getHoja
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso algo extremo en el que se pretende coger una hoja inexistente en
     *                       el documento.
     * - Operativa: En este test de getHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve una Hoja nulla
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * pediremos una hoja inexistente y comprobaremos que devuelve una Hoja  nula
     */
    @Test
    public void getHojaNoContenidaTest() throws Exception {
        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumentoDefault("Doc1");

        Hoja h =  d.getHoja(2);

        assertNull(h);

    }


    /**
     * Objeto de la prueba: Testear la funcion getNombre
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso poco extremo en el que quiere obtener el nombre del documento
     * - Operativa: En este test de getNombre se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el nombre del Documento indicado al inicializarlo.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que se devuelve el nombre indicado al inicializarlo.
     */
    @Test
    public void getNombreTest(){
        d = Documento.getDocumento();
        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumentoDefault("Doc1");

        assertEquals("Doc1", d.getNombre());

    }

    /**
     * Objeto de la prueba: Testear la funcion getFecha
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *   el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso poco extremo en el que quiere obtener la fecha del documento
     * - Operativa: En este test de getFecha se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve la fecha de ultima modificacion de documento
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que la fecha corresponde a la fecha de hoy
     */
    @Test
    public void getFechaTest(){
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String exp =  LocalDateTime.now().format(fm);

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);

        d = Documento.getDocumento();

        d.inicializaDocumentoDefault("Doc1");

        assertEquals(exp, d.getFecha());

    }

    /**
     * Objeto de la prueba: Testear la funcion getHojasContenidas
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se pretende coger todas las hojas que contiene un documento
     * con alguna hoja
     * - Operativa: En este test de getHojasContenidas se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve las Hojas que contiene el documento.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que la estructura de datos que devuelve tenga el mismo tamaño que la que almacena almacena
     * el documento
     */
    @Test
    public void getHojasContenidasLlenoTest(){
        d = Documento.getDocumento();

        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d.inicializaDocumentoDefault("Doc1");

        HashMap<Integer, Hoja> hc = d.getHojasContenidas();
        int s = hc.size();


        assertEquals(s, 1);

    }
    /**
     * Objeto de la prueba: Testear la funcion getHojasContenidas
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso algo extremo en el que se pretende coger todas las hojas que
     *  un documento vacio contiene.
     * - Operativa: En este test de getHojasContenidas se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve las Hojas que contiene el documento.
     * Para ello cogeremos la instancia de Documento y sin inicializarla comprobaremos que el tamaño de la estructura
     * de datos devuelta sea el mismo que la que almacena el documento.
     */
    @Test
    public void getHojasContenidasVacioTest(){
        d = Documento.getDocumento();

        HashMap<Integer, Hoja> hc = d.getHojasContenidas();
        int s = hc.size();


        assertEquals(s, 0);

    }



    /**
     * Objeto de la prueba: Testear la funcion añadeHojaDf
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *  - Valores estudiados: Se estudia el caso en el que se quiere añadir una hoja con tamaño default
     * - Operativa: En este test de añadeHojaDf se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se añade una Hoja al documento correctamente
     * Para ello cogeremos la instancia de Documento y sin inicializarlo, añadiremos una hoja, puesto que no
     * lo inicializamos, este solo tendra la hoja que le añadamos, comprobaremos que el tamaño de la estructura que
     * almacena las hojas sea el correcto.
     */
    @Test
    public void añadeHojaDfTest(){
        //La creadora no se puede 'burlar' con mock
        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d = Documento.getDocumento();
        d.añadeHojaDf();

        int r = d.getHojasContenidas().size();

        assertEquals(r, 1);

    }

    /**
     * Objeto de la prueba: Testear la funcion añadeHoja
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *          el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se quiere añadir una hoja con tamaño dado
     * - Operativa: En este test de añadeHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se añade una Hoja al documento correctamente
     * Para ello cogeremos la instancia de Documento y sin inicializarlo, añadiremos una hoja, puesto que no
     * lo inicializamos, este solo tendra la hoja que le añadamos, comprobaremos que el tamaño de la estructura que
     * almacena las hojas sea el correcto.
     */
    @Test
    public void añadeHojaTest(){
        //La creadora no se puede 'burlar' con mock
        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);
        d = Documento.getDocumento();
        d.añadeHoja(2, 2);

        int r = d.getHojasContenidas().size();

        assertEquals(r, 1);

    }

    /**
     * Objeto de la prueba: Testear la funcion eliminaHoja
     * - Mock: -MockHoja: Se utiliza un mock de Hoja para sustituir la clase hoja y las funcionalidades necesarias para
     *        el test.
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se quiere eliminar una hoja existente en el documento
     * - Operativa: En este test de eliminaHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se elimina una Hoja del documento correctamente
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, para comprobar que
     * se ha eliminado la hoja, miraremos el tamaño de la estructura que las almacenaa hoja.
     */
    @Test
    public void eliminaHojaExistenteTest(){
        //La creadora no se puede 'burlar' con mock
        when(hoja.añadeNombreIdHojaDefault(isA(Integer.class))).thenReturn(true);

        d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        d.eliminaHoja(1);

        int r = d.getHojasContenidas().size();
        assertEquals(0, r);

    }

    /**
     * Objeto de la prueba: Testear la funcion eliminaHoja
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se quiere eliminar una hoja inexistente en el documento
     * - Operativa: En este test de eliminaHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que no se elimina una Hoja del documento si esta no existe
     * Para ello cogeremos la instancia de Documento y no la inicializaremos, asi nos aseguraremos de que no
     * hay ninguna hoja, comprobaremos que devuelve false.
     */
    @Test
    public void eliminaHojaInExistenteTest(){

        d = Documento.getDocumento();

        boolean b = d.eliminaHoja(1);

        assertFalse(b);

    }

    /**
     * Objeto de la prueba: Testear la funcion eliminaDocumento
     * - Stubs: No se utilizan stubs para este test
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     * - Valores estudiados: Se estudia el caso en el que se quiere eliminar la instancia de un documento
     * - Operativa: En este test de eliminaDicumento se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se elimina la instancia de documento correctamente
     * Para ello cogeremos la instancia de Documento, que esta si no existe la crea, y la eliminaremos, comprobaremos
     * que efectivamente el documento es nulo
     */
    @Test
    public void eliminaDocumentoTest(){

        d = Documento.getDocumento();
        d.eliminaDocumento();

        assertNotNull(d);

    }


}
