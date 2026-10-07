package Ejercicios_hilos_java25.Ejercicio3.Bloque_syncronized;

import Ejercicios_hilos_java25.Ejercicio3.Bloque_uno.CuentaBancaria;

public class CuentaDeposito extends Thread {

    CuentaBancaria cuenta;

    public CuentaDeposito(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {

        cuenta.depositar(1);
    }
}
