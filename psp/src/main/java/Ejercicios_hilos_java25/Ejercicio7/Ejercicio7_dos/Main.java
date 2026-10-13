package Ejercicios_hilos_java25.Ejercicio7.Ejercicio7_dos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args)
            throws Exception {

        List<Callable<String>> tareas =
                new ArrayList<>();

        tareas.add(
                new LlamadaAPI("API Usuarios")
        );

        tareas.add(
                new LlamadaAPI("API Vuelos")
        );

        tareas.add(
                new LlamadaAPI("API Hoteles")
        );

        tareas.add(
                new LlamadaAPI("API Clima")
        );

        tareas.add(
                new LlamadaAPI("API Pagos")
        );

        try (ExecutorService executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            List<Future<String>> resultados =
                    executor.invokeAll(tareas);

            for (Future<String> future : resultados) {

                System.out.println(
                        future.get()
                );
            }
        }
    }
}
