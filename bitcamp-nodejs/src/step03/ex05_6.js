// 주제: 코드를 모듈로 분리 - 요청 핸들러를 관리하는 코드 분리


const mysql = require('mysql')
const app = require('express')();

var pool = mysql.createPool({
    connectionLimit: 10,
    host: '52.79.234.169', 
    //port: '3306',
    database: 'studydb',
    user: 'study',
    password: '1111'
});


app.get('/member/list', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain; charset=UTF-8' 
     });
    var pageNo = 1;
    var pageSize = 3;
    
    if (req.query.pageNo) {
        pageNo = parseInt(req.query.pageNo)
    }
    if (req.query.pageSize) {
        pageSize = parseInt(req.query.pageSize)
    }
    
    var startIndex = (pageNo - 1) * pageSize;
    
    pool.query('select mid, email from pms2_member limit ?, ?',
            [startIndex, pageSize],
            function(err, results) {
        if (err) {
            res.end('DB 조회 중 예외 발생!')
            return;
        }
        
        for (var row of results) {
            res.write(`${row.email}, ${row.mid}\n`);
        }
        res.end();
    });
});

app.get('/member/add', (req, res) => {
    
    res.writeHead(200, {
        'Content-Type' : 'text/plain; charset=UTF-8' 
     });
    
    pool.query(
            'insert into pms2_member(mid,email,pwd)\
            values(?, ?, password(?))',
        [req.query.id, req.query.email, req.query.password],
        function(err, results) {
            if (err) {
                res.end('데이터 처리 중 예외 발생!')
                return;
            }
            
            res.write('등록성공!\n')
            res.end();
    });
});

app.get('/member/update', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain; charset=UTF-8' 
     });
    pool.query(
            'update pms2_member set\
             email=?,\
             pwd=?\
             where mid=?',
        [req.query.email,
         req.query.password,
         req.query.id],
        function(err, results) {
            if (err) {
                res.end('DB 조회 중 예외 발생!')
                return;
            }
            
            res.write('변경 성공!')
            res.end();
    });
});

app.get('/member/delete', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain; charset=UTF-8' 
     });
    pool.query('delete from pms2_member where mid=?',
        [req.query.id],
        function(err, results) {
            if (err) {
                res.end('DB 조회 중 예외 발생!')
                return;
            }
            
            res.write('삭제 성공!')
            res.end();
    });
});


app.get('/hello', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain; charset=UTF-8' 
     });
    res.write(`${urlInfo.query.name}님 안녕하세요!`);
    res.end();
});

app.listen(8000, () => {
    console.log('서버연결됨');
});






