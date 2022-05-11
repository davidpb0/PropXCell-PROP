package main.Domain.DomainModel;

import main.Domain.DomainControllers.ControladorHoja;

public class DesvEstandar extends Funcion{

    private String[] valores;


    /**
     * Creadora de la clase
     * @param _v valores a almacenar
     */
    public DesvEstandar(String[] _v){
        this.valores = _v;
    }

    /**
     * Obtiene la desviación estandard de los valores del conjunto almacenado.
     * @return desviación estandard de los elementos de valores
     */
    @Override
    public String execute() {
        Varianza v = new Varianza(this.valores);
        Double d = Math.sqrt(Double.parseDouble(v.execute()));
        return String.valueOf(d);
    }
}
