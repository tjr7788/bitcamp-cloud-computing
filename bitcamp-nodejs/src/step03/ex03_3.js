

const http = require('http')
const url = require('url')

const server = http.createServer((req, res) => {
    console.log('요청받았음');
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain; charset=UTF-8' 
    });
    res.write(req.url + '\n');
    
    var urlInfo = url.parse(req.url, true);
    
    res.write(`name= ${urlInfo.query.name}\n`);
    res.write(`age=${urlInfo.query.age}\n`);
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
