<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>카드 결제</p>

<form action="ticketCard" method="post">
	고객 아이디 : <input type="text" name="consumerId"/><br/>
	티켓 구매수 : <input type="text" name="amount"/><br/>
	<input type="submit" value="구매"/>
</form>

</body>
</html>