<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<h2>DB 시간 : ${today }</h2>

<c:if test="${not empty list }">
	<c:forEach var="map" items="${list }">
		<c:out value="${map.NAME }"/> : 
		<c:out value="${map['DEPTNAME'] }"/> : 
		<c:out value="${map['CITY'] }"/> <br />
	</c:forEach>
</c:if>
</body>
</html>
