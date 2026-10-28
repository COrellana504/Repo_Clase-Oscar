package RepasandoDesdeCero.cocinita;

import java.util.Random;

public class Cocinero extends Thread{
    private String nombre;
    private Random rand = new Random();

    public Cocinero(String nombre){
        this.nombre = nombre;
    }


    @Override
    public void run() {
        System.out.println(nombre +": empieza a cocinar...");

        try {
            System.out.println(nombre + "Prepara el plato: N");
            Thread.sleep(300 + rand.nextInt(501));
        }catch (InterruptedException e){
            System.out.println("Interrumpido");
        }
        System.out.println(nombre + " Termino...");
    }
}
