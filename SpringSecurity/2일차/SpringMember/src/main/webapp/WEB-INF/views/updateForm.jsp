<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<%-- 다음 우편번호 API --%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	// 아이디 입력시 Ajax를 호출하여 아이디 중복확인하기	
	$("#userid").keyup(function() {
		var value = $(this).val();
		if(value!=null && value.length>=4){
			// alert(value);
			$.ajax("idCheck", {
				type : "GET",
				data : {"userid":value},
				success : function(data){
					//alert(typeof(data) + "\n" + data )
					if(data=='0'){
						$("#msg").html("&nbsp;&nbsp;사용가능").css('color','blue');
					}else{
						$("#msg").html("&nbsp;&nbsp;사용불가능").css('color','red');
					}
				},
				error : function(){
					alert('에러다!!!!')
				}
			});
		}else{
			$("#msg").html("").css('color','black');
		}
	});
});

function daumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address2").focus();
        }
    }).open();
}

// 폼검증하는 자바스크립트 함수
function formCheck(){
	var value = $("#password").val();
	if(value==null || value.trim().length==0){
		alert('사용자 비빌번호는 반드시 입력해야 합니다.');
		$("#password").val("");
		$("#password").focus();
		return false;
	}
	var value = $("#username").val();
	if(value==null || value.trim().length==0){
		alert('사용자 이름은 반드시 입력해야 합니다.');
		$("#username").val("");
		$("#username").focus();
		return false;
	}
	var value = $("#nickname").val();
	if(value==null || value.trim().length==0){
		alert('사용자 별명은 반드시 입력해야 합니다.');
		$("#nickname").val("");
		$("#nickname").focus();
		return false;
	}
	var value = $("#email").val();
	if(value==null || value.trim().length==0){
		alert('이메일 주소는 반드시 입력해야 합니다.');
		$("#email").val("");
		$("#email").focus();
		return false;
	}
	var value = $("#hp").val();
	if(value==null || value.trim().length==0){
		alert('전화번호는 반드시 입력해야 합니다.');
		$("#hp").val("");
		$("#hp").focus();
		return false;
	}
	var value = $("#zipcode").val();
	if(value==null || value.trim().length==0){
		alert('우편번호는 반드시 입력해야 합니다.');
		$("#zipcode").val("");
		$("#zipcode").focus();
		return false;
	}
	var value = $("#address2").val();
	if(value==null || value.trim().length==0){
		alert('상세 주소는 반드시 입력해야 합니다.');
		$("#address2").val("");
		$("#address2").focus();
		return false;
	}
}
</script>

<style type="text/css">
	.title { font-size: 18pt;text-align: center; padding: 10px; font-weight: bold;}
</style>
</head>
<body>
	<div class="container" style="border: 1px solid gray;padding: 15px;margin-top: 30px;border-radius: 30px;">
		<form action="updateOk" method="post" onsubmit="return formCheck();">
			<div class="title" >회원 정보 수정</div>
			<div class="mb-3 row">
				<%-- idx는 숨겨서 넘기자 --%>
				<input type="hidden" name="idx" value="${mvo.idx }" />
				
			  	<label for="userid" class="col-sm-2 col-form-label">사용자 아이디</label>
			  	<div class="col-sm-2">
				<input type="text" class="form-control" id="userid" name="userid" readonly value="${mvo.userid }">
				</div>
				<div class="col-sm-1 col-form-label" id="msg"></div>
				<div class="col-sm-2"></div>
			  	<label for="password" class="col-sm-2 col-form-label">사용자 비밀번호</label>
			  	<div class="col-sm-3">
				<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호입력"  required>
				</div>
			</div>
			<div class="mb-3 row">
			  	<label for="username" class="col-sm-2 col-form-label">사용자 이름</label>
			  	<div class="col-sm-3">
				<input type="text" class="form-control" id="username" name="username" value="${sessionScope.mvo.username }" required>
				</div>
				<div class="col-sm-2"></div>
			  	<label for="nickname" class="col-sm-2 col-form-label">사용자 별명</label>
			  	<div class="col-sm-3">
				<input type="text" class="form-control" id="nickname" name="nickname" value="${sessionScope.mvo.nickname }" required>
				</div>
			</div>
			<div class="mb-3 row">
			  	<label for="email" class="col-sm-2 col-form-label">사용자 이메일</label>
			  	<div class="col-sm-3">
				<input type="email" class="form-control" id="email" name="email" value="${sessionScope.mvo.email }"  readonly>
				</div>
				<div class="col-sm-2"></div>
			  	<label for="hp" class="col-sm-2 col-form-label">사용자 전화번호</label>
			  	<div class="col-sm-3">
				<input type="tel" class="form-control" id="hp" name="hp" value="${sessionScope.mvo.hp }"  required>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-2">
			  	  <label for="gender1" class="col-sm-2 col-form-label">남자</label>
				  <input class="form-check-input" type="radio" name="gender" id="gender1" value="M" ${mvo.gender=='M' ? "checked" : ""}>
				</div>
				<div class="col-sm-2">
			  	  <label for="gender2" class="col-sm-2 col-form-label">여자</label>
				  <input class="form-check-input" type="radio" name="gender" id="gender2" value="F" ${mvo.gender=='F' ? "checked" : ""}>
				 </div> 
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label" for="zipcode">우편번호</label>
				<div class="col-sm-2">
				  <input class="form-control" type="text" name="zipcode" id="zipcode" readonly  required value="${mvo.zipcode }">
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
				  <input type="button" class="btn-check" id="zipCodebtn" onclick="daumPostcode();">
				  <label class="btn btn-outline-primary" for="zipCodebtn">우편번호찾기</label>
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label" for="address1">주소</label>
				<div class="col-sm-10">
				  <input class="form-control" type="text" name="address1" id="address1" readonly  required value="${mvo.address1 }">
				</div>
			</div>
			<div class="mb-3 row">
				<label class="col-sm-2 col-form-label" for="address2">상세주소</label>
				<div class="col-sm-10">
				  <input class="form-control" type="text" name="address2" id="address2"  required  value="${mvo.address2 }">
				</div>
			</div>
			<div class="mb-3 row">
				<div class="col-sm-12" style="text-align: right;">
					  <!-- 시큐리트에서 사용자가 지정한 폼을 사용하려면 반드시 아래의 코드를 첨부해줘야 한다.-->
					  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					  <input type="submit" class="btn-check" id="submitBtn" >
					  <label class="btn btn-outline-success" for="submitBtn">정보수정</label>
					  <input type="reset" class="btn-check" id="resetBtn" >
					  <label class="btn btn-outline-success" for="resetBtn">다시쓰기</label>
					  <input type="button" class="btn-check" id="cancelBtn" onclick="location.href='${pageContext.request.contextPath}'">
					  <label class="btn btn-outline-success" for="cancelBtn">돌아가기</label>
				</div>
			</div>
		</form>
	</div>
</body>
</html>