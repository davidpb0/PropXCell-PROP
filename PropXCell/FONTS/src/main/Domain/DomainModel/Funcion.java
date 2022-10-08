package main.Domain.DomainModel;


/*
 * ClassName Funcion
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
/**
 * Clase Funcion, clase abstracta padre de todas las funciones del sistema
 * @author David Perez Barroso
 */
public abstract class Funcion {

    /**
     * Funcion abstracta que se implementa en sus subclases codificandola con la funcion necesaria
     * @return el resultado de una funcion en String
     */
    public abstract String execute();

}
