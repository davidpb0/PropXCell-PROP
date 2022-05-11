package main.Domain.DomainModel;


/*
 * ClassName ObtenerAño
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
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

        int dy = Integer.parseInt(d);
        if(dy >= 0) return d;
        else return "#ERROR";
    }
}
