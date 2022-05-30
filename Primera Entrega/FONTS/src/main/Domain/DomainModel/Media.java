package main.Domain.DomainModel;


/*
 * ClassName Media
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
/**
 * Clase Media, clase encargada de gestionar la funcion media de valores numericos
 * @author David Perez Barroso
 */
public class Media extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v conjunto de valores a almacenar
     */
    public Media(String[] _v){
        this.valores = _v;
    }

    /**
     * Obtiene la media aritmetica de los valores del conjunto almacenado.
     * @return media aritmética de los elementos de valores
     */
    @Override
    public String execute() {
        double media = 0;

        for (String v : this.valores) media += Double.parseDouble(v);

        return String.valueOf(media / this.valores.length);
    }
}
