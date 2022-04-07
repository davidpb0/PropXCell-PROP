
package DomainControllers;
import DomainModel.*;
import static DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 0.0.1
*
* Author David Pérez Barroso
*/

public class ControladorCelda {
   private Celda celdaRef;



   ControladorCelda(){}

   /**
    * Coge una celda de la hoja que le pasa presentación
    * @param _idh identificador de la hoja
    * @param _f fila de la celda
    * @param _c columna de la celda
    */

   public void asignaCelda(int _idh, int _f, int _c){
      Documento d = getDocumento();
      Hoja h = d.getHoja(_idh);
      Celda cl = h.getCelda(_f, _c);
      this.celdaRef = cl;
   }


    /**
     * Trunca el contenido decimal de la celda
     * @param _op numero de decimales a truncar
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


