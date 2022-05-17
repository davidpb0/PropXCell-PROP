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
 * Author David Pérez Barroso
 */
public class PantallaInicial extends JFrame {

    public JPanel principal;
    public JPanel panelizq;
    public JButton bteliminarDoc;
    public JButton BotonCrearDoc;
    public JButton btCargarDoc;
    public JButton BotonHome;
    public JLabel titulo;
    public JButton btclose;
    public JTextPane descripción;
    public JButton crearButton;
    public JTextField tfFilas;
    public JTextField tfColumnas;
    public JLabel lbFilas;
    public JPanel panelCrearDoc;
    public JLabel lbColumnas;
    public JLabel lbDoc;

    private ControladorDocumento cd;
    private ControladorPresentacion cp;


    public PantallaInicial() {
        setTitle("Hoja de cálculo");

        setIconImage(new ImageIcon(getClass().getResource("/main/Presentation/imagenes/icons8-ms-excel-80.png")).getImage());

        add(principal);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelCrearDoc.setVisible(false);

    }


    public void inicializaVista() {

        cd = ControladorDominio.getControladorDominio().getControladorDocumento();
        cp = new ControladorPresentacion();

        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("Archivos prop, csv, pdf (.prop, .csv, .pdf)", "prop", "csv", "pdf");

        ActionListener crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int f = Integer.parseInt(tfFilas.getText());
                int c = Integer.parseInt(tfColumnas.getText());
                cd.crearDocumento();
                //pasar a la siguiente vista

                setVisible(false);
                PantallaPrincipal pp = cp.getPantallaPrincipal();
                pp.setVisible(true);

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
                descripción.setVisible(false);
            }
        };

        ActionListener home = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                descripción.setVisible(true);
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
                    System.out.println(doc.getAbsolutePath());
                    System.out.println(doc.getName());
                    // cd.cargaDocumento(doc.getAbsolutePath());

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

                    int result = JOptionPane.showConfirmDialog(principal, "¿Está seguro que desea eliminar el archivo seleccionado?");
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


