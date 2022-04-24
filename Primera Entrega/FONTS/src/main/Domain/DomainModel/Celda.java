package main.Domain.DomainModel;

/*
 * Celda
 *
 * v0.1.0
 *
 * Joaquim Torra Garcia
 */


import java.util.LinkedList;

public class Celda {
    private String valor = "";
    private String contenido = "";
    private Posicion posicion;
    private final LinkedList<Celda> referenciantes = new LinkedList<>();


    /**
     * Constructora de una celda vacía
     * @param _pos objeto posición asignado a la celda
     */
    public Celda(Posicion _pos) {
        this.valor = "";
        this.contenido = "";
        this.posicion = _pos;
    }

    /**
     * Constructora de una celda con contenido
     * @param _pos objeto posición asignado a la celda
     * @param contenido el contenido que se asigna a la celda en su creación
     */
    public Celda(Posicion _pos, String contenido) {
        this.posicion = _pos;
        this.setContenido(contenido);
    }

    /**
     * Constructora de una celda copia de otra, sin copiar sus referencias.
     * @param c celda a copiar
     */
    public Celda(Celda c) {
        this.contenido = c.getContenido();
        this.valor = c.getValor();
        this.posicion = c.getPosicion();
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
            c.setValorRef(_valor, this);
        } 
        this.valor = _valor;
    }

    /**
     * Asigna el nuevo valor que la celda debe mostrar, que proviene de una referencia y lo envía a todas las celdas referenciantes.
     * Evita los bucles de referencias.
     * @param _valor el valor de la referencia a mostrar
     * @param _ref la celda que manda la referencia
     */
    protected void setValorRef(String _valor, Celda _ref) {
        if(_ref == this) {
            this.referenciantes.pop();
        }
        for (Celda c : this.referenciantes) {
            c.setValorRef(_valor, _ref);
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
     * Asigna un nuevo contenido a la celda.
     * @param _contenido el nuevo contenido a asignar a la celda
     */
    public void setContenido(String _contenido) {
        this.contenido = _contenido;
    }

    /**
     * @return el objeto posición de la celda
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * @param posicion el objeto posición de la celda
     */
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    /**
     * @return la lista de celdas referenciantes
     */
    public LinkedList<Celda> getReferenciantes() {
        return this.referenciantes;
    }

    /**
     * Asigna una lista de referenciantes a la celda
     * @param refs lista de referenciantes a asignar
     */
    public void setReferenciantes(LinkedList<Celda> refs) {
        for(Celda ref : refs) {
           if(ref == this || ref.getReferenciantes().contains(this)) {
               System.out.println("!!! AUTO REFERENCIA o REFERENCIA CIRCULAR NO PERMITIDAS !!!");
               return;
           }
        }
        this.referenciantes.addAll(refs);
        this.setValor(this.valor);
    }

    /**
     * Añade un referenciante a la lista, que verá su valor actualizado cuando el valor de la celda cambie.
     * @param _ref la celda referenciante
     */
    public void addReferenciante(Celda _ref) {
        if(_ref == this || _ref.getReferenciantes().contains(this)) {
            System.out.println("!!! AUTO REFERENCIA o REFERENCIA CIRCULAR NO PERMITIDAS !!!");
            return;
        }
        referenciantes.add(_ref);
        _ref.setValorRef(this.valor, this);
    }

    /**
     * Borra un referenciante de la lista.
     * @param _ref la celda referenciante a borrar
     */
    public void borrarReferenciante(Celda _ref) {
        this.referenciantes.remove(_ref);
    }

    @Override
    public boolean equals(Object _o){
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        Celda that = (Celda) _o;
        return this.posicion.getFila() == that.posicion.getFila() && this.posicion.getColumna() == that.posicion.getColumna();
    }
}
