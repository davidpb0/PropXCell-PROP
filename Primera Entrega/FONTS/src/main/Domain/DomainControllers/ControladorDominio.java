package main.Domain.DomainControllers;
/*
 * ClassName DomainControllers.ControladorDominio
 *
 * Version info 0.0.1
 *
 * Author Iván Risueño Martín
 */

public class ControladorDominio {
    private static ControladorDominio instanceOfThisClass;
    private final ControladorBloque contBloque;
    private final ControladorDocumento contDocumento;
    private final ControladorHoja contHoja;

    private ControladorDominio() {
        contBloque = new ControladorBloque();
        contDocumento = new ControladorDocumento();
        contHoja = new ControladorHoja();
    }

    public static ControladorDominio getControladorDominio() {
        if (instanceOfThisClass == null) instanceOfThisClass = new ControladorDominio();
        return instanceOfThisClass;
    }

    public ControladorBloque getControladorBloque() {
        return contBloque;
    }


    public ControladorDocumento getControladorDocumento() {
        return contDocumento;
    }

    public ControladorHoja getControladorHoja() {
        return contHoja;
    }
}
