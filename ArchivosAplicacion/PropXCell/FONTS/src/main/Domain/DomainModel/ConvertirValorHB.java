package main.Domain.DomainModel;


/*
 * ClassName ConvertirValorHB
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ConvertirValorHB, clase encargada de gestionar la funcion que convierte un valor hexadecimal en uno binario
 * @author David Perez Barroso
 */
public class ConvertirValorHB extends Funcion{

    private String hexa;

    /**
     * Constructora de la clase
     * @param _hexa numero hexadeciamol a almacenar
     */
    public ConvertirValorHB(String _hexa){
        this.hexa = _hexa;

    }

    /**
     * Covierte el valor almacenado en base hexadecimal a base binaria
     * @return Devuelve un String con el valor en binario, -1 si hay un error en el formato del numero
     */
    @Override
    public String execute() {
        try {
            int decConv = Integer.parseInt(this.hexa, 16);
            return Integer.toBinaryString(decConv);
        } catch (NumberFormatException e) {
            return "-1";
        }
    }
}
