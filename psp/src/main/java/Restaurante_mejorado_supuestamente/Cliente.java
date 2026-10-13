package Restaurante_mejorado_supuestamente;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Cliente implements Runnable {
    private final int id;
    private final Semaphore mesas; // cola de espera para sentarse
    private final BlockingQueue<Pedido> ordenesNuevas;
    private final Estadisticas stats;
    private final TipoPlato plato;

    public Cliente(int id, Semaphore mesas, BlockingQueue<Pedido> ordenesNuevas, Estadisticas stats) {
        this.id = id;
        this.mesas = mesas;
        this.ordenesNuevas = ordenesNuevas;
        this.stats = stats;
        TipoPlato[] platos = TipoPlato.values();
        this.plato = platos[ThreadLocalRandom.current().nextInt(platos.length)];
    }

    @Override
    public void run() {
        String nombre = "Cliente-" + String.format("%03d", id);
        try {
            long llegada = System.nanoTime();
            Restaurante.log(nombre + " llega y hace cola para sentarse");

            mesas.acquire();
            stats.esperaSentarseTotalMs.addAndGet((System.nanoTime() - llegada) / 1_000_000);
            Restaurante.log(nombre + " se sienta en una mesa");

            Pedido pedido = new Pedido(id, plato);
            Restaurante.log(nombre + " pide " + plato);
            ordenesNuevas.put(pedido);

            pedido.servido.await();
            stats.esperaPlatoTotalMs.addAndGet((System.nanoTime() - pedido.horaPedido) / 1_000_000);

            Thread.sleep(1000);
            Restaurante.log(nombre + " termina de comer y se va");
            stats.clientesAtendidos.incrementAndGet();
            mesas.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
