<template>
  <div class="app-container">
    实验室预约
   
    <el-form ref="form" :model="sizeForm" label-width="120px" size="normal">
       实验室名称
       <el-select v-model="labname" clearable filterable placeholder="请选择">
       <el-option
            v-for="item in labnames"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
      </el-select>

       <el-form-item label="申请人编号">
        <el-input v-model="AppInfo.userId" />
      </el-form-item>

      <el-form-item label="申请人姓名">
        <el-input v-model="AppInfo.userName" />
      </el-form-item>

      <el-form-item label="实验人数">
        <el-input v-model="AppInfo.count" />
      </el-form-item>
      <el-form-item label="实验内容">

        <el-input
         type="textarea"
         :autosize="{ minRows: 2, maxRows: 8}"
         placeholder="请输入内容"
          v-model="AppInfo.description">
         </el-input>
      
      </el-form-item>

<!-- 日期框 -->
       <div class="block">
         <span class="demonstration">请选择日期</span>
         <el-date-picker
          v-model="AppInfo.appointDate"
           type="date"
           placeholder="选择日期">
        </el-date-picker>
     
<!-- 选择具体时间段 -->

      <el-select v-model="AppInfo.appointTime" placeholder="请选择时间">
           <el-option
           v-for="item in times"
           :key="item.value"
           :label="item.label"
           :value="item.value">
          </el-option>
      </el-select> 

    </div>
                  
    <br><br>
    <el-form-item>
      <el-button type="primary" @click="submitAppointment">提交</el-button>
      </el-form-item>
    </el-form>
    
  </div>
</template>

<script>
import appointment from "@/api/appointment";
export default {
  data() {
    return {
      AppInfo:{},//条件封装对象
      times: [{       //默认时间选择
          value: 1,
          label: '8:00-10:00',
        }, 
        {
          value: 2,
          label: '10:00-12:00',
        },
        {
           value: 3,
          label: '14:00-16:00',
        },
        {
           value: 4,
          label: '16:00-18:00',
        }
        ],
      appointTime:'',
        AppInfo:{},
      labnames:[
        { value: '1',
          label: '实验室1'
        }, {
          value: '2',
          label: '实验室2'
        }, {
          value: '3',
          label: '实验室3'
        }, {
          value: '4',
          label: '实验室4'
        }, {
          value: '5',
          label: '实验室5'
        },
         {
          value: '6',
          label: '实验室6'
        },
         {
          value: '7',
          label: '实验室7'
        },
         {
          value: '8',
          label: '实验室8'}
      ],
      labname:'',
      
    };
  },
  created() {
   
  },

  methods: {
    
    //提交预约申请S
    submitAppointment(){ 
      
      alert(this.AppInfo.appointTime)
      
      appointment.addAppointment(this.AppInfo)
      .then((response) => {
        //提示
        this.$message({
          type: "success",
          message: "提交成功!",
        })
        .catch((error) => {
          //请求失败
          console.log(error);
        });
        
      })
    


    },
    //设置可选择时间
  

    
  }
}

</script>