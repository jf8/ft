import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDdBookPersonFt } from '@/shared/model/dd-book-person-ft.model';
import DdBookPersonFtService from './dd-book-person-ft.service';

@Component
export default class DdBookPersonFtDetails extends Vue {
  @Inject('ddBookPersonService') private ddBookPersonService: () => DdBookPersonFtService;
  public ddBookPerson: IDdBookPersonFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddBookPersonId) {
        vm.retrieveDdBookPersonFt(to.params.ddBookPersonId);
      }
    });
  }

  public retrieveDdBookPersonFt(ddBookPersonId) {
    this.ddBookPersonService()
      .find(ddBookPersonId)
      .then(res => {
        this.ddBookPerson = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
