package main.Domain.DomainControllers;

import main.Domain.DomainModel.Documento;
import main.Domain.DomainModel.Hoja;
import main.Domain.DomainModel.Posicion;

import java.util.Arrays;

/*ClassName ControladorHoja
 *
 * Version info 1.0.0
 *
 * Author Daniel Gallardo Peña
 */

public class ControladorHoja {
    
    private Hoja hojaRef;

    public ControladorHoja() {}

    /**
    * Se guarda la hoja que le pasa presentación.
    * @param _idh identificador de la hoja que siempre corresponderá a una hoja existente
    */
    public void asignaHoja(int _idh) {
        this.hojaRef = Documento.getDocumento().getHoja(_idh);
    }

    /**
     * Se cambia el nombre de la hoja.
     * @param _nuevoNombre nombre de la hoja pasado por presentación.
     */
    public void renombraHoja(String _nuevoNombre) {
        hojaRef.asignaNombre(_nuevoNombre);
    }

    /**
     * Devuelve el id de la hoja actual.
     * @return el id de la hoja actual.
     */
    public int getIdHoja() {
        return hojaRef.getId();
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
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Movemos todas las filas por encima de _pos _num posiciones 
        for (int iterFilas = numFilas; iterFilas >= _pos; --iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas + _num, iterColums));
            }
        }

        // Añadimos las nuevas filas
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.addCeldaVacia(new Posicion(_pos + iterFilas, iterColums));

            }
        }

        hojaRef.setFilas(numFilas + _num);
    }

    /**
    * Se añaden un conjunto de columnas en blanco a partir un número de columna.
    * Las columnas que habían en la posición _pos y posteriores se moveran _num posiciones hacia delante.
    * @param _pos numero de columna que tendrá la primera columna del bloque añadido
    * Siempre será un número de columna existente o el inmediatamente superior a la última fila
    * @param _num numero de columnas añadidas, siempre es positivo
    */
    public void addColumnas(int _pos, int _num) {
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Movemos todas las columnas a la derecha de _pos _num posiciones 
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = numColums; iterColums >= _pos; --iterColums) {
                hojaRef.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas, iterColums + _num));
            }
        }

        // Añadimos las nuevas columnas
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaRef.addCeldaVacia(new Posicion(iterFilas, _pos + iterColums));
            }
        }

        hojaRef.setFilas(numColums + _num);
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
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Borramos las filas indicada
        for (int iterFilas = 0; iterFilas < _num; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.quitarCelda(new Posicion(_pos + iterFilas, iterColums));
            }
        }

        // Reasignamos la posicion a las filas posteriores
        for (int iterFilas = _pos + 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas - 1, iterColums));
            }
        }

        hojaRef.setFilas(numFilas - _num);
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
        int numFilas = hojaRef.getFilas();
        int numColums = hojaRef.getColumnas();

        // Borramos las columna indicada
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums = 0; iterColums < _num; ++iterColums) {
                hojaRef.quitarCelda(new Posicion(iterFilas, _pos + iterColums));
            }
        }

        // Reasignamos la posicion a las columnas posteriores
        for (int iterFilas = 1; iterFilas <= numFilas; ++iterFilas) {
            for (int iterColums =  _pos + 1; iterColums <= numColums; ++iterColums) {
                hojaRef.cambiarPosicionCelda(new Posicion(iterFilas, iterColums), new Posicion(iterFilas, iterColums - 1));
            }
        }

        hojaRef.setFilas(numColums - _num);
    }

    /**
    * Obtiene la media aritmética de los valores del conjunto indicado.
    * @param _valores conjunto de valores sobre las que se calculará la media, todos son números válidos
    * @return media aritmética de los elementos de _valores
    */
    public static double media(String[] _valores) {
        double media = 0;

        for (String v : _valores) media += Double.parseDouble(v);
        
        return media / _valores.length;
    }

    /**
    * Obtiene la mediana de los valores del conjunto indicado.
    * @param _valores conjunto de valores sobre las que se calculará la mediana, todos son números válidos
    * @return mediana de los elementos de _valores
    */
    public static double mediana(String[] _valores) {
        //Convertimos el array de Strings a array de Double
        double[] valores = Arrays.stream(_valores).mapToDouble(Double::parseDouble).toArray();

        Arrays.sort(valores);
        double mediana;
        if (valores.length % 2 == 0) mediana = (valores[valores.length/2] + valores[valores.length/2 - 1]) / 2;
        else mediana = valores[valores.length/2];

        return mediana;
    }

    /**
    * Obtiene la varianza de los valores del conjunto indicado.
    * @param _valores conjunto de valores sobre las que se calculará la varianza, todos son números válidos
    * @return varianza de los elementos de _valores
    */
    public static double varianza(String[] _valores) {
        double sx = 0;
        double sxx = 0;
        int n = _valores.length;

        for (String x : _valores) {
            double valor = Double.parseDouble(x);
            sx += valor;
            sxx += valor * valor; 
        }

        // Varianza = E(X^2) - E(X)^2
        return sxx/n - sx/n * sx/n;
    }

    /**
    * Obtiene la desviación estandard de los valores del conjunto indicado.
    * @param _valores conjunto de valores sobre las que se calculará la desviación estandard, todos son números válidos
    * @return desviación estandard de los elementos de _valores
    */
    public static double desvEstandar(String[] _valores) {
        return Math.sqrt(ControladorHoja.varianza(_valores));
    }

    /**
    * Obtiene la covarianza resultante de los dos conjuntos de valores indicados.
    * @param _valoresX primer conjunto de valores sobre las que se calculará la covarianza, todos son números válidos
    * @param _valoresY segundo conjunto de celdas sobre las que se calculará la covarianza, todos son números válidos
    * @return covarianza resultante de los dos conjuntos de valores _valoresX y _valoresY
    * Pre: Tanto _valoresX como _valoresY tienen el mismo número de elementos
    */
    public static double covarianza(String[] _valoresX, String[] _valoresY) {
        double sx = 0;
        double sy = 0;
        double sxy = 0;
        int n = _valoresX.length;

        for(int i = 0; i < n; ++i) {
            double x = Double.parseDouble(_valoresX[i]);
            double y = Double.parseDouble(_valoresY[i]);
            
            sx += x;
            sy += y;
            sxy += x * y;
        }

        // Covarianza = E(XY) - E(X)*E(Y)
        return sxy/n - sx/n * sy/n;
    }

    /**
    * Obtiene el coeficiente de correlacion de Pearson resultante de los dos conjuntos de valores indicados.
    * @param _valoresX primer conjunto de valores sobre las que se calculará el coeficiente de correlacion de Pearson, todos son números válidos
    * @param _valoresY segundo conjunto de celdas sobre las que se calculará el coeficiente de correlacion de Pearson, todos son números válidos
    * @return coeficiente de correlacion de Pearson resultante de los dos conjuntos de valores _valoresX y _valoresY
    * Pre: Tanto _valoresX como _valoresY tienen el mismo número de elemento
    */
    public static double correlacionPearson(String[] _valoresX, String[] _valoresY) {
        double sx = 0;
        double sy = 0;
        double sxx = 0;
        double syy = 0;
        double sxy = 0;
        int n = _valoresX.length;
        
        for(int i = 0; i < n; ++i) {
            double x = Double.parseDouble(_valoresX[i]);
            double y = Double.parseDouble(_valoresY[i]);
            
            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }
    
        // Covarianza = E(XY) - E(X)*E(Y)
        double cov = sxy/n - sx/n * sy/n;
        // Desviación estandard de X = sqrt(E(X^2) - E(X)^2)
        double sigmaX = Math.sqrt(sxx/n - sx/n * sx/n);
        // Desviación estandard de Y = sqrt(E(Y^2) - E(Y)^2)
        double sigmaY = Math.sqrt(syy/n - sy/n * sy/n);
    
        return cov / (sigmaX * sigmaY);
      }
}