package DomainModel;
/*
 * ClassName DomainModel.Hoja
 *
 * Version info 0.0.3
 *
 * Author David Pérez Barroso
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
    public void assignaNombre(String _nombre){
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
     * Añada una celda vacia en la posicion indicada
     * @param _f fila de la posicion en la que se encontrara la celda
     * @param _c columna de la posicion en la que se encontrara la celda
     */
    public void addCeldaVacia(int _f, int _c){
        if (this.celdas.containsKey(new Posicion(_f, _c))){
            System.out.println("La posicion introducida ya existe");
        }
        else{
            Posicion p = new Posicion(_f, _c);
            this.celdas.put(p, new Celda(p));
        }

    }

    /**
     * Borra una celda con la posicion dada
     * @param _f fila de la posicion de la celda a borrar
     * @param _c columna de la posicion de la celda a borrar
     */
    public void quitarCelda(int _f, int _c){
        if (this.celdas.containsKey(new Posicion(_f, _c))) {
            this.celdas.remove(new Posicion(_f, _c));
        }
        else{
            System.out.println("No existe la posicion introducida");
        }

    }

    /**
     * Cambia la posicion de una celda a otra posicion dada
     * @param _fant fila de la posicion de la celda a la cual pertenece
     * @param _cant columna de la posicion de la celda a la cual pertenece
     * @param _fdp fila de la nueva posicion a la que se mueve la celda
     * @param _cdp columna de la nueva posicion a la que se mueve la celda
     */
    public void cambiarPosiconCelda(int _fant, int _cant, int _fdp, int _cdp){
        Posicion pant = new Posicion(_fant, _cant);
        Posicion pdp = new Posicion(_fdp, _cdp);

        if(!this.celdas.get(pdp).getContenido().isEmpty()){
            System.out.println("Vas a remplazar una celda con contenido");
        }
        if (this.celdas.containsKey(pdp) && this.celdas.containsKey(pant)) {
            Celda c = this.celdas.get(pant);
            this.celdas.replace(pdp, c);
            c.setPosicion(pdp);
        }
        else{
            System.out.println("No existe una de las posiciones introducidas");
        }

    }


}
