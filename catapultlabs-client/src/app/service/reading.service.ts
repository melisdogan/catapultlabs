import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../components/client";

@Injectable({
  providedIn: 'root'
})
export class ReadingService {

  constructor(private http: HttpClient) { }
  public getClients(): Observable<Client[]>{
    return this.http.get<Client[]>('http://localhost:8080'+'/get-readings');
  }
}
