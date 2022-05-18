package main.Presentation.vistas.PantallaPrincipal;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import main.Presentation.vistas.PantallaPrincipal.ContextMenus.HojasCtxMenu;
import main.Presentation.vistas.PantallaPrincipal.Tabla.Tabla;
import main.Presentation.vistas.PantallaPrincipal.Tabla.TablaListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/*
 * Vista Principal
 *
 * v0.0.5
 *
 * Joaquim Torra Garcia
 */

public class PantallaPrincipal extends JFrame {
    private final ControladorDominio cd;

    private JPanel principal;
    private JTextField nombre_docTextField;
    private JLabel fechaCreacion;
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

    private final ArrayList<Tabla> tablas = new ArrayList<>();

    private final Dimension MIN_SIZE = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.6));

    public PantallaPrincipal(ControladorDominio _cd) throws Exception {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/Presentation/imagenes/icons8-ms-excel-80.png"))).getImage());
        this.cd = _cd;
        // cd.getControladorDocumento().crearDocumento();
        cd.getControladorDocumento().crearDocumento(50, 50);
        Documento doc = cd.getControladorDocumento().getDocumento();
        init(doc);
    }

    private void init(Documento doc) throws Exception {
        int hojas = doc.getNumHojas();
        int filas = doc.getHoja(1).getFilas();
        int columnas = doc.getHoja(1).getColumnas();
        setTitle(doc.getNombre() + " - PROPxCEL");
        fechaCreacion.setText("Fecha de creación: " + doc.getFecha());
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
        cd.getControladorHoja().asignaHoja(1);

        nombre_docTextField.setText(doc.getNombre());
        nombre_docTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doc.setNombre(nombre_docTextField.getText());
                setTitle(doc.getNombre() + " - PROPxCEL");
            }
        });

        contenidoFormattedTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tabla t = tablas.get(tabbedPane1.getSelectedIndex());
                t.enviarContenido(e, e.getActionCommand(), t.getSelectedRowEnd()+1, t.getSelectedColumnEnd());
            }
        });

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                try {
                    cd.getControladorHoja().asignaHoja(tabbedPane1.getSelectedIndex()+1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        HojasCtxMenu hojasCtxMenu = new HojasCtxMenu(tabbedPane1, Activity, tablas, contenidoFormattedTextField);
        tabbedPane1.setComponentPopupMenu(hojasCtxMenu);
    }

    public void nuevaHoja() {
        cd.getControladorDocumento().anadirHoja();
        System.out.println(tabbedPane1.getTabCount());

    }

    public void creaHoja(int filas, int columnas, String nombre, int idx) {
        tablas.add(new Tabla(filas, columnas, contenidoFormattedTextField, cd, idx));
        tabbedPane1.addTab(nombre, null, tablas.get(idx), nombre);
    }

    public void configuraHerramientas() {
        String[] archivoOpciones = {"Nuevo Documento", "Cargar Documento", "Guardar Documento", "Cerrar Documento", "Separador", "Nueva Hoja", "Eliminar Hoja", "Renombrar Hoja", "Separador",
                "Importar/Exportar", "Separador", "Separador", "Detalles", "Salir"};
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
                j.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cmd = e.getActionCommand().toLowerCase();
                        int idx;
                        switch (cmd) {
                            case "nueva hoja":
                                cd.getControladorDocumento().anadirHoja();
                                idx = cd.getControladorDocumento().getNumHojas();
                                try {
                                    cd.getControladorHoja().asignaHoja(idx);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                Hoja h = cd.getControladorHoja().getHojaRef();
                                creaHoja(h.getFilas(), h.getColumnas(), "Hoja " + idx, idx-1);
                                tabbedPane1.setSelectedIndex(idx-1);
                                try {
                                    cd.getControladorHoja().asignaHoja(idx);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            case "renombrar hoja":
                                String antiguoNombre = tabbedPane1.getTitleAt(tabbedPane1.getSelectedIndex());
                                String nuevoNombre = (String) JOptionPane.showInputDialog(
                                        Activity,
                                        "Cambiar Nombre:",
                                        "Renombrar Hoja",
                                        JOptionPane.PLAIN_MESSAGE,
                                        null,
                                        null,
                                        antiguoNombre
                                );
                                try {
                                    cd.getControladorDocumento().asignaNombreHoja(nuevoNombre);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                tabbedPane1.setTitleAt(tabbedPane1.getSelectedIndex(), cd.getControladorHoja().getNombreHoja());
                                break;
                            case "eliminar hoja":
                                idx = tabbedPane1.getSelectedIndex();
                                int idH = ((Tabla) tabbedPane1.getComponentAt(tabbedPane1.getSelectedIndex())).getIdH();
                                if (cd.getControladorDocumento().getNumHojas() <= 1) {
                                    JOptionPane.showMessageDialog(
                                            Activity,
                                            "No puedes eliminar la última hoja",
                                            "Eliminar Hoja",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                    return;
                                }
                                cd.getControladorDocumento().eliminarHoja(idH+1);
                                tabbedPane1.remove(idx);
                                idH = ((Tabla) tabbedPane1.getComponentAt(tabbedPane1.getSelectedIndex())).getIdH();
                                try {
                                    cd.getControladorHoja().asignaHoja(idH);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                break;

                            default:
                                System.out.println(cmd);
                                break;
                        }
                    }
                });
                this.archivo.add(j);
            }
        }
        String[] editarOpciones = {"Cortar", "Copiar", "Pegar", "Separador", "Ordenar"};
        for (String s : editarOpciones) {
            if (s.equals("Separador")) this.editar.addSeparator();
            else {
                JMenuItem j = new JMenuItem(s);
                j.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cmd = e.getActionCommand().toLowerCase();
                    }
                });
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

    public static void main(String[] args) throws Exception {
        PantallaPrincipal p = new PantallaPrincipal(ControladorDominio.getControladorDominio());
        p.setVisible(true);
    }
}
