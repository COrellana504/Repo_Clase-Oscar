package boveda;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/contenido")
public class ContenidoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Boveda boveda = Boveda.de(req.getSession());

        if (!boveda.isDesbloqueada()) {
            // Acceso no permitido: lo mandamos a la página de ingresar
            resp.sendRedirect(req.getContextPath() + "/ingresar");
            return;
        }

        req.setAttribute("boveda", boveda);
        req.setAttribute("contenido", "Un zambo picante");
        Vista.render(req, resp, "contenido");
    }
}