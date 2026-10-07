package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_tres;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RecursoCompartido {

    private ReentrantLock lock =
            new ReentrantLock();

    public void usar(String nombre)
            throws InterruptedException {

        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {

            try {

                System.out.println(
                        nombre
                                + " consiguió el recurso."
                );

                Thread.sleep(2000);

                System.out.println(
                        nombre
                                + " termina de usarlo."
                );

            } finally {

                lock.unlock();
            }

        } else {

            System.out.println(
                    nombre
                            + ": Ocupado, lo intento más tarde."
            );
        }
    }
}