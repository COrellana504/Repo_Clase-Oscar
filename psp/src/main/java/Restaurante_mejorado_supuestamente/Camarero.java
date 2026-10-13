package Restaurante_mejorado_supuestamente;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Camarero implements Runnable {
    private final int id;
    private final BlockingQueue<Pedido> ordenesNuevas;
    private final BlockingQueue<Pedido> mesaPedidos;
    private final BlockingQueue<Pedido> platosListos;
    private final Estadisticas stats;

    public Camarero(int id, BlockingQueue<Pedido> ordenesNuevas, BlockingQueue<Pedido> mesaPedidos,
                    BlockingQueue<Pedido> platosListos, Estadisticas stats) {
        this.id = id;
        this.ordenesNuevas = ordenesNuevas;
        this.mesaPedidos = mesaPedidos;
        this.platosListos = platosListos;
        this.stats = stats;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Pedido listo = platosListos.poll();
                if (listo != null) {
                    Restaurante.log("Camarero-" + id + " sirve " + listo.plato + " a Cliente-" + listo.idCliente);
                    stats.platosServidos.incrementAndGet();
                    stats.gananciasDelDia.addAndGet(listo.plato.precio);
                    listo.servido.countDown(); // despierta al cliente
                    continue;
                }

                Pedido nuevo = ordenesNuevas.poll(100, TimeUnit.MILLISECONDS);
                if (nuevo != null) {
                    Restaurante.log("Camarero-" + id + " toma pedido " + nuevo.plato + " de Cliente-" + nuevo.idCliente);
                    Thread.sleep(1000); // 1 segundo por pedido

                    if (!mesaPedidos.offer(nuevo)) { // mesa llena
                        stats.mesaLlena.incrementAndGet();
                        Restaurante.log("Camarero-" + id + " MESA LLENA, esperando...");
                        mesaPedidos.put(nuevo); // bloquea hasta que haya hueco
                    }
                }
            }
        } catch (InterruptedException e) {
        }
    }
}
