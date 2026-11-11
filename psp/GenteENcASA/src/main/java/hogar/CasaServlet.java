package hogar;

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
        Personas personas =Personas.de(req.getSession());
        String nombre = req.getParameter("nombre");

        if (nombre != null && !nombre.isBlank()) {
            personas.agregar(nombre.trim());
        }

        req.setAttribute("personas", personas);
        req.setAttribute("nombre", nombre);
        Vista.render(req,resp, "casa");
    }
}
