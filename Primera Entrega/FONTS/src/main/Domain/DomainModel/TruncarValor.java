package main.Domain.DomainModel;


/*
 * ClassName TruncarValor
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase TruncarValor, clase encargada de gestionar la funcion que trunca un valor numerico
 * @author David Perez Barroso
 */
public class TruncarValor extends Funcion{

    private double valor;
    private int op;


    /**
     * Constructora de la clase
     * @param _v valor a truncar almacenado
     * @param _op indicador de posiciones a truncar
     */
    public TruncarValor(double _v, int _op){
        this.valor = _v;
        this.op = _op;

    }


    /**
     * Trunca el valor contenido por la clase tantos numeros como el parametro op almacenado indica.
     * @return Devuelve un string con el valor almacenado truncado
     */
    @Override
    public String execute() {

        String str = String.valueOf(Math.abs(this.valor));

        //Cojo la parte entera del numero
        int n = Integer.parseInt(str.substring(0, str.indexOf('.')));

        //Cojo la parte decimal del numero y la transformo en un entero
        int dn = Integer.parseInt(str.substring(str.indexOf('.') + 1));

        //Calculo los digitos que tiene cada uno y los sumo para saber el total
        int dig = ((int)(Math.log10(n)+1)) + ((int)(Math.log10(dn)+1));

        if (dig >= this.op && this.op >= 0) {
            int indice = (int) Math.pow(10, this.op);
            this.valor = (int) (this.valor * indice);
            this.valor = (double) (this.valor / indice);
            return (String.valueOf(this.valor));
        }
        else return "#ERROR";

    }


}
