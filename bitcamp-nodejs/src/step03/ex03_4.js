

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
    if (urlInfo.pathname === '/board/list') {
        res.write('게시물 목록\n')
    } else if (urlInfo.pathname === '/board/add') {
        res.write('게시물 등록\n')
    } else {
        res.write('해당 URL을 지원하지 않습니다.\n')
    }
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
