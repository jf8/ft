import { ILiveSharingFt } from '@/shared/model/live-sharing-ft.model';

export interface IRankingDataFt {
  id?: number;
  name?: string;
  totalPeople?: number;
  signdPeople?: number;
  attendance?: number;
  orderNum?: number;
  parentId?: number;
  day?: Date;
  isLeaf?: boolean;
  liveSharing?: ILiveSharingFt;
}

export class RankingDataFt implements IRankingDataFt {
  constructor(
    public id?: number,
    public name?: string,
    public totalPeople?: number,
    public signdPeople?: number,
    public attendance?: number,
    public orderNum?: number,
    public parentId?: number,
    public day?: Date,
    public isLeaf?: boolean,
    public liveSharing?: ILiveSharingFt
  ) {
    this.isLeaf = this.isLeaf || false;
  }
}
