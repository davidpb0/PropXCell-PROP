package main.Domain.DomainModel;

/*
 * ClassName ConvertirValorBH
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ConvertirValorBH, clase encargada de gestionar la funcion que convierte un valor binario en uno hexadecimal
 * @author David Perez Barroso
 */
public class ConvertirValorBH extends Funcion{


    private int binario;


    /**
     * Constructora de la clase
     * @param _b valor binario a almacenar
     */
    public ConvertirValorBH(int _b){
        this.binario = _b;
    }

    /** Pre: _b es un numero binario correcto
     * Covierte el valor almacenado en base binaria a base hexadecimal
     * @return Devuelve un String con el valor en hexadecimal
     */
    @Override
    public String execute() {
        int dec = Integer.parseInt(String.valueOf(this.binario), 2);
        return Integer.toHexString(dec);
    }
}
