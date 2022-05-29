package main.Domain.DomainModel;


/*
 * ClassName ObtenerAño
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ObtenerAno extends Funcion{


    private String fecha;


    /**
     * Constructora de la clase
     * @param _f fecha a almacenar
     */
    public ObtenerAno(String _f){
        this.fecha = _f;

    }


    /** Pre: _fecha es una fecha valida
     * Obtiene el año de la fecha almacenada
     * @return Devuelve un String con el año, #ERROR si falla algun parametro del argumento
     */
    @Override
    public String execute() {

        String dp[] = this.fecha.split("/");
        String y = dp[2];

        int yr = Integer.parseInt(y);
        if(yr >= 0) return y;
        else return"#ERROR";
    }
}
