package Restaurante_mejorado_supuestamente;

import java.util.concurrent.CountDownLatch;

public class Pedido {
    final int idCliente;
    final TipoPlato plato;
    final long horaPedido = System.nanoTime();
    final CountDownLatch servido = new CountDownLatch(1);

    public Pedido(int idCliente, TipoPlato plato) {
        this.idCliente = idCliente;
        this.plato = plato;
    }
}
