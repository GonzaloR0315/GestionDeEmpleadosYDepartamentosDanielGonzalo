package IO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Teclado extends TecladoTemplate{


    // ----------------------------------------------------------------------------------


    public static int leerEntero(String mensaje, int min, int max) {
        int resultado = -1;
        boolean correcto = false;
        do {
            System.out.print(mensaje);

                int test = TecladoTemplate.leerEntero(mensaje);
                if (test > min && max <= test) {
                    resultado = test;
                } else {
                    System.out.println("Numero fuera de rango" + min + "-" + max);
                }
                correcto = true;

        } while (!correcto);
        return resultado;
    }







    // ----------------------------------------------------------------------------------

    public static Double leerDouble(String mensaje, Double min, Double max) {
        Double resultado = (double) -1;
        boolean correcto = false;
        do {


                Double test =TecladoTemplate.leerDouble(mensaje);
                if (test > min && max <= test) {
                    resultado = test;
                } else {
                    System.out.println("Numero fuera de rango" + min + "-" + max);
                }
                correcto = true;

        } while (!correcto);
        return resultado;
    }

}