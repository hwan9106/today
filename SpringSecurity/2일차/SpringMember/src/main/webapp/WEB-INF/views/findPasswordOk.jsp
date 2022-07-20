<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 성공</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<script src="https://kit.fontawesome.com/3c36eed32b.js" crossorigin="anonymous"></script>
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/app.css" />
</head>

<body>
	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<div style="text-align: center;font-size: 13pt; color: blue;margin: 10px;">
						${vo.userid }님의 임시 비밀번호는 "${vo.password }"입니다. <br />
						로그인을 하시고 반드시 변경하여 사용하시기 바랍니다.
					</div>					
					<div style="margin: 15px;text-align: center;">
						<input type="button" onclick="location.href='${pageContext.request.contextPath}/login'"
							class="btn btn-success btn-default" value="로그인하기">
						<input type="button" onclick="location.href='${pageContext.request.contextPath}'"
							class="btn btn-success btn-default" value="홈으로가기">
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>