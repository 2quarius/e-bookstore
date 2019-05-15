<template>
    <div class="carousel slide" id="carousel-807189">
            <ol class="carousel-indicators">
              <li class="active" data-slide-to="0" data-target="#carousel-807189">
              </li>
              <li data-slide-to="1" data-target="#carousel-807189">
              </li>
              <li data-slide-to="2" data-target="#carousel-807189">
              </li>
              <li data-slide-to="3" data-target="#carousel-807189">
              </li>
              <li data-slide-to="4" data-target="#carousel-807189">
              </li>
              <li data-slide-to="5" data-target="#carousel-807189">
              </li>
              </ol>
          <div class="carousel-inner">
          <div class="item active">
            <img alt="" v-bind:src="'/static/img/'+url0" />
            <div class="carousel-caption">
              <h4>
                {{head0}}
              </h4>
              <p>
                {{info0}}
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" v-bind:src="'/static/img/'+url1" />
            <div class="carousel-caption">
              <h4>
                {{head1}}
              </h4>
              <p>
                {{info1}}
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" v-bind:src="'/static/img/'+url2" />
            <div class="carousel-caption">
              <h4>
                {{head2}}
              </h4>
              <p>
                {{info2}}
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" v-bind:src="'/static/img/'+url3" />
            <div class="carousel-caption">
              <h4>
                {{head3}}
              </h4>
              <p>
                {{info3}}
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" v-bind:src="'/static/img/'+url4" />
            <div class="carousel-caption">
              <h4>
                {{head4}}
              </h4>
              <p>
                {{info4}}
              </p>
            </div>
          </div>
          <div class="item">
            <img alt="" v-bind:src="'/static/img/'+url5" />
            <div class="carousel-caption">
              <h4>
                {{head5}}
              </h4>
              <p>
                {{info5}}
              </p>
            </div>
          </div>
        </div> <a class="left carousel-control" href="#carousel-807189" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-807189" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
      </div>
</template>

<script>
export default {
    name: 'ScollAdvertise',
    props: {
        url0: String,
        url1: String,
        url2: String,
        url3: String,
        url4: String,
        url5: String,
        head0: String,
        head1: String,
        head2: String,
        head3: String,
        head4: String,
        head5: String,
        info0: String,
        info1: String,
        info2: String,
        info3: String,
        info4: String,
        info5: String
    }
}
</script>
