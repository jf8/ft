import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

export interface IDdBookPersonFt {
  id?: number;
  unionid?: string;
  remark?: string;
  userid?: string;
  isLeaderInDepts?: string;
  isBoss?: boolean;
  hiredDate?: number;
  isSenior?: boolean;
  tel?: string;
  department?: string;
  email?: string;
  workPlace?: string;
  orderInDepts?: string;
  mobile?: string;
  errmsg?: string;
  active?: boolean;
  avatar?: string;
  isAdmin?: boolean;
  isHide?: boolean;
  jobnumber?: string;
  name?: string;
  extattr?: string;
  stateCode?: string;
  position?: string;
  roles?: string;
  parentDeptsIdList?: string;
  depts?: IDdBookDeptFt[];
}

export class DdBookPersonFt implements IDdBookPersonFt {
  constructor(
    public id?: number,
    public unionid?: string,
    public remark?: string,
    public userid?: string,
    public isLeaderInDepts?: string,
    public isBoss?: boolean,
    public hiredDate?: number,
    public isSenior?: boolean,
    public tel?: string,
    public department?: string,
    public email?: string,
    public workPlace?: string,
    public orderInDepts?: string,
    public mobile?: string,
    public errmsg?: string,
    public active?: boolean,
    public avatar?: string,
    public isAdmin?: boolean,
    public isHide?: boolean,
    public jobnumber?: string,
    public name?: string,
    public extattr?: string,
    public stateCode?: string,
    public position?: string,
    public roles?: string,
    public parentDeptsIdList?: string,
    public depts?: IDdBookDeptFt[]
  ) {
    this.isBoss = this.isBoss || false;
    this.isSenior = this.isSenior || false;
    this.active = this.active || false;
    this.isAdmin = this.isAdmin || false;
    this.isHide = this.isHide || false;
  }
}
