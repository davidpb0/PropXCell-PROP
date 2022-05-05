package main.Domain.DomainModel;
/*
 * ClassName DomainModel.Hoja
 *
 * Version info 0.1.1
 *
 * Author David Perez Barroso
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Hoja implements Serializable {

    private int id;
    private String nombre;
    private int filas;
    private int columnas;
    private HashMap<Posicion, Celda> celdas;


    /**
     * Funcion privada que inicializa la estructura de datos de hoja
     * @param _h hoja a inicializar
     */
    private void inicializaHoja(Hoja _h){
        int f = _h.getFilas();
        int c = _h.getColumnas();

        for (int i = 1; i <= f; ++i){
            for (int j = 1; j <= c; ++j){
                Posicion p = new Posicion(i, j);
                Celda cel = new Celda(p);
                cel.setHoja(this);
                _h.celdas.put(p, cel);
            }
        }
    }

    public void setCeldas(HashMap<Posicion, Celda> celdas) {
        this.celdas = celdas;
    }

    /**
     * Creadora con los valores por defecto
     */
    public Hoja(){
        this.filas = 50;
        this.columnas = 50;
        this.celdas = new HashMap<Posicion, Celda>();
        inicializaHoja(this);
    }

    /**
     * Creadora dados un numero de filas y un numero de columnas
     * @param _filas numero de filas de la hoja
     * @param _columnas numero de columnas de la hoja
     */
    public Hoja(int _filas, int _columnas){
        this.filas = _filas;
        this.columnas = _columnas;
        this.celdas = new HashMap<Posicion, Celda>();
        inicializaHoja(this);

    }


    /**
     * Asigna un numero de filas a la hoja
     * @param _f numero de filas que se asignaran a al hoja
     * @return Devuelve true si se ha añadido correctamente, false si _f es 0 o menor que 0
     */
    public boolean setFilas(int _f){
        if(_f > 0) {
            this.filas = _f;
            return true;
        }
        return false;

    }

    /**
     * Asigna un numero de columnas a la hoja
     * @param _c numero de columnas que se asignaran a la hoja
     * @return Devuelve true si se ha añadido correctamente, false si _c es 0 o menor que 0
     */
    public boolean setColumnas(int _c){
        if (_c > 0){
            this.columnas = _c;
            return true;
        }
        return false;

    }

    /**
     * Añade el identificador a la hoja y un nombre default
     * @param _aId identificador que la hoja tomará
     * @return Devuelve true si se ha asignado un nombre y id correctamente, false si el numero es negativo
     */
    public boolean añadeNombreIdHojaDefault(int _aId){
        if(_aId > 0){
            this.id = _aId;
            this.nombre = "Hoja" + this.id;
            return true;
        }
        return false;
    }

    /**
     * Le asigna un nombre a la hoja
     * @param _nombre nombre asignado
     * @return devuelve true, si se ha introducido correctamente el nombre y este no era vacio, de lo contrario
     * devuelve false
     */
    public boolean asignaNombre(String _nombre){

        if (!_nombre.isEmpty()){
            this.nombre = _nombre;
            return true;
        }
        return false;
    }

    /**
     * Comprueba si la hoja es la pedida
     * @param _id identificador de la hoja pedida
     * @return devuelve cierto si _id coincide con el de la hoja, falso de lo contrario
     */
    public boolean esHoja(int _id){
        return this.id == _id;
    }

    /**
     * Devuelve el identificador de la hoja
     * @return un entero que es el identificador de la hoja
     */
    public int getId(){
        return this.id;
    }

    /**
     * Devuelve el numero de columnas de la hoja
     * @return un entero que es el el numero de columnas
     */
    public int getColumnas() {
        return this.columnas;
    }

    /**
     * Devuelve el numero de filas de la hoja
     * @return un entero que es el numero de filas
     */
    public int getFilas(){
        return this.filas;
    }

    /**
     * Devuelve el nombre de la hoja
     * @return un string que es el nombre de la hoja
     */
    public String getNombre(){
        return this.nombre;
    }


    /**
     * Devuelve todas las celdas de la hoja
     * @return un HashMap de las celdas con sus posiciones
     */
    public HashMap<Posicion, Celda> getCeldas(){
        return this.celdas;
    }

    /**
     * Devuelve la celda en la fila y columna pedidas
     * @param _p posicion donde se encuebntra la celda
     * @return la celda con Posicion(f, c)
     */
    public Celda getCelda(Posicion _p){
        Celda cl = null;
        if(this.celdas.containsKey(_p)) {
            cl = this.celdas.get(_p);
        }

        return cl;
    }


    /**
     * Comprueba si existe en la hoja la posicion dada
     * @param p posicion a comprobar si es existente
     * @return true si existe, false de lo contrario
     */
    public boolean existePosicion(Posicion p){
        return this.celdas.containsKey(p);
    }

    /**
     * Añada una celda vacia en la posicion indicada, el que llama a la funcion se encarga de actualizar el valor de
     * filas y columnas, devuelve false si en la posicion introducida ya hay una celda
     * @param _p nueva posicion donde se va a añadir la nueva celda
     * @return devuleve true si se ha añadido la celda y la posicion correctamente, flase si la posicion ya existe o
     * ha habido algun error.
     */
    public boolean addCeldaVacia(Posicion _p){

        if (!this.celdas.containsKey(_p)){
            Celda cel = new Celda(_p);
            cel.setHoja(this);
            this.celdas.put(_p, cel);
            return true;
        }
        return false;

    }

    /**
     * Borra una celda con la posicion dada, el que llama a la funcion se encarga de actualizar el valor de
     * filas y columnas, devuelve false si la posicion no existe
     * @param _p posicion de la celda a borrar
     * @return devuelve true en caso de exito en el borrado, false si la posicion no existia o si no se ha borrado
     * correctamente
     */
    public boolean quitarCelda(Posicion _p){

        if (this.celdas.containsKey(_p)) {
            this.celdas.remove(_p);

            if(!celdas.containsKey(_p)) return true;
            return false;
        }
        return false;

    }

    /**
     * Cambia la posicion de una celda a otra posicion dada, devuelve false si una de las posiciones no existe
     * @param _pant posicion actual de la celda
     * @param _pdp posicion a la cual se desea cambiar la celda
     * @return devuelve true si se ha realizado correctamente el cambio, false si alguna de las posiciones no existe o
     * no se ha realizado correctamente el cambio
     */
    public boolean cambiarPosicionCelda(Posicion _pant, Posicion _pdp){

        //Si contiene las dos posiciones -> cambia la posicion
        if (this.celdas.containsKey(_pdp) && this.celdas.containsKey(_pant)) {
            Celda c = this.celdas.get(_pant);
            c.setReferenciantes(this.celdas.get(_pdp).getReferenciantes());
            this.celdas.replace(_pdp, c);
            c.setPosicion(_pdp);
            //Comprobamos que ha cambiado a la posicon deseada
            if (c.getPosicion() == _pdp) return true;
            return false;
        }

        //Si una de las dos no existe, no hace nada y devuelve falso
        return false;

    }

    /**
     * Cambia la posicion de una celda dada a otra posicion dada, devuelve false si la posicion dada no existe
     * @param p posicion a la que se va mover la celda
     * @param c celda que va a cambiar de posicion
     */
    public boolean cambiarPosicionCelda(Posicion p, Celda c){
        //Si contiene la posicion -> cambia la posicion
        Boolean b1 = this.celdas.containsKey(p);
        Boolean b2 = this.celdas.containsValue(c);
        if (b1 && b2) {
            c.setReferenciantes(this.celdas.get(p).getReferenciantes());
            this.celdas.replace(p, c);
            c.setPosicion(p);
            //Comprobamos que ha cambiado a la posicon deseada
            if (c.getPosicion() == p) return true;
            return false;
        }
        //Si no existe, no hace nada y devuelve falso
        return false;
    }


    /**
     * Devuelve un vector con las celdas en columna o en fila segun los parametros introducidos
     * @param _cel1 primera posicion de la fila o columna
     * @param _cel2 ultima posicion de la fila o columna que se desea
     * @return ArrayList<Celda> con las celdas de la columna o fila delimitada por los parametros de entrada
     */
    public ArrayList<Celda> getColumnaFila(String _cel1, String _cel2){
        ArrayList<Celda> agrup = new ArrayList<>();

        Celda celd1 = Traductor.getTraductor().traduceCelda(_cel1, this.id);
        Posicion pos1 = celd1.getPosicion();

        Celda celd2 = Traductor.getTraductor().traduceCelda(_cel2, this.id);
        Posicion pos2 = celd2.getPosicion();

        if (celdas.containsKey(pos1) && celdas.containsKey(pos2)) {

            //Columna Up->Down
            if (pos1.getFila() < pos2.getFila() && pos1.getColumna() == pos2.getColumna()) {
                for (int i = pos1.getFila(); i <= pos2.getFila(); ++i) {
                    agrup.add(this.celdas.get(new Posicion(i, pos1.getColumna())));
                }
            }
            //Columna Down->Up
            else if (pos1.getFila() > pos2.getFila() && pos1.getColumna() == pos2.getColumna()) {
                for (int i = pos1.getFila(); i >= pos2.getFila(); --i) {
                    agrup.add(this.celdas.get(new Posicion(i, pos1.getColumna())));
                }
            }
            //Fila Izq->Der
            else if (pos1.getFila() == pos2.getFila() && pos1.getColumna() < pos2.getColumna()) {
                for (int i = pos1.getColumna(); i <= pos2.getColumna(); ++i) {
                    agrup.add(this.celdas.get(new Posicion(pos1.getFila(), i)));
                }
            }
            //Fila Der->Izq
            else if (pos1.getFila() == pos2.getFila() && pos1.getColumna() > pos2.getColumna()) {
                for (int i = pos1.getColumna(); i >= pos2.getColumna(); --i) {
                    agrup.add(this.celdas.get(new Posicion(pos1.getFila(), i)));
                }
            } else if (pos1.getFila() == pos2.getFila() && pos1.getColumna() == pos2.getColumna()) {
                agrup.add(this.celdas.get(new Posicion(pos1.getFila(), pos1.getColumna())));
            }
        }

        return agrup;
    }


    /**
     * Transforma todo el contenido de la hoja en un ArrayList de Strings con los valores de las celdas de una
     * misma fila separados por ";" y separacion de filas con un espacio en blanco
     * @param _h Hoja a transformar en CSV
     * @return Devuelve un ArrayList de String con todos los valores de las celdas de las Hojas
     */
    public ArrayList<String> transformaCSV(Hoja _h){
        ArrayList<String> csv = new ArrayList<>();

        HashMap<Posicion, Celda> m = _h.getCeldas();

        int f = _h.getFilas();
        int c = _h.getColumnas();
        String aux = "";

        for(int i = 1; i <= f; ++i){
            aux= "";
            for(int j = 1; j <= c; ++j) {
                if (j == c) aux += m.get(new Posicion(i, j)).getValor()+ " ";
                else {
                    aux += m.get(new Posicion(i, j)).getValor() + ";";
                }
            }
            csv.add(aux);
        }

        return csv;
    }





}
