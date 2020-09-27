import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { IVFtUserSignInfoFt, VFtUserSignInfoFt } from '@/shared/model/v-ft-user-sign-info-ft.model';
import VFtUserSignInfoFtService from './v-ft-user-sign-info-ft.service';

const validations: any = {
  vFtUserSignInfo: {
    phoneCode: {},
    phone: {},
    email: {},
    seat: {},
    groupIds: {},
    startTime: {},
    endTime: {},
    nameCn: {},
    nameEn: {},
    companyCn: {},
    companyEn: {},
    titleCn: {},
    titleEn: {},
    remark: {},
    ddid: {},
    updateTime: {},
    createTime: {},
    signTime: {},
    meetId: {},
  },
};

@Component({
  validations,
})
export default class VFtUserSignInfoFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('vFtUserSignInfoService') private vFtUserSignInfoService: () => VFtUserSignInfoFtService;
  public vFtUserSignInfo: IVFtUserSignInfoFt = new VFtUserSignInfoFt();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vFtUserSignInfoId) {
        vm.retrieveVFtUserSignInfoFt(to.params.vFtUserSignInfoId);
      }
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
    if (this.vFtUserSignInfo.id) {
      this.vFtUserSignInfoService()
        .update(this.vFtUserSignInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.vFtUserSignInfo.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.vFtUserSignInfoService()
        .create(this.vFtUserSignInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.vFtUserSignInfo.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.vFtUserSignInfo[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.vFtUserSignInfo[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.vFtUserSignInfo[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.vFtUserSignInfo[field] = null;
    }
  }

  public retrieveVFtUserSignInfoFt(vFtUserSignInfoId): void {
    this.vFtUserSignInfoService()
      .find(vFtUserSignInfoId)
      .then(res => {
        res.startTime = new Date(res.startTime);
        res.endTime = new Date(res.endTime);
        res.updateTime = new Date(res.updateTime);
        res.createTime = new Date(res.createTime);
        res.signTime = new Date(res.signTime);
        this.vFtUserSignInfo = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
