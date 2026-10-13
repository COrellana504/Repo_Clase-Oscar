package Ejercicios_hilos_java25.Ejercicio1;

public class MainRunnable {

    public static void main(String[] args) {

        for (int i = 1; i <= 4; i++) {

            DescargaRunnable tarea =
                    new DescargaRunnable("archivo" + i + ".zip");

            Thread hilo = new Thread(tarea);

            hilo.setName("descarga-" + i);

            hilo.start();
        }
    }
}
