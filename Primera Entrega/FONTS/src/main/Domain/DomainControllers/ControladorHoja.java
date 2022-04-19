package main.Domain.DomainControllers;

import java.util.ArrayList;
import java.util.Arrays;

import main.Domain.DomainModel.Celda;
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

        hojaRef.setFilas(numFilas + _num);
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

        hojaRef.setFilas(numColums + _num);
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

        hojaRef.setFilas(numFilas - _num);
    }

    /**
    * Se elimina el conjunto de columnas contiguas indicadas a partir de un número de columna.
    * Las columnas posteriores al conjunto de columnas indicado reocuparan esas posiciones inferiores.
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

        hojaRef.setFilas(numColums - _num);
    }

    /**
    * Devuelve los valores de las celdas indicadas en formato array.
    * @param _celdas ArrayList de celdas de las cuales obtener los valores
    * @return Array de valores de las columnas indicadas en formato double
    */
    private static double[] getValores(ArrayList<Celda> _celdas) {
        int numValores = _celdas.size();
        double[] valores = new double[_celdas.size()];

        for (int i = 0; i < numValores; i++) {
            valores[i] = Double.parseDouble(_celdas.get(i).getValor());
        }

        return valores;
    }

    /**
    * Obtiene la media aritmética de los valores que contienen las celdas que se indican.
    * @param _celdas conjunto de celdas sobre las que se calculará la media
    * @return media aritmética del valor las celdas indicadas por _celdas
    */
    public static double media(ArrayList<Celda> _celdas) {
        double media = 0;

        for (Celda celda : _celdas) media += Double.parseDouble(celda.getValor());
        
        return media / _celdas.size();
    }

    /**
    * Obtiene la mediana de los valores que contienen las celdas que se indican.
    * @param _celdas conjunto de celdas sobre las que se calculará la mediana
    * @return mediana del valor las celdas indicadas por _celdas
    */
    public static double mediana(ArrayList<Celda> _celdas) {
        double[] valores = ControladorHoja.getValores(_celdas);

        Arrays.sort(valores);
        double mediana;
        if (valores.length % 2 == 0) mediana = (valores[valores.length/2] + valores[valores.length/2 - 1]) / 2;
        else mediana = valores[valores.length/2];

        return mediana;
    }

    /**
    * Obtiene la varianza de los valores que contienen las celdas que se indican.
    * @param _celdas conjunto de celdas sobre las que se calculará la varianza
    * @return varianza del valor las celdas indicadas por _celdas
    */
    public static double varianza(ArrayList<Celda> _celdas) {
        double media = ControladorHoja.media(_celdas);

        double varianza = 0;
        for (Celda celda : _celdas) varianza += Math.pow(Double.parseDouble(celda.getValor()) - media, 2);

        return varianza / _celdas.size();
    }

    /**
    * Obtiene la desviación estandard de los valores que contienen las celdas que se indican.
    * @param _celdas conjunto de celdas sobre las que se calculará la desviación estandard
    * @return desviación estandard del valor las celdas indicadas por _celdas
    */
    public static double desvEstandar(ArrayList<Celda> _celdas) {
        return Math.sqrt(ControladorHoja.varianza(_celdas));
    }

    /**
    * Obtiene el coeficiente de Pearson de los valores que contienen los dos conjuntos de celdas indicados.
    * @param _x primer conjunto de celdas sobre las que se calculará el coeficiente de correlación de Pearson
    * @param _y segundo conjunto de celdas sobre las que se calculará el coeficiente de correlación de Pearson
    * @return coeficiente de correlación de Pearson de los valores las celdas indicadas por _x e _y
    */
    public static double CorrelacionPearson(ArrayList<Celda> _x, ArrayList<Celda> _y) {
        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;
    
        int n = _x.size();
        for(int i = 0; i < n; ++i) {
            double x = Double.parseDouble(_x.get(i).getValor());
            double y = Double.parseDouble(_y.get(i).getValor());
            
            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }
    
        // Covarianza
        double cov = sxy / n - sx * sy / n / n;
        // Desviación estandard de X
        double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
        // Desviación estandard de Y
        double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);
    
        return cov / (sigmax * sigmay);
      }
}