import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/*
 * ClassName Documento
 *
 * Version info 1.0
 *
 * Author David Pérez Barroso
 */

//Clase serializable
public class Documento {

        private static Documento instanceOfThisClass;
        private String nombre;
        private int numHojas;
        private LocalDateTime fechaUltMod;
        private HashMap<Integer, Hoja> hojasContenidas = new HashMap<Integer, Hoja>();

        private Documento(){}

        public static Documento getInstance(){
            if(instanceOfThisClass == null){
                instanceOfThisClass = new Documento();
            }
            return instanceOfThisClass;
        }


        //Inicializa el documento con una Hoja con los valores fila y columnas dados por el usuario

        public void inicializaDocumento(String nombre, int fila, int columna){
            this.nombre = nombre;

            this.fechaUltMod = LocalDateTime.now();

            Hoja hIni = new Hoja(fila, columna);
            hIni.inicializaHojaDefault(hojasContenidas.size()+1);
            this.hojasContenidas.put(hojasContenidas.size()+1, hIni);
            this.numHojas = hojasContenidas.size();
        }

        //Inicializa el documento con una Hoja con los valores fila y columnas por defecto

        public void inicializaDocumentoDefault(String nombre){
            this.nombre = nombre;

            this.fechaUltMod = LocalDateTime.now();

            Hoja hIni = new Hoja();
            hIni.inicializaHojaDefault(hojasContenidas.size()+1);
            this.hojasContenidas.put(hojasContenidas.size()+1, hIni);
            this.numHojas = hojasContenidas.size();
        }

        //Obtiene el numero de hojas del documento
        public int getNumHojas() {
            return this.numHojas;
        }

        /*
        Pre: Existe una hoja con el identificador id
        Post: Obtiene la hoja con el identificador id
        */
        public Hoja getHoja(int id){
            return this.hojasContenidas.get(id);

        }



}
