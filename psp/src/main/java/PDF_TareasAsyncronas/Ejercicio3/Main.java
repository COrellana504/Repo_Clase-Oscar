package PDF_TareasAsyncronas.Ejercicio3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

     static void main(String[] args)
            throws Exception {

        long inicio = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<Integer> tarea1 = () -> {
            Thread.sleep(2000);
            return 10;
        };

        Callable<Integer> tarea2 = () -> {
            Thread.sleep(1000);
            return 20;
        };

        Callable<Integer> tarea3 = () -> {
            Thread.sleep(3000);
            return 30;
        };

        Future<Integer> future1 =
                executor.submit(tarea1);

        Future<Integer> future2 =
                executor.submit(tarea2);

        Future<Integer> future3 =
                executor.submit(tarea3);

        int total = future1.get()
                  + future2.get() + future3.get();

        long fin = System.currentTimeMillis();

        System.out.println("Resultado total: " + total);

        System.out.println("Tiempo: " + (fin - inicio) + " ms"
        );

        executor.shutdown();
    }
}