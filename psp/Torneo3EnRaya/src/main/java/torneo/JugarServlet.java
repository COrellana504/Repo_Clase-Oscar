package torneo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/jugar")
public class JugarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Partida p = Torneo.de(req.getSession()).getPartidaActual();
        if (p == null) {
            req.setAttribute("mensaje", "No hay partida en curso.");
            req.setAttribute("volver", "inicio.html");
            Vista.render(req, resp, "mensaje");
            return;
        }

        String f = req.getParameter("fila"), c = req.getParameter("col");
        if (f != null && c != null && !p.isTerminada()) {
            try {
                if (!p.mover(Integer.parseInt(f), Integer.parseInt(c)))
                    req.setAttribute("aviso", "Movimiento no válido");
            } catch (NumberFormatException e) {
                req.setAttribute("aviso", "Fila/columna incorrectas");
            }
        }
        req.setAttribute("partida", p);
        Vista.render(req, resp, "tablero");
    }
}
