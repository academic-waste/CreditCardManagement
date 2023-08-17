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
  receiveSumData : any[] = []//实际调用api应该不需要这个
  private lineData: LineData = {xAxis:[],yAxis:[]};
  private pieData: PieData[] = [];
  categoryState: string='';
  tableName: string = 'Total Transactions By State';
  loading: boolean = false;

  //将得到的数据转换为pie的数据
  transResponseDataToPieData(responseData: StateSum[]) :void{
    this.pieData=[];
    responseData.forEach((item)=>{
      this.pieData.push({name: item.state, value:item.total_transactions})
    })
  }

  transResponseDataToTableData(responseData: StateCatgoryData[]) :void{
    this.pieData=[];
    responseData.forEach((item)=>{
      this.pieData.push({name: item.category, value:item.total_transactions})
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

  getStateSum(category: string){
    //choice为0时是饼图，1是柱状图，只用这两种
    //两种情况：仅不同state的total，某个state下的spending category total
    console.log('hhh');
    this.tableName = 'Total Transactions By State';
    //调用api拿到数据，然后将拿到的数据作为参数传给updateCharts来更新，category，请求接口的类型
    // //if(!choice){
      //还需要更新左边显示的数据
      this.dataService.getStateSumDate(category).subscribe((response)=>{
        console.log("jinruqingqiu");
        
        this.transResponseDataToPieData(response);
          this.updatePieCharts(response);
          this.receiveSumData=this.pieData;
        // 如果type是pie，直接用数据，但是要
      },(err)=>{}
      )
    // //}else{
    //     //this.dataService.getStateCatData(this.categoryState).subscribe((response)=>{
    //       //this.transResponseDataToLineData(response);
    //       //this.updateCharts(reponse);
    //     //},(err)=>{})
    // //}
    // this.updatePieCharts(22)
  }

  getStateCatSum(){
    if(!this.categoryState){
      alert("Please choose state")
    }else{
      this.dataService.getStateCatData(this.categoryState).subscribe((response)=>{
        this.transResponseDataToLineData(response);
        this.updateCharts(response);
        this.transResponseDataToTableData(response);
        this.receiveSumData=this.pieData;
        console.log(this.pieData);
        
        this.tableName = "Spending Category Total By State"
      },(err)=>{})
    }
  // this.updateCharts(22)
  }

  //下拉框选州的时候更改this.categoryState
  changeState(stateName: string): void{
    this.categoryState = stateName;
    console.log("number", stateName);
  }



  updateCharts(retureData: any){
    const ec = echarts as any;
    ec.dispose(document.getElementById('lineChart'));
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'category',
        axisTick: {
          alignWithLabel: true
        },
        axisLabel: {
          interval: 0,//横轴信息全部显示
            rotate: -45, //倾斜度 -90 至 90 默认为0
            margin: 15, //刻度标签与轴线之间的距离
            textStyle: {
              fontSize: 14, //横轴字体大小
              color: "#000000",//颜色
            },
        },
        data: this.lineData.xAxis,
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        name: 'Direct',
        type: 'bar',
        barWidth: '60%',
        data: this.lineData.yAxis,
      }]
    }
    lineChart.setOption(lineChartOption);
  }

  updatePieCharts(returndata: any){
    const ec = echarts as any;
    ec.dispose(document.getElementById('lineChart'));
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
        data: this.pieData,
      }]
    }
    lineChart.setOption(lineChartOption);
  }

   
  ngOnInit() {
    this.dataService.getStateSumDate('').subscribe((response)=>{
      this.transResponseDataToPieData(response);
        this.updatePieCharts(response);
        this.receiveSumData=this.pieData;
        this.initCharts()
        console.log('aaa');
    },(err)=>{})
  }
  initCharts() {
    const ec = echarts as any;
    const lineChart = ec.init(document.getElementById('lineChart'));

    const lineChartOption = {
      tooltip: {
        trigger: 'item'
      },
      series: [{
        // data: this.receiveSumData,
        data: this.pieData,
        type: 'pie'
      }],
    }
    lineChart.setOption(lineChartOption);
  }
  

}
