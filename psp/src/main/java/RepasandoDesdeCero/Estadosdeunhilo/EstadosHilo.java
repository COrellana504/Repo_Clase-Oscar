package RepasandoDesdeCero.Estadosdeunhilo;

public class EstadosHilo {

   void main(String[] args) throws InterruptedException {
        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }, "Hilo-Prueba");

        System.out.println("Antes de start:  " + hilo.getState());

        hilo.start();
        System.out.println("Justo tras start: " + hilo.getState());

        Thread.sleep(200);
        System.out.println("Mientras duerme:  " + hilo.getState());

        hilo.join();
        System.out.println("Tras terminar:    " + hilo.getState());
    }
}
