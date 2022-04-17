package drivers.components.ControladorBloque;

import main.Domain.DomainModel.Celda;
import main.Domain.DomainModel.Posicion;

public class StubBloqueTemporalCopiado {
    public Celda[][] celdas = new Celda[3][3];
    public Boolean cortar;

    public StubBloqueTemporalCopiado() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.celdas[i][j] = new Celda(new Posicion(i, j), String.valueOf(i+j));
            }
        }
    }

    public void setCortar(Boolean _cortar) {
        this.cortar = _cortar;
    }

    public Boolean getCortar() {
        return this.cortar;
    }

    public Celda getCelda(int i, int j) {
        return this.celdas[i - 1][j - 1];
    }

}
