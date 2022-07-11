package kr.human.app;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ThymeleafApp {
	public static TemplateEngine getTemplateEngine(ServletContext servletContext) {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        return templateEngine;
	}
	
	public static WebContext getWebContext(HttpServletRequest request, HttpServletResponse response,ServletContext servletContext) {
		return new WebContext(request, response, servletContext, request.getLocale());
	}
}
