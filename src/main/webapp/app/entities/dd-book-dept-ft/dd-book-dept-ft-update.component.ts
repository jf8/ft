import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import DdBookPersonFtService from '../dd-book-person-ft/dd-book-person-ft.service';
import { IDdBookPersonFt } from '@/shared/model/dd-book-person-ft.model';

import DdUserFtService from '../dd-user-ft/dd-user-ft.service';
import { IDdUserFt } from '@/shared/model/dd-user-ft.model';

import AlertService from '@/shared/alert/alert.service';
import { IDdBookDeptFt, DdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';
import DdBookDeptFtService from './dd-book-dept-ft.service';

const validations: any = {
  ddBookDept: {
    name: {},
    orderNum: {},
    parentid: {},
    createDeptGroup: {},
    autoAddUser: {},
    deptHiding: {},
    deptPermits: {},
    userPermits: {},
    outerDept: {},
    outerPermitDepts: {},
    outerPermitUsers: {},
    orgDeptOwner: {},
    deptManagerUseridList: {},
    sourceIdentifier: {},
    ext: {},
  },
};

@Component({
  validations,
})
export default class DdBookDeptFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('ddBookDeptService') private ddBookDeptService: () => DdBookDeptFtService;
  public ddBookDept: IDdBookDeptFt = new DdBookDeptFt();

  public ddBookDepts: IDdBookDeptFt[] = [];

  @Inject('ddBookPersonService') private ddBookPersonService: () => DdBookPersonFtService;

  public ddBookPeople: IDdBookPersonFt[] = [];

  @Inject('ddUserService') private ddUserService: () => DdUserFtService;

  public ddUsers: IDdUserFt[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddBookDeptId) {
        vm.retrieveDdBookDeptFt(to.params.ddBookDeptId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
    this.ddBookDept.ddBookPeople = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.ddBookDept.id) {
      this.ddBookDeptService()
        .update(this.ddBookDept)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddBookDept.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.ddBookDeptService()
        .create(this.ddBookDept)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddBookDept.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDdBookDeptFt(ddBookDeptId): void {
    this.ddBookDeptService()
      .find(ddBookDeptId)
      .then(res => {
        this.ddBookDept = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.ddBookDeptService()
      .retrieve()
      .then(res => {
        this.ddBookDepts = res.data;
      });
    this.ddBookPersonService()
      .retrieve()
      .then(res => {
        this.ddBookPeople = res.data;
      });
    this.ddBookDeptService()
      .retrieve()
      .then(res => {
        this.ddBookDepts = res.data;
      });
    this.ddUserService()
      .retrieve()
      .then(res => {
        this.ddUsers = res.data;
      });
  }

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
