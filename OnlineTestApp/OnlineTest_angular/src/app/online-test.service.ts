import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { OnlineTest } from './online-test';

@Injectable({
  providedIn: 'root'
})

export class OnlineTestService {

  public attemptCount:number=0;
  private url:string;
  constructor(private http:HttpClient) { 
    this.url="http://localhost:9100/quiz";
  }

  public getAllQuesAns(): Observable<OnlineTest[]> {
    return this.http.get<OnlineTest[]>(this.url+"zes");
  }

  public getQuizById(id:number):Observable<any>
  {
    return this.http.get(`${this.url}/${id}`);
  }

  public updateUserAnswer(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.url}/${id}`, value);
  }

  public getResult(value: any){
    return this.http.put(this.url+"/result", value);
  }
}
