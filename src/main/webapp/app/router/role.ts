const RoleUser = () => import('@/role/user/user.vue');
const RoleList = () => import('@/role/list/list.vue');

export default [
  {
    path: '/role',
    name: 'roleList',
    component: RoleList,
  },
  {
    path: '/role/user',
    name: 'roleUser',
    component: RoleUser,
  },
];
