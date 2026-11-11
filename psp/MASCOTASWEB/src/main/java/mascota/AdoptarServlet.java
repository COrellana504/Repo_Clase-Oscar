package mascota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/adoptar")
public class AdoptarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mascota mascota = Mascota.de(req.getSession());
        req.setAttribute("mascota", mascota);
        Vista.render(req, resp, "adoptar");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        if(nombre == null || nombre.isBlank()){
            Mascota mascota = Mascota.de(req.getSession());
            req.setAttribute("mascota", mascota);
            req.setAttribute("error", "debes ponerle un nombre");
            Vista.render(req, resp, "adoptar");
        }
        Mascota mascota = Mascota.de(req.getSession());
        mascota.adoptar(nombre.trim());

        resp.sendRedirect(req.getContextPath() + "/casa");

    }
}
