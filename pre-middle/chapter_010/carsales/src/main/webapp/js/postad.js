'use strict';

var postStorage = {
    fetch(that) {
        fetch('postad').then(function (response) {
            return response.json()
        }).then(function (result) {
            that.manufactures = result.manufactures || []
            that.carmodels = result.carmodels || []
            that.categories = result.categories || []
            that.bodies = result.bodies || []
            that.fuels = result.fuels || []
            that.transmissions = result.transmissions || []
        }).catch(function (err) {
            return console.error(err);
        })
    },
    postad(formdata) {
        var request = new XMLHttpRequest();
        request.open('POST', 'postad');
        request.send(formdata);
        // fetch('postad', {
        //     method: 'POST',
        //     headers: {
        //         'Accept': 'application/json, application/xml, text/plain, text/html, *.*',
        //         'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'
        //     },
        //     body: formdata
        // }).then(function (data) {
        //     return console.log(data);
        // }).catch(function (err) {
        //     return console.error(err);
        // })
    }
}

// app Vue instance
var app = new Vue({
    // app initial state
    data: {
        selectedManufacture: 1,
        manufactures: [],
        carmodels: [],
        categories: [],
        bodies: [],
        fuels: [],
        transmissions: [],
        postad: {
            content: '',
            price: 0.0,
            isActive: true,
            car: {
                body: {
                    id: 1
                },
                category: {
                    id: 1
                },
                fuel: {
                    id: 1
                },
                carModel: {
                    id: 1,
                    manufacture: {
                        id: 1
                    }
                },
                transmission: {
                    id: 1
                },
                engineSize: 0.0,
                mileage: 0,
                year: 2015
            },
            user: {
                email: '',
                phone: ''
            }
        },
        pictures: []
    },

    created() {
        var that = this
        postStorage.fetch(that);
    },

    computed: {
        models: {
            get() {
                var that = this
                return this.carmodels.filter(function (c) {
                    if (c.manufacture.id == that.selectedManufacture) {
                        return c
                    }
                })
            },
            set(event) {
                // this.models = this.models
            }
        }
    },

    methods: {
        onChange(e) {
            this.selectedManufacture = event.srcElement.value
        },
        onSubmit(e) {
            event.preventDefault();
            var formdata = new FormData()
            // this.pictures.forEach(function (value) {
            //     formdata.append(value.name, value)
            // })
            this.pictures.forEach(function (value, idx) {
                formdata.append("pic" + idx, value, value.name)
            })
            formdata.append("postad", JSON.stringify(this.postad))
            postStorage.postad(formdata)
        }
    },

    filters: {
        pluralize: function (n) {
            return n === 1 ? 'item' : 'items'
        }
    },

    // a custom directive to wait for the DOM to be updated
    // before focusing on the input field.
    directives: {
        'post-focus': function (el, binding) {
            if (binding.value) {
                el.focus()
            }
        }
    }
})

function getBase64Image(img) {
    var canvas = document.createElement('canvas');
    canvas.width = img.width;
    canvas.height = img.height;

    var ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0);

    var dataURL = canvas.toDataURL('image/png');

    return dataURL;
}

// handle routing
function onHashChange() {
    var visibility = window.location.hash.replace(/#\/?/, '')
    // if (filters[visibility]) {
    //     app.visibility = visibility
    // } else {
    //     window.location.hash = ''
    //     app.visibility = 'all'
    // }
}

window.addEventListener('hashchange', onHashChange)
onHashChange()

// mount
app.$mount('.carsalespostadd')
