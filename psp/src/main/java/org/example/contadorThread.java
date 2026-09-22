package org.example;

public class contadorThread extends Thread {
    public void run() {

        for( int i = 1; i<=10 ; i++) {
            System.out.println("hilo "+i);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
