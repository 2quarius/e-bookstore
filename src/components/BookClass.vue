<template>
  <div class="col-md-4">
    <div class="thumbnail">
      <img alt="300x200" v-bind:src="'/static/img/'+url" />
      <div class="caption">
        <h3>
          {{headname}}
        </h3>
        <p>
					{{subclasses}}
				</p>
        <p>
          <a class="btn btn-primary" href="#/shop">进入会场</a> <a class="btn" href="#">不感兴趣</a>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
	name: 'BookClass',
	props: {
		url: String,
		headname: String,
		subclasses: String
	}
}
</script>

