
package DomainControllers;
import DomainModel.*;



/*ClassName ControladorCelda
         *
         * Version info 1.0
         *
         * Author David Pérez Barroso
         */
public class ControladorCelda {
   private Celda celdaRef;



   ControladorCelda(){}


   public void asignaCelda(Celda c){
      this.celdaRef = c;
   }


    /**
     * @param _value = String
     * @param _op = integer, numero de decimales a truncar
     * @return Un string con _value truncado a tantos decimales como se ha seleccionado en _op
     */

 /*  public String truncarValor(int _op){
       float v = this.celdaRef.getValor();

       int indice = (int) Math.pow(10, _op);
       v = (int)(v*indice);
       v = (float)(v/indice);
       return Float.toString(v);


    }*/


}


