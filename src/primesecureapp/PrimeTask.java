package primesecureapp;

public class PrimeTask implements Runnable {
    private final int number;
    private final PrimesList primesList;

    public PrimeTask(int number, PrimesList primesList) {
        this.number = number;
        this.primesList = primesList;
    }

    @Override
    public void run() {
        try {
            primesList.add(number);
            // La línea de impresión de éxito está comentada aquí
            // System.out.println("Hilo " + Thread.currentThread().getName() + ": Añadido Código Primo -> " + number); 
        } catch (IllegalArgumentException e) {
            // Este mensaje se mantiene para notificar rechazos
            System.out.println("Hilo " + Thread.currentThread().getName() + ": No se añadió " + number + " -> " + e.getMessage());
        } catch (Exception e) {
            // Este mensaje también se mantiene para errores inesperados
            System.err.println("Hilo " + Thread.currentThread().getName() + ": Error inesperado al procesar " + number + " -> " + e.getMessage());
        }
    }
}