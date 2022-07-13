/**
 * @param action - url
 * @param params - JSON
 * @returns
 */
function sendPost(url, params) {
    var form = document.createElement('form');
    form.setAttribute('method', 'post'); //POST 메서드 적용
    form.setAttribute('action', url);	// 데이터를 전송할 url
    document.charset = "utf-8";
    for ( var key in params) {	// key, value로 이루어진 객체 params
        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden'); //값 입력
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', params[key]);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();	// 전송~
}
// 문자열 모두 바꾸기 : (원본, 찾을문자열,바꿀문자열)
function replaceAll(str, searchStr, replaceStr) {
   return str.split(searchStr).join(replaceStr);
}
// 이메일 검증하는 함수
function verifyEmail() { 
	var emailVal = $("#email").val(); 
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; 
	// 검증에 사용할 정규식 변수 regExp에 저장 
	return (emailVal.match(regExp) != null);
}


