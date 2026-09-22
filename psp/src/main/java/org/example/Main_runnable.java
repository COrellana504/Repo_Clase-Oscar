package org.example;

public class Main_runnable {
    static void main() {
        contadorRunnable contador = new contadorRunnable();
        Thread hilo = new Thread(contador);
        Thread hilo2 = new Thread(contador);
        hilo.start();
        hilo2.start();
    }
}
