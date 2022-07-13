<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1줄 메모장!!!</title>
<%-- axicon 사용하기 --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/axicon/axicon.min.css" />
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<%-- 사용자 정의 자바스크립트 함수를 추가한다. --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/commons.js"></script>
<style type="text/css">
	.memo-title{
		margin: auto;
		text-align: center; width: 80%; font-size: 18pt; border: none; padding: 5px;
	}
	.pageInfo{
		margin: auto;
		text-align: right; width: 80%; font-size: 10pt; border: none; padding: 5px;
	}
	.content{
		margin: auto;
		text-align: left; width: 80%; font-size: 10pt; border: 1px solid gray; padding: 5px;
		margin-bottom: 5px;
	}
	.contentTitle{
		margin: auto; background-color:silver;
		text-align: left; width: 100%; font-size: 10pt; border: 1px solid gray; padding: 5px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#cancelBtn").css('display','none');
	});
	
	// 메모폼 지우기
	function memoReset(){
		$("#cancelBtn").css('display','none');
		$("#idx").val(0);
		$("#mode").val("insert");
		$("#submitBtn").val("저장");
		$("#name").val("");
		$("#password").val("");
		$("#content").val("");
		$("#name").focus();
	}
	// 수정
	function memoUpdate(idx){
		$("#cancelBtn").css('display','inline');
		$("#idx").val(idx);
		$("#mode").val("update");
		$("#submitBtn").val("수정");
		$("#name").val($("#name"+idx).html());
		$("#password").val("");
		$("#content").val($("#content"+idx).html());
		$("#password").focus();
	}
	// 삭제
	function memoDelete(idx){
		$("#cancelBtn").css('display','inline');
		$("#idx").val(idx);
		$("#mode").val("delete");
		$("#submitBtn").val("삭제");
		$("#name").val($("#name"+idx).html());
		$("#password").val("");
		$("#content").val($("#content"+idx).html());
		$("#password").focus();
	}
	
</script>
</head>
<body>
	<div class="memo-title">재미없는 메모장 Ver 0.009</div>
	<div class="pageInfo">${pv.pageInfo }</div>
	<c:if test="${pv.totalCount==0 }">
		<div class="content">등록된 글이 없습니다.</div>
	</c:if>
	<c:if test="${pv.totalCount>0 }">
		<c:if test="${not empty pv.list }">
			<c:forEach var="vo" items="${pv.list }">
				<div class="content">
					<div class="contentTitle">
						 <b id="name${vo.idx}">${vo.name }</b>님이 
						 <b>${vo.ip }</b>에서
						 <b><fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd(E) hh:mm:ss"/></b>에 남긴글
						 <button type="button" class="btn btn-outline-danger btn-sm" onclick="memoUpdate(${vo.idx})">수정</button>
						 <button type="button" class="btn btn-outline-danger btn-sm" onclick="memoDelete(${vo.idx})">삭제</button>
					</div>
					<div id="content${vo.idx }" style="display: none;">${vo.content }</div>
					<c:set var="content" value="${vo.content }"/>
					<c:set var="content" value='${fn:replace(content, "<","&lt;") }'/>
					<c:set var="content" value='${fn:replace(content, newLine, br) }'/>
					${content }
				</div>
			</c:forEach>
			<div>${pv.pageList }</div>
		</c:if>
	</c:if>
	<div class="content" style="border: none;">
		<form action="update" id="memoForm" method="post">
			<input type="hidden" name="p" value="${p }"> 
			<input type="hidden" name="s" value="${s }"> 
			<input type="hidden" name="b" value="${b }"> 
			<input type="hidden" name="idx" value="0" id="idx"> 
			<input type="hidden" name="mode" value="insert" id="mode"> 
			<input type="hidden" name="ip" value="${pageContext.request.remoteAddr }"> 
			<input type="text" name="name" id="name" placeholder="이름입력" required="required">
			<input type="password" name="password" id="password" placeholder="비번입력" required="required">
			<input type="submit" id="submitBtn" class="btn btn-outline-danger btn-sm" value="저장" style="margin-bottom: 5px;"/>
			<input type="button" id="cancelBtn" class="btn btn-outline-danger btn-sm" value="취소" style="margin-bottom: 5px;" onclick="memoReset()"/>
			<br />
			<textarea rows="5" cols="120" name="content" id="content"></textarea>
		</form>
	</div>
	
</body>
</html>