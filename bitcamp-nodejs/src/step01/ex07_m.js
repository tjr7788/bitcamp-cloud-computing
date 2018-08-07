module.exports = function() {
    var result = 0;
    return {
        plus(value) {result += value},
        minus(value) {result -= value},
        multiple(value) {result *= value},
        divide(value) {result /= value},
        getResult() {return result}
    };
}