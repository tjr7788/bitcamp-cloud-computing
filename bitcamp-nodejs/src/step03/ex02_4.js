


const http = require('http')

const server = http.createServer((req, res) => {
    res.writeHead(200, {
       'Content-Type' : 'text/html; charset=UTF-8' 
    });
    res.write('<html>\n\
<head>\n\
  <title>nodejs</title>\n\
</head>\n\
<body>\n\
  <h1>하이</h1>\n\
</body>\n\
</html>\n');
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
