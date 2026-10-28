package RepasandoDesdeCero.Estadosdeunhilo;

public class HiloEducado {
        public static void main (String[]args){
            Thread hilo = new Thread(() -> {
                while (true) {
                    System.out.println("Trabajando...");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("Me interrumpieron, termino educadamente");
                        return;
                    }
                }
            }, "Trabajador");

            hilo.getState();
            hilo.start();
            hilo.getState();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("Esperando");
            }

            hilo.interrupt();
            hilo.getState();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Interrumpido");
            }


            System.out.println("main: el hilo terminó (" + hilo.getState() + ")");
        }
    }