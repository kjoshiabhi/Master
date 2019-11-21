import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

export class HistoryLog {
  id: number;
  log_id: number;
  action: number;
  user_id: string;
  dateOfChange: string;
  entity_class: string;
  entity_id: string;
}

@Injectable({
  providedIn: 'root'
})
export class HttpclientService {

  
  

  constructor(
    private httpClient: HttpClient

  ) { }

  getLogs() {
    let username = localStorage.getItem("username");
    let password = localStorage.getItem("password");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa("dwe" + ':' + "dwe") });
    return this.httpClient.get<any[]>
    ('http://localhost:9091/backendservice/getLogs', { headers }).pipe(
      map((obj: any) => {
        return obj;
      }), catchError( error => {
        return throwError( 'Something went wrong!' );
      })
    )}
}
