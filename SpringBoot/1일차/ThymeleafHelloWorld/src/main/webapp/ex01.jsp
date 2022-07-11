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
// properties파일에서 내용읽기
templateEngine.process("templates01", webContext, response.getWriter());
%>