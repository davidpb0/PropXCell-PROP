package main.Domain.DomainModel;


/*
 * ClassName LongitudPalabra
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase LongitudPalabra, clase encargada de gestionar la funcion que mide la longitud de una palabra o frase
 * @author David Perez Barroso
 */
public class LongitudPalabra extends Funcion{

    private String palabra;

    /**
     * Constructora de la clase
     * @param _p palabra a almacenar
     */
    public LongitudPalabra(String _p){
        this.palabra = _p;

    }


    /**
     * Mide la longitud de la palabra almacenada
     * @return Devuelve un String con la longitud de la palabra
     */
    @Override
    public String execute() {
        return String.valueOf(this.palabra.length());
    }
}
