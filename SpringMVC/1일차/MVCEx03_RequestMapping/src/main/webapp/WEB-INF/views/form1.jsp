<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="m05" method="get">
		<input type="text" name="name" placeholder="이름을 입력하세요" required="required"/>
		<input type="submit" value="GET전송" />
	</form>
	<hr />
	<form action="m05" method="post">
		<input type="text" name="name" placeholder="이름을 입력하세요" required="required"/>
		<input type="submit" value="POST전송" />
	</form>
	
</body>
</html>