<template>
  <div class="container">
  <div class="row clearfix">
    <div class="col-md-12 column">
      <div class="row">
        <h1></h1>
        <h2></h2>
      </div>
      <div class="row">
        <Couplet url="00.jpg" />
        <div class="col-md-8">
          <ScollAdvertise url0="1.jpg" url1="2.jpg" url2="3.jpg" url3="20.jpg" url4="21.jpg" url5="22.jpg" />
          <FlatAdvertise url0="10.jpg" url1="11.jpg" url2="12.jpg" />
        </div>
        <Couplet url="01.jpg" />
      </div>
      <div class="row">
        <BookClass url="4.jpg" headname="科技" subclasses="科普读物 | 计算机/网络 | 医学 | 工业技术 | 建筑 | 自然科学 | 农业/林业" />
        <BookClass url="5.jpg" headname="文艺" subclasses="小说 | 文学 | 传记 | 青春文学 | 动漫/幽默 | 艺术 | 摄影 | 偶像明星 |涂色/填色" />
        <BookClass url="6.jpg" headname="经管" subclasses="经济 | 管理 | 投资理财 | 股票 | 金融 | 市场/销售 | 会计 | 互联网" />
      </div> 
      <div class="row">
        <BookClass url="7.jpg" headname="人文社科" subclasses="哲学宗教 | 历史 | 政治军事 | 文化 | 社会科学 | 心理学 | 古籍 | 法律" />
        <BookClass url="8.jpg" headname="生活" subclasses="两性 | 孕期 | 育儿 | 亲子家教 | 保健 | 运动 | 休闲 | 旅游 | 美食 | 美妆 | 手工DIY | 家庭家居 | 风水占卜" />
        <BookClass url="9.jpg" headname="进口图书" subclasses="英文图书 | 港台图书 | 日文图书 | 法文图书 | 韩文图书 | 其他语种 | 杂志 | 英文原版小说 | 进口艺术画册" />
      </div>
    </div>
  </div>
</div>
</template>

<script>
import BookClass from '@/components/BookClass.vue'
import Couplet from '@/components/Couplet.vue'
import ScollAdvertise from '@/components/ScollAdvertise.vue'
import FlatAdvertise from '@/components/FlatAdvertise.vue'
export default {
  name: 'home',
  components: {
    BookClass,
    Couplet,
    ScollAdvertise,
    FlatAdvertise
  }
}
</script>

