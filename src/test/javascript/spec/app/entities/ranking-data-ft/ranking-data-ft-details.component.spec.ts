/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import RankingDataFtDetailComponent from '@/entities/ranking-data-ft/ranking-data-ft-details.vue';
import RankingDataFtClass from '@/entities/ranking-data-ft/ranking-data-ft-details.component';
import RankingDataFtService from '@/entities/ranking-data-ft/ranking-data-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RankingDataFt Management Detail Component', () => {
    let wrapper: Wrapper<RankingDataFtClass>;
    let comp: RankingDataFtClass;
    let rankingDataServiceStub: SinonStubbedInstance<RankingDataFtService>;

    beforeEach(() => {
      rankingDataServiceStub = sinon.createStubInstance<RankingDataFtService>(RankingDataFtService);

      wrapper = shallowMount<RankingDataFtClass>(RankingDataFtDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { rankingDataService: () => rankingDataServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRankingDataFt = { id: 123 };
        rankingDataServiceStub.find.resolves(foundRankingDataFt);

        // WHEN
        comp.retrieveRankingDataFt(123);
        await comp.$nextTick();

        // THEN
        expect(comp.rankingData).toBe(foundRankingDataFt);
      });
    });
  });
});
