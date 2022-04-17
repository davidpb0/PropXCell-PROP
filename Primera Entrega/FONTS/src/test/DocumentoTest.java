package DomainModel;
import static org.junit.Assert.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class DocumentoTest {


    /**
     * Objeto de la prueba: Testear la funcion getDocumento
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getDocumento se utiliza o crea la instancia de Documento
     * El objetivo es ver si se crea o utiliza correctamente la instancia de Documento (al ser Singleton solo habra una)
     * Para ello dado que su creadora es vacia, tambien tendremos que inicialzarlo con los valores default
     * para poder comprobar algunos valores, comprobaremos despues de inicializarlo, que el nombre corresponde
     * con el de documento y que tiene una hoja, ademas comprobaremos que el objeto es el mismo que el que se obtiene
     * al pedir instancia de Documento
     */
    @Test
    public void cogerDocumentoTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        int i = d.getNumHojas();

        assertEquals(1, i);
        assertEquals("Doc1", d.getNombre());
        assertSame(Documento.getDocumento(), d);

        d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion inicializaDocumento
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de inicializaDocumento se utiliza o crea la instancia de Documento.
     * El objetivo es ver que el Documento se inicializa correctamente.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado y valores de fila y columna
     * dados para la Hoja, comprobaremos despues que el nombre corresponde con el introducido, la fecha corresponde
     * con la actual y que tiene una hoja, con el tamaño indicado, con el identificador que le añade segun el numero
     * de hojas que haya y nombre por defecto.
     */
    @Test
    public void inicializaDocumentoTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumento("Doc1", 2, 3);
        Hoja h =  d.getHoja(1);

        String s = d.getNombre();
        String s2 = h.getNombre();

        int i = d.getNumHojas();
        int i2 = h.getCeldas().size();
        int i3 = d.getHojasContenidas().size();
        int i4 = h.getId();

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        assertEquals(LocalDateTime.now().format(fm), d.getFecha());
        assertEquals(1, i);
        assertEquals(6, i2);
        assertEquals("Doc1", s);
        assertEquals("Hoja1", s2);
        assertEquals(1, i3);
        assertEquals(1, i4);

        d.eliminaDocumento();
    }


    /**
     * Objeto de la prueba: Testear la funcion inicializaDocumentoDefault
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     *- Operativa: En este test de inicializaDocumento se utiliza o crea la instancia de Documento.
     * El objetivo es ver que el Documento se inicializa correctamente con los valores para la Hoja por defecto.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado,
     * comprobaremos despues que el nombre corresponde con el introducido, la fecha corresponde con la actual
     * y que tiene una hoja, con el tamaño por defecto con el identificador que le añade segun el numero
     * de hojas que haya y nombre por defecto.
     */
    @Test
    public void inicializaDocumentoDefaultTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");
        Hoja h =  d.getHoja(1);

        String s = d.getNombre();
        String s2 = h.getNombre();

        int i = d.getNumHojas();
        int i2 = h.getCeldas().size();
        int i3 = d.getHojasContenidas().size();
        int i4 = h.getId();


        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        assertEquals(LocalDateTime.now().format(fm), d.getFecha());
        assertEquals(1, i);
        assertEquals(2500, i2);
        assertEquals("Doc1", s);
        assertEquals("Hoja1", s2);
        assertEquals(1, i3);
        assertEquals(1, i4);

        d.eliminaDocumento();
    }


    /**
     * Objeto de la prueba: Testear la funcion getNumHoja
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     *- Operativa: En este test de getNumHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el numero de hojas correcto
     * Para ello cogeremos la instancia de Documento, comprobaremos aqui el numero de hojas que deberia ser 0,
     * seguidamente lo  inicializaremos con un nombre dado, comprobaremos despues el numero de hojas, añadiremos unas
     * hojas mas e iremos comprobando que el numero que devuelve es el correcto.
     */
    @Test
    public void getNumHojasTest(){
        Documento d = Documento.getDocumento();

        int n = d.getNumHojas();

        d.inicializaDocumentoDefault("Doc1");
        int n2 = d.getNumHojas();

        d.añadeHojaDf();
        int n3 = d.getNumHojas();

        d.añadeHojaDf();
        int n4 = d.getNumHojas();

        assertEquals(0, n);
        assertEquals(1, n2);
        assertEquals(2, n3);
        assertEquals(3, n4);

        d.eliminaDocumento();

    }


    /**
     * Objeto de la prueba: Testear la funcion getHoja
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve la Hoja indicada
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que pidiendo la hoja con el identificador dado, devuelve la correcta, ademas se comprueba
     * que si pedimos una hoja que no existe devuelve Null
     */
    @Test
    public void getHojaTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        Hoja h = d.getHoja(1);
        Hoja h2 = d.getHoja(2);

        int i = h.getId();
        boolean b = h2 == null;

        assertEquals(1, i);
        assertTrue(b);
        assertNotNull(h);

        d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion getNombre
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getNombre se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el nombre del Documento indicado al inicializarlo.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que se devuelve el nombre indicado al inicializarlo.
     */
    @Test
    public void getNombreTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        assertEquals("Doc1", d.getNombre());

        d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion getFecha
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getFecha se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve la fecha de ultima modificacion de documento
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que la fecha corresponde a la fecha de hoy
     */
    @Test
    public void getFechaTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        assertEquals("17-04-2022", d.getFecha());

        d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion getHojasContenidas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de getHojasContenidas se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se devuelve el numero de Hojas que contiene el documento.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, seguidamente
     * comprobaremos que el tamaño de la estructura de datos que almacena las hojas sea el esperado
     */
    @Test
    public void getHojasContenidasTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        HashMap<Integer, Hoja> hc = d.getHojasContenidas();
        int s = hc.size();

        d.añadeHojaDf();
        int s2 = hc.size();

        d.añadeHojaDf();
        int s3 = hc.size();

        d.añadeHojaDf();
        int s4 = hc.size();

        assertEquals(s, 1);
        assertEquals(s2, 2);
        assertEquals(s3, 3);
        assertEquals(s4, 4);

         d.eliminaDocumento();
    }

    /**
     * Objeto de la prueba: Testear la funcion recalculaNumHojas
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de recalculaNumHojas se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se calcula el numero de hojas correctamente
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, añadiremos alguna hoja
     * e iremos comprobando que el numero recalculado es el correcto
     */
    @Test
    public void recalculaNumHojasTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        int s = d.getNumHojas();
        assertEquals(s, 1);

        d.añadeHojaDf();
        int s2 = d.getNumHojas();
        assertEquals(s2, 2);

        d.añadeHojaDf();
        int s3 = d.getNumHojas();
        assertEquals(s3, 3);

        d.añadeHojaDf();
        int s4 = d.getNumHojas();
        assertEquals(s4, 4);

        d.eliminaHoja(2);
        int s5 = d.getNumHojas();
        assertEquals(s5, 3);

        d.eliminaDocumento();

    }

    /**
     * Objeto de la prueba: Testear la funcion añadeHojaDf
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de añadeHojaDf se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se añade una Hoja al documento correctamente
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, añadiremos alguna hoja
     * e iremos comprobando que se ha añadido correctamente comprobando el numero de hojas que hay y que las hojas
     * añadidadas tienen los parametros por defecto
     */
    @Test
    public void añadeHojaDfTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        d.añadeHojaDf();
        int s2 = d.getNumHojas();
        Hoja h2 =  d.getHoja(2);
        int i2 = h2.getCeldas().size();
        String n2 = h2.getNombre();
        int id2 = h2.getId();

        assertEquals(s2, 2);
        assertEquals(2500, i2);
        assertEquals("Hoja2", n2);
        assertEquals(2, id2);

        d.añadeHojaDf();
        int s3 = d.getNumHojas();
        Hoja h3 =  d.getHoja(3);
        int i3 = h3.getCeldas().size();
        String n3 = h3.getNombre();
        int id3 = h3.getId();

        assertEquals(s3, 3);
        assertEquals(2500, i3);
        assertEquals("Hoja3", n3);
        assertEquals(3, id3);

        d.eliminaDocumento();

    }

    /**
     * Objeto de la prueba: Testear la funcion añadeHoja
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de añadeHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se añade una Hoja al documento correctamente con los valores dados.
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, añadiremos alguna hoja
     * con filas y columnas dadas e iremos comprobando que se ha añadido correctamente comprobando el numero de hojas
     * que hay y que las hojas añadidadas tienen los parametros correctos.
     */
    @Test
    public void añadeHojaTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        d.añadeHoja(2, 2);
        int s2 = d.getNumHojas();
        Hoja h2 =  d.getHoja(2);
        int i2 = h2.getCeldas().size();
        String n2 = h2.getNombre();
        int id2 = h2.getId();

        assertEquals(s2, 2);
        assertEquals(4, i2);
        assertEquals("Hoja2", n2);
        assertEquals(2, id2);


        d.añadeHoja(3, 4);
        int s3 = d.getNumHojas();
        Hoja h3 =  d.getHoja(3);
        int i3 = h3.getCeldas().size();
        String n3 = h3.getNombre();
        int id3 = h3.getId();

        assertEquals(s3, 3);
        assertEquals(12, i3);
        assertEquals("Hoja3", n3);
        assertEquals(3, id3);

        d.eliminaDocumento();

    }

    /**
     * Objeto de la prueba: Testear la funcion eliminaHoja
     *
     * - Ficheros de datos necesarios: No se necesitan ficheros para este test.
     *
     * - Operativa: En este test de eliminaHoja se utiliza o crea la instancia de Documento.
     * El objetivo es ver que se elimina una Hoja al documento correctamente
     * Para ello cogeremos la instancia de Documento y la inicializaremos con un nombre dado, añadiremos alguna hoja
     * para que no solo haya una e iremos comprobando que se han ido eliminando correctamente comprobando el numero
     * de hojas que hay en el documento, ademas comprobaremos si devuelve false en caso de que la hoja pedida para
     * borrara no exista
     */
    @Test
    public void eliminaHojaTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");

        boolean b1 = d.eliminaHoja(1);
        int s1 = d.getNumHojas();
        assertEquals(true, b1);
        assertEquals(0, s1);

        boolean b2 = d.eliminaHoja(1);
        int s2 = d.getNumHojas();
        assertEquals(0, s2);
        assertEquals(false, b2);


        d.añadeHojaDf();
        d.añadeHojaDf();
        d.añadeHojaDf();

        d.eliminaHoja(2);
        int s3 = d.getNumHojas();
        assertEquals(2, s3);

        int id1 = d.getHoja(1).getId();
        assertEquals(1, id1);

        int id3 = d.getHoja(3).getId();
        assertEquals(3, id3);

        d.eliminaDocumento();


    }


}
