package org.aprendiendo_hilos;

public class mainContador {
    static void main() {
        Contador contador = new Contador();

        runnableContador rc = new runnableContador(contador);

        Thread hilo1 = new Thread(rc);
        Thread hilo2 = new Thread(rc);

        hilo1.start();
        hilo2.start();
    }
}
