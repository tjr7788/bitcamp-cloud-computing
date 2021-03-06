////다합치기

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


const express = function() {
    reqMap = {},
    add(url, handler) {
        this.reqMap[url] = handler;
    },
    getHandler(url) {
        return this.reqMap[url];
    }
};

const server = http.createServer((req, res) => {
    var urlInfo = url.parse(req.url, true);
    
    if (urlInfo.pathname === '/favicon.ico') {
        res.end();
        return;
    }
    
    console.log('요청받았슴');
    
    res.writeHead(200, {
       'Content-Type' : 'text/plain;charset=UTF-8' 
    });
    var handler = express.getHandler(urlInfo.pathname);
    
    if (handler) {
        handelr(urlInfo, req, res)
    } else {
        res.end('해당 url이 존재하지않습니다.');
        return;
    }
});

server.listen(8000, () => {
    console.log('서버가 실행됨');
});

express.add('/member/list', (urlInfo, req, res) => {
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
express.add('/member/add', (urlInfo, req, res) => {
    var id = urlInfo.query.id;
    var email = urlInfo.query.email;
    var password = urlInfo.query.password;
    pool.query('insert into pms2_member(mid, email, pwd) values(?, ?, password(?))',
            [id, email, password],
        function(err, result) {
            if (err) {
                console.log(err);
                res.end('DB 조회 중 예외 발생!')
                return;
            }
        res.write('등록성공!');
        
        res.end();
    });
});
express.add('/member/update', (urlInfo, req, res) => {
    var id = urlInfo.query.id;
    var email = urlInfo.query.email;
    var password = urlInfo.query.password;
    pool.query('update pms2_member set email=?, pwd=? where mid=?',
            [email, password, id],
    function(err, results) {
        if (err) {
            res.end('DB 조회 중 예외 발생!')
            return;
        }
        res.write('변경성공');
        
        res.end();
    });
});
express.add('/member/delete', (urlInfo, req, res) => {
    var id = urlInfo.query.id;
    pool.query('delete from pms2_member where mid=?',
            [id],
            function(err, results) {
        if (err) {
            res.end('DB 조회 중 예외 발생!')
            return;
        }
        res.write('삭제성공');
        
        res.end();
    });
});
