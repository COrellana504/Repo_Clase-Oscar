package Ejercicios_hilos_java25.Ejercicio7.Ejercicio7_uno;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainSumaTarea {

    public static void main(String[] args)
            throws Exception {

        int TAM = 10_000_000;

        int[] numeros = new int[TAM];

        for (int i = 0; i < TAM; i++) {

            numeros[i] = 1;
        }

        ExecutorService pool =
                Executors.newFixedThreadPool(4);

        List<Future<Long>> resultados =
                new ArrayList<>();

        int trozo = TAM / 4;

        for (int i = 0; i < 4; i++) {

            int inicio = i * trozo;

            int fin;

            if (i == 3) {

                fin = TAM;

            } else {

                fin = inicio + trozo;
            }

            SumaTarea tarea =
                    new SumaTarea(
                            numeros,
                            inicio,
                            fin
                    );

            Future<Long> future =
                    pool.submit(tarea);

            resultados.add(future);
        }

        long sumaTotal = 0;

        for (Future<Long> future : resultados) {

            sumaTotal += future.get();
        }

        System.out.println(
                "Suma total: "
                        + sumaTotal
        );

        pool.shutdown();
    }
}
