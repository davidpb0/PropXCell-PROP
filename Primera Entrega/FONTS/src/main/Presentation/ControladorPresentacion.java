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

    /**
     * Creadora pública del Controlador de Presentación.
     */
    public ControladorPresentacion() {
        this.contDominio = ControladorDominio.getControladorDominio();
    }

    /**
     * Getter del ControladorDominio.
     * @return la instancia del ControladorDominio.
     */
    public ControladorDominio getControladorDominio() {
        return contDominio;
    }

    /**
     * Getter de la PantallaPrincipal. Este método sólo se invoca una vez.
     * @return la nueva instancia de la PantallaPrincipal.
     * @throws Exception en caso de que haya algún error en la creación de la PantallaPrincipal.
     */
    public PantallaPrincipal getPantallaPrincipal() throws Exception {
        return new PantallaPrincipal(this.contDominio);
    }

    /**
     * Inicializa la PantallaInicial y la vuelve visible.
     */
    public void iniciaPInicial(){
        this.pIni = new PantallaInicial();
        pIni.inicializaVista();
        pIni.setLocationRelativeTo(null);
        pIni.setVisible(true);
    }
}
