

const http = require('http')
const url = require('url')
const mysql = require('mysql');

var pool = mysql.createPool({
    connectionLimit: 10,
    host: '52.79.234.169',
    //port: '3306',  3306이면 생략가능
    database: 'studydb',
    user: 'study',
    password: '1111'
});

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
    if (urlInfo.pathname !== '/member/list') {
        res.end('해당 URL을 지원하지 않습니다.\n');
        return;
    }
    var pageNo = 1;
    var pageSize = 3;
    if (urlInfo.query.pageNo)
        pageNo = parseInt(urlInfo.query.pageNo);
    if (urlInfo.query.pageSize)
        pageSize = parseInt(urlInfo.query.pageSize);
    var startIndex = (pageNo - 1) * pageSize;
    
    pool.query('select * from pms2_member limit ?, ?',
            [startIndex, pageSize],
            function(err, results) {
        if (err) {
            res.end('DB 조회 중 예외 발생!')
            return;
        }
        for (var row of results)
            res.write(`${row.email}, ${row.mid}\n`);
        
        res.end();
    });
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});
