package main.Domain.DomainModel;

/*
 * ClassName ConvertirValorDB
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ConvertirValorDB, clase encargada de gestionar la funcion que convierte un valor decimal en uno binario
 * @author David Perez Barroso
 */
public class ConvertirValorDB extends Funcion{

    private int decimal;


    /**
     * Creadora de la clase
     * @param _dec parametro decimal a almacenar
     */
    public ConvertirValorDB(int _dec){
        this.decimal = _dec;

    }

    /** Pre: _dec es un numero decimal correcto
     * Covierte el valor en base decimal almacenado a base binaria
     * @return Devuelve un String con el valor en binario
     */
    @Override
    public String execute() {
        return Integer.toBinaryString(this.decimal);
    }
}
