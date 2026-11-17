package examen.esperarTareas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Ejercicio 2 — Esperar a 3 tareas
Situación: Una tienda consulta tres servicios independientes para obtener los precios de tres productos.
- Tarea 1: tarda 2 segundos y devuelve 100.
- Tarea 2: tarda 3 segundos y devuelve 200.
- Tarea 3: tarda 5 segundos y devuelve 300.
Tu objetivo:
1. Crea tres CompletableFuture<Double>.
2. Usa un ExecutorService con un pool de 3 hilos.
3. Lanza las tres tareas antes de esperar.
4. Espera a que terminen todas.
5. Modifica cada precio aplicando un descuento del 10 %.
6. Calcula el precio total con los tres precios modificados.
7. Imprime los tres precios y el total.
* */
public class Pract2 {
    static void main() {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        CompletableFuture<Double> tarea1 =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    return 100 * 0.90;
                }, executor);

        CompletableFuture<Double> tarea2 =
                CompletableFuture.supplyAsync(() -> {
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    return 200 * 0.90;
                },executor);

        CompletableFuture<Double> tarea3 =
                CompletableFuture.supplyAsync(() -> {
                    try{
                        Thread.sleep(5000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    return 300 * 0.90;
                },executor);

        CompletableFuture<Double> suma =
                tarea1.thenCombine(tarea2, (p1, p2) ->
                        p1+ p2 )
                        .thenCombine(tarea3, (totalsuma, p3) -> totalsuma + p3);

        CompletableFuture.allOf(tarea1, tarea2, tarea3).join();

        System.out.println(" tarea1:" + tarea1.join());
        System.out.println(" tarea2:" + tarea2.join());
        System.out.println(" tarea3:" + tarea3.join());
        System.out.println(" suma: " + suma.join()) ;
        executor.shutdown();
    }
}
