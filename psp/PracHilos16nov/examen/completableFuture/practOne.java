package examen.completableFuture;

import java.util.concurrent.CompletableFuture;

public class practOne {
    static void main() {

        CompletableFuture<Void> assigmen =
                CompletableFuture.supplyAsync(() -> 8)
                        .thenApply(t -> t * 3)
                        .thenApply(t -> t + 10)
                        .thenApply(String::valueOf)
                        .thenAccept( resultado ->
                                System.out.println("TOTAL: "+ resultado));
        assigmen.join();

    }
}
