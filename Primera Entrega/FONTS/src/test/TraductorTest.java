package test;

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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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



    @Test
    public void testStringInt() {
        String s = "123";
        assertEquals(traductor.StringInt(s), 123);
    }

    @Test
    public void testStringFloat() {
        String s = "123.4";
        assertEquals(traductor.StringFloat(s), 123.4f, 0.0f);
    }

    @Test
    public void testStringDate() throws ParseException {
        String s = "01/01/2001";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date d = formatoFecha.parse(s);
        assertEquals(d, new Date(946681200000L)); // Milisegundos desde 01/01/1970 0:00 hasta 01/01/2000 0:00
    }

    @Test
    public void testIntString() {
        int i = 123;
        assertEquals(String.valueOf(i), "123");
    }

    @Test
    public void testFloatString() {
        Float f = 123.4f;
        assertEquals(String.valueOf(f), "123.4");
    }

    @Test
    public void testDateString() {
        Date d = new Date(946681200000L); // Milisegundos desde 01/01/1970 0:00 hasta 01/01/2000 0:00
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(formatoFecha.format(d), "01/01/2000");
    }

    @Test
    public void testDetecta() {
        String[] funcs = {"abs()", "trunc()", "aprox()", "convertirDB()", "convertirBD", "convertirHD", "convertirDH", "convertirHB", "convertirBH", "mes()", "año()", "diasemana()", "nombredia()", "longitud()", "media()", "mediana()", "varianza()", "covarianza()", "desviacion()", "pearson()", "contarLetra()", "reemplazarPal()", "reemplazarLet()", "funcionerronea()", "$A1", "01/01/2000", "99/99/9999"};
        String[] codigos = {"#ABS", "#TRUNC", "#APROX", "#VDB", "#VBD", "#VHD", "#VDH", "#VHB", "#VBH", "#MES", "#AÑO", "#DIAS", "#NDIA", "#LONG", "#MEDIA", "#MEDIANA", "#VAR", "#COV", "#DESV", "#COEFP", "#CLETRA", "#REEMPPAL", "#REEMPLET", "#ERRORFUNC", "#REFERENCIA", "#FECHA", "#ERRORFECHA"};
        Random random = new Random();
        int funcRandom = random.nextInt(funcs.length);
        assertEquals(traductor.getTraductor().detecta(funcs[funcRandom]), codigos[funcRandom]);
    }

    @Test
    public void testTraduceColumna() {
        when(traductor.StringInt(isA(String.class))).thenReturn(1);
        String col = "A";
        assertEquals(traductor.getTraductor().traduceColumna(col), 1);
    }

    @Test
    public void testTraduceCelda() {
        when(documento.getHoja(isA(Integer.class))).thenReturn(new HojaStub());
        when(new Posicion(isA(Integer.class), isA(Integer.class))).thenReturn(new PosicionStub(1, 1));
        when(hoja.getCelda(isA(PosicionStub.class))).thenReturn(new CeldaStub(new PosicionStub(1, 1)));

        CeldaStub c = (CeldaStub) traductor.getTraductor().traduceCelda("A1", 1);
        assertEquals(c, new CeldaStub(new PosicionStub(1, 1), "1"));
    }

    @Test
    public void testGetArgumentos() {
        when(documento.getHoja(isA(Integer.class))).thenReturn(new HojaStub());
        when(traductor.traduceCelda(isA(String.class), isA(Integer.class))).thenReturn(new CeldaStub(new PosicionStub(1, 1)));
        when(celda.getValor()).thenReturn("hola");
        when(hoja.getColumnaFila(isA(String.class), isA(String.class))).thenReturn(new ArrayList<Celda>());

        String[] res = traductor.getArgumentos("abs(A1)", 1);
        assertArrayEquals(res, new String[]{"A1"});
    }

    public static class PosicionStub extends Posicion {
        public PosicionStub(int _f, int _c) {
            super(_f, _c);
        }
    }

    public static class CeldaStub extends Celda {
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

    public static class HojaStub extends Hoja {
        HashMap<PosicionStub, CeldaStub> celdas;

        public HojaStub() {
            super();
        }
    }

}