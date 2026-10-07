package Ejercicios_hilos_java25.ejercicio2;

public class MainCohete {

    public static void main(String[] args) {

        CuentaRegresiva cohete = new CuentaRegresiva();

        cohete.start();

        try {

            cohete.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("¡Despegue!");
    }
}
