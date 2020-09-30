/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import DdBookPersonFtUpdateComponent from '@/entities/dd-book-person-ft/dd-book-person-ft-update.vue';
import DdBookPersonFtClass from '@/entities/dd-book-person-ft/dd-book-person-ft-update.component';
import DdBookPersonFtService from '@/entities/dd-book-person-ft/dd-book-person-ft.service';

import DdBookDeptFtService from '@/entities/dd-book-dept-ft/dd-book-dept-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('DdBookPersonFt Management Update Component', () => {
    let wrapper: Wrapper<DdBookPersonFtClass>;
    let comp: DdBookPersonFtClass;
    let ddBookPersonServiceStub: SinonStubbedInstance<DdBookPersonFtService>;

    beforeEach(() => {
      ddBookPersonServiceStub = sinon.createStubInstance<DdBookPersonFtService>(DdBookPersonFtService);

      wrapper = shallowMount<DdBookPersonFtClass>(DdBookPersonFtUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          ddBookPersonService: () => ddBookPersonServiceStub,

          ddBookDeptService: () => new DdBookDeptFtService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.ddBookPerson = entity;
        ddBookPersonServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddBookPersonServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.ddBookPerson = entity;
        ddBookPersonServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddBookPersonServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
