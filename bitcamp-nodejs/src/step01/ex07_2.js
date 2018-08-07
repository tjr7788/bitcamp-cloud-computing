var fn = require('./ex07_m');

var obj1 = fn();
var obj2 = fn();

obj1.plus(100);
obj1.minus(7);
console.log(obj1.getResult());


obj2.plus(100);
obj2.multiple(7);
console.log(obj2.getResult());