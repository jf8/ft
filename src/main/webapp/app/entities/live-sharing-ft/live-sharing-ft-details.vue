<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="liveSharing">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.liveSharing.detail.title')">LiveSharingFt</span> {{liveSharing.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.liveSharing.name')">Name</span>
                    </dt>
                    <dd>
                        <span>{{liveSharing.name}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.liveSharing.day')">Day</span>
                    </dt>
                    <dd>
                        <span v-if="liveSharing.day">{{$d(Date.parse(liveSharing.day), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.liveSharing.confNumber')">Conf Number</span>
                    </dt>
                    <dd>
                        <span>{{liveSharing.confNumber}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="liveSharing.id" :to="{name: 'LiveSharingFtEdit', params: {liveSharingId: liveSharing.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./live-sharing-ft-details.component.ts">
</script>
