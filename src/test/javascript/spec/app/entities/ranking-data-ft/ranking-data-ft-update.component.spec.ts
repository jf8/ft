/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import RankingDataFtUpdateComponent from '@/entities/ranking-data-ft/ranking-data-ft-update.vue';
import RankingDataFtClass from '@/entities/ranking-data-ft/ranking-data-ft-update.component';
import RankingDataFtService from '@/entities/ranking-data-ft/ranking-data-ft.service';

import LiveSharingFtService from '@/entities/live-sharing-ft/live-sharing-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('RankingDataFt Management Update Component', () => {
    let wrapper: Wrapper<RankingDataFtClass>;
    let comp: RankingDataFtClass;
    let rankingDataServiceStub: SinonStubbedInstance<RankingDataFtService>;

    beforeEach(() => {
      rankingDataServiceStub = sinon.createStubInstance<RankingDataFtService>(RankingDataFtService);

      wrapper = shallowMount<RankingDataFtClass>(RankingDataFtUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          rankingDataService: () => rankingDataServiceStub,

          liveSharingService: () => new LiveSharingFtService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.rankingData = entity;
        rankingDataServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rankingDataServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.rankingData = entity;
        rankingDataServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(rankingDataServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
