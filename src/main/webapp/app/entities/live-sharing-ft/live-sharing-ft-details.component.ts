import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILiveSharingFt } from '@/shared/model/live-sharing-ft.model';
import LiveSharingFtService from './live-sharing-ft.service';

@Component
export default class LiveSharingFtDetails extends Vue {
  @Inject('liveSharingService') private liveSharingService: () => LiveSharingFtService;
  public liveSharing: ILiveSharingFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.liveSharingId) {
        vm.retrieveLiveSharingFt(to.params.liveSharingId);
      }
    });
  }

  public retrieveLiveSharingFt(liveSharingId) {
    this.liveSharingService()
      .find(liveSharingId)
      .then(res => {
        this.liveSharing = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
