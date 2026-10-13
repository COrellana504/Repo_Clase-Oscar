package Restaurante_mejorado_supuestamente;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Restaurante {
    static final int NUM_COCINEROS = 3;
    static final int NUM_CAMAREROS = 5;
    static final int NUM_CLIENTES = 30;
    static final int NUM_MESAS = 20;
    static final int CAPACIDAD_PEDIDOS = 10;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");

    static synchronized void log(String msg) {
        System.out.println("[" + LocalTime.now().format(FMT) + "] " + msg);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== RESTAURANTE CONCURRENTE ===");
        System.out.println("Iniciando servicio con " + NUM_COCINEROS + " cocineros y "
                + NUM_CAMAREROS + " camareros...\n");

        Estadisticas stats = new Estadisticas();
        BlockingQueue<Pedido> mesaPedidos = new ArrayBlockingQueue<>(CAPACIDAD_PEDIDOS);
        BlockingQueue<Pedido> ordenesNuevas = new LinkedBlockingQueue<>();
        BlockingQueue<Pedido> platosListos = new LinkedBlockingQueue<>();
        Semaphore mesas = new Semaphore(NUM_MESAS, true);

        long inicio = System.nanoTime();

        List<Thread> trabajadores = new ArrayList<>();
        for (int i = 1; i <= NUM_COCINEROS; i++) {
            Thread t = new Thread(new Cocinero(i, mesaPedidos, platosListos, stats), "Cocinero-" + i);
            t.start();
            trabajadores.add(t);
        }
        for (int i = 1; i <= NUM_CAMAREROS; i++) {
            Thread t = new Thread(new Camarero(i, ordenesNuevas, mesaPedidos, platosListos, stats), "Camarero-" + i);
            t.start();
            trabajadores.add(t);
        }

        Thread monitor = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5000);
                    log("--- [STATS] esperando mesa: " + mesas.getQueueLength()
                            + " | pedidos en cocina: " + mesaPedidos.size() + "/" + CAPACIDAD_PEDIDOS
                            + " | platos servidos: " + stats.platosServidos.get()
                            + " | ganancias: " + stats.gananciasDelDia.get() + "€ ---");
                }
            } catch (InterruptedException e) {
            }
        });
        monitor.setDaemon(true);
        monitor.start();


        List<Thread> clientes = new ArrayList<>();
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            Thread c = new Thread(new Cliente(i, mesas, ordenesNuevas, stats), "Cliente-" + i);
            c.start();
            clientes.add(c);
            Thread.sleep(500);
        }

        for (Thread c : clientes) c.join();

        for (Thread t : trabajadores) t.interrupt();
        for (Thread t : trabajadores) t.join();

        long totalMs = (System.nanoTime() - inicio) / 1_000_000;
        double promEspera = stats.esperaPlatoTotalMs.get() / 1000.0 / NUM_CLIENTES;
        double promMesa = stats.esperaSentarseTotalMs.get() / 1000.0 / NUM_CLIENTES;
        double eficiencia = 100.0 * stats.cocinerosOcupadosMs.get() / (NUM_COCINEROS * (double) totalMs);

        System.out.println("\n--- ESTADÍSTICAS FINALES ---");
        System.out.println("Clientes atendidos: " + stats.clientesAtendidos.get() + "/" + NUM_CLIENTES );
        System.out.println("Platos servidos: " + stats.platosServidos.get());
        System.out.printf("Tiempo promedio de espera del plato: %.1fs%n", promEspera);
        System.out.printf("Tiempo promedio de espera para sentarse: %.1fs%n", promMesa);
        System.out.println("Mesa llena (veces): " + stats.mesaLlena.get());
        System.out.println("Cocineros esperando (tiempo): " + stats.cocinerosEsperandoMs.get() / 1000 + "s total");
        System.out.printf("Eficiencia: %.0f%%%n", eficiencia);
        System.out.println("Ganancias del día: " + stats.gananciasDelDia.get() + "€");
    }
}
