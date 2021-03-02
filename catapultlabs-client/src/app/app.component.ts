import {Component} from '@angular/core';
import {AppService} from "./service/app.service";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'catapultlabs-client';
  constructor(private app: AppService, private cookieService: CookieService) {
    if(!cookieService.get("token")) {
      this.app.authenticate(undefined, undefined!);
    } else {
      this.app.authenticated = true
    }
  }
}
