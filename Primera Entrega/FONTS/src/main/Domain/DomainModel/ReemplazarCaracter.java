package main.Domain.DomainModel;


/*
 * ClassName ReemplazarCaracter
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ReemplazarCaracter extends Funcion{

    private String txt;
    private String cr;
    private String nc;


    /**
     * Constructora de la clase
     * @param _txt texto al cual se le va a reemplazar un caracter a almacenar
     * @param _cr caracter a reemplazar para alamcenar
     * @param _nc caracter nuevo a almacenar
     */
    public ReemplazarCaracter(String _txt, String _cr, String _nc){
         this.txt = _txt;
         this.cr = _cr;
         this.nc = _nc;

    }


    /**
     * Remplaza un caracter elegido por otro en el texto original
     * @return Devuelve un string con -1 si no contiene la letra a remplazar, de lo contrario devuelve la palabra con
     * la letra reemplazada
     */
    public String execute() {
        if(!this.txt.contains(this.cr)) return "-1";
        return this.txt.replace(this.cr, this.nc);
    }
}
