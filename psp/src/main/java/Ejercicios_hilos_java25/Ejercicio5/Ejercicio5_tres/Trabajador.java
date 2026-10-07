package Ejercicios_hilos_java25.Ejercicio5.Ejercicio5_tres;

public class Trabajador extends Thread {

    private RecursoCompartido recurso;

    public Trabajador(
            String nombre,
            RecursoCompartido recurso) {

        super(nombre);

        this.recurso = recurso;
    }

    @Override
    public void run() {

        try {

            while (true) {

                recurso.usar(getName());

                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {

            System.out.println(
                    getName()
                            + " finaliza."
            );
        }
    }
}
