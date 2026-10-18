package RepasandoDesdeCero.cocinita;

public class Pedido implements Runnable{
private String plato;

    public Pedido(String plato){
        this.plato = plato;
    }

    @Override
    public void run() {
        System.out.println("Entregando plato: " + plato);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrumpido");
        }
        System.out.println("Plato listo!!");
    }
}
