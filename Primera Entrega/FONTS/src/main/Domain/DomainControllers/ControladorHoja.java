package main.Domain.DomainControllers;
import DomainModel.*;
import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;

/*ClassName ControladorHoja
 *
 * Version info 0.1
 *
 * Author Daniel Gallardo Peña
 */

public class ControladorHoja {
    
    private Hoja hojaRef;

    public ControladorHoja() {}

    /**
    * Se guarda la hoja que le pasa presentación.
    * @param _idh identificador de la hoja
    */
    public void asignaHoja(int _idh) {
        hojaRef = Documento.getDocumento().getHoja(_idh);
    }

    /**
    * Se añade una referencia entre celdas de la misma hoja
    * @param _filaEmisor fila de la celda referenciada
    * @param _columEmisor columna de la celda referenciada
    * @param _filaRecep fila de la celda referenciante
    * @param _columRecep columna de la celda referenciante
    public void referenciarContenido(int _filaEmisor, int _columEmisor, int _filaRecep, int _columRecep) {
        Celda celdaEmisora = hojaRef.getCelda(_filaEmisor, _columEmisor);
        celdaEmisora.addReferenciante(hojaRef.getCelda(_filaRecep, _columRecep));
        }
    */

    /**
    * Se añaden un conjunto de filas en blanco a partir un número de fila.
    * Las filas que habían en la posición _pos y posteriores se moveran _num posiciones hacia delante.
    * @param _pos numero de fila que tendrá la primera fila del bloque añadido
    * @param _num numero de filas añadidas
    */
    public void addFilas(int _pos, int _num) {
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Movemos todas las filas por encima de _pos _num posiciones 
        for (int iterFilas = numFilas; iterFilas >= _pos; --iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(iterFilas, iterColums, iterFilas + _num, iterColums);
            }
        }

        // Añadimos las nuevas filas
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.addCeldaVacia(_pos + iterFilas, iterColums);
            }
        }

        //FALTA ACTUALIZAR TAMAÑO FILAS, ESPERANDO A QUE IMPLEMENTEN EL METODO
    }

    /**
    * Se añaden un conjunto de columnas en blanco a partir un número de columna.
    * Las columnas que habían en la posición _pos y posteriores se moveran _num posiciones hacia delante.
    * @param _pos numero de columna que tendrá la primera columna del bloque añadido
    * @param _num numero de columnas añadidas
    */
    public void addColumnas(int _pos, int _num) {
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Movemos todas las columnas a la derecha de _pos _num posiciones 
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = numColums; iterColums >= _pos; --iterColums) {
                hojaRef.cambiarPosicionCelda(iterFilas, iterColums, iterFilas, iterColums + _num);
            }
        }

        // Añadimos las nuevas columnas
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaRef.addCeldaVacia(iterFilas, _pos + iterColums);
            }
        }

        //FALTA ACTUALIZAR TAMAÑO COLUMNAS, ESPERANDO A QUE IMPLEMENTEN EL METODO
    }

    /**
    * Se elimina el conjunto de filas contiguas indicadas a partir de un número de fila.
    * Las filas posteriores al conjunto de filas indicado reocuparan esas posiciones inferiores.
    * @param _pos numero de columna que tendrá la primera fila del conjunto a borrar
    * @param _num numero de filas a eliminar
    */
    public void eliminarFilas(int _pos, int _num) {
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Borramos las filas indicada
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.quitarCelda(_pos + iterFilas, iterColums);
            }
        }

        // Reasignamos la posicion a las filas posteriores
        for (int iterFilas = _pos + 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(iterFilas, iterColums, iterFilas - 1, iterColums);
            }
        }

         //FALTA ACTUALIZAR TAMAÑO FILAS, ESPERANDO A QUE IMPLEMENTEN EL METODO
    }

    /**
    * Se elimina el conjunto de columnas contiguas indicadas a partir de un número de columna.
    * Las columnass posteriores al conjunto de columnas indicado reocuparan esas posiciones inferiores.
    * @param _pos numero de columna que tendrá la primera columna del conjunto a borrar
    * @param _num numero de columnas a eliminar
    */
    public void eliminarColumnas(int _pos, int _num) {
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Borramos las columna indicada
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaRef.quitarCelda(iterFilas, _pos + iterColums);
            }
        }

        // Reasignamos la posicion a las columnas posteriores
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums =  _pos + 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(iterFilas, iterColums, iterFilas, iterColums - 1);
            }
        }

         //FALTA ACTUALIZAR TAMAÑO COLUMNAS, ESPERANDO A QUE IMPLEMENTEN EL METODO
    }






}