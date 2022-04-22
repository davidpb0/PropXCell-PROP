package drivers.components.ControladorCelda;

import main.Domain.DomainControllers.ControladorCelda;


import java.io.BufferedReader;

public class DriverControladorCelda {
    private ControladorCelda cc;
    private static BufferedReader br;

    private static void opciones() {
        System.out.println("A continuacio hay un lista de les funciones que puede ejecutar la classe ControladorCelda");
        System.out.println("\t1: ControladorCelda()");
        System.out.println("\t2: SeleccionarCelda()");
        System.out.println("\t3: DefinirFuncionTruncamiento()");
        System.out.println("\t4: DefinirFuncionAproximarValor()");
        System.out.println("\t5: DefinirFuncionValorAbsoluto()");

        System.out.print("Escoge una opcion: ");

    }

}
