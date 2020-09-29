import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import DdBookDeptFtService from '../dd-book-dept-ft/dd-book-dept-ft.service';
import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

import AlertService from '@/shared/alert/alert.service';
import { IDdUserFt, DdUserFt } from '@/shared/model/dd-user-ft.model';
import DdUserFtService from './dd-user-ft.service';

const validations: any = {
  ddUser: {
    unionid: {},
    remark: {},
    userid: {},
    isLeaderInDepts: {},
    isBoss: {},
    hiredDate: {},
    isSenior: {},
    tel: {},
    department: {},
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
  },
};

@Component({
  validations,
})
export default class DdUserFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('ddUserService') private ddUserService: () => DdUserFtService;
  public ddUser: IDdUserFt = new DdUserFt();

  @Inject('ddBookDeptService') private ddBookDeptService: () => DdBookDeptFtService;

  public ddBookDepts: IDdBookDeptFt[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddUserId) {
        vm.retrieveDdUserFt(to.params.ddUserId);
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
    this.ddUser.ddBookDepts = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.ddUser.id) {
      this.ddUserService()
        .update(this.ddUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddUser.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.ddUserService()
        .create(this.ddUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.ddUser.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDdUserFt(ddUserId): void {
    this.ddUserService()
      .find(ddUserId)
      .then(res => {
        this.ddUser = res;
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
