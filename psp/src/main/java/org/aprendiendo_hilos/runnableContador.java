package org.aprendiendo_hilos;

public class runnableContador extends Thread implements Runnable{
    private Contador contador;

    public runnableContador(Contador contador){
        this.contador = contador;
    }

    public void run(){
        for (int i = 0; i <= 5; i++) {
            contador.incrementarse();
            System.out.println(contador.getNumero());
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
