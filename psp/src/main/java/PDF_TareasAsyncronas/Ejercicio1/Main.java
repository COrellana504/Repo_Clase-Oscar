package PDF_TareasAsyncronas.Ejercicio1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

     static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        MiTarea tarea = new MiTarea();

        Future<Integer> future = executor.submit(tarea);

        System.out.println("Tarea enviada");

        System.out.println("Esperando resultado...");

        try {

            Integer resultado = future.get();

            System.out.println("Resultado: " + resultado);

        } catch (Exception e) {

            e.printStackTrace();
        }

        executor.shutdown();
    }
}
