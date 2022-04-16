package DomainControllers;

import DomainModel.*;

public class ControladorBloque {

    private BloqueTemporalCopiado bloqueCopiado;

    public void copiar() {
        bloqueCopiado = new BloqueTemporalCopiado();
        bloqueCopiado.setCortar(false);
    }

    public void cortar() {
        bloqueCopiado = new BloqueTemporalCopiado();
        bloqueCopiado.setCortar(true);
    }

    public void pegar(int _numHoja, int _FilaInicio, int _filaInicio, int _columnaInicio) {
        Hoja h = Documento.getDocumento().getHoja(_numHoja);
        // Falta la operacion de Hoja para reemplazar una celda por otra
    }
}
