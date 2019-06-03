<template>
  <newStorage admin="false"></newStorage>
</template>

<style>
@import '/lib/css/storage.css';
</style>

<script>
import newStorage from '@/components/newStorage.vue'
  export default {
    name: 'shop',
    components: {
      newStorage
    }
  }
</script>
