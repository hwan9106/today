<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="get1">
		<input type="text" name="name" placeholder="이름을 입력하세요" required="required"/>
		<input type="number" name="age" placeholder="나이를 입력하세요" required="required"/>
		<input type="submit" value="전송" />
	</form>

	<hr />
	
	<form action="get2" method="get">
		<input type="text" name="name" placeholder="이름을 입력하세요" required="required"/>
		<input type="number" name="age" placeholder="나이를 입력하세요" required="required"/>
		<input type="submit" value="GET전송" />
	</form>

	<hr />
	
	<form action="get2" method="post">
		<input type="text" name="name" placeholder="이름을 입력하세요" required="required"/>
		<input type="number" name="age" placeholder="나이를 입력하세요" required="required"/>
		<input type="submit" value="POST전송" />
	</form>
	<hr />
	
	<form action="get3" method="post">
		<input type="text" name="name" placeholder="이름을 입력하세요" />
		<input type="number" name="age" placeholder="나이를 입력하세요"/>
		<input type="submit" value="전송" />
	</form>

	<hr />
	
	<form action="vo" method="post">
		<input type="text" name="name" placeholder="이름을 입력하세요" />
		<input type="number" name="age" placeholder="나이를 입력하세요"/>
		<br />
		<label><input type="radio" value="true" name="gender" checked="checked" /> 남자</label> 
		<label><input type="radio" value="false" name="gender" /> 여자</label>
		<br />
		 <input type="date" name="birth" value="1992-08-22" />
		<input type="submit" value="VO전송" />
	</form>

	
</body>
</html>