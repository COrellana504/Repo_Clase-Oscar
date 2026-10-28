package RepasandoDesdeCero;

import java.util.Random;

public class Coches implements Runnable{

    private String nombre;
    public Coches(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 1; i <=10; i++) {
            System.out.println(nombre + " Avanza...");
            try {
                Thread.sleep(100 + rand.nextInt(401));
                System.out.println(nombre+ "Avanzo 10 metros...");
            } catch (InterruptedException e) {
                System.out.println(nombre + "se atraza");
            }
            if(i == 10){
                System.out.println(nombre + " llego a la meta...");
            }
            }
    }


    static void main() {
        try {
            Coches uno = new Coches("EL BUS SAID ");
            Coches dos = new Coches("EL BUS DE VICTOR ");
            Coches tres = new Coches("EL BUS DE CRIS");

            Thread huno = new Thread(uno);
            Thread hdos = new Thread(dos);
            Thread htres = new Thread(tres);
            huno.start();
            hdos.start();
            htres.start();

            huno.join();
            hdos.join();
            htres.join();

            System.out.println("Carrera terminada!!");
        }catch(InterruptedException e){
            System.out.println("Carrera interrumpida");
        }
    }
}
