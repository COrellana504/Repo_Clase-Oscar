package boveda;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/abrircaja")
public class AbrirCaja extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String clave = (String) session.getAttribute("clave");
        String claveingresada = req.getParameter("pass");

        if (clave == null) {
            session.setAttribute("aviso", " Caja fuerte cerrada. Primero crea una contraseña.");
            resp.sendRedirect(req.getContextPath() + "/password");
            return;
        }

        if (claveingresada == null || claveingresada.isBlank()) {
            req.setAttribute("mensaje", "Ingresa la contraseña.");

            // Caso 3: comparan
        } else if (clave.equals(claveingresada)) {
            req.setAttribute("mensaje", "Caja fuerte abierta.");
        } else {
            req.setAttribute("mensaje", " Contraseña incorrecta. Caja cerrada.");
        }

        Vista.render(req, resp, "abrircaja");
    }
}
