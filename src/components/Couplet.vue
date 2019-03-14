<template>
  <div class="col-md-2">
    <div class="thumbnail">
      <img alt="200x500" v-bind:src="'/static/img/'+url" />
    </div>
  </div>
</template>

<script>
export default {
    name: 'Couplet',
    props: {
        url: String
    }
}
</script>

