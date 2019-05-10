<!--评论模块-->
<template>
  <div class="container">
    <div class="comment" v-for="item in comments" :key="item">
      <div class="info">
        <img class="avatar" src="/static/img/22.png" width="36" height="36"/>
        <div class="right">
          <div class="name">{{item.userName}}</div>
          <div class="date">{{item.date}}</div>
        </div>
      </div>
      <div class="content">{{item.context}}</div>
      <div class="control">
        <span class="like" :class="{active: item.isLike}" @click="likeClick(item)">
          <i class="iconfont icon-like"></i>
          <span class="like-num">{{item.likeNum > 0 ? item.likeNum + '人赞' : '赞'}}</span>
        </span>
        <span class="comment-reply" @click="showCommentInput(item)">
          <i class="iconfont icon-comment"></i>
          <span>回复</span>
        </span>
				<span class="comment-delete" v-if="rights(item)" @click="deleteUpperComment(item)">
          <i class="iconfont icon-comment"></i>
          <span>删除</span>
        </span>
      </div>
      <div class="reply">
        <div class="item" v-for="children in item.children" :key="children">
          <div class="reply-content">
            <span class="from-name">{{children.userName}}</span><span>: </span>
            <span class="to-name">@{{children.fatherName}}</span>
            <span>{{children.context}}</span>
          </div>
          <div class="reply-bottom">
            <span>{{children.date}}</span>
            <span class="reply-text" @click="showCommentInput(item, children)">
              <i class="iconfont icon-comment"></i>
              <span>回复</span>
            </span>
						<span class="delete-text" v-if="rights(children)" @click="deleteLowerComment(item,children)">
              <!-- icon needs to be changed -->
							<i class="iconfont icon-comment"></i>
              <span>删除</span>
            </span>
          </div>
        </div>
        <div class="write-reply" v-if="item.children.length > 0" @click="showCommentInput(item)">
          <i class="el-icon-edit"></i>
          <span class="add-comment">添加新评论</span>
        </div>
        <transition name="fade">
          <div class="input-wrapper" v-if="showItemId === item.commentId">
            <el-input class="gray-bg-input"
                      v-model="inputComment"
                      type="textarea"
                      :rows="3"
                      autofocus
                      placeholder="写下你的评论">
            </el-input>
            <div class="btn-control">
              <span class="cancel" @click="cancel">取消</span>
              <el-button class="btn" type="success" round @click="commitComment(item)">确定</el-button>
            </div>
          </div>
        </transition>
      </div>
    </div>
		<!-- here -->
		<div class="comment">
			<div class="reply">
			<div class="write-reply" @click="showCommentInput()">
        <i class="el-icon-edit"></i>
        <span class="add-comment">添加新评论</span>
      </div>
			<transition name="fade">
          <div class="input-wrapper" v-if="clicked">
            <el-input class="gray-bg-input"
                      v-model="inputComment"
                      type="textarea"
                      :rows="3"
                      autofocus
                      placeholder="写下你的评论">
            </el-input>
            <div class="btn-control">
              <span class="cancel" @click="cancel">取消</span>
              <el-button class="btn" type="success" round @click="commitComment()">确定</el-button>
            </div>
          </div>
        </transition>
			</div>
		</div>
  </div>
</template>

<script>
import store from '../store.js'
import Vue from 'vue'
	export default {
    props: {
      comments: {
        type: Array,
        required: true
			},
			bookId: {
				type: Number,
				required: true
			}
    },
    components: {},
    data() {
      return {
        inputComment: '',
				showItemId: '',
				clicked: false
      }
    },
    computed: {},
    methods: {
      /**
       * 点赞
       */
      likeClick(item) {
        if (item.isLike === null) {
          Vue.$set(item, "isLike", true);
          item.likeNum++
        } else {
          if (item.isLike) {
            item.likeNum--
          } else {
            item.likeNum++
          }
          item.isLike = !item.isLike;
				}
      },

      /**
       * 点击取消按钮
       */
      cancel() {
				this.showItemId = '';
				this.clicked = false
      },

      /**
       * 提交评论
       */
      commitComment(item) {
				var commentId;
				if(store.state.user.username === ''){
					this.$message({
						type: 'error',
						message: '请先登录！'
					});
					return;
				}
				else if(item){
					commentId = item.commentId;
				}
				else{
					commentId = '';
				}
				var url = "/book_comment/"+this.bookId;
				var self = this;
				this.postBodyRequest(url,{
					"commentId": commentId,
					"userName": store.state.user.username,
					"context": this.inputComment
				}).then((response)=>{
					if(response.data.code == 0){
						self.comments = response.data.result.comments;
						self.cancel();
					}
					else{
						this.$message({
							type: 'error',
							message: response.data.message
						});
					}
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
      },

      /**
       * 点击评论按钮显示输入框
       * item: 当前大评论
       * reply: 当前回复的评论
       */
      showCommentInput(item, reply) {
        if (reply) {
          this.inputComment = "@" + reply.userName + " "
        } else if(item){
          this.inputComment = ''
				} else{
					this.clicked = true;
				}
        this.showItemId = item.commentId
			},
			/**
			 * 判断此登录用户是否有权限删除此条评论
			 */
			rights(item){
				console.log(item.userName);
				console.log(store.state.user.username);
				if(item.userName === store.state.user.username){
					return true;
				}
				return false;
			},
			/**
			 * 删除评论
			 */
			deleteHelper(item,fatherCommentId){
				var self = this;
				var url = '/book_comment/'+self.bookId;
				this.deleteRequest(url,{
					"fatherCommentId": fatherCommentId,
					"deleteCommentId": item.commentId
				}).then((response)=>{
					if(response.data.code == 0){
						self.comments = response.data.result.comments;
						self.cancel();
					}
					else{
						this.$message({
							type: 'error',
							message: response.data.message
						});
					}
				}).catch(error => {
					JSON.stringify(error);
					console.log(error);
				});
			},
			deleteUpperComment(item){
				if(item.fatherName === ''){
					var fatherCommentId = null;
					this.deleteHelper(item,fatherCommentId);
				}
				else{
					this.$message({
						type: 'error',
						message: '错误操作'
					});
				}
			},
			deleteLowerComment(item,children){
				if(children.fatherName === ''){
					this.$message({
						type: 'error',
						message: '错误操作'
					});
				}
				else{
					var fatherCommentId = item.commentId;
					this.deleteHelper(children,fatherCommentId);
				}
			
			}
    },
    created() {
      console.log(this.comments)
    }
  }
</script>

<style scoped lang="scss">
@import "public/lib/scss/index.scss";
@import "public/lib/scss/comment.scss";
</style>
