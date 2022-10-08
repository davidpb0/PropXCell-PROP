package main.Domain.DomainModel;

/*
 * ClassName Covarianza
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase Covarianza, clase encargada de gestionar la funcion covarianza de valores numericos
 * @author David Perez Barroso
 */
public class Covarianza extends Funcion{

    private String[] valoresX;
    private String[] valoresY;

    /**
     * Creador de la clase
     * @param _vx valoresx a almacenar
     * @param _vy valoresy a almacenar
     */
    public Covarianza(String[] _vx, String[] _vy){
        this.valoresX = _vx;
        this.valoresY = _vy;
    }


    /**
     * Obtiene la covarianza resultante de los dos conjuntos de valores almacenados.
     * @return covarianza resultante de los dos conjuntos de valores valoresX y valoresY
     * Pre: Tanto valoresX como valoresY tienen el mismo número de elementos
     */
    @Override
    public String execute() {
        double sx = 0;
        double sy = 0;
        double sxy = 0;
        int n = this.valoresX.length;

        for(int i = 0; i < n; ++i) {
            double x = Double.parseDouble(this.valoresX[i]);
            double y = Double.parseDouble(this.valoresY[i]);

            sx += x;
            sy += y;
            sxy += x * y;
        }

        // Covarianza = E(XY) - E(X)*E(Y)
        return String.valueOf(sxy/n - sx/n * sy/n);
    }
}
