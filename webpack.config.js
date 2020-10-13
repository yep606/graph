const path = require('path');

module.exports = {
    entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'app.js'),
    output: {
        filename: 'main.js',
        path: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
    },
};