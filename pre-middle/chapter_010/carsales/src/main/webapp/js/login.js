'use strict';

// app Vue instance
var app = new Vue({
    // app initial state
    data: {
        form: {
            email: '',
            passw: ''
        }
    }
})
// mount
app.$mount('.loginapp')
