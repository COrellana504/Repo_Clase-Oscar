package examen.productor_cosnumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class pract1 {

    static Semaphore aparcamiento = new Semaphore(3);
    static AtomicInteger cochesDentro = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        // Creamos un array para guardar los 6 hilos
        Thread[] coches = new Thread[6];

        // Creamos los 6 coches
        for (int i = 1; i <= 6; i++) {

            final int id = i;

            coches[i - 1] = new Thread(() -> {

                try {
                    // 1. Intentar conseguir una plaza
                    System.out.println("Coche-" + id + " espera para entrar.");

                    aparcamiento.acquire();

                    // 2. Entrar al aparcamiento
                    int dentro = cochesDentro.incrementAndGet();

                    System.out.println("Coche-" + id
                            + " ENTRA. Coches dentro: " + dentro);

                    try {
                        // 3. Simular que aparca durante 3 segundos
                        Thread.sleep(3000);

                    } finally {
                        // 4. Salir del aparcamiento
                        int restantes = cochesDentro.decrementAndGet();

                        System.out.println("Coche-" + id
                                + " SALE. Coches dentro: " + restantes);

                        // 5. Liberar la plaza
                        aparcamiento.release();
                    }

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    System.out.println("Coche-" + id + " ha sido interrumpido.");
                }

            });

            // Iniciar el hilo
            coches[i - 1].start();
        }

        // Esperar a que terminen todos los coches
        for (Thread coche : coches) {
            coche.join();
        }

        System.out.println("\nTodos los coches han salido.");
    }
}