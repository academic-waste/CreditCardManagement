import { Component, OnInit } from '@angular/core';
import * as echarts from 'echarts';
import { DataService } from '../services/in-transactions-data.service';
import { LineData, PieData, StateCatgoryData, StateSum } from '../ulities/module';

@Component({
  selector: 'app-data-analysis',
  templateUrl: './data-analysis.component.html',
  styleUrls: ['./data-analysis.component.css']
})
export class DataAnalysisComponent implements OnInit {
  //category是柱状图，state是饼图

  constructor(private dataService: DataService) {}
  receiveSumData : any[] = [
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
  private lineData: LineData = {xAxis:[],yAxis:[]};
  private pieData: PieData[] = [];
  private categoryState: string='';
  private tableName: string = 'State Total';

  //将得到的数据转换为pie的数据
  transResponseDataToPieData(responseData: StateSum[]) :void{
    this.pieData=[];
    responseData.forEach((item)=>{
      this.pieData.push({name: item.state, value:item.total_transactions})
    })
  }

  transResponseDataToLineData(responseData: StateCatgoryData[]): void{
    //遍历
    this.lineData={xAxis:[],yAxis:[]};
    responseData.forEach((item)=>{
      this.lineData.xAxis.push(item.category)
      this.lineData.yAxis.push(item.total_transactions)
    })
  }

  getStateSum(choice: number, category: string){
    //choice为0时是饼图，1是柱状图，只用这两种
    //两种情况：仅不同state的total，某个state下的spending category total
    console.log('hhh');
    //调用api拿到数据，然后将拿到的数据作为参数传给updateCharts来更新，category，请求接口的类型
    // //if(!choice){
    //   //还需要更新左边显示的数据
    //   this.dataService.getStateSummDate(category).subscribe((response)=>{
    //     this.transResponseDataToPieData(response);
    //       this.updatePieCharts(response);
    //       this.receiveSumData=this.pieData;
    //     // 如果type是pie，直接用数据，但是要
    //   },(err)=>{}
    //   )
    // //}else{
    //     //this.dataService.getStateCatData(this.categoryState).subscribe((response)=>{
    //       //this.transResponseDataToLineData(response);
    //       //this.updateCharts(reponse);
    //     //},(err)=>{})
    // //}
    this.updatePieCharts(22)
  }

  getStateCatSum(){
  //   this.dataService.getStateCatData(this.categoryState).subscribe((response)=>{
  //         this.transResponseDataToLineData(response);
  //         this.updateCharts(response);
  //         this.transResponseDataToPieData(response);
  //         this.receiveSumData=this.pieData;
  //       },(err)=>{})
  this.updateCharts(22)
  }

  //下拉框选州的时候更改this.categoryState
  changeState(stateName: String): void{
    // this.categoryState = stateName;
    console.log("number", stateName);
    
  }



  updateCharts(retureData: any){
    const ec = echarts as any;
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {

      xAxis: {
        type: 'category',
        data: ['aa', 'bb', 'cc', 'dd', 'Fri', 'Sat', 'Sun'],
        // data: this.lineData.xAxis,
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
        // data: this.lineData.yAxis,
        type: 'bar'
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
        // data: this.pieData,
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
    console.log(this.receiveSumData);
    this.initCharts();
    this.transResponseDataToPieData([{state:"OK", total_transactions:100},{state:"dk", total_transactions:200}]);
    console.log(this.pieData);
    this.transResponseDataToLineData([{category:"OK", total_transactions:100},{category:"dk", total_transactions:200}])
    console.log(this.lineData);
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