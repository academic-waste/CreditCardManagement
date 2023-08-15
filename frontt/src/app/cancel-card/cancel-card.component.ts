import { Component } from '@angular/core';
// import { NzDividerModule } from 'ng-zorro-antd/divider'; // 导入 NzDividerModule


interface Person {
  customerId: string;
  fistName: string;
  lastName: string;
  gender: string;
  job: string;
  dob: string;
}

@Component({
  selector: 'app-cancel-card',
  templateUrl: './cancel-card.component.html',
  styleUrls: ['./cancel-card.component.css']
})
export class CancelCardComponent {
  listOfData: Person[] = [
    {
      customerId: '1',
      fistName: 'Amy',
      lastName: 'Wang',
      gender: 'Female',
      job: 'Teacher',
      dob: '1972-01-22'
    },
    {
      customerId: '2',
      fistName: 'Amy',
      lastName: 'Wang',
      gender: 'Female',
      job: 'Teacher',
      dob: '1972-01-22'
    },
    {
      customerId: '3',
      fistName: 'Amy',
      lastName: 'Wang',
      gender: 'Female',
      job: 'Teacher',
      dob: '1972-01-22'
    }
  ];
}

