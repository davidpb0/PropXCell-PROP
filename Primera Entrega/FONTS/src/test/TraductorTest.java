package test;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Posicion;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TraductorTest {

    @Test
    public void contenidoVacio() {
        Posicion p = new Posicion(0, 0);
        Celda cell = new Celda(p);
        assertEquals("", cell.getContenido());

        Celda cell2 = new Celda(cell);
        assertEquals("", cell2.getContenido());
    }

    @Test
    public void StringInt() {
        String s = "123";
        Assert.assertTrue(s.matches("[0-9.]+"));
        assertEquals(Integer.parseInt(s), 123);
    }

    @Test
    public void StringFloat() {
        String s = "123.4";
        assertEquals(Float.parseFloat(s), 123.4f);
    }

    @Test
    public void StringDate() throws ParseException {
        String s = "01/01/2001";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date d = formatoFecha.parse(s);
        assertEquals(formatoFecha.format(d), "01/01/2001");
    }

    @Test
    public void IntString() {
        int i = 123;
        assertEquals(String.valueOf(i), "123");
    }

    @Test
    public void FloatString() {
        Float f = 123.4f;
        assertEquals(String.valueOf(f), "123.4");
    }

    @Test
    public void DateString() {
        Date d = new Date(2001, Calendar.JANUARY, 30);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        assertEquals(formatoFecha.format(d), "30/01/2001");
    }

}