import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class AppService {

  authenticated = false;

  constructor(private http: HttpClient, private cookieService: CookieService) {
  }

  // @ts-ignore
  authenticate(credentials, callback: () => any) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('api/user', {headers: headers}).subscribe(response => {
      // @ts-ignore
      if (response['name']) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }
  // @ts-ignore
  getToken(credentials, callback: () => any) {
    const headers = new HttpHeaders(credentials ? {
      authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});

    this.http.get('api/token', {headers: headers}).subscribe(response => {
      // @ts-ignore
      this.cookieService.set("token", response["token"])
      return callback && callback();
    });

  }

}
