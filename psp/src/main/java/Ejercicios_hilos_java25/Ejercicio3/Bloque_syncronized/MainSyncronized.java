package Ejercicios_hilos_java25.Ejercicio3.Bloque_syncronized;

import Ejercicios_hilos_java25.Ejercicio3.Bloque_uno.CuentaBancaria;
import Ejercicios_hilos_java25.Ejercicio3.Bloque_uno.CuentaDeposito;

public class MainSyncronized {

     static void main(String[] args)
            throws InterruptedException {

        CuentaBancaria cuenta = new CuentaBancaria();

        Thread[] hilos = new Thread[100];

        for (int i = 0; i < 100; i++) {

            hilos[i] = new CuentaDeposito(cuenta);

            hilos[i].start();
        }

        for (int i = 0; i < 100; i++) {

            hilos[i].join();
        }

        System.out.println(
                "Saldo final 3.3: "
                        + cuenta.getSaldo()
        );
    }
}
