package Restaurante_mejorado_supuestamente;

import java.util.concurrent.BlockingQueue;

public class Cocinero implements Runnable {
    private final int id;
    private final BlockingQueue<Pedido> mesaPedidos;
    private final BlockingQueue<Pedido> platosListos;
    private final Estadisticas stats;

    public Cocinero(int id, BlockingQueue<Pedido> mesaPedidos,
                    BlockingQueue<Pedido> platosListos, Estadisticas stats) {
        this.id = id;
        this.mesaPedidos = mesaPedidos;
        this.platosListos = platosListos;
        this.stats = stats;
    }

    @Override
    public void run() {
        try {
            while (true) {
                long t0 = System.nanoTime();
                Pedido p = mesaPedidos.take(); // espera si no hay pedidos
                stats.cocinerosEsperandoMs.addAndGet((System.nanoTime() - t0) / 1_000_000);

                Restaurante.log("Cocinero-" + id + " empieza " + p.plato + " de Cliente-" + p.idCliente);
                Thread.sleep(p.plato.tiempoMs);
                stats.cocinerosOcupadosMs.addAndGet(p.plato.tiempoMs);

                Restaurante.log("Cocinero-" + id + " termina " + p.plato + " para Cliente-" + p.idCliente);
                platosListos.put(p); // un camarero lo recogerá y lo servirá
            }
        } catch (InterruptedException e) {
        }
    }
}
