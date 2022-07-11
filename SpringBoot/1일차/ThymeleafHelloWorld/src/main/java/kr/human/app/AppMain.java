package kr.human.app;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet({ "/AppMain", "/" })
public class AppMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); // 한글깨짐 방지!!!!

		/*
		ServletContext context = request.getServletContext();

		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(context);
		
		// templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(false);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        
        log.info(request.getLocale().toString());
        */
		TemplateEngine templateEngine = ThymeleafApp.getTemplateEngine(getServletContext());
		// WebContext webContext = new WebContext(request, response, getServletContext() , request.getLocale());
		WebContext webContext = ThymeleafApp.getWebContext(request, response, getServletContext());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일(EEE) hh:mm:ss ");
        Calendar cal = Calendar.getInstance();
        webContext.setVariable("today", dateFormat.format(cal.getTime()));
        webContext.setVariable("name", "한사람");
        
        templateEngine.process("home", webContext, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
