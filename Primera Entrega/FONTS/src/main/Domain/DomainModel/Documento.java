package main.Domain.DomainModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


/*
 * ClassName DomainModel.Documento
 *
 * Version info 1.0.2
 *
 * Author David Pérez Barroso
 */

//Clase serializable y Singleton

/**
 * Clase Documento, clase encargada de gestionar y alamcenar la informacion de un Documento
 * @author David Perez Barroso
 */
public class Documento implements Serializable {

    private static final long serialVersionUID = 41L;


    private static Documento instanceOfThisClass;
    private String nombre = "";
    private int numHojas;
    private String fechaCreacion;
    private HashMap<Integer, Hoja> hojasContenidas = new HashMap<Integer, Hoja>();


    /**
     * Creador del Documento
     */
    private Documento() {}

    /**
     * Funcion que obtiene la instancia de Documento
     * @return la instancia de Documento
     */
    public static Documento getDocumento(){
        if(instanceOfThisClass == null){
            instanceOfThisClass = new Documento();
        }
        return instanceOfThisClass;
    }


    /** Pre: La hoja a anadir no existe en el documento.
     * Inicializa el documento con una Hoja con los valores fila y columnas dados por el usuario
     * @param _nombre sera el nombre del documento
     * @param _fila numero de filas que tendra la hoja por defecto del documento
     * @param _columna numero de columnas que tendra la hoja por defecto del documento
     */
    public void inicializaDocumento(String _nombre, int _fila, int _columna){
        this.nombre = _nombre;

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaCreacion = LocalDateTime.now().format(fm);

        anadeHoja(_fila, _columna);
    }


    /**
     * Le asigna un nombre a documento
     * @param _nombre nombre a asignar
     */
    public void setNombre(String _nombre){
        this.nombre = _nombre;
    }





    /**
     * Obtiene el numero de hojas del documentoObtiene el numero de hojas del documento
     * @return el numero de hojas del documento
     */
    public int getNumHojas() {
        return this.numHojas;
    }


    /**
     * Devuelve la Hoja con identificador _id, si no existe devuelve null
     * @param _id identificador de la hoja
     * @return Hoja identificada por _id, null si no existe
     */
    public Hoja getHoja(int _id) {
        if(this.hojasContenidas.containsKey(_id))
            return this.hojasContenidas.get(_id);
        return null;
    }

    /**
     * Devuelve el nombre del documento
     * @return el nombre del documento
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Devuelve la fecha de creacion del documento
     * @return fecha de creacion del documento
     */
    public String getFecha(){
        return this.fechaCreacion;
    }

    /**
     * Devuelve un HashMap con las hojas contenidas, clasificadas por su id
     * @return hojas contenidas
     */
    public HashMap<Integer, Hoja> getHojasContenidas() {
        return hojasContenidas;
    }

    /**
     * Recalcula el numero de hojas del documento
     */
    private void recalculaNumHojas(){
        this.numHojas = this.hojasContenidas.size();
    }



    /**
     * Anade una Hoja al documento con los valores por defecto, se le asigna un identificador autmaticamente
     */
    public void anadeHojaDf(){
        Hoja h = new Hoja();
        h.anadeNombreIdHojaDefault(hojasContenidas.size()+1);
        this.hojasContenidas.put(hojasContenidas.size()+1, h);
        recalculaNumHojas();
    }

    /**
     * Anade una Hoja al documento con los valores dados, se le asigna un identificador autmaticamente
     * @param _f numero de filas que tendra la hoja
     * @param _c numero de columnas que tendra la hoja
     */
    public void anadeHoja(int _f, int _c){
        Hoja h = new Hoja(_f, _c);
        h.anadeNombreIdHojaDefault(hojasContenidas.size()+1);
        this.hojasContenidas.put(hojasContenidas.size()+1, h);
        recalculaNumHojas();
    }

    /**
     * Elimina una hoja si el identificador de la hoaj existe, devuelve falso si no existe
     * @param _id identificador de la hoja a borrar
     * @return true si se ha borrado, false si la hoja no existe
     */
    public boolean eliminaHoja(int _id){
        if (!this.hojasContenidas.isEmpty() && this.hojasContenidas.containsKey(_id)){
            this.hojasContenidas.remove(_id);
            recalculaNumHojas();
            return true;
        }
        return false;


    }

    /**
     * Elimina la instancia de Documento
     */
    public void eliminaDocumento(){
        instanceOfThisClass = null;
        this.nombre = null;
        this.numHojas = 0;
        this.fechaCreacion = null;
        this.hojasContenidas = null;
    }

    /**
     * Lee un ArrayList en el que hay un archivo csv en string y anade una hoja al documento con los valores que contiene
     * @param csv ArrayList de String donde estan los valores del archivo csv
     */
    public void leeCSV(ArrayList<String> csv){

        Hoja h = new Hoja();
        h = h.csvAHoja(csv);
        h.anadeNombreIdHojaDefault(hojasContenidas.size()+1);
        this.hojasContenidas.put(hojasContenidas.size()+1, h);
        recalculaNumHojas();

    }

}
