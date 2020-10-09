/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import DdBookDeptFtDetailComponent from '@/entities/dd-book-dept-ft/dd-book-dept-ft-details.vue';
import DdBookDeptFtClass from '@/entities/dd-book-dept-ft/dd-book-dept-ft-details.component';
import DdBookDeptFtService from '@/entities/dd-book-dept-ft/dd-book-dept-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DdBookDeptFt Management Detail Component', () => {
    let wrapper: Wrapper<DdBookDeptFtClass>;
    let comp: DdBookDeptFtClass;
    let ddBookDeptServiceStub: SinonStubbedInstance<DdBookDeptFtService>;

    beforeEach(() => {
      ddBookDeptServiceStub = sinon.createStubInstance<DdBookDeptFtService>(DdBookDeptFtService);

      wrapper = shallowMount<DdBookDeptFtClass>(DdBookDeptFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { ddBookDeptService: () => ddBookDeptServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDdBookDeptFt = { id: 123 };
        ddBookDeptServiceStub.find.resolves(foundDdBookDeptFt);

        // WHEN
        comp.retrieveDdBookDeptFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ddBookDept).toBe(foundDdBookDeptFt);
      });
    });
  });
});
