package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_tres;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        RecursoCompartido recurso =
                new RecursoCompartido();

        Trabajador t1 =
                new Trabajador(
                        "Hilo-1",
                        recurso
                );

        Trabajador t2 =
                new Trabajador(
                        "Hilo-2",
                        recurso
                );

        Trabajador t3 =
                new Trabajador(
                        "Hilo-3",
                        recurso
                );

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(10000);

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }
}
