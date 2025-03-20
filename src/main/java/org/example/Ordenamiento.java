package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.time.Duration;
import java.time.Instant;

public class Ordenamiento {

    private int arreglo[];
    private final int size = 10000;

    public Ordenamiento() {
        arreglo = new int[size];
        Cargar();
    }

    private void Cargar() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arreglo[i] = random.nextInt(10000);
        }
    }

    public void Muestra() {
        System.out.println("Numeros desordenados:");
        Arrays.stream(arreglo).forEach(System.out::println);

        var inicio = Instant.now();
        System.out.println(inicio);
        System.out.println("Numeros usando Bubble Sort");
        bubbleSort();
        Arrays.stream(arreglo).forEach((iokc) -> System.out.println(iokc));
        System.out.println(inicio);

        System.out.println("Numeros usando InsertionSort");
        InsertionSort();
        Arrays.stream(arreglo).forEach((iokc) -> System.out.println(iokc));

        System.out.println("Numeros usando ShellSort");
        ShellSort();
        Arrays.stream(arreglo).forEach((iokc) -> System.out.println(iokc));

        System.out.println("Busqueda binaria: Buscamos al numero 925");

        int busquedaBinaria = BusquedaBinaria(925);
        if(busquedaBinaria != -1){
            System.out.println("El elemento se encuentra en la posicion: "+ busquedaBinaria);
        }else{
            System.out.println("El elemento no fue encontrado");
        }
        System.out.println("--------------------------------------------");
        System.out.println("Busqueda Lineal: Buscamos al numero 956");

        int busquedaLineal = BusquedaLineal(956);
        if(busquedaLineal != -1){
            System.out.println("El elemento se encuentra en la posicion: "+ busquedaLineal);
        }else{
            System.out.println("El elemento no fue encontrado");
        }



    }

    private int bubbleSort() {
        long startTime = new Date().getTime();
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < size - 1; i++) {
            swapped = false;
            for (j = 0; j < size - i - 1; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        long endTime = new Date().getTime();

        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
        return (int) timeElapsed;

    }

    private int InsertionSort() {
        long startTime = Instant.now().toEpochMilli();

        for (int i = 1; i < size; ++i) {
            int key = arreglo[i];
            int j = i - 1;

            while (j >= 0 && arreglo[j] > key) {
                arreglo[j + 1] = arreglo[j];
                j = j - 1;
            }
            arreglo[j + 1] = key;
        }
        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
        return (int) timeElapsed;
    }

    private int ShellSort() {
        long startTime = Instant.now().toEpochMilli();
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i += 1) {
                int temp = arreglo[i];
                int j;
                for (j = i; j >= gap && arreglo[j - gap] > temp; j -= gap)
                    arreglo[j] = arreglo[j - gap];
                arreglo[j] = temp;
            }
        }
        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
        return (int)timeElapsed;
    }

    private int BusquedaBinaria(int valor){
        int li = 0;
        int ls = size-1;
        while (li <= ls) {
            int lm = (ls - li) / 2 + li;
            if (arreglo[lm] < valor) {
                li = lm + 1;
            } else if (arreglo[lm] > valor) {
                ls = lm - 1;
            } else {
                return lm;
            }
        }
        return -1;
    }

    private int BusquedaLineal(int valor){
        int pos = -1;
        for (int i = 0; i < size; i++) {
            if (valor == arreglo[i]) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    private void MergeSort(int l, int m, int r){
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arreglo[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arreglo[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                i++;
            }
            else {
                arreglo[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arreglo[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arreglo[k] = R[j];
            j++;
            k++;
        }
    }
}
