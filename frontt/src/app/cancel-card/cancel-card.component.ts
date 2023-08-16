import { Component, OnInit } from '@angular/core';
import { cacelData } from '../ulities/module'
import { CancelDataService } from '../services/in-cacel-data.service';

// 弹窗
import { NzMessageService } from 'ng-zorro-antd/message';




@Component({
  selector: 'app-cancel-card',
  templateUrl: './cancel-card.component.html',
  styleUrls: ['./cancel-card.component.css']
})
export class CancelCardComponent implements OnInit {
  listOfCustomerData: any[] = [];
  loading = true;
  constructor(private cancelDataService: CancelDataService, private message: NzMessageService) { }

  ngOnInit(): void {

    // this.listOfCustomerData = this.cancelDataService.createDb().listOfCustomerData;
    this.cancelDataService.getItems().subscribe((data: any) => {
      // this.cancelDataService.getItems = data;
      this.listOfCustomerData = data;
    });
  }

  deleteItem(id: number): void{
    this.cancelDataService.deleteItem(id).subscribe(() => {
      this.listOfCustomerData = this.listOfCustomerData.filter(listOfCustomerData => listOfCustomerData.id !== id);
    });
    // this.cancelDataService.deleteItem(id);
    // this.listOfCustomerData = this.cancelDataService.createDb().listOfCustomerData;
    // console.log("Successfully cancel!")
    // this.message.success('Successfully cancel');
  }

}

