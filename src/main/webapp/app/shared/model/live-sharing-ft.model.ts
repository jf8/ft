import { IRankingDataFt } from '@/shared/model/ranking-data-ft.model';

export interface ILiveSharingFt {
  id?: number;
  name?: string;
  day?: Date;
  rankingData?: IRankingDataFt[];
}

export class LiveSharingFt implements ILiveSharingFt {
  constructor(public id?: number, public name?: string, public day?: Date, public rankingData?: IRankingDataFt[]) {}
}
