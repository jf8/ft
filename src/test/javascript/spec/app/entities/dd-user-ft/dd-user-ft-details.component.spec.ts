/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DdUserFtDetailComponent from '@/entities/dd-user-ft/dd-user-ft-details.vue';
import DdUserFtClass from '@/entities/dd-user-ft/dd-user-ft-details.component';
import DdUserFtService from '@/entities/dd-user-ft/dd-user-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DdUserFt Management Detail Component', () => {
    let wrapper: Wrapper<DdUserFtClass>;
    let comp: DdUserFtClass;
    let ddUserServiceStub: SinonStubbedInstance<DdUserFtService>;

    beforeEach(() => {
      ddUserServiceStub = sinon.createStubInstance<DdUserFtService>(DdUserFtService);

      wrapper = shallowMount<DdUserFtClass>(DdUserFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { ddUserService: () => ddUserServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDdUserFt = { id: 123 };
        ddUserServiceStub.find.resolves(foundDdUserFt);

        // WHEN
        comp.retrieveDdUserFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ddUser).toBe(foundDdUserFt);
      });
    });
  });
});
