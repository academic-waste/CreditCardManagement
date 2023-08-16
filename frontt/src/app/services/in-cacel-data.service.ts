import { Injectable } from '@angular/core';
import { cacelData } from '../ulities/module'
// import { InMemoryDbService } from 'angular-in-memory-web-api';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class CancelDataService {
  private apiUrl = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) {}

  getItems() {
    return this.http.get(`${this.apiUrl}`);
  }

  deleteItem(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
//假数据
// export class CancelDataService implements InMemoryDbService {

//   private eachCancelData: cacelData[] = [
//     {
//       customerId: 1,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 2,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 3,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 4,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 5,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 6,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 7,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 8,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 9,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 10,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     },
//     {
//       customerId: 11,
//       fistName: 'Amy',
//       lastName: 'Wang',
//       gender: 'Female',
//       job: 'Teacher',
//       dob: '1972-01-22'
//     }

//   ];
//   constructor() { }

//   createDb() {
//     const listOfCustomerData = this.eachCancelData;
//     return { listOfCustomerData };
//   }
//   deleteItem(id: number): void {
//     // const index = this.eachCancelData.findIndex(item => item.customerId === id);
//     // 把需要注销账户的customerId传给后端
//     const index = id;
//     console.log(index);
//     if (index !== -1) {
//       this.eachCancelData.splice(index, 1);
//       console.log("delete");
//     }
//     console.log("Cancel a card")
//   }
// }
