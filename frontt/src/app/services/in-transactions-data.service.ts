import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StateSum, TransactionsItem, TransactionsItemAll,StateCatgoryData } from '../ulities/module'
import { transition } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
//真实API
export class DataService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getStateSumDate(category:string) : Observable<StateSum[]>{
    return this.http.get<StateSum[]>(`${this.baseUrl}/transaction/analysisByState`)
  }

  getStateCatData(stateName:string): Observable<StateCatgoryData[]>{
    const para = {state:stateName}
    return this.http.get<StateCatgoryData[]>(`${this.baseUrl}/transaction/analysisByCategory`,{params:para});
  }

  getTransactions(): Observable<TransactionsItemAll[]> {
    return this.http.get<TransactionsItemAll[]>(`${this.baseUrl}/transaction`);
  }

  getDataFromRedirect(redirectUrl: string): Observable<any> {
    return this.http.get<any>(redirectUrl);
  }


    getFilteredData(searchTerm: TransactionsItem) : Observable<TransactionsItemAll[]> {
      const requestParams : TransactionsItem = {
        gender: searchTerm.gender || " ",
        category: searchTerm.category || " ",
        merchant: searchTerm.merchant || " ",
        city: searchTerm.city || " ",
        state: searchTerm.state || " ",
        job:searchTerm.job || " ",
        amt:searchTerm.amt || -1,
      }
      let queryParams = new HttpParams()
      for(const key in requestParams){
        queryParams = queryParams.set(key, requestParams[key])
      }
      console.log("jinru");
      
      return this.http.get<TransactionsItemAll[]>(`${this.baseUrl}/transaction/find`,{ params: queryParams});

      // return this.http.get(`transaction/find`, requestBody);
    }
  }

//假数据
// export class DataService implements InMemoryDbService {

//   private transactions : TransactionsItemAll[] = [
//     {
//       gender: "F",
//       category: "entertainment",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "M",
//       category: "work",
//       merchant: "apple",
//       city: "ch",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "entertainment",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     },
//     {
//       gender: "F",
//       category: "work",
//       merchant: "Abbott-Rogan",
//       city: "sh",
//       state: "OK",
//       cityPopulation: 100,
//       amt: 1,
//       transDateTransTime:'1',
//       transNum:1,
//       first:'string',
//       last:'string',
//       job:'string',
//       dob:'string'
//     }
//   ];

//   createDb() {
//     const listOfData=this.transactions 
//     return { listOfData };
//   }

//   getFilteredData(searchTerm: TransactionsItem): TransactionsItemAll[] {
//     //参数改值的
//     //先对searchTerm进行处理，即null改成空格这些
//     //然后再加到api上获取数据
    
//       return this.transactions.filter(transition => {
//         return (
//           (!searchTerm.gender || transition.gender.toLowerCase() === searchTerm.gender.toLowerCase()) &&
//           (!searchTerm.category || transition.category.toLowerCase().includes(searchTerm.category.toLowerCase())) &&
//           (!searchTerm.merchant || transition.merchant.toLowerCase().includes(searchTerm.merchant.toLowerCase())) &&
//           (!searchTerm.city || transition.city.toLowerCase().includes(searchTerm.city.toLowerCase())) &&
//           (!searchTerm.state || transition.state.toLowerCase().includes(searchTerm.state.toLowerCase()))&&
//           (!searchTerm.job || transition.job.toLowerCase().includes(searchTerm.job.toLowerCase())) &&
//           (!searchTerm.amt || transition.amt == searchTerm.amt)
//         );
//       });
//     }

    
//   }

