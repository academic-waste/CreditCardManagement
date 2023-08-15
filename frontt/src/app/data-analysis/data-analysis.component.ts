import { Component, OnInit } from '@angular/core';
import * as echarts from 'echarts';
import { DataService } from '../services/in-transactions-data.service';
import { pieData, stateSum } from '../ulities/module';

@Component({
  selector: 'app-data-analysis',
  templateUrl: './data-analysis.component.html',
  styleUrls: ['./data-analysis.component.css']
})
export class DataAnalysisComponent implements OnInit {

  constructor(private dataService: DataService) {}
  receiveSumData : pieData[] = [
    {
      value:14,
      name:'hh'
    },
    {
      value:19,
      name:'hhh'
    },
    {
      value:81,
      name:'saahh'
    },
    {
      value:11,
      name:'hdadah'
    }
  ]//实际调用api应该不需要这个
  
  sumDateobjToArr(data: stateSum[]){
    this.receiveSumData = [];
    //遍历data，将相应的键值赋给receiveSumData
    data.forEach((item, index)=>{
      
    })
  }

  transResponseDataToPieData(responseData: stateSum){
    //遍历
  }

  getStateSum(choice: number, type: string, category: string){
    console.log('hhh');
    //调用api拿到数据，然后将拿到的数据作为参数传给updateCharts来更新，category，请求接口的类型
    // this.dataService.getStateSummDate(category).subscribe((response)=>{
      //如果type是pie，直接用数据，但是要
    // },
    // )
    // this.updateCharts(1,"bar")
    !choice ? this.updatePieCharts(22) : this.updateCharts(22, type)
    
  }
  updateCharts(retureData: any,type: String){
    const ec = echarts as any;
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {

      xAxis: {
        type: 'category',
        data: ['aa', 'bb', 'cc', 'dd', 'Fri', 'Sat', 'Sun'],
        axisLine: { show: false },
        axisLabel: { show: true },
        axisTick: { show: true },
        splitLine: { show: true },
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: type
      }]
    }
    lineChart.setOption(lineChartOption);
  }

  updatePieCharts(returndata: any){
    const ec = echarts as any;
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {
      tooltip: {
        trigger: 'item'
      },
      series: [{
        data: this.receiveSumData,
        type: 'pie'
      }],
      xAxis: {
        type: 'category',
        data: [],
        axisLine: { show: false },
        axisLabel: { show: false },
        axisTick: { show: false },
        splitLine: { show: false },
      },
    }
    lineChart.setOption(lineChartOption);
  }

   
  ngOnInit() {
    this.initCharts();
  }
  initCharts() {
    const ec = echarts as any;
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: {
        type: 'category',
        data: ['Mon', '66', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'bar'
      }]
    }
    lineChart.setOption(lineChartOption);

  }
  

}
