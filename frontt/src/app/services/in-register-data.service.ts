import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class InRegisterDataService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}
// 通过 post 方法将表单数据发送到后端的特定 API 端点
  sendData(data: any) {
    console.log(222,data)
    let queryParams = new HttpParams()
      for(const key in data){
        queryParams = queryParams.set(key, data[key])
      }
      console.log("jinru",queryParams);
    return this.http.post(`${this.apiUrl}/customer/create/`, { params: queryParams});
  }
}
