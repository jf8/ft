/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import VFtUserSignInfoFtDetailComponent from '@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft-details.vue';
import VFtUserSignInfoFtClass from '@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft-details.component';
import VFtUserSignInfoFtService from '@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('VFtUserSignInfoFt Management Detail Component', () => {
    let wrapper: Wrapper<VFtUserSignInfoFtClass>;
    let comp: VFtUserSignInfoFtClass;
    let vFtUserSignInfoServiceStub: SinonStubbedInstance<VFtUserSignInfoFtService>;

    beforeEach(() => {
      vFtUserSignInfoServiceStub = sinon.createStubInstance<VFtUserSignInfoFtService>(VFtUserSignInfoFtService);

      wrapper = shallowMount<VFtUserSignInfoFtClass>(VFtUserSignInfoFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { vFtUserSignInfoService: () => vFtUserSignInfoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVFtUserSignInfoFt = { id: 123 };
        vFtUserSignInfoServiceStub.find.resolves(foundVFtUserSignInfoFt);

        // WHEN
        comp.retrieveVFtUserSignInfoFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.vFtUserSignInfo).toBe(foundVFtUserSignInfoFt);
      });
    });
  });
});
