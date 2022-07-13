<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${vo.name }(${vo.age }, ${vo.gender ? "남":"여" })
	<br />
	생년월일 : <fmt:formatDate value="${vo.birth }" pattern="yyyy-MM-dd(E)"/>
</body>
</html>