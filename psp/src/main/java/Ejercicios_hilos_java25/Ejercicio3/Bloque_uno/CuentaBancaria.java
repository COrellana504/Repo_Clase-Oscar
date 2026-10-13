package Ejercicios_hilos_java25.Ejercicio3.Bloque_uno;

public class CuentaBancaria {

    private int saldo = 0;

    public void depositar(int cantidad) {

        saldo = saldo + cantidad;
    }

    public int getSaldo() {
        return saldo;
    }
}
