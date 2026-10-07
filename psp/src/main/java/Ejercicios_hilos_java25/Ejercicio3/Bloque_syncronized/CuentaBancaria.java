package Ejercicios_hilos_java25.Ejercicio3.Bloque_syncronized;

public class CuentaBancaria {

    private int saldo = 0;

    public void depositar(int cantidad) {

        synchronized (this) {

            int temporal = saldo;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            saldo = temporal + cantidad;
        }
    }

    public int getSaldo() {
        return saldo;
    }
}