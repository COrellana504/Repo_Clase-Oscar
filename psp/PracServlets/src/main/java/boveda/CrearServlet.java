package boveda;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/crear")
public class CrearServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Vista.render(req, resp, "crear");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String n1 = req.getParameter("n1");
        String n2 = req.getParameter("n2");
        String n3 = req.getParameter("n3");

        if( !esDigito(n1) || !esDigito(n2) || !esDigito(n3)) {
            req.setAttribute("error", "Debes introducir tres numeros del 0 al 9");
            Vista.render(req, resp, "crear");
        }

        Boveda boveda = Boveda.de(req.getSession());
        boveda.crear(n1 + n2 + n3);

        req.setAttribute("boveda", boveda);
        Vista.render(req, resp, "creada");
    }


    private boolean esDigito(String s) {
        return s != null && s.matches("[0-9]");
    }
}
