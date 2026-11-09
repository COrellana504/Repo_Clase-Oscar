package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/configurar")
public class ConfigurarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");

        String s1 = req.getParameter("n1");
        String s2 = req.getParameter("n2");
        String s3 = req.getParameter("n3");

        if (s1 == null || s2 == null || s3 == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error, falta un parametro");
            return;
        }

        int n1, n2, n3;

        try {
            n1 = Integer.parseInt(s1.trim());
            n2 = Integer.parseInt(s2.trim());
            n3 = Integer.parseInt(s3.trim());
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: n1, n2 y n3 deben ser números enteros");
            return;
        }

        HttpSession sesion = req.getSession(true);
        sesion.setAttribute("combinacion", new int[]{n1, n2, n3});
        sesion.setAttribute("progreso", 0);
        sesion.setAttribute("abierta", false);

        JakartaServletWebApplication app = JakartaServletWebApplication.buildApplication(getServletContext());
        WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(app);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        TemplateEngine motor = new TemplateEngine();
        motor.setTemplateResolver(resolver);

        IWebExchange exchange = app.buildExchange(req, resp);
        WebContext contexto = new WebContext(exchange);
        contexto.setVariable("n1", n1);
        contexto.setVariable("n2", n2);
        contexto.setVariable("n3", n3);

        resp.setContentType("text/html;charset=UTF-8");
        motor.process("index", contexto, resp.getWriter());
    }
}