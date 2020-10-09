import { ILiveSharingFt } from '@/shared/model/live-sharing-ft.model';
import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

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
  ddBookDept?: IDdBookDeptFt;
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
    public liveSharing?: ILiveSharingFt,
    public ddBookDept?: IDdBookDeptFt
  ) {
    this.isLeaf = this.isLeaf || false;
  }
}
