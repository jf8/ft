<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.vFtUserSignInfo.home.title')" id="v-ft-user-sign-info-ft-heading">V Ft User Sign Infos</span>
            <router-link :to="{name: 'VFtUserSignInfoFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-v-ft-user-sign-info-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.vFtUserSignInfo.home.createLabel')">
                    Create a new V Ft User Sign Info
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
        <div class="alert alert-warning" v-if="!isFetching && vFtUserSignInfos && vFtUserSignInfos.length === 0">
            <span v-text="$t('ftApp.vFtUserSignInfo.home.notFound')">No vFtUserSignInfos found</span>
        </div>
        <div class="table-responsive" v-if="vFtUserSignInfos && vFtUserSignInfos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phoneCode')"><span v-text="$t('ftApp.vFtUserSignInfo.phoneCode')">Phone Code</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phoneCode'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('phone')"><span v-text="$t('ftApp.vFtUserSignInfo.phone')">Phone</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'phone'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('email')"><span v-text="$t('ftApp.vFtUserSignInfo.email')">Email</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('seat')"><span v-text="$t('ftApp.vFtUserSignInfo.seat')">Seat</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'seat'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('groupIds')"><span v-text="$t('ftApp.vFtUserSignInfo.groupIds')">Group Ids</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'groupIds'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('startTime')"><span v-text="$t('ftApp.vFtUserSignInfo.startTime')">Start Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'startTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('endTime')"><span v-text="$t('ftApp.vFtUserSignInfo.endTime')">End Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'endTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('nameCn')"><span v-text="$t('ftApp.vFtUserSignInfo.nameCn')">Name Cn</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameCn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('nameEn')"><span v-text="$t('ftApp.vFtUserSignInfo.nameEn')">Name En</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameEn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyCn')"><span v-text="$t('ftApp.vFtUserSignInfo.companyCn')">Company Cn</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyCn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyEn')"><span v-text="$t('ftApp.vFtUserSignInfo.companyEn')">Company En</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyEn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('titleCn')"><span v-text="$t('ftApp.vFtUserSignInfo.titleCn')">Title Cn</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'titleCn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('titleEn')"><span v-text="$t('ftApp.vFtUserSignInfo.titleEn')">Title En</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'titleEn'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('remark')"><span v-text="$t('ftApp.vFtUserSignInfo.remark')">Remark</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'remark'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ddid')"><span v-text="$t('ftApp.vFtUserSignInfo.ddid')">Ddid</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ddid'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('updateTime')"><span v-text="$t('ftApp.vFtUserSignInfo.updateTime')">Update Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updateTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('createTime')"><span v-text="$t('ftApp.vFtUserSignInfo.createTime')">Create Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('signTime')"><span v-text="$t('ftApp.vFtUserSignInfo.signTime')">Sign Time</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'signTime'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('meetId')"><span v-text="$t('ftApp.vFtUserSignInfo.meetId')">Meet Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'meetId'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="vFtUserSignInfo in vFtUserSignInfos"
                    :key="vFtUserSignInfo.id">
                    <td>
                        <router-link :to="{name: 'VFtUserSignInfoFtView', params: {vFtUserSignInfoId: vFtUserSignInfo.id}}">{{vFtUserSignInfo.id}}</router-link>
                    </td>
                    <td>{{vFtUserSignInfo.phoneCode}}</td>
                    <td>{{vFtUserSignInfo.phone}}</td>
                    <td>{{vFtUserSignInfo.email}}</td>
                    <td>{{vFtUserSignInfo.seat}}</td>
                    <td>{{vFtUserSignInfo.groupIds}}</td>
                    <td>{{vFtUserSignInfo.startTime ? $d(Date.parse(vFtUserSignInfo.startTime), 'short') : ''}}</td>
                    <td>{{vFtUserSignInfo.endTime ? $d(Date.parse(vFtUserSignInfo.endTime), 'short') : ''}}</td>
                    <td>{{vFtUserSignInfo.nameCn}}</td>
                    <td>{{vFtUserSignInfo.nameEn}}</td>
                    <td>{{vFtUserSignInfo.companyCn}}</td>
                    <td>{{vFtUserSignInfo.companyEn}}</td>
                    <td>{{vFtUserSignInfo.titleCn}}</td>
                    <td>{{vFtUserSignInfo.titleEn}}</td>
                    <td>{{vFtUserSignInfo.remark}}</td>
                    <td>{{vFtUserSignInfo.ddid}}</td>
                    <td>{{vFtUserSignInfo.updateTime ? $d(Date.parse(vFtUserSignInfo.updateTime), 'short') : ''}}</td>
                    <td>{{vFtUserSignInfo.createTime ? $d(Date.parse(vFtUserSignInfo.createTime), 'short') : ''}}</td>
                    <td>{{vFtUserSignInfo.signTime ? $d(Date.parse(vFtUserSignInfo.signTime), 'short') : ''}}</td>
                    <td>{{vFtUserSignInfo.meetId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'VFtUserSignInfoFtView', params: {vFtUserSignInfoId: vFtUserSignInfo.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'VFtUserSignInfoFtEdit', params: {vFtUserSignInfoId: vFtUserSignInfo.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(vFtUserSignInfo)"
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
            <span slot="modal-title"><span id="ftApp.vFtUserSignInfo.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-vFtUserSignInfo-heading" v-text="$t('ftApp.vFtUserSignInfo.delete.question', {'id': removeId})">Are you sure you want to delete this V Ft User Sign Info?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-vFtUserSignInfo" v-text="$t('entity.action.delete')" v-on:click="removeVFtUserSignInfoFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="vFtUserSignInfos && vFtUserSignInfos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./v-ft-user-sign-info-ft.component.ts">
</script>
