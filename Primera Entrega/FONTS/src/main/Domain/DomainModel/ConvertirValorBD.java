package main.Domain.DomainModel;


/*
 * ClassName ConvertirValorBD
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ConvertirValorBD, clase encargada de gestionar la funcion que convierte un valor binario en uno decimal
 * @author David Perez Barroso
 */
public class ConvertirValorBD extends Funcion{


    private int binario;

    /**
     * Constructora de la clase
     * @param _b parametro en binario a almacenar
     */
    public ConvertirValorBD(int _b){
        this.binario = _b;

    }

    /** Pre: _b es un numero binario correcto
     * Covierte el valor en base binaria almacenado a base decimal
     * @return Devuelve un String con el valor en decimal
     */
    @Override
    public String execute() {
        int dec = Integer.parseInt(String.valueOf(this.binario), 2);
        return (String.valueOf(dec));
    }
}
