package Ejercicios_hilos_java25.Ejercicio3.Bloque_dos;

public class CuentaBancaria {

    private int saldo = 0;

    public synchronized void depositar(int cantidad) {

        int temporal = saldo;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saldo = temporal + cantidad;
    }

    public int getSaldo() {
        return saldo;
    }
}