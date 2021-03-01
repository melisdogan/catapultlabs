import {Component} from '@angular/core';
import {AppService} from "./service/app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'catapultlabs-client';
  constructor(private app: AppService) {
    this.app.authenticate(undefined, undefined!);
  }
}
