package boveda;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/password")
public class ContraseñaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String num3 = req.getParameter("num3");
        String nameguardado = (String) session.getAttribute("nombre"); //añadido ahoira
        String nombre = req.getParameter("name");

        if (num1 == null || num2 == null || num3 == null
                || num1.isBlank() || num2.isBlank() || num3.isBlank()) {
            req.setAttribute("mensaje", "Necesitas ingresar 3 parámetros: num1, num2 y num3");
        } else {
            String clave = num1 + num2 + num3;
            session.setAttribute("nombre", nameguardado);
            req.setAttribute("nombre", nameguardado);
            session.setAttribute("clave", clave);
            req.setAttribute("clave", clave);
        }

        if(nombre == null || nombre.isBlank()){
            req.setAttribute("mensaje", "Ingresa un nombre de usuario");
        } else if(!nameguardado.equals(nombre)) {
            req.setAttribute("mensaje", "No eres la persona dueña de esta caja");
            resp.sendRedirect(req.getContextPath() + "/usuario");
        }else{
            req.setAttribute("mensaje", "Caja fuerte abierta.");
        }
        Vista.render(req, resp, "password");
    }

}
