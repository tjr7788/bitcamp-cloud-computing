const mysql = require('mysql');

console.log(mysql);


var pool = mysql.createPool({
    connectionLimit: 10,
    host: '52.79.234.169',
    //port: '3306',  3306이면 생략가능
    database: 'studydb',
    user: 'study',
    password: '1111'
});


pool.getConnection(function(err, con) {
    if (err) throw err;
    
    console.log('연결객체를 얻었습니다.');
    
    var email = 'user120@122';
    var mid = "user01' or 1=1 or ''='";
    //var mid = 'user01'
    var pwd = '1111';
    con.query('select * from pms2_member',  
            function(err, results) {
        if (err) throw err;
        for (var row of results)
            console.log(row);
    });
    con.release();
    pool.end(function(err) {
        console.log('커넥션 풀에 있는 모든 커넥션을 닫습니다.')
    });
});

console.log('연결 테스트!'); 