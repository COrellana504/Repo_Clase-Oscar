package org.example;
//una clase se ejecuta de forma lineal -  vertical arrancando desde la parte de arriba hasta abajo,
// hay varias formas de interrumpir ese hilo de ejecucion y es creando otro con la clase thread
// hay que arrancarlo con el metodo start



public class Main_mv{

    static void main(String[] args) {
        contadorThread cnt = new contadorThread();
        cnt.start();

    }
}
