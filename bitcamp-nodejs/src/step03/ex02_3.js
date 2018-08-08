


const http = require('http')

const server = http.createServer((req, res) => {
    res.writeHead(200, {
       'Content-Type' : 'text/html; charset=UTF-8' 
    });
    res.write('<html>');
    res.write('<head>');
    res.write('<title>nodejs</title>');
    res.write('</head>');
    res.write('<body>');
    res.write('<h1>하이</h1>');
    res.write('</body>');
    res.write('</html>');
    res.end();
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
