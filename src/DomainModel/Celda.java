package DomainModel;

/*
 * Celda
 *
 * v0.0.2
 *
 * Joaquim Torra Garcia
 */


import java.util.ArrayList;
import java.util.Vector;

public class Celda {
    private String valor = "";
    private String contenido = "";
    private final ArrayList<Celda> referenciantes = new ArrayList<>();

    /**
     * Constructora de una celda vacía
     */
    public Celda() {
        this.valor = "";
        this.contenido = "";
    }

    /**
     * Constructora de una celda con contenido
     * @param contenido el contenido que se asigna a la celda en su creación
     */
    public Celda(String contenido) {
        this.setContenido(contenido);
    }

    /**
     * @return el valor que muestra la celda, ya sea el contenido o el resultado de la función establecida en el contenido
     */
    public String getValor() {
        return this.valor;
    }

    /**
     * Asigna de nuevo el valor que la celda debe mostrar y lo envía a todas las celdas referenciantes.
     * @param _valor el nuevo valor que mostrar
     */
    public void setValor(String _valor) {
        for (Celda c : this.referenciantes) {
            c.setValor(_valor);
        } 
        this.valor = _valor;
    }

    /**
     * @return el contenido real de la celda
     */
    public String getContenido() {
        return this.contenido;
    }

    /**
     * Asigna un nuevo contenido a la celda. Si éste contenido empieza con el símbolo "=", asigna el valor "#FUNC"
     * !!! De momento implementa #FUNC para representar que el valor sería el resultado de la función pedida. El valor que pone cambiará una vez tengamos implementadas las funciones necesarias. !!!
     * @param _contenido el nuevo contenido a asignar a la celda
     */
    public void setContenido(String _contenido) {
        /*
        !! Lo que hay que conseguir:

            if (this.contenido = ref)
            then
               ref.borrarRef(me)

            this.contenido = _contenido

            type = detectContentType ();
            switch (type)
                case FUNCTION:
                    valor = function.result
                case REFERENCE:
                    ref.añadirRef(me)
                case DATE:
                case INTEGER:
                default:
                    this.valor = _contenido
         */

        this.contenido = _contenido;
        if (_contenido.startsWith("=")) {
            setValor("#FUNC");
            if (_contenido.startsWith("=$")) {
                setValor("#REF");
            }
        }
        else
            setValor(_contenido);

        this.contenido = _contenido;
    }

    /**
     * @return la lista de celdas referenciantes
     */
    public ArrayList<Celda> getReferenciantes() {
        return this.referenciantes;
    }

    /**
     * Añade un referenciante a la lista, que verá su valor actualizado cuando el valor de la celda cambie.
     * @param _ref la celda referenciante
     */
    public void addReferenciante(Celda _ref) {
        referenciantes.add(_ref);
        _ref.setValor(this.valor);
    }

    /**
     * Borra un referenciante de la lista.
     * @param _ref la celda referenciante a borrar
     */
    public void borrarReferenciante(Celda _ref) {
        this.referenciantes.remove(_ref);
    }

}
