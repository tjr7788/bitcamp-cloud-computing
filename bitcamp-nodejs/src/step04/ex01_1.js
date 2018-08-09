

const express = require('express')

const app = express();



app.get('/test01', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/html;charset=UTF-8' 
     });
    res.write('testok');
    res.end();
});

app.listen(8000, () => {
    console.log('서버연결됨');
});

