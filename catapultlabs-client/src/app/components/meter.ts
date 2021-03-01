import {MeterReading} from "./meter-reading";
import {Client} from "./client";

export interface Meter {
  id: number
  readingList: MeterReading[]
  client: Client
}
