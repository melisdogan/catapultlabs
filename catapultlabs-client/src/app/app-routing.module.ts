import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsageGraphComponent} from "./components/usage-graph/usage-graph.component";

const routes: Routes = [
  { path: 'get-readings-chart', component: UsageGraphComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
