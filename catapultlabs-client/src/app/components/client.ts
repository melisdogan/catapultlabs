import {Meter} from "./meter";

export interface Client {
  id: number
  name: string
  meterList: Meter[]
}
