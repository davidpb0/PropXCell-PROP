package main.Domain.DomainModel;
/*
 * ClassName Suma
 *
 * Version info 1.0.0
 *
 * Iván Risueño Martín
 */
import java.util.Arrays;

/**
 * Clase Suma, utilizada como implementación de la suma de dos o más valores dentro de la hoja.
 * @author Iván Risueño Martín
 */

public class Suma extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Suma(String[] _v){
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

        double resultado = 0;
        for (double v : valores) resultado += v;

        return String.valueOf(resultado);
    }
}
