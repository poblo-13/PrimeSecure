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
        } catch (IllegalArgumentException e) {
            System.out.println("Hilo " + Thread.currentThread().getName() + ": No se añadió " + number + " -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Hilo " + Thread.currentThread().getName() + ": Error inesperado al procesar " + number + " -> " + e.getMessage());
        }
    }
}