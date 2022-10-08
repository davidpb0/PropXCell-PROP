package main.Domain.DomainModel;


/*
 * ClassName ConvertirValorHD
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ConvertirValorHD, clase encargada de gestionar la funcion que convierte un valor hexadecimal en uno decimal
 * @author David Perez Barroso
 */
public class ConvertirValorHD extends Funcion{


    private String hexa;


    /**
     * Constructora de la clase
     * @param _h valor en hexadecimal a alamcenar
     */
    public ConvertirValorHD(String _h){
        this.hexa = _h;
    }

    /**
     * Covierte el valor almacenado en base hexadecimal a base decimal
     * @return Devuelve un String con el valor en decimal, -1 si hay un error en el formato del numero
     */
    @Override
    public String execute() {
        try {
            int dec = Integer.parseInt(this.hexa, 16);
            return String.valueOf(dec);
        } catch (NumberFormatException e) {
            return "-1";
        }
    }
}
