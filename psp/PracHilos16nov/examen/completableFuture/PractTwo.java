package examen.completableFuture;

import java.util.concurrent.CompletableFuture;

public class PractTwo {
    static void main(String[] args) {
        CompletableFuture<Void> ejr2 =
                CompletableFuture.supplyAsync(() -> 25.0)
                        .thenApply(g -> g *1.8)
                        .thenApply(g -> g + 32)
                        .thenApply(g -> Math.round(g * 100.0) / 100.0)
                        .thenApply( g -> String.format("%.2f", g))
                        .thenAccept( resultado ->
                                System.out.println("Fahrenheit " + resultado));

        ejr2.join();
    }
}
