package drivers.integration;

import main.Domain.DomainModel.Documento;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainTest {
    private static Documento d = null;
    private static BufferedReader br = null;
    private static String[] inputs;

    private static void opcionesDeCarga() {
        System.out.println("A continuación hay una lista de hojas de prueba:");
        System.out.println("\t1: Números enteros sencillos.");
        System.out.println("\t0: Salir.");
        System.out.print("Escoge una opción: ");
    }

    private static void casosDeUso() {
        System.out.println("Selecciona un caso de uso para realizar:");
        System.out.println("\t1: Crear documento.");
        System.out.println("\t2: Cerrar documento.");
        System.out.print("Escoge una opción: ");
    }

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean salir = false;
            while (!salir) {
                //opcionesDeCarga();
                //inputs = br.readLine().split(" ");
                int opcion = -1;
                try {
                    opcion = Integer.parseInt(inputs[0]);
                } catch (NumberFormatException ne) {}

                casosDeUso();
                int caso = Integer.parseInt(br.readLine());
                if (caso != 1 && d == null) System.out.println("Primero hay que crear el documento.");
                else {
                    switch (caso) {
                        case 1:
                            d = Documento.getDocumento();
                            System.out.println("Documento creado correctamente.");
                            break;

                        case 2:
                            d = null;
                            System.out.println("Documento cerrado correctamente.");
                            break;

                        case 0:
                            salir = true;
                            System.out.println("Saliendo...");
                            break;

                        default:
                            System.out.println("Error: Escoge una opción válida.");
                            break;
                    }
                }

                if (opcion == 0) salir = true;

            }
        } catch (Exception e) {
            System.err.println("Error interno.");
            e.printStackTrace();
        }
    }
}
