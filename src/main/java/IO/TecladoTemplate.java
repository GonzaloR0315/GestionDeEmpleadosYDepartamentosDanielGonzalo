package IO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TecladoTemplate {
   private static Scanner entrada = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
        int resultado = 0;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            if (entrada.hasNextInt()) {
                resultado = entrada.nextInt();
                correcto = true;
            }else {
                System.out.println("!!!Eso no es un numero entero¡¡¡");

            }
            entrada.nextLine();
        } while (!correcto);
        return resultado;
    }
    // ----------------------------------------------------------------------------------

    public static String leerString(String mensaje) {
        System.out.print(mensaje);
        String resultado = entrada.next();
        entrada.nextLine();
        return resultado;
    }
    // ----------------------------------------------------------------------------------

    public static boolean leerBoolean(String mensaje) {
        boolean resultado = false;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            String cadena = entrada.nextLine();
            if (Character.toUpperCase(cadena.charAt(0)) == 'S') {
                resultado = true;
                correcto = true;
            } else if (Character.toUpperCase(cadena.charAt(0)) == 'N') {
                resultado = false;
                correcto = true;
            }
        } while (!correcto);
        return resultado;
    }
    // ----------------------------------------------------------------------------------
    public static LocalDate leerLocalDate(String mensaje) {
        LocalDate resultado = null;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            String cadena = entrada.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                // Parse the string to obtain a LocalDate
                resultado = LocalDate.parse(cadena, formatter);
            } catch (Exception e) {
                e.getStackTrace();
            }

        } while (!correcto);
        return resultado;
    }
    // ----------------------------------------------------------------------------------

    public static Double leerDouble(String mensaje) {
        Double resultado = (double) -1;
        boolean correcto = false;
        do {
            System.out.print(mensaje);
            if (entrada.hasNextDouble()) {

                resultado = entrada.nextDouble();
                correcto = true;
            } else {
                System.out.println("!!!Eso no es un numero decimal¡¡¡");
            }
            entrada.nextLine();
        } while (!correcto);
        return resultado;
    }
}
