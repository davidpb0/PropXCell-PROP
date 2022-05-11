
package main.Domain.DomainControllers;

import main.Domain.DomainModel.*;

import java.util.ArrayList;

import static main.Domain.DomainModel.Documento.getDocumento;


/*
* ClassName ControladorCelda
*
* Version info 1.0.1
*
* Author David Pérez Barroso
*/

public class ControladorCelda {
   private static ControladorCelda instanceOfThisClass;
   private Celda celdaRef;
   private Hoja hojaAct;


   private ControladorCelda(){}


    public static ControladorCelda getControladorCelda(){
       if (instanceOfThisClass == null) instanceOfThisClass = new ControladorCelda();
       return instanceOfThisClass;
    }

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

    /**
     * Devuelve la celda contenida en el controlador
     * @return La celda contenida del controlador
     */
    public Celda getCeldaRef(){
       return this.celdaRef;
    }


    /**
     * Comprueba que los parametros del string introducido son numeros, en caso de no ser asi, devuelve false y añade
     * al valor de la celda referenciada por el controlador un codigo de error
     * @param arg String a comprobar
     * @return Devuelve true si todos los caracteres son numeros, devuelve false de lo contrario
     */
    private boolean numCorrecto(String arg){
        if (!arg.matches("[+-]?\\d*(\\.\\d+)?")) {
            this.celdaRef.setValor("#ERROR_NO_NUM");
            return false;
        }
        return true;
    }

    /**
     * Comprueba que el vector de string pasado tenga tamaño 1, de no ser asi añade al valor de la celda referenciada
     * por el controlador un codigo de error
      * @param arg vector de string a comprobar
     * @return True si tiene tamaño 1, false de lo contrario
     */
    private boolean numArgCorrecto(String[] arg){
        if (arg.length != 1) {
            this.celdaRef.setValor("#ERROR_N_ARG");
            return false;
        }
        return true;
    }


    /**
     * Escribe el contenido que le pasan en la celda, si es una funcion, llama a la funcion correspondiente y ademas
     * escribe su valor en la celda
     * @param _content contenido a escribir en la celda
     */
  public void escribirContenido(String _content) throws Exception {
      this.celdaRef.setContenido(_content);

      String type = Traductor.getTraductor().detecta(_content);
      String[] arg;
      ArrayList<String[]> argm = new ArrayList<>();
      ArrayList<String> argu = new ArrayList<>();
      String[] arg1;
      String[] arg2;

      switch (type) {
          case "#ABS": // =abs()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

              ValorAbsoluto va = new ValorAbsoluto(Double.parseDouble(arg[0]));

              String s = va.execute();

              this.celdaRef.setValor(s);
              break;

          case "#TRUNC": // =trunc()
              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);
              //Se comprueba que el numero de argumentos sea el correcto
              try {
                  if (!numArgCorrecto(arg1) || !numArgCorrecto(arg2)) break;

              } catch (NullPointerException e) {
              }

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg1[0]) && !numCorrecto(arg2[0])) break;
              try {

                  TruncarValor tv = new TruncarValor(Double.parseDouble(arg1[0]), Integer.parseInt(arg2[0]));

                  String s1 = tv.execute();
                  this.celdaRef.setValor(s1);

              } catch (NumberFormatException e) {
                  this.celdaRef.setValor("#ERROR_ARGS");

              }
              break;

          case "#APROX": // =aprox()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

              AproximarValor apxv = new AproximarValor(Double.parseDouble(arg[0]));
              String s2 = apxv.execute();
              this.celdaRef.setValor(s2);
              break;

          case "#VDB": // =convertirDB()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

              ConvertirValorDB cdb = new ConvertirValorDB(Integer.parseInt(arg[0]));
              String s3 = cdb.execute();

              this.celdaRef.setValor(s3);
              break;

          case "#VBD": // =convertirValorBD()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

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

              ConvertirValorBD cbd = new ConvertirValorBD(Integer.parseInt(arg[0]));
              String s4 = cbd.execute();

              this.celdaRef.setValor(s4);
              break;

          case "#VHD": // =convertirHD()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              ConvertirValorHD chd = new ConvertirValorHD(arg[0]);
              String s5 = chd.execute();

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
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

              try {

                  ConvertirValorDH cdh = new ConvertirValorDH(Integer.parseInt(arg[0]));
                  String s6 = cdh.execute();

                  this.celdaRef.setValor(s6);

              } catch (NumberFormatException e) {
                  this.celdaRef.setValor("#ERROR_NUM");
              }
              break;

          case "#VHB": // =convertirHB()
              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              ConvertirValorHB chb = new ConvertirValorHB(arg[0]);
              String s7 = chb.execute();

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
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que los argumentos sean numeros
              if (!numCorrecto(arg[0])) break;

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

              ConvertirValorBH cbh = new ConvertirValorBH(Integer.parseInt(arg[0]));
              String s8 = cbh.execute();

              this.celdaRef.setValor(s8);
              break;

          case "#MES": // =mes()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }

              ObtenerMes om = new ObtenerMes(arg[0]);

              String s9 = om.execute();

              this.celdaRef.setValor(s9);
              break;

          case "#AÑO": // =año()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }

              ObtenerAño oa = new ObtenerAño(arg[0]);
              String s10 = oa.execute();

              this.celdaRef.setValor(s10);
              break;

          case "#DIAS": // =diasemana()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }

              ObtenerDia od = new ObtenerDia(arg[0]);
              String s11 = od.execute();

              this.celdaRef.setValor(s11);
              break;

          case "#NDIA": // =nombredia()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              //Se comprueba que sea una fecha valida
              if (!esFechaValida(arg[0])) {
                  this.celdaRef.setValor("#ERROR_NO_FECHA_VALIDA");
                  break;
              }

              ObtenerNombreDia ond = new ObtenerNombreDia(arg[0]);
              String s12 = ond.execute();

              this.celdaRef.setValor(s12);
              break;

          case "#LONG": // =longitud()

              //Se cogen los argumentos necesarios para realizar la operacion
              arg = Traductor.getTraductor().getArgumentosFuncion1aria(_content, hojaAct.getId());

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg)) break;

              LongitudPalabra lp = new LongitudPalabra(arg[0]);
              String s13 = lp.execute();

              this.celdaRef.setValor(s13);
              break;

          case "#CLETRA": // =contarletra()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);
              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg1) || !numArgCorrecto(arg2)) break;

              ContarLetra cl = new ContarLetra(arg1[0], arg2[0]);
              String s14 = cl.execute();

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s14.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");
              else this.celdaRef.setValor(s14);
              break;

          case "#REEMPPAL": // =reemplazarPal()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);
              String[] arg3 = argm.get(2);
              String[] arg4 = argm.get(3);

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg1) || !numArgCorrecto(arg2) ||
                      !numArgCorrecto(arg3) || !numArgCorrecto(arg4)) break;

              ReemplazarPalabra rp = new ReemplazarPalabra(arg1[0], Integer.parseInt(arg2[0]),
                      Integer.parseInt(arg3[0]), arg4[0]);
              String s15 = rp.execute();

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s15.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");

              else this.celdaRef.setValor(s15);
              break;

          case "#REEMPLET": // =reemplazarLet()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);
              arg3 = argm.get(2);

              //Se comprueba que el numero de argumentos sea el correcto
              if (!numArgCorrecto(arg1) || !numArgCorrecto(arg2) || !numArgCorrecto(arg3)) break;

              ReemplazarCaracter rc = new ReemplazarCaracter(arg1[0], arg2[0], arg3[0]);

              String s16 = rc.execute();

              //Si devuelve -1 ha habido un fallo al introducir los argumentos
              if (s16.equals("-1")) this.celdaRef.setValor("#ERROR_ARG");
              else this.celdaRef.setValor(s16);
              break;

          case "#REFERENCIA": // $C1 (por ejemplo)
              //Se coge la celda referenciantes, para añadirle la referenciada
              Celda reft = Traductor.getTraductor().traduceCelda(_content, this.hojaAct.getId());
              reft.addReferenciante(this.celdaRef);

              //Se coge el valor de la celda referenciante
              this.celdaRef.setValor(reft.getValor());
              break;

          case "#MEDIA": // =media()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              for(int i = 0; i < argm.size(); ++i){
                    String[] a = argm.get(i);
                    if(a.length > 1){
                        for(String v : a) argu.add(v);
                    }
                    else argu.add(a[0]);

              }

              //Se comprueba que el numero de argumentos sea el correcto
              if (argu.size() == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : argu) {
                  if (!numCorrecto(v)) {
                      b = true;
                      break;
                  }
              }
              if (b) break;

              double m = ControladorHoja.media(argu.toArray(new String[0]));
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#MEDIANA": // =mediana()
              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              for(int i = 0; i < argm.size(); ++i){
                  String[] a = argm.get(i);
                  if(a.length > 1){
                      for(String v : a) argu.add(v);
                  }
                  else argu.add(a[0]);
              }

              //Se comprueba que el numero de argumentos sea el correcto
              if (argu.size() == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : argu) {
                  if (!numCorrecto(v)) {
                      b = true;
                      break;
                  }
              }
              if (b) break;


              m = ControladorHoja.mediana(argu.toArray(new String[0]));
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#VAR": // =varianza()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              for(int i = 0; i < argm.size(); ++i){
                  String[] a = argm.get(i);
                  if(a.length > 1){
                      for(String v : a) argu.add(v);
                  }
                  else argu.add(a[0]);
              }

              //Se comprueba que el numero de argumentos sea el correcto
              if (argu.size() == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : argu) {
                  if (!numCorrecto(v)) {
                      b = true;
                      break;
                  }
              }
              if (b) break;;

              m = ControladorHoja.varianza(argu.toArray(new String[0]));
              this.celdaRef.setValor(String.valueOf(m));

              break;

          case "#COV":// =covarianza()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length != arg2.length) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              int k = 0;
              while (k < arg1.length){
                  String v = arg1[k];
                  String v2 = arg2[k];
                  if (!numCorrecto(v) || !numCorrecto(v2)) {
                      b = true;
                      break;
                  }
                  ++k;

              }
              if (b) break;

              m = ControladorHoja.covarianza(arg1, arg2);
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#DESV":// =desviacion()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());

              for(int i = 0; i < argm.size(); ++i){
                  String[] a = argm.get(i);
                  if(a.length > 1){
                      for(String v : a) argu.add(v);
                  }
                  else argu.add(a[0]);

              }

              //Se comprueba que el numero de argumentos sea el correcto
              if (argu.size() == 0) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              //Se comprueba que los argumentos sean numeros
              b = false;
              for (String v : argu) {
                  if (!numCorrecto(v)) {
                      b = true;
                      break;
                  }
              }
              if (b) break;;

              m = ControladorHoja.desvEstandar(argu.toArray(new String[0]));
              this.celdaRef.setValor(String.valueOf(m));
              break;

          case "#COEFP": // =pearson()

              //Se cogen los argumentos necesarios para realizar la operacion
              argm = Traductor.getTraductor().getArgumentosFuncionNaria(_content, hojaAct.getId());
              arg1 = argm.get(0);
              arg2 = argm.get(1);

              //Se comprueba que el numero de argumentos sea el correcto
              if (arg1.length != arg2.length) {
                  this.celdaRef.setValor("#ERROR_N_ARG");
                  break;
              }

              b = false;
              k = 0;
              //Se comprueba que los argumentos sean numeros
              while (k < arg1.length){
                  String v = arg1[k];
                  String v2 = arg2[k];
                  if (!numCorrecto(v) || !numCorrecto(v2)) {
                      b = true;
                      break;
                  }
                  ++k;

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
       if(!numCorrecto(dp[0]) || dp[0].length() > 2
               || Integer.parseInt(dp[0]) <= 0 ) return false;

       if(!numCorrecto(dp[1]) || dp[1].length() > 2
               || Integer.parseInt(dp[1]) <= 0) return false;

       if(!numCorrecto(dp[2]) || dp[2].length() > 4
               || Integer.parseInt(dp[2]) <= 0) return false;

       return true;
   }


}


