package main.Presentation;

import main.Domain.DomainControllers.ControladorDocumento;
import main.Presentation.vistas.PantallaInicial.PantallaInicial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/*
 * ClassName PantallaInicialController
 *
 * Version info 0.0.2
 *
 * Author David Perez Barroso
 */

public class PantallaInicialController implements ActionListener {

    private PantallaInicial pi;
    private ControladorDocumento cd;

    public PantallaInicialController(PantallaInicial _pi){
        this.pi = _pi;
        this.pi.crearButton.addActionListener(this);
        this.pi.BotonCrearDoc.addActionListener(this);
        this.pi.BotonHome.addActionListener(this);
        this.pi.btCargarDoc.addActionListener(this);
    }

    public void iniciar(){
        pi.setTitle("Hoja de cálculo");
        pi.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pi.crearButton) {
            int f = Integer.parseInt(pi.tfFilas.getText());
            int c = Integer.parseInt(pi.tfColumnas.getText());
            this.cd = new ControladorDocumento(f, c);
            //pasar a la siguiente vista


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

        else if (e.getSource() == pi.btCargarDoc){
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(pi);
            File doc = jf.getSelectedFile();
            if (doc != null){
                System.out.println(doc.getAbsolutePath());
                this.cd = new ControladorDocumento(20, 20);
                cd.cargaDocumento(doc.getAbsolutePath());

            }

           // System.out.println(cd.getDocumento().getHoja(1).getFilas());

        }

    }

}
