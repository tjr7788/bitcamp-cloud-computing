

const http = require('http')
const url = require('url')

const server = http.createServer((req, res) => {
    console.log('요청받았음');
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain; charset=UTF-8' 
    });
    res.write(req.url + '\n');
    
    var urlInfo = url.parse(req.url);
    
    res.write(`href= ${urlInfo.href}\n`);
    res.write(`pathname=${urlInfo.pathname}\n`);
    res.write(`search=${urlInfo.search}\n`);
    res.write(`query=${urlInfo.query}\n`);
    res.write('테스트');
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
