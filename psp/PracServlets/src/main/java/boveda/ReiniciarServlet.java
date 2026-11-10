package boveda;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/reiniciar")
public class ReiniciarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();   // destruye la sesión entera
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}
