
package main.Domain.DomainControllers;

import main.Domain.DomainModel.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;

import static main.Domain.DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 1.0.0
*
* Author David Pérez Barroso
*/

public class ControladorCelda {
   private Celda celdaRef;
   private Hoja hojaAct;


   public ControladorCelda(){}

   /** Pre: Tiene que haber un documento inicializado
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
  public void escribirContenido(String _content) {
      this.celdaRef.setContenido(_content);

      String type = Traductor.getTraductor().detecta(_content);
      String[] arg;
      String[] arg1;
      String[] arg2;
      String[][]aux;
      switch (type) {
          case "#ABS": // =abs()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }
              String s = valorAbsoluto(Double.parseDouble(arg[0]));
              this.celdaRef.setValor(s);
              break;

          case "#TRUNC": // =trunc()
              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];
              //Se comprueba que el numero de argumentos sea el correcto
              try {
                  if (arg1.length != 1 || arg2.length != 1) {
                      this.celdaRef.setValor("#ERROR_N_ARG");
                      break;
                  }
              } catch (NullPointerException e) {
              }
              //Se comprueba que los argumentos sean numeros
              if (!arg1[0].matches("[+-]?\\d*(\\.\\d+)?") && !arg2[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }
              try {

                  String s1 = truncarValor(Double.parseDouble(arg1[0]), Integer.parseInt(arg2[0]));
                  this.celdaRef.setValor(s1);
              } catch (NumberFormatException e) {
                  this.celdaRef.setValor("#ERROR_ARGS");

              }
              break;

          case "#APROX": // =aprox()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }
              String s2 = aproximarValor(Double.parseDouble(arg[0]));
              this.celdaRef.setValor(s2);
              break;

          case "#VDB": // =convertirDB()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }
              String s3 = convertirValorDB(Integer.parseInt(arg[0]));
              this.celdaRef.setValor(s3);
              break;

          case "#VBD": // =convertirValorBD()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }

              //Se comprueba que los argumentos sean '1' i '0'
              boolean b = false;
              for (int i = 0; i < arg[0].length(); i++) {
                  char c = arg[0].charAt(i);
                  if (c != '0' && c != '1') {
                      b = true;
                      break;
                  }
              }
              if (b){
                  this.celdaRef.setValor("#ERROR_NO_01");
                  break;
              }
              String s4 = convertirValorBD(Integer.parseInt(arg[0]));
              this.celdaRef.setValor(s4);
              break;

          case "#VHD": // =convertirHD()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s5 = convertirValorHD(arg[0]);

              //Devuelve -1 si el argumento no es un numero hexadecimal valido
              if (s5 == "-1") {
                  this.celdaRef.setValor("#ERROR_NO_HEX");
                  break;
              }
              this.celdaRef.setValor(s5);
              break;

          case "#VDH": // =convertirDH()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }
              try {

                  String s6 = convertirValorDH(Integer.parseInt(arg[0]));
                  this.celdaRef.setValor(s6);
              } catch (NumberFormatException e) {
                  this.celdaRef.setValor("#ERROR_NUM");
              }
              break;

          case "#VHB": // =convertirHB()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s7 = convertirValorHB(arg[0]);

              //Devuelve -1 si el argumento no es un numero hexadecimal valido
              if (s7 == "-1") {
                  this.celdaRef.setValor("#ERROR_NO_HEX");
                  break;
              }
              this.celdaRef.setValor(s7);
              break;

          case "#VBH": // =convertirBH()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              if (!arg[0].matches("[+-]?\\d*(\\.\\d+)?")) {
                  this.celdaRef.setValor("#ERROR_NO_NUM");
                  break;
              }

              //Se comprueba que los argumentos sean '1' i '0'
              b = false;
              for (int i = 0; i < arg[0].length(); i++) {
                  char c = arg[0].charAt(i);
                  if (c != '0' && c != '1') {
                      b = true;
                      this.celdaRef.setValor("#ERROR_NO_01");
                      break;
                  }
              }
              if (b) break;
              String s8 = convertirValorBH(Integer.parseInt(arg[0]));
              this.celdaRef.setValor(s8);
              break;

          case "#MES": // =mes()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }
              String s9 = obtenerMes(arg[0]);
              this.celdaRef.setValor(s9);
              break;

          case "#AÑO": // =año()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }
              String s10 = obtenerAño(arg[0]);
              this.celdaRef.setValor(s10);
              break;

          case "#DIAS": // =diasemana()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }
              String s11 = obtenerDia(arg[0]);
              this.celdaRef.setValor(s11);
              break;

          case "#NDIA": // =nombredia()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }
              String s12 = obtenerNombreDia(arg[0]);
              this.celdaRef.setValor(s12);
              break;

          case "#LONG": // =longitud()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s13 = longitudPalabra(arg[0]);
              this.celdaRef.setValor(s13);
              break;

          case "#CLETRA": // =contarletra()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];
              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length != 1 || arg2.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s14 = contarLetra(arg1[0], arg2[0]);

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s14.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");
              else this.celdaRef.setValor(s14);
              break;

          case "#REEMPPAL": // =reemplazarPal()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];
              String[] arg3 = aux[2];
              String[] arg4 = aux[3];

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length != 1 || arg2.length != 1 || arg3.length != 1 || arg4.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s15 = reemplazarPalabra(arg1[0], Integer.parseInt(arg2[0]), Integer.parseInt(arg3[0]), arg4[0]);

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s15.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");
              else this.celdaRef.setValor(s15);
              break;

          case "#REEMPLET": // =reemplazarLet()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];
              arg3 = aux[2];
              System.out.println(arg1[0]);
              System.out.println(arg2[0]);
              System.out.println(arg3[0]);
              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length != 1 || arg2.length != 1 || arg3.length != 1) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              String s16 = reemplazarCaracter(arg1[0], arg2[0], arg3[0]);

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s16.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");
              else this.celdaRef.setValor(s16);
              break;

          case "#REFERENCIA": // $C1 (por ejemplo)
              //Se coge la celda referenciantes, para añadirle la referenciada
              Celda reft = Traductor.getTraductor().traduceCelda(_content, this.hojaAct.getId());
              reft.addReferenciante(this.celdaRef);
              //Se coge el valor de la celda referenciante
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());
              this.celdaRef.setValor(arg[0]);
              break;

          case "#MEDIA": // =media()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (aux[0].length == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : aux[0]) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              double m = ControladorHoja.media(aux[0]);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#MEDIANA": // =mediana()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (aux[0].length == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : aux[0]) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              m = ControladorHoja.mediana(aux[0]);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#VAR": // =varianza()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (aux[0].length == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : aux[0]) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              m = ControladorHoja.varianza(aux[0]);
              this.celdaRef.setValor(String.valueOf(m));

              break;

          case "#COV":// =covarianza()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length == arg2.length) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : arg1) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;
              for (String v : arg2) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;
              m = ControladorHoja.covarianza(arg1, arg2);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#DESV":// =desviacion()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg.length == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : arg) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              m = ControladorHoja.desvEstandar(arg);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#COEFP": // =pearson()

              //Se cogen los argumentos necesarios para realizar la operacion
              aux = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = aux[0];
              arg2 = aux[1];

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length == arg2.length) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }
              b = false;

              //Se comprueba que los argumentos sean numeros
              for (String v : arg1) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              //Se comprueba que los argumentos sean numeros
              for (String v : arg2) {
                  if (!v.matches("[+-]?\\d*(\\.\\d+)?")) {
                      this.celdaRef.setValor("#ERROR_NO_NUM");
                      b = true;
                      break;
                  }
              }
              if (b) break;

              m = ControladorHoja.correlacionPearson(arg1, arg2);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          default: //caso en el que no se escribe una funcion
              this.celdaRef.setValor(_content);
      }
  }



    /**
     * Comprueba que la fecha introducida sea valida
     * @param _fecha fecha a comprobar
     * @return Devuelve true si la fecha es valida, false de lo contrario
     */
   public boolean esFechaValida(String _fecha){
       String dp[] = _fecha.split("/");

       if(dp.length != 3) return false;
       if(!dp[0].matches("[+-]?\\d*(\\.\\d+)?") || dp[0].length() > 2
               || Integer.parseInt(dp[0]) <= 0 ) return false;

       if(!dp[1].matches("[+-]?\\d*(\\.\\d+)?") || dp[1].length() > 2
               || Integer.parseInt(dp[1]) <= 0) return false;

       if(!dp[2].matches("[+-]?\\d*(\\.\\d+)?") || dp[2].length() > 4
               || Integer.parseInt(dp[2]) <= 0) return false;

       return true;
   }

    /** :Pre _v es un numero decimal correcto
     * Trunca el numero decimal introducido
     * @param _v valor decimal a truncar
     * @param _op numero de decimales a truncar
     * @return Devuelve un String con el valor truncado, #ERROR si hay algun argumento erroneo
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


    /** Pre: _a es un valor decimal correcto
     * Pone el valor absoluto del numero introducido en un string
     * @param _a numero a poner en valor absoluto
     * @return Devuelve un String con el valor absoluto del introducido
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

    /** Pre _v es un valor decimal correcto
     * Aproxima el numero decimal introducido
     * @param _v valor a aproximar
     * @return Devuelve un String con el valor aproximado
     */
    public String aproximarValor(double _v){
        _v = Math.round(_v);
        return (String.valueOf(_v));

    }

    /** Pre: _dec es un numero decimal correcto
     * Covierte el valor en base decimal introducido a base binaria
     * @param _dec valor decimal a convertir en binario
     * @return Devuelve un String con el valor en binario
     */
    public String convertirValorDB(int _dec){
        return Integer.toBinaryString(_dec);
    }

    /** Pre: _b es un numero binario correcto
     * Covierte el valor en base binaria introducido a base decimal
     * @param _b valor binario a convertir en decimal
     * @return Devuelve un String con el valor en decimal
     */
    public String convertirValorBD(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        return (String.valueOf(dec));
    }

    /** Pre: _dec es un numero decimal correcto
     * Covierte el valor en base decimal introducido a base hexadecimal
     * @param _dec valor en decimal a convertir en hexadecimal
     * @return Devuelve un String con el valor en hexadecimal
     */
    public String convertirValorDH(int _dec){
        return Integer.toHexString(_dec);
    }


    /**
     * Covierte el valor en base hexadecimal introducido a base decimal
     * @param _h valor hexadecimal a convertir en decimal
     * @return Devuelve un String con el valor en decimal, -1 si hay un error en el formato del argumento
     */
    public String convertirValorHD(String _h){
        try {
            int dec = Integer.parseInt(_h, 16);
            return String.valueOf(dec);
        } catch (NumberFormatException e) {
           return "-1";
        }
    }

    /** Pre: _b es un numero binario correcto
     * Covierte el valor en base binairia introducido a base hexadecimal
     * @param _b valor binario a convertir en hexadecimal
     * @return Devuelve un String con el valor en hexadecimal
     */
    public String convertirValorBH(int _b){
        int dec = Integer.parseInt(String.valueOf(_b), 2);
        return Integer.toHexString(dec);
    }


    /**
     * Covierte el valor en base hexadecimal introducido a base binaria
     * @param _h valor hexadecimal a convertir en binario
     * @return Devuelve un String con el valor en binario, -1 si hay un error en el formato del argumento
     */
    public String convertirValorHB(String _h){
        try {
            int decConv = Integer.parseInt(_h, 16);
            return Integer.toBinaryString(decConv);
        } catch (NumberFormatException e) {
            return "-1";
        }
    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el mes de la fecha introducida
     * @param _fecha de donde se obtiene el mes
     * @return Devuelve un String con el mes, #ERROR si falla algun parametro del argumento
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
     * Obtiene el año de la fecha introducida
     * @param _fecha introducida de donde se obtiene el año
     * @return Devuelve un String con el año, #ERROR si falla algun parametro del argumento
     */
    public String obtenerAño(String _fecha){
        String dp[] = _fecha.split("/");
        String y = dp[2];

        int yr = Integer.parseInt(y);
        if(yr >= 0) return y;
        else return"#ERROR";

    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el dia en forma numerica de la fecha introducida
     * @param _fecha introducida de donde se obtiene el dia
     * @return Devuelve un String con el dia, #ERROR si falla algun parametro del argumento
     */
    public String obtenerDia(String _fecha){
        String dp[] = _fecha.split("/");
        String d = dp[0];

        int dy = Integer.parseInt(d);
        if(dy >= 0) return d;
        else return "#ERROR";

    }

    /** Pre: _fecha es una fecha valida
     * Obtiene el nombre del dia en castellano de la fecha introducida
     * @param _fecha introducida de donde se obtiene el nombre del dia
     * @return Devuelve un String con el nombre del dia, #ERROR si falla algun parametro del argumento
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
            try {
                return dias.get(LocalDate.of(y, m, dy).getDayOfWeek().getValue());
            } catch (Exception e) {
                return "#FECHA_NO_VALIDA";
            }
        } return "#ERROR";

    }


    /**
     * Mide la longitud de la palabra introducia
     * @param _palabra introducida a medir
     * @return Devuelve un String con la longitud de la palabra
     */
    public String longitudPalabra(String _palabra){
        return String.valueOf(_palabra.length());
    }


    /**
     * Cuenta el numero de veces que aparece una letra en una palabra y deja el valor en la celda
     * @param _palabra palabra donde se va a contar el numero de veces en la que aparece la letra
     * @param _letra letra a contar
     * @return Devuelve un string con -1 si no contiene la letra a contar, de lo contrario devuelve la palabra con
     *      * la letra reemplazada
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
     * @return Devuelve un string con -1 si hay algun error en los argumentos (_pos o _long), de lo contrario
     * devuelve la palabra con el trozo reemplazado
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
     * @return Devuelve un string con -1 si no contiene la letra a remplazar, de lo contrario devuelve la palabra con
     * la letra reemplazada
     */
    public String reemplazarCaracter(String _txt, String _cr, String _nc){
        if(!_txt.contains(_cr)) return "-1";
        return _txt.replace(_cr, _nc);


    }








}


