package Ejercicios_hilos_java25.ejercicio2;

public class MainCocinero {

    public static void main(String[] args) {

        Cocinero cocinero = new Cocinero();

        System.out.println(
                "Estado inicial: "
                        + cocinero.getState()
        );

        cocinero.start();

        System.out.println(
                "Tras start(): "
                        + cocinero.getState()
        );

        try {

            Thread.sleep(1500);

            System.out.println(
                    "Mientras duerme: "
                            + cocinero.getState()
            );

            Thread.sleep(1500);

            cocinero.interrupt();

            cocinero.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Estado final: "
                        + cocinero.getState()
        );
    }
}