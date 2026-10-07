package Ejercicios_hilos_java25.Ejercicio1;

public class MainThread {

    public static void main(String[] args) {

        for (int i = 1; i <= 4; i++) {

            DescargaThread hilo =
                    new DescargaThread("archivo" + i + ".zip");

            hilo.setName("descarga-" + i);

            hilo.start();
        }
    }
}
