package main.Domain.DomainControllers;

import main.Domain.DomainModel.*;

import java.util.ArrayList;
import java.util.Comparator;

/* ControladorHoja
 *
 * v1.0.1
 *
 * David Pérez Barroso
 */

public class ControladorHoja {

    private Hoja hojaAct;
    private Celda celdaRef;

    public enum Criterio {
        ASCENDENTE,
        DESCENDENTE
    }

    public ControladorHoja() {}

    /**
    * Se guarda la hoja que le pasa presentación.
    * @param _idh identificador de la hoja que siempre corresponderá a una hoja existente
    */
    public void asignaHoja(int _idh) {
        hojaAct = ControladorDominio.getControladorDominio().getControladorDocumento().getDocumento().getHoja(_idh);
    }


    /** Pre: Tiene que haber un documento inicializado y una hoja referenciada en la clase
     * Se guarda una celda de la hoja, fila y columna que le pasa presentación
     * @param _f fila de la celda
     * @param _c columna de la celda
     */
    public void asignaCelda(String _f, String _c){
        this.celdaRef = hojaAct.getCelda(new Posicion(Integer.parseInt(_f), Integer.parseInt(_c)));
    }

    /**
     * Devuelve la celda contenida en el controlador
     * @return La celda contenida del controlador
     */
    public Celda getCeldaRef(){
        return this.celdaRef;
    }

    /**
     * Se cambia el nombre de la hoja.
     * @param _nuevoNombre nombre de la hoja pasado por presentación.
     */
    public void renombraHoja(String _nuevoNombre) {
        hojaAct.asignaNombre(_nuevoNombre);
    }


    /**
     * Devuelve la hoja actual
     * @return la hoja actual
     */
    public Hoja getHojaRef() { return this.hojaAct; }

    /**
     * Devuelve el nombre de la hoja del controlador.
     * @return el nombre de la hoja actual.
     */
    public String getNombreHoja() { return hojaAct.getNombre(); }



    /**
     * Devuelve el id de la hoja actual.
     * @return el id de la hoja actual.
     */
    public int getIdHoja() {
        return hojaAct.getId();
    }


    /**
    * Se añaden un conjunto de filas en blanco a partir un número de fila.
    * Las filas que habían en la posición _pos y posteriores se moveran _num posiciones hacia delante.
    * @param _pos numero de fila que tendrá la primera fila del bloque añadido.
    * Siempre será un número de fila existente o el inmediatamente superior a la última fila
    * @param _num numero de filas añadidas, siempre es positivo
    * @
    */
    public void addFilas(int _pos, int _num) {
        int numFilas = hojaAct.getFilas();
        int numColums = hojaAct.getColumnas();

        // Movemos todas las filas por encima de _pos _num posiciones 
        for (int iterFilas = numFilas; iterFilas >= _pos; --iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaAct.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas + _num, iterColums));
            }
        }

        // Añadimos las nuevas filas
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaAct.addCeldaVacia(new Posicion(_pos + iterFilas, iterColums));

            }
        }

        hojaAct.setFilas(numFilas + _num);
    }

    /**
    * Se añaden un conjunto de columnas en blanco a partir un número de columna.
    * Las columnas que habían en la posición _pos y posteriores se moveran _num posiciones hacia delante.
    * @param _pos numero de columna que tendrá la primera columna del bloque añadido
    * Siempre será un número de columna existente o el inmediatamente superior a la última fila
    * @param _num numero de columnas añadidas, siempre es positivo
    */
    public void addColumnas(int _pos, int _num) {
        int numFilas = hojaAct.getFilas();
        int numColums = hojaAct.getColumnas();

        // Movemos todas las columnas a la derecha de _pos _num posiciones 
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = numColums; iterColums >= _pos; --iterColums) {
                hojaAct.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas, iterColums + _num));
            }
        }

        // Añadimos las nuevas columnas
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaAct.addCeldaVacia(new Posicion(iterFilas, _pos + iterColums));
            }
        }

        hojaAct.setColumnas(numColums + _num);
    }

    /**
    * Se elimina el conjunto de filas contiguas indicadas a partir de un número de fila.
    * Las filas posteriores al conjunto de filas borrado reocuparan esas posiciones inferiores.
    * @param _pos numero de columna que tendrá la primera fila del conjunto a borrar.
    * Siempre será un número de fila existente
    * @param _num numero de filas a eliminar, siempre es >= 1 
    * Pre: Se garantiza que numero de filas total - _pos >= _num + 1
    */
    public void eliminarFilas(int _pos, int _num) {
        int numFilas = hojaAct.getFilas();
        int numColums = hojaAct.getColumnas();

        // Borramos las filas indicada
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaAct.quitarCelda(new Posicion(_pos + iterFilas, iterColums));
            }
        }

        // Reasignamos la posicion a las filas posteriores
        for (int iterFilas = _pos + 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaAct.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas - 1, iterColums));
            }
        }

        hojaAct.setFilas(numFilas - _num);
    }

    /**
    * Se elimina el conjunto de columnas contiguas indicadas a partir de un número de columna.
    * Las columnas posteriores al conjunto de columnas borrado reocuparan esas posiciones inferiores.
    * @param _pos numero de columna que tendrá la primera columna del conjunto a borrar.
    * Siempre será un número de columna existente
    * @param _num numero de columnas a eliminar, siempre es >= 1
    * Pre: Se garantiza que numero de filas total - _pos >= _num + 1
    */
    public void eliminarColumnas(int _pos, int _num) {
        int numFilas = hojaAct.getFilas();
        int numColums = hojaAct.getColumnas();

        // Borramos las columna indicada
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaAct.quitarCelda(new Posicion(iterFilas, _pos + iterColums));
            }
        }

        // Reasignamos la posicion a las columnas posteriores
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums =  _pos + 1; iterColums <= numColums; ++iterColums) {
                hojaAct.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas, iterColums - 1));
            }
        }

        hojaAct.setColumnas(numColums - _num);
    }


    /**
     * Ordena un bloque de manera ascendente o descendente
     * @param descendente parametro que indica si es ascendente (false) o descendente (true)
     */
    public static void ordenar(boolean descendente) {
        ArrayList<Celda> celdas = new ArrayList<>();

        int filaIni = BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getFila();
        int filaFin = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getFila();
        int colIni = BloqueSeleccionado.getBloque().getCeldaInicial().getPosicion().getColumna();
        int colFin = BloqueSeleccionado.getBloque().getCeldaFinal().getPosicion().getColumna();

          for (int i = filaIni; i <=filaFin; i++) {
              for (int j = colIni; j <=colFin; j++) {
                  celdas.add(new Celda(BloqueSeleccionado.getBloque().getHoja().getCelda(new Posicion(i, j))));
              }
          }

          if (descendente)
              celdas.sort(Comparator.comparing(Celda::getValor));
          else
            celdas.sort(Comparator.comparing(Celda::getValor));

          for (int i = filaIni; i <=filaFin; i++) {
              for (int j = colIni; j <=colFin; j++) {
                  BloqueSeleccionado.getBloque().getHoja().getCelda(new Posicion(i, j)).copiarCelda(celdas.get(0));
                  celdas.remove(0);
              }
          }
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

        String type = Traductor.detecta(_content);
        String[] arg;
        ArrayList<String[]> argm = new ArrayList<>();
        ArrayList<String> argu = new ArrayList<>();
        String[] arg1;
        String[] arg2;

        switch (type) {
            case "#ABS": // =abs()
                //Se cogen los argumentos necesarios para realizar la operacion
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

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
                arg = Traductor.getArgumentosFuncion1aria(_content, hojaAct.getId());

                //Se comprueba que el numero de argumentos sea el correcto
                if (!numArgCorrecto(arg)) break;

                LongitudPalabra lp = new LongitudPalabra(arg[0]);
                String s13 = lp.execute();

                this.celdaRef.setValor(s13);
                break;

            case "#CLETRA": // =contarletra()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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
                Celda reft = Traductor.traduceCelda(_content, this.hojaAct.getId());
                reft.addReferenciante(this.celdaRef);

                //Se coge el valor de la celda referenciante
                this.celdaRef.setValor(reft.getValor());
                break;

            case "#MEDIA": // =media()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());

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

                Media md = new Media(argu.toArray(new String[0]));
                this.celdaRef.setValor(md.execute());
                break;

            case "#MEDIANA": // =mediana()
                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());

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

                Mediana mn = new Mediana(argu.toArray(new String[0]));

                this.celdaRef.setValor(mn.execute());
                break;

            case "#VAR": // =varianza()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());

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

                Varianza vr = new Varianza(argu.toArray(new String[0]));

                this.celdaRef.setValor(vr.execute());

                break;

            case "#COV":// =covarianza()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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

                Covarianza cv = new Covarianza(arg1, arg2);

                this.celdaRef.setValor(cv.execute());
                break;

            case "#DESV":// =desviacion()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());

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

                DesvEstandar dsv = new DesvEstandar(argu.toArray(new String[0]));

                this.celdaRef.setValor(dsv.execute());
                break;

            case "#COEFP": // =pearson()

                //Se cogen los argumentos necesarios para realizar la operacion
                argm = Traductor.getArgumentosFuncionNaria(_content, hojaAct.getId());
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

                Pearson cp = new Pearson(arg1, arg2);

                this.celdaRef.setValor(cp.execute());
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