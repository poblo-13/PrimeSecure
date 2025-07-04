package primesecureapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

/**
 * La clase PrimesList extiende ArrayList<Integer> y está diseñada para
 * almacenar y gestionar exclusivamente números primos. Sobrescribe los
 * métodos de adición y eliminación para asegurar la integridad de los datos
 * como números primos.
 * Esta versión incluye sincronización para ser segura para hilos.
 */
public class PrimesList extends ArrayList<Integer> {

    /**
     * Verifica si un número dado es primo.
     * Un número primo es un número natural mayor que 1 que no tiene divisores
     * positivos distintos de 1 y de sí mismo.
     *
     * @param number El entero a verificar.
     * @return true si el número es primo, false en caso contrario.
     */
    public boolean isPrime(int number) {
        // Los números menores o iguales a 1 (incluido 0) no son primos.
        if (number <= 1) {
            return false;
        }
        // El 2 es el único número primo par.
        if (number == 2) {
            return true;
        }
        // Si es par y mayor que 2, no es primo.
        if (number % 2 == 0) {
            return false;
        }

        // Solo necesitamos verificar divisores impares hasta la raíz cuadrada del número.
        // Si un número tiene un divisor mayor que su raíz cuadrada,
        // también tiene uno menor.
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false; // Si tiene un divisor, no es primo.
            }
        }
        return true; // Si no se encontraron divisores, es primo.
    }

    /**
     * Sobrescribe el método add para asegurar que solo se añadan números primos a la lista.
     * Sincronizado para ser seguro para hilos.
     *
     * @param element El número entero a añadir.
     * @return true si el número primo fue añadido exitosamente.
     * @throws IllegalArgumentException si el número no es primo o es nulo.
     */
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

    /**
     * Sobrescribe el método add para asegurar que solo se añada un número primo en un índice específico.
     * Sincronizado para ser seguro para hilos.
     *
     * @param index El índice en el que se insertará el elemento.
     * @param element El número entero a añadir.
     * @throws IllegalArgumentException si el número no es primo o es nulo.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango (index < 0 || index > size()).
     */
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

    /**
     * Sobrescribe el método addAll para asegurar que solo se añadan colecciones de números primos a la lista.
     * Sincronizado para ser seguro para hilos.
     *
     * @param c La colección de números enteros a añadir.
     * @return true si todos los números primos fueron añadidos exitosamente.
     * @throws IllegalArgumentException si algún número en la colección no es primo o es nulo.
     */
    @Override
    public synchronized boolean addAll(Collection<? extends Integer> c) {
        // Primero, valida todos los elementos de la colección antes de intentar añadirlos.
        for (Integer element : c) {
            if (element == null || !isPrime(element)) {
                throw new IllegalArgumentException("La colección contiene números no primos o nulos. No se puede añadir.");
            }
        }
        // Si todos son válidos, entonces se añaden.
        return super.addAll(c);
    }

    /**
     * Sobrescribe el método addAll para asegurar que solo se añadan colecciones de números primos
     * en un índice específico.
     * Sincronizado para ser seguro para hilos.
     *
     * @param index El índice en el que se insertarán los elementos.
     * @param c La colección de números enteros a añadir.
     * @return true si todos los números primos fueron añadidos exitosamente.
     * @throws IllegalArgumentException si algún número en la colección no es primo o es nulo.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango (index < 0 || index > size()).
     */
    @Override
    public synchronized boolean addAll(int index, Collection<? extends Integer> c) {
        // Primero, valida todos los elementos de la colección antes de intentar añadirlos.
        for (Integer element : c) {
            if (element == null || !isPrime(element)) {
                throw new IllegalArgumentException("La colección contiene números no primos o nulos. No se puede añadir.");
            }
        }
        // Si todos son válidos, entonces se añaden.
        return super.addAll(index, c);
    }

    /**
     * Sobrescribe el método remove para controlar la eliminación de elementos por objeto.
     * Mantiene la lógica original de ArrayList. Sincronizado para ser seguro para hilos.
     * No se requiere validación de 'primo' al remover, ya que si el elemento está en la lista,
     * ya fue verificado como primo al ser añadido.
     *
     * @param o El objeto a remover de la lista.
     * @return true si el elemento fue removido exitosamente.
     */
    @Override
    public synchronized boolean remove(Object o) {
        return super.remove(o);
    }

    /**
     * Sobrescribe el método remove para controlar la eliminación de elementos por índice.
     * Mantiene la lógica original de ArrayList. Sincronizado para ser seguro para hilos.
     *
     * @param index El índice del elemento a remover.
     * @return El elemento que fue removido de la lista.
     * @throws IndexOutOfBoundsException si el índice está fuera de rango (index < 0 || index >= size()).
     */
    @Override
    public synchronized Integer remove(int index) {
        return super.remove(index);
    }

    /**
     * Sobrescribe el método removeAll para controlar la eliminación de una colección de elementos.
     * Mantiene la lógica original de ArrayList. Sincronizado para ser seguro para hilos.
     *
     * @param c La colección de elementos a remover de esta lista.
     * @return true si esta lista cambió como resultado de la llamada.
     */
    @Override
    public synchronized boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    /**
     * Devuelve la cantidad de números primos actualmente en la lista.
     * Sincronizado para ser seguro para hilos.
     *
     * @return La cantidad de números primos en la lista.
     */
    public synchronized int getPrimesCount() {
        return this.size();
    }

    /**
     * Devuelve el tamaño actual de la lista.
     * Sincronizado para ser seguro para hilos.
     * @return El número de elementos en esta lista.
     */
    @Override
    public synchronized int size() {
        return super.size();
    }

    /**
     * Guarda la lista actual de números primos en un archivo de texto.
     * Cada primo se escribe en una nueva línea.
     * Sincronizado para evitar problemas de concurrencia al acceder a la lista.
     *
     * @param nombreArchivo El nombre del archivo donde se guardará la lista.
     */
    public synchronized void guardarEnArchivo(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Integer primo : this) {
                writer.println(primo.toString()); // Usa println para añadir nueva línea automáticamente.
            }
            System.out.println("Lista de primos guardada en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage()); // Usa System.err para mensajes de error.
        }
    }
}