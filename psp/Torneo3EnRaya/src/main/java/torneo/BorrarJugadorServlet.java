package torneo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/borrar")
public class BorrarJugadorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Torneo torneo = Torneo.de(req.getSession());
        String jugador = req.getParameter("jugador");

        if (jugador == null || !torneo.getJugadores().contains(jugador)) {
            req.setAttribute("mensaje", "Jugador no encontrado.");
        } else {
            torneo.borrarJugador(jugador);
            req.setAttribute("mensaje", "Jugador " + jugador + " eliminado junto con sus partidas.");
        }
        req.setAttribute("volver", "borrar.html");
        Vista.render(req, resp, "mensaje");
    }
}