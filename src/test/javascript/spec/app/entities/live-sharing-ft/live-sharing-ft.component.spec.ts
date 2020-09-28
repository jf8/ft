/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import LiveSharingFtComponent from '@/entities/live-sharing-ft/live-sharing-ft.vue';
import LiveSharingFtClass from '@/entities/live-sharing-ft/live-sharing-ft.component';
import LiveSharingFtService from '@/entities/live-sharing-ft/live-sharing-ft.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('LiveSharingFt Management Component', () => {
    let wrapper: Wrapper<LiveSharingFtClass>;
    let comp: LiveSharingFtClass;
    let liveSharingServiceStub: SinonStubbedInstance<LiveSharingFtService>;

    beforeEach(() => {
      liveSharingServiceStub = sinon.createStubInstance<LiveSharingFtService>(LiveSharingFtService);
      liveSharingServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LiveSharingFtClass>(LiveSharingFtComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          liveSharingService: () => liveSharingServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      liveSharingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLiveSharingFts();
      await comp.$nextTick();

      // THEN
      expect(liveSharingServiceStub.retrieve.called).toBeTruthy();
      expect(comp.liveSharings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      liveSharingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(liveSharingServiceStub.retrieve.called).toBeTruthy();
      expect(comp.liveSharings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      liveSharingServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(liveSharingServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      liveSharingServiceStub.retrieve.reset();
      liveSharingServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(liveSharingServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.liveSharings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      liveSharingServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeLiveSharingFt();
      await comp.$nextTick();

      // THEN
      expect(liveSharingServiceStub.delete.called).toBeTruthy();
      expect(liveSharingServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
