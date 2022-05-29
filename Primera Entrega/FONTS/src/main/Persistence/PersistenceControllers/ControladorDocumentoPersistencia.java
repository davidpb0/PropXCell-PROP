package main.Persistence.PersistenceControllers;

import main.Domain.DomainModel.Documento;

import java.io.*;

    /*
     * ClassName ControladorDocumentoPersistencia
     *
     * Version info 0.0.2
     *
     * Author David Perez Barroso
     */

//Si se lo paso por parametro, no lo estoy comunicando, no?

public class ControladorDocumentoPersistencia {

    /**
     * Creadora de la clase
     */
        public ControladorDocumentoPersistencia(){}

    /**
     * Almacena el documento que le pasan en disco en el path que le pasan como parametro
     * @param _d Documento a guardar
     * @param _path Directorio donde se guardara el documento
     * @throws Exception lanza una excepcion si no encuentra el path o si surge algun error a la hora de escribir.
     */
        public void almacenaDocumento(Documento _d, String _path) throws Exception {
            try {
                ObjectOutputStream escribiendoDoc =
                        new ObjectOutputStream(new FileOutputStream(_path));
                escribiendoDoc.writeObject(_d);
                escribiendoDoc.close();

            } catch (Exception e) {
                throw new Exception("Error al escribir el Documento.");
            }
        }


    /**
     * Carga un documento en la aplicación almacenado en disco
     * @param _path Directorio donde se encuentra el disco a cargar
     * @return Devuelve el documento seleccionado en disco
     * @throws Exception lanza una excepcion si surge algun error en la carga
     */
    public Documento cargaDocumento(String _path) throws Exception {
            try {
                ObjectInputStream recuperandoDoc =
                        new ObjectInputStream(new FileInputStream(_path));
                Documento d = (Documento) recuperandoDoc.readObject();
                recuperandoDoc.close();
                return d;


            } catch (Exception e) {
                throw new Exception("Error al cargar el Documento.");
            }
        }
}


