<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			$("#cancelBtn").css('visibility','hidden');
		});
		
		function updateEmp(idx, name, role){
			$("#cancelBtn").css('visibility','visible');
			$("#submitBtn").val("수정");
			$("#idx").val(idx);
			$("#name").val(name);
			$("#role").val(role);
			$("#mode").val(1);
		}
		function deleteEmp(idx){
			if(confirm(idx + "번 글을 정말로 지울래?")){
				location.href = 'delete?idx=' + idx;
			}
		}
		function resetEmp(){
			$("#cancelBtn").css('visibility','hidden');
			$("#submitBtn").val("저장");
			$("#idx").val(0);
			$("#name").val("");
			$("#role").val("");
			$("#mode").val(0);
		}
	</script>
	<style type="text/css">
		table{ width: 50%; margin: auto; }
		.title{ font-size: 18pt; text-align: center; border: none; background-color: white;}
		.info{ text-align: right; border: none; background-color: white;}
		th {
			padding: 5px; border: 1px solid gray; background-color: silver; text-align: center;
		}
		td {
			padding: 5px; border: 1px solid gray; text-align: center;  visibility:
		}
	
	</style>
</head>
<body>
	<table>
		<tr>
			<th colspan="4" class="title">EMP 목록보기</th>
		</tr>
		<tr>
			<th colspan="4" class="info">전체 : ${totalCount }명</th>
		</tr>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>직업</th>
			<th>E/D</th>
		</tr>
		<c:forEach var="vo" items="${list }" varStatus="vs">
			<tr>
				<td>${totalCount-vs.index }</td>			
				<td>${vo.name }</td>			
				<td>${vo.role }</td>			
				<td>
					<input type="button" value="수정" class="btn btn-danger btn-sm"
					       onclick="updateEmp(${vo.idx},'${vo.name }','${vo.role }')">
					<input type="button" value="삭제" class="btn btn-danger btn-sm"
					       onclick="deleteEmp(${vo.idx})">
				</td>			
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" style="border: none;">
				<form action="update" method="post">
					<input type="hidden" name="idx" id="idx" value="0" />
					<input type="hidden" name="mode" id="mode" value="0" /> <!-- 0(저장), 1(수정) -->
					<input type="text" name="name" id="name" required="required" placeholder="이름 입력">
					<input type="text" name="role" id="role" required="required" placeholder="직업 입력">
					<input type="submit" id="submitBtn" value="저장" class="btn btn-danger btn-sm" />
					<input type="button" id="cancelBtn" value="취소" class="btn btn-danger btn-sm" onclick="resetEmp()"/>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
