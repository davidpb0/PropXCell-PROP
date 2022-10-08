package main.Domain.DomainModel;
/*
 * ClassName Division
 *
 * Version info 1.0.0
 *
 * Iván Risueño Martín
 */
import java.util.Arrays;

/**
 * Clase Division, utilizada como implementacion de la funcionalidad de dividir dentro de la hoja.
 * @author Iván Risueño Martín
 */

public class Division extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Division(String[] _v){
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

        double resultado = valores[0];
        for (int i = 1; i < valores.length; ++i) {
            if (valores[i] == 0) return "#ERRORDIV";
            resultado /= valores[i];
        }

        return String.valueOf(resultado);
    }
}
