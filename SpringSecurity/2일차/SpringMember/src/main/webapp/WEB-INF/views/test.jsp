<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기에 제목</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script>
	$(function(){
		
	});
</script>
</head>
<body>
	<h2>오늘의 날짜 : <fmt:formatDate value="${today }" type="both" dateStyle="long" timeStyle="long"/> </h2>
	<h2>합계 : ${num1 } + ${num2 } = ${sum }</h2>
	<h2>곱셈 : ${num1 } * ${num2 } = ${mul }</h2>
	<hr />
	<h2>오늘의 날짜 : <fmt:formatDate value="${vo.today }" type="both" dateStyle="long" timeStyle="long"/> </h2>
	<h2>합계 : ${vo.num1 } + ${vo.num2 } = ${vo.sum }</h2>
	<h2>곱셈 : ${vo.num1 } * ${vo.num2 } = ${vo.mul }</h2>
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body></html>