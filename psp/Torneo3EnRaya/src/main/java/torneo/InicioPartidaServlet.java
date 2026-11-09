package torneo;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/inicio")
public class InicioPartidaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String j1 = req.getParameter("j1"), j2 = req.getParameter("j2");
        String s1 = req.getParameter("s1"), s2 = req.getParameter("s2");

        if (j1 == null || j2 == null || s1 == null || s2 == null
                || j1.isBlank() || j2.isBlank() || s1.isEmpty() || s2.isEmpty()
                || j1.equals(j2) || s1.equals(s2)) {
            req.setAttribute("mensaje", "Parámetros incorrectos: nombres y símbolos deben ser distintos.");
            req.setAttribute("volver", "inicio.html");
            Vista.render(req, resp, "mensaje");
            return;
        }

        Torneo torneo = Torneo.de(req.getSession());
        Partida p = torneo.nuevaPartida(j1, s1.charAt(0), j2, s2.charAt(0));
        req.setAttribute("partida", p);
        req.setAttribute("s1", s1.charAt(0));
        req.setAttribute("s2", s2.charAt(0));
        Vista.render(req, resp, "inicio");
    }
}
