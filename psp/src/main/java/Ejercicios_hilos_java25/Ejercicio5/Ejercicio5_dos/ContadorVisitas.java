package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_dos;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorVisitas {

    private AtomicInteger visitas =
            new AtomicInteger(0);

    public void incrementar() {

        visitas.incrementAndGet();
    }

    public int getVisitas() {

        return visitas.get();
    }
}
