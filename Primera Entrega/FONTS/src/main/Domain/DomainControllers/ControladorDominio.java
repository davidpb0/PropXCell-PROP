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

    /**
     * Constructora para el Controlador de Dominio. Crea el objeto e inicializa el resto de controladores.
     */
    private ControladorDominio() {
        contBloque = new ControladorBloque();
        contDocumento = new ControladorDocumento();
        contHoja = new ControladorHoja();
    }

    /**
     * Getter del Controlador de Dominio.
     * @return la instancia singleton del Controlador de Dominio.
     */
    public static ControladorDominio getControladorDominio() {
        if (instanceOfThisClass == null) instanceOfThisClass = new ControladorDominio();
        return instanceOfThisClass;
    }

    /**
     * Getter del Controlador de Bloque.
     * @return la instancia del Controlador de Bloque.
     */
    public ControladorBloque getControladorBloque() {
        return contBloque;
    }

    /**
     * Getter del Controlador de Documento.
     * @return la instancia del Controlador de Documento.
     */
    public ControladorDocumento getControladorDocumento() {
        return contDocumento;
    }

    /**
     * Getter del Controlador de Hoja.
     * @return la instancia del Controlador de Hoja.
     */
    public ControladorHoja getControladorHoja() {
        return contHoja;
    }
}
