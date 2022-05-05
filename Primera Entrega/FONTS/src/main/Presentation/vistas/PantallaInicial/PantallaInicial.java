package main.Presentation.vistas.PantallaInicial;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PantallaInicial extends JFrame{
    public JPanel principal;
    public JPanel panelizq;
    public JButton eliminarDocumentoButton;
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

    public PantallaInicial() {
        add(principal);
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelCrearDoc.setVisible(false);

    }





}


