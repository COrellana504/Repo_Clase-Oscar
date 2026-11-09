package torneo;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/finTorneo")
public class FinTorneoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Torneo torneo = Torneo.de(req.getSession());
        List<String> ranking = torneo.ranking();

        if (ranking.size() < 3) {
            req.setAttribute("mensaje", "Hacen falta al menos 3 jugadores para el podio.");
            req.setAttribute("volver", "index.html");
            Vista.render(req, resp, "mensaje");
            return;
        }

        int llamada = torneo.siguienteLlamadaFin();   // 1, 2, 3
        String titulo, jugador;
        if (llamada == 1)      { titulo = "Tercer puesto"; jugador = ranking.get(2); }
        else if (llamada == 2) { titulo = "Segundo puesto"; jugador = ranking.get(1); }
        else {
            titulo = "¡CAMPEÓN!"; jugador = ranking.get(0);
            torneo.reiniciarLlamadasFin();
        }
        req.setAttribute("titulo", titulo);
        req.setAttribute("jugador", jugador);
        req.setAttribute("victorias", torneo.victorias(jugador));
        Vista.render(req, resp, "podio");
    }
}