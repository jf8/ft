import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';
import { IDdBookPersonFt } from '@/shared/model/dd-book-person-ft.model';
import { IDdUserFt } from '@/shared/model/dd-user-ft.model';

export interface IDdBookDeptFt {
  id?: number;
  name?: string;
  orderNum?: number;
  parentid?: number;
  createDeptGroup?: boolean;
  autoAddUser?: boolean;
  deptHiding?: boolean;
  deptPermits?: string;
  userPermits?: string;
  outerDept?: boolean;
  outerPermitDepts?: string;
  outerPermitUsers?: string;
  orgDeptOwner?: string;
  deptManagerUseridList?: string;
  sourceIdentifier?: string;
  ext?: string;
  ddBookDepts?: IDdBookDeptFt[];
  ddBookPeople?: IDdBookPersonFt[];
  ddBookDept?: IDdBookDeptFt;
  ddUsers?: IDdUserFt[];
}

export class DdBookDeptFt implements IDdBookDeptFt {
  constructor(
    public id?: number,
    public name?: string,
    public orderNum?: number,
    public parentid?: number,
    public createDeptGroup?: boolean,
    public autoAddUser?: boolean,
    public deptHiding?: boolean,
    public deptPermits?: string,
    public userPermits?: string,
    public outerDept?: boolean,
    public outerPermitDepts?: string,
    public outerPermitUsers?: string,
    public orgDeptOwner?: string,
    public deptManagerUseridList?: string,
    public sourceIdentifier?: string,
    public ext?: string,
    public ddBookDepts?: IDdBookDeptFt[],
    public ddBookPeople?: IDdBookPersonFt[],
    public ddBookDept?: IDdBookDeptFt,
    public ddUsers?: IDdUserFt[]
  ) {
    this.createDeptGroup = this.createDeptGroup || false;
    this.autoAddUser = this.autoAddUser || false;
    this.deptHiding = this.deptHiding || false;
    this.outerDept = this.outerDept || false;
  }
}
