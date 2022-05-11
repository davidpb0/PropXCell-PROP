package main.Presentation;
/*
 * ClassName DomainControllers.ControladorPresentacion
 *
 * Version info 0.0.1
 *
 * Author Iván Risueño Martín
 */
import main.Domain.DomainControllers.ControladorDominio;

public class ControladorPresentacion {
    private final ControladorDominio contDominio;

    public ControladorPresentacion() {
        this.contDominio = new ControladorDominio();
    }

    public ControladorDominio getControladorDominio() {
        return contDominio;
    }
}
