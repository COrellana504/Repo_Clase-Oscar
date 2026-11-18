package examen.productor_cosnumidor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class pract2 {

    // El restaurante tiene 5 mesas
    static Semaphore mesas = new Semaphore(5);

    public static void main(String[] args) throws InterruptedException {

        // Creamos 10 clientes
        Thread[] clientes = new Thread[10];

        for (int i = 1; i <= 10; i++) {
            final int id = i;

            clientes[i - 1] = new Thread(() -> {

                boolean tieneMesa = false;

                try {
                    System.out.println("Cliente-" + id
                            + " intenta conseguir mesa.");

                    // Esperar como máximo 4 segundos
                    tieneMesa = mesas.tryAcquire(4, TimeUnit.SECONDS);

                    if (tieneMesa) {

                        System.out.println("Cliente-" + id
                                + " consigue mesa y empieza a comer.");

                        // Simular que come durante 2 segundos
                        Thread.sleep(2000);

                        System.out.println("Cliente-" + id
                                + " termina de comer.");

                    } else {

                        System.out.println("Cliente-" + id
                                + " se marcha: no hay mesas disponibles.");

                    }

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    System.out.println("Cliente-" + id + " interrumpido.");

                } finally {

                    // Liberar la mesa solamente si consiguió una
                    if (tieneMesa) {
                        mesas.release();

                        System.out.println("Cliente-" + id
                                + " libera su mesa.");
                    }
                }

            });

            clientes[i - 1].start();
        }

        // Esperar a que terminen los 10 clientes
        for (Thread cliente : clientes) {
            cliente.join();
        }

        System.out.println("\nEl servicio ha terminado.");
    }
}