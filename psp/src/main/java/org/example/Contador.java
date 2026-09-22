package org.example;

public class Contador {
    private int numero = 0;

    public synchronized void incrementarse() {
        numero++;
    }

    public int getNumero(){
        return numero;
    }
}
