package mascota;

import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

public class Mascota implements Serializable {

    private String nombre = "sin nombre";
    private boolean adoptada = false;
    private int visitas = 0;

    private int hambre = 0;
    private int energia = 70;
    private int felicidad = 100;
    private boolean viva = true;
    private String mensaje = "";


    public static Mascota de(HttpSession session) {
        Mascota m = (Mascota) session.getAttribute("mascota");
        if (m == null) {
            m = new Mascota();
            session.setAttribute("mascota", m);
        }
        return m;
    }

    public void registrarVisitas() { visitas++; }

    public void adoptar(String nombre){
        this.nombre = nombre;
        this.adoptada = true;
        this.hambre = 30;
        this.energia = 70;
        this.felicidad = 30;
    }

    public void alimentar() {
        if (!puedeActuar()) return;
        hambre = Math.max(0, hambre - 25);
        felicidad = Math.min(100, felicidad + 5);
        mensaje = nombre + " se ha comido todo. ¡Ñam!";
    }

    public void jugar() {
        if (!puedeActuar()) return;
        if (hambre >= 70) {
            mensaje = nombre + " tiene demasiada hambre para jugar.";
            return;
        }
        if (energia < 20) {
            mensaje = nombre + " está muy cansada para jugar.";
            return;
        }
        felicidad = Math.min(100, felicidad + 20);
        energia = Math.max(0, energia - 20);
        subirHambre(15);
        if (viva) mensaje = nombre + " ha jugado y está feliz.";
    }

    public void dormir() {
        if (!puedeActuar()) return;
        if (hambre >= 80) {
            mensaje = nombre + " no puede dormir con tanta hambre.";
            return;
        }
        energia = Math.min(100, energia + 40);
        subirHambre(10);
        if (viva) mensaje = nombre + " ha dormido y recuperó energía.";
    }

    private void subirHambre(int cantidad) {
        hambre = Math.min(100, hambre + cantidad);
        if (hambre >= 100) {
            viva = false;
            mensaje = nombre + " ha muerto de hambre.";
        }
    }


    public String consumirMensaje() {
        String m = mensaje;
        mensaje = null;
        return m;
    }

    private boolean puedeActuar() {
        if (!adoptada) { mensaje = "Primero tienes que adoptar una mascota."; return false; }
        if (!viva)     { mensaje = nombre + " ha muerto. Adopta otra."; return false; }
        return true;
    }










    public String getNombre()    { return nombre; }
    public boolean isAdoptada()  { return adoptada; }
    public boolean isViva()      { return viva; }
    public int getVisitas()      { return visitas; }
    public int getHambre()       { return hambre; }
    public int getEnergia()      { return energia; }
    public int getFelicidad()    { return felicidad; }
}
