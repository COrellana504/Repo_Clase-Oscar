package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_uno;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        Parking parking = new Parking(3);

        Thread[] coches = new Thread[10];

        for (int i = 0; i < 10; i++) {

            coches[i] =
                    new Coche(
                            "Coche-" + (i + 1),
                            parking
                    );

            coches[i].start();
        }

        for (int i = 0; i < 10; i++) {

            coches[i].join();
        }

        System.out.println(
                "Todos los coches han salido."
        );
    }
}
