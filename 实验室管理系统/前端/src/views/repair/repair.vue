<template>
  <div class="app-container">
    <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
      <el-form-item label="报修类型">
<!--        <el-input placeholder="设备/材料报修" style="width: 200px" v-model="sizeForm.tname"></el-input>-->
        <el-select v-model="sizeForm.tname" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备/材料名称">
        <el-input placeholder="" style="width: 500px"
                  v-model="sizeForm.rname"></el-input>
      </el-form-item>
      <el-form-item label="报修原因">
        <el-input placeholder="请输入报修原因"  type="textarea" :autosize="{ minRows: 2, maxRows: 4} " style="width: 500px"
                  v-model="sizeForm.rinfo"></el-input>
      </el-form-item>

      <el-form-item size="large">
        <el-button type="primary" @click="onSubmit">立即提交</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>

import {mapGetters} from "vuex";
import lmApi from '@/api/lm'

export default {
  data() {
    return {
      sizeForm: {
        tname: '',
        rname: '',
        rinfo: '',
      },
      options: [{
        value: '实验室设备',
        label: '实验室设备'
      }, {
        value: '实验室材料',
        label: '实验室材料'
      }
      ],
      value: ''
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
      lmApi.addR(this.sizeForm)
        .then((response) => {
          this.$message({
            message: '报修成功',
            type: 'success'
          });
        })
        .catch((error) => {
          console.log(error);
          this.$message.error('报修失败');
        });
    }
  }
};
</script>
