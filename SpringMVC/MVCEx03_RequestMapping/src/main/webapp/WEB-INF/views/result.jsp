<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${map }
	<br />
	<c:if test="${not empty d }">
		<c:forEach var="s" items="${d }">
			${s } <br />
		</c:forEach>
	</c:if>
	<br />
</body>
</html>