package mascota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/casa")
public class CasaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mascota mascotacasa = Mascota.de(req.getSession());
        mascotacasa.registrarVisitas();

        req.setAttribute("mascota", mascotacasa);
        req.setAttribute("mensaje", mascotacasa.consumirMensaje());
        Vista.render(req, resp,"casa");
    }
}
