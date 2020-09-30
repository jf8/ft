/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import DdBookDeptFtUpdateComponent from '@/entities/dd-book-dept-ft/dd-book-dept-ft-update.vue';
import DdBookDeptFtClass from '@/entities/dd-book-dept-ft/dd-book-dept-ft-update.component';
import DdBookDeptFtService from '@/entities/dd-book-dept-ft/dd-book-dept-ft.service';

import DdBookPersonFtService from '@/entities/dd-book-person-ft/dd-book-person-ft.service';

import DdUserFtService from '@/entities/dd-user-ft/dd-user-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('DdBookDeptFt Management Update Component', () => {
    let wrapper: Wrapper<DdBookDeptFtClass>;
    let comp: DdBookDeptFtClass;
    let ddBookDeptServiceStub: SinonStubbedInstance<DdBookDeptFtService>;

    beforeEach(() => {
      ddBookDeptServiceStub = sinon.createStubInstance<DdBookDeptFtService>(DdBookDeptFtService);

      wrapper = shallowMount<DdBookDeptFtClass>(DdBookDeptFtUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          ddBookDeptService: () => ddBookDeptServiceStub,

          ddBookPersonService: () => new DdBookPersonFtService(),

          ddUserService: () => new DdUserFtService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.ddBookDept = entity;
        ddBookDeptServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddBookDeptServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.ddBookDept = entity;
        ddBookDeptServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddBookDeptServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
