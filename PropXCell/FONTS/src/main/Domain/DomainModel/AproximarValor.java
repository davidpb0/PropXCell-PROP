package main.Domain.DomainModel;


/*
 * ClassName AproximarValor
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 *
 */

/**
 * Clase AprixmarValor, clase encargada de gestionar la funcion que aproxima un valor numerico
 * @author David Perez Barroso
 */

public class AproximarValor extends Funcion{

    private double valor;

    /**
     * Constructora de la clase
     * @param _valor valor decimal almacenado en this.valor
     */
    public  AproximarValor(double _valor) {
        this.valor = _valor;
    }

    /** Pre _v es un valor decimal correcto
     * Aproxima el numero decimal guardado en la clase
     * @return Devuelve un String con el valor aproximado
     */
    @Override
    public String execute(){
        this.valor = Math.round(this.valor);
        return (String.valueOf(this.valor));

    }
}
