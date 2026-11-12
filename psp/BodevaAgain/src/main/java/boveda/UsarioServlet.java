package boveda;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/usuario")
public class UsarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String nombre = req.getParameter("nombre");

        if (nombre == null || nombre.isBlank()) {
            req.setAttribute("mensaje", "Ingresa un usuario.");
        } else {
            session.setAttribute("nombre", nombre);
            req.setAttribute("nombre", nombre);
        }
        Vista.render(req, resp, "usuario");
    }
}
