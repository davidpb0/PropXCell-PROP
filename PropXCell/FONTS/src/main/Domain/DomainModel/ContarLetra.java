package main.Domain.DomainModel;


/*
 * ClassName ContarLetra
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase ContarLetra, clase encargada de gestionar la funcion que cuenta las veces que aparece una letra en una palabra o frase
 * @author David Perez Barroso
 */
public class ContarLetra extends Funcion{

    private String palabra;
    private String letra;


    /**
     * Constructora de la clase
     * @param _p palabra a almacenar
     * @param _l letra a almacenar
     */
    public ContarLetra(String _p, String _l){
        this.palabra = _p;
        this.letra = _l;

    }


    /**
     * Cuenta el numero de veces que aparece una letra en una palabra
     * @return Devuelve un string con -1 si no contiene la letra a contar, de lo contrario devuelve la palabra con
     * la letra reemplazada
     */
    @Override
    public String execute() {
        char aux;
        int count = 0;
        if(!this.palabra.contains(this.letra)) return "-1";

        for(int i = 0; i < this.palabra.length(); i++){
            aux = this.palabra.charAt(i);
            if(aux == this.letra.charAt(0)) {
                ++count;
            }
        }
        return String.valueOf(count);


    }
}
