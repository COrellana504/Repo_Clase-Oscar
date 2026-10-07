package Ejercicios_hilos_java25.Ejercicio6;

import java.util.concurrent.ConcurrentHashMap;

public class ContadorDePalabrasConcurrentes implements Runnable{


        private String texto;
        private ConcurrentHashMap<String, Integer> contador;

        public ContadorDePalabrasConcurrentes(String texto, ConcurrentHashMap<String, Integer> contador){

            this.texto = texto;
            this.contador = contador;
        }


    @Override
    public void run() {
        String[] palabras = texto.split(" ");

        for(String palabra : palabras){
            contador.merge(palabra, 1, Integer::sum);
        }
    }
}
