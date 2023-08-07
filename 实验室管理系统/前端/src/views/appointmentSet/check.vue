<template>
  <div class="app-container">

    <el-row>
      <el-col :span="22">
        <div class="grid-content bg-purple">
         实验室申请信息
          </div>
      </el-col>


      <el-col :span="2">
        <div class="grid-content bg-purple-light">
        <router-link :to="'/appointmentSet/old'">
              <el-button type="info" >已审核信息</el-button>
          </router-link>
      </div>
      </el-col>

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
       <el-table-column prop="appointDate" label="申请日期" width="150">
      </el-table-column>
      <el-table-column prop="equipmentNameList" label="使用设备列表" width="150">
      </el-table-column>

       <el-table-column prop="time" label="申请时间" width="150">

      </el-table-column>



      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">

          <el-button  size="mini" type="primary"
           icon="el-icon-check" @click="agree(scope.row.uid)">同意</el-button>

            <el-button type="danger" size="mini"
           icon="el-icon-close" @click="refuse(scope.row.uid)">拒绝</el-button>



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
</template>

<script>
import checkSet from "@/api/appointment";
export default {
  data() {
    return {
      current: 1, //当前页
      limit: 10, //每页显示记录数
      AppInfo:{},
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
      list: [], //每页数据集合
      total: 0,
      multipleSelection: [],
      checker:''
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList(page=1) {


      //1.获取待审核预约列表
      this.current = page;
      checkSet.getAppointmentList(this.current, this.limit) //调用api中写的方法
        .then((response) => {
          //response接口返回的数据
          this.list = response.data.records; //给数组赋值
          this.total = response.data.total;
           for(var i=0;i<this.list.length;i++){
            if(this.list[i].appointTime==1)
             this.list[i].time='8:00-10:00'
          if(this.list[i].appointTime==2)
             this.list[i].time='10:00-12:00'
          if(this.list[i].appointTime==3)
             this.list[i].time='14:00-16:00'
          if(this.list[i].appointTime==3)
             this.list[i].time='16:00-18:00'
          }
          console.log(response);
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
    },
    //2.同意审核  获取当前预约uid和审核员身份
    agree(uid){
      checkSet.checkAgree(uid)
      .then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "审核成功!",
        });
        this.getList()
      })

    },

    refuse(uid){
      checkSet.checkRefuse(uid)
      .then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "审核成功!",
        });
        this.getList()
      })


    },

  }

};
</script>
