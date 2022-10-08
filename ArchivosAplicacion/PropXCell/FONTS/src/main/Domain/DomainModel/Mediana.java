package main.Domain.DomainModel;

import java.util.Arrays;


/*
 * ClassName Mediana
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase Mediana, clase encargada de gestionar la funcion mediana de valores numericos
 * @author David Perez Barroso
 */
public class Mediana extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Mediana(String[] _v){
        this.valores = _v;

    }

    /**
     * Obtiene la mediana de los valores del conjunto almacenado.
     * @return mediana de los elementos de _valores
     */
    @Override
    public String execute() {
        //Convertimos el array de Strings a array de Double
        double[] valores = Arrays.stream(this.valores).mapToDouble(Double::parseDouble).toArray();

        Arrays.sort(valores);
        double mediana;
        if (valores.length % 2 == 0) mediana = (valores[valores.length/2] + valores[valores.length/2 - 1]) / 2;
        else mediana = valores[valores.length/2];

        return String.valueOf(mediana);
    }
}
