import {Component, OnInit, ViewChild} from '@angular/core';
import {ReadingService} from "../../service/reading.service";
import {ChartDataSets, ChartOptions, ChartType, ChartPoint} from "chart.js";
import {BaseChartDirective, Label} from 'ng2-charts';
import {Client} from "../client";
import {AppService} from "../../service/app.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler/src/util";

@Component({
  selector: 'app-usage-graph',
  templateUrl: './usage-graph.component.html',
  styleUrls: ['./usage-graph.component.css']
})
export class UsageGraphComponent implements OnInit {
  @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;
  clients: Client[] | undefined;
  isFiltered = false
  constructor(private readingService: ReadingService, private appService: AppService, private router: Router) {

  }
  authenticated() { return this.appService.authenticated; }
  showUserData(client: Client) {
    this.chartData.forEach(data => {
      data.hidden = !data.label?.includes(client.name + ": ");
    })
    this.isFiltered = true
    this.chart?.chart.update()
  }
  removeFilter(){
    this.chartData.forEach(data => {
      data.hidden = false
    })
    this.isFiltered = false
    this.chart?.chart.update()
  }
  changeDateFilter(isToday: boolean){
    this.chartData = []
    this.chartLabels = []
    // @ts-ignore
    this.clients.forEach((client, index) => {
      client.meterList.forEach((meter) => {
        this.chartData.push({
          hidden: false,
          borderColor: this.chartColorScheme[index%10],
          data: meter.readingList
            .filter(item => {
              return !isToday || new Date(item.timestamp).toDateString() === new Date().toDateString()
            })
            .map(item => (
              {x: new Date(item.timestamp), y: item.reading}
            )),
          fill: false,
          label: client.name + ": " + meter.id.toString(),
          lineTension: 0.2})
      })

    })
    this.chart?.chart.update()
  }

  public chartOptions: ChartOptions = {
    responsive: true,
    legend: {
      labels: { fontColor: 'white', fontFamily: 'Fira Sans' }
    },
    tooltips: {
      mode: 'nearest',
      titleFontFamily: 'Fira Sans',
      bodyFontFamily: 'Fira Sans'
    },
    scales: {
      xAxes: [{
        type: 'time',
        distribution: 'series',
        time: {
          round: 'minute',
          unit: 'hour',
          displayFormats: {
            hour: 'h:mm a'
          }
        },
        ticks: {
          fontColor: 'white',
          fontFamily: 'Fira Sans'
        }
      }],
      yAxes: [{
        ticks: { fontColor: 'white', fontFamily: 'Fira Sans' }
      }]
    }
  };
  // @ts-ignore
  public chartLabels: Label[] = []
  public chartType: ChartType = 'line';
  public chartLegend = true;
  // @ts-ignore
  public chartData: ChartDataSets[] = []
  public chartColorScheme: string[] = [
    'rgba(243, 166, 131,1)',
    'rgba(247, 215, 148,1)',
    'rgba(119, 139, 235,1)',
    'rgba(231, 127, 103,1)',
    'rgba(207, 106, 135,1)',
    'rgba(120, 111, 166,1)',
    'rgba(248, 165, 194,1)',
    'rgba(99, 205, 218,1)',
    'rgba(234, 134, 133,1)',
    'rgba(89, 98, 117,1)'
  ];
  ngOnInit(): void {
      // @ts-ignore
      this.readingService.getClients().subscribe(
        (clients: Client[]) => {
          this.clients = clients
          this.chartData.pop()
          clients.forEach((client, index) => {
            client.meterList.forEach((meter) => {
              this.chartData.push({hidden: false, borderColor: this.chartColorScheme[index%10], data: meter.readingList.map(item => (
                  {x: new Date(item.timestamp), y: item.reading}
                )), fill: false, label: client.name + ": " + meter.id.toString(), lineTension: 0.2})
            })
          })
        },
        error => {
          this.router.navigateByUrl('/login');
        }
      )
  }

}
