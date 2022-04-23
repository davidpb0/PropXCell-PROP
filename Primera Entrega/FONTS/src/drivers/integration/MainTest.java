package drivers.integration;

import main.Domain.DomainControllers.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainTest {
    private static ControladorDocumento cd = null;
    private static ControladorHoja ch = null;
    private static ControladorCelda cc = null;
    private static ControladorBloque cb = null;
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
        System.out.println("\t3: Seleccionar hoja.");
        System.out.println("\t4: Añadir una hoja.");
        System.out.println("\t5: Renombrar la hoja actual.");
        System.out.println("\t6: Eliminar la hoja actual.");
        System.out.println("\t0: Salir del programa.");
        System.out.print("Escoge una opción: ");
    }

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        try {
            boolean salir = false;
            boolean hojaSeleccionada = false;
            while (!salir) {
                //opcionesDeCarga();
                //inputs = br.readLine().split(" ");
                int opcion = -1;
                /*try {
                    opcion = Integer.parseInt(inputs[0]);
                } catch (NumberFormatException ne) {}*/

                casosDeUso();
                int caso = -1;
                try {
                     caso = Integer.parseInt(br.readLine());
                } catch (NumberFormatException ignored) {}

                if (caso > 1 && cd == null) System.out.println("Primero hay que crear el documento.");
                else {
                    switch (caso) {
                        case 1:
                            cd = new ControladorDocumento();
                            ch = new ControladorHoja();
                            cc = new ControladorCelda();
                            cb = new ControladorBloque();
                            System.out.println("Documento creado correctamente.");
                            break;

                        case 2:
                            cd = null;
                            ch = null;
                            cc = null;
                            cb = null;
                            System.out.println("Documento cerrado correctamente.");
                            break;

                        case 3:
                            System.out.println("Actualmente hay " + cd.getNumHojas() + " hojas.");
                            System.out.print("Selecciona la hoja con la que quieres trabajar: ");
                            int idh = -1;
                            try {
                                idh = Integer.parseInt(br.readLine());
                            } catch (NumberFormatException e) {
                                System.out.println("El ID de la hoja introducido es incorrecto, debe ser un número entero mayor o igual que 0.");
                                break;
                            }
                            if (idh >= cd.getNumHojas()) System.out.println("Error: la hoja no existe.");
                            else {
                                ch.asignaHoja(idh);
                                hojaSeleccionada = true;
                                System.out.println("Hoja con id " + idh + " seleccionada correctamente.");
                            }
                            break;

                        case 4:
                            System.out.println("Escribe el tamaño de la hoja a añadir:");
                            System.out.print("filas: ");
                            String filas = br.readLine();
                            System.out.print("columnas: ");
                            String columnas = br.readLine();
                            int f = -1;
                            int c = -1;
                            try {
                                f = Integer.parseInt(filas);
                                c = Integer.parseInt(columnas);
                            } catch (NumberFormatException e) {
                                System.out.println("Algún valor introducido es incorrecto.");
                                break;
                            }
                            cd.anadirHoja(f, c);
                            System.out.println("Hoja añadida con " + f + " filas y " + c + " columnas.");
                            ch.asignaHoja(cd.getNumHojas() - 1);
                            System.out.println("Ahora la nueva hoja es la seleccionada.");
                            hojaSeleccionada = true;
                            break;

                        case 5:
                            if (ch.getIdHoja() == 0) System.out.println("Bug: Con la hoja 0 no se puede trabajar. Selecciona otra.");
                            else if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Escribe el nombre de la hoja: ");
                                String nombre = br.readLine();
                                ch.renombraHoja(nombre);
                                System.out.println("Ahora la hoja con ID " + ch.getIdHoja() + " se llama " + nombre + ".");
                            }
                            break;

                        case 6:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.println("Se va a eliminar la hoja actual(hoja " + ch.getIdHoja() + "), ¿quieres continuar? (1: Continuar - 2: Cancelar)");
                                int i = -1;
                                try {
                                     i = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }

                                if (i == 1) {
                                    cd.eliminarHoja(ch.getIdHoja());
                                    System.out.println("Hoja eliminada correctamente, hay que volver a seleccionar una hoja para continuar.");
                                } else System.out.println("Cancelando...");

                            }
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
