package drivers.components.ControladorDocumento;

import main.Domain.DomainControllers.ControladorDocumento;
import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.StreamSupport;

public class DriverControladorDocumento {

    private static BufferedReader br;
    private static Documento doc;
    private static ControladorDocumento cd = null;

    private static void initCD () {
        cd = new ControladorDocumento();
        doc = cd.getDocumento();
        System.out.println("ControladorDocumento inicializado correctamente!");
    }

    private static void ops() {
        System.out.println("-------------------------------------");
        System.out.println("1: Reinicializar Controlador");
        System.out.println("2: Información Documento");
        System.out.println("3: Ver Hojas");
        System.out.println("4: Informacion Hoja");
        System.out.println("5: Cerrar Documento");
        System.out.println("6: Crear Documento");
        System.out.println("7: Eliminar Documento");
        System.out.println("8: Añadir Hoja");
        System.out.println("9: Eliminar Hoja");
        System.out.println("10: Cambiar Nombre Hoja");

        System.out.println("0: Salir");
        System.out.print("Escoja una opción entre las mostradas: ");
    }

    private static int read() throws IOException {
        String r = br.readLine();
        if (r.length() == 0) {
            r = "-1";
        }

        return Integer.parseInt(r);
    }

    public static void main(String[] args) {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            initCD();
            ops();
            int op = read();
            while(op != 0) {
                switch (op) {
                    case -1:
                        System.out.print("Escoja una opción entre las mostradas: ");
                        op = read();
                        continue;
                    case 1:
                        initCD();
                        break;
                    case 2:
                        DocInfo();
                        break;
                    case 3:
                        VerHojas();
                        break;
                    case 4:
                        InfoHoja();
                        break;
                    case 5:
                        cd.cerrarDocumento();
                        doc = cd.getDocumento();
                        System.out.print("Documento cerrado.");
                        System.in.read();
                        break;
                    case 6:
                        cd.crearDocumento();
                        doc = cd.getDocumento();
                        System.out.print("Nuevo documento: " + doc);
                        System.in.read();
                        break;
                    case 7:
                        cd.eliminarDocumento();
                        doc = cd.getDocumento();
                        System.out.print("Documento eliminado.");
                        System.in.read();
                        break;
                    case 8:
                        AddHoja();
                        break;
                    case 9:
                        DelHoja();
                        break;
                    case 10:
                        ChangeNameHoja();
                        break;
                }
                System.out.println();
                ops();
                System.out.print("Escoja una opción entre las mostradas: ");
                op = read();
            }
            System.out.println("...terminando programa...");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("Ha surgido un problema inesperado.");
        }
    }

    private static void DocInfo () throws IOException {
        System.out.println("--Información Documento--");
        if (doc == null) {
            System.out.print("No hay ningún documento abierto.");
            System.in.read();
            return;
        }
        System.out.println("Objeto: " + doc);
        System.out.println("Nombre: " + doc.getNombre());
        System.out.println("Numero de hojas: " + doc.getNumHojas());
        System.out.print("Fecha Creación: " + doc.getFecha());
        System.in.read();
    }

    private static void VerHojas () throws IOException {
       System.out.println("--Ver Hojas--");
       if (doc == null) {
           System.out.print("No hay ningún documento abierto.");
           System.in.read();
           return;
       }
       System.out.print(doc.getHojasContenidas().toString());
       System.in.read();
    }

    private static void InfoHoja () throws IOException {
        System.out.println("--Información Hoja--");
        if (doc == null) {
            System.out.print("No hay ningún documento abierto.");
            System.in.read();
            return;
        }
        System.out.print("ID hoja: ");
        int id = read();
        if (id == -1) {
            System.out.print("Ninguna hoja seleccionada.");
            System.in.read();
            return;
        }
        Hoja h = doc.getHoja(id);
        System.out.println("Objeto: " + h);
        System.out.println("Nombre: " + h.getNombre());
        System.out.print("Dimensiones: " + h.getFilas() + "x" + h.getColumnas());
        System.in.read();
    }

    private static void AddHoja () throws IOException {
        System.out.println("--Añadir Hoja--");
        System.out.print("Filas: ");
        int filas = read();
        System.out.print("Columnas: ");
        int col = read();
        if (filas == -1 || col == -1) {
            cd.anadirHoja();
            System.out.print("Hoja con filas y columnas por defecto creada! (50x50)");
        } else {
            cd.anadirHoja(filas, col);
            System.out.print("Hoja con " + filas + " filas y " + col + " columnas creada!");
        }
        System.in.read();
    }

    private static void DelHoja () throws IOException {
        System.out.println("--Eliminar Hoja--");
        System.out.print("ID hoja: ");
        int id = read();
        if (id == -1) {
            System.out.print("Ninguna hoja seleccionada.");
            System.in.read();
            return;
        }
        doc.eliminaHoja(id);
        System.out.print("Hoja " + id + " eliminada correctamente!");
        System.in.read();
    }

    private static void ChangeNameHoja () throws IOException {
        System.out.println("--Cambiar Nombre Hoja--");
        System.out.print("ID hoja: ");
        int id = read();
        if (id == -1) {
            System.out.print("Ninguna hoja seleccionada.");
            System.in.read();
            return;
        }
        System.out.print("Nombre: ");
        String name = br.readLine();
        cd.asignaNombreHoja(id, name);
        System.out.print("Nombre " + name + " asignado a Hoja" + id + "!");
        System.in.read();
    }
}
