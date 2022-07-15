<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link href="${pageContext.request.contextPath }/webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="${pageContext.request.contextPath }/webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){

		});
	
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<hr />
	${ds } <br />
	${ds.connection } <br />
</body>
</html>
