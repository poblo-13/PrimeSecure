package primesecureapp;

public class PrimeTask implements Runnable {
    private final int numero;
    private final PrimesList lista;

    public PrimeTask(int numero, PrimesList lista) {
        this.numero = numero;
        this.lista = lista;
    }

    @Override
    public void run() {
        try {
            lista.add(numero);
            System.out.println("Número agregado por hilo: " + numero);
        } catch (IllegalArgumentException e) {
            System.out.println("Hilo rechazó número " + numero + ": " + e.getMessage());
        }
    }
}
