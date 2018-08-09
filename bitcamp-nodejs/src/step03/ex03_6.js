
const http = require('http')
const url = require('url')
const querystring = require('querystring')

const server = http.createServer((req, res) => {
    var urlInfo = url.parse(req.url, true);
    
    if (urlInfo.pathname !== '/') {
        res.end();
        return;
    }
    
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain; charset=UTF-8' 
    });
    
    if (req.method !== 'POST') {
        res.end('GET 요청을 지원하지 않습니다.');
        return;
    }
    
    var data = '';
    
    req.on('data', (chunk) => {
        data += chunk;
    });
    
    req.on('end', (chunk) => {
        
        var params = querystring.parse(data);
        res.write(`name=${params.name}\n`);
        res.write(`age=${params.age}`);
        res.end();
    });
    
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
