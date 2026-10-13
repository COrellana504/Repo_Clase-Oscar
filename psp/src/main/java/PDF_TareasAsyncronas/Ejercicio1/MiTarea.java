package PDF_TareasAsyncronas.Ejercicio1;

import java.util.concurrent.Callable;

public class MiTarea implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        Thread.sleep(2000);

        return 42;
    }
}
