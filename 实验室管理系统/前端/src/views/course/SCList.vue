<template>
  <div class="app-container">
<!--    <el-form :inline="true" class="demo-form-inline">-->
<!--      <el-form-item>-->
<!--        <el-input v-model="searchObj.cname" placeholder="课程名"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-select clearable v-model="searchObj.lname" placeholder="实验室名">-->
<!--          <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value"-->
<!--            :disabled="item.disabled">-->
<!--          </el-option>-->
<!--        </el-select>-->

<!--      </el-form-item>-->
<!--      <el-button type="primary" icon="el-icon-search" @click="getList()"-->
<!--      >查询-->
<!--      </el-button>-->
<!--    </el-form>-->

    <el-table
      :data="list"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column align="center" prop="cid" label="课程号" width="100"></el-table-column>
      <el-table-column align="center" prop="cname" label="课程名" width="200"></el-table-column>
      <el-table-column align="center" prop="lname" label="实验室" width="200">
      </el-table-column>
      <el-table-column align="center" prop="cdate" label="上课时间" width="200">
      </el-table-column>
      <el-table-column align="center" prop="ctype" label="课程类型" width="150"></el-table-column>


      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="danger" size="mini"
                     icon="el-icon-delete" @click="removeDataById(scope.row.uuid)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total,prev, pager, next,jumper"
      @current-change="getList"
    />

  </div>
  <!-- 条件查询 -->

</template>

<script>
import courseApi from '@/api/course'
import {mapGetters} from "vuex";


export default {
  data() {
    return {
      current: 1, //当前页
      limit: 10, //每页显示记录数
      searchObj: {}, //条件封装对象
      list: [], //每页数据集合
      total: 0,
      options: [
        {
          label: '网络攻防实验室',
          value: '网络攻防实验室'
        },
        {
          label: '数据结构实验室',
          value: '数据结构实验室'
        }, {
          label: '计算机实验室',
          value: '计算机实验室'
        }]
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'id'
    ]),


  },
  created() {
    this.getList();
  },
  methods: {
    getList(page = 1) {
      //获取用户列表
      this.current = page;
      courseApi.SCList(this.id)
        .then((response) => {
          this.list = response.data; //给数组赋值
          this.total = response.data.total;
          console.log(response);
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
    },
    removeDataById(uuid) {
      this.$confirm("是否删除该课程?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        courseApi.deleteSC(uuid)
          .then((response) => {
            this.$message({
              type: "success",
              message: "删除成功!",
            })

            this.getList(1);
          });
      });
    },
    selectDataById(uuid) {

      let data = {
        userId: this.id,
        userName: this.name,
        uuid: uuid
      }
      console.log(data);
      courseApi.addSC(data)
        .then((response) => {
          this.$message({
            message: '订阅成功',
            type: 'success'
          })
        })
        .catch((error) => {
          this.$message.error('不可重复订阅');
          console.log(error);
        });


    }
  }
}

</script>

<style scoped>

</style>
