'use strict';

var postStorage = {
    fetch: function (that) {
        fetch('list').then(function (response) {
            return response.json()
        }).then(function (result) {
            that.posts = result || []
        }).catch(function (err) {
            return console.error(err);
        })
    },
    edit: function (posts) {
        fetch('edit', {
            method: 'POST',
            body: JSON.stringify(posts)
        }).then(function (data) {
            return console.log(data);
        }).catch(function (err) {
            return console.error(err);
        })
    },
    remove: function (post) {
        fetch('remove', {
            method: 'POST',
            body: JSON.stringify(post)
        }).then(function (data) {
            return console.log(data);
        }).catch(function (err) {
            return console.error(err);
        })
    },
    add: function (post) {
        fetch('postad', {
            method: 'GET',
            body: JSON.stringify(post)
        }).then(function (data) {
            return console.log(data);
        }).catch(function (err) {
            return console.error(err);
        })
    }
}

// visibility filters
var filters = {
    all: function (posts) {
        return posts
    },
    active: function (posts) {
        return posts.filter(function (post) {
            return !post.done
        })
    },
    done: function (posts) {
        return posts.filter(function (post) {
            return post.done
        })
    }
}

// app Vue instance
var app = new Vue({
    // app initial state
    data: {
        posts: [],
        newPost: '',
        editedPost: null,
        visibility: 'all'
    },

    created: function () {
        var that = this
        postStorage.fetch(that);
    },

    computed: {
        filteredPosts: function () {
            return filters[this.visibility](this.posts)
        },
        remaining: function () {
            return filters.active(this.posts).length
        },
        allDone: {
            get: function () {
                return this.remaining === 0
            },
            set: function (value) {
                this.posts.forEach(function (post) {
                    post.done = value
                })
                var that = this
                postStorage.edit(this.posts)
                postStorage.fetch(that)
            }
        }
    },

    filters: {
        pluralize: function (n) {
            return n === 1 ? 'item' : 'items'
        }
    },

    methods: {
        addPost: function () {
            var value = this.newPost && this.newPost.trim()
            if (!value) {
                return
            }
            var that = this
            postStorage.add({
                id: null,
                description: value,
                done: false
            })
            postStorage.fetch(that)
            this.newPost = ''
        },

        removePost: function (post) {
            var that = this
            postStorage.remove(post)
            postStorage.fetch(that)
        },

        editPost: function (post) {
            this.beforeEditCache = post.description
            this.editedPost = post
            postStorage.edit([post])
            var that = this
            postStorage.fetch(that)
        },

        doneEdit: function (post) {
            if (!this.editedPost) {
                return
            }
            this.editedPost = null
            post.description = post.description.trim()
            if (!post.description) {
                this.removePost(post)
            }
            postStorage.edit([post])
            var that = this
            postStorage.fetch(that)
        },

        carSold: function (post) {
            post.done = !post.done
            postStorage.edit([post])
            var that = this
            postStorage.fetch(that)
        },

        cancelEdit: function (post) {
            this.editedPost = null
            post.description = this.beforeEditCache
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

// handle routing
function onHashChange() {
    var visibility = window.location.hash.replace(/#\/?/, '')
    if (filters[visibility]) {
        app.visibility = visibility
    } else {
        window.location.hash = ''
        app.visibility = 'all'
    }
}

window.addEventListener('hashchange', onHashChange)
onHashChange()

// mount
app.$mount('.carsalesapp')
