<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1개 업로드하기</title>
</head>
<body>
	<form action="uploadOk" method="post" enctype="multipart/form-data">
		<input type="file" name="file" id="file" required="required"/> <br />
		<textarea rows="5" cols="50" name="content" id="content" required="required" placeholder="내용입력"></textarea>
		<br />
		<input type="submit" value="업로드하기" />
	</form>
</body>
</html>