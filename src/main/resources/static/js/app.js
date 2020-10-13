// import Vue from 'vue'
// import axios from 'axios'

var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    },
    mounted () {
        axios
            .get('localhost:8080/task')
            .then(response => (this.message = response.text))
    }
})