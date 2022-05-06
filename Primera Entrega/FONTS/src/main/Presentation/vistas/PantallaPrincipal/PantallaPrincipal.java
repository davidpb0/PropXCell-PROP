package main.Presentation.vistas.PantallaPrincipal;

import javax.swing.*;
import java.awt.*;

public class PantallaPrincipal extends JFrame {
    private JPanel principal;
    private JTextField nombre_docTextField;
    private JMenuBar barraH;
    private JMenu archivo, editar, insertar, ayuda;

    public PantallaPrincipal() {
        add(principal);
        setResizable(false);
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        configuraHerramientas();
    }

    public void configuraHerramientas() {
        String[] archivoOpciones = {"Nuevo Documento", "Cargar Documento", "Guardar Documento", "Cerrar Documento", "Separador", "Nueva Hoja", "Eliminar Hoja", "Separador",
                "Importar/Exportar", "Separador", "Renombrar Documento", "Separador", "Detalles", "Salir"};
        for (String s : archivoOpciones) {
            if (s.equals("Separador")) this.archivo.addSeparator();
            else if (s.equals("Importar/Exportar")) {
                JMenu submenu = new JMenu(s);
                submenu.getAccessibleContext().setAccessibleName(s);
                JMenuItem m = new JMenuItem("Nueva Hoja(CSV)");
                submenu.add(m);
                m = new JMenuItem("Importar Hoja(CSV)");
                submenu.add(m);
                m = new JMenuItem("Exportar Hoja(CSV)");
                submenu.add(m);
                this.archivo.add(submenu);
            }
            else {
                JMenuItem j = new JMenuItem(s);
                this.archivo.add(j);
            }
        }
        String[] editarOpciones = {"Cortar", "Copiar", "Pegar", "Separador", "Ordenar"};
        for (String s : editarOpciones) {
            if (s.equals("Separador")) this.editar.addSeparator();
            else {
                JMenuItem j = new JMenuItem(s);
                this.editar.add(j);
            }
        }
        String[] insertarOpciones = {"Filas", "Columnas", "Separador", "Función"};
        for (String s : insertarOpciones) {
            if (s.equals("Separador")) this.insertar.addSeparator();
            else if (s.equals("Filas") || s.equals("Columnas")) {
                JMenu submenu = new JMenu(s);
                JMenuItem m = new JMenuItem("Insertar " + s + "...");
                submenu.add(m);
                m = new JMenuItem("Insertar " + s.substring(0, s.length() - 1) + " delante...");
                submenu.add(m);
                m = new JMenuItem("Insertar " + s.substring(0, s.length() - 1) + " detrás...");
                submenu.add(m);
                this.insertar.add(submenu);
            }
            else {
                JMenuItem j = new JMenuItem(s);
                this.insertar.add(j);
            }
        }
        String[] ayudaOpciones = {"Manual de Usuario", "Separador", "Lista de Funciones", "Atajos de Teclado"};
        for (String s : ayudaOpciones) {
            if (s.equals("Separador")) this.ayuda.addSeparator();
            else {
                JMenuItem j = new JMenuItem(s);
                this.ayuda.add(j);
            }
        }


        barraH.add(this.archivo);
        barraH.add(this.editar);
        barraH.add(this.insertar);
        barraH.add(this.ayuda);
    }

    public static void main(String[] args){
        PantallaPrincipal p = new PantallaPrincipal();
        p.setVisible(true);
    }
}
