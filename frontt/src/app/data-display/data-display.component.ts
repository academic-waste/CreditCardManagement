import { Component, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators, UntypedFormControl, } from '@angular/forms';
import { Data } from '@angular/router';
//获取数据服务
import { DataService } from '../services/in-transactions-data.service';
import { TransactionsItem, ColumnItem, TransactionsItemAll } from '../ulities/module'


@Component({
  selector: 'app-data-display',
  templateUrl: './data-display.component.html',
  styleUrls: ['./data-display.component.css']
})
export class DataDisplayComponent implements OnInit{

  
  constructor(private fb: UntypedFormBuilder,private dataService: DataService) {}
  
  validateForm!: UntypedFormGroup;
  //后端拿到的数据，表单接收到的数据跟后端拿到的数据应该无关
  listOfData: TransactionsItemAll[] = [];

  submitForm(): void {
    if (this.validateForm.valid) {
      console.log('submit', this.validateForm.value);
      this.listOfData = this.dataService.getFilteredData(this.validateForm.value);
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  resetForm(): void {
    this.validateForm.reset();
    this.listOfData = this.dataService.createDb().listOfData;
  }

  // get isHorizontal(): boolean {
  //   return this.validateForm.controls['formLayout']?.value === 'horizontal';
  // }


  ngOnInit(): void {
    this.listOfData = this.dataService.createDb().listOfData;
    this.validateForm = this.fb.group({
      gender: [null],
      category: [null],
      merchant: [null],
      city: [null],
      state: [null],
      job:[null],
      amt:[null]
    });
  }
  //Table just use to display data
  listOfColumn: ColumnItem[] = [
    {
      name: 'Gender'
      // sortOrder: null,
      // sortFn: (a: TransactionsItem, b: TransactionsItem) => a.gender.localeCompare(b.gender),
      // sortDirections: ['ascend', 'descend', null],
      // filterMultiple: true,
      // listOfFilter: [
      //   { text: 'Joe', value: 'Joe' },
      //   { text: 'Jim', value: 'Jim', byDefault: true }
      // ],
      // filterFn: (list: string[], item: TransactionsItem) => list.some(name => item.gender.indexOf(gender) !== -1)
    },
    {
      name: 'category',
      // sortOrder: 'descend',
      // sortFn: (a: TransactionsItem, b: TransactionsItem) => a.age - b.age,
      // sortDirections: ['descend', null],
      // listOfFilter: [],
      // filterFn: null,
      // filterMultiple: true
    },
    {
      name: 'merchant',
      // sortOrder: null,
      // sortDirections: ['ascend', 'descend', null],
      // sortFn: (a: DataItem, b: DataItem) => a.address.length - b.address.length,
      // filterMultiple: false,
      // listOfFilter: [
      //   { text: 'London', value: 'London' },
      //   { text: 'Sidney', value: 'Sidney' }
      // ],
      // filterFn: (address: string, item: DataItem) => item.address.indexOf(address) !== -1
    },
    {name: 'city'},
    {name: 'state'},
    {name: 'city population'},
    {name: 'job'},
    {name: 'dob'},
    {name: 'amt'},
  ];
}

//真实api
// export class DataDisplayComponent implements OnInit{

  
//   constructor(private fb: UntypedFormBuilder,private dataService: DataService) {}
//   loading: boolean = true;
//   validateForm!: UntypedFormGroup;
//   //后端拿到的数据，表单接收到的数据跟后端拿到的数据应该无关
//   listOfData: TransactionsItemAll[] =[];

//   submitForm(): void {
//     this.loading = true;
//     if (this.validateForm.valid) {
//       console.log('submit', this.validateForm.value);
//       this.dataService.getFilteredData(this.validateForm.value).subscribe(
//         (response: any)=> {
//           if(response.status == 302) {
//             const redirectUrl = response.header.get('Location');
//             this.dataService.getDataFromRedirect(redirectUrl).subscribe(
//               (actualResponse) => {
//                 console.log('filter',actualResponse);
//                 this.listOfData = actualResponse;
//                 this.loading = false;
//               },
//               error => {
//                 console.error('Error fetching data:', error);
//                 this.loading = false;
//               }
//             )
//           }else{
//             this.listOfData = response;
//             this.loading = false;
//           }
//         },
//         (error) => {
//           console.error('Error fetching data:', error);
//           this.loading = false;
//         })
//     } else {
//       Object.values(this.validateForm.controls).forEach(control => {
//         if (control.invalid) {
//           control.markAsDirty();
//           control.updateValueAndValidity({ onlySelf: true });
//         }
//       });
//     }
//   }

//   resetForm(): void {
//     this.validateForm.reset();
//     this.dataService.getTransactions().subscribe(data => {this.listOfData = data;this.loading = false});
//   }

//   // get isHorizontal(): boolean {
//   //   return this.validateForm.controls['formLayout']?.value === 'horizontal';
//   // }


//   ngOnInit(): void {
//     console.log("okkkk")
//     this.dataService.getTransactions().subscribe((data)=> {
//       console.log(111,data);
//       this.listOfData = data;
//       this.loading = false;
//     },
//     error => {
//     console.error('Error fetching data:', error);
//     });
//     this.validateForm = this.fb.group({
//       gender: [null],
//       category: [null],
//       merchant: [null],
//       city: [null],
//       state: [null],
//       job:[null],
//       amt:[null]
//     });
//   }
//   //Table just use to display data
//   listOfColumn: ColumnItem[] = [
//     {
//       name: 'Gender'
//       // sortOrder: null,
//       // sortFn: (a: TransactionsItem, b: TransactionsItem) => a.gender.localeCompare(b.gender),
//       // sortDirections: ['ascend', 'descend', null],
//       // filterMultiple: true,
//       // listOfFilter: [
//       //   { text: 'Joe', value: 'Joe' },
//       //   { text: 'Jim', value: 'Jim', byDefault: true }
//       // ],
//       // filterFn: (list: string[], item: TransactionsItem) => list.some(name => item.gender.indexOf(gender) !== -1)
//     },
//     {
//       name: 'category',
//       // sortOrder: 'descend',
//       // sortFn: (a: TransactionsItem, b: TransactionsItem) => a.age - b.age,
//       // sortDirections: ['descend', null],
//       // listOfFilter: [],
//       // filterFn: null,
//       // filterMultiple: true
//     },
//     {
//       name: 'merchant',
//       // sortOrder: null,
//       // sortDirections: ['ascend', 'descend', null],
//       // sortFn: (a: DataItem, b: DataItem) => a.address.length - b.address.length,
//       // filterMultiple: false,
//       // listOfFilter: [
//       //   { text: 'London', value: 'London' },
//       //   { text: 'Sidney', value: 'Sidney' }
//       // ],
//       // filterFn: (address: string, item: DataItem) => item.address.indexOf(address) !== -1
//     },
//     {name: 'city'},
//     {name: 'state'},
//     {name: 'city population'},
//     {name: 'job'},
//     {name: 'dob'},
//     {name: 'amt'},
    
//   ];
// }

