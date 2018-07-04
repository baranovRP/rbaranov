<template>
  <b-container fluid>
    <b-row class="main">
      <b-form-select id="gaugeId"
                     :options="bunch"
                     v-model="visibility"
                     @change="onChange($event)"
      />
    </b-row>
    <b-row class="main" v-show="posts && posts.length" v-cloak>
      <b-list-group class="post-list">
        <job4j-post v-for="post in filteredPosts" :key="post.id"
                    :post="post"></job4j-post>
      </b-list-group>
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios'
import Post from './Post.vue'

import { ALL, POSTED_TODAY, POSTS_WITH_PICS } from '../constants.js'

export default {
  props: ['post'],
  data () {
    return {
      posts: [],
      bunch: [ALL, POSTED_TODAY, POSTS_WITH_PICS],
      visibility: ALL
    }
  },
  components: {
    'job4j-post': Post
  },
  beforeCreate () {
    axios.get('/metadata')
      .then(res => {
        console.log(res)
        let manufactures = res.data.manufactures.map(m => m.name) || []
        this.bunch = this.bunch.concat(manufactures.reverse())
        console.log(this.bunch)
      })
      .catch(error => console.log(error))
    axios.get('/list')
      .then(res => {
        console.log(res)
        const [...posts] = res.data
        this.posts = posts

        console.log(posts)
      })
      .catch(error => console.log(error))
  },
  computed: {
    filteredPosts: function () {
      let posts
      const visibility = this.visibility.toLowerCase()

      switch (visibility) {
        case POSTED_TODAY.toLowerCase():
          posts = this.posts.filter(p => {
            const pDate = new Date(p.publishDate).toDateString()
            const today = new Date().toDateString()
            return pDate === today
          })
          break
        case POSTS_WITH_PICS.toLowerCase():
          posts = this.posts.filter(p => p.pictures.length !== 0)
          break
        case ALL.toLowerCase():
          posts = this.posts
          break
        default:
          posts = this.posts.filter(p =>
            visibility === p.car.carModel.manufacture.name.toLowerCase())
      }
      console.log(posts)
      return posts || []
    }
  },
  methods: {
    onChange (value) {
      this.visibility = value
      console.log(`visibility: ${this.visibility}`)
    }
  }
}
</script>

<style scoped>
  .main {
    position: relative;
    z-index: 2;
    border-top: 1px solid #e6e6e6;
    padding-left: 8px;
    padding-right: 8px;
    max-width: 800px;
    min-width: 230px;
    margin: 0 auto;
  }

  .post-list {
    list-style: none;
  }
</style>
