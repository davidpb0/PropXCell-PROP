
package main.Domain.DomainControllers;

import main.Domain.DomainModel.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import static main.Domain.DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 0.0.5
*
* Author David Pérez Barroso
*/

public class ControladorCelda {
   private Celda celdaRef;
   private Hoja hojaAct;


   public ControladorCelda(){}

   /**
    * Se guarda una celda de la hoja, fila y columna que le pasa presentación
    * @param _idh identificador de la hoja
    * @param _f fila de la celda
    * @param _c columna de la celda
    */

   public void asignaCeldaPosicion(String _idh, String _f, String _c){
      Documento d = getDocumento();
      Hoja h = d.getHoja(Integer.parseInt(_idh));
      this.hojaAct = h;
      this.celdaRef = h.getCelda(new Posicion(Integer.parseInt(_f), Integer.parseInt(_c)));
   }


    /**
     * Escribe el contenido que le pasan en la celda, si es una funcion, llama a la funcion correspondiente y ademas
     * escribe su valor en la celda
     * @param _content contenido a escribir en la celda
     */
  public void escribirContenido(String _content){
       this.celdaRef.setContenido(_content);

       String type = Traductor.detecta(_content);
       String[] arg;
      // if (_content = ref) ref.borrarRef(me);
       switch (type) {
           case "#ABS":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               valorAbsoluto(Double.parseDouble(arg[0]));
               break;

           case "#TRUNC":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 2) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?") && !!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               truncarValor(Double.parseDouble(arg[0]), Integer.parseInt(arg[1]));
               break;

           case "#APROX":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               aproximarValor(Double.parseDouble(arg[0]));
               break;

           case "#VDB":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorDB(Integer.parseInt(arg[0]));
               break;

           case "#VBD":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorBD(Integer.parseInt(arg[0]));
               break;

           case "#VHD":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorHD(arg[0]);
               break;

           case "#VDH":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorDH(Integer.parseInt(arg[0]));
               break;

           case "#VHB":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorHB(arg[0]);
               break;

           case "#VBH":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               convertirValorBH(Integer.parseInt(arg[0]));
               break;

           case "#MES":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               obtenerMes(arg[0]);
               break;
           case "#AÑO":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               obtenerAño(arg[0]);
               break;
           case "#DIAS":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               obtenerDia(arg[0]);
               break;
           case "#NDIA":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               obtenerNombreDia(arg[0]);
               break;
           case "#LONG":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               longitudPalabra(arg[0]);
               break;
           case "#REFERENCIA":
               //falta añadir la celda referenciada a la refrenciante
               arg = Traductor.getArgumentos(_content, hojaAct.getId());
               this.celdaRef.setValor(arg[0]);
               break;
           case "#MEDIA":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               boolean b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
             /*  double m = ControladorHoja.media(arg);
               this.celdaRef.setValor(String.valueOf(m));*/
               break;
           case "#MEDIANA":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
               /*double m = ControladorHoja.mediana(arg);
               this.celdaRef.setValor(String.valueOf(m));*/
               break;
           case "#VAR":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
               /*double m = ControladorHoja.varianza(arg);
               this.celdaRef.setValor(String.valueOf(m));*/

               break;
           case "#COV":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
               /*double m = ControladorHoja.covarianza(arg);
               this.celdaRef.setValor(String.valueOf(m));*/
               break;
           case "#DESV":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
               /*double m = ControladorHoja.desvEstandar(arg);
               this.celdaRef.setValor(String.valueOf(m));*/
               break;
           case "#COEFP":
               arg = Traductor.getArgumentos(_content, hojaAct.getId());

               b  = false;
               for(String v : arg){
                   if(!v.matches("[+-]?\\d*(\\.\\d+)?")){
                       this.celdaRef.setValor("#ERROR");
                       b = true;
                       break;
                   }
               }
               if (b) break;
               /*double m = ControladorHoja.CorrelacionPearson(arg);
               this.celdaRef.setValor(String.valueOf(m));*/
               break;
           default:
               this.celdaRef.setValor(_content);
       }
   }


   public boolean esFechaValida(String _fecha){
       String dp[] = _fecha.split("/");
       if(dp.length != 3) return false;
       if(!dp[0].matches("[+-]?\\d*(\\.\\d+)?")) return false;
       if(!dp[1].matches("[+-]?\\d*(\\.\\d+)?")) return false;
       if(!dp[2].matches("[+-]?\\d*(\\.\\d+)?")) return false;
       return true;
   }

    /**
     * Trunca el numero decimal introducido y lo pone en el valor de la celda
     * @param _v valor decimal a truncar
     * @param _op numero de decimales a truncar
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
           celdaRef.setContenido(String.valueOf(_v));
       }
       else celdaRef.setContenido("#ERROR");

    }


    /**
     * Pone el valor absoluto del numero introducido en el valor de la celda
     * @param _a numero a poner en valor absoluto
     */
    public void valorAbsoluto(double _a){
        //Para enteros que en decimal se escribe -> 5.0
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
     * Aproxima el numero decimal introducido y lo pone en el valor de la celda
     * @param _v valor a aproximar
     */
    public void aproximarValor(double _v){
        _v = Math.round(_v);
        celdaRef.setValor(String.valueOf(_v));

    }

    /**
     * Covierte el valor en base decimal introducido y lo deja en el valor de la celda en base binaria
     * @param _dec valor decimal a convertir en binario
     */
    public void convertirValorDB(int _dec){
        this.celdaRef.setValor(Integer.toBinaryString(_dec));
    }

    /**
     * Covierte el valor en base binaria introducido y lo deja en el valor de la celda en base decimal
     * @param _b valor binario a convertir en decimal
     */
    public void convertirValorBD(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        this.celdaRef.setValor(String.valueOf(dec));
    }

    /**
     * Covierte el valor en base decimal introducido y lo deja en el valor de la celda en base hexadecimal
     * @param _dec valor en decimal a convertir en hexadecimal
     */
    public void convertirValorDH(int _dec){
        this.celdaRef.setValor(Integer.toHexString(_dec));
    }


    /**
     * Covierte el valor en base hexadecimal introducido y lo deja en el valor de la celda en base decimal
     * @param _h valor hexadecimal a convertir en decimal
     */
    public void convertirValorHD(String _h){
        int dec = Integer.parseInt(_h, 16);
        this.celdaRef.setValor(String.valueOf(dec));
    }

    /**
     * Covierte el valor en base binairia introducido y lo deja en el valor de la celda en base hexadecimal
     * @param _b valor binario a convertir en hexadecimal
     */
    public void convertirValorBH(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        this.celdaRef.setValor(Integer.toHexString(dec));
    }


    /**
     * Covierte el valor en base hexadecimal introducido y lo deja en el valor de la celda en base binaria
     * @param _h valor hexadecimal a convertir en binario
     */
    public void convertirValorHB(String _h){
        int decConv = Integer.parseInt(_h, 16);
        this.celdaRef.setValor(Integer.toBinaryString(decConv));
    }

    /**
     * Obtiene el mes de la fecha introducida y lo pone en el valor de la celda
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


    /**
     * Obtiene el año de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el año
     */
    public void obtenerAño(String _fecha){
        String dp[] = _fecha.split("/");
        String y = dp[2];

        int yr = Integer.parseInt(y);
        if(yr >= 0) this.celdaRef.setValor(y);
        else this.celdaRef.setValor("#ERROR");

    }

    /**
     * Obtiene el dia en forma numerica de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el dia
     */
    public void obtenerDia(String _fecha){
        String dp[] = _fecha.split("/");
        String d = dp[0];

        int dy = Integer.parseInt(d);
        if(dy >= 0) this.celdaRef.setValor(d);
        else this.celdaRef.setValor("#ERROR");

    }

    /**
     * Obtiene el nombre del dia en castellano de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el nombre del dia
     */
    public void obtenerNombreDia(String _fecha){
        /*Dado que DayOfWeek nos devuelve el dia en ingles, utilizaremos estas dos estructuras de datos para
        traducirlos al castellano*/

        String dyn[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        HashMap<Integer, String> dias = new HashMap<>();

        for(int i = 0; i < 7; ++i){
            dias.put(i+1, dyn[i]);
        }

        Month months[] = {Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY,
                Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER};

        String dp[] = _fecha.split("/");

        int y = Integer.parseInt(dp[2]);
        int mt = Integer.parseInt(dp[1]);
        Month m = months[mt-1];
        int dy = Integer.parseInt(dp[0]);

        if(dy >= 0 && y >= 0 && (mt > 0 && mt < 12)) {
            this.celdaRef.setValor(dias.get(LocalDate.of(y, m, dy).getDayOfWeek().getValue()));
        } else this.celdaRef.setValor("#ERROR");

    }


    /**
     * Mide la longitud de la palabra introducia y lo pone en el valor de la celda
     * @param _palabra introducida a medir
     */
    public void longitudPalabra(String _palabra){
        this.celdaRef.setValor(String.valueOf(_palabra.length()));
    }








}


