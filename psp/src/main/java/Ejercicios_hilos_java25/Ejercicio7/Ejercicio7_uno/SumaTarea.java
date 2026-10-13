package Ejercicios_hilos_java25.Ejercicio7.Ejercicio7_uno;

import java.util.concurrent.Callable;

public class SumaTarea implements Callable<Long> {

    private int[] array;
    private int inicio;
    private int fin;

    public SumaTarea(
            int[] array,
            int inicio,
            int fin) {

        this.array = array;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public Long call() {

        long suma = 0;

        for (int i = inicio; i < fin; i++) {

            suma += array[i];
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " suma parcial: "
                        + suma
        );

        return suma;
    }
}
