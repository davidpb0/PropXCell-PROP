package main.Presentation.vistas.PantallaPrincipal;

import main.Domain.DomainControllers.ControladorDominio;
import main.Presentation.vistas.PantallaPrincipal.Tabla.Tabla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * Vista Principal
 *
 * v0.0.3
 *
 * Joaquim Torra Garcia
 */

public class PantallaPrincipal extends JFrame {
    private ControladorDominio cd;

    private JPanel principal;
    private JTextField nombre_docTextField;
    private JMenuBar barraH;
    private JMenu archivo, editar, insertar, eliminar, ayuda;
    private JPanel Window_Header;
    private JPanel Activity;
    private JPanel Act_Header;
    private JPanel Act_Main;
    private JPanel Header_Opts;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JFormattedTextField contenidoFormattedTextField;
    private JTabbedPane tabbedPane1;

    private ArrayList<Tabla> tablas = new ArrayList<>();

    private Dimension MIN_SIZE = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.6));

    public PantallaPrincipal(ControladorDominio _cd) {
        this.cd = _cd;
        //cd.getControladorDocumento().crearDocumento();
        init(1, 50, 50);
    }

    public PantallaPrincipal(int hojas, int filas, int columnas, ControladorDominio _cd) {
        this.cd = _cd;
        init(hojas, filas, columnas);
    }

    private void init(int hojas, int filas, int columnas) {
        setTitle("Document - PROPxCEL");
        add(principal);
        setResizable(true);
        setMinimumSize(MIN_SIZE);
        setSize(MIN_SIZE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configuraHerramientas();
        for (int i = 1; i <= hojas; i++) {
            creaHoja(filas, columnas, "Hoja " + i, i-1);
        }
        contenidoFormattedTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablas.get(tabbedPane1.getSelectedIndex()).changeContent(e.getActionCommand());
            }
        });
    }

    public void creaHoja(int filas, int columnas, String nombre, int idx) {
        JPanel panel = new JPanel();
        panel.add(new Tabla(filas, columnas, contenidoFormattedTextField, cd, idx));
        tablas.add(new Tabla(filas, columnas, contenidoFormattedTextField, cd, idx));
        tablas.get(idx).setName(nombre);

        tabbedPane1.addTab(nombre, null, tablas.get(idx), nombre);
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
        String[] eliminarOpciones = {"Filas", "Columnas"};
        for (String s : eliminarOpciones) {
            if (s.equals("Filas") || s.equals("Columnas")) {
                JMenu submenu = new JMenu(s);
                JMenuItem m = new JMenuItem("Eliminar " + s + "...");
                submenu.add(m);
                m = new JMenuItem("Eliminar " + s.substring(0, s.length() - 1) + " posterior...");
                submenu.add(m);
                m = new JMenuItem("Eliminar " + s.substring(0, s.length() - 1) + " anterior...");
                submenu.add(m);
                m = new JMenuItem("Eliminar " + s.substring(0, s.length() - 1) + " actual...");
                submenu.add(m);
                this.eliminar.add(submenu);
            }
            else {
                JMenuItem j = new JMenuItem(s);
                this.eliminar.add(j);
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
        barraH.add(this.eliminar);
        barraH.add(this.ayuda);
    }

    public static void main(String[] args){
        PantallaPrincipal p = new PantallaPrincipal(new ControladorDominio());
        p.setVisible(true);
    }
}
