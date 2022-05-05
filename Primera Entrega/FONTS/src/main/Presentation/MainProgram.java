package main.Presentation;

import main.Presentation.vistas.PantallaInicial.PantallaInicial;

public class MainProgram {

    public static void main(String[] args) {
        PantallaInicial vista = new PantallaInicial();

        PantallaInicialController pic = new PantallaInicialController(vista);
        pic.iniciar();
        vista.setVisible(true);



    }
}
