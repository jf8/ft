import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import LiveSharingFtService from '../live-sharing-ft/live-sharing-ft.service';
import { ILiveSharingFt } from '@/shared/model/live-sharing-ft.model';

import DdBookDeptFtService from '../dd-book-dept-ft/dd-book-dept-ft.service';
import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';

import AlertService from '@/shared/alert/alert.service';
import { IRankingDataFt, RankingDataFt } from '@/shared/model/ranking-data-ft.model';
import RankingDataFtService from './ranking-data-ft.service';

const validations: any = {
  rankingData: {
    name: {},
    totalPeople: {},
    signdPeople: {},
    attendance: {},
    orderNum: {},
    parentId: {},
    day: {},
    isLeaf: {},
  },
};

@Component({
  validations,
})
export default class RankingDataFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('rankingDataService') private rankingDataService: () => RankingDataFtService;
  public rankingData: IRankingDataFt = new RankingDataFt();

  @Inject('liveSharingService') private liveSharingService: () => LiveSharingFtService;

  public liveSharings: ILiveSharingFt[] = [];

  @Inject('ddBookDeptService') private ddBookDeptService: () => DdBookDeptFtService;

  public ddBookDepts: IDdBookDeptFt[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rankingDataId) {
        vm.retrieveRankingDataFt(to.params.rankingDataId);
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
    if (this.rankingData.id) {
      this.rankingDataService()
        .update(this.rankingData)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.rankingData.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.rankingDataService()
        .create(this.rankingData)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.rankingData.created', { param: param.id });
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
      this.rankingData[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.rankingData[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.rankingData[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.rankingData[field] = null;
    }
  }

  public retrieveRankingDataFt(rankingDataId): void {
    this.rankingDataService()
      .find(rankingDataId)
      .then(res => {
        res.day = new Date(res.day);
        this.rankingData = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.liveSharingService()
      .retrieve()
      .then(res => {
        this.liveSharings = res.data;
      });
    this.ddBookDeptService()
      .retrieve()
      .then(res => {
        this.ddBookDepts = res.data;
      });
  }
}
