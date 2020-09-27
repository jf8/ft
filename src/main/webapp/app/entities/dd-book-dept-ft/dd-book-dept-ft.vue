<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.ddBookDept.home.title')" id="dd-book-dept-ft-heading">Dd Book Depts</span>
            <router-link :to="{name: 'DdBookDeptFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-dd-book-dept-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.ddBookDept.home.createLabel')">
                    Create a new Dd Book Dept
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && ddBookDepts && ddBookDepts.length === 0">
            <span v-text="$t('ftApp.ddBookDept.home.notFound')">No ddBookDepts found</span>
        </div>
        <div class="table-responsive" v-if="ddBookDepts && ddBookDepts.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('ftApp.ddBookDept.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('orderNum')"><span v-text="$t('ftApp.ddBookDept.orderNum')">Order Num</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderNum'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('parentid')"><span v-text="$t('ftApp.ddBookDept.parentid')">Parentid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'parentid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('createDeptGroup')"><span v-text="$t('ftApp.ddBookDept.createDeptGroup')">Create Dept Group</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createDeptGroup'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('autoAddUser')"><span v-text="$t('ftApp.ddBookDept.autoAddUser')">Auto Add User</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'autoAddUser'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('deptHiding')"><span v-text="$t('ftApp.ddBookDept.deptHiding')">Dept Hiding</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deptHiding'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('deptPermits')"><span v-text="$t('ftApp.ddBookDept.deptPermits')">Dept Permits</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deptPermits'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userPermits')"><span v-text="$t('ftApp.ddBookDept.userPermits')">User Permits</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userPermits'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('outerDept')"><span v-text="$t('ftApp.ddBookDept.outerDept')">Outer Dept</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'outerDept'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('outerPermitDepts')"><span v-text="$t('ftApp.ddBookDept.outerPermitDepts')">Outer Permit Depts</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'outerPermitDepts'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('outerPermitUsers')"><span v-text="$t('ftApp.ddBookDept.outerPermitUsers')">Outer Permit Users</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'outerPermitUsers'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('orgDeptOwner')"><span v-text="$t('ftApp.ddBookDept.orgDeptOwner')">Org Dept Owner</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orgDeptOwner'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('deptManagerUseridList')"><span v-text="$t('ftApp.ddBookDept.deptManagerUseridList')">Dept Manager Userid List</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'deptManagerUseridList'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('sourceIdentifier')"><span v-text="$t('ftApp.ddBookDept.sourceIdentifier')">Source Identifier</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sourceIdentifier'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ext')"><span v-text="$t('ftApp.ddBookDept.ext')">Ext</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ext'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ddUser.id')"><span v-text="$t('ftApp.ddBookDept.ddUser')">Dd User</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ddUser.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="ddBookDept in ddBookDepts"
                    :key="ddBookDept.id">
                    <td>
                        <router-link :to="{name: 'DdBookDeptFtView', params: {ddBookDeptId: ddBookDept.id}}">{{ddBookDept.id}}</router-link>
                    </td>
                    <td>{{ddBookDept.name}}</td>
                    <td>{{ddBookDept.orderNum}}</td>
                    <td>{{ddBookDept.parentid}}</td>
                    <td>{{ddBookDept.createDeptGroup}}</td>
                    <td>{{ddBookDept.autoAddUser}}</td>
                    <td>{{ddBookDept.deptHiding}}</td>
                    <td>{{ddBookDept.deptPermits}}</td>
                    <td>{{ddBookDept.userPermits}}</td>
                    <td>{{ddBookDept.outerDept}}</td>
                    <td>{{ddBookDept.outerPermitDepts}}</td>
                    <td>{{ddBookDept.outerPermitUsers}}</td>
                    <td>{{ddBookDept.orgDeptOwner}}</td>
                    <td>{{ddBookDept.deptManagerUseridList}}</td>
                    <td>{{ddBookDept.sourceIdentifier}}</td>
                    <td>{{ddBookDept.ext}}</td>
                    <td>
                        <div v-if="ddBookDept.ddUser">
                            <router-link :to="{name: 'DdUserFtView', params: {ddUserId: ddBookDept.ddUser.id}}">{{ddBookDept.ddUser.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DdBookDeptFtView', params: {ddBookDeptId: ddBookDept.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DdBookDeptFtEdit', params: {ddBookDeptId: ddBookDept.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(ddBookDept)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="ftApp.ddBookDept.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-ddBookDept-heading" v-text="$t('ftApp.ddBookDept.delete.question', {'id': removeId})">Are you sure you want to delete this Dd Book Dept?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-ddBookDept" v-text="$t('entity.action.delete')" v-on:click="removeDdBookDeptFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="ddBookDepts && ddBookDepts.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-book-dept-ft.component.ts">
</script>
