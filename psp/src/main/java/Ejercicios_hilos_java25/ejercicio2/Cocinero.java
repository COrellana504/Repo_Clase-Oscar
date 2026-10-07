package Ejercicios_hilos_java25.ejercicio2;

public class Cocinero extends Thread {

    @Override
    public void run() {

        try {

            for (int segundo = 1; segundo <= 10; segundo++) {

                System.out.println(
                        "Cocinando... segundo " + segundo
                );

                Thread.sleep(1000);
            }

            System.out.println("Plato terminado");

        } catch (InterruptedException e) {

            System.out.println("Cocción cancelada");
        }
    }
}
