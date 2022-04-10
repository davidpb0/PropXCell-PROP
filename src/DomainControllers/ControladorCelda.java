
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

   public void truncarValor(int _op) {
       float v = Float.parseFloat(this.celdaRef.getValor());

       String str = String.valueOf(Math.abs(v));

       //Cojo la parte entera del numero
       int n = Integer.parseInt(str.substring(0, str.indexOf('.')));

       //Cojo la parte decimal del numero y la transformo en un entero
       int dn = Integer.parseInt(str.substring(str.indexOf('.') + 1));

       //Calculo los digitos que tiene cada uno y los sumo para saber el total
       int dig = ((int)(Math.log10(n)+1)) + ((int)(Math.log10(dn)+1));

       if (dig >= _op && _op >= 0) {
           int indice = (int) Math.pow(10, _op);
           v = (int) (v * indice);
           v = (float) (v / indice);
           celdaRef.setValor(String.valueOf(v));
       }
       else celdaRef.setValor("#ERROR");

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

    /**
     * Covierte el valor decimal de la celda a binario
     */
    public void convertirValorDB(){
        int dec = Integer.parseInt(this.celdaRef.getValor());
        this.celdaRef.setValor(Integer.toBinaryString(dec));
    }

    /**
     * Convierte el valor binario de la celda a decimal
     */
    public void convertirValorBD(){
        int decConv = Integer.parseInt(this.celdaRef.getValor(), 2);
        this.celdaRef.setValor(String.valueOf(decConv));
    }

    /**
     * Convierte el valor decimal de la celda a hexadecimal
     */
    public void convertirValorDH(){
        int dec = Integer.parseInt(this.celdaRef.getValor());
        this.celdaRef.setValor(Integer.toHexString(dec));
    }


    /**
     * Convierte el valor hexadecimal de la celda a decimal
     */
    public void convertirValorHD(){
        int decConv = Integer.parseInt(this.celdaRef.getValor(), 16);
        this.celdaRef.setValor(String.valueOf(decConv));
    }

    /**
     * Convierte el valor binario de la celda en hexadecimal
     */
    public void convertirValorBH(){
        int decConv = Integer.parseInt(this.celdaRef.getValor(), 2);
        this.celdaRef.setValor(Integer.toHexString(decConv));
    }


    /**
     * Convierte el valor hexadeciaml de la celda en binario
     */
    public void convertirValorHB(){
        int decConv = Integer.parseInt(this.celdaRef.getValor(), 16);
        this.celdaRef.setValor(Integer.toBinaryString(decConv));
    }








}


