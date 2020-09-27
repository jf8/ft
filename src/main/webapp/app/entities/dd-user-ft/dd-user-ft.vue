<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.ddUser.home.title')" id="dd-user-ft-heading">Dd Users</span>
            <router-link :to="{name: 'DdUserFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-dd-user-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.ddUser.home.createLabel')">
                    Create a new Dd User
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
        <div class="alert alert-warning" v-if="!isFetching && ddUsers && ddUsers.length === 0">
            <span v-text="$t('ftApp.ddUser.home.notFound')">No ddUsers found</span>
        </div>
        <div class="table-responsive" v-if="ddUsers && ddUsers.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('unionid')"><span v-text="$t('ftApp.ddUser.unionid')">Unionid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'unionid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('remark')"><span v-text="$t('ftApp.ddUser.remark')">Remark</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'remark'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('userid')"><span v-text="$t('ftApp.ddUser.userid')">Userid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isLeaderInDepts')"><span v-text="$t('ftApp.ddUser.isLeaderInDepts')">Is Leader In Depts</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isLeaderInDepts'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isBoss')"><span v-text="$t('ftApp.ddUser.isBoss')">Is Boss</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isBoss'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('hiredDate')"><span v-text="$t('ftApp.ddUser.hiredDate')">Hired Date</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'hiredDate'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isSenior')"><span v-text="$t('ftApp.ddUser.isSenior')">Is Senior</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isSenior'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('tel')"><span v-text="$t('ftApp.ddUser.tel')">Tel</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'tel'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('department')"><span v-text="$t('ftApp.ddUser.department')">Department</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'department'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('workPlace')"><span v-text="$t('ftApp.ddUser.workPlace')">Work Place</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'workPlace'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('orderInDepts')"><span v-text="$t('ftApp.ddUser.orderInDepts')">Order In Depts</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderInDepts'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('mobile')"><span v-text="$t('ftApp.ddUser.mobile')">Mobile</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobile'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('errmsg')"><span v-text="$t('ftApp.ddUser.errmsg')">Errmsg</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'errmsg'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('active')"><span v-text="$t('ftApp.ddUser.active')">Active</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'active'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('avatar')"><span v-text="$t('ftApp.ddUser.avatar')">Avatar</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'avatar'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isAdmin')"><span v-text="$t('ftApp.ddUser.isAdmin')">Is Admin</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isAdmin'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isHide')"><span v-text="$t('ftApp.ddUser.isHide')">Is Hide</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isHide'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('jobnumber')"><span v-text="$t('ftApp.ddUser.jobnumber')">Jobnumber</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jobnumber'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('ftApp.ddUser.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('extattr')"><span v-text="$t('ftApp.ddUser.extattr')">Extattr</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extattr'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('stateCode')"><span v-text="$t('ftApp.ddUser.stateCode')">State Code</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'stateCode'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('position')"><span v-text="$t('ftApp.ddUser.position')">Position</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'position'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('roles')"><span v-text="$t('ftApp.ddUser.roles')">Roles</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'roles'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="ddUser in ddUsers"
                    :key="ddUser.id">
                    <td>
                        <router-link :to="{name: 'DdUserFtView', params: {ddUserId: ddUser.id}}">{{ddUser.id}}</router-link>
                    </td>
                    <td>{{ddUser.unionid}}</td>
                    <td>{{ddUser.remark}}</td>
                    <td>{{ddUser.userid}}</td>
                    <td>{{ddUser.isLeaderInDepts}}</td>
                    <td>{{ddUser.isBoss}}</td>
                    <td>{{ddUser.hiredDate}}</td>
                    <td>{{ddUser.isSenior}}</td>
                    <td>{{ddUser.tel}}</td>
                    <td>{{ddUser.department}}</td>
                    <td>{{ddUser.workPlace}}</td>
                    <td>{{ddUser.orderInDepts}}</td>
                    <td>{{ddUser.mobile}}</td>
                    <td>{{ddUser.errmsg}}</td>
                    <td>{{ddUser.active}}</td>
                    <td>{{ddUser.avatar}}</td>
                    <td>{{ddUser.isAdmin}}</td>
                    <td>{{ddUser.isHide}}</td>
                    <td>{{ddUser.jobnumber}}</td>
                    <td>{{ddUser.name}}</td>
                    <td>{{ddUser.extattr}}</td>
                    <td>{{ddUser.stateCode}}</td>
                    <td>{{ddUser.position}}</td>
                    <td>{{ddUser.roles}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DdUserFtView', params: {ddUserId: ddUser.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'DdUserFtEdit', params: {ddUserId: ddUser.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(ddUser)"
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
            <span slot="modal-title"><span id="ftApp.ddUser.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-ddUser-heading" v-text="$t('ftApp.ddUser.delete.question', {'id': removeId})">Are you sure you want to delete this Dd User?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-ddUser" v-text="$t('entity.action.delete')" v-on:click="removeDdUserFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="ddUsers && ddUsers.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./dd-user-ft.component.ts">
</script>
