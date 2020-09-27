import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVFtUserSignInfoFt } from '@/shared/model/v-ft-user-sign-info-ft.model';
import VFtUserSignInfoFtService from './v-ft-user-sign-info-ft.service';

@Component
export default class VFtUserSignInfoFtDetails extends Vue {
  @Inject('vFtUserSignInfoService') private vFtUserSignInfoService: () => VFtUserSignInfoFtService;
  public vFtUserSignInfo: IVFtUserSignInfoFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vFtUserSignInfoId) {
        vm.retrieveVFtUserSignInfoFt(to.params.vFtUserSignInfoId);
      }
    });
  }

  public retrieveVFtUserSignInfoFt(vFtUserSignInfoId) {
    this.vFtUserSignInfoService()
      .find(vFtUserSignInfoId)
      .then(res => {
        this.vFtUserSignInfo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
