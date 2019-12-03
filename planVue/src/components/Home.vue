<template>
  <el-container style="height: 100%">
    <el-aside width="70%" style="height: 100%">
      <el-container style="height: 100%">
        <el-header height="300px">
          <el-carousel indicator-position="outside">
            <el-carousel-item v-for="item in items" :key="item">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </el-header>
        <el-main style="height: auto; width: auto">
          <el-row :gutter="20">
            <el-col class="grid-content bg-purple" :span="8" v-for="(o, index) in 10" :key="o">
              <el-card :body-style="{ padding: '10px' }">
                <div>
                  <router-link :to="('/login')">
                    <img src="../images/user.jpg" class="image">
                  </router-link>
                </div>
                <div style="padding: 14px; height: 60px">
                  <span>好吃的汉堡</span>
                  <div class="bottom clearfix">
                    <time class="time">{{ currentDate }}</time>
                    <el-button type="text" class="button">点击查看</el-button>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-aside>
    <el-main>

    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
        items: 4,
        currentDate: new Date()
      }
    },
    methods: {
      // getInfo:function () {
      //   alert("info")
      // }
    }
  }
</script>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }

  /*.el-main{*/
  /*color: #475669;*/
  /*font-size: 18px;*/
  /*opacity: 0.75;*/
  /*line-height: 300px;*/
  /*margin: 0;*/
  /*background-color: #fc18a9;*/
  /*}*/

  .el-carousel__item:nth-child(2n) {
    margin-top: 10px;
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    margin-top: 10px;
    background-color: #d3dce6;
  }

  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>
