package boveda;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ingresar")
public class IngresarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Boveda boveda = Boveda.de(req.getSession());
        req.setAttribute("boveda", boveda);
        Vista.render(req, resp, "ingresar");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Boveda boveda = Boveda.de(req.getSession());

        String n1 = req.getParameter("n1");
        String n2 = req.getParameter("n2");
        String n3 = req.getParameter("n3");

        if (boveda.isCreada() && n1 != null && n2 != null && n3 != null) {
            boveda.intentar(n1 + n2 + n3);
        }

        // Redirect: le decimos al navegador "ve a pedir /ingresar otra vez"
        resp.sendRedirect(req.getContextPath() + "/ingresar");
    }

}