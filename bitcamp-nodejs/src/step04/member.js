
const express = require('express');

const router = express.Router();

router.use((req, res, next) => {
    console.log("멤버실행됨");
})

router.get('/list', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain;charset=UTF-8' 
     });
    res.write('회원 목록입니다.');
    res.end();
});

router.get('/view', (req, res) => {
    res.writeHead(200, {
        'Content-Type' : 'text/plain;charset=UTF-8' 
     });
    res.write('회원 상세정보입니다.');
    res.end();
});

module.exports = router;