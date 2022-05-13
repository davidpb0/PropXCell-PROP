package main.Domain.DomainModel;
/*
 * ClassName Mediana
 *
 * Version info 1.0.0
 *
 * Iván Risueño Martín
 */
import java.util.Arrays;

public class Multiplicacion extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Multiplicacion(String[] _v){
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
        for (double v : valores) resultado *= v;

        return String.valueOf(resultado);
    }
}
