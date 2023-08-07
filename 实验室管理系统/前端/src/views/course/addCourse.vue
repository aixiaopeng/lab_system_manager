<template>
  <div class="app-container">
    <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
      <el-form-item label="课程id">
        <el-input placeholder="请输入课程id" style="width: 200px" v-model="sizeForm.cid"></el-input>
      </el-form-item>
      <el-form-item label="课程名称">
        <el-input placeholder="请输入课程名称" style="width: 500px"
                  v-model="sizeForm.cname"></el-input>
      </el-form-item>
      <el-form-item label="实验室id">
        <el-input placeholder="请输入实验室id" style="width: 200px"
                  v-model="sizeForm.lid"></el-input>
      </el-form-item>
      <el-form-item label="实验室名称">
        <el-input placeholder="请输入实验室名称" style="width: 500px"
                  v-model="sizeForm.lname"></el-input>
      </el-form-item>
      <el-form-item label="开课日期">
        <el-input placeholder="请输入开课日期" style="width: 800px" type="textarea" :autosize="{ minRows: 2, maxRows: 4}"
                  v-model="sizeForm.cdate"></el-input>
      </el-form-item>
      <el-form-item label="课程类型">
        <el-input placeholder="必修、选修" style="width: 200px" v-model="sizeForm.ctype"></el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即发布</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {mapGetters} from "vuex";
import courseApi from "@/api/course"

export default {
  data() {
    return {
      sizeForm: {
        cid: '',
        cname: '',
        lid: '',
        lname: '',
        cdate: '',
        ctype: ''
      }
    };
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    onSubmit() {
      console.log('submit!')
      courseApi.addCourse(this.sizeForm)
        .then((response) => {
          this.$message({
            message: '发布成功',
            type: 'success'
          });
        })
        .catch((error) => {
          console.log(error);
          this.$message.error('发布失败');
        });
    }
  }
};
</script>
