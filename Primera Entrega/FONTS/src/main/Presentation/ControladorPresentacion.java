package main.Presentation;
/*
 * ClassName DomainControllers.ControladorPresentacion
 *
 * Version info 0.0.2
 *
 * Author Iván Risueño Martín
 */

import main.Domain.DomainControllers.ControladorDominio;
import main.Presentation.vistas.PantallaInicial.PantallaInicial;
import main.Presentation.vistas.PantallaPrincipal.PantallaPrincipal;

public class ControladorPresentacion {
    private final ControladorDominio contDominio;
    private PantallaInicial pIni;

    public ControladorPresentacion() {
        this.contDominio = ControladorDominio.getControladorDominio();
    }

    public ControladorDominio getControladorDominio() {
        return contDominio;
    }

    public PantallaPrincipal getPantallaPrincipal() {
        return new PantallaPrincipal(this.contDominio);
    }

    public void iniciaPInicial() {
        this.pIni = new PantallaInicial();
        pIni.inicializaVista();
        pIni.setVisible(true);
    }
}
