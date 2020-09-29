<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="rankingData">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.rankingData.detail.title')">RankingDataFt</span> {{rankingData.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.rankingData.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.totalPeople')">Total People</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.totalPeople}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.signdPeople')">Signd People</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.signdPeople}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.attendance')">Attendance</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.attendance}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.orderNum')">Order Num</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.orderNum}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.parentId')">Parent Id</span>
                    </dt>
                    <dd>
                        <span>{{rankingData.parentId}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.day')">Day</span>
                    </dt>
                    <dd>
                        <span v-if="rankingData.day">{{$d(Date.parse(rankingData.day), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.rankingData.liveSharing')">Live Sharing</span>
                    </dt>
                    <dd>
                        <div v-if="rankingData.liveSharing">
                            <router-link :to="{name: 'LiveSharingFtView', params: {liveSharingId: rankingData.liveSharing.id}}">{{rankingData.liveSharing.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="rankingData.id" :to="{name: 'RankingDataFtEdit', params: {rankingDataId: rankingData.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./ranking-data-ft-details.component.ts">
</script>
