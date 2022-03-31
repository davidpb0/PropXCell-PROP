package DomainModel;/*
 * ClassName DomainModel.Hoja
 *
 * Version info 0.0.2
 *
 * Author David Pérez Barroso
 */

import java.util.HashMap;

public class Hoja {

    private int id;
    private String nombre;
    private int filas;
    private int columnas;
    private HashMap<Posicion, Celda> celdas;

    //Creadora con los valores por defecto, el que crea la hoja se hace responsable de inicializarla
    Hoja(){
        this.filas = 50;
        this.columnas = 50;
        this.celdas = new HashMap<Posicion, Celda>();
        inicializaHoja(this);
    }

    //Creadora dados un numero de filas y un nuemero de columnas, el que crea la hoja se hace responsable de inicializarla
    Hoja(int _filas, int _columnas){
        this.filas = _filas;
        this.columnas = _columnas;
        this.celdas = new HashMap<Posicion, Celda>();
        inicializaHoja(this);

    }

    private void inicializaHoja(Hoja h){
        int f = h.getFilas();
        int c = h.getColumnas();

        for (int i = 1; i <= f; ++i){
            for (int j = 1; j <= c; ++j){
               h.celdas.put(new Posicion(i, j), new Celda());
            }
        }
    }

    //inicializa la DomainModel.Hoja con identificador y nombre por defecto
    public void añadeNombreIdHojaDefault(int _aId){
        this.id = _aId;
        this.nombre = "Hoja" + this.id;
    }

    //Le asigna un nombre a la hoja
    public void assignaNombre(String _nombre){
        this.nombre = _nombre;
    }

    //Comprueba que la hoja es la hoja con identificador id
    public boolean esHoja(int _id){
        return this.id == _id;
    }

    //Devuelve el identificador de la hoja
    public int getId(){
        return this.id;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public int getFilas(){
        return this.filas;
    }

    public String getNombre(){
        return this.nombre;
    }

    public HashMap<Posicion, Celda> getCeldas(){
        return this.celdas;
    }
}
