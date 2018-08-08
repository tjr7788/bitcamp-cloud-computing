


const http = require('http')

const server = http.createServer((req, res) => {
    console.log('요청받았음');
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain; charset=UTF-8' 
    });
    res.write(req.url);
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
