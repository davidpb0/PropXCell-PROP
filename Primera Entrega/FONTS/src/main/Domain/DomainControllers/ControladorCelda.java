
package main.Domain.DomainControllers;

import main.Domain.DomainModel.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import static main.Domain.DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 0.0.6
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

    public Celda getCeldaRef(){
       return this.celdaRef;
    }
    /**
     * Escribe el contenido que le pasan en la celda, si es una funcion, llama a la funcion correspondiente y ademas
     * escribe su valor en la celda
     * @param _content contenido a escribir en la celda
     */
  public void escribirContenido(String _content){
       this.celdaRef.setContenido(_content);

       String type = Traductor.getTraductor().detecta(_content);
       String[] arg;
      // if (_content = ref) ref.borrarRef(me);
       switch (type) {
           case "#ABS":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s = valorAbsoluto(Double.parseDouble(arg[0]));
               this.celdaRef.setValor(s);
               break;

           case "#TRUNC":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 2) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?") && !!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s1 = truncarValor(Double.parseDouble(arg[0]), Integer.parseInt(arg[1]));
               this.celdaRef.setValor(s1);
               break;

           case "#APROX":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s2 = aproximarValor(Double.parseDouble(arg[0]));
               this.celdaRef.setValor(s2);
               break;

           case "#VDB":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s3 = convertirValorDB(Integer.parseInt(arg[0]));
               this.celdaRef.setValor(s3);
               break;

           case "#VBD":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               boolean b  = false;
               for (int i = 0; i < arg[0].length(); i++) {
                   char c = arg[0].charAt(i);
                   if(c != '0' || c != '1'){
                       b = true;
                       this.celdaRef.setValor("#ERROR");
                       break;
                   }
               }
               if (b) break;
               String s4 = convertirValorBD(Integer.parseInt(arg[0]));
               this.celdaRef.setValor(s4);
               break;

           case "#VHD":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s5 = convertirValorHD(arg[0]);
               this.celdaRef.setValor(s5);
               break;

           case "#VDH":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s6 = convertirValorDH(Integer.parseInt(arg[0]));
               this.celdaRef.setValor(s6);
               break;

           case "#VHB":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s7 = convertirValorHB(arg[0]);
               this.celdaRef.setValor(s7);
               break;

           case "#VBH":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!arg[0].matches("[+-]?\\d*(\\.\\d+)?")){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               b = false;
               for (int i = 0; i < arg[0].length(); i++) {
                   char c = arg[0].charAt(i);
                   if(c != '0' || c != '1'){
                       b = true;
                       this.celdaRef.setValor("#ERROR");
                       break;
                   }
               }
               if (b) break;
               String s8 = convertirValorBH(Integer.parseInt(arg[0]));
               this.celdaRef.setValor(s8);
               break;

           case "#MES":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s9 = obtenerMes(arg[0]);
               this.celdaRef.setValor(s9);
               break;

           case "#AÑO":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s10 = obtenerAño(arg[0]);
               this.celdaRef.setValor(s10);
               break;

           case "#DIAS":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s11 = obtenerDia(arg[0]);
               this.celdaRef.setValor(s11);
               break;

           case "#NDIA":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               if(!esFechaValida(arg[0])){
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s12 = obtenerNombreDia(arg[0]);
               this.celdaRef.setValor(s12);
               break;

           case "#LONG":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 1) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s13 = longitudPalabra(arg[0]);
               this.celdaRef.setValor(s13);
               break;

           case "#CLETRA":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 2) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s14 = contarLetra(arg[0], arg[1]);

               if(s14 == "-1") this.celdaRef.setValor("#ERROR");
               else this.celdaRef.setValor(s14);
               break;

           case "#REEMPPAL":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 4) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s15 = reemplazarPalabra(arg[0], Integer.parseInt(arg[1]), Integer.parseInt(arg[2]), arg[3]);
               if(s15 == "-1") this.celdaRef.setValor("#ERROR");
               else this.celdaRef.setValor(s15);
               break;

           case "#REEMPLET":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length != 2) {
                   this.celdaRef.setValor("#ERROR");
                   break;
               }
               String s16 = reemplazarCaracter(arg[0], arg[1], arg[2]);
               if(s16 == "-1") this.celdaRef.setValor("#ERROR");
               else this.celdaRef.setValor(s16);
               break;

           case "#REFERENCIA":
               Celda reft = Traductor.getTraductor().traduceCelda(_content, this.hojaAct.getId());
               reft.addReferenciante(this.celdaRef);
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               this.celdaRef.setValor(arg[0]);
               break;

           case "#MEDIA":
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
               b = false;
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
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
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
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
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
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
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
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
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
               arg = Traductor.getTraductor().getArgumentos(_content, hojaAct.getId());
               if(arg.length == 0) {
                   this.celdaRef.setValor("#FALTANARGS");
                   break;
               }
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

   public String truncarValor(double _v, int _op) {

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
           return (String.valueOf(_v));
       }
       else return "#ERROR";

    }


    /**
     * Pone el valor absoluto del numero introducido en el valor de la celda
     * @param _a numero a poner en valor absoluto
     */
    public String valorAbsoluto(double _a){
        //Para enteros que en decimal se escribe -> 5.0
        if((_a % 1) == 0) {
             int aux = (int) Math.abs(_a);
             return String.valueOf(aux);
        }
        else{
            _a = Math.abs(_a);
            return String.valueOf(_a);
        }

    }

    /**
     * Aproxima el numero decimal introducido y lo pone en el valor de la celda
     * @param _v valor a aproximar
     */
    public String aproximarValor(double _v){
        _v = Math.round(_v);
        return (String.valueOf(_v));

    }

    /**
     * Covierte el valor en base decimal introducido y lo deja en el valor de la celda en base binaria
     * @param _dec valor decimal a convertir en binario
     */
    public String convertirValorDB(int _dec){
        return Integer.toBinaryString(_dec);
    }

    /**
     * Covierte el valor en base binaria introducido y lo deja en el valor de la celda en base decimal
     * @param _b valor binario a convertir en decimal
     */
    public String convertirValorBD(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        return (String.valueOf(dec));
    }

    /**
     * Covierte el valor en base decimal introducido y lo deja en el valor de la celda en base hexadecimal
     * @param _dec valor en decimal a convertir en hexadecimal
     */
    public String convertirValorDH(int _dec){
        return Integer.toHexString(_dec);
    }


    /**
     * Covierte el valor en base hexadecimal introducido y lo deja en el valor de la celda en base decimal
     * @param _h valor hexadecimal a convertir en decimal
     */
    public String convertirValorHD(String _h){
        int dec = Integer.parseInt(_h, 16);
        return String.valueOf(dec);
    }

    /**
     * Covierte el valor en base binairia introducido y lo deja en el valor de la celda en base hexadecimal
     * @param _b valor binario a convertir en hexadecimal
     */
    public String convertirValorBH(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        return Integer.toHexString(dec);
    }


    /**
     * Covierte el valor en base hexadecimal introducido y lo deja en el valor de la celda en base binaria
     * @param _h valor hexadecimal a convertir en binario
     * @return
     */
    public String convertirValorHB(String _h){
        int decConv = Integer.parseInt(_h, 16);
        return Integer.toBinaryString(decConv);
    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el mes de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha de donde se obtiene el mes
     */
    public String obtenerMes(String _fecha){
        String months[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        String dp[] = _fecha.split("/");
        String m = dp[1];

        int mth = Integer.parseInt(m);
        if(mth > 0 && mth <= 12){
            return (months[mth-1]);
        }
        return "#ERROR";

    }


    /** Pre: _fecha es una fecha valida
     * Obtiene el año de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el año
     */
    public String obtenerAño(String _fecha){
        String dp[] = _fecha.split("/");
        String y = dp[2];

        int yr = Integer.parseInt(y);
        if(yr >= 0) return y;
        else return"#ERROR";

    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el dia en forma numerica de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el dia
     */
    public String obtenerDia(String _fecha){
        String dp[] = _fecha.split("/");
        String d = dp[0];

        int dy = Integer.parseInt(d);
        if(dy >= 0) return d;
        else return "#ERROR";

    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el nombre del dia en castellano de la fecha introducida y lo pone en el valor de la celda
     * @param _fecha introducida de donde se obtiene el nombre del dia
     */
    public String obtenerNombreDia(String _fecha){
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
            return dias.get(LocalDate.of(y, m, dy).getDayOfWeek().getValue());
        } return "#ERROR";

    }


    /**
     * Mide la longitud de la palabra introducia y lo pone en el valor de la celda
     * @param _palabra introducida a medir
     */
    public String longitudPalabra(String _palabra){
        return String.valueOf(_palabra.length());
    }


    /**
     * Cuenta el numero de veces que aparece una letra en una palabra y deja el valor en la celda
     * @param _palabra palabra donde se va a contar el numero de veces en la que aparece la letra
     * @param _letra letra a contar
     * @return Devuelve true si la función se ha relaizado correctamente, false si la palabra no contiene la letra a
     * contar
     */
    public String contarLetra(String _palabra, String _letra){
        char aux;
        int count = 0;
        if(!_palabra.contains(_letra)) return "-1";

        for(int i = 0; i < _palabra.length(); i++){
            aux = _palabra.charAt(i);
            if(aux == _letra.charAt(0)) {
                ++count;
            }
        }
        return String.valueOf(count);

    }


    /**
     * Sustituye parte de una cadena de texto por otra cadena y lo pone en el valor de la celda
     * @param _txt texto del cual una parte va a ser sustituida
     * @param _pos posicion donde comenzará la sustitución
     * @param _long numero de caracteres del texto que se sustituirán
     * @param _ntxt texto que se insertará en el texto original
     * @return Devuelve cierto si la funcion se ha realizado correctamente, falso en caso de que hay algun error en los
     * parametros introducidos
     */

    public String reemplazarPalabra(String _txt, int _pos, int _long,  String _ntxt){
        if(_pos > _txt.length()) return "-1";
        if(_pos < 0 || _long <= 0) return "-1";
        if(_long-1 > _txt.length()-_pos) return "-1";
       String s = _txt.substring(_pos-1, _pos+_long-1);
       return _txt.replaceAll(s, _ntxt);


    }

    /**
     * Remplaza un caracter elegido por otro en el texto original y lo ponen en el valor de la celda
     * @param _txt texto al cual se le va a reemplazar un caracter
     * @param _cr caracter a reemplazar
     * @param _nc caracter nuevo
     * @return Devuelve cierto si la funcion se ha realizado correctamente, falso en caso de que el caracter a sustituir
     * no exista en el texto introducido
     */
    public String reemplazarCaracter(String _txt, String _cr, String _nc){
        if(!_txt.contains(_cr)) return "-1";
        return _txt.replace(_cr, _nc);


    }








}


