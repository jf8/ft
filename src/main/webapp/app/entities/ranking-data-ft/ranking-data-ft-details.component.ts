import { Component, Vue, Inject } from 'vue-property-decorator';

import { IRankingDataFt } from '@/shared/model/ranking-data-ft.model';
import RankingDataFtService from './ranking-data-ft.service';

@Component
export default class RankingDataFtDetails extends Vue {
  @Inject('rankingDataService') private rankingDataService: () => RankingDataFtService;
  public rankingData: IRankingDataFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.rankingDataId) {
        vm.retrieveRankingDataFt(to.params.rankingDataId);
      }
    });
  }

  public retrieveRankingDataFt(rankingDataId) {
    this.rankingDataService()
      .find(rankingDataId)
      .then(res => {
        this.rankingData = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
