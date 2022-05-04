package main.Presentation.vistas.PCrearDoc;

import main.Presentation.vistas.PantallaInicial.PantallaInicial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CrearDocumento extends JFrame{
    private JPanel principal;
    private JPanel panelizq;
    private JButton eliminarDocumentoButton;
    private JButton BotonCrearDoc;
    private JButton btCargarDoc;
    private JButton BotonHome;
    private JLabel titulo;
    private JButton btclose;
    private JTextField a50TextField;
    private JTextField a50TextField1;
    private JLabel lbColumnas;
    private JLabel lbFilas;
    private JButton button1;

    public CrearDocumento() {
        add(principal);
        setResizable(false);
        setUndecorated(true);
        //setVisible(true);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(1);
            }
        });

        BotonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaInicial pp = new PantallaInicial();
                pp.setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args){
        CrearDocumento p = new CrearDocumento();

    }


}


