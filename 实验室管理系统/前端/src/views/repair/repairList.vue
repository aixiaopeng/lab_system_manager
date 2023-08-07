<template>
  <div class="app-container">
    <el-table
      :data="list"
      style="width: 100%"
      stripe="true">
      <el-table-column align="center" prop="tname" label="报修类型" width="200"></el-table-column>
      <el-table-column align="center" prop="rname" label="报修材料/设备名称" width="300">
      </el-table-column>
      <el-table-column align="center" prop="rinfo" label="报修原因" width="500">
      </el-table-column>
      <el-table-column label="操作" width="280" align="center">
        <template slot-scope="scope">
          <el-button type="primary" size="mini"
                      @click="removeDataById(scope.row.uuid)">已处理
          </el-button>

        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>

import lrApi from '@/api/lm'
export default {
  data() {
    return {
      list: []
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      lrApi.rpList()
        .then((response) => {
          this.list = response.data;
          console.log(response)
          console.log('list' + this.list);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    removeDataById(uuid) {
      this.$confirm("是否已处理该条报修?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        lrApi.deleteR(uuid)
          .then((response) => {
            this.$message({
              type: "success",
              message: "处理成功!",
            })

            this.getList();
          });
      });
    }
  }
}
</script>


