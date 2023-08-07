<template>
  <div class="app-container">

  <el-form :inline="true" class="demo-form-inline">
      <el-form-item lable="日期">
          <el-date-picker
           v-model="day"
           type="date"
           placeholder="选择日期"
           value-format="yyyy-MM-dd"/>
         </el-form-item>

         <el-button
           
           type="primary"
           @click="create()"> 生成</el-button>
        </el-form>

  </div>
</template>

<script>
import sta from '@/api/statistics'
export default{
    data(){
        return{
            day:''
        }
    },
    created(){

    },
    methods:{
        create(){
            sta.createDate(this.day)
            .then(response=>{
                this.$message({
                type: "success",
                 message: "生成数据成功!",
                 })
                 //跳转页面
                 this.$router.push({path:'/sta/show'})
            })
            
        }
    }
}



</script>
