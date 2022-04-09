
package DomainControllers;
import DomainModel.*;
import static DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 0.0.2
*
* Author David Pérez Barroso
*/

public class ControladorCelda {
   private Celda celdaRef;


   ControladorCelda(){}

   /**
    * Coge una celda de la hoja que le pasa presentación y se guarda la hoja, la posicion y la celda
    * @param _idh identificador de la hoja
    * @param _f fila de la celda
    * @param _c columna de la celda
    */

   public void asignaCeldaPosicion(int _idh, int _f, int _c){
      Documento d = getDocumento();
      Hoja h = d.getHoja(_idh);
      this.celdaRef = h.getCelda(_f, _c);
   }


    /**
     * Trunca el contenido decimal de la celda
     * @param _op numero de decimales a truncar
     * @return Un string con _value truncado a tantos decimales como se ha seleccionado en _op
     */

   public void truncarValor(int _op){
       float v = Float.parseFloat(this.celdaRef.getValor());

       int indice = (int) Math.pow(10, _op);
       v = (int)(v*indice);
       v = (float)(v/indice);
       celdaRef.setValor(String.valueOf(v));

    }


    /**
     * Pone el valor absoluto del valor de la celda
     */
    public void valorAbsoluto(){
       int a = Integer.parseInt(this.celdaRef.getValor());
       a = Math.abs(a);
       celdaRef.setValor(String.valueOf(a));

    }

    /**
     * Aproxima el valor de la celda
     */
    public void aproximarValor(){
        float v = Float.parseFloat(this.celdaRef.getValor());
        v = Math.round(v);
        celdaRef.setValor(String.valueOf(v));

    }


}


