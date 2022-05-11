package main.Domain.DomainModel;


/*
 * ClassName Pearson
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class Pearson extends Funcion{

    private String[] valoresX;
    private String[] valoresY;


    /**
     * Creadora de la clase
     * @param _vx valoresx a almacenar
     * @param _vy valoresy a almacenar
     */
    public Pearson(String[] _vx, String[] _vy){
        this.valoresX = _vx;
        this.valoresY = _vy;

    }


    /**
     * Obtiene el coeficiente de correlacion de Pearson resultante de los dos conjuntos de valores almacenados.
     * @return coeficiente de correlacion de Pearson resultante de los dos conjuntos de valores valoresX y valoresY
     * Pre: Tanto valoresX como valoresY tienen el mismo número de elemento
     */
    @Override
    public String execute() {
        double sx = 0;
        double sy = 0;
        double sxx = 0;
        double syy = 0;
        double sxy = 0;
        int n = valoresX.length;

        for(int i = 0; i < n; ++i) {
            double x = Double.parseDouble(valoresX[i]);
            double y = Double.parseDouble(valoresY[i]);

            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }

        // Covarianza = E(XY) - E(X)*E(Y)
        double cov = sxy/n - sx/n * sy/n;
        // Desviación estandard de X = sqrt(E(X^2) - E(X)^2)
        double sigmaX = Math.sqrt(sxx/n - sx/n * sx/n);
        // Desviación estandard de Y = sqrt(E(Y^2) - E(Y)^2)
        double sigmaY = Math.sqrt(syy/n - sy/n * sy/n);

        return String.valueOf(cov / (sigmaX * sigmaY));
    }
}
