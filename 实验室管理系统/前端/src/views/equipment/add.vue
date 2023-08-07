<template>
  <div class="app-container">
  设备添加
    <el-form label-width="120px">
      <el-form-item label="设备型号">
        <el-input v-model="equipmentSet.equName" />
      </el-form-item>
      <el-form-item label="设备号">
        <el-input v-model="equipmentSet.equId" />
      </el-form-item>
      <el-form-item label="设备价格">
        <el-input v-model="equipmentSet.price" />
      </el-form-item>
      <el-form-item label="所属实验室">
        <el-input v-model="equipmentSet.labName" />
      </el-form-item>
      <el-form-item label="功能简述">
        <el-input v-model="equipmentSet.description" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>



<script>
import equipment from "@/api/equipment";
export default {
  data() {
    return {
     equipmentSet: {
      // equId:'',
      // equName:'',
      // price:'',
      // labName:'',
      // description:''

     },
    };
  },
  created() {
    //获取路由id值 调用接口得到医院设置信息


    if (this.$route.params && this.$route.params.uid) {
      const uid = this.$route.params.uid;
      this.getEquipment(uid);
    }
    else {
      this.equipmentSet = {};
    }
  },
  methods: {
    saveOrUpdate() {
      //判断添加还是修改
      if (!this.equipmentSet.uid) {
        //没有id，做添加
        this.save();
      } else {
        //修改
        this.update();
      }
    },
    //添加
    save() {

        // if(this.equipmentSet.equId==null){
        //     this.$message({
        //   type: "fail",
        //   message: "设备id不能为空!",
        // });
        // return
        // }
        // if(this.equipmentSet.labName==null){
        //     this.$message({
        //   type: "fail",
        //   message: "请输入设备类型!",
        // });
        // return
        // }
        // if(this.equipmentSet.price==null){
        //     this.$message({
        //   type: "fail",
        //   message: "请输入设备价格!",
        // });
        // return
        // }
        // if(this.equipmentSet.labId==null){
        //     this.$message({
        //   type: "fail",
        //   message: "所属实验室不能为空!",
        // });
        // return
        // }

        equipment.saveEquipment(this.equipmentSet)
        .then((response) =>{
              this.$message({
          type: "success",
          message: "添加成功!",
        });
        })
        //跳转列表页面，使用路由跳转方式实现
        this.$router.push({ path: "/equipmentSet/list" });
    },

    //修改
    update() {
        // if(this.equipmentSet.labId==null){
        //     this.$message({
        //   type: "fail",
        //   message: "所属实验室不能为空!",
        // });
        // return
        // }
       equipment.updateEquipment(this.equipmentSet).then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        //跳转列表页面，使用路由跳转方式实现
        this.$router.push({ path: "/equipmentset/list" });
      });

    },
    //根据id查询,数据回显
    getEquipment(id) {
      equipment.getEquipment(id)
      .then((response) => {
        this.equipmentSet = response.data;
      });
    }

  }
};
</script>
