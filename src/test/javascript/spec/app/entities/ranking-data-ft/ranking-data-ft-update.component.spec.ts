/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

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

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(format(date, DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
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
