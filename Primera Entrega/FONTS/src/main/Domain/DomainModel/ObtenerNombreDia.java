package main.Domain.DomainModel;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;


/*
 * ClassName ObtenerNombreDia
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ObtenerNombreDia extends Funcion{

    private String fecha;


    /**
     * Creadora de la clase
     * @param _f fecha a almacenar
     */
    public ObtenerNombreDia(String _f){
        this.fecha = _f;

    }


    /** Pre: fecha es una fecha valida
     * Obtiene el nombre del dia en castellano de la fecha almacenada
     * @return Devuelve un String con el nombre del dia, #ERROR si falla algun parametro del argumento
     */
    @Override
    public String execute() {
        /*Dado que DayOfWeek nos devuelve el dia en ingles, utilizaremos estas dos estructuras de datos para
        traducirlos al castellano*/

        String dyn[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        HashMap<Integer, String> dias = new HashMap<>();

        for(int i = 0; i < 7; ++i){
            dias.put(i+1, dyn[i]);
        }

        Month months[] = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY,
                Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER};

        String dp[] = this.fecha.split("/");

        int y = Integer.parseInt(dp[2]);
        int mt = Integer.parseInt(dp[1]);
        Month m = months[mt-1];
        int dy = Integer.parseInt(dp[0]);

        if(dy >= 0 && y >= 0 && (mt > 0 && mt < 12)) {
            try {
                return dias.get(LocalDate.of(y, m, dy).getDayOfWeek().getValue());
            } catch (Exception e) {
                return "#FECHA_NO_VALIDA";
            }
        } return "#ERROR";
    }
}
