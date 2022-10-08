package main.Domain.DomainModel;


/*
 * ClassName ObtenerMes
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
/**
 * Clase ObtenerMes, clase encargada de gestionar la funcion que obtiene el mes de una fecha
 * @author David Perez Barroso
 */
public class ObtenerMes extends Funcion{

    private String fecha;


    /**
     * Constructora de la clase
     * @param _f fecha a almacenar
     */
    public ObtenerMes(String _f){
        this.fecha = _f;

    }


    /** Pre: _fecha es una fecha valida
     * Obtiene el mes de la fecha almacenada
     * @return Devuelve un String con el mes, #ERROR si falla algun parametro del argumento
     */
    @Override
    public String execute() {
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
                "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        String dp[] = this.fecha.split("/");
        String m = dp[1];

        int mth = Integer.parseInt(m);
        if(mth > 0 && mth <= 12){
            return (months[mth-1]);
        }
        return "#ERROR";
    }
}
