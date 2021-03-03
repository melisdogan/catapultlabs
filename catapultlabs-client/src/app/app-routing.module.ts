import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsageGraphComponent} from "./components/usage-graph/usage-graph.component";
import {LoginComponent} from "./components/login/login.component";

const routes: Routes = [
  { path: 'get-readings-chart', component: UsageGraphComponent},
  { path: 'login', component: LoginComponent},
  { path: '',   redirectTo: '/get-readings-chart', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
