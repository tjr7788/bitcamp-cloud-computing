
const express = require('express');
const app = express();


const bodyParser = require('body-parser')
const memberRouter = require('./member')
const teamRouter = require('./team')

app.use('/member', memberRouter)
app.use('/team', teamRouter)

const postParameterParser = bodyParser.urlencoded({extended: false})

app.use(postParameterParser);

app.use(express.static('public'))
const path = require('path');
const consolidate = require('consolidate');

app.engine('html', consolidate.handlebars);

app.set('view engine', 'html')

app.set('views', path.join(__dirname, 'temples');

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

app.post('/test032', (req, res) => {
});

app.listen(8000, () => {
    console.log('서버연결됨');
});

