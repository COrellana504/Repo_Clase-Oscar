package Ejercicios_hilos_java25.Ejercicio6;

import java.util.concurrent.ConcurrentHashMap;

public class MainContadorPalabras {

    static void main() throws InterruptedException {
        ConcurrentHashMap<String, Integer> contador = new ConcurrentHashMap<>();

        String texto1 = "hola hola ";
        String texto2 = "pe ese pe ";
        String texto3 = "hola java java";

                Thread hilo1 = new Thread(new ContadorDePalabrasConcurrentes(texto1, contador));
                Thread hilo2 = new Thread(new ContadorDePalabrasConcurrentes(texto2, contador));
                Thread hilo3 = new Thread(new ContadorDePalabrasConcurrentes(texto3, contador));

           hilo1.start();
           hilo2.start();
           hilo3.start();

           hilo1.join();
           hilo2.join();
           hilo3.join();


        System.out.println("Contador:" + contador);
    }
}
