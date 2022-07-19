<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 결과 확인</title>
<script type="text/javascript">
	function fileDown(ofile, sfile) {
		location.href='download ? of=' + encodeURI(ofile) +'&sf='+encodeURI(sfile);
	}

</script>

</head>
<body>
	<h1>처리된 파일 정보</h1>
	저장이름 : ${saveName }<br />
	원본이름 : ${originalName }<br />
	파일형식 : ${contentType }<br />
	파일크기 : ${fileSize }<br />
	저장경로 : ${realPath }<br />
	내용 : ${content }<br />
	<hr />
	<button onlick="fileDown('${originalName }','${saveName }')">다운로드</button>
	<hr />
	<button onlick="location.href='${pageContext.request.contextPath }'">홈으로</button>
</body>
</html>