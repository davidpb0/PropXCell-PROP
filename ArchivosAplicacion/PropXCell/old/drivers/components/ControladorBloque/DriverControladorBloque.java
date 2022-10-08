package drivers.components.ControladorBloque;

import main.Domain.DomainControllers.ControladorBloque;
import main.Domain.DomainModel.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DriverControladorBloque {
    private static BufferedReader br;
    private static String[] palabras;
    private static boolean constructor = false;
    private static boolean bloqueDefinido = false;
    private static Documento d;
    private static Hoja h;
    private static ControladorBloque cb = null;

    private static void opciones() {
        System.out.println("Funciones disponibles para la clase ControladorBloque:");
        System.out.println("\t1: ControladorBloque()");
        System.out.println("\t2: Copiar()");
        System.out.println("\t3: Cortar()");
        System.out.println("\t4: Pegar(int _filaInicio, int _columnaInicio)");
        System.out.println("\t5: SetBloqueSeleccionado(int _idH, int _filaInicial, int _columnaInicial, int _filaFinal, int _columnaFinal)");
        System.out.println("\t0: Salir");
        System.out.print("Escoge una opción: ");
    }

    public static void main(String[] args) {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;

            DriverControladorBloque driver = new DriverControladorBloque();
            System.out.println("Este driver setea el valor de cada celda en i*j, siendo i su fila y j su columna.");
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
                            if (nParamValido(0)) driver.constructorTest();
                            break;

                        case 2:
                            if (nParamValido(0)) driver.copiarTest();
                            break;

                        case 3:
                            if (nParamValido(0)) driver.cortarTest();
                            break;

                        case 4:
                            if (nParamValido(2)) driver.pegarTest(palabras[1], palabras[2]);
                            break;

                        case 5:
                            if (nParamValido(4)) driver.setBloqueSeleccionadoTest(palabras[1], palabras[2], palabras[3], palabras[4]);
                            break;

                        default:
                            System.out.println("La opción escogida es incorrecta, debe ser un número entre 0 y 5.");
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

    private void constructorTest() throws Exception {
        System.out.println("Test de la constructora de ControladorBloque:");
        cb = new ControladorBloque();
        constructor = true;
        System.out.println("Se ha creado el ControladorBloque correctamente.");

        d = Documento.getDocumento();
        Documento.getDocumento().inicializaDocumentoDefault("doc");
        h = d.getHoja(0);
        for (int i = 1; i <= 50; ++i) {
            for (int j = 1; j <= 50; ++j) {
                h.getCelda(new Posicion(i, j)).setValor(String.valueOf(i*j));
                h.getCelda(new Posicion(i, j)).setContenido(String.valueOf(i*j));
            }
        }
    }

    private void copiarTest() {
        if (!bloqueDefinido) System.out.println("Primero hay que definir un bloque.");
        else {
            System.out.println("Test de copiar():");
            cb.copiar();
            System.out.println("Bloque copiado correctamente, ahora el booleano Cortar vale "+ false);
            escribeBloque();
        }
    }

    private void cortarTest() {
        if (!bloqueDefinido) System.out.println("Primero hay que definir un bloque.");
        else {
            System.out.println("Test de cortar():");
            cb.cortar();
            System.out.println("Ahora el booleano Cortar vale "+ true);
            escribeBloque();
        }
    }

    private void pegarTest(String _fI, String _cI) throws Exception {
        if (!bloqueDefinido) System.out.println("Primero hay que definir un bloque.");
        else {
            int f = Traductor.StringInt(_fI);
            int c = Traductor.StringInt(_cI);
            cb.pegar(f, c);
            System.out.println("El bloque se ha pegado correctamente. El contenido de la hoja a partir de las celdas pegadas es: ");
            int tamanoF = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getFila() - BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getFila();
            int tamanoC = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getColumna() - BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getColumna();
            for (int i = f; i <= tamanoF + 3; ++i) {
                for (int j = c; j <= tamanoC + 3; ++j) {
                    System.out.print(h.getCelda(new Posicion(i, j)).getValor() + " ");
                }
                System.out.println("");
            }
        }
    }

    private void setBloqueSeleccionadoTest(String _filaInicial, String _columnaInicial, String _filaFinal, String _columnaFinal) throws Exception {
        int _fI = Traductor.StringInt(_filaInicial);
        int _cI = Traductor.StringInt(_columnaInicial);
        int _fF = Traductor.StringInt(_filaFinal);
        int _cF = Traductor.StringInt(_columnaFinal);
        Celda inicialC = h.getCelda(new Posicion(_fI, _cI));
        Celda finalC = h.getCelda(new Posicion(_fF, _cF));
        BloqueSeleccionado.getBloque().setCelda(inicialC, finalC);
        escribeBloque();
        bloqueDefinido = true;
    }

    private void escribeBloque() {
        int fI = BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getFila();
        int cI = BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getColumna();
        int fF = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getFila();
        int cF = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getColumna();
        System.out.println("Ahora el bloque seleccionado contiene las celdas:");
        for (int i = fI; i <= fF; ++i) {
            for (int j = cI; j <= cF; ++j) {
                System.out.print(h.getCelda(new Posicion(i, j)).getValor() + " ");
            }
            System.out.println("");
        }
    }
}
