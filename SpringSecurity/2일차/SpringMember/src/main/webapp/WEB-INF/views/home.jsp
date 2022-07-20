<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>테스트하기</title>
	<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
</head>
<body>
	<h1>서버시간 : ${serverTime }</h1>
	<hr />
	현재 사용자 : ${user }
	<hr />
	세션의 회원정보 : ${mvo }
	<hr />
	<a href="test">DB 연결테스트</a>
	<a href="testMail">메일테스트</a>
	<a href="board/list">회원전용게시판</a>
	<br />
	<hr />
	<!-- POST로 로그아웃 -->
	<c:if test='${pageContext.request.userPrincipal.name !=null }'>
		<c:url value="/logout" var="logoutURL"/>
		<form action="${logoutURL }" method="post" id="logoutForm">
			<%-- 시큐리티에 있는 로그아웃을 사용하려면 토큰값도 넘겨줘야 한다. --%>
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="submit" value="로그아웃">
		</form>
		<a href="updatePasswordForm">비밀 번호 변경</a>
		<a href="updateForm">회원 정보 수정</a>
		<a href="deleteForm">회원 탈퇴</a>
	</c:if>
	<c:if test='${user== "anonymousUser" }'>
		<a href="insertForm">회원가입하기</a>
		<a href="${pageContext.request.contextPath }/login">로그인</a>
	</c:if>
</body>
</html>
