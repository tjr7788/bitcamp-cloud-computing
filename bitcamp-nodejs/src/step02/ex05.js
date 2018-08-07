const mysql = require('mysql');

console.log(mysql);


var con = mysql.createConnection({
    host: '52.79.234.169',
    //port: '3306',  3306이면 생략가능
    database: 'studydb',
    user: 'study',
    password: '1111'
});

con.connect(function(err) {
    if (err) throw err;
    
    console.log('연결성공입니다.');
    
    con.query('select * from pms2_member', function(err, results) {
        if (err) throw err;
        
        for (var row of results) {
            console.log(row.mid, row.email, row.pwd);
            
        }
    });
});


con.end(function(err) {
    if (err) throw err;
    console.log('연결끊음');
});
console.log('연결 테스트!'); 