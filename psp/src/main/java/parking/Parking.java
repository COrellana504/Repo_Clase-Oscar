package parking;

public class Parking {
    private int plazas = 50;

    public synchronized void entrar(){
        if(plazas>0){
            plazas--;
            System.out.println(" Coche entrando, plazas libres: " + plazas);
        } else {
            System.out.println(" Parking lleno");
        }
    }

    public synchronized  void salir(){
        plazas++;
        System.out.println(" Ha salido, plazas libres: " + plazas);
    }
}
