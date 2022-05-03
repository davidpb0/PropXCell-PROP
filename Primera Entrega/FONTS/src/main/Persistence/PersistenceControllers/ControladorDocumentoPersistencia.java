package main.Persistence.PersistenceControllers;
import java.io.*;
import main.Domain.DomainModel.Documento;

    /*
     * ClassName ControladorDocumentoPersistencia
     *
     * Version info 0.0.1
     *
     * Author David Perez Barroso
     */

//Si se lo paso por parametro, no lo estoy comunicando, no?

public class ControladorDocumentoPersistencia {

        private static ControladorDocumentoPersistencia instanceOfThisClass;

        private ControladorDocumentoPersistencia(){}

        public static ControladorDocumentoPersistencia getCtrlDocPers(){
            if (instanceOfThisClass == null) instanceOfThisClass = new ControladorDocumentoPersistencia();
            return instanceOfThisClass;
        }

        public void almacenaDocumento(Documento _d){
            try{
                ObjectOutputStream escribiendoDoc =
                        new ObjectOutputStream(new FileOutputStream("D:/david/FIB/PROP/DataSer/documento"));
                escribiendoDoc.writeObject(_d);
                escribiendoDoc.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Documento cargaDocumento(){
            try {
                ObjectInputStream recuperandoDoc =
                        new ObjectInputStream(new FileInputStream("D:/david/FIB/PROP/DataSer/documento"));
                return (Documento) recuperandoDoc.readObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}


