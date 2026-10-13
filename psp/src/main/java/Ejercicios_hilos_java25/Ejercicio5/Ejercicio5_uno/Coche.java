package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_uno;

import java.util.Random;

public class Coche extends Thread {

    private Parking parking;

    public Coche(String nombre, Parking parking) {

        super(nombre);

        this.parking = parking;
    }

    @Override
    public void run() {

        try {

            parking.entrar(getName());

            Random random = new Random();

            int tiempo =
                    random.nextInt(4000) + 1000;

            Thread.sleep(tiempo);

            parking.salir(getName());

        } catch (InterruptedException e) {

            System.out.println(
                    getName()
                            + " fue interrumpido."
            );
        }
    }
}
