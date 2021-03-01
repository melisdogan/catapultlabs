import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Client} from "../components/client";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class ReadingService {
  constructor(private http: HttpClient, private cookieService: CookieService ) { }
  public getClients(): Observable<Client[]>{
    // @ts-ignore
    return this.http.get<Client[]>('http://localhost:8080'+'/get-readings', {headers : new HttpHeaders().set('x-auth-token', this.cookieService.get("token"))});
  }
}
