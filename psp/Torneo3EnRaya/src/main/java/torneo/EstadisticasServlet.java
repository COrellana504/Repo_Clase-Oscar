package torneo;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/estadisticas")
public class EstadisticasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Torneo torneo = Torneo.de(req.getSession());
        String jugador = req.getParameter("jugador");
        String partida = req.getParameter("partida");

        if (partida != null) {
            try {
                Partida p = torneo.buscarPartida(Integer.parseInt(partida));
                if (p == null) { error(req, resp, "Partida no encontrada"); return; }
                req.setAttribute("modo", "historial");
                req.setAttribute("partida", p);
            } catch (NumberFormatException e) {
                error(req, resp, "Número de partida incorrecto");
                return;
            }
        } else if (jugador != null) {
            List<String> filas = new ArrayList<>();
            for (Partida p : torneo.getPartidas()) {
                if (!p.participa(jugador)) continue;
                String res;
                if (!p.isTerminada())                    res = "en curso";
                else if (p.getGanador() == null)         res = "empate";
                else if (p.getGanador().equals(jugador)) res = "ganó";
                else                                     res = "perdió";
                filas.add("Partida " + p.getId() + " - símbolo " + p.simboloDe(jugador)
                        + " - " + res + " contra " + p.rival(jugador));
            }
            req.setAttribute("modo", "jugador");
            req.setAttribute("jugador", jugador);
            req.setAttribute("filas", filas);
        } else {
            req.setAttribute("modo", "victorias");
            req.setAttribute("victorias", torneo.getVictorias());
        }
        Vista.render(req, resp, "estadisticas");
    }

    private void error(HttpServletRequest req, HttpServletResponse resp, String msg)
            throws IOException {
        req.setAttribute("mensaje", msg);
        req.setAttribute("volver", "estadisticas.html");
        Vista.render(req, resp, "mensaje");
    }
}