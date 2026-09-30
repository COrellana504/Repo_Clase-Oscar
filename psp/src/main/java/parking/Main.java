package parking;

public class Main {
    static void main() {
        Parking parking = new Parking();
        Coche coche = new Coche(parking);

        Thread entrada1 = new Thread(coche);
        Thread entrada2 = new Thread(coche);
        Thread entrada3 = new Thread(coche);

        entrada1.start();
        entrada2.start();
        entrada3.start();

    }
}
