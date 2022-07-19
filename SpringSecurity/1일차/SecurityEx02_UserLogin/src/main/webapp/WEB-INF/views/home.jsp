<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	서버시간 : ${serverTime }
	<hr />
	현재 사용자 : ${user }
	<hr />
	<a href="admin">관리자 페이지 가기</a> (ADMIN권한 사용자만 접근 가능)<br />
	<a href="dba">DB 관리자 페이지 가기</a> (ADMIN 또는 DBA권한을 가진 사용자만 접근 가능) <br />
	<a href="home">일반 페이지 가기</a> (누구나 접근가능)<br />
	
	<c:if test='${user!= "anonymousUser" }'>
		<a href="${pageContext.request.contextPath }/logout">로그아웃</a>
	</c:if>
	<c:if test='${user== "anonymousUser" }'>
		<a href="${pageContext.request.contextPath }/login">로그인</a>
	</c:if>
</body>
</html>