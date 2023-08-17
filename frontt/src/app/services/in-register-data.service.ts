import { Injectable } from '@angular/core';
import { HttpClient, HttpParams,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InRegisterDataService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}
// 通过 post 方法将表单数据发送到后端的特定 API 端点
// postData(data: any): Observable<any> {
//   const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
//   // 使用 post 方法发送 POST 请求，第一个参数是 URL，第二个参数是请求体
//   return this.http.post<any>(`${this.apiUrl}/customer/create`, data, { headers:{'Content-Type':'application/json',responseType:'text'} });
// }

postData(data: any): Observable<string> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  
  return this.http.post<string>(`${this.apiUrl}/customer/create`, data, { headers: headers, responseType: 'text' as 'json' });
}


  // sendData(data: any) {
  //   console.log(222,data)
  //   let queryParams = new HttpParams()
  //     for(const key in data){
  //       queryParams = queryParams.set(key, data[key])
  //     }
  //     console.log("jinru",queryParams);
  //   return this.http.post(`${this.apiUrl}/customer/create`, { params: queryParams});
  // }
}
