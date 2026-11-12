package boveda;

import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

public class CajaFuerte implements Serializable {


    public static CajaFuerte de(HttpSession session){
        CajaFuerte cajafuerte =(CajaFuerte) session.getAttribute("cajaFuerteSession");

        if(cajafuerte == null){
            cajafuerte = new CajaFuerte();
            session.setAttribute("cajafuerteSession", cajafuerte);
        }
        return cajafuerte;
    }


}
