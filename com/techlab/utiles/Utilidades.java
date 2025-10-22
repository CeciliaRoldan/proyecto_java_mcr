package com.techlab.utiles;

import java.util.Scanner;

public class Utilidades {
    private static final Scanner scanner = new Scanner(System.in);

    public static String capitalizarTexto(String nombre) {
        nombre = nombre.trim().toLowerCase();
        String primeraLetra = nombre.substring(0, 1).toUpperCase();
        String resto = nombre.substring(1);
        return primeraLetra + resto;
    }
    
    public static int inputInt(String mensaje) {
        int input = 0;
        boolean valido = false;
        
        while (!valido) {
            System.out.println(mensaje);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                valido = true;
            } else {
                System.out.println("Ingrese un numero valido.");
                scanner.next(); 
            }
        }
        
        return input;
    }
    
    public static double inputDouble(String mensaje) {
        double input = 0.0;
        boolean valido = false;

        while (!valido) {
            System.out.println(mensaje);
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                scanner.nextLine();
                valido = true;
            } else {
                System.out.println("Ingrese un numero decimal valido (Usar coma para el decimal).");
                scanner.next(); 
            }
        }

        return input;
    }
    
    public static String inputString(String mensaje) {
        String input = "";

        while (input.trim().isEmpty()) {
            System.out.println(mensaje);
            input = scanner.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Ingrese un texto valido.");
            }
        }

        return input;
    }
}
