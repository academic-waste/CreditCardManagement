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