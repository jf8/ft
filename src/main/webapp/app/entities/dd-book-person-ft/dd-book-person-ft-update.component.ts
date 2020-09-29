import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import DdBookDeptFtService from '../dd-book-dept-ft/dd-book-dept-ft.service';
import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

import AlertService from '@/shared/alert/alert.service';
import { IDdBookPersonFt, DdBookPersonFt } from '@/shared/model/dd-book-person-ft.model';
import DdBookPersonFtService from './dd-book-person-ft.service';

const validations: any = {
  ddBookPerson: {
    unionid: {},
    remark: {},
    userid: {},
    isLeaderInDepts: {},
    isBoss: {},
    hiredDate: {},
    isSenior: {},
    tel: {},
    department: {},
    email: {},
    workPlace: {},
    orderInDepts: {},
    mobile: {},
    errmsg: {},
    active: {},
    avatar: {},
    isAdmin: {},
    isHide: {},
    jobnumber: {},
    name: {},
    extattr: {},
    stateCode: {},
    position: {},
    roles: {},
    parentDeptsIdList: {},
  },
};

@Component({
  validations,
})
export default class DdBookPersonFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('ddBookPersonService') private ddBookPersonService: () => DdBookPersonFtService;
  public ddBookPerson: IDdBookPersonFt = new DdBookPersonFt();

  @Inject('ddBookDeptService') private ddBookDeptService: () => DdBookDeptFtService;

  public ddBookDepts: IDdBookDeptFt[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddBookPersonId) {
        vm.retrieveDdBookPersonFt(to.params.ddBookPersonId);
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
  }

  public save(): void {
    this.isSaving = true;
    if (this.ddBookPerson.id) {
      this.ddBookPersonService()
        .update(this.ddBookPerson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddBookPerson.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.ddBookPersonService()
        .create(this.ddBookPerson)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddBookPerson.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDdBookPersonFt(ddBookPersonId): void {
    this.ddBookPersonService()
      .find(ddBookPersonId)
      .then(res => {
        this.ddBookPerson = res;
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
  }
}
