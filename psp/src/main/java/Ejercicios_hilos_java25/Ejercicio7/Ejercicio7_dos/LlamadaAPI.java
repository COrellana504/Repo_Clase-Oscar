package Ejercicios_hilos_java25.Ejercicio7.Ejercicio7_dos;

import java.util.Random;
import java.util.concurrent.Callable;

public class LlamadaAPI implements Callable<String> {

    private String nombreApi;

    public LlamadaAPI(String nombreApi) {

        this.nombreApi = nombreApi;
    }

    @Override
    public String call() throws Exception {

        Random random = new Random();

        int tiempo =
                random.nextInt(2000) + 1000;

        System.out.println(
                nombreApi + " iniciada..."
        );

        Thread.sleep(tiempo);

        return nombreApi + " respondió en "+ tiempo + " ms";
    }
}
