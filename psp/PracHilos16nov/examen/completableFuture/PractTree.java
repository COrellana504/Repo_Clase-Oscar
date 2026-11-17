package examen.completableFuture;

import java.util.concurrent.CompletableFuture;

public class PractTree {
    static void main(String[] args) {
        CompletableFuture<Integer> asinc1 =
                CompletableFuture.supplyAsync(() -> 5)
                        .thenApply(r1 -> r1 *3);
        asinc1.join();

        CompletableFuture<Integer> asinc2 =
                CompletableFuture.supplyAsync(() -> 5)
                        .thenApply(r2 -> r2 * 2 )
                        .thenApply(r2 -> r2 + 10);
        asinc2.join();

        CompletableFuture<Void> resultadofinal =
                asinc1.thenCombine(asinc2, (res1, res2) ->
                        res1 + res2)
                                .thenApply(total -> total * 2 )
                                .thenApply(String::valueOf)
                                        .thenAccept(tot ->
                                                System.out.println("total: " + tot)
                                        );

        resultadofinal.join();
    }
}
