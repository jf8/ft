import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDdBookDeptFt } from '@/shared/model/dd-book-dept-ft.model';
import DdBookDeptFtService from './dd-book-dept-ft.service';

@Component
export default class DdBookDeptFtDetails extends Vue {
  @Inject('ddBookDeptService') private ddBookDeptService: () => DdBookDeptFtService;
  public ddBookDept: IDdBookDeptFt = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddBookDeptId) {
        vm.retrieveDdBookDeptFt(to.params.ddBookDeptId);
      }
    });
  }

  public retrieveDdBookDeptFt(ddBookDeptId) {
    this.ddBookDeptService()
      .find(ddBookDeptId)
      .then(res => {
        this.ddBookDept = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
