import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import spring.ApplicationContext;
import spring.SpringConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        var context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfiguration.class);
        ApplicationContext.InitializeWith(context);
        context.setServletContext(servletContext);
        context.refresh();

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/api/*");

    }
}
