package main.Domain.DomainModel;
/*
 * ClassName DomainModel.Hoja
 *
 * Version info 0.0.5
 *
 * Author David Perez Barroso
 */

import java.io.Serializable;
import java.util.HashMap;

public class Hoja implements Serializable {

    private int id;
    private String nombre;
    private int filas;
    private int columnas;
    private HashMap<Posicion, Celda> celdas;


    /**
     * Funcion privada que inicializa la estructura de datos
     * @param _h hoja a inicializar
     */
    private void inicializaHoja(Hoja _h){
        int f = _h.getFilas();
        int c = _h.getColumnas();

        for (int i = 1; i <= f; ++i){
            for (int j = 1; j <= c; ++j){
                Posicion p = new Posicion(i, j);
                _h.celdas.put(p, new Celda(p));
            }
        }
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
     */
    public void setFilas(int _f){
        this.filas = _f;

    }

    /**
     * Asigna un numero de columnas a la hoja
     * @param _c numero de columnas que se asignaran a la hoja
     */
    public void setColumnas(int _c){
        this.columnas = _c;

    }

    /**
     * Añade el identificador a la hoja y un nombre default
     * @param _aId identificador que la hoja tomará
     */
    public void añadeNombreIdHojaDefault(int _aId){
        this.id = _aId;
        this.nombre = "Hoja" + this.id;
    }

    /**
     * Le asigna un nombre a la hoja
     * @param _nombre nombre asignado
     */
    public void asignaNombre(String _nombre){
        this.nombre = _nombre;
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
     * @param _f fila en la que se encuentra la celda
     * @param _c columna en la que se encuentra la fila
     * @return la celda con Posicion(f, c)
     */
    public Celda getCelda(int _f, int _c){
        Celda cl = this.celdas.get(new Posicion(_f, _c));
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
     * @param _f fila de la posicion en la que se encontrara la celda
     * @param _c columna de la posicion en la que se encontrara la celda
     */
    public boolean addCeldaVacia(int _f, int _c){
        if (!this.celdas.containsKey(new Posicion(_f, _c))){
            Posicion p = new Posicion(_f, _c);
            this.celdas.put(p, new Celda(p));
            return true;
        }
        return false;

    }

    /**
     * Borra una celda con la posicion dada, el que llama a la funcion se encarga de actualizar el valor de
     * filas y columnas, devuelve false si la posicion no existe
     * @param _f fila de la posicion de la celda a borrar
     * @param _c columna de la posicion de la celda a borrar
     */
    public boolean quitarCelda(int _f, int _c){
        if (this.celdas.containsKey(new Posicion(_f, _c))) {
            this.celdas.remove(new Posicion(_f, _c));

            return true;
        }
        return false;

    }

    /**
     * Cambia la posicion de una celda a otra posicion dada, devuelve false si una de las posiciones no existe
     * @param _fant fila de la posicion de la celda a la cual pertenece
     * @param _cant columna de la posicion de la celda a la cual pertenece
     * @param _fdp fila de la nueva posicion a la que se mueve la celda
     * @param _cdp columna de la nueva posicion a la que se mueve la celda
     */
    public boolean cambiarPosicionCelda(int _fant, int _cant, int _fdp, int _cdp){
        Posicion pant = new Posicion(_fant, _cant);
        Posicion pdp = new Posicion(_fdp, _cdp);

        if (this.celdas.containsKey(pdp) && this.celdas.containsKey(pant)) {
            Celda c = this.celdas.get(pant);
            //c.addReferenciante(this.celdas.get(pdp).getReferenciantes());
            this.celdas.replace(pdp, c);
            c.setPosicion(pdp);
            return true;
        }
        return false;

    }

    /**
     * Cambia la posicion de una celda dada a otra posicion dada, devuelve false si la posicion dada no existe
     * @param p posicion a la que se va mover la celda
     * @param c celda que va a cambiar de posicion
     */
    public boolean cambiarPosicionCelda(Posicion p, Celda c){

        if (this.celdas.containsKey(p)) {
            //c.addReferenciante(this.celdas.get(p).getReferenciantes());
            this.celdas.replace(p, c);
            c.setPosicion(p);
            return true;
        }
        return false;


    }





}
