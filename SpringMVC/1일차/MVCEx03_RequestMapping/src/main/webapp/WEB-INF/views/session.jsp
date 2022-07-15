<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${name }님 (${age }세) <br />
	${sessionScope.name }님 (${sessionScope.age }세) <br />
	${requestScope.name }님 (${requestScope.age }세) <br />
	
</body>
</html>