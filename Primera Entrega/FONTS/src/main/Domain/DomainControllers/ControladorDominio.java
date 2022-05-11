package main.Domain.DomainControllers;
/*
 * ClassName DomainControllers.ControladorDominio
 *
 * Version info 0.0.1
 *
 * Author Iván Risueño Martín
 */

public class ControladorDominio {
    private final ControladorBloque contBloque;
    private final ControladorCelda contCelda;
    private final ControladorDocumento contDocumento;
    private final ControladorHoja contHoja;

    public ControladorDominio() {
        contBloque = ControladorBloque.getControldorBloque();
        contCelda = ControladorCelda.getControladorCelda();
        contDocumento = ControladorDocumento.getControladorDocumento();
        contHoja = ControladorHoja.getControladorHoja();
    }

    public ControladorBloque getControladorBloque() {
        return contBloque;
    }

    public ControladorCelda getControladorCelda() {
        return contCelda;
    }

    public ControladorDocumento getControladorDocumento() {
        return contDocumento;
    }

    public ControladorHoja getControladorHoja() {
        return contHoja;
    }
}
