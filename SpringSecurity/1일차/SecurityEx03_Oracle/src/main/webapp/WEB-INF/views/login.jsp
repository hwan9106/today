<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/css/app.css" />
</head>

<body>
	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<form action="${pageContext.request.contextPath }/login" method="post" class="form-horizontal">
						<%-- 로그인 실패시 에러메세지 출력 --%>
						<c:if test="${not empty error}">
							<div style="color: red;">${error }</div>
						</c:if>
						<%-- 로그아웃 성공시 메세지 출력 --%>
						<c:if test="${not empty msg}">
							<div style="color: green;">${msg }</div>
						</c:if>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="username"><i
								class="fa fa-user" style="font-size: 25pt;margin-right: 5px;color: green;"></i></label> <input type="text" class="form-control"
								id="username" name="userid" placeholder="Enter Username" required>
						</div>
						<div class="input-group input-sm">
							<label class="input-group-addon" for="password"><i
								class="fa fa-lock"  style="font-size: 25pt;margin-right: 5px;color: green;"></i></label> <input type="password"
								class="form-control" id="password" name="password"
								placeholder="Enter Password" required>
						</div>
						<!-- 시큐리트에서 사용자가 지정한 폼을 사용하려면 반드시 아래의 코드를 첨부해줘야 한다.-->
						<!-- 
						 -->
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="form-actions">
							<input type="submit"
								class="btn btn-block btn-primary btn-default" value="Log in">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>