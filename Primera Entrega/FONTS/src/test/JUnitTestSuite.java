

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
        BloqueSeleccionadoTest.class,
        BloqueTemporalCopiadoTest.class,
        CeldaTest.class,
        DocumentoTest.class,
        HojaTest.class,
        PosicionTest.class,
        TraductorTest.class
})

public class JUnitTestSuite{}