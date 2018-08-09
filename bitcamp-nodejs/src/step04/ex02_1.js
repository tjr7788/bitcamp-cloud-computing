

const express = require('express');
const app = express();


const bodyParser = require('body-parser')

const postParameterParser = bodyParser.urlencoded({extended: false})

app.use(postParameterParser);

app.get('/test01', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain;charset=UTF-8' 
     });
    res.write(`name=${req.query.name}\n`);
    res.write(`age=${req.query.age}\n`);
    res.end();
});

app.post('/test02', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain;charset=UTF-8' 
     });
    res.write(`name=${req.body.name}\n`);
    res.write(`age=${req.body.age}\n`);
    res.end();
});


app.listen(8000, () => {
    console.log('서버연결됨');
});

