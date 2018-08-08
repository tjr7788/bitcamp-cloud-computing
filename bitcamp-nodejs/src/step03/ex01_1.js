



const http = require('http')

const server = http.createServer((req, res) => {
    console.log('요청 받았음!')
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});