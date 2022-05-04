package main.Presentation.vistas.PantallaPrincipal;

import main.Presentation.vistas.PCrearDoc.CrearDocumento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PantallaInicial extends JFrame{
    private JPanel principal;
    private JPanel panelizq;
    private JButton eliminarDocumentoButton;
    private JButton BotonCrearDoc;
    private JButton btCargarDoc;
    private JButton BotonHome;
    private JLabel titulo;
    private JButton btclose;
    private JTextPane descripción;

    public PantallaInicial() {
        add(principal);
        setResizable(false);
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

        BotonCrearDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearDocumento cd = new CrearDocumento();
                cd.setVisible(true);
                setVisible(false);

            }
        });
    }

    public static void main(String[] args){
        PantallaInicial p = new PantallaInicial();
        p.setVisible(true);

    }


}


