package hogar;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

public final class Vista {
    private static TemplateEngine motor;

    private Vista() {}

    private static synchronized TemplateEngine motor(JakartaServletWebApplication app) {
        if (motor == null) {
            WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(app);
            resolver.setPrefix("/WEB-INF/templates/");
            resolver.setSuffix(".html");
            resolver.setTemplateMode(TemplateMode.HTML);
            resolver.setCharacterEncoding("UTF-8");
            resolver.setCacheable(false);

            motor = new TemplateEngine();
            motor.setTemplateResolver(resolver);
        }
        return motor;
    }

    public static void render(HttpServletRequest req, HttpServletResponse resp, String plantilla)
            throws IOException {
        ServletContext sc = req.getServletContext();
        JakartaServletWebApplication app = JakartaServletWebApplication.buildApplication(sc);
        WebContext ctx = new WebContext(app.buildExchange(req, resp));

        resp.setContentType("text/html;charset=UTF-8");
        motor(app).process(plantilla, ctx, resp.getWriter());
    }
}
