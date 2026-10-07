package PDF_TareasAsyncronas;

import java.util.concurrent.Callable;

public class MiTarea implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        Thread.sleep(2000);

        return 42;
    }
}
