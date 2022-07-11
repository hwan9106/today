<%@page import="java.util.Calendar"%>
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
Calendar cal = Calendar.getInstance();
webContext.setVariable("today", dateFormat.format(cal.getTime()));
webContext.setVariable("name", "한사람");

templateEngine.process("home", webContext, response.getWriter());
%>