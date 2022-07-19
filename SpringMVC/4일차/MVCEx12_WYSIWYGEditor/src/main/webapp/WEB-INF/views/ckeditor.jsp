<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace('editor1',{
		// 이것을 써줘야 로컬파일 업로드가 가능하다.
		filebrowserUploadUrl: '${pageContext.request.contextPath }/fileupload'
	});
});
</script>
<style type="text/css">

</style>
</head>
<body>
	<form action="result2" method="post">
		<textarea name="editor1" id="editor1"></textarea>
		<br />
		<button class="btn btn-outline-success btn-sm">전송하기</button>
	</form>
</body>
</html>