console.log(__dirname);
console.log(__filename);

var path = require('path');

console.log(path.join('c:/apps', '/aaa', '/bbb', '/okok.js'));

console.log(path.join(__dirname, 'ex12_m.js'));

console.log(path.join(__dirname, '../step02/ex12_m.js'));