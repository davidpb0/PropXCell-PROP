package drivers.components.ControladorBloque;

import main.Domain.DomainControllers.ControladorBloque;
import main.Domain.DomainModel.*;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

public class DriverControladorBloque {
    private static BufferedReader br;
    private static String[] palabras;
    private static boolean constructor = false;

    @InjectMocks
    private static ControladorBloque cb = null;

    @Mock
    private static BloqueTemporalCopiado bloqueTemporalCopiado;
    @Mock
    private Documento documento;
    @Mock
    private static Hoja hoja;
    @Mock
    private static Celda celda;
    @Mock
    private static Posicion posicion;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() {}

    private static void testPegar() {
        when(hoja.getCelda(isA(Posicion.class))).thenReturn(celda = new Celda(posicion, "hola"));
        for (int f = 1; f <= 3; ++f) {
            for (int c = 1; c <= 3; ++c) {
                posicion = new Posicion(f, c);
                Celda cell = hoja.getCelda(posicion);
                System.out.println(cell.getContenido() + ' ');
            }
            System.out.println("\n");
        }
        if (bloqueTemporalCopiado.getCortar()) {
            bloqueTemporalCopiado = null;
            System.out.println("Al haber pegado un contenido cortado, el bloque temporal copiado se ha borrado.");
        }
    }

    private static void opciones() {
        System.out.println("Funciones disponibles para la clase ControladorBloque:");
        System.out.println("\t1: ControladorBloque()");
        System.out.println("\t2: Copiar()");
        System.out.println("\t3: Cortar()");
        System.out.println("\t4: Pegar(int _numHoja, int _filaInicio, int _columnaInicio)");
        System.out.println("\t5: SetBloqueSeleccionado(int _idH, int _filaInicial, int _columnaInicial, int _filaFinal, int _columnaFinal)");
        System.out.println("\t0: Salir\n");
        System.out.println("Escoge una opción: ");
    }

    public static void main(String[] args) {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;

            DriverControladorBloque driver = new DriverControladorBloque();

            while(!salir){
                opciones();
                String line = br.readLine();
                if (line != "") palabras = line.split(" ");
                else palabras[0] = "-1";

                int optionSelected = -1;
                try {
                    optionSelected = Integer.parseInt(palabras[0]);
                } catch (NumberFormatException ignored) {}

                if (optionSelected > 1 && !constructor) {
                    System.out.println("Primero hay que llamar a la constructora.");
                    optionSelected = -1;
                }
                else {
                    switch (optionSelected) {
                        case 0:
                            salir = true;
                            break;

                        case 1:

                            break;

                        case 2:
                            if (nParamValido(0)) driver.copiarTest();
                            break;

                        case 3:
                            if (nParamValido(0)) driver.cortarTest();
                            break;

                        case 4:
                            if (nParamValido(3)) driver.pegarTest();
                            break;

                        default:
                            System.out.println("La opción escogida es incorrecta, debe ser un número entre 0 y 4.");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static Boolean nParamValido(int n) {
        if (palabras.length != n + 1) {
            System.out.println("El numero de parametros introducidos no es valido");
            return false;
        }
        return true;
    }

    private void constructorTest() {
        System.out.println("Test de la constructora de ControladorBloque:");
        cb = new ControladorBloque();
        constructor = true;
        System.out.println("Se ha creado el ControladorBloque correctamente.");
    }

    private void copiarTest() {
        System.out.println("Test de copiar():");
        cb.copiar();
        System.out.println("Ahora el booleano Cortar vale "+ false);
    }

    private void cortarTest() {
        System.out.println("Test de cortar():");
        cb.cortar();
        System.out.println("Ahora el booleano Cortar vale "+ true);
    }

    private void pegarTest() {
        cb.pegar(hoja.getId(), 1, 1);
    }
}
