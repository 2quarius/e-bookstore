<template>
	<div class="row">
  	<div class="col-md-4">
    	<img v-bind:src="'/static/img/'+url0" />
  	</div>
  	<div class="col-md-4">
    	<img v-bind:src="'/static/img/'+url1" />
  	</div>
  	<div class="col-md-4">
  	  <img v-bind:src="'/static/img/'+url2" />
  	</div>
	</div>
</template>

<script>
export default {
	name: 'FlatAdvertise',
	props: {
		url0: String,
		url1: String,
		url2: String
	}
}
</script>

