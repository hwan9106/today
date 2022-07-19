<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링에서 섭머노트 사용하기</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<!-- 언어 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.20/lang/summernote-ko-KR.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#summernote').summernote(
				{
					lang : 'ko-KR', // default: 'en-US'
					height : 300, // set editor height
					minHeight : null, // set minimum height of editor
					maxHeight : null, // set maximum height of editor
					fontNames : [ '맑은고딕', 'Arial', 'Arial Black','Comic Sans MS', 'Courier New', ],
					fontNamesIgnoreCheck : [ '맑은고딕' ],
					focus : true,
					// 이미지가 1MB를 넘을경우 수동으로 업로드를 처리하고 실행될 코드를 지정해준다.
					callbacks : {
						onImageUpload : function(files, editor, welEditable) {
							for (var i = files.length - 1; i >= 0; i--) {
								sendFile(files[i], this);
							}
						}
					}
				});
	});
	// 실제 업로드되는 서버의 파일을 Ajax로 호출해 줘야한다.
	function sendFile(file, el) {
		// 폼 작성
		var form_data = new FormData();
      	form_data.append('file', file);
      	// Ajax 호출
      	$.ajax({
        	data: form_data,
        	type: "POST",
        	url: 'imageUpload',
        	cache: false,
        	contentType: false,
        	enctype: 'multipart/form-data',
        	processData: false,
        	success: function(img_name) { // 성공
          		$(el).summernote('editor.insertImage', img_name);
        	}
      	});
    }
</script>
</head>
<body>
	<form action="result" method="post">
		<input type="text" size="60" name="subject"><br>
		<textarea id="summernote" name="note"></textarea>
		<input type="submit" value="전송" class="btn btn-outline-success btn-sm" >
	</form>
</body>
</html>