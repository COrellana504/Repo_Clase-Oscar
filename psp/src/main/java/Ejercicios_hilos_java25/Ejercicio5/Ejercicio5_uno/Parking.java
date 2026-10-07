package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_uno;

import java.util.concurrent.Semaphore;

public class Parking {

    private Semaphore plazas;

    public Parking(int numeroPlazas) {

        plazas = new Semaphore(numeroPlazas);
    }

    public void entrar(String nombreCoche)
            throws InterruptedException {

        if (plazas.availablePermits() == 0) {

            System.out.println(
                    nombreCoche
                            + " espera porque no hay plazas."
            );
        }

        plazas.acquire();

        System.out.println(
                nombreCoche
                        + " ha entrado."
                        + " Plazas libres: "
                        + plazas.availablePermits()
        );
    }

    public void salir(String nombreCoche) {

        plazas.release();

        System.out.println(
                nombreCoche
                        + " ha salido."
                        + " Plazas libres: "
                        + plazas.availablePermits()
        );
    }
}
