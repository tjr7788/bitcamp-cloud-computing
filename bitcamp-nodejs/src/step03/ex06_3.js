// 주제: 코드를 모듈로 분리 - 요청 핸들러를 관리하는 코드 분리


const express = require('express');
const app = express();

const handlebars = require('handlebars');

// 템플릿 소스를 정의한다.
var templateSrc = 
'<html>\
<head>\
<title>테스트</title>\
</head>\
<body>\
<h1>환영합니다!</h1>\
<p>{{name}}님 안녕하세요!</p>\
</body>\
</html>';

// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn = handlebars.compile(templateSrc);

app.get('/hello', (req, res) => {
    var resultStr = templateFn(req.query);
    res.writeHead(200, {
        'Content-Type' : 'text/html;charset=UTF-8' 
     });
    res.write(resultStr);
    res.end();
});

app.listen(8000, () => {
    console.log('서버연결됨');
});






