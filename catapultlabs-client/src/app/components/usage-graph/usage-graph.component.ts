import {Component, OnInit} from '@angular/core';
import {ReadingService} from "../../service/reading.service";
import {ChartDataSets, ChartType} from "chart.js";
import {Label} from 'ng2-charts';
import {Client} from "../client";

@Component({
  selector: 'app-usage-graph',
  templateUrl: './usage-graph.component.html',
  styleUrls: ['./usage-graph.component.css']
})
export class UsageGraphComponent implements OnInit {
  clients: Client[] | undefined;
  constructor(private readingService: ReadingService) {

  }
  public colorScheme = [
    'rgba(243, 166, 131,1.0)',
    'rgba(247, 215, 148,1.0)',
    'rgba(119, 139, 235,1.0)',
    'rgba(231, 127, 103,1.0)',
    'rgba(207, 106, 135,1.0)',
    'rgba(120, 111, 166,1.0)',
    'rgba(248, 165, 194,1.0)',
    'rgba(99, 205, 218,1.0)',
    'rgba(234, 134, 133,1.0)',
    'rgba(89, 98, 117,1.0)'
  ]

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };
  // @ts-ignore
  public barChartLabels: Label[] = []
  public barChartType: ChartType = 'line';
  public barChartLegend = true;
  // @ts-ignore
  public barChartData: ChartDataSets[] = []

  ngOnInit(): void {
    this.readingService.getClients().subscribe(
      (clients: Client[]) => {
        this.clients = clients
        this.barChartData.pop()
        clients.forEach(client => {
          client.meterList.forEach(meter => {
            this.barChartData.push({data: meter.readingList.map(item => item.reading), fill: false, label: client.name + ": " + meter.id.toString(), borderColor: this.colorScheme[meter.id-1], lineTension: 0.2})
          })
          this.barChartLabels = (clients[1].meterList[0].readingList.map(item => {
            var date = new Date(item.timestamp)
            return date.toTimeString()
          }))
        })

      }
    )
  }

}
