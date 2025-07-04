package primesecureapp;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PrimeSecureApp {
    public static void main(String[] args) {
        PrimesList lista = new PrimesList();
        Scanner scanner = new Scanner(System.in);

        // Se crea un pool de hilos para gestionar las tareas de manera eficiente.
        // Aquí se define un pool con 4 hilos, lo cual es una buena práctica para tareas concurrentes.
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("--------------------------------------------------");
        System.out.println("  Bienvenido al Gestor de Códigos Primos PrimeSecure ");
        System.out.println("--------------------------------------------------");
        System.out.println("Ingrese números para agregar a la base de datos de primos.");
        System.out.println("Puede ingresar varios números separados por espacios (ej. '2 3 5 7').");
        System.out.println("Escriba 'fin' para terminar el programa.");

        while (true) {
            System.out.print("\nIngrese números (o 'fin'): ");
            String linea = scanner.nextLine();

            if (linea.equalsIgnoreCase("fin")) {
                break;
            }

            // Divide la línea de entrada por uno o más espacios en blanco.
            String[] tokens = linea.split("\\s+");

            for (String token : tokens) {
                if (token.isEmpty()) { // Ignora cualquier token vacío que pueda resultar de múltiples espacios.
                    continue;
                }
                try {
                    int num = Integer.parseInt(token);
                    // Envía la tarea de procesamiento del número a un hilo en el pool.
                    executor.submit(new PrimeTask(num, lista));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido ignorado: '" + token + "' (no es un número entero).");
                }
            }
        }

        // Se cierra el ExecutorService. Esto indica que no se aceptarán nuevas tareas.
        executor.shutdown();

        System.out.println("\n--------------------------------------------------");
        System.out.println("  Finalizando programa. Esperando que los hilos terminen... ");
        System.out.println("--------------------------------------------------");
        try {
            // Se espera hasta que todas las tareas en el pool hayan finalizado,
            // con un tiempo de espera máximo de 60 segundos para evitar bloqueos indefinidos.
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("Advertencia: No todas las tareas terminaron en el tiempo especificado. Forzando cierre...");
                executor.shutdownNow(); // Si el tiempo expira, intenta detener las tareas en ejecución.
            }
        } catch (InterruptedException e) {
            System.err.println("El programa fue interrumpido mientras esperaba a que los hilos terminaran.");
            executor.shutdownNow(); // Interrumpe todos los hilos si el hilo principal es interrumpido.
            Thread.currentThread().interrupt(); // Restablece el estado de interrupción del hilo principal.
        }

        System.out.println("\n============ Resultados Finales ============");
        System.out.println("Lista final de Códigos Primos: " + lista);
        System.out.println("Cantidad total de Códigos Primos: " + lista.getPrimesCount());

        lista.guardarEnArchivo("codigos_primos.txt"); // Guarda la lista en un archivo.

        System.out.println("\nPrograma terminado. Lista guardada en 'codigos_primos.txt'.");
        scanner.close();
    }
}