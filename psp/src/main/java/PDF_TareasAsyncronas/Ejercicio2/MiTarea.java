package PDF_TareasAsyncronas.Ejercicio2;

import java.util.concurrent.Callable;

public class MiTarea implements Callable<String> {

    @Override
    public String call() throws Exception {

        System.out.println(
                "Ejecutando en: "+ Thread.currentThread().getName()
        );

        Thread.sleep(5000);

        return "Tarea finalizada";
    }
}
