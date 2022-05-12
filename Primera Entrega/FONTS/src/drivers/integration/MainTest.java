package drivers.integration;

import main.Domain.DomainControllers.ControladorBloque;
import main.Domain.DomainControllers.ControladorDocumento;
import main.Domain.DomainControllers.ControladorHoja;
import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Posicion;
import main.Domain.DomainModel.Traductor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainTest {
    private static ControladorDocumento cd = null;
    private static ControladorHoja ch = null;
    //private static ControladorCelda cc = null;
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
        System.out.println("\t6: (NO IMPLEMENTADO)Ordenar.");
        System.out.println("\t7: (NO IMPLEMENTADO)Buscar.");
        System.out.println("\t8: (NO IMPLEMENTADO)Reemplazar.");
        System.out.println("\t9: Eliminar la hoja actual.");
        System.out.println("\t10: Consultar el número de filas y columnas de la hoja actual.");
        System.out.println("\t11: Añadir n filas a partir de una fila en concreto en la hoja actual.");
        System.out.println("\t12: Añadir n columnas a partir de una columna en concreto en la hoja actual.");
        System.out.println("\t13: Eliminar filas a partir de una fila en concreto en la hoja actual.");
        System.out.println("\t14: Eliminar columnas a partir de una columna en concreto en la hoja actual.");
        System.out.println("\t15: Asignar valor a una celda.");
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
                            cd = ControladorDocumento.getControladorDocumento();
                            ch = ControladorHoja.getControladorHoja();
                            ch.asignaHoja(1);
                            hojaSeleccionada = true;
                            //cc = ControladorCelda.getControladorCelda();
                            cb = ControladorBloque.getControldorBloque();
                            System.out.println("Documento creado correctamente.");
                            break;

                        case 2:
                            cd = null;
                            ch = null;
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
                                System.out.println("El ID de la hoja introducido es incorrecto, debe ser un número entero mayor o igual que 1.");
                                break;
                            }
                            if (idh > cd.getNumHojas()) System.out.println("Error: la hoja no existe.");
                            else {
                                ch.asignaHoja(idh);
                                hojaSeleccionada = true;
                                System.out.println("Hoja con id " + idh + " y nombre " + cd.getDocumento().getHoja(idh).getNombre() +
                                        " seleccionada correctamente.");
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
                            ch.asignaHoja(cd.getNumHojas());
                            System.out.println("Ahora la nueva hoja es la seleccionada.");
                            hojaSeleccionada = true;
                            break;

                        case 5:
                            if (!hojaSeleccionada) {
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
                            System.out.println("Ordenar: Funcionalidad no implementada.");
                            break;

                        case 7:
                            System.out.println("Buscar: Funcionalidad no implementada.");
                            break;

                        case 8:
                            System.out.println("Reemplazar: Funcionalidad no implementada.");
                            break;

                        case 9:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.println("Se va a eliminar la hoja actual(hoja " + ch.getIdHoja() +
                                        ", llamada " + ch.getNombreHoja() + "), ¿quieres continuar? (1: Continuar - 2: Cancelar)");
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
                                    if (cd.getNumHojas() == 0) hojaSeleccionada = false;
                                } else System.out.println("Cancelando...");

                            }
                            break;

                        case 10:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.println("La hoja (" + ch.getIdHoja() + ", " + ch.getNombreHoja() + ") tiene " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getFilas() + " filas y " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getColumnas() + " columnas.");
                            }
                            break;

                        case 11:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Ingresa el número de filas a añadir: ");
                                int i = -1;
                                try {
                                    i = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }
                                System.out.print("Ingresa a partir de qué fila añadirlas(actualmente hay " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getFilas() + " filas): ");
                                int j = -1;
                                try {
                                    j = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }

                                ch.addFilas(j, i);
                                System.out.println("Se han añadido " + i + " filas a la hoja (" + ch.getIdHoja() + ", " + ch.getNombreHoja() +
                                        ") a partir de la fila " + j + ", ahora tiene " + cd.getDocumento().getHoja(ch.getIdHoja()).getFilas() +
                                        " filas en total.");
                            }
                            break;

                        case 12:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Ingresa el número de columnas a añadir: ");
                                int i = -1;
                                try {
                                    i = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }
                                System.out.print("Ingresa a partir de qué columna añadirlas(actualmente hay " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getColumnas() + " columnas): ");
                                int j = -1;
                                try {
                                    j = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }

                                ch.addColumnas(j, i);
                                System.out.println("Se han añadido " + i + " columnas a la hoja (" + ch.getIdHoja() + ", " + ch.getNombreHoja() +
                                        ") a partir de la columna " + j + ", ahora tiene " + cd.getDocumento().getHoja(ch.getIdHoja()).getColumnas() +
                                        " columnas en total.");
                            }
                            break;

                        case 13:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Ingresa el número de filas a eliminar: ");
                                int i = -1;
                                try {
                                    i = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }
                                System.out.print("Ingresa a partir de qué fila eliminarlas(actualmente hay " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getFilas() + " filas): ");
                                int j = -1;
                                try {
                                    j = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }

                                ch.eliminarFilas(j, i);
                                System.out.println("Se han eliminado " + i + " filas a la hoja (" + ch.getIdHoja() + ", " + ch.getNombreHoja() +
                                        ") a partir de la fila " + j + ", ahora tiene " + cd.getDocumento().getHoja(ch.getIdHoja()).getFilas() +
                                        " filas en total.");
                            }
                            break;

                        case 14:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Ingresa el número de columnas a eliminar: ");
                                int i = -1;
                                try {
                                    i = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }
                                System.out.print("Ingresa a partir de qué columna eliminarlas(actualmente hay " +
                                        cd.getDocumento().getHoja(ch.getIdHoja()).getColumnas() + " columnas): ");
                                int j = -1;
                                try {
                                    j = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                    break;
                                }

                                ch.eliminarColumnas(j, i);
                                System.out.println("Se han eliminado " + i + " columnas a la hoja (" + ch.getIdHoja() + ", " + ch.getNombreHoja() +
                                        ") a partir de la columna " + j + ", ahora tiene " + cd.getDocumento().getHoja(ch.getIdHoja()).getColumnas() +
                                        " columnas en total.");
                            }
                            break;

                        case 15:
                            if (!hojaSeleccionada) {
                                System.out.println("Actualmente no hay ninguna hoja seleccionada.");
                                break;
                            } else {
                                System.out.print("Selecciona una celda. Puedes especificar su posición usando A1 o 1 1: ");
                                String pos = br.readLine();
                                String[] s = pos.split(" ");
                                Celda celda;
                                if (s.length == 1) { // El usuario ha introducido A1
                                    celda = Traductor.traduceCelda(s[0], ch.getIdHoja());
                                } else { // El usuario ha introducido 1 1
                                    celda = Documento.getDocumento().getHoja(ch.getIdHoja()).getCelda(
                                            new Posicion(Traductor.StringInt(s[0]), Traductor.StringInt(s[1])));
                                }
                                ch.asignaCelda(Traductor.IntString(celda.getPosicion().getFila()),
                                        Traductor.IntString(celda.getPosicion().getColumna()));
                                System.out.println("1: Valor - 2: Referencia - 3: Valor a través de función");
                                System.out.print("Celda correctamente seleccionada. Su valor actual es " + celda.getValor() +
                                        ". Selecciona el tipo de valor a introducir: ");
                                String tip = br.readLine();
                                int tipo = -1;
                                try {
                                    tipo = Integer.parseInt(tip);
                                } catch (NumberFormatException e) {
                                    System.out.println("El valor introducido es incorrecto.");
                                }

                                switch (tipo) {
                                    case -1:
                                        break;

                                    case 1:
                                        System.out.print("Introduce el valor(Decimal -> 123.4; Entero -> 123; Fecha -> 01/02/2003): ");
                                        try {
                                            ch.escribirContenido(br.readLine());
                                        } catch (Exception e) {
                                            System.out.println("Ha habido un error inesperado.");
                                        }
                                        break;

                                    case 2:
                                        System.out.print("Introduce la referencia(estilo $A1): ");
                                        try {
                                            ch.escribirContenido(br.readLine());
                                        } catch (Exception e) {
                                            System.out.println("Ha habido un error inesperado.");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("\t=valorAbsoluto(double a)");
                                        System.out.println("\t=truncarValor(double v, int op)");
                                        System.out.println("\t=aproximarValor(double v)");
                                        System.out.println("\t=convertirValorDB(int dec)");
                                        System.out.println("\t=convertirValorBD(int _b)");
                                        System.out.println("\t=convertirValorDH(int _dec)");
                                        System.out.println("\t=convertirValorHD(String _h)");
                                        System.out.println("\t=convertirValorBH(int _b)");
                                        System.out.println("\t=convertirValorHB(String _h)");
                                        System.out.println("\t=obtenerMes(String _fecha)");
                                        System.out.println("\t=obtenerAño(String _fecha)");
                                        System.out.println("\t=obtenerDia(String _fecha)");
                                        System.out.println("\t=obtenerNombreDia(String _fecha)");
                                        System.out.println("\t=longitudPalabra(String _palabra)");
                                        System.out.println("\t=contarLetra(String _palabra, String _letra)");
                                        System.out.println("\t=reemplazarPalabra(String _txt, int _pos, int _long,  String _ntxt)");
                                        System.out.println("\t=reemplazarCaracter(String _txt, String _cr, String _nc)");
                                        System.out.println("\t=esFechaValida(String _txt)");
                                        System.out.print("Introduce la función con sus argumentos: ");
                                        try {
                                            ch.escribirContenido(br.readLine());
                                        } catch (Exception e) {
                                            System.out.println("Ha habido un error inesperado.");
                                        }
                                        break;
                                }

                                System.out.println("El valor de la celda ahora es " + ch.getCeldaRef().getValor() + ".");

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
