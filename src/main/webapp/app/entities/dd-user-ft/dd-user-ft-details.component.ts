import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDdUserFt } from '@/shared/model/dd-user-ft.model';
import DdUserFtService from './dd-user-ft.service';

@Component
export default class DdUserFtDetails extends Vue {
  @Inject('ddUserService') private ddUserService: () => DdUserFtService;
  public ddUser: IDdUserFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddUserId) {
        vm.retrieveDdUserFt(to.params.ddUserId);
      }
    });
  }

  public retrieveDdUserFt(ddUserId) {
    this.ddUserService()
      .find(ddUserId)
      .then(res => {
        this.ddUser = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
