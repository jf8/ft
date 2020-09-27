<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.ddBookPerson.home.title')" id="dd-book-person-ft-heading">Dd Book People</span>
            <router-link :to="{name: 'DdBookPersonFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-dd-book-person-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.ddBookPerson.home.createLabel')">
                    Create a new Dd Book Person
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
        <div class="alert alert-warning" v-if="!isFetching && ddBookPeople && ddBookPeople.length === 0">
            <span v-text="$t('ftApp.ddBookPerson.home.notFound')">No ddBookPeople found</span>
        </div>
        <div class="table-responsive" v-if="ddBookPeople && ddBookPeople.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('unionid')"><span v-text="$t('ftApp.ddBookPerson.unionid')">Unionid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'unionid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('remark')"><span v-text="$t('ftApp.ddBookPerson.remark')">Remark</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'remark'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userid')"><span v-text="$t('ftApp.ddBookPerson.userid')">Userid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isLeaderInDepts')"><span v-text="$t('ftApp.ddBookPerson.isLeaderInDepts')">Is Leader In Depts</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isLeaderInDepts'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isBoss')"><span v-text="$t('ftApp.ddBookPerson.isBoss')">Is Boss</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isBoss'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('hiredDate')"><span v-text="$t('ftApp.ddBookPerson.hiredDate')">Hired Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hiredDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isSenior')"><span v-text="$t('ftApp.ddBookPerson.isSenior')">Is Senior</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isSenior'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('tel')"><span v-text="$t('ftApp.ddBookPerson.tel')">Tel</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tel'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('department')"><span v-text="$t('ftApp.ddBookPerson.department')">Department</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'department'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('email')"><span v-text="$t('ftApp.ddBookPerson.email')">Email</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('workPlace')"><span v-text="$t('ftApp.ddBookPerson.workPlace')">Work Place</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'workPlace'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('orderInDepts')"><span v-text="$t('ftApp.ddBookPerson.orderInDepts')">Order In Depts</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderInDepts'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mobile')"><span v-text="$t('ftApp.ddBookPerson.mobile')">Mobile</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobile'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('errmsg')"><span v-text="$t('ftApp.ddBookPerson.errmsg')">Errmsg</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'errmsg'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('active')"><span v-text="$t('ftApp.ddBookPerson.active')">Active</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'active'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('avatar')"><span v-text="$t('ftApp.ddBookPerson.avatar')">Avatar</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'avatar'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isAdmin')"><span v-text="$t('ftApp.ddBookPerson.isAdmin')">Is Admin</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isAdmin'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isHide')"><span v-text="$t('ftApp.ddBookPerson.isHide')">Is Hide</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isHide'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('jobnumber')"><span v-text="$t('ftApp.ddBookPerson.jobnumber')">Jobnumber</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jobnumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('ftApp.ddBookPerson.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extattr')"><span v-text="$t('ftApp.ddBookPerson.extattr')">Extattr</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extattr'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('stateCode')"><span v-text="$t('ftApp.ddBookPerson.stateCode')">State Code</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stateCode'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('position')"><span v-text="$t('ftApp.ddBookPerson.position')">Position</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'position'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('roles')"><span v-text="$t('ftApp.ddBookPerson.roles')">Roles</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'roles'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="ddBookPerson in ddBookPeople"
                    :key="ddBookPerson.id">
                    <td>
                        <router-link :to="{name: 'DdBookPersonFtView', params: {ddBookPersonId: ddBookPerson.id}}">{{ddBookPerson.id}}</router-link>
                    </td>
                    <td>{{ddBookPerson.unionid}}</td>
                    <td>{{ddBookPerson.remark}}</td>
                    <td>{{ddBookPerson.userid}}</td>
                    <td>{{ddBookPerson.isLeaderInDepts}}</td>
                    <td>{{ddBookPerson.isBoss}}</td>
                    <td>{{ddBookPerson.hiredDate}}</td>
                    <td>{{ddBookPerson.isSenior}}</td>
                    <td>{{ddBookPerson.tel}}</td>
                    <td>{{ddBookPerson.department}}</td>
                    <td>{{ddBookPerson.email}}</td>
                    <td>{{ddBookPerson.workPlace}}</td>
                    <td>{{ddBookPerson.orderInDepts}}</td>
                    <td>{{ddBookPerson.mobile}}</td>
                    <td>{{ddBookPerson.errmsg}}</td>
                    <td>{{ddBookPerson.active}}</td>
                    <td>{{ddBookPerson.avatar}}</td>
                    <td>{{ddBookPerson.isAdmin}}</td>
                    <td>{{ddBookPerson.isHide}}</td>
                    <td>{{ddBookPerson.jobnumber}}</td>
                    <td>{{ddBookPerson.name}}</td>
                    <td>{{ddBookPerson.extattr}}</td>
                    <td>{{ddBookPerson.stateCode}}</td>
                    <td>{{ddBookPerson.position}}</td>
                    <td>{{ddBookPerson.roles}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DdBookPersonFtView', params: {ddBookPersonId: ddBookPerson.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DdBookPersonFtEdit', params: {ddBookPersonId: ddBookPerson.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(ddBookPerson)"
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
            <span slot="modal-title"><span id="ftApp.ddBookPerson.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-ddBookPerson-heading" v-text="$t('ftApp.ddBookPerson.delete.question', {'id': removeId})">Are you sure you want to delete this Dd Book Person?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-ddBookPerson" v-text="$t('entity.action.delete')" v-on:click="removeDdBookPersonFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="ddBookPeople && ddBookPeople.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-book-person-ft.component.ts">
</script>
