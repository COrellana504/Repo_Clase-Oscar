package iesquevedo;

import config.ThymeleafConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

@WebServlet("/tres")
public class MiPrimerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String marca = req.getParameter("marca");
        String coordenada = req.getParameter("coordenada");
        String error = "";
        String j1 = "X";
        String j2 = "O";
        String turno = "Turno de ";


        String[] Tablero = (String[]) req.getSession().getAttribute("Tablero");
        if (Tablero==null) {
            Tablero = new String[]{"-", "-", "-", "-", "-", "-", "-", "-", "-"};
            if (req.getSession().getAttribute("marca") == null) {
                req.getSession().setAttribute("marca", j1);
            }
        } else {
            req.getSession().setAttribute("Tablero",Tablero);
            if (marca==null || coordenada==null) {
                error = "No se han introducido datillos.";
            } else {
                if (!(marca.equalsIgnoreCase("X")) || !(marca.equalsIgnoreCase("O"))){
                    error = "Introduce una marca válida.";
                } else {
                    if (!marca.equalsIgnoreCase(req.getParameter("marca"))){
                        error = "No es tu turno sucio tramposo";
                    } else {
                        Tablero[Integer.parseInt(coordenada)-1]=marca;
                        if(req.getSession().getAttribute("marca") == j1) {
                            req.getSession().setAttribute("marca", j2);
                        } else {
                            req.getSession().setAttribute("marca", j1);
                        }
                        turno = turno.concat(marca);
                    }
                }
            }
        }

        String fila1 = Tablero[0]+ " " + Tablero[1]+ " " + Tablero[2];
        String fila2 = Tablero[3]+ " " + Tablero[4]+ " " + Tablero[5];
        String fila3 = Tablero[6]+ " " + Tablero[7] + " " + Tablero[8];


        var webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext ctx = new WebContext(webExchange);
        ctx.setVariable("fila1", fila1);
        ctx.setVariable("fila2", fila2);
        ctx.setVariable("fila3", fila3);
        ctx.setVariable("error", error);
        ctx.setVariable("turno", turno);
        resp.setContentType(ThymeleafConstants.CONTENT_TYPE);
        ((TemplateEngine)getServletContext().getAttribute(ThymeleafConstants.TEMPLATE_ENGINE_ATTR))
                .process("tresEnRaya.html", ctx, resp.getWriter());


    }
}
