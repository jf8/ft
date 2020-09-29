<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('ftApp.rankingData.home.title')" id="ranking-data-ft-heading">Ranking Data</span>
            <router-link :to="{name: 'RankingDataFtCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-ranking-data-ft">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('ftApp.rankingData.home.createLabel')">
                    Create a new Ranking Data
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
        <div class="alert alert-warning" v-if="!isFetching && rankingData && rankingData.length === 0">
            <span v-text="$t('ftApp.rankingData.home.notFound')">No rankingData found</span>
        </div>
        <div class="table-responsive" v-if="rankingData && rankingData.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span v-text="$t('global.field.id')">ID</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('name')"><span v-text="$t('ftApp.rankingData.name')">Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('totalPeople')"><span v-text="$t('ftApp.rankingData.totalPeople')">Total People</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalPeople'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('signdPeople')"><span v-text="$t('ftApp.rankingData.signdPeople')">Signd People</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'signdPeople'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('attendance')"><span v-text="$t('ftApp.rankingData.attendance')">Attendance</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'attendance'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('orderNum')"><span v-text="$t('ftApp.rankingData.orderNum')">Order Num</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'orderNum'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('parentId')"><span v-text="$t('ftApp.rankingData.parentId')">Parent Id</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'parentId'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('day')"><span v-text="$t('ftApp.rankingData.day')">Day</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'day'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('isLeaf')"><span v-text="$t('ftApp.rankingData.isLeaf')">Is Leaf</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isLeaf'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('liveSharing.id')"><span v-text="$t('ftApp.rankingData.liveSharing')">Live Sharing</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'liveSharing.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="rankingData in rankingData"
                    :key="rankingData.id">
                    <td>
                        <router-link :to="{name: 'RankingDataFtView', params: {rankingDataId: rankingData.id}}">{{rankingData.id}}</router-link>
                    </td>
                    <td>{{rankingData.name}}</td>
                    <td>{{rankingData.totalPeople}}</td>
                    <td>{{rankingData.signdPeople}}</td>
                    <td>{{rankingData.attendance}}</td>
                    <td>{{rankingData.orderNum}}</td>
                    <td>{{rankingData.parentId}}</td>
                    <td>{{rankingData.day ? $d(Date.parse(rankingData.day), 'short') : ''}}</td>
                    <td>{{rankingData.isLeaf}}</td>
                    <td>
                        <div v-if="rankingData.liveSharing">
                            <router-link :to="{name: 'LiveSharingFtView', params: {liveSharingId: rankingData.liveSharing.id}}">{{rankingData.liveSharing.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RankingDataFtView', params: {rankingDataId: rankingData.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'RankingDataFtEdit', params: {rankingDataId: rankingData.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(rankingData)"
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
            <span slot="modal-title"><span id="ftApp.rankingData.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-rankingData-heading" v-text="$t('ftApp.rankingData.delete.question', {'id': removeId})">Are you sure you want to delete this Ranking Data?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-rankingData" v-text="$t('entity.action.delete')" v-on:click="removeRankingDataFt()">Delete</button>
            </div>
        </b-modal>
        <div v-show="rankingData && rankingData.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ranking-data-ft.component.ts">
</script>
