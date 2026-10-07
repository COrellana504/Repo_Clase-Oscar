package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_dos;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        ContadorVisitas contador =
                new ContadorVisitas();

        Thread[] visitantes =
                new Thread[1000];

        for (int i = 0; i < 1000; i++) {

            visitantes[i] =
                    new Visitante(contador);

            visitantes[i].start();
        }

        for (int i = 0; i < 1000; i++) {

            visitantes[i].join();
        }

        System.out.println(
                "Visitas totales: "
                        + contador.getVisitas()
        );
    }
}
