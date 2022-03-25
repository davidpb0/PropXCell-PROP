/*
 * Celda
 *
 * v0.0.1
 *
 * Joaquim Torra Garcia
 */

package DomainModel;

public class Celda {
    private String valor = "";
    private String contenido = "";

    /**
     * @return el valor que muestra la celda, ya sea el contenido o el resultado de la función establecida en el contenido
     */
    public String getValor() {
        return valor;
    }

    /**
     * Asigna de nuevo el valor que la celda debe mostrar.
     * @param _valor el nuevo valor que mostrar
     */
    public void setValor(String _valor) {
        this.valor = _valor;
    }

    /**
     * @return el contenido real de la celda
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Asigna un nuevo contenido a la celda. Si éste contenido empieza con el símbolo "=", asigna el valor "#FUNC"
     * !!! De momento implementa #FUNC para representar que el valor sería el resultado de la función pedida. El valor que pone cambiará una vez tengamos implementadas las funciones necesarias. !!!
     * @param _contenido el nuevo contenido a asignar a la celda
     */
    public void setContenido(String _contenido) {
        this.contenido = _contenido;
        if (_contenido.startsWith("="))
            setValor("#FUNC");
        else
            setValor(_contenido);
    }
}
