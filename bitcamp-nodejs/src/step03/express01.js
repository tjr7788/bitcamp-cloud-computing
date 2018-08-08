module.exports = {
    reqMap: {},
    add(url, handler) {
        this.reqMap[url] = handler;
    },
    getHandler(url) {
        return this.reqMap[url];
    }
};
