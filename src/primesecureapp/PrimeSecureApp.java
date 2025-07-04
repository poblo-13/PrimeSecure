package primesecureapp;

import java.util.Scanner;

public class PrimeSecureApp {
    public static void main(String[] args) {
        PrimesList lista = new PrimesList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese números para agregar a la lista de primos.");
        System.out.println("Puede ingresar varios números separados por espacios.");
        System.out.println("Escriba 'fin' para terminar el programa.");

        while (true) {
            System.out.print("\nIngrese números (o 'fin'): ");
            String linea = scanner.nextLine();

            if (linea.equalsIgnoreCase("fin")) {
                break;
            }

            String[] tokens = linea.split("\\f+");

            for (String token : tokens) {
                try {
                    int num = Integer.parseInt(token);
                    Thread hilo = new Thread(new PrimeTask(num, lista));
                    hilo.start();
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido ignorado: " + token);
                }
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n== Resultados Finales ==");
        System.out.println("Lista de primos: " + lista);
        System.out.println("Cantidad total de primos: " + lista.getPrimesCount());

        lista.guardarEnArchivo("primos.txt");

        System.out.println("Programa terminado. Lista guardada en 'primos.txt'.");
        scanner.close();
    }
}
