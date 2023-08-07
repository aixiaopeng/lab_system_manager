<template>
  <div class="app-container">

<el-row>
  <el-col :span="22"><div class="grid-content bg-purple">
      <a href="http://localhost:8206/admin/appointment/exportData" target="_blank">
         <el-button type="primary" icon="el-icon-download">导出</el-button>
        </a>


    </div></el-col>

  <el-col :span="2"><div class="grid-content bg-purple-light">
     <router-link :to="'/appointmentSet/check'">
              <el-button type="info" >查看待审核信息</el-button>
     </router-link>
    </div></el-col>
</el-row>



    <el-table
      :data="list"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column prop="labId" label="实验室id" width="120"> </el-table-column>
      <el-table-column prop="labName" label="实验室名称" width="100"></el-table-column>
      <el-table-column prop="userId" label="申请人id" width="100"></el-table-column>
      <el-table-column prop="userName" label="申请人姓名" width="150"> </el-table-column>
      <el-table-column prop="count" label="申请人数" width="150"> </el-table-column>
      <el-table-column prop="description" label="实验内容" width="150"> </el-table-column>
      <el-table-column prop="appointDate" label="申请日期" width="150"> </el-table-column>
      <el-table-column prop="appointTime" label="申请时间" width="150">  </el-table-column>
      <el-table-column label="意见" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === "未通过" ? "拒绝" : "通过" }}
        </template>
      </el-table-column>







      <!-- </el-table-column>
       <el-table-column prop="appointTime" label="意见" width="150"> -->

    </el-table>
    <!-- 分页 -->
      <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total,prev, pager, next,jumper"
      @current-change="getListOld"
    />
  </div>
</template>

<script>
import checkSet from '@/api/appointment';
export default {
  data() {
    return {
      current: 1, //当前页
      limit: 10, //每页显示记录数
      AppInfo:{},
      list: [], //每页数据集合
      options:[{
        value: 1,
          label: 'user'
      },{
        value:2,
        label:"teacher"
      },
      {value:3,
      label:'admin'
      }
      ],
      total: 0,
      multipleSelection: [],
      ifCheck:''
    };
  },
  created() {

   this.getListOld()


  },
  methods: {
    //1.获取已经审核预约列表
     getListOld(page=1) {

      this.current = page;
      checkSet.getAppointmentListOld(this.current, this.limit) //调用api中写的方法
        .then((response) => {
          //response接口返回的数据
          this.list = response.data.records; //给数组赋值
          this.total = response.data.total;
          console.log(response);
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
    }
  }

};
</script>
<style>

.btn-block{
    position: bottom-right;
    bottom: 5px;
}

</style>
