package RepasandoDesdeCero.cocinita;

public class MainCocina {
     void main() {
        try {
            Cocinero uno = new Cocinero("Cocinero uno: ");
            Cocinero dos = new Cocinero("Cocinero dos: ");
            Cocinero tres = new Cocinero("Cocinero tres: ");

            uno.start();
            dos.start();
            tres.start();

            uno.join();
            dos.join();
            tres.join();


            Pedido camarero_1 = new Pedido("SOPA");
            Pedido camarero_2 = new Pedido("Pollo");
            Pedido camarero_3 = new Pedido("picapollo");

            Thread Puno = new Thread(camarero_1, "camarero_1");
            Thread Pdos = new Thread(camarero_2, "camarero_2");
            Thread Ptres = new Thread(camarero_3, "camarero_3");

            Puno.start();
            Pdos.start();
            Ptres.start();

            uno.join();
            dos.join();
            tres.join();
            Puno.join();
            Pdos.join();
            Ptres.join();

            System.out.println("Restaurante cerrado");
        }catch(InterruptedException e ){
            System.out.println("Resturante en quiebra");
        }
    }
}