package examen.esperarTareas;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* Ejercicio 3 — Esperar a 5 tareas
Reto final
Situación: Un videojuego necesita consultar cinco estadísticas de un jugador.
Tarea	Tiempo	Resultado
Vida	2 segundos	100
Ataque	3 segundos	50
Defensa	4 segundos	30
Velocidad	5 segundos	20
Energía	2 segundos	80

Tu objetivo:
1. Crea cinco CompletableFuture<Integer>.
2. Utiliza un ExecutorService con 5 hilos.
3. Lanza las cinco tareas antes de hacer ningún join().
4. Espera a que terminen todas con allOf.
5. Modifica los resultados:
   - Vida: suma 50.
   - Ataque: multiplica por 2.
   - Defensa: suma 20.
   - Velocidad: multiplica por 3.
   - Energía: resta 10.
6. Calcula la suma de todas las estadísticas modificadas.
7. Muestra todoo en pantalla.
*           */
public class Pract3 {
    static void main() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<Integer> vida =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    return 100 + 50;
                }, executor);

        CompletableFuture<Integer> ataque =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    return 50 * 2;
                }, executor);

        CompletableFuture<Integer> defensa =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    return 30 + 20;
                }, executor);

        CompletableFuture<Integer> velocidad =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    return 20 * 3;
                }, executor);

        CompletableFuture<Integer> energia =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                    return 80 - 10;
                }, executor);


        CompletableFuture.allOf(
                vida, ataque, defensa, velocidad, energia
        ).join();


        int total = vida.join()
                + ataque.join()
                + defensa.join()
                + velocidad.join()
                + energia.join();


        System.out.println("=== ESTADÍSTICAS DEL JUGADOR ===");
        System.out.println("Vida: " + vida.join());
        System.out.println("Ataque: " + ataque.join());
        System.out.println("Defensa: " + defensa.join());
        System.out.println("Velocidad: " + velocidad.join());
        System.out.println("Energía: " + energia.join());

        System.out.println("-------------------------------");
        System.out.println("Puntuación total: " + total);


        executor.shutdown();
    }
}