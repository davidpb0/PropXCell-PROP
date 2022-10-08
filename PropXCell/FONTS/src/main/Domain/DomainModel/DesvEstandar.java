package main.Domain.DomainModel;

import main.Domain.DomainControllers.ControladorHoja;

/*
 * ClassName DesvEstandar
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */

/**
 * Clase DesvEstandar, clase encargada de gestionar la funcion desviacion estandar de valores numericos
 * @author David Perez Barroso
 */

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
