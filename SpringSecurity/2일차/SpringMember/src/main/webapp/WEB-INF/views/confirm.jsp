<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증 처리</title>
</head>
<body>
	<c:if test="${empty memberVO }">
		<c:url value="/" var="home"/>
		<c:redirect url="${home }"/>
	</c:if>
	<c:if test="${not empty memberVO }">
		<c:if test="${memberVO.use=='1' }">
			<h2>${memberVO.username }님 인증에 성공했습니다. 로그인하셔서 사용하시기 바랍니다.</h2>
		</c:if>
		<c:if test="${memberVO.use=='0' }">
			<h2>인증에 실패했습니다. 인증 코드를 다시 확인 바랍니다.</h2>
		</c:if>
	</c:if>
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body>
</html>