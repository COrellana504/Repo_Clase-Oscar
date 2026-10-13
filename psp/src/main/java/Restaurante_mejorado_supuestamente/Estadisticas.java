package Restaurante_mejorado_supuestamente;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Estadisticas {
    final AtomicInteger clientesAtendidos = new AtomicInteger();
    final AtomicInteger platosServidos = new AtomicInteger();
    final AtomicInteger mesaLlena = new AtomicInteger();
    final AtomicInteger gananciasDelDia = new AtomicInteger();
    final AtomicLong esperaPlatoTotalMs = new AtomicLong();
    final AtomicLong esperaSentarseTotalMs = new AtomicLong();
    final AtomicLong cocinerosEsperandoMs = new AtomicLong();
    final AtomicLong cocinerosOcupadosMs = new AtomicLong();
}
