package main.Domain.DomainModel;


/*
 * ClassName ReemplazarPalabra
 *
 * Version info 1.0.0
 *
 * Author David Pérez Barroso
 */
public class ReemplazarPalabra extends Funcion{


    private String txt;
    private int pos;
    private int longd;
    private String ntxt;

  /**
   * Constructora de la clase
   * @param _txt texto del cual una parte va a ser sustituida a almacenar
   * @param _pos posicion donde comenzará la sustitución a almacenar
   * @param _longd numero de caracteres del texto que se sustituirán a almacenar
   * @param _ntxt texto que se insertará en el texto original a almacenar
   */
    public ReemplazarPalabra(String _txt, int _pos, int _longd, String _ntxt){
        this.txt = _txt;
        this.pos = _pos;
        this.longd = _longd;
        this.ntxt = _ntxt;

    }

    /**
     * Sustituye parte de una cadena de texto almacenada por otra cadena almacenada y lo pone en el valor de la celda
     * @return Devuelve un string con -1 si hay algun error en los argumentos (_pos o _long), de lo contrario
     * devuelve la palabra con el trozo reemplazado
     */
    @Override
    public String execute() {
        if(this.pos > this.txt.length()) return "-1";

        if(this.pos < 0 || this.longd <= 0) return "-1";

        if(this.longd-1 > this.txt.length()-this.pos) return "-1";

        String s = this.txt.substring(this.pos-1, this.pos+this.longd-1);

        return this.txt.replaceAll(s, this.ntxt);
    }
}
