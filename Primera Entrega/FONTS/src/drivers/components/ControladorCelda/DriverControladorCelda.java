package drivers.components.ControladorCelda;

import main.Domain.DomainControllers.ControladorCelda;
import main.Domain.DomainModel.*;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class DriverControladorCelda {
    private static BufferedReader br;
    private static String[] palabras;
    private static int constructor = 0;

    @InjectMocks
    private static ControladorCelda cc = null;

    @Mock
    Traductor traductor = Traductor.getTraductor();

    @Mock
    private Celda celda;

    @Mock
    private Hoja hoja;

    @Mock
    private Documento documento;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);

    }


    private static void opciones() {
        System.out.println("A continuacion dispone de una lista de las funciones que puede ejecutar la clase ControladorCelda");
        System.out.println("\t1: ControladorCelda()");
        System.out.println("\t2: asignaCeldaPosicion(String idhoja, String fila, String columna)");
        System.out.println("\t3: escribirContenido(String content)::DefinirFuncionTruncamiento");
        System.out.println("\t4: escribirContenido(String content)::DefinirFuncionAproximarValor");
        System.out.println("\t5: escribirContenido(String content)::DefinirFuncionValorAbsoluto");
        System.out.println("\t6: escribirContenido(String content)::DefinirFuncionConversionDecBin");
        System.out.println("\t7: escribirContenido(String content)::DefinirFuncionConversionBinDec");
        System.out.println("\t8: escribirContenido(String content)::DefinirFuncionConversionHexDec");
        System.out.println("\t9: escribirContenido(String content)::DefinirFuncionConversionDecHex");
        System.out.println("\t10: escribirContenido(String content)::DefinirFuncionConversionHexBin");
        System.out.println("\t11: escribirContenido(String content)::DefinirFuncionConversionBinHex");
        System.out.println("\t12: escribirContenido(String content)::DefinirFuncionMes");
        System.out.println("\t13: escribirContenido(String content)::DefinirFuncionAño");
        System.out.println("\t14: escribirContenido(String content)::DefinirFuncionDia");
        System.out.println("\t15: escribirContenido(String content)::DefinirFuncionNombreDia");
        System.out.println("\t16: escribirContenido(String content)::DefinirFuncionLongitud");
        System.out.println("\t17: escribirContenido(String content)::DefinirFuncionContarLetra");
        System.out.println("\t18: escribirContenido(String content)::DefinirFuncionReemplazarCaracter");
        System.out.println("\t19: escribirContenido(String content)::DefinirFuncionReemplazarPalabra");
        System.out.println("\t20: escribirContenido(String content)::DefinirFuncionMedia");
        System.out.println("\t21: escribirContenido(String content)::DefinirFuncionMediana");
        System.out.println("\t22: escribirContenido(String content)::DefinirFuncionVarianza");
        System.out.println("\t23: escribirContenido(String content)::DefinirFuncionCovarianza");
        System.out.println("\t24: escribirContenido(String content)::DefinirFuncionDesviacionStd");
        System.out.println("\t25: escribirContenido(String content)::DefinirFuncionCoefPearson");
        System.out.println("\t26: escribirContenido(String content)::Referencia");
        System.out.println("\t27: escribirContenido(String content)::NoFuncion");
        System.out.println("\t28: truncarValor(double v, int op)");
        System.out.println("\t29: valorAbsoluto(double a)");
        System.out.println("\t30: aproximarValor(double v)");
        System.out.println("\t31: convertirValorDB(int dec)");
        System.out.println("\t32: convertirValorBD(int _b)");
        System.out.println("\t33: convertirValorDH(int _dec)");
        System.out.println("\t34: convertirValorHD(String _h)");
        System.out.println("\t35: convertirValorBH(int _b)");
        System.out.println("\t36: convertirValorHB(String _h)");
        System.out.println("\t37: obtenerMes(String _fecha)");
        System.out.println("\t38: obtenerAño(String _fecha)");
        System.out.println("\t39: obtenerDia(String _fecha)");
        System.out.println("\t40: obtenerNombreDia(String _fecha)");
        System.out.println("\t41: longitudPalabra(String _palabra)");
        System.out.println("\t42: contarLetra(String _palabra, String _letra)");
        System.out.println("\t43: reemplazarPalabra(String _txt, int _pos, int _long,  String _ntxt)");
        System.out.println("\t44: reemplazarCaracter(String _txt, String _cr, String _nc)");
        System.out.println("\t0: quit");
        System.out.print("Escoge una opcion: ");

    }

    public static void main(String[] args) {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            boolean quit = false;

            DriverControladorCelda driver = new DriverControladorCelda();
            while (!quit) {
                System.out.println("");
                opciones();
                String line = br.readLine();
                if (!Objects.equals(line, "")) palabras = line.split(" ");
                else palabras[0] = "-1";
                int opcionSelc = -1;
                try {
                    opcionSelc = Integer.parseInt(palabras[0]);
                } catch (NumberFormatException ignored) {}

                if(cc == null && opcionSelc > 1 && constructor == 0) System.out.println("Primero tienes que llamar a la constructora");
                else if(constructor == 1 && opcionSelc > 2) System.out.println("Hace falta que selecciones una celda");
                else{
                    switch (opcionSelc) {
                        case 1: //constructora
                            if (nParamValido(0)) driver.constructorTest();
                            break;
                        case 2:
                            if (nParamValido(2)) driver.asignaCeldaPosicionTest();
                            break;
                        case 3:
                            if (nParamValido(1)) driver.escribirContenidoFTruncamientoTest();
                            break;
                        case 4:
                            if (nParamValido(1)) driver.escribirContenidoFAproximarValor();
                            break;
                        case 5:
                            if (nParamValido(1)) driver.escribirContenidoFValorAbsolutoTest();
                            break;
                        case 6:
                            if (nParamValido(1)) driver.escribirContenidoFConversionDecBinTest();
                            break;
                        case 7:
                            if (nParamValido(1)) driver.escribirContenidoFConversionBinDecTest();
                            break;
                        case 8:
                            if (nParamValido(1)) driver.escribirContenidoFConversionHexDecTest();
                            break;
                        case 9:
                            if (nParamValido(1)) driver.escribirContenidoFConversionDecHexTest();
                            break;
                        case 10:
                            if (nParamValido(1)) driver.escribirContenidoFConversionHexBinTest();
                            break;
                        case 11:
                            if (nParamValido(1)) driver.escribirContenidoFConversionBinHexTest();
                            break;
                        case 12:
                            if (nParamValido(1)) driver.escribirContenidoFMesTest();
                            break;
                        case 13:
                            if (nParamValido(1)) driver.escribirContenidoFAñoTest();
                            break;
                        case 14:
                            if (nParamValido(1)) driver.escribirContenidoFDiaTest();
                            break;
                        case 15:
                            if (nParamValido(1)) driver.escribirContenidoFNombreDiaTest();
                            break;
                        case 16:
                            if (nParamValido(1)) driver.escribirContenidoFLongitudTest();
                            break;
                        case 17:
                            if (nParamValido(1)) driver.escribirContenidoFContarLetraTest();
                            break;
                        case 18:
                            if (nParamValido(1)) driver.escribirContenidoFReemplazarCaracterTest();
                            break;
                        case 19:
                            if (nParamValido(1)) driver.escribirContenidoFReemplazarPalabraTest();
                            break;
                        case 20:
                            if (nParamValido(1)) driver.escribirContenidoFMediaTest();
                            break;
                        case 21:
                            if (nParamValido(1)) driver.escribirContenidoFMedianaTest();
                            break;
                        case 22:
                            if (nParamValido(1)) driver.escribirContenidoFVarianzaTest();
                            break;
                        case 23:
                            if (nParamValido(1)) driver.escribirContenidoFCovarianzaTest();
                            break;
                        case 24:
                            if (nParamValido(1)) driver.escribirContenidoFDesvStdTest();
                            break;
                        case 25:
                            if (nParamValido(1)) driver.escribirContenidoFCoefPearsonTest();
                            break;
                        case 26:
                            if (nParamValido(1)) driver.escribirContenidoReferenciaTest();
                            break;
                        case 27:
                            if (nParamValido(1)) driver.escribirContenidoNoFuncTest();
                            break;
                        case 28:
                            if (nParamValido(2)) driver.truncarValorTest();
                            break;
                        case 29:
                            if (nParamValido(1)) driver.valorAbsolutoTest();
                            break;
                        case 30:
                            if (nParamValido(1)) driver.aproximarValorTest();
                            break;
                        case 31:
                            if (nParamValido(1)) driver.convertirValorDecBinTest();
                            break;
                        case 32:
                            if (nParamValido(1)) driver.convertirValorBinDecTest();
                            break;
                        case 33:
                            if (nParamValido(1)) driver.convertirValorDecHexTest();
                            break;
                        case 34:
                            if (nParamValido(1)) driver.convertirValorHexDecTest();
                            break;
                        case 35:
                            if (nParamValido(1)) driver.convertirValorBinHexTest();
                            break;
                        case 36:
                            if (nParamValido(1)) driver.convertirValorHexBinTest();
                            break;
                        case 37:
                            if (nParamValido(1)) driver.obtenerMesTest();
                            break;
                        case 38:
                            if (nParamValido(1)) driver.obtenerAñoTest();
                            break;
                        case 39:
                            if (nParamValido(1)) driver.obtenerDiaTest();
                            break;
                        case 40:
                            if (nParamValido(1)) driver.obtenerNombreDiaTest();
                            break;
                        case 41:
                            if (nParamValido(1)) driver.longitudPalabraTest();
                            break;
                        case 42:
                            if (nParamValido(2)) driver.contarLetraTest();
                            break;
                        case 43:
                            if (nParamValido(4)) driver.reemplazarPalabraTest();
                            break;
                        case 44:
                            if (nParamValido(3)) driver.reemplazarCaracterTest();
                            break;
                        case 0:
                            quit = true;
                            break;
                        default:
                            System.out.println("El parametro insertado no es correcto, tiene que ser un nombre natural entre 0 y 44");
                    }
                    System.out.print("\nPulsa ENTER tecla para continuar");
                    br.readLine();
                }
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("Ha habido un problema inesperado.");
        }
    }
    public static Boolean nParamValido(int n) {
        if (palabras.length != n + 1) {
            System.out.println("El numero de parametros introducidos no es valido");
            return false;
        }
        return true;
    }

    private void constructorTest() {
            cc = new ControladorCelda();
            constructor = 1;
            System.out.println("Se ha construido correctamente el ControladorCelda");
    }

    private void asignaCeldaPosicionTest(){
        Documento d = Documento.getDocumento();
        d.inicializaDocumentoDefault("Doc1");
        cc.asignaCeldaPosicion(String.valueOf(1),palabras[1],palabras[2]);
        constructor = 2;
        System.out.println("Se ha asignado la celda " + palabras[1] + " " + palabras[2] + " de la hoja " +
                1 + " correctamente.");

    }

    private void escribirContenidoFTruncamientoTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFAproximarValor(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFValorAbsolutoTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFConversionDecBinTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFConversionBinDecTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero binario valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFConversionHexDecTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero hexadecimal valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFConversionDecHexTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFConversionHexBinTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero hexadecimal valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFConversionBinHexTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "un numero binario valido");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFMesTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "una fecha valida");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFAñoTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "una fecha valida");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFDiaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "una fecha valida");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFNombreDiaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "una fecha valida");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFLongitudTest(){
        cc.escribirContenido(palabras[1]);
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
        System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());

    }

    private void escribirContenidoFContarLetraTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "una letra contenida en la palabra");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFReemplazarCaracterTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que el caracter " +
                "introducido para substituir existe en la palabra");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFReemplazarPalabraTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "correctamente posicion y longitud, estas no deberian ser negativas o mayores que el texto ");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFMediaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFMedianaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFVarianzaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFCovarianzaTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoFDesvStdTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }
    private void escribirContenidoFCoefPearsonTest(){
        cc.escribirContenido(palabras[1]);
        if(cc.getCeldaRef().getValor() == "#ERROR") System.out.println("Ha habido un error: Revisa que has introducido " +
                "numeros correctos");
        else{
            System.out.println("Se ha escrito correctamente el contenido de la funcion");
            System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
            System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());
        }
    }

    private void escribirContenidoReferenciaTest(){
        System.out.println("Dado que esta funcion precisa de celdas, creamos una celda con valor 5.");
        System.out.println("Esta celda sera la referenciada.");

        String arg[] = {"5"};
        CeldaStub c = new CeldaStub(new PosicionStub(1, 2));

        //tratamos las dependencias con mock
        when(traductor.traduceCelda(isA(String.class), isA(Integer.class))).thenReturn(c);
        doNothing().when(celda).addReferenciante(isA(Celda.class));
        when(traductor.getArgumentosFuncion1aria(isA(String.class), isA(Integer.class))).thenReturn(arg);

        cc.escribirContenido(palabras[1]);

        System.out.println("Se ha referenciado correctamente el contenido de la celda");
        System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
        System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());

    }

    private void escribirContenidoNoFuncTest(){
        cc.escribirContenido(palabras[1]);

        System.out.println("Se ha escrito correctamente el contenido");
        System.out.println("El contenido de la celda ahora es " + cc.getCeldaRef().getContenido());
        System.out.println("El valor de la celda ahora es: " + cc.getCeldaRef().getValor());

    }

    private void truncarValorTest(){
        try {
            String s = cc.truncarValor(Double.parseDouble(palabras[1]), Integer.parseInt(palabras[2]));
            if (cc.getCeldaRef().getValor() == "#ERROR")
                System.out.println("Ha habido un error: Revisa que has introducido " +
                        "numeros correctos, recuerda no puedes truncar mas numeros de los que tienes");
            else {
                System.out.println("Se ha truncado correctamente el valor");
                System.out.println("El valor de la celda ahora es: " + s);
            }
        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }

    private void valorAbsolutoTest(){
        try {
            String s = cc.valorAbsoluto(Double.parseDouble(palabras[1]));
                System.out.println("Se ha procesado el valor absoluto correctamente");
                System.out.println("El valor es: " + s);

        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }

    private void aproximarValorTest(){
        try {
            String s = cc.aproximarValor(Double.parseDouble(palabras[1]));
            System.out.println("Se ha aproximado el valor correctamente");
            System.out.println("El valor ahora es: " + s);

        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }
    private void convertirValorDecBinTest(){
        try {
            String s = cc.convertirValorDB(Integer.parseInt((palabras[1])));
            System.out.println("Se ha convertido el valor a binario correctamente");
            System.out.println("El valor ahora es: " + s);

        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }
    private void convertirValorBinDecTest(){
        try {
            String s = cc.convertirValorBD(Integer.parseInt((palabras[1])));
            System.out.println("Se ha convertido el valor a decimal correctamente");
            System.out.println("El valor ahora es: " + s);

        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }

    private void convertirValorDecHexTest(){
        try {
           String s = cc.convertirValorDH(Integer.parseInt((palabras[1])));
            System.out.println("Se ha convertido el valor a hexadecimal correctamente");
            System.out.println("El valor ahora es: " + s);

        } catch(NumberFormatException e){
            System.out.println("El valor pasado como numero no es valido");
        }
    }

    private void convertirValorHexDecTest(){
            String s = cc.convertirValorHD(palabras[1]);
            System.out.println("Se ha convertido el valor a binario correctamente");
            System.out.println("El valor ahora es: " + s);

    }

    private void convertirValorBinHexTest(){
            String s = cc.convertirValorHD(palabras[1]);
            System.out.println("Se ha convertido el valor a Hexadecimal correctamente");
            System.out.println("El valor ahora es: " + s);
    }

    private void convertirValorHexBinTest(){
            String s = cc.convertirValorHD(palabras[1]);
            System.out.println("Se ha convertido el valor a binario correctamente");
            System.out.println("El valor ahora es: " + s);

    }

    private void obtenerMesTest(){
            String s = cc.obtenerMes(palabras[1]);
            System.out.println("Se ha obtenido el mes de la fecha introducida correctamente");
            System.out.println("El mes es el " + s);
    }

    private void obtenerAñoTest(){
        String s = cc.obtenerAño(palabras[1]);
        System.out.println("Se ha obtenido el año de la fecha introducida correctamente");
        System.out.println("El año es el " + s);
    }

    private void obtenerDiaTest(){
        String s = cc.obtenerDia(palabras[1]);
        System.out.println("Se ha obtenido el dia de la fecha introducida correctamente");
        System.out.println("El dia es el " + s);
    }

    private void obtenerNombreDiaTest(){
        String s = cc.obtenerNombreDia(palabras[1]);
        System.out.println("Se ha obtenido el dia de la fecha introducida correctamente");
        System.out.println("El dia es: " + s);
    }

    private void longitudPalabraTest(){
        String s = cc.longitudPalabra(palabras[1]);
        System.out.println("Se ha obtenido la longitud de la fecha introducida correctamente");
        System.out.println("La longitud es: " + s);
    }
    private void contarLetraTest(){
        String s = cc.contarLetra(palabras[1], palabras[2]);
        System.out.println("Se ha obtenido el numero de veces que aparece la letra " + palabras[1] + " en la palabra" +
                " introducida correctamente");
        System.out.println("El numero de veces es: " + s);
    }

    private void reemplazarPalabraTest(){
        String s = cc.reemplazarPalabra(palabras[1], Integer.parseInt(palabras[2]),
                Integer.parseInt(palabras[3]), palabras[4]);
        if (s == "-1") System.out.println("Alguno de los parametros introducidos no es correcto, recuerda introducir " +
                "tanto una posicion valida como una longitud valida");
        System.out.println("Se ha remplazado la palabra correctamente");
        System.out.println("La palabra ahora es: " + s);
    }

    private void reemplazarCaracterTest(){
        String s = cc.reemplazarCaracter(palabras[1], palabras[2], palabras[3]);
        if (s == "-1") System.out.println("El caracter " + palabras[2] + " no existe en la palabra");
        System.out.println("Se ha remplazado el caracter correctamente");
        System.out.println("La palabra ahora es: " + s);
    }







    //Stub de Posicion que sustituye la clase Posicion asi como los metodos necesarios para el test
    private static class PosicionStub extends Posicion {

        public PosicionStub(int _fila, int _columna) {
            super(_fila, _columna);
        }
    }

    //Stub de Celda que sustituye la clase Celda asi como los metodos necesarios para el test
    private static class CeldaStub extends Celda {
        private PosicionStub pos;
        String contenido;
        String valor;
        ArrayList<Celda> refs;

        public CeldaStub(PosicionStub _pos) {
            super(_pos);
            pos = _pos;
            contenido = "";
            valor = "";

        }

        @Override
        public PosicionStub getPosicion() {
            return pos;
        }


        @Override
        public void setReferenciantes(ArrayList<Celda> _refs){
            refs = new ArrayList<>();

        }

        @Override
        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    private static class DocumentoStub{
        private String nombre;
        private int numHojas;
        private HashMap<Integer, Hoja> hojasContenidas = new HashMap<Integer, Hoja>();

        public DocumentoStub(){
            this.nombre = "Doc1";

        }

    }

    private static class HojaStub extends Hoja{
        private int id;
        HojaStub(){
            id = 1;

        }

        @Override
        public int getId() {
            return id;
        }
    }





}

