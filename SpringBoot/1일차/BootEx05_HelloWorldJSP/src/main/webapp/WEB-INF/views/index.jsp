<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<c:forEach var="h" items="${hobby }">
			<li>${h }</li>
		</c:forEach>
	</ul>
	<hr />
	<ol>
		<li>${map.name }</li>
		<li>${map.age }</li>
		<li>${map.gender ? "남자":"여자" }</li>
	</ol>
	<hr />
	<ul>
		<c:forEach items="${list }" var="vo" >
			<li>${vo.name }(${vo.age }, ${gender?"남자":"여자" })</li>
		</c:forEach>
	</ul>
</body>
</html>