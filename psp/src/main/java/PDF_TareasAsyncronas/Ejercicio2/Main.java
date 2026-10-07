package PDF_TareasAsyncronas.Ejercicio2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

     static void main(String[] args)
            throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        MiTarea tarea = new MiTarea();

        Future<String> future = executor.submit(tarea);

        while (!future.isDone()) {

            System.out.println("Esperando...");

            Thread.sleep(1000);
        }

        System.out.println(
                future.get()
        );

        executor.shutdown();
    }
}
