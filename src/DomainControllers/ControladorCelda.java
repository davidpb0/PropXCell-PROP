
package DomainControllers;
import DomainModel.*;

import java.lang.reflect.Array;
import java.util.Date;

import static DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 0.0.3
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
     * @param _v valor decimal a truncar
     * @param _op numero de decimales a truncar
     * @return Un string con _value truncado a tantos decimales como se ha seleccionado en _op
     */

   public void truncarValor(double _v, int _op) {

       String str = String.valueOf(Math.abs(_v));

       //Cojo la parte entera del numero
       int n = Integer.parseInt(str.substring(0, str.indexOf('.')));

       //Cojo la parte decimal del numero y la transformo en un entero
       int dn = Integer.parseInt(str.substring(str.indexOf('.') + 1));

       //Calculo los digitos que tiene cada uno y los sumo para saber el total
       int dig = ((int)(Math.log10(n)+1)) + ((int)(Math.log10(dn)+1));

       if (dig >= _op && _op >= 0) {
           int indice = (int) Math.pow(10, _op);
           _v = (int) (_v * indice);
           _v = (double) (_v / indice);
           celdaRef.setValor(String.valueOf(_v));
       }
       else celdaRef.setValor("#ERROR");

    }


    /**
     * Pone el valor absoluto del valor de la celda
     * @param _a numero a poner en valor absoluto
     */
    public void valorAbsoluto(double _a){
        if((_a % 1) == 0) {
             int aux = (int) Math.abs(_a);
            celdaRef.setValor(String.valueOf(aux));
        }
        else{
            _a = Math.abs(_a);
            celdaRef.setValor(String.valueOf(_a));
        }

    }

    /**
     * Aproxima el valor de la celda
     * @param _v valor a aproximar
     */
    public void aproximarValor(double _v){
        _v = Math.round(_v);
        celdaRef.setValor(String.valueOf(_v));

    }

    /**
     * Covierte el valor decimal de la celda a binario
     * @param _dec valor decimal a convertir en binario
     */
    public void convertirValorDB(int _dec){
        this.celdaRef.setValor(Integer.toBinaryString(_dec));
    }

    /**
     * Convierte el valor binario de la celda a decimal
     * @param _b valor binario a convertir en decimal
     */
    public void convertirValorBD(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        this.celdaRef.setValor(String.valueOf(dec));
    }

    /**
     * Convierte el valor decimal de la celda a hexadecimal
     * @param _dec valor en decimal a convertir en hexadecimal
     */
    public void convertirValorDH(int _dec){
        this.celdaRef.setValor(Integer.toHexString(_dec));
    }


    /**
     * Convierte el valor hexadecimal de la celda a decimal
     * @param _h valor hexadecimal a convertir en decimal
     */
    public void convertirValorHD(String _h){
        int dec = Integer.parseInt(_h, 16);
        this.celdaRef.setValor(String.valueOf(dec));
    }

    /**
     * Convierte el valor binario de la celda en hexadecimal
     * @param _b valor binario a convertir en hexadecimal
     */
    public void convertirValorBH(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        this.celdaRef.setValor(Integer.toHexString(dec));
    }


    /**
     * Convierte el valor hexadeciaml de la celda en binario
     * @param _h valor hexadecimal a convertir en binario
     */
    public void convertirValorHB(String _h){
        int decConv = Integer.parseInt(_h, 16);
        this.celdaRef.setValor(Integer.toBinaryString(decConv));
    }

    /**
     * Obtiene el mes de la fecha que hay en la celda
     * @param _fecha de donde se obtiene el mes
     */
    public void obtenerMes(String _fecha){
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        String dp[] = _fecha.split("/");
        String m = dp[1];

        int mth = Integer.parseInt(m);
        if(mth > 0 && mth <= 12){
            this.celdaRef.setValor(months[mth-1]);
        }
        else this.celdaRef.setValor("#ERROR");

    }








}


