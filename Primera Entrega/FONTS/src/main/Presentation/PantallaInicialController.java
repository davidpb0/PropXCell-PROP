package main.Presentation;

import main.Domain.DomainControllers.ControladorDocumento;
import main.Presentation.vistas.PantallaInicial.PantallaInicial;
import main.Presentation.vistas.PantallaPrincipal.PantallaPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
 * ClassName PantallaInicialController
 *
 * Version info 0.0.4
 *
 * Author David Perez Barroso
 */

public class PantallaInicialController implements ActionListener {

    private PantallaInicial pi;
    private PantallaPrincipal pp;
    private ControladorDocumento cd;

    public PantallaInicialController(PantallaInicial _pi){
        this.pi = _pi;
        this.pi.crearButton.addActionListener(this);
        this.pi.BotonCrearDoc.addActionListener(this);
        this.pi.BotonHome.addActionListener(this);
        this.pi.btCargarDoc.addActionListener(this);
        this.pi.bteliminarDoc.addActionListener(this);
    }

    public void iniciar(){
        pi.setTitle("Hoja de cálculo");
        pi.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FileNameExtensionFilter filtro =
                new FileNameExtensionFilter("Archivos prop, csv, pdf (.prop, .csv, .pdf)", "prop", "csv", "pdf");

        if (e.getSource() == pi.crearButton) {
            int f = Integer.parseInt(pi.tfFilas.getText());
            int c = Integer.parseInt(pi.tfColumnas.getText());
            this.cd = ControladorDocumento.getControladorDocumento();
            this.cd.crearDocumento();
            //pasar a la siguiente vista

            pi.setVisible(false);
            pp = new PantallaPrincipal();
            pp.setVisible(true);



            //Esto es para probar que se crea correctamente
           /* System.out.println(cd.getDocumento().getNombre());
            System.out.println(cd.getDocumento().getNumHojas());
            System.out.println(cd.getDocumento().getHoja(1).getFilas());
            System.out.println(cd.getDocumento().getHoja(1).getCeldas().size());*/
        }

        else if (e.getSource() == pi.BotonCrearDoc){
            pi.panelCrearDoc.setVisible(true);
            pi.descripción.setVisible(false);

        }

        else if (e.getSource() == pi.BotonHome){
            pi.descripción.setVisible(true);
            pi.panelCrearDoc.setVisible(false);

        }

        else if (e.getSource() == pi.btCargarDoc) {
            JFileChooser jf = new JFileChooser();

            jf.setFileFilter(filtro);

            jf.showOpenDialog(pi);
            File doc = jf.getSelectedFile();

            if (doc != null) {
                System.out.println(doc.getAbsolutePath());
                System.out.println(doc.getName());
                this.cd = ControladorDocumento.getControladorDocumento();
               // cd.cargaDocumento(doc.getAbsolutePath());

            }
        }

        else if (e.getSource() == pi.bteliminarDoc){
                JFileChooser jfl = new JFileChooser();

                jfl.setFileFilter(filtro);

                jfl.showOpenDialog(pi);
                File docbr = jfl.getSelectedFile();

                if (docbr != null){
                   File fl = new File(docbr.getAbsolutePath());

                   int result = JOptionPane.showConfirmDialog(pi, "¿Está seguro que desea eliminar el archivo seleccionado?");
                   if (result == 0 && fl.exists() && fl.delete()){
                       JOptionPane.showMessageDialog(pi, "Archivo borrado correctamente");
                   }
                   else JOptionPane.showMessageDialog(pi, "El archivo no se ha borrado");
                }

        }

           // System.out.println(cd.getDocumento().getHoja(1).getFilas());



    }

}
