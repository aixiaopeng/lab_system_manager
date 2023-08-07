<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">

      <el-form-item>
        <el-select v-model="searchObj.type" clearable placeholder="请选择">
          <el-option label="实验室1使用情况" value="lab1"/>
          <el-option label="实验室2使用情况" value="lab2"/>
          <el-option label="实验室3使用情况" value="lab3"/>
          <el-option label="实验室4使用情况" value="lab4"/>
          <el-option label="实验室5使用情况" value="lab5"/>
          <el-option label="实验室6使用情况" value="lab6"/>
          <el-option label="实验室7使用情况" value="lab7"/>
          <el-option label="实验室8使用情况" value="lab8"/>
          <el-option label="实验室9使用情况" value="lab9"/>

          <el-option label="预约总量统计" value="appointment_num"/>
         
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="searchObj.begin"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()">查询</el-button>
    </el-form>

    <div class="chart-container">
      <div id="chart" class="chart" style="height:500px;width:100%" />
    </div>

  </div>
</template>
<script>

import staApi from '@/api/statistics'
import * as echarts from 'echarts'
export default {
    data() {
        return {
            searchObj:{},
            btnDisabled:false,
            xData:[],
            yData:[]
        }
    },
    methods:{
        showChart() {
           
            staApi.getDataSta(this.searchObj)
                .then(response => { 
                   
                    console.log('*****************'+response)
                    this.yData = response.data.numDataList
                    this.xData = response.data.date_staList
                   
                    //调用下面生成图表的方法，改变值
                    this.setChart()
                  
                })
        },
        setChart() {
            // 基于准备好的dom，初始化echarts实例
            this.chart = echarts.init(document.getElementById('chart'))
            // console.log(this.chart)

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '数据统计'
                },
                tooltip: {
                    trigger: 'axis'
                },
                dataZoom: [{
                    show: true,
                    height: 30,
                    xAxisIndex: [
                        0
                    ],
                    bottom: 10,
                    start: 10,
                    end: 80,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle: {
                        color: '#d3dee5'

                    },
                    textStyle: {
                        color: '#fff'
                    },
                    borderColor: '#90979c'
                    },
                    {
                    type: 'inside',
                    show: true,
                    height: 15,
                    start: 1,
                    end: 35
                 }],
                // x轴是类目轴（离散数据）,必须通过data设置类目数据
                xAxis: {
                    type: 'category',
                    data: this.xData
                },
                // y轴是数据轴（连续数据）
                yAxis: {
                    type: 'value'
                },
                // 系列列表。每个系列通过 type 决定自己的图表类型
                series: [{
                    // 系列中的数据内容数组
                    data: this.yData,
                    // 折线图
                    type: 'line'
                }]
            }

            this.chart.setOption(option)
        }
    }
}
</script>
