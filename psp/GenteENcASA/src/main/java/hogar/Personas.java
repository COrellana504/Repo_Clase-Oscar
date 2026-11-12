package hogar;

import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Personas implements Serializable {

    private int edad = 0;
    private List<String> nombres = new ArrayList<>();


    public static Personas de(HttpSession session){
        Personas personas = (Personas) session.getAttribute("personas");

        if(personas == null){
            personas = new Personas();
            session.setAttribute("personas",personas);
        }
        return personas;
    }


    public void agregar(String nombre){
        nombres.add(nombre);
    }

    public List<String> getNombres(){
        return nombres;
    }

    public void obteneredad(int edad){
        this.edad = edad;
    }

}
