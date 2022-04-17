package main.Domain.DomainModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


/*
 * ClassName DomainModel.Documento
 *
 * Version info 0.0.3
 *
 * Author David Pérez Barroso
 */

//Clase serializable y Singleton
public class Documento implements Serializable {

    private static Documento instanceOfThisClass;
    private String nombre;
    private int numHojas;
    private String fechaUltMod;
    private HashMap<Integer, Hoja> hojasContenidas = new HashMap<Integer, Hoja>();


    /**
     * Creador del Documento
     */
    private Documento(){}

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


    /**
     * Inicializa el documento con una Hoja con los valores fila y columnas dados por el usuario
     * @param _nombre sera el nombre del documento
     * @param _fila numero de filas que tendra la hoja por defecto del documento
     * @param _columna numero de columnas que tendra la hoja por defecto del documento
     */
    public void inicializaDocumento(String _nombre, int _fila, int _columna){
        this.nombre = _nombre;

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaUltMod = LocalDateTime.now().format(fm);

        añadeHoja(_fila, _columna);
    }



    /**
     * Inicializa el documento con una Hoja con los valores fila y columnas por defecto
     * @param _nombre sera el nombre del documento
     */
    public void inicializaDocumentoDefault(String _nombre){
        this.nombre = _nombre;

        DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.fechaUltMod = LocalDateTime.now().format(fm);

        añadeHojaDf();
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
    public Hoja getHoja(int _id){
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
     * Devuelve la fecha de creación del documento
     * @return fecha de creacion del documento
     */
    public String getFecha(){
        return this.fechaUltMod;
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
    public void recalculaNumHojas(){
        this.numHojas = this.hojasContenidas.size();
    }

    /**
     * Añade una Hoja con los valores por defecto al documento
     */
    public void añadeHojaDf(){
        Hoja h = new Hoja();
        h.añadeNombreIdHojaDefault(hojasContenidas.size()+1);
        this.hojasContenidas.put(hojasContenidas.size()+1, h);
        recalculaNumHojas();
    }

    /**
     * Añada una Hoja al documento con los valores dados, se le asigna un identificador autmaticamente
     * @param _f numero de filas que tendra la hoja
     * @param _c numero de columnas que tendra la hoja
     */
    public void añadeHoja(int _f, int _c){
        Hoja h = new Hoja(_f, _c);
        h.añadeNombreIdHojaDefault(hojasContenidas.size()+1);
        this.hojasContenidas.put(hojasContenidas.size()+1, h);
        recalculaNumHojas();
    }

    /**
     * Elimina una hoja si el identificador de la hoaj existe, devuelve falso si no existe
     * @param _id identificador de la hoja a borrar
     * @return true si se ha borrado, false si la hoja no existe
     */
    public boolean eliminaHoja(int _id){
        if (this.hojasContenidas.containsKey(_id) && !this.hojasContenidas.isEmpty()){
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
        this.instanceOfThisClass = null;
        this.nombre = null;
        this.numHojas = 0;
        this.fechaUltMod = null;
        this.hojasContenidas = null;
    }
}