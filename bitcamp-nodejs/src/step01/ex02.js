const http = require('http');

const server = http.createServer((req, res) => {
    console.log("클라이언트가 요청하였음");
    res.end();
});

server.listen(8000, () => {
    console.log("서버 실행 중...");
});