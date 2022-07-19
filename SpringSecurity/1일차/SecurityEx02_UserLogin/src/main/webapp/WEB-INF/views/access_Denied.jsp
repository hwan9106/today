<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<h1 style="color:red;">${msg }</h1>
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body>
</html>