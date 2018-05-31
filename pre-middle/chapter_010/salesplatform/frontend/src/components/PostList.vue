<template>
  <b-container fluid>
    <b-row class="main" v-show="posts && posts.length" v-cloak>
      <b-list-group class="post-list">
        <job4j-post v-for="post in posts" :key="post.id"
                    :post="post"></job4j-post>
      </b-list-group>
    </b-row>
  </b-container>
</template>

<script>
import axios from 'axios'
import Post from './Post.vue'

export default {
  props: ['post'],
  data () {
    return {
      posts: []
    }
  },
  components: {
    'job4j-post': Post
  },
  beforeCreate () {
    axios.get('/list')
      .then(res => {
        console.log(res)
        const [...posts] = res.data
        this.posts = posts

        console.log(posts)
      })
      .catch(error => console.log(error))
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
