import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [


  {
    path: '/live-sharing-ft',
    name: 'LiveSharingFt',
    component: LiveSharingFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/live-sharing-ft/new',
    name: 'LiveSharingFtCreate',
    component: LiveSharingFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/live-sharing-ft/:liveSharingId/edit',
    name: 'LiveSharingFtEdit',
    component: LiveSharingFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/live-sharing-ft/:liveSharingId/view',
    name: 'LiveSharingFtView',
    component: LiveSharingFtDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ranking-data-ft',
    name: 'RankingDataFt',
    component: RankingDataFt,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ranking-data-ft/new',
    name: 'RankingDataFtCreate',
    component: RankingDataFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ranking-data-ft/:rankingDataId/edit',
    name: 'RankingDataFtEdit',
    component: RankingDataFtUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ranking-data-ft/:rankingDataId/view',
    name: 'RankingDataFtView',
    component: RankingDataFtDetails,
    meta: { authorities: [Authority.USER] },
  },
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
]
