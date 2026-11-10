package boveda;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

    @WebServlet("/estado")
    public class EstadoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("boveda", Boveda.de(req.getSession()));
        Vista.render(req, resp, "estado");
    }
}