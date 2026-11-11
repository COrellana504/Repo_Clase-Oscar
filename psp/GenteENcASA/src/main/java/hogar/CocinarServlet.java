package hogar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/cocina")
public class CocinarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Personas personas = Personas.de(req.getSession());
        String nombre = req.getParameter("nombre");
        String edadTexto = req.getParameter("edad");
        String mensaje = null;

        if (nombre != null && edadTexto != null) {
            try {
                int edad = Integer.parseInt(edadTexto.trim());
                if (edad >= 18) {
                    mensaje = "Adelante " + nombre + ", puedes usar la cocina ";
                } else {
                    mensaje = nombre + ", no puedes usar la cocina todavía.";
                }
            } catch (NumberFormatException e) {
                mensaje = "La edad debe ser un número.";
            }
        }

        req.setAttribute("personas", personas);
        req.setAttribute("mensaje", mensaje);
        Vista.render(req, resp, "cocina");
    }
}