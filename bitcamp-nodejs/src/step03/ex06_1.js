// 주제: 코드를 모듈로 분리 - 요청 핸들러를 관리하는 코드 분리


const express = require('express');
const app = express();

const handlebars = require('handlebars');

// 템플릿 소스를 정의한다.
var templateSrc = '{{name}}님 안녕하세요!';

// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어 낼 함수를 준비한다.
var templateFn = handlebars.compile(templateSrc);

var resultStr = templateFn({name: '홍길동'});
console.log(resultStr);







