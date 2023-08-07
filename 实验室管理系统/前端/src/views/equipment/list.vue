<template>
  <div class="app-container">
    <!-- 条件查询 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.equName" placeholder="设备型号" />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="getList()"
        >查询</el-button
      >
    </el-form>
    <!-- 工具条 -->
    <div>
      <el-button type="danger" size="mini" @click="removeRows()"
        >批量删除</el-button>
    </div>

    <el-table
      :data="list"
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="equId" label="设备号" width="80">
      </el-table-column>
      <el-table-column prop="equName" label="设备型号" width="100">
      </el-table-column>

      <el-table-column prop="price" label="价格" width="100"> </el-table-column>
      <el-table-column prop="startTime" label="配置时间" width="180">
      </el-table-column>
       <el-table-column prop="labName" label="所属实验室" width="120">
      </el-table-column>

      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status === 0 ? "可用" : "禁用" }}
        </template>
      </el-table-column>

      <el-table-column prop="description" label="功能描述" width="250">
      </el-table-column>

      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.uid)"
            >删除</el-button
          >

          <el-button
            v-if="scope.row.status == 1"
            type="primary"
            size="mini"
            @click="lockHospSet(scope.row.uid, 0)"
            >解锁</el-button
          >

          <el-button
            v-if="scope.row.status == 0"
            type="danger"
            size="mini"
            @click="lockHospSet(scope.row.uid, 1)"
            >锁定</el-button
          >

          <router-link :to="'/equipmentSet/edit/' + scope.row.uid">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
            ></el-button>
          </router-link>
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
import equipment from "@/api/equipment";
export default {
  data() {
    return {
      current: 1, //当前页
      limit: 10, //每页显示记录数
      searchObj: {}, //条件封装对象
      list: [], //每页数据集合
      total: 0,
      multipleSelection: [],
    };
  },
  created() {
    //一般调用method中的方法调用，当前vue中方法
    this.getList();
  },
  methods: {
    getList(page = 1) {
      //获取用户列表

      this.current = page;
      equipment
        .gerEquList(this.current, this.limit, this.searchObj) //调用api中写的方法
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
    },
    //删除
    removeDataById(uid) {
      this.$confirm("此操作将永久删除用户信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //确定执行then方法
        //调用接口
        equipment.deleteEquipment(uid).then((response) => {
          //提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //刷新页面
          this.getList(1);
        });
      });
    },
    //批量删除
    removeRows() {
      this.$confirm("此操作将永久删除设备信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        //确定执行then方法
        var idList = [];
        //遍历数组得到每个id值，设置到idList里面
        for (var i = 0; i < this.multipleSelection.length; i++) {
          var obj = this.multipleSelection[i];
          var id = obj.uid;
          idList.push(id);
        }
        //调用接口
        equipment.batchEquipment(idList).then((response) => {
          //提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          //刷新页面
          this.getList(1);
        });
      });
    },
    //获取批量删除id信息
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    //锁定和取消锁定
    lockHospSet(id,status){
      equipment.lockEquipment(id,status)
      .then(response =>{
        this.getList(1);
      })
    }
  }
};
</script>
