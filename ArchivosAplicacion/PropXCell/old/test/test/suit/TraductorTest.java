
package test.suit;
import main.Domain.DomainModel.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

public class TraductorTest {

    @InjectMocks
    private Traductor traductor;

    @Mock
    private Documento documento;
    @Mock
    private Hoja hoja;
    @Mock
    private Celda celda;
    @Mock
    private Posicion posicion;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() {}

    /**
     * Objeto de la prueba: conversión de string a int
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: string con valor "123"
     * Efectos estudiados: -
     * Operativa: Se crea el string con el valor estudiado y se comprueba que su conversión
     *      a entero es correcta
     */
    @Test
    public void testStringInt() throws Exception {
        String s = "123";
        assertEquals(traductor.StringInt(s), 123);
    }

    /**
     * Objeto de la prueba: conversión de string a float
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: string con valor "123.4"
     * Efectos estudiados: -
     * Operativa: Se crea el string con el valor estudiado y se comprueba que su conversión
     *      a float es correcta
     */
    @Test
    public void testStringFloat() throws Exception {
        String s = "123.4";
        assertEquals(traductor.StringFloat(s), 123.4f, 0.0f);
    }

    /**
     * Objeto de la prueba: conversión de string a date
     * Otros elementos integrados en la prueba: mock de Traductor, SimpleDateFormat
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: string con valor "01/01/2001"
     * Efectos estudiados: -
     * Operativa: Se crea el string con el valor estudiado y se comprueba que su conversión
     *      a fecha es correcta
     */
    @Test
    public void testStringDate() throws ParseException {
        String s = "01/01/2001";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date d = formatoFecha.parse(s);
        assertEquals(d, new Date(946681200000L)); // Milisegundos desde 01/01/1970 0:00 hasta 01/01/2000 0:00
    }

    /**
     * Objeto de la prueba: conversión de int a string
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: entero con valor 123
     * Efectos estudiados: -
     * Operativa: Se crea el entero con el valor estudiado y se comprueba que su conversión
     *      a String es correcta
     */
    @Test
    public void testIntString() {
        int i = 123;
        assertEquals(String.valueOf(i), "123");
    }

    /**
     * Objeto de la prueba: conversión de float a string
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: float con valor 123.4
     * Efectos estudiados: -
     * Operativa: Se crea el float con el valor estudiado y se comprueba que su conversión
     *      a String es correcta
     */
    @Test
    public void testFloatString() {
        Float f = 123.4f;
        assertEquals(String.valueOf(f), "123.4");
    }

    /**
     * Objeto de la prueba: conversión de date a string
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: fecha que representa el transcurso de 946681200000 ms desde 01/01/1970 0:00
     * Efectos estudiados: -
     * Operativa: Se crea la fecha con el valor estudiado y se comprueba que su conversión
     *      a String es correcta
     */
    @Test
    public void testDateString() {
        Date d = new Date(946681200000L); // Milisegundos desde 01/01/1970 0:00 hasta 01/01/2000 0:00
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(formatoFecha.format(d), "01/01/2000");
    }

    /**
     * Objeto de la prueba: detección de la fórmula escrita por el usuario
     * Otros elementos integrados en la prueba: mock de Traductor, número aleatorio
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: función aleatoria cogida con el número random
     * Efectos estudiados: -
     * Operativa: Se selecciona una función aleatoria del array funcs y su código resultante, y se
     *      comparan ambos valores.
     */
    @Test
    public void testDetecta() {
        String[] funcs = {"abs()", "trunc()", "aprox()", "convertirDB()", "convertirBD", "convertirHD", "convertirDH", "convertirHB", "convertirBH", "mes()", "año()", "diasemana()", "nombredia()", "longitud()", "media()", "mediana()", "varianza()", "covarianza()", "desviacion()", "pearson()", "contarLetra()", "reemplazarPal()", "reemplazarLet()", "funcionerronea()", "$A1", "01/01/2000", "99/99/9999"};
        String[] codigos = {"#ABS", "#TRUNC", "#APROX", "#VDB", "#VBD", "#VHD", "#VDH", "#VHB", "#VBH", "#MES", "#AÑO", "#DIAS", "#NDIA", "#LONG", "#MEDIA", "#MEDIANA", "#VAR", "#COV", "#DESV", "#COEFP", "#CLETRA", "#REEMPPAL", "#REEMPLET", "#ERRORFUNC", "#REFERENCIA", "#FECHA", "#ERRORFECHA"};
        Random random = new Random();
        int funcRandom = random.nextInt(funcs.length);
        assertEquals(Traductor.detecta(funcs[funcRandom]), codigos[funcRandom]);
    }

    /**
     * Objeto de la prueba: traducción de una columna
     * Otros elementos integrados en la prueba: mock de Traductor
     * Drivers: -
     * Stubs: -
     * Archivos de datos necesarios: -
     * Valores estudiados: columna "A"
     * Efectos estudiados: -
     * Operativa: Se comprueba que la función a estudiar traduce "A" a 1
     */
    @Test
    public void testTraduceColumna() throws Exception {
        when(traductor.StringInt(isA(String.class))).thenReturn(1);
        String col = "A";
        assertEquals(Traductor.traduceColumna(col), 1);
    }

    /**
     * Objeto de la prueba: traducción de una celda
     * Otros elementos integrados en la prueba: mock de Documento, mock de Hoja, mock de Traductor
     * Drivers: -
     * Stubs: PosicionStub, CeldaStub
     * Archivos de datos necesarios: -
     * Valores estudiados: celda con posición "A1" y hoja 1
     * Efectos estudiados: -
     * Operativa: Se comprueba que la función a estudiar traduce la posición "A1" a (1, 1)
     */
    @Test
    public void testTraduceCelda() throws Exception {
        when(documento.getHoja(isA(Integer.class))).thenReturn(new HojaStub());
        when(new Posicion(isA(Integer.class), isA(Integer.class))).thenReturn(new PosicionStub(1, 1));
        when(hoja.getCelda(isA(PosicionStub.class))).thenReturn(new CeldaStub(new PosicionStub(1, 1)));

        CeldaStub c = (CeldaStub) Traductor.traduceCelda("A1");
        assertEquals(c, new CeldaStub(new PosicionStub(1, 1), "1"));
    }

    /**
     * Objeto de la prueba: obtención de los argumentos de una función unaria
     * Otros elementos integrados en la prueba: mock de Documento, mock de Traductor, mock de Celda y mock de Hoja
     * Drivers: -
     * Stubs: PosicionStub, CeldaStub
     * Archivos de datos necesarios: -
     * Valores estudiados: =abs($A1)
     * Efectos estudiados: -
     * Operativa: Se comprueba que la función a estudiar obtiene "$A1" de "=abs($A1)"
     */
    @Test
    public void testGetArgumentosFuncion1aria() throws Exception {
        when(documento.getHoja(isA(Integer.class))).thenReturn(new HojaStub());
        when(traductor.traduceCelda(isA(String.class))).thenReturn(new CeldaStub(new PosicionStub(1, 1)));
        when(celda.getValor()).thenReturn("hola");
        when(hoja.getColumnaFila(isA(String.class), isA(String.class))).thenReturn(new ArrayList<>());

        String[] res = traductor.getArgumentosFuncion1aria("=abs($A1)");
        assertArrayEquals(res, new String[]{"$A1"});
    }

    /**
     * Objeto de la prueba: obtención de los argumentos de una función n-aria
     * Otros elementos integrados en la prueba: mock de Documento, mock de Traductor, mock de Celda y mock de Hoja
     * Drivers: -
     * Stubs: PosicionStub, CeldaStub
     * Archivos de datos necesarios: -
     * Valores estudiados: =media(20, 10)
     * Efectos estudiados: -
     * Operativa: Se comprueba que la función a estudiar obtiene {{"20"}, {"10"}} de "=media(20, 10)"
     */
    @Test
    public void testGetArgumentosFuncionNaria() throws Exception {
        when(documento.getHoja(isA(Integer.class))).thenReturn(new HojaStub());
        when(traductor.traduceCelda(isA(String.class))).thenReturn(new CeldaStub(new PosicionStub(1, 1)));
        when(celda.getValor()).thenReturn("hola");
        when(hoja.getColumnaFila(isA(String.class), isA(String.class))).thenReturn(new ArrayList<>());

        ArrayList<String[]> res = traductor.getArgumentosFuncionNaria("=media(20, 10)");
        ArrayList<String[]> res2 = new ArrayList<>();
        res2.add(new String[]{"20"});
        res2.add(new String[]{"10"});
        assertTrue(res.equals(res2));
    }

    private static class PosicionStub extends Posicion {
        public PosicionStub(int _f, int _c) {
            super(_f, _c);
        }
    }

    private static class CeldaStub extends Celda {
        PosicionStub pos;
        String contenido = "";

        public CeldaStub(PosicionStub _p) {
            super(_p);
            pos = _p;
        }

        public CeldaStub(PosicionStub _p, String _cont) {
            super(_p, _cont);
            pos = _p;
            contenido = _cont;
        }

        public PosicionStub getPos() {
            return pos;
        }
    }

    private static class HojaStub extends Hoja {
        HashMap<PosicionStub, CeldaStub> celdas;

        public HojaStub() {
            super();
        }
    }

}