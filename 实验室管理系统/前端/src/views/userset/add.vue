<template>
  <div class="app-container">

    <el-form label-width="120px">
      <el-form-item label="姓名">
        <el-input v-model="userSet.userName"/>
      </el-form-item>
      <el-form-item label="编号">
        <el-input v-model="userSet.userId"/>
      </el-form-item>

      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="userSet.phone"/>
      </el-form-item>
      <el-form-item label="电子邮箱">
        <el-input v-model="userSet.email"/>
      </el-form-item>
      <!-- <el-form-item label="权限">
        <el-input v-model="userSet.role" />
      </el-form-item> -->
      <el-form-item label="权限">
        <el-select v-model="userSet.role" placeholder="权限">
          <el-option
            v-for="item in times"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled">
          </el-option>
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <router-link :to="'/userSet/list'">
          <el-button type="primary">取消</el-button>
        </router-link>
      </el-form-item>

    </el-form>
  </div>

</template>


<script>
import user from "@/api/userset";

export default {
  data() {
    return {
      userSet: {},
      times: [{       //默认时间选择
        value: 'user',
        label: '普通用户',
        disabled: false
      },
        {
          value: 'teacher',
          label: '实验室技术人员',
          disabled: false
        },
        {
          value: 'admin',
          label: '实验室主任',
          disabled: false
        },
      ],
    };
  },
  created() {
    //获取路由id值 调用接口得到医院设置信息
    if (this.$route.params && this.$route.params.uid) {
      const uid = this.$route.params.uid;

      this.getUser(uid);
    } else {
      this.userSet = {};
    }
  },
  methods: {
    saveOrUpdate() {
      //判断添加还是修改
      if (!this.userSet.uid) {
        //没有id，做添加
        this.save();
      } else {
        //修改
        this.update();
      }
    },
    //添加
    save() {

      if (this.userSet.userName == null) {
        this.$message({
          type: "fail",
          message: "用户名不能为空!",
        });

        return;
      }
      if (this.userSet.userId == null) {
        this.$message({
          type: "fail",
          message: "用户id不能为空!",
        });

        return;
      }
      // if (this.userSet.password == null) {
      //   this.$message({
      //     type: "fail",
      //     message: "密码不能为不能为空!",
      //   });
      //   return;
      // }

      //  if(/^[a-zA-Z]\w{5,17}$/.test(this.userSet.password)){
      //   this.$message({
      //     type: "fail",
      //     message: "密码格式错误!",
      //   });
      //   alert("44")
      //   return
      //  }

      user.saveUser(this.userSet).then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "添加成功!",
        });

        //跳转列表页面，使用路由跳转方式实现
        this.$router.push({path: "/userset/list"});
      });
    },
    //修改
    update() {

      user.updateUser(this.userSet)
        .then((response) => {
          //提示
          this.$message({
            type: "success",
            message: "修改成功!",
          });
          //跳转列表页面，使用路由跳转方式实现
          this.$router.push({path: "/userset/list"});
        });

    },
    //根据id查询,数据回显
    getUser(id) {

      user.getUser(id).then((response) => {
        this.userSet = response.data;
      });
    },
  },
};
</script>
