<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러개 업로드하기</title>
</head>
<body>
	<form action="multiUploadOk" method="post" enctype="multipart/form-data">
		<input type="file" name="files" id="file1" required="required"/> <br />
		<input type="file" name="files" id="file2" required="required"/> <br />
		<input type="file" name="files" id="file3" required="required"/> <br />
		<input type="file" name="files" id="file4" required="required"/> <br />
		<input type="file" name="files" id="file5" required="required"/> <br />
		<textarea rows="5" cols="50" name="content" id="content" required="required" placeholder="내용입력"></textarea>
		<br />
		<input type="submit" value="업로드하기" />
	</form>
</body>
</html>