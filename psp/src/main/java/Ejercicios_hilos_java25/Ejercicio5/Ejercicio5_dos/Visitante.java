package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_dos;

public class Visitante extends Thread {

    private ContadorVisitas contador;

    public Visitante(ContadorVisitas contador) {

        this.contador = contador;
    }

    @Override
    public void run() {

        contador.incrementar();
    }
}