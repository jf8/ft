<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="ddBookDept">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.ddBookDept.detail.title')">DdBookDeptFt</span> {{ddBookDept.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.orderNum')">Order Num</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.orderNum}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.parentid')">Parentid</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.parentid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.createDeptGroup')">Create Dept Group</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.createDeptGroup}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.autoAddUser')">Auto Add User</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.autoAddUser}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.deptHiding')">Dept Hiding</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.deptHiding}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.deptPermits')">Dept Permits</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.deptPermits}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.userPermits')">User Permits</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.userPermits}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.outerDept')">Outer Dept</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.outerDept}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.outerPermitDepts')">Outer Permit Depts</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.outerPermitDepts}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.outerPermitUsers')">Outer Permit Users</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.outerPermitUsers}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.orgDeptOwner')">Org Dept Owner</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.orgDeptOwner}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.deptManagerUseridList')">Dept Manager Userid List</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.deptManagerUseridList}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.sourceIdentifier')">Source Identifier</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.sourceIdentifier}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.ext')">Ext</span>
                    </dt>
                    <dd>
                        <span>{{ddBookDept.ext}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.ddBookPerson')">Dd Book Person</span>
                    </dt>
                    <dd>
                        <span v-for="(ddBookPerson, i) in ddBookDept.ddBookPeople" :key="ddBookPerson.id">{{i > 0 ? ', ' : ''}}
                            <router-link :to="{name: 'DdBookPersonFtView', params: {ddBookPersonId: ddBookPerson.id}}">{{ddBookPerson.id}}</router-link>
                        </span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.ddBookDept.ddUser')">Dd User</span>
                    </dt>
                    <dd>
                        <div v-if="ddBookDept.ddUser">
                            <router-link :to="{name: 'DdUserFtView', params: {ddUserId: ddBookDept.ddUser.id}}">{{ddBookDept.ddUser.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="ddBookDept.id" :to="{name: 'DdBookDeptFtEdit', params: {ddBookDeptId: ddBookDept.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-book-dept-ft-details.component.ts">
</script>
