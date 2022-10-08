package main.Presentation.vistas.PantallaInicial;

import main.Domain.DomainControllers.ControladorDocumento;
import main.Domain.DomainControllers.ControladorDominio;
import main.Presentation.ControladorPresentacion;
import main.Presentation.vistas.PantallaPrincipal.PantallaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
 * ClassName PantallaInicial
 *
 * Version info 0.1.0
 *
 * Author David Perez Barroso
 */

/**
 * Clase PantallaInicial, vista de la Pantalla Inicial
 * @author David Perez Barroso
 */
public class PantallaInicial extends JFrame {

    private JPanel principal;
    private JPanel panelizq;
    private JButton bteliminarDoc;
    private JButton BotonCrearDoc;
    private JButton btCargarDoc;
    private JButton BotonHome;
    private JLabel titulo;
    private JTextPane descripcion;
    private JButton crearButton;
    private JTextField tfFilas;
    private JTextField tfColumnas;
    private JLabel lbFilas;
    private JPanel panelCrearDoc;
    private JLabel lbColumnas;
    private JLabel lbDoc;

    private ControladorDocumento cd;
    private ControladorPresentacion cp;


    /**
     * Creadora de la vista
     */
    public PantallaInicial() {
        setTitle("PropXcel");

        setIconImage(new ImageIcon(getClass().getResource("/main/Presentation/imagenes/icons8-ms-excel-80.png")).getImage());

        add(principal);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelCrearDoc.setVisible(false);

    }

    /**
     * Iniciliza los atributos controladores de la vista asi como todas las funcionalidades de sus botones
     */
    public void inicializaVista() {

        cp = new ControladorPresentacion();
        cd = cp.getControladorDominio().getControladorDocumento();

        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("Archivos prop (.prop)", "prop");

        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int f = Integer.parseInt(tfFilas.getText());
                int c = Integer.parseInt(tfColumnas.getText());

                if(f < 1 || c < 1){
                    JOptionPane.showMessageDialog(principal,
                            "Asegúrese que ha introducido un valor en filas o columnas mayor que 0",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);

                }
                else {
                    cd.crearDocumento(f, c);
                    //pasar a la siguiente vista

                    setVisible(false);
                    PantallaPrincipal pp = null;
                    try {
                        pp = cp.getPantallaPrincipal();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    pp.setVisible(true);
                }

                //Esto es para probar que se crea correctamente
           /* System.out.println(cd.getDocumento().getNombre());
            System.out.println(cd.getDocumento().getNumHojas());
            System.out.println(cd.getDocumento().getHoja(1).getFilas());
            System.out.println(cd.getDocumento().getHoja(1).getCeldas().size());*/
            }
        };

        ActionListener crearDoc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCrearDoc.setVisible(true);
                descripcion.setVisible(false);
            }
        };

        ActionListener home = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descripcion.setVisible(true);
                panelCrearDoc.setVisible(false);
            }
        };

        ActionListener cargarDoc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();

                jf.setFileFilter(filtro);

                jf.showOpenDialog(principal);
                File doc = jf.getSelectedFile();

                if (doc != null) {
                    cd.cargaDocumento(doc.getAbsolutePath());

                    setVisible(false);
                    PantallaPrincipal pp = null;
                    try {
                        pp = cp.getPantallaPrincipal();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    pp.setVisible(true);

                }
            }
        };

        ActionListener eliminaDoc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfl = new JFileChooser();

                jfl.setFileFilter(filtro);

                jfl.showOpenDialog(principal);
                File docbr = jfl.getSelectedFile();

                if (docbr != null){
                    File fl = new File(docbr.getAbsolutePath());

                    int result = JOptionPane.showConfirmDialog(principal,
                            "¿Está seguro que desea eliminar el archivo seleccionado?",
                            "¿Eliminar documento?",
                            JOptionPane.WARNING_MESSAGE,
                            JOptionPane.WARNING_MESSAGE);
                    if (result == 0 && fl.exists() && fl.delete()){
                        JOptionPane.showMessageDialog(principal, "Archivo borrado correctamente");
                    }
                    else JOptionPane.showMessageDialog(principal, "El archivo no se ha borrado");
                }

            }
        };
        crearButton.addActionListener(crear);
        BotonCrearDoc.addActionListener(crearDoc);
        BotonHome.addActionListener(home);
        btCargarDoc.addActionListener(cargarDoc);
        bteliminarDoc.addActionListener(eliminaDoc);
    }




}


