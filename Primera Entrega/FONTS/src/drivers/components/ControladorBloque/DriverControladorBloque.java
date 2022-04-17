package drivers.components.ControladorBloque;

import main.Domain.DomainModel.Celda;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DriverControladorBloque {

    static StubBloqueTemporalCopiado cont = null;

    public static void testConstructora() {
        cont = new StubBloqueTemporalCopiado();
        System.out.println("Se ha creado el stub del bloque temporal copiado correctamente.");
    }

    public static void testCopiar() {
        cont.setCortar(false);
        System.out.println("Ahora el booleano Cortar vale "+ false);
    }

    public static void testCortar() {
        cont.setCortar(true);
        System.out.println("Ahora el booleano Cortar vale "+ true);
    }

    public static void testPegar() {
        for (int f = 1; f <= 3; ++f) {
            for (int c = 1; c <= 3; ++c) {
                Celda cell = cont.getCelda(f, c);
                System.out.println(cell.getContenido() + ' ');
            }
            System.out.println("\n");
        }
        if (cont.getCortar()) {
            cont = null;
            System.out.println("Al haber pegado un contenido cortado, el bloque temporal copiado se ha borrado.");
        }
    }

    public static void opciones() {
        System.out.println("Funciones disponibles para la clase ControladorBloque:");
        System.out.println("\t1: ControladorBloque()");
        System.out.println("\t2: Copiar()");
        System.out.println("\t3: Cortar()");
        System.out.println("\t4: Pegar()");
        System.out.println("\t0: Salir\n");
        System.out.println("Escoge una opción: ");
    }

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;

            while(!salir){
                opciones();
                String line = br.readLine();
                String[] datos = line.split(" ");

                int optionSelected = -1;
                try {
                    optionSelected = Integer.parseInt(datos[0]);
                } catch (NumberFormatException ignored) {}

                if (cont == null && optionSelected == 4) {
                    System.out.println("No es posible pegar si no se ha copiado nada.");
                }
                else {
                    switch (optionSelected) {
                        case 0:
                            salir = true;
                            break;

                        case 1:
                            testConstructora();
                            break;

                        case 2:
                            testCopiar();
                            break;

                        case 3:
                            testCortar();
                            break;

                        case 4:
                            testPegar();
                            break;

                        default:
                            System.out.println("La opción escogida es incorrecta, debe ser un número entre 0 y 4.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
