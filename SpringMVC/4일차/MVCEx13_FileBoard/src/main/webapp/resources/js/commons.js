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
