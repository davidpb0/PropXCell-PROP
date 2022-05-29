package main.Presentation.vistas.PantallaPrincipal;

import main.Domain.DomainControllers.ControladorDominio;
import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import main.Presentation.ControladorPresentacion;
import main.Presentation.vistas.PantallaPrincipal.ContextMenus.HojasCtxMenu;
import main.Presentation.vistas.PantallaPrincipal.Tabla.Tabla;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/*
 * Vista Principal
 *
 * v0.0.6
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

    private ArrayList<Tabla> tablas = new ArrayList<>();

    private final Dimension MIN_SIZE = new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.6));

    public PantallaPrincipal(ControladorDominio _cd) throws Exception {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/main/Presentation/imagenes/icons8-ms-excel-80.png"))).getImage());
        this.cd = _cd;
        Documento doc = cd.getControladorDocumento().getDocumento();
        init(doc);
        configuraHerramientas();
    }

    private void init(Documento doc) throws Exception {
        tabbedPane1.removeAll();
        tablas = new ArrayList<>();
        contenidoFormattedTextField.setText("");
        nombre_docTextField.setText(doc.getNombre());

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
        for (int i = 0; i < hojas; i++) {
            creaHoja(filas, columnas, "Hoja " + (i+1), i);
        }
        cd.getControladorHoja().asignaHoja(1);

        setKeyBindings();

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

    private void setKeyBindings () {
        InputMap inputMap = principal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = principal.getActionMap();

        // Nuevo Documento
        inputMap.put(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK), "nuevo documento");
        actionMap.put("nuevo documento", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoDocumento();
            }
        });

        // Guardar Documento
        inputMap.put(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK), "guardar documento");
        actionMap.put("guardar documento", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDocumento();
            }
        });

        // Abrir Documento
        inputMap.put(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK), "cargar documento");
        actionMap.put("cargar documento", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarDocumento();
            }
        });

        // Añadir Hoja
        inputMap.put(KeyStroke.getKeyStroke('N', InputEvent.ALT_DOWN_MASK), "anadir hoja");
        actionMap.put("anadir hoja", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirHoja();
            }
        });

        // Copiar
        inputMap.put(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK), "copiar");
        actionMap.put("copiar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiar();
            }
        });

        // Pegar
        inputMap.put(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK), "pegar");
        actionMap.put("pegar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pegar();
            }
        });

        // Cortar
        inputMap.put(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK), "cortar");
        actionMap.put("cortar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cortar();
            }
        });

    }

    public void nuevaHoja() {
        cd.getControladorDocumento().anadirHoja();
        System.out.println(tabbedPane1.getTabCount());

    }

    public void creaHoja(int filas, int columnas, String nombre, int idx) {
        tablas.add(new Tabla(filas, columnas, contenidoFormattedTextField, cd, idx+1));
        tablas.get(idx).getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK), "none");
        tabbedPane1.addTab(nombre, null, tablas.get(idx), nombre);
    }

    public void configuraHerramientas() {
        String[] archivoOpciones = {"Nuevo Documento", "Cargar Documento", "Guardar Documento", "Separador", "Nueva Hoja", "Eliminar Hoja", "Renombrar Hoja", "Separador",
                "Importar CSV", "Exportar CSV", "Separador", "Detalles", "Salir"};
        for (String s : archivoOpciones) {
            if (s.equals("Separador")) this.archivo.addSeparator();
            else {
                JMenuItem j = new JMenuItem(s);
                switch (s) {
                    case "Nuevo Documento":
                        j.setToolTipText("Ctrl+N");
                        break;
                    case "Cargar Documento":
                        j.setToolTipText("Ctrl+O");
                        break;
                    case "Guardar Documento":
                        j.setToolTipText("Ctrl+S");
                        break;
                    case "Nueva Hoja":
                        j.setToolTipText("Alt+N");
                        break;
                }
                j.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cmd = e.getActionCommand().toLowerCase();
                        switch (cmd) {
                            case "nuevo documento":
                                nuevoDocumento();
                                break;
                            case "guardar documento":
                                guardarDocumento();
                                break;
                            case "cargar documento":
                                cargarDocumento();
                                break;
                            case "nueva hoja":
                                anadirHoja();
                                break;
                            case "renombrar hoja":
                                break;
                            case "eliminar hoja":
                                eliminarHoja();
                                break;
                            case "importar csv":
                                importarCSV();
                                break;
                            case "exportar csv":
                                exportarCSV();
                                break;

                            case "detalles":
                                String message = "" + cd.getControladorDocumento().getDocumento().getNombre() + "\nNúmero de Hojas: " + cd.getControladorDocumento().getNumHojas() + "\nFecha de creación: " + cd.getControladorDocumento().getDocumento().getFecha();
                                JOptionPane.showMessageDialog(Activity,
                                        message,
                                        "Detalles",
                                        JOptionPane.PLAIN_MESSAGE);
                                break;
                            case "salir":
                                if (JOptionPane.showConfirmDialog(
                                        Activity,
                                        "Asegurate de haber guardado.\n¿Salir igualmente?",
                                        "¿Salir sin guardar?",
                                        JOptionPane.WARNING_MESSAGE,
                                        JOptionPane.WARNING_MESSAGE
                                ) == 0) {
                                    ControladorPresentacion cp = new ControladorPresentacion();
                                    cp.iniciaPInicial();
                                    dispose();
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
        String[] editarOpciones = {"Copiar", "Pegar", "Cortar", "Separador", "Ordenar A-Z", "Ordenar Z-A"};
        for (String s : editarOpciones) {
            if (s.equals("Separador")) this.editar.addSeparator();
            else {
                JMenuItem j = new JMenuItem(s);
                switch (s) {
                    case "Cortar":
                        j.setToolTipText("Ctrl+X");
                        break;
                    case "Copiar":
                        j.setToolTipText("Ctrl+C");
                        break;
                    case "Pegar":
                        j.setToolTipText("Ctrl+V");
                        break;
                }
                j.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String cmd = e.getActionCommand().toLowerCase();
                        switch (cmd) {
                            case "cortar":
                                cortar();
                                break;
                            case "copiar":
                                copiar();
                                break;
                            case "pegar":
                                pegar();
                                break;
                            case "ordenar a-z":
                                ordenar(false);
                                break;
                            case "ordenar z-a":
                                ordenar(true);
                                break;
                            default:
                                System.out.println(cmd);
                                break;
                        }
                    }
                });
                this.editar.add(j);
            }
        }
        String[] insertarOpciones = {"Filas", "Columnas"};
        for (String s : insertarOpciones) {
            if (s.equals("Separador")) this.insertar.addSeparator();
            else if (s.equals("Filas") || s.equals("Columnas")) {
                JMenu submenu = new JMenu(s);
                JMenuItem m = new JMenuItem("Insertar " + s + "...");
                m.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int num = Integer.parseInt(JOptionPane.showInputDialog(
                                Activity,
                                "¿ Cuantas " + s.toLowerCase() + " quieres añadir?",
                                "Añadir " + s.toLowerCase(),
                                JOptionPane.PLAIN_MESSAGE
                        ));
                        if ( s.equals("Columnas") ) {
                            cd.getControladorHoja().addColumnas(cd.getControladorHoja().getHojaRef().getColumnas(), num);
                            tablas.get(tabbedPane1.getSelectedIndex()).addColumns(num);
                        }

                        if (s.equals("Filas")) {
                            cd.getControladorHoja().addFilas(cd.getControladorHoja().getHojaRef().getFilas(), num);
                            tablas.get(tabbedPane1.getSelectedIndex()).addRows(num);
                        }
                    }
                });
                submenu.add(m);
                m = new JMenuItem("Insertar " + s.substring(0, s.length() - 1) + " delante...");
                m.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ( s.equals("Columnas") ) {
                            cd.getControladorHoja().addColumnas(1, 1);
                            tablas.get(tabbedPane1.getSelectedIndex()).insertColumn(0);
                        }

                        if (s.equals("Filas")) {
                            cd.getControladorHoja().addFilas(0, 1);
                            tablas.get(tabbedPane1.getSelectedIndex()).insertRow(0);
                        }
                    }
                });
                submenu.add(m);
                m = new JMenuItem("Insertar " + s.substring(0, s.length() - 1) + " detrás...");
                m.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if ( s.equals("Columnas") ) {
                            cd.getControladorHoja().addColumnas(cd.getControladorHoja().getHojaRef().getColumnas(), 1);
                            tablas.get(tabbedPane1.getSelectedIndex()).addColumns(1);
                        }

                        if (s.equals("Filas")) {
                            cd.getControladorHoja().addFilas(cd.getControladorHoja().getHojaRef().getFilas(), 1);
                            tablas.get(tabbedPane1.getSelectedIndex()).addRows(1);
                        }
                    }
                });
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

    private void nuevoDocumento() {
        String val = (String) JOptionPane.showInputDialog(
                Activity,
                "Crear documento:",
                "Nuevo Documento",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "50, 50"
        );
        if (val == null) return;
        if (!val.contains(",")) {
            val = "50, 50";
        }
        String fil_ = val.split(",")[0].strip();
        String col_ = val.split(",")[1].strip();
        if (fil_ == "") fil_ = "50";
        if (col_ == "") col_ = "50";
        int __filas = Integer.parseInt(fil_);
        int __columnas = Integer.parseInt(col_);


        cd.getControladorDocumento().crearDocumento(__filas,__columnas);
        try {
            init(cd.getControladorDocumento().getDocumento());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void guardarDocumento() {
        JFileChooser jf = new JFileChooser();
        jf.setCurrentDirectory(new File("."));
        jf.setDialogTitle("Guardar Archivo...");
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setAcceptAllFileFilterUsed(false);
        if (jf.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            cd.getControladorDocumento().guardarDocumento(f.toString());
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private void cargarDocumento () {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos prop (.prop)", "prop");
        jf.setFileFilter(filtro);
        jf.setCurrentDirectory(new File("."));
        jf.setDialogTitle("Guardar Archivo...");
        jf.setAcceptAllFileFilterUsed(false);
        if (jf.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            cd.getControladorDocumento().cargaDocumento(f.toString());

            try {
                init(cd.getControladorDocumento().getDocumento());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private void anadirHoja () {
        cd.getControladorDocumento().anadirHoja();
        int idx = cd.getControladorDocumento().getNumHojas();
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
    }

    private void renombrarHoja () {
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
    }

    private void eliminarHoja () {
        int idx = tabbedPane1.getSelectedIndex();
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
    }

    private void importarCSV () {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos prop, csv (.prop, .csv)", "prop", "csv");
        jf.setFileFilter(filtro);
        jf.setCurrentDirectory(new File("."));
        jf.setDialogTitle("Importar CSV...");
        jf.setAcceptAllFileFilterUsed(false);
        if (jf.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            cd.getControladorDocumento().cargaCSV(f.toString());
            try {
                cd.getControladorHoja().asignaHoja(cd.getControladorDocumento().getNumHojas());
                cd.getControladorHoja().renombraHoja(f.getName().replace(".csv", ""));
                creaHoja(
                        cd.getControladorHoja().getHojaRef().getFilas(),
                        cd.getControladorHoja().getHojaRef().getColumnas(),
                        cd.getControladorHoja().getNombreHoja(),
                        cd.getControladorHoja().getIdHoja()-1
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private void exportarCSV () {
        JFileChooser jf = new JFileChooser();
        jf.setCurrentDirectory(new File("."));
        jf.setDialogTitle("Exportar CSV...");
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setAcceptAllFileFilterUsed(false);
        if (jf.showOpenDialog(principal) == JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            String nombre = cd.getControladorHoja().getNombreHoja();
            cd.getControladorHoja().exportarHoja(f.toString(), nombre);
        }
    }

    private void cortar () {
        System.out.println("Cortar");
        tablas.get(tabbedPane1.getSelectedIndex()).cortar();
    }

    private void copiar () {
        System.out.println("Copiar");
        tablas.get(tabbedPane1.getSelectedIndex()).copiar();
    }

    private void pegar () {
        System.out.println("Pegar");
        tablas.get(tabbedPane1.getSelectedIndex()).pegar();
    }

    private void ordenar (boolean desc) {
        System.out.println("Ordenar");
        tablas.get(tabbedPane1.getSelectedIndex()).ordenar(desc);
    }

    public static void main(String[] args) throws Exception {
        PantallaPrincipal p = new PantallaPrincipal(ControladorDominio.getControladorDominio());
        p.setVisible(true);
    }
}
