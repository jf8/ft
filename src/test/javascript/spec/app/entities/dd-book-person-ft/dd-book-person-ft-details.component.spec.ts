/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DdBookPersonFtDetailComponent from '@/entities/dd-book-person-ft/dd-book-person-ft-details.vue';
import DdBookPersonFtClass from '@/entities/dd-book-person-ft/dd-book-person-ft-details.component';
import DdBookPersonFtService from '@/entities/dd-book-person-ft/dd-book-person-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DdBookPersonFt Management Detail Component', () => {
    let wrapper: Wrapper<DdBookPersonFtClass>;
    let comp: DdBookPersonFtClass;
    let ddBookPersonServiceStub: SinonStubbedInstance<DdBookPersonFtService>;

    beforeEach(() => {
      ddBookPersonServiceStub = sinon.createStubInstance<DdBookPersonFtService>(DdBookPersonFtService);

      wrapper = shallowMount<DdBookPersonFtClass>(DdBookPersonFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { ddBookPersonService: () => ddBookPersonServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDdBookPersonFt = { id: 123 };
        ddBookPersonServiceStub.find.resolves(foundDdBookPersonFt);

        // WHEN
        comp.retrieveDdBookPersonFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ddBookPerson).toBe(foundDdBookPersonFt);
      });
    });
  });
});
