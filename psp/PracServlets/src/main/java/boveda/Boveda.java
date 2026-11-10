package boveda;

import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

public class Boveda implements Serializable {
    private String clave;
    private boolean creada;
    private boolean intentado;
    private boolean desbloqueada;

    public static Boveda de(HttpSession session){
        Boveda b = (Boveda) session.getAttribute("boveda");
        if (b == null){
            b = new Boveda();
            session.setAttribute("boveda", b);
        }
        return b;
    }

    public void crear(String clave){
        this.clave = clave;
        this.creada = true;
        this.desbloqueada = false;
        this.intentado = false;
    }

    public void intentar(String intento){
        this.intentado = true;
        this.desbloqueada = creada && intento.equals(clave);
    }


    public String getClave() {
        return clave;
    }
    public boolean isCreada() {
        return creada;
    }
    public boolean isIntentado() {
        return intentado;
    }
    public boolean isDesbloqueada() {
        return desbloqueada;
    }
}
