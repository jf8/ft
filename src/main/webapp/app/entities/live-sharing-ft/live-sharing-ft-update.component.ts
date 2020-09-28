import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import RankingDataFtService from '../ranking-data-ft/ranking-data-ft.service';
import { IRankingDataFt } from '@/shared/model/ranking-data-ft.model';

import AlertService from '@/shared/alert/alert.service';
import { ILiveSharingFt, LiveSharingFt } from '@/shared/model/live-sharing-ft.model';
import LiveSharingFtService from './live-sharing-ft.service';

const validations: any = {
  liveSharing: {
    name: {},
    day: {},
  },
};

@Component({
  validations,
})
export default class LiveSharingFtUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('liveSharingService') private liveSharingService: () => LiveSharingFtService;
  public liveSharing: ILiveSharingFt = new LiveSharingFt();

  @Inject('rankingDataService') private rankingDataService: () => RankingDataFtService;

  public rankingData: IRankingDataFt[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.liveSharingId) {
        vm.retrieveLiveSharingFt(to.params.liveSharingId);
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
    if (this.liveSharing.id) {
      this.liveSharingService()
        .update(this.liveSharing)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.liveSharing.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.liveSharingService()
        .create(this.liveSharing)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ftApp.liveSharing.created', { param: param.id });
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
      this.liveSharing[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.liveSharing[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.liveSharing[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.liveSharing[field] = null;
    }
  }

  public retrieveLiveSharingFt(liveSharingId): void {
    this.liveSharingService()
      .find(liveSharingId)
      .then(res => {
        res.day = new Date(res.day);
        this.liveSharing = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.rankingDataService()
      .retrieve()
      .then(res => {
        this.rankingData = res.data;
      });
  }
}
