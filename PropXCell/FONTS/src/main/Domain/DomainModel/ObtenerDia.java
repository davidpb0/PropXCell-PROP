package main.Domain.DomainModel;


/*
 * ClassName ObtenerAño
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
/**
 * Clase ObtenerDia, clase encargada de gestionar la funcion que obtiene el dia de una fecha
 * @author David Perez Barroso
 */
public class ObtenerDia extends Funcion{

    private String fecha;


    /**
     * Constructora de la clase
     * @param _f fecha a almacenar
     */
    public ObtenerDia(String _f){
        this.fecha = _f;
    }


    /** Pre: _fecha es una fecha valida
     * Obtiene el dia en forma numerica de la fecha almacenada
     * @return Devuelve un String con el dia, #ERROR si falla algun parametro del argumento
     */
    @Override
    public String execute() {
        String dp[] = this.fecha.split("/");
        String d = dp[0];
        if (d.length() == 2 && d.charAt(0) == '0') d = String.valueOf(d.charAt(1));

        int dy = Integer.parseInt(d);
        if(dy > 0) return d;
        else return "#ERROR";
    }
}
