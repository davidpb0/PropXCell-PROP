package main.Domain.DomainModel;


/*
 * ClassName ValorAbsoluto
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ValorAbsoluto extends Funcion{


    private double valor;


    /**
     * Creadora de la clase
     * @param _a Numero a almacenar
     */
    public ValorAbsoluto(double _a){
        this.valor = _a;

    }

    /** Pre: _a es un valor decimal correcto
     * Pone el valor absoluto del numero almacenado por la clase en un string
     * @return Devuelve un String con el valor absoluto del numero almacenado
     */
    @Override
    public String execute(){
        //Para enteros que en decimal se escribe -> 5.0
        if((this.valor % 1) == 0) {
            int aux = (int) Math.abs(this.valor);
            return String.valueOf(aux);
        }
        else{
            this.valor = Math.abs(this.valor);
            return String.valueOf(this.valor);
        }

    }


}
