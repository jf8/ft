export interface IVFtUserSignInfoFt {
  id?: number;
  phoneCode?: string;
  phone?: string;
  email?: string;
  seat?: string;
  groupIds?: string;
  startTime?: Date;
  endTime?: Date;
  nameCn?: string;
  nameEn?: string;
  companyCn?: string;
  companyEn?: string;
  titleCn?: string;
  titleEn?: string;
  remark?: string;
  ddid?: string;
  updateTime?: Date;
  createTime?: Date;
  signTime?: Date;
  meetId?: number;
}

export class VFtUserSignInfoFt implements IVFtUserSignInfoFt {
  constructor(
    public id?: number,
    public phoneCode?: string,
    public phone?: string,
    public email?: string,
    public seat?: string,
    public groupIds?: string,
    public startTime?: Date,
    public endTime?: Date,
    public nameCn?: string,
    public nameEn?: string,
    public companyCn?: string,
    public companyEn?: string,
    public titleCn?: string,
    public titleEn?: string,
    public remark?: string,
    public ddid?: string,
    public updateTime?: Date,
    public createTime?: Date,
    public signTime?: Date,
    public meetId?: number
  ) {}
}
