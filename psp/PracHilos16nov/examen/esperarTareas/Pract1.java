package examen.esperarTareas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pract1 {
    static void main() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CompletableFuture<String> temperatura =
                CompletableFuture.supplyAsync(() -> {
                   try{
                       Thread.sleep(2000);
                   }catch(InterruptedException e){
                       e.printStackTrace();
                   }
                   return "25 ºC";
                }, executor);

        CompletableFuture<String> humedad =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    return "60 %";
                }, executor);
        CompletableFuture.allOf(humedad, temperatura);

        System.out.println("Humedad: " + humedad.join());
        System.out.println("Temperatura: " + temperatura.join());
        executor.shutdown();
    }



}
