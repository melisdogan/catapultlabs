import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UsageGraphComponent} from './components/usage-graph/usage-graph.component';
import {ChartsModule} from 'ng2-charts';
import {ReadingService} from "./service/reading.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    UsageGraphComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ChartsModule,
    HttpClientModule
  ],
  providers: [ReadingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
