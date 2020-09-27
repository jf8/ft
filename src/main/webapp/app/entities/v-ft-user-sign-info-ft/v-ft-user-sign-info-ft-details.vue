<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="vFtUserSignInfo">
                <h2 class="jh-entity-heading"><span v-text="$t('ftApp.vFtUserSignInfo.detail.title')">VFtUserSignInfoFt</span> {{vFtUserSignInfo.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.phoneCode')">Phone Code</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.phoneCode}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.phone')">Phone</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.phone}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.email')">Email</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.email}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.seat')">Seat</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.seat}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.groupIds')">Group Ids</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.groupIds}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.startTime')">Start Time</span>
                    </dt>
                    <dd>
                        <span v-if="vFtUserSignInfo.startTime">{{$d(Date.parse(vFtUserSignInfo.startTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.endTime')">End Time</span>
                    </dt>
                    <dd>
                        <span v-if="vFtUserSignInfo.endTime">{{$d(Date.parse(vFtUserSignInfo.endTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.nameCn')">Name Cn</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.nameCn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.nameEn')">Name En</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.nameEn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.companyCn')">Company Cn</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.companyCn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.companyEn')">Company En</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.companyEn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.titleCn')">Title Cn</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.titleCn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.titleEn')">Title En</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.titleEn}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.remark')">Remark</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.remark}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.ddid')">Ddid</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.ddid}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.updateTime')">Update Time</span>
                    </dt>
                    <dd>
                        <span v-if="vFtUserSignInfo.updateTime">{{$d(Date.parse(vFtUserSignInfo.updateTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.createTime')">Create Time</span>
                    </dt>
                    <dd>
                        <span v-if="vFtUserSignInfo.createTime">{{$d(Date.parse(vFtUserSignInfo.createTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.signTime')">Sign Time</span>
                    </dt>
                    <dd>
                        <span v-if="vFtUserSignInfo.signTime">{{$d(Date.parse(vFtUserSignInfo.signTime), 'long') }}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('ftApp.vFtUserSignInfo.meetId')">Meet Id</span>
                    </dt>
                    <dd>
                        <span>{{vFtUserSignInfo.meetId}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="vFtUserSignInfo.id" :to="{name: 'VFtUserSignInfoFtEdit', params: {vFtUserSignInfoId: vFtUserSignInfo.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./v-ft-user-sign-info-ft-details.component.ts">
</script>
