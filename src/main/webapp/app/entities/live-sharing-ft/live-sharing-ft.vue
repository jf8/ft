<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.liveSharing.home.title')" id="live-sharing-ft-heading">Live Sharings</span>
            <router-link :to="{name: 'LiveSharingFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-live-sharing-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.liveSharing.home.createLabel')">
                    Create a new Live Sharing
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
        <div class="alert alert-warning" v-if="!isFetching && liveSharings && liveSharings.length === 0">
            <span v-text="$t('ftApp.liveSharing.home.notFound')">No liveSharings found</span>
        </div>
        <div class="table-responsive" v-if="liveSharings && liveSharings.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('ftApp.liveSharing.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('day')"><span v-text="$t('ftApp.liveSharing.day')">Day</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'day'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('confNumber')"><span v-text="$t('ftApp.liveSharing.confNumber')">Conf Number</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'confNumber'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="liveSharing in liveSharings"
                    :key="liveSharing.id">
                    <td>
                        <router-link :to="{name: 'LiveSharingFtView', params: {liveSharingId: liveSharing.id}}">{{liveSharing.id}}</router-link>
                    </td>
                    <td>{{liveSharing.name}}</td>
                    <td>{{liveSharing.day ? $d(Date.parse(liveSharing.day), 'short') : ''}}</td>
                    <td>{{liveSharing.confNumber}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LiveSharingFtView', params: {liveSharingId: liveSharing.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'LiveSharingFtEdit', params: {liveSharingId: liveSharing.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(liveSharing)"
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
            <span slot="modal-title"><span id="ftApp.liveSharing.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-liveSharing-heading" v-text="$t('ftApp.liveSharing.delete.question', {'id': removeId})">Are you sure you want to delete this Live Sharing?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-liveSharing" v-text="$t('entity.action.delete')" v-on:click="removeLiveSharingFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="liveSharings && liveSharings.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./live-sharing-ft.component.ts">
</script>
