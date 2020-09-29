<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="ddUser">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.ddUser.detail.title')">DdUserFt</span> {{ddUser.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.ddUser.unionid')">Unionid</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.unionid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.remark')">Remark</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.remark}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.userid')">Userid</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.userid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.isLeaderInDepts')">Is Leader In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isLeaderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.isBoss')">Is Boss</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isBoss}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.hiredDate')">Hired Date</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.hiredDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.isSenior')">Is Senior</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isSenior}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.tel')">Tel</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.tel}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.department')">Department</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.department}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.workPlace')">Work Place</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.workPlace}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.orderInDepts')">Order In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.orderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.mobile')">Mobile</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.mobile}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.errmsg')">Errmsg</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.errmsg}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.active')">Active</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.active}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.avatar')">Avatar</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.avatar}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.isAdmin')">Is Admin</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isAdmin}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.isHide')">Is Hide</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.isHide}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.jobnumber')">Jobnumber</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.jobnumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.extattr')">Extattr</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.extattr}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.stateCode')">State Code</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.stateCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.position')">Position</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.position}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.roles')">Roles</span>
                    </dt>
                    <dd>
                        <span>{{ddUser.roles}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddUser.ddBookDept')">Dd Book Dept</span>
                    </dt>
                    <dd>
                        <span v-for="(ddBookDept, i) in ddUser.ddBookDepts" :key="ddBookDept.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'DdBookDeptFtView', params: {ddBookDeptId: ddBookDept.id}}">{{ddBookDept.id}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="ddUser.id" :to="{name: 'DdUserFtEdit', params: {ddUserId: ddUser.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-user-ft-details.component.ts">
</script>
