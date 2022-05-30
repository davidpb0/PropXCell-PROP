package main.Domain.DomainModel;


/*
 * ClassName Varianza
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase Varianza, clase encargada de gestionar la funcion que calcula la varianza de unos valores numericos
 * @author David Perez Barroso
 */
public class Varianza extends Funcion{


    private String[] valores;

    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public Varianza(String[] _v){
        this.valores = _v;
    }

    /**
     * Obtiene la varianza de los valores del conjunto almacenado.
     * @return varianza de los elementos de _valores
     */
    @Override
    public String execute() {
        double sx = 0;
        double sxx = 0;
        int n = this.valores.length;

        for (String x : this.valores) {
            double valor = Double.parseDouble(x);
            sx += valor;
            sxx += valor * valor;
        }

        // Varianza = E(X^2) - E(X)^2
        return String.valueOf(sxx/n - sx/n * sx/n);
    }
}
