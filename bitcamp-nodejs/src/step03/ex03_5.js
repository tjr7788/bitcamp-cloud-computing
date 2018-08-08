

const http = require('http')
const url = require('url')

const server = http.createServer((req, res) => {
    var urlInfo = url.parse(req.url, true);
    
    if (urlInfo.pathname === '/favicon.ico') {
        res.end();
        return;
    }
    
    console.log('요청받았슴');
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain; charset=UTF-8' 
    });
    if (urlInfo.pathname === '/calc') {
        var a = parseInt(urlInfo.query.a);
        var b = parseInt(urlInfo.query.b);
        var op = urlInfo.query.op;
        var result = 0;
        switch(op) {
        case '+': result = a + b; break;
        case '-': result = a - b; break;
        case '*': result = a * b; break;
        case '/': result = a / b; break;
        default:
            res.write('해당 계산이없음.\n');
            res.end();
            return;
        } 
        res.write(`${a} ${op} ${b} = ${result}`);
        
    } else {
        res.end('해당 URL을 지원하지 않습니다.\n');
        return;
    }
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
