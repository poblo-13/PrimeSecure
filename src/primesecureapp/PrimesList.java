package primesecureapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class PrimesList extends ArrayList<Integer> {
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false; 
            }
        }
        return true;
    }

    @Override
    public synchronized boolean add(Integer element) {
        if (element == null) {
            throw new IllegalArgumentException("No se puede añadir un valor nulo a la lista.");
        }
        if (!isPrime(element)) {
            throw new IllegalArgumentException("El número " + element + " no es primo y no puede ser añadido a la lista.");
        }
        return super.add(element);
    }

    @Override
    public synchronized void add(int index, Integer element) {
        if (element == null) {
            throw new IllegalArgumentException("No se puede añadir un valor nulo a la lista.");
        }
        if (!isPrime(element)) {
            throw new IllegalArgumentException("El número " + element + " no es primo y no puede ser añadido a la lista.");
        }
        super.add(index, element);
    }

    @Override
    public synchronized boolean addAll(Collection<? extends Integer> c) {
        for (Integer element : c) {
            if (element == null || !isPrime(element)) {
                throw new IllegalArgumentException("La colección contiene números no primos o nulos. No se puede añadir.");
            }
        }
        return super.addAll(c);
    }

    @Override
    public synchronized boolean addAll(int index, Collection<? extends Integer> c) {
        for (Integer element : c) {
            if (element == null || !isPrime(element)) {
                throw new IllegalArgumentException("La colección contiene números no primos o nulos. No se puede añadir.");
            }
        }
        return super.addAll(index, c);
    }

    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public synchronized Integer remove(int index) {
        return super.remove(index);
    }

    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    public synchronized int getPrimesCount() {
        return this.size();
    }

    @Override
    public synchronized int size() {
        return super.size();
    }

    public synchronized void guardarEnArchivo(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Integer primo : this) {
                writer.println(primo.toString()); 
            }
            System.out.println("Lista de primos guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage()); 
        }
    }
}