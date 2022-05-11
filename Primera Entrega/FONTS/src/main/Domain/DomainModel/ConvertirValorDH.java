package main.Domain.DomainModel;


/*
 * ClassName ConvertirValorDH
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ConvertirValorDH extends Funcion{

    private int decimal;


    /**
     * Constructora de la clase
     * @param _d decimal a almacenar
     */
    public ConvertirValorDH(int _d){
        this.decimal = _d;

    }

    /** Pre: decimal es un numero decimal correcto
     * Covierte el valor almacenado en base decimal a base hexadecimal
     * @return Devuelve un String con el valor en hexadecimal
     */
    @Override
    public String execute() {
        return Integer.toHexString(this.decimal);
    }
}
