<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 Ver 0.9</title>
<%-- axicon 사용하기 --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/resources/axicon/axicon.min.css" />

<link href="${pageContext.request.contextPath }/webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/webjars/jquery/3.6.0/dist/jquery.js"></script>
<%-- 사용자 정의 자바스크립트 함수를 추가한다. --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/commons.js"></script>
<script type="text/javascript">
	$(function() {
		$(".content").css('display', 'none');
		// 펼치기/접기버튼 이벤트 지정
		$("i.axi").click(function(){
			//alert($(this).parent().next().css('display'));
			if($(this).parent().next().css('display') == 'none'){
				$(this).parent().next().slideDown(500) // 내용보이기
				$(this).html("접기") // 글자 변경
				$(this).attr("class", "axi axi-ion-chevron-up") // 아이콘 변경
			}else{
				$(this).parent().next().slideUp(500);
				$(this).html("펼치기")
				$(this).attr("class", "axi axi-ion-chevron-down")
			}
		});
		
		$("button.delForm").click(function() {
			if ($(this).next().css('display') == 'inline') {
				$(this).next().css('display','none');
				$(this).parent().next().slideUp(500);
				$(this).next().next().html('펼치기')
				$(this).next().next().attr("class", "axi axi-ion-chevron-down");
			}else{
				$(this).next().css('display','inline');
				$(this).parent().next().slideDown(500)
				$(this).next().next().html('접기')
				$(this).next().next().attr("class", "axi axi-ion-chevron-up");
			}
		});
		
		$("button.replyForm").click(function() {
			if ($(this).parent().next().css('display') == 'block') {
				$(this).parent().next().slideUp(500)
			} else {
				$(this).parent().next().slideDown(500);
				$(this).parent().next().next().slideUp(500);
			}
		});
		
		$("button.updateForm").click(function() {
			if ($(this).parent().next().next().css('display') == 'block') {
				$(this).parent().next().next().slideUp(500)
			} else {
				$(this).parent().next().next().slideDown(500);
				$(this).parent().next().slideUp(500);
			}
		});
		
		$("[value='취소']").click(function() {
			$(this).parent().parent().slideUp(500);
		});
	});
	
	// 삭제 확인버튼 클릭시 이벤트 지정
	function deleteOk(idx){
		alert(idx + "번글 삭제요청\n" + $("#password"+idx).val());
		var password = $("#password"+idx).val();
		sendPost("delete", {"idx":idx, "password":password});
	}
	// 삭제 취소 버튼 클릭시 이벤트 지정
	function deleteCancel(obj){
		 // alert($(obj).val());
		 $(obj).parent().fadeOut(500);
		 $(obj).parent().parent().next().slideUp(500); // 내용 숨기기
		 $(obj).parent().next().html("펼치기");
		 $(obj).parent().next().attr("class", "axi axi-ion-chevron-down");
	}
</script>
<style type="text/css">
* {font-size: 10pt;}
i.axi:hover{ font-size: 12pt;cursor: pointer;font-weight: bold; color: red;} /* 마우스 오버시 진하게/글자크기 변경*/

div {
	padding: 5px;
}

#content {
	width: 80%;
	margin: auto;
}

#main-title {
	padding: 5px;
	text-align: center;
	font-size: 18pt;
}

#pageinfo {
	padding: 5px;
	text-align: right;
}

#insertForm {
	padding: 5px;
	text-align: right;
	margin-bottom: 15px;
}

.title {
	border: 1px solid gray;
	background-color: silver;
	padding: 5px;
	cursor: pointer;
	border-top-right-radius: 10px;
	border-top-left-radius: 10px;
}

.content {
	border: 1px solid gray;
	padding: 5px;
	border-bottom-right-radius: 10px;
	border-bottom-left-radius: 10px;
}

.reply {
	border: 0px solid gray;
	padding: 5px;
	margin: 5px;
	display: none;
	text-align: right;
}
</style>
</head>
<body>
	<div id="content">
		<div id="main-title">방명록 Ver 0.9</div>
		<div id="pageinfo">${fn:length(list) }개의흔적이 있습니다.</div>
		<!-- 원본글 저장하는 폼 -->
		<div id="insertForm">
			<form action="insert" method="post">
				<input type="text" name="name" id="name" required="required"
					placeholder="이름입력"> <input type="password" name="password"
					id="password" required="required" placeholder="비번입력"> <br />
				<textarea style="margin-top: 10px;" rows="5" cols="60"
					name="content" id="content" required="required" placeholder="내용입력"></textarea>
				<br /> <input type="submit" value="흔적남기기"
					class="btn btn-outline-success btn-sm" /> <input type="reset"
					value="다시쓰기" class="btn btn-outline-success btn-sm" />
			</form>
		</div>
		<hr />
		<!-- 글 목록이 표시되어야 한다. -->
		<c:if test="${empty list }">
			<div style="border: 1px solid gray;">등록된 글이 없습니다.</div>
		</c:if>
		<c:if test="${not empty list }">
			<c:forEach var="vo" items="${list }" varStatus="vs">
				<div style="margin-left:${vo.lev*50}px;">
				<%-- 삭제표시가 되어 있으면 삭제표시된 글이라고 표시한다.--%>
				<c:if test="${vo.del=='Y' }">
					<div class="title" onclick="return false;" style="background-color: gray;color: red;">삭제된 글입니다.</div>
				</c:if>
				<%-- 삭제표시가 되어 있지 않으면 보여준다. --%>
				<c:if test="${vo.del=='N' }">
					<%-- 단계별로 타이틀 색상 변경 --%>
					<div class="title" style="background-color: ${vo.lev==0 ? 'skyblue':vo.lev==1 ? 'orange':vo.lev==2 ? 'pink':'silver' }">
						<c:out value="${vo.name }"/>님이 ${vo.ip }에서 
						<fmt:formatDate value="${vo.regDate }" pattern="yyyy년 MM월 dd일(E) HH:mm:ss"/>에서 남긴글
						
						<%-- 삭제표시를 달아보자 --%>
						<button class="delForm btn btn-outline-success btn-sm">삭제</button>
						<span style="display:none;" onclick="return false;" >
							<input type="password" onfocus="return false;" name="password" id="password${vo.idx }" placeholder="비번입력" />
							<input type="button" value="삭제확인" onclick="deleteOk(${vo.idx})" class="btn btn-outline-danger btn-sm"/>		
							<input type="button" value="삭제취소" onclick="deleteCancel(this)" class="btn btn-outline-danger btn-sm"/>		
						</span>
						<%-- 아이콘에 접기/펼치기 구현 --%>
						<i class="axi axi-ion-chevron-down" style="font-size:10pt;">펼치기</i>
						
					</div>
					<div class="content">
						<div>
						<!-- 여기에 글의 내용을 출력한다. -->
						<c:set var="content" value="${vo.content }"/>
						<%-- 태그 무시 --%>
						<c:set var="content" value="${fn:replace(content,'<','&lt;') }"/>
						<%-- \n을 <br>로 변경 --%>
						<c:set var="content" value="${fn:replace(content, newLine, br ) }"/>
						${content }
						</div>
					
						<div style="text-align: right;">
							<button class="btn btn-outline-danger btn-sm updateForm">수정하기</button>
							<button class="btn btn-outline-danger btn-sm replyForm">답변달기</button>
						</div>
						<div class="reply">
							<form action="reply" method="post">
									<input type="hidden" name="ref" value="${vo.ref }"/>
									<input type="hidden" name="seq" value="${vo.seq }"/>
									<input type="hidden" name="lev" value="${vo.lev }"/>
									<input type="text" name="name" id="name" required="required"
										placeholder="이름입력"> 
									<input type="password" name="password"
										id="password" required="required" placeholder="비번입력"> <br />
									<textarea style="margin-top: 10px;" rows="3" cols="40"
										name="content" id="content" required="required" placeholder="내용입력"></textarea>
									<br /> 
									<input type="submit" value="답변쓰기"
										class="btn btn-outline-success btn-sm" /> 
									<input type="reset"
										value="다시쓰기" class="btn btn-outline-success btn-sm" /> 
									<input type="button" class="btn btn-outline-success btn-sm" value="취소" />
							</form>
						</div>
						<div class="reply">
							<form action="update" method="post">
									<input type="hidden" name="idx" value="${vo.idx }"/>
									<input type="text" name="name" id="name" readonly="readonly"
										value="${vo.name }"> 
									<input type="password" name="password"
										id="password" required="required" placeholder="비번입력"> <br />
									<textarea style="margin-top: 10px;" rows="3" cols="40"
										name="content" id="content" required="required" placeholder="내용입력">${vo.content }</textarea>
									<br /> 
									<input type="submit" value="글수정"
										class="btn btn-outline-success btn-sm" /> 
									<input type="reset"
										value="다시쓰기" class="btn btn-outline-success btn-sm" /> 
									<input type="button" class="btn btn-outline-success btn-sm" value="취소" />
							</form>
						</div>
					</div>
				</c:if>
				</div>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>