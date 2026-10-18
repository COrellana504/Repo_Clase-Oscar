package RepasandoDesdeCero;

public class Contador extends Thread {
    private String nombre;
    public Contador(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for(int i = 0; i<=10 ; i++){
            System.out.println(getName() + ":" + i);
            try {
                Thread.sleep(500);
            }catch(InterruptedException e) {
                System.out.println(getName() + " fue interrumpido");
                return;
            }
        }
    }


    static void main() {
        new Contador("primer Hilo: ").start();
        new Contador("Segundo Hilo: ").start();
    }
}
