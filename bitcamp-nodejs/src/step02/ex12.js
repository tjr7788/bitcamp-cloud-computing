const mysql = require('mysql');

var pool = mysql.createPool({
    connectionLimit: 10,
    host: '52.79.234.169',
    //port: '3306',  3306이면 생략가능
    database: 'studydb',
    user: 'study',
    password: '1111'
});

pool.query('select * from pms2_member',  
        function(err, results) {
    if (err) throw err;
    for (var row of results)
        console.log(row);
    
    pool.end();
});

console.log('연결 테스트!'); 