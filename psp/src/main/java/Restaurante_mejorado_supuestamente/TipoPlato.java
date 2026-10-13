package Restaurante_mejorado_supuestamente;

public enum TipoPlato {
    ENSALADA(2000, 8),
    PASTA(3000, 12),
    PIZZA(4000, 15),
    CARNE(5000, 20);

    final int tiempoMs;
    final int precio; // para las ganancias

    TipoPlato(int tiempoMs, int precio) {
        this.tiempoMs = tiempoMs;
        this.precio = precio;
    }
}