package Ejercicios_hilos_java25.Ejercicio1;

public class DescargaRunnable implements Runnable {

    private String nombreArchivo;

    public DescargaRunnable(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void run() {

        for (int progreso = 0; progreso <= 100; progreso += 10) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " - "
                            + nombreArchivo
                            + " -> "
                            + progreso
                            + "%"
            );

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(nombreArchivo + " descargado.");
    }
}
