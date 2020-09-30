/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LiveSharingFtDetailComponent from '@/entities/live-sharing-ft/live-sharing-ft-details.vue';
import LiveSharingFtClass from '@/entities/live-sharing-ft/live-sharing-ft-details.component';
import LiveSharingFtService from '@/entities/live-sharing-ft/live-sharing-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('LiveSharingFt Management Detail Component', () => {
    let wrapper: Wrapper<LiveSharingFtClass>;
    let comp: LiveSharingFtClass;
    let liveSharingServiceStub: SinonStubbedInstance<LiveSharingFtService>;

    beforeEach(() => {
      liveSharingServiceStub = sinon.createStubInstance<LiveSharingFtService>(LiveSharingFtService);

      wrapper = shallowMount<LiveSharingFtClass>(LiveSharingFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { liveSharingService: () => liveSharingServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLiveSharingFt = { id: 123 };
        liveSharingServiceStub.find.resolves(foundLiveSharingFt);

        // WHEN
        comp.retrieveLiveSharingFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.liveSharing).toBe(foundLiveSharingFt);
      });
    });
  });
});
