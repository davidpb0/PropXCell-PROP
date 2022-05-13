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

        public ControladorDocumentoPersistencia(){}

        public void almacenaDocumento(Documento _d, String _path) throws Exception {
            try {
                ObjectOutputStream escribiendoDoc =
                        new ObjectOutputStream(new FileOutputStream(_path));
                escribiendoDoc.writeObject(_d);
                escribiendoDoc.close();

            } catch (FileNotFoundException e) {
                throw new Exception("Error al escribir el Documento.");
            }
        }

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


