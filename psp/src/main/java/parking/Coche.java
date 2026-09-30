package parking;

public class Coche implements Runnable{

    private Parking parking;
    private int contadordeentradas = 0;
    public Coche(Parking parking){
        this.parking = parking;
    }

    public void run(){
        for (int i = 1; i <=2; i++) {
            parking.entrar();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            contadordeentradas++;
            parking.salir();

        }

        }
}
