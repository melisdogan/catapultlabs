import {Meter} from "./meter";

export interface MeterReading {
  id: number;
  timestamp: number;
  reading: number;
  meter: Meter;
}
