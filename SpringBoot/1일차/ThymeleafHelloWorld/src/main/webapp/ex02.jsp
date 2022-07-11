<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.thymeleaf.context.WebContext"%>
<%@page import="kr.human.app.ThymeleafApp"%>
<%@page import="org.thymeleaf.TemplateEngine"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8"); // 한글깨짐 방지!!!!
TemplateEngine templateEngine = ThymeleafApp.getTemplateEngine(getServletContext());
WebContext webContext = ThymeleafApp.getWebContext(request, response, getServletContext());

SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일(EEE) hh:mm:ss ");
Date today = new Date();
webContext.setVariable("today1", dateFormat.format(today));
webContext.setVariable("today2", today.toLocaleString());
webContext.setVariable("today3", LocalDateTime.now());
webContext.setVariable("name", "한사람");

templateEngine.process("templates02", webContext, response.getWriter());
%>