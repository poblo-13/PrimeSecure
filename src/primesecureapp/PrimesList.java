package primesecureapp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrimesList extends ArrayList<Integer> {

    public boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @Override
    public boolean add(Integer num) {
        if (isPrime(num)) {
            return super.add(num);
        } else {
            throw new IllegalArgumentException("Número no primo: " + num);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Integer && isPrime((Integer) o)) {
            return super.remove(o);
        } else {
            throw new IllegalArgumentException("Solo se pueden eliminar números primos.");
        }
    }

    public int getPrimesCount() {
        return this.size();
    }

    public void guardarEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Integer primo : this) {
                writer.write(primo.toString());
                writer.newLine();
            }
            System.out.println("Lista de primos guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
