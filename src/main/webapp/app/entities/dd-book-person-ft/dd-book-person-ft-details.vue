<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="ddBookPerson">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.ddBookPerson.detail.title')">DdBookPersonFt</span> {{ddBookPerson.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.unionid')">Unionid</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.unionid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.remark')">Remark</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.remark}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.userid')">Userid</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.userid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.isLeaderInDepts')">Is Leader In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.isLeaderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.isBoss')">Is Boss</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.isBoss}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.hiredDate')">Hired Date</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.hiredDate}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.isSenior')">Is Senior</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.isSenior}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.tel')">Tel</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.tel}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.department')">Department</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.department}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.email')">Email</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.email}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.workPlace')">Work Place</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.workPlace}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.orderInDepts')">Order In Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.orderInDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.mobile')">Mobile</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.mobile}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.errmsg')">Errmsg</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.errmsg}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.active')">Active</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.active}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.avatar')">Avatar</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.avatar}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.isAdmin')">Is Admin</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.isAdmin}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.isHide')">Is Hide</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.isHide}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.jobnumber')">Jobnumber</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.jobnumber}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.extattr')">Extattr</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.extattr}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.stateCode')">State Code</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.stateCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.position')">Position</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.position}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.roles')">Roles</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.roles}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.parentDeptsIdList')">Parent Depts Id List</span>
                    </dt>
                    <dd>
                        <span>{{ddBookPerson.parentDeptsIdList}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookPerson.ddBookDept')">Dd Book Dept</span>
                    </dt>
                    <dd>
                        <span v-for="(ddBookDept, i) in ddBookPerson.ddBookDepts" :key="ddBookDept.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'DdBookDeptFtView', params: {ddBookDeptId: ddBookDept.id}}">{{ddBookDept.id}}</router-link>
                        </span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="ddBookPerson.id" :to="{name: 'DdBookPersonFtEdit', params: {ddBookPersonId: ddBookPerson.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-book-person-ft-details.component.ts">
</script>
