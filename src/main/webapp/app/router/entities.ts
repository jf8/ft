import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const DdBookDeptFt = () => import('@/entities/dd-book-dept-ft/dd-book-dept-ft.vue');
// prettier-ignore
const DdBookDeptFtUpdate = () => import('@/entities/dd-book-dept-ft/dd-book-dept-ft-update.vue');
// prettier-ignore
const DdBookDeptFtDetails = () => import('@/entities/dd-book-dept-ft/dd-book-dept-ft-details.vue');
// prettier-ignore
const DdBookPersonFt = () => import('@/entities/dd-book-person-ft/dd-book-person-ft.vue');
// prettier-ignore
const DdBookPersonFtUpdate = () => import('@/entities/dd-book-person-ft/dd-book-person-ft-update.vue');
// prettier-ignore
const DdBookPersonFtDetails = () => import('@/entities/dd-book-person-ft/dd-book-person-ft-details.vue');
// prettier-ignore
const VFtUserSignInfoFt = () => import('@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft.vue');
// prettier-ignore
const VFtUserSignInfoFtUpdate = () => import('@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft-update.vue');
// prettier-ignore
const VFtUserSignInfoFtDetails = () => import('@/entities/v-ft-user-sign-info-ft/v-ft-user-sign-info-ft-details.vue');
// prettier-ignore
const DdUserFt = () => import('@/entities/dd-user-ft/dd-user-ft.vue');
// prettier-ignore
const DdUserFtUpdate = () => import('@/entities/dd-user-ft/dd-user-ft-update.vue');
// prettier-ignore
const DdUserFtDetails = () => import('@/entities/dd-user-ft/dd-user-ft-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/dd-book-dept-ft',
    name: 'DdBookDeptFt',
    component: DdBookDeptFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-dept-ft/new',
    name: 'DdBookDeptFtCreate',
    component: DdBookDeptFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-dept-ft/:ddBookDeptId/edit',
    name: 'DdBookDeptFtEdit',
    component: DdBookDeptFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-dept-ft/:ddBookDeptId/view',
    name: 'DdBookDeptFtView',
    component: DdBookDeptFtDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-person-ft',
    name: 'DdBookPersonFt',
    component: DdBookPersonFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-person-ft/new',
    name: 'DdBookPersonFtCreate',
    component: DdBookPersonFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-person-ft/:ddBookPersonId/edit',
    name: 'DdBookPersonFtEdit',
    component: DdBookPersonFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-book-person-ft/:ddBookPersonId/view',
    name: 'DdBookPersonFtView',
    component: DdBookPersonFtDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/v-ft-user-sign-info-ft',
    name: 'VFtUserSignInfoFt',
    component: VFtUserSignInfoFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/v-ft-user-sign-info-ft/new',
    name: 'VFtUserSignInfoFtCreate',
    component: VFtUserSignInfoFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/v-ft-user-sign-info-ft/:vFtUserSignInfoId/edit',
    name: 'VFtUserSignInfoFtEdit',
    component: VFtUserSignInfoFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/v-ft-user-sign-info-ft/:vFtUserSignInfoId/view',
    name: 'VFtUserSignInfoFtView',
    component: VFtUserSignInfoFtDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user-ft',
    name: 'DdUserFt',
    component: DdUserFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user-ft/new',
    name: 'DdUserFtCreate',
    component: DdUserFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user-ft/:ddUserId/edit',
    name: 'DdUserFtEdit',
    component: DdUserFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/dd-user-ft/:ddUserId/view',
    name: 'DdUserFtView',
    component: DdUserFtDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
