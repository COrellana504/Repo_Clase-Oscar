package Ejercicios_hilos_java25.Ejercicio3.Bloque_uno;

public class CuentaDeposito extends Thread {

    private CuentaBancaria cuenta;

    public CuentaDeposito(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {

        cuenta.depositar(1);
    }
}