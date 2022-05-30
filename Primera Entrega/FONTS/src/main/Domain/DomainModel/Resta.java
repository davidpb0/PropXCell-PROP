package main.Domain.DomainModel;
/*
 * ClassName Resta
 *
 * Version info 1.0.0
 *
 * Iván Risueño Martín
 */
import java.util.Arrays;

/**
 * Clase Resta, utlilizada como implementacion de la resta dentro de la hoja.
 * @author Iván Risueño Martín
 */

public class Resta extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Resta(String[] _v){
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
        for (int i = 1; i < valores.length; ++i) resultado -= valores[i];

        return String.valueOf(resultado);
    }
}
