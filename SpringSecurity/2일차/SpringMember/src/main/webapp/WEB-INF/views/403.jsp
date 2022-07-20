<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접근 금지</title>
</head>
<body>
	<h1>HTTP Status 403 - Access id denied</h1>
	<c:choose>
		<c:when test="${empty username }">
			<h2 style="color:red;">당신은 이 페이지에 접근 권한이 없습니다.</h2>
		</c:when>
		<c:otherwise>
			<h2 style="color:red;">${username }님은 이 페이지에 접근 권한이 없습니다.</h2>
		</c:otherwise>
	</c:choose>
	
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body>
</html>