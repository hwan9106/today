<%@page import="java.util.Arrays"%>
<%@page import="kr.human.vo.PersonVO"%>
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

PersonVO vo = new PersonVO("한사람",23,true, Arrays.asList("등산","낚시","음주","가무"));
session.setAttribute("vo", vo);

PersonVO vo2 = new PersonVO("두사람",18,false, Arrays.asList("음주","가무"));
request.setAttribute("vo", vo2);
templateEngine.process("templates03", webContext, response.getWriter());
%>